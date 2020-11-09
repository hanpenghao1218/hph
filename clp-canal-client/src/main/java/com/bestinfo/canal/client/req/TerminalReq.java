package com.bestinfo.canal.client.req;

public class TerminalReq extends BaseReq {
	/** 销售终端序列号（唯一） */
	public String terminalSerial = "";
	/** 销售终端编码 */
	public String terminalCode = "";
	/** 所属站点编号 */
	public String stationCode = "";
	/** 销售终端名称 */
	public String terminalName = "";
	/** 销售终端类型（0：投注机，1：自助终端） */
	public int terminalType;
	/** 销售终端型号 */
	public String terminalModel = "";
	/** 销售终端安装时间 */
	public String installTime = "";
	/** 销售终端拆除时间 */
	public String removeTime = "";

	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = terminalSerial;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public int getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(int terminalType) {
		this.terminalType = terminalType;
	}

	public String getTerminalModel() {
		return terminalModel;
	}

	public void setTerminalModel(String terminalModel) {
		this.terminalModel = terminalModel;
	}

	public String getInstallTime() {
		return installTime;
	}

	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}

	public String getRemoveTime() {
		return removeTime;
	}

	public void setRemoveTime(String removeTime) {
		this.removeTime = removeTime;
	}
}