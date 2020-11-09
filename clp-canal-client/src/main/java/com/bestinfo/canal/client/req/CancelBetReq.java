package com.bestinfo.canal.client.req;

public class CancelBetReq extends BaseReq {
	/** 流水号 */
	public String flowid = "";
	/** 票面特征码 */
	public String featureCode = "";
	/** 站点编号 */
	public String stationCode = "";
	/** 游戏 ID */
	public int gameId;
	/** 注销类型 1-普通注销 2-中心注销，默认 1 */
	public int cancelType = 1;
	/** 终端编号 */
	public String terminalSerial = "";

	public String getFlowid() {
		return flowid;
	}

	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}

	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getCancelType() {
		return cancelType;
	}

	public void setCancelType(int cancelType) {
		this.cancelType = cancelType;
	}

	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = terminalSerial;
	}
}