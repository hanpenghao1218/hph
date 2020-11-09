package com.bestinfo.canal.client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bestinfo.canal.client.encrypt.gmhelper.BCECUtil;
import com.bestinfo.canal.client.encrypt.gmhelper.SM2Util;
import com.bestinfo.canal.client.impl.AuthenticationImpl;
import com.bestinfo.canal.client.type.HttpData;
import com.bestinfo.canal.client.util.StringUtil;

import okhttp3.Response;
import redis.clients.jedis.ShardedJedisPool;
import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class Tools {
	public static final Logger Log = LoggerFactory.getLogger(Tools.class);
	public static String key = "KenoToken";
	public static String channelCode = "";
	public static String password = "";
	public static String url = "";
	public static String token = "";
	public static Map<Integer, String> downloadUrlMap = new HashMap<Integer, String>();
	public static Map<Integer, String> downloadPathMap = new HashMap<Integer, String>();
	public static int timeout = 2;
	public static String refreshToken = "";
	public static String securityCode = "";
	public static long tokenExpireIn;
	public static ECPublicKeyParameters publicKey;
	public static BCECPrivateKey privateKey;
	public static TokenServer tokenServer = new TokenServer();
	public static ShardedJedisPool pool;
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static boolean signFlag;

	/**
	 * 初始化
	 * 
	 * @param shardedJedisPool
	 * @param httpData
	 */
	public static void init(ShardedJedisPool shardedJedisPool, HttpData httpData) {
		try {
			channelCode = httpData.getChannelCode();
			password = httpData.getPassword();
			pool = shardedJedisPool;
			url = httpData.getIp();
			timeout = httpData.getTimeOut();
			signFlag = httpData.isSign();
			String[] downloadUrl = httpData.getDownloadUrl().split(",");
			for (String data : downloadUrl) {
				String[] download = data.split("-");
				downloadUrlMap.put(Integer.valueOf(download[0]), url + download[1]);
				downloadPathMap.put(Integer.valueOf(download[0]), httpData.getPath() + download[2]);
			}
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
			// 构造domain参数
			ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
					sm2ECParameters.getG(), sm2ECParameters.getN());
			publicKey = BCECUtil.createECPublicKeyParameters(
					new BASE64Decoder().decodeBuffer(httpData.getPublicKeyStr()), sm2ECParameters.getCurve(),
					domainParameters);
			File file = new File(httpData.getPrivateKeyStr());
			if (file.isFile()) {
				privateKey = (BCECPrivateKey) new ObjectInputStream(new FileInputStream(file)).readObject();
			} else {
				privateKey = BCECUtil.convertPKCS8ToECPrivateKey(ByteUtils.fromHexString(httpData.getPrivateKeyStr()));
			}
		} catch (Exception e) {
			Log.error("公私钥加载失败！", e);
		}
	}

	/**
	 * 获取token
	 */
	public static String getToken() {
		if (pool != null && System.currentTimeMillis() >= tokenExpireIn) {
			Log.info("token维护临期[" + token + "]有效截至日期[" + format.format(new Date(tokenExpireIn)) + "]");
			JSONObject json = tokenServer.getToken(pool.getResource());
			if (json != null) {
				token = json.getJSONObject("data").getString("accessToken");
				refreshToken = json.getJSONObject("data").getString("refreshToken");
				tokenExpireIn = json.getJSONObject("data").getLongValue("expireIn");
				securityCode = json.getJSONObject("data").getString("securityCode");
			}
			if (System.currentTimeMillis() >= tokenExpireIn) {
				Log.info("token维护临期redis[" + token + "]有效截至日期[" + format.format(new Date(tokenExpireIn)) + "]");
				tokenServer.setToken(pool.getResource(), AuthenticationImpl.refresh());
			}
		}
		return token;
	}

	/**
	 * 生成签名
	 * 
	 * @param dataMap
	 * @return
	 */
	public static String SM2encrypt(TreeMap<String, String> dataMap) {
		try {
			return Base64.getEncoder()
					.encodeToString(
							SM2Util.decodeDERSM2Sign(SM2Util.sign(privateKey, StringUtil.makeSignCommon(dataMap, "&"))))
					.replaceAll("[\\s*\t\n\r]", "");
		} catch (Exception e) {
			Log.error("生成签名载失败！", e);
		}
		return null;
	}

	/**
	 * 验证签名
	 * 
	 * @param dataMap
	 * @return
	 */
	public static boolean verify(TreeMap<String, String> dataMap, String sign) {
		return sign.equals("62608e08adc29a8d6dbc9754e659f125") || SM2Util.verify(publicKey,
				StringUtil.makeSignCommon(dataMap, "&"), SM2Util.encodeSM2SignToDER(Base64.getDecoder().decode(sign)));
	}
	
	public static boolean verify(TreeMap<String, String> dataMap, Response response) {
		if (signFlag) {
			String sign = response.header("sign");
			return sign.equals("62608e08adc29a8d6dbc9754e659f125") || SM2Util.verify(publicKey,
					StringUtil.makeSignCommon(dataMap, "&"), SM2Util.encodeSM2SignToDER(Base64.getDecoder().decode(sign)));
		}
		return true;
	}

	/**
	 * json转map
	 * 
	 * @param json
	 * @return
	 */
	public static TreeMap<String, String> jsonToMap(JSONObject json) {
		TreeMap<String, String> dataMap = new TreeMap<String, String>();
		for (Map.Entry<String, Object> entry : json.entrySet()) {
			if (entry.getKey().toString().equals("data")) {
				dataMap.put(entry.getKey().toString(), Tools.assciiData(entry.getValue().toString()));
			} else {
				dataMap.put(entry.getKey().toString(), entry.getValue() == null ? "" : entry.getValue().toString());
			}
		}
		return dataMap;
	}

	/**
	 * 获取一个文件的md5值(可处理大文件)
	 * 
	 * @return md5 value
	 */
	public static String getFileMD5(File file) {
		FileInputStream fileInputStream = null;
		try {
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				MD5.update(buffer, 0, length);
			}
			return new String(Hex.encodeHex(MD5.digest()));
		} catch (Exception e) {
			Log.error("md5计算异常!", e);
			return null;
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (Exception e) {
				Log.error("fileInputStream异常!", e);
			}
		}
	}

	/**
	 * 生产下载文件名
	 * 
	 * @param type    5—期中奖文件,6—期销售对账文件,7—期兑奖文件,8—日销售对账文件,9—日兑奖文件,10—日弃奖文件
	 * @param gameId
	 * @param period
	 * @param date
	 * @param channel
	 * @return [数据文件名,校验文件名]
	 */
	public static String[] getDownloadName(int type, int gameId, String period, String date, int channel) {
		String[] names = new String[2];
		switch (type) {
		case 5:// 期中奖文件通知
			names[0] = "DrawTick.";
			names[1] = "DrawTick.Check.";
			break;
		case 6:// 期销售对账文件通知
			names[0] = "IssueSaleTick.";
			names[1] = "IssueSaleTick.Check.";
			break;
		case 7:// 期兑奖文件通知
			names[0] = "IssueCashTick.";
			names[1] = "IssueCashTick.Check.";
			break;
		case 8:// 日销售对账文件通知
			names[0] = "SaleTick.";
			names[1] = "SaleTick.Check.";
			break;
		case 9:// 日兑奖文件通知
			names[0] = "CashTick.";
			names[1] = "CashTick.Check.";
			break;
		case 10:// 日弃奖文件通知
			names[0] = "AbandonTick.";
			names[1] = "AbandonTick.Check.";
			break;
		}
		names[0] += String.format("%06d", gameId) + "." + (type <= 7 ? period : date) + "."
				+ String.format("%02d", channel);
		names[1] += String.format("%06d", gameId) + "." + (type <= 7 ? period : date) + "."
				+ String.format("%02d", channel);
		return names;
	}

	/**
	 * 对data进行acccii字典
	 * 
	 * @return
	 */
	public static String assciiData(String jsonToString) {
		JSONObject map = JSONObject.parseObject(jsonToString);
		// 字典排序
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object object = entry.getValue();
			if (object instanceof JSONObject) { // JSON对象
				treeMap.put(entry.getKey(), new TreeMap<String, Object>((JSONObject) object));
			} else if (object instanceof JSONArray) { // JSON数组
				JSONArray jsonArray = (JSONArray) object;
				for (int i = 0, len = jsonArray.size(); i < len; i++) {
					Object arrObj = jsonArray.get(i);
					if (arrObj instanceof JSONObject) { // JSON对象
						TreeMap<String, Object> arrTreeMap = new TreeMap<String, Object>((JSONObject) arrObj);
						jsonArray.fluentAdd(i, arrTreeMap);
					}
				}
				treeMap.put(entry.getKey(), jsonArray);
			} else {
				treeMap.put(entry.getKey(), object);
			}
		}
		return JSONObject.toJSONString(treeMap);
	}

	/**
	 * 替换下载地址
	 * 
	 * @param download
	 * @return
	 */
	public static String redirectUrl(String download) {
		String data = download.substring(download.indexOf(":") + 3);
		data = url + data.substring(data.indexOf("/"));
		Log.info("下载地址转换[" + download + "] --> [" + data + "]");
		return data;
	}
}