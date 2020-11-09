package com.bestinfo.canal.client.req;

public class GetWinNoticeReq extends BaseReq {
	/** 游戏 ID */
	public int gameId;
	/** 游戏期号 */
	public String issueNum = "";
	/** 省码 */
	public String channelCode = "";

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

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
}