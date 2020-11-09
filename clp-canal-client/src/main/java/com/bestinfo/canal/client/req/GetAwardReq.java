package com.bestinfo.canal.client.req;

public class GetAwardReq extends BaseReq {
	/** 流水号 */
	public String flowid = "";
	/** 票面特征码 */
	public String featureCode = "";
	/** 验票码 */
	public String checkCode = "";
	/** 站点编号 */
	public String stationCode = "";
	/** 兑奖类型 1-普通兑奖 2-中心兑奖 */
	public int cashType;
	/** 游戏 ID */
	public int gameId;
	/** 销售终端序列号（唯一） */
	public String terminalSerial = "";
	/** 渠道订单编号 */
	public String channelOrderId = "";

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

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public int getCashType() {
		return cashType;
	}

	public void setCashType(int cashType) {
		this.cashType = cashType;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = terminalSerial;
	}

	public String getChannelOrderId() {
		return channelOrderId;
	}

	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}
}