package com.bestinfo.canal.client.req;

public class GetAbandonIssueReq extends BaseReq {
	/** 游戏 ID */
	public int gameId;
	/** 目标日期，格式 YYYYMMDD */
	public String targetDate = "";

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
}