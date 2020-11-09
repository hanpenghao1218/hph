package com.bestinfo.canal.client.req;

public class GetDrawNumReq extends BaseReq {
	/** 游戏 ID */
	public int gameId;
	/** 游戏期号 */
	public String issueNum = "";

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
}