package com.bestinfo.canal.client.impl;

import com.alibaba.fastjson.JSONObject;
import com.bestinfo.canal.client.encrypt.gmhelper.MD5Util;
import com.bestinfo.canal.client.util.HttpClient;
import com.bestinfo.canal.client.util.Tools;

import java.util.Date;
import java.util.TreeMap;

public class AuthenticationImpl {
	/**
	 * 获取token
	 *
	 * @return json
	 */
	public static JSONObject getToken() {
		TreeMap<String, String> dataMap = new TreeMap<String, String>();
		dataMap.put("channelCode", Tools.channelCode);
		dataMap.put("channelSecret", MD5Util.encrypt(MD5Util.encrypt(Tools.password)));
		JSONObject result = HttpClient.doPost(Tools.url + "/channel/access_token", dataMap, "");
		if (result.getInteger("code") == 0) {
			Tools.token = result.getJSONObject("data").getString("accessToken");
			Tools.refreshToken = result.getJSONObject("data").getString("refreshToken");
			Tools.securityCode = result.getJSONObject("data").getString("securityCode");
			Tools.tokenExpireIn = System.currentTimeMillis()
					+ result.getJSONObject("data").getLongValue("expireIn") * 800;
			Tools.Log.info("token维护初始化[{}], 有效截至时间[{}]", Tools.token,
					Tools.format.format(new Date(Tools.tokenExpireIn)));
		}
		return result;
	}

	/**
	 * 刷新token
	 *
	 * @return json
	 */
	public static JSONObject refresh() {
		TreeMap<String, String> dataMap = new TreeMap<String, String>();
		dataMap.put("channelCode", Tools.channelCode);
		dataMap.put("refreshToken", Tools.refreshToken);
		JSONObject result = HttpClient.doPost(Tools.url + "/channel/refresh_token", dataMap, Tools.token);
		if (result.getInteger("code") == 0) {
			JSONObject data = result.getJSONObject("data");
			Tools.token = data.getString("accessToken");
			Tools.refreshToken = data.getString("refreshToken");
			Tools.tokenExpireIn = System.currentTimeMillis() + data.getLongValue("expireIn") * 800;
			data.put("securityCode", Tools.securityCode);
			result.put("data", data);
			Tools.Log.info("token维护刷新[{}], 有效截至时间[{}]", Tools.token,
					Tools.format.format(new Date(Tools.tokenExpireIn)));
		}
		return result;
	}

	/**
	 * 时间同步
	 *
	 * @return json
	 */
	public static JSONObject syncTime() {
		TreeMap<String, String> dataMap = new TreeMap<String, String>();
		dataMap.put("channelTime", String.valueOf(System.currentTimeMillis()));
		return HttpClient.doPost(Tools.url + "/channel/sync_time", dataMap);
	}

}