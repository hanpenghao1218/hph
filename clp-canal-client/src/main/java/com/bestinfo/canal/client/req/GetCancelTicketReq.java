package com.bestinfo.canal.client.req;

public class GetCancelTicketReq extends BaseReq {
	/** 游戏 ID */
	public int gameId;
	/** 票面特征码 */
	public String featureCode = "";
	/** 终端编号 */
	public String terminalSerial = "";

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = terminalSerial;
	}
}