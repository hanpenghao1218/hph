package com.bestinfo.canal.client.resp;

import com.alibaba.fastjson.JSONObject;

public class ReverseResp extends BaseResp {
	/** 冲正时间（单位：秒） */
	public long reverseTime;
	/** 票号 */
	public int tickNo;
	/** 游戏期号 */
	public String issueNum;
	/** 游戏 ID */
	public int gameId;
	/** 站点编号 */
	public String stationCode;

	public ReverseResp() {
	}

	public ReverseResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONObject data = json.getJSONObject("data");
			reverseTime = data.getLongValue("reverseTime");
			tickNo = data.getIntValue("tickNo");
			issueNum = data.getString("issueNum");
			gameId = data.getIntValue("gameId");
			stationCode = data.getString("stationCode");
		}
	}

	public long getReverseTime() {
		return reverseTime;
	}

	public void setReverseTime(long reverseTime) {
		this.reverseTime = reverseTime;
	}

	public int getTickNo() {
		return tickNo;
	}

	public void setTickNo(int tickNo) {
		this.tickNo = tickNo;
	}

	public String getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
}