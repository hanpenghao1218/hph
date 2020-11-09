package com.bestinfo.canal.client.req;

public class GetBetResultReq extends BaseReq {
	/** 站点编号 */
	public String stationCode = "";
	/** 查询期号 */
	public String issueNum = "";
	/** 游戏 ID */
	public int gameId;
	/** 票号 */
	public int tickNo;

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
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

	public int getTickNo() {
		return tickNo;
	}

	public void setTickNo(int tickNo) {
		this.tickNo = tickNo;
	}
}