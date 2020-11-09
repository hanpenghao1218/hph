package com.bestinfo.canal.client.req;

public class GetIssueReq extends BaseReq {
	/** 游戏 ID */
	public int gameId;
	/** 游戏期号 */
	public String issueNum = "";
	/** 客户端时间，毫秒数 */
	public long clientTime = System.currentTimeMillis();

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

	public long getClientTime() {
		return clientTime;
	}

	public void setClientTime(long clientTime) {
		this.clientTime = clientTime;
	}
}