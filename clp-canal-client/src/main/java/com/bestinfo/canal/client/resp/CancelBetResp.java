package com.bestinfo.canal.client.resp;

import com.alibaba.fastjson.JSONObject;

public class CancelBetResp extends BaseResp {
	/** 注销票时间（单位：秒） */
	public long cancelTime;
	/** 游戏 ID */
	public int gameId;
	/** 游戏期号 */
	public String issueNum;
	/** 票号 */
	public int tickNo;
	/** 销售站点编号 */
	public int stationCode;
	/** 0：注销成功，1：已经注销 */
	public int stat;

	public CancelBetResp() {
	}

	public CancelBetResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONObject data = json.getJSONObject("data");
			cancelTime = data.getLongValue("cancelTime");
			gameId = data.getIntValue("gameId");
			issueNum = data.getString("issueNum");
			tickNo = data.getIntValue("tickNo");
			stationCode = data.getIntValue("stationCode");
			stat = data.getIntValue("stat");
		}
	}

	public long getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(long cancelTime) {
		this.cancelTime = cancelTime;
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

	public int getTickNo() {
		return tickNo;
	}

	public void setTickNo(int tickNo) {
		this.tickNo = tickNo;
	}

	public int getStationCode() {
		return stationCode;
	}

	public void setStationCode(int stationCode) {
		this.stationCode = stationCode;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}
}