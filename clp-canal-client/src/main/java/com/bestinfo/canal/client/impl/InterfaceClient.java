package com.bestinfo.canal.client.impl;

import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.bestinfo.canal.client.req.*;
import com.bestinfo.canal.client.resp.*;
import com.bestinfo.canal.client.type.HttpData;
import com.bestinfo.canal.client.util.HttpClient;
import com.bestinfo.canal.client.util.Tools;

import redis.clients.jedis.ShardedJedisPool;

@Component
public class InterfaceClient {
	/**
	 * 参数初始化
	 * 
	 * @param shardedJedisPool 请求地址
	 * @param httpData         超时时间(秒)
	 */
	public static void init(ShardedJedisPool shardedJedisPool, HttpData httpData) {
		Tools.init(shardedJedisPool, httpData);
		HttpClient.init();
	}

	/**
	 * 接口调用
	 * 
	 * @param req
	 * @return
	 */
	public static BaseResp call(BaseReq req) {
		if (req.getClass().equals(CityReq.class)) {// 地市机构信息
			return new CityResp(HttpClient.doPost(Tools.url + "/channel/institution", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(StationReq.class)) {// 站点信息
			return new StationResp(HttpClient.doPost(Tools.url + "/channel/station", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(PermissionReq.class)) {// 站点权限
			return new PermissionResp(
					HttpClient.doPost(Tools.url + "/channel/station_permission", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(TerminalReq.class)) {// 设备信息
			return new TerminalResp(HttpClient.doPost(Tools.url + "/channel/terminal", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(HeartbeatReq.class)) {// 设备心跳
			return new HeartbeatResp(
					HttpClient.doPost(Tools.url + "/channel/terminal_heartbeat", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(CoatsReq.class)) {// 代销费汇总上报
			return new CoatsResp(HttpClient.doPost(Tools.url + "/channel/coats_total", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(GetSellingGameReq.class)) {// 查询可销售游戏
			return new GetSellingGameResp(HttpClient.doGet(Tools.url + "/channel/trade/getSellingGame"));
		} else if (req.getClass().equals(GetIssueReq.class)) {// 查询游戏期
			return new GetIssueResp(
					HttpClient.doPost(Tools.url + "/channel/trade/getIssue", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(SynBettingReq.class)) {// 同步投注
			TreeMap<String, String> dataMap = new TreeMap<String, String>();
			dataMap = Tools.jsonToMap(req.toJson());
			dataMap.put("securityCode", Tools.securityCode);
			return new SynBettingResp(HttpClient.doPost(Tools.url + "/channel/trade/synBetting", dataMap));
		} else if (req.getClass().equals(GetBetResultReq.class)) {// 查询投注结果
			return new GetBetResultResp(
					HttpClient.doPost(Tools.url + "/channel/trade/getBetResult", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(GetWinNoticeReq.class)) {// 查询开奖公告
			return new GetWinNoticeResp(
					HttpClient.doPost(Tools.url + "/channel/trade/getWinNotice", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(GetDrawNumReq.class)) {// 查询开奖号码
			return new GetDrawNumResp(
					HttpClient.doPost(Tools.url + "/channel/trade/getDrawNum", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(GetAbandonIssueReq.class)) {// 查询弃奖期
			return new GetAbandonIssueResp(
					HttpClient.doPost(Tools.url + "/channel/trade/getAbandonIssue", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(ReverseReq.class)) {// 冲正
			TreeMap<String, String> dataMap = new TreeMap<String, String>();
			dataMap = Tools.jsonToMap(req.toJson());
			dataMap.put("securityCode", Tools.securityCode);
			return new ReverseResp(HttpClient.doPost(Tools.url + "/channel/trade/reverse", dataMap));
		} else if (req.getClass().equals(GetCancelTicketReq.class)) {// 注销票查询
			return new GetCancelTicketResp(
					HttpClient.doPost(Tools.url + "/channel/trade/getCancelTicket ", Tools.jsonToMap(req.toJson())));
		} else if (req.getClass().equals(CancelBetReq.class)) {// 注销票
			TreeMap<String, String> dataMap = new TreeMap<String, String>();
			dataMap = Tools.jsonToMap(req.toJson());
			dataMap.put("securityCode", Tools.securityCode);
			return new CancelBetResp(HttpClient.doPost(Tools.url + "/channel/trade/cancelBet", dataMap));
		} else if (req.getClass().equals(GetAwardReq.class)) {// 兑奖
			TreeMap<String, String> dataMap = new TreeMap<String, String>();
			dataMap = Tools.jsonToMap(req.toJson());
			dataMap.put("securityCode", Tools.securityCode);
			return new GetAwardResp(HttpClient.doPost(Tools.url + "/channel/trade/getAward", dataMap));
		} else if (req.getClass().equals(GetWinTicketReq.class)) {// 查询中奖票
			return new GetWinTicketResp(
					HttpClient.doPost(Tools.url + "/channel/trade/getWinTicket", Tools.jsonToMap(req.toJson())));
		}
		return new BaseResp(99, "类型错误");
	}
}