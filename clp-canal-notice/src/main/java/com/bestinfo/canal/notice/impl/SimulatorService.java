package com.bestinfo.canal.notice.impl;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bestinfo.canal.client.encrypt.gmhelper.MD5Util;

@RestController
public class SimulatorService {
	private static final Logger Log = LoggerFactory.getLogger(SimulatorService.class);

	@RequestMapping(value = "/channel/trade/access_token", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void accessToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			JSONObject resp = new JSONObject();
			resp.put("code", 0);
			resp.put("msg", "交易成功");
			JSONObject data = new JSONObject();
			data.put("accessToken", "accessToken");
			data.put("expireIn", 99999999);
			data.put("refreshToken", "refreshToken");
			data.put("channelCode", 44);
			data.put("securityCode", "securityCode");
			resp.put("data", data);
			response.setHeader("sign", "clp-server");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(resp.toString());
		} catch (Exception e) {
			Log.error("获取token异常", e);
		}
	}

	@RequestMapping(value = "/channel/trade/refresh_token", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			JSONObject resp = new JSONObject();
			resp.put("code", 0);
			resp.put("msg", "交易成功");
			JSONObject data = new JSONObject();
			data.put("accessToken", "accessToken");
			data.put("expireIn", 99999999);
			data.put("refreshToken", "refreshToken");
			data.put("channelCode", 44);
			resp.put("data", data);
			response.setHeader("sign", "clp-server");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(resp.toString());
		} catch (Exception e) {
			Log.error("刷新token异常", e);
		}
	}

	@RequestMapping(value = "/channel/trade/synBetting", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void synBetting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject req = null;
		try {
			int len = request.getContentLength();
			byte[] reqData = new byte[len];
			try {
				ServletInputStream sis = request.getInputStream();
				sis.read(reqData, 0, len);
			} catch (Exception e) {
				e.printStackTrace();
			}
			req = JSONObject.parseObject(new String(reqData));
			String md5 = MD5Util.encrypt(req.getString("tickNo"));
			JSONObject resp = new JSONObject();
			resp.put("code", 0);
			resp.put("msg", "交易成功");
			JSONObject data = new JSONObject();
			data.put("gameId", req.getIntValue("gameId"));
			data.put("issueNum", req.getString("issueNum"));
			data.put("stationCode", req.getString("stationCode"));
			data.put("featureCode", md5);
			data.put("tickNo", req.getIntValue("tickNo"));
			data.put("sellTime", System.currentTimeMillis() / 1000);
			data.put("checkCode", md5.substring(16));
			data.put("address", "模拟器");
			resp.put("data", data);
			response.setHeader("sign", "clp-server");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(resp.toString());
		} catch (Exception e) {
			Log.error("投注模拟异常[" + req + "]", e);
		}
	}
	
	@RequestMapping(value = "/test/hello", method = RequestMethod.GET)
	@ResponseBody
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.getWriter().write("I`m working!");
	}
}