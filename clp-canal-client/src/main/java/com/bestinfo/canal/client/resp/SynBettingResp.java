package com.bestinfo.canal.client.resp;

import com.alibaba.fastjson.JSONObject;

public class SynBettingResp extends BaseResp {
	/** 游戏 ID */
	public int gameId;
	/** 游戏期号 */
	public String issueNum;
	/** 站点编号 */
	public String stationCode;
	/** 特征码 */
	public String featureCode;
	/** 票号 */
	public int tickNo;
	/** 销售系统实际出票时间（单位：秒） */
	public long sellTime;
	/** 验票码 */
	public String checkCode;
	/** 出票销售站点地址 */
	public String address;

	public SynBettingResp() {
	}

	public SynBettingResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONObject data = json.getJSONObject("data");
			gameId = data.getIntValue("gameId");
			issueNum = data.getString("issueNum");
			stationCode = data.getString("stationCode");
			featureCode = data.getString("featureCode");
			tickNo = data.getIntValue("tickNo");
			sellTime = data.getLongValue("sellTime");
			checkCode = data.getString("checkCode");
			address = data.getString("address");
		}
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public int getTickNo() {
		return tickNo;
	}

	public void setTickNo(int tickNo) {
		this.tickNo = tickNo;
	}

	public long getSellTime() {
		return sellTime;
	}

	public void setSellTime(long sellTime) {
		this.sellTime = sellTime;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}