package com.bestinfo.canal.client.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Date;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bestinfo.canal.client.impl.AuthenticationImpl;
import com.bestinfo.canal.client.type.ErrorCode;

@Component
public class HttpClient {
	private static final Logger Log = LoggerFactory.getLogger(HttpClient.class);
	private static OkHttpClient client;
	private static AtomicLong atomicLong = new AtomicLong(0);

	/**
	 * 初始化
	 */
	public static void init() {
		try {
			OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
					.connectTimeout(Tools.timeout, TimeUnit.SECONDS).writeTimeout(Tools.timeout, TimeUnit.SECONDS)
					.readTimeout(Tools.timeout, TimeUnit.SECONDS)
					.connectionPool(new ConnectionPool(100, 10, TimeUnit.MINUTES));
			TrustManager[] trustAllCerts = buildTrustManagers();
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			client = clientBuilder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
					.hostnameVerifier((hostname, session) -> true).build();
			heartbeat();
		} catch (Exception e) {
			Log.error("初始化异常！", e);
		}
	}

	private static TrustManager[] buildTrustManagers() {
		return new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}
		} };
	}

	/**
	 * get请求
	 *
	 * @param url
	 * @return
	 */
	public static JSONObject doGet(String url) {
		JSONObject resp = new JSONObject();
		long i = atomicLong.get();
		Response response = null;
		try {
			Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/json")
					.addHeader("Channel-Code", Tools.channelCode).addHeader("Access-Token", Tools.getToken())
					.addHeader("Sign", Tools.SM2encrypt(new TreeMap<String, String>())).addHeader("Version", "1.0.0")
					.addHeader("TimeStamp", String.valueOf(System.currentTimeMillis())).build();
			Log.info("HttpClientGet-[" + i + "]" + request.toString() + "["
					+ request.headers().toString().replaceAll("\\r|\\n", " ") + "]");
			response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				JSONObject result = JSONObject.parseObject(response.body().string());
				Log.info("HttpClientGet-[" + i + "]" + response + "["
						+ response.headers().toString().replaceAll("\\r|\\n", " ") + "]" + result);
				if (result.getIntValue("code") == 0) {
					if (result.containsKey("data")) {
						resp = result;
					} else {
						resp.put("code", ErrorCode.SUCCESS.getCode());
						resp.put("msg", ErrorCode.SUCCESS.getMsg());
					}
				} else {
					if (result.getIntValue("code") == 2004) {
						Log.info("[" + i + "]token维护失效[" + Tools.token + "]有效截至时间["
								+ Tools.format.format(new Date(Tools.tokenExpireIn)) + "]");
						Tools.tokenServer.setToken(Tools.pool.getResource(), AuthenticationImpl.getToken());
					}
					resp.put("code", result.getString("code"));
					resp.put("msg", result.getString("msg"));
					Log.error("[" + i + "]get请求错误[" + result + "]");
				}
			} else {
				resp.put("code", ErrorCode.HTTPFAIL.getCode());
				resp.put("msg", ErrorCode.HTTPFAIL.getMsg());
				Log.error("[" + i + "]get请求失败[" + response.toString() + "]");
			}
		} catch (Exception e) {
			Log.error("[" + i + "]get请求异常！ ", e);
			if (e.getMessage().equals("connect timed out")) {
				resp.put("code", ErrorCode.TIMEOUT.getCode());
				resp.put("msg", ErrorCode.TIMEOUT.getMsg());
			} else {
				resp.put("code", ErrorCode.EXCEPTION.getCode());
				resp.put("msg", ErrorCode.EXCEPTION.getMsg());
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return resp;
	}

	/**
	 * post请求
	 *
	 * @param url     请求url
	 * @param dataMap post data
	 * @param token   请求token
	 * @return json object
	 */
	public static JSONObject doPost(String url, TreeMap<String, String> dataMap, String token) {
		JSONObject resp = new JSONObject();
		long i = atomicLong.getAndAdd(1);
		Response response = null;
		try {
			Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/json")
					.addHeader("Channel-Code", Tools.channelCode).addHeader("Access-Token", token)
					.addHeader("Sign", Tools.SM2encrypt(dataMap)).addHeader("Version", "1.0.0")
					.addHeader("TimeStamp", String.valueOf(System.currentTimeMillis())).post(RequestBody
							.create(MediaType.parse("application/json;charset=UTF-8"), GsonUtil.toJson(dataMap)))
					.build();
			Log.info("HttpClientPost[{}] request[{}], header[{}], data:[{}]", i, request.toString(),
					replaceLF(request.headers().toString()), replaceLF(dataMap.toString()));
			response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				JSONObject result = JSONObject.parseObject(response.body().string());
				Log.info("HttpClientPost-[" + i + "]" + response.toString() + "["
						+ replaceLF(response.headers().toString()) + "]" + result);
				if (result.getIntValue("code") == 0) {
					if (result.containsKey("data")) {
						if (Tools.verify(Tools.jsonToMap(result), response)) {
							resp = result;
						} else {
							Log.error("[" + i + "]post请求验签错误");
							resp.put("code", ErrorCode.SIGNERROR.getCode());
							resp.put("msg", ErrorCode.SIGNERROR.getMsg());
						}
					} else {
						resp.put("code", ErrorCode.SUCCESS.getCode());
						resp.put("msg", ErrorCode.SUCCESS.getMsg());
					}
				} else {
					if (result.getIntValue("code") == ErrorCode.CLP_TOKEN_AUTH_FAIL.getCode()
							|| result.getIntValue("code") == ErrorCode.CLP_TOKEN_AUTH_ILLEGAL.getCode()) {
						Log.info("[" + i + "]token维护失效[" + Tools.token + "]有效截至时间["
								+ Tools.format.format(new Date(Tools.tokenExpireIn)) + "]");
						Tools.tokenServer.setToken(Tools.pool.getResource(), AuthenticationImpl.getToken());
					}
					resp.put("code", result.getString("code"));
					resp.put("msg", result.getString("msg"));
					Log.error("post[{}] 请求错误 result [{}]", i, result);
				}
			} else {
				Log.error("post request[{}] fail, response:{}, response headers:[{}]", i, response.toString(),
						replaceLF(response.headers().toString()));
				resp.put("code", ErrorCode.READTIMEOUT.getCode());
				resp.put("msg", ErrorCode.READTIMEOUT.getMsg());
			}
		} catch (Exception e) {
			Log.error("[" + i + "]post请求异常！ ", e);
			if (e.getClass().equals(SocketTimeoutException.class)) {
				resp.put("code", ErrorCode.READTIMEOUT.getCode());
				resp.put("msg", ErrorCode.READTIMEOUT.getMsg());
			} else {
				resp.put("code", ErrorCode.READTIMEOUT.getCode());
				resp.put("msg", ErrorCode.READTIMEOUT.getMsg());
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return resp;
	}

	public static String replaceLF(String s) {
		return s.replaceAll("\\r|\\n", " ");
	}

	/**
	 * post请求(默认token)
	 *
	 * @param url
	 * @param dataMap
	 * @return
	 */
	public static JSONObject doPost(String url, TreeMap<String, String> dataMap) {
		return doPost(url, dataMap, Tools.getToken());
	}

	/**
	 * 文件下载
	 *
	 * @param downUrl  下载url
	 * @param downPath 本地路径
	 * @return
	 */
	public static boolean download(String downUrl, String downPath) {
		try {
			Log.info("开始下载：" + downUrl);
			URL url = new URL(downUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(Tools.timeout * 1000);
			InputStream inputStream = conn.getInputStream();
			File file = new File(downPath);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(readInputStream(inputStream));
			if (fos != null) {
				fos.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (Exception e) {
			Log.error("下载异常！", e);
			return false;
		}
		Log.info("下载成功：" + downPath);
		return true;
	}

	private static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * 心跳(维护token有效)
	 */
	private static void heartbeat() {
		if (Tools.pool != null) {
			JSONObject json = Tools.tokenServer.getToken(Tools.pool.getResource());
			if (json != null) {
				Tools.token = json.getJSONObject("data").getString("accessToken");
				Tools.refreshToken = json.getJSONObject("data").getString("refreshToken");
				Tools.tokenExpireIn = System.currentTimeMillis()
						+ json.getJSONObject("data").getLongValue("expireIn") * 1000;
				Tools.securityCode = json.getJSONObject("data").getString("securityCode");
				Log.info("token维护redis[" + Tools.token + "]有效截至日期[" + Tools.format.format(new Date(Tools.tokenExpireIn))
						+ "]");
			} else {
				Tools.tokenServer.setToken(Tools.pool.getResource(), AuthenticationImpl.getToken());
			}
			new Thread(() -> {
				try {
					while (true) {
						AuthenticationImpl.syncTime();
						Thread.sleep(60000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}

}