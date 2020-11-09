package com.bestinfo.canal.client.req;

public class HeartbeatReq extends BaseReq {
	/** 销售终端序列号 */
	public String terminalSerial = "";

	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = terminalSerial;
	}
}