package com.bestinfo.canal.client.encrypt;

import com.bestinfo.canal.client.encrypt.gmhelper.MD5Util;
import com.bestinfo.canal.client.encrypt.gmhelper.SM2Util;
import com.bestinfo.canal.client.encrypt.gmhelper.SM3Util;
import com.bestinfo.canal.client.encrypt.gmhelper.SM4Util;
import com.bestinfo.canal.client.util.GsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.math.BigInteger;
import java.util.*;

/**
 * 签名帮助类
 *
 * @author huliang
 * @version $Id: SignUtil.java, v 0.1 2013年10月25日 下午3:33:38 huliang Exp $
 */
public final class EncryptUtil {

	/**
	 * md5签名方式
	 * 
	 * @param params
	 * @param signKey
	 * @return
	 */
	public static final String md5sign1(Map<String, String> params, String signKey) {
		// 根据元素的自然顺序按升序进行排序
		List<String> keys = Arrays.asList(params.keySet().toArray(new String[params.size()]));
		Collections.sort(keys);

		// 根据不同的签名方法计算签名值
		StringBuilder sb = new StringBuilder();
		sb.append(signKey);
		for (String key : keys) {
			String value = params.get(key);
			if (StringUtils.isEmpty(value) || "sign".equals(key)) {
				continue;
			}
			sb.append(key).append(StringUtils.trim(value));
		}
		sb.append(signKey);
		return MD5Util.encrypt(sb.toString());
	}

	/**
	 * md5签名方式
	 * 
	 * @param params
	 * @param signKey
	 * @return
	 */
	public static final String md5sign2(Map<String, Object> params, String signKey) {
		// 根据元素的自然顺序按升序进行排序
		List<String> keys = Arrays.asList(params.keySet().toArray(new String[params.size()]));
		Collections.sort(keys);

		// 根据不同的签名方法计算签名值
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			String value = (String) params.get(key);
			if (StringUtils.isEmpty(value) || "sign".equals(key)) {
				continue;
			}
			sb.append(key).append("=").append(StringUtils.trim(value)).append("&");
		}
		sb.append(signKey);
		return MD5Util.encrypt(sb.toString());
	}

	/**
	 * sm3签名方式
	 * 
	 * @param params
	 * @param signKey
	 * @return
	 */
	public static final String sm3sign(Map<String, String> params, String signKey) {
		// 根据元素的自然顺序按升序进行排序
		List<String> keys = Arrays.asList(params.keySet().toArray(new String[params.size()]));
		Collections.sort(keys);

		// 根据不同的签名方法计算签名值
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			String value = params.get(key);
			if (StringUtils.isEmpty(value) || "sign".equals(key)) {
				continue;
			}
			sb.append(key).append("=").append(StringUtils.trim(value)).append("&");
		}
		sb.append(signKey);
		byte[] hash = SM3Util.hash(ByteUtils.fromHexString(sb.toString()));
		return ByteUtils.toHexString(hash);
	}

	/**
	 * 解密sm2加密的秘钥
	 * 
	 * @param enSm2KeyNumy
	 * @param priKey
	 * @return
	 */
	public static String decSm2SecretKey(String enSm2KeyNumy, String priKey) {
		try {
			// 获取一条SM2曲线参数
			X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
			// 构造domain参数
			ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
					sm2ECParameters.getG(), sm2ECParameters.getN());

			BigInteger privateKeyD = new BigInteger(priKey, 16);
			ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(privateKeyD, domainParameters);

			byte[] decryptedData = SM2Util.decrypt(privateKeyParameters, ByteUtils.fromHexString(enSm2KeyNumy));
			return ByteUtils.toHexString(decryptedData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 加密sm2秘钥
	 * 
	 * @param keyNum
	 * @return
	 */
	public static String encSm2SecretKey(String keyNum, String pubKey) {
		try {
			// 获取一条SM2曲线参数
			X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
			// 提取公钥点 CommonUtils.hexString2byte(pubKey)
			ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(ByteUtils.fromHexString(pubKey));
			// 构造domain参数
			ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(),
					sm2ECParameters.getG(), sm2ECParameters.getN());
			// 公钥前面的02或者03表示是压缩公钥，04表示未压缩公钥, 04的时候，可以去掉前面的04
			ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);
			byte[] decryptedData = SM2Util.encrypt(publicKeyParameters, ByteUtils.fromHexString(keyNum));
			return ByteUtils.toHexString(decryptedData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解密Sm4加密数据
	 * 
	 * @param enSm4Data Sm4加密数据
	 * @param keyNum    数据加密秘钥
	 * @return
	 */
	public static Map<String, String> decSm4Data(String enSm4Data, String keyNum) {
		Map<String, String> retMap = new HashMap<>();
		try {
			byte[] decryptedData = SM4Util.decrypt_Ecb_Padding(ByteUtils.fromHexString(keyNum),
					ByteUtils.fromHexString(enSm4Data));
			String dataStr = new String(decryptedData);// 这里为什么只能new String,因为加密时用的是str.getBytes()
			if (StringUtils.isEmpty(dataStr)) {
				return null;
			}
			String[] keyValues = dataStr.split("&");
			for (String keyValue : keyValues) {
				if (!StringUtils.isEmpty(keyValue)) {
					String[] kvs = keyValue.split("=");
					if (kvs.length <= 0) {
						continue;
					} else if (kvs.length == 1) {
						retMap.put(kvs[0], "");
					} else {
						retMap.put(kvs[0], kvs[1]);
					}
				}
			}
			return retMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 加密Sm4数据
	 * 
	 * @param dataMap Sm4加密数据
	 * @param keyNum  数据加密秘钥
	 * @return
	 */
	public static String encSm4Data(Map<String, String> dataMap, String keyNum) {
		// 拼接参数
		Set<String> kes = dataMap.keySet();
		Iterator<String> itKeys = kes.iterator();
		StringBuilder sb = new StringBuilder();
		while (itKeys.hasNext()) {
			String key = itKeys.next();
			Object value = dataMap.get(key);
			if (value == null) {
				continue;
			}
			if (value instanceof String) {
				if (StringUtils.isEmpty((String) value)) {
					continue;
				}
				sb.append(key).append("=").append(value).append("&");
			} else {
				sb.append(key).append("=").append(GsonUtil.toJson(value)).append("&");
			}
		}
		String dataString = sb.toString().substring(0, sb.toString().lastIndexOf("&"));
		try {
			byte[] cipherText = SM4Util.encrypt_Ecb_Padding(ByteUtils.fromHexString(keyNum), dataString.getBytes());
			return ByteUtils.toHexString(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}