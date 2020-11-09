package com.bestinfo.canal.client.req;

public class ReverseReq extends BaseReq {
	/** 流水号 */
	public String flowid = "";
	/** 站点编号 */
	public String stationCode = "";
	/** 游戏 ID */
	public int gameId;
	/** 投注期号 */
	public String issueNum = "";
	/** 票号 */
	public int tickNo;
	/** 终端编号 */
	public String terminalSerial = "";
	
	public String getFlowid() {
		return flowid;
	}

	public void setFlowid(String flowid) {
		this.flowid = flowid;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = terminalSerial;
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
}