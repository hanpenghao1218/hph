package com.bestinfo.canal.client.resp;

import com.alibaba.fastjson.JSONObject;

public class GetAwardResp extends BaseResp {
	/** 兑奖时间 */
	public long awardTime;
	/** 游戏 ID */
	public long gameId;
	/** 站点编号 */
	public String stationCode;
	/** 票号 */
	public int tickNo;
	/** 兑奖金额（税前，单位：分） */
	public long cashValue;
	/** 开奖期号 */
	public String issueNum;
	/** 0：兑奖成功，1：已兑奖 */
	public int stat;

	public GetAwardResp() {
	}

	public GetAwardResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONObject data = json.getJSONObject("data");
			awardTime = data.getLongValue("awardTime");
			gameId = data.getLongValue("gameId");
			stationCode = data.getString("stationCode");
			tickNo = data.getIntValue("tickNo");
			cashValue = data.getLongValue("cashValue");
			issueNum = data.getString("issueNum");
			stat = data.getIntValue("stat");
		}
	}

	public long getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(long awardTime) {
		this.awardTime = awardTime;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public int getTickNo() {
		return tickNo;
	}

	public void setTickNo(int tickNo) {
		this.tickNo = tickNo;
	}

	public long getCashValue() {
		return cashValue;
	}

	public void setCashValue(long cashValue) {
		this.cashValue = cashValue;
	}

	public String getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}
}