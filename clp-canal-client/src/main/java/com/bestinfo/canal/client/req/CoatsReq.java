package com.bestinfo.canal.client.req;

public class CoatsReq extends BaseReq {
	/** 渠道编号 */
	public String insCode = "";
	/** 游戏 ID */
	public int gameId;
	/** 游戏期号 */
	public String issueNum = "";
	/** 代销费汇总 */
	public long costsTotal;

	public String getInsCode() {
		return insCode;
	}

	public void setInsCode(String insCode) {
		this.insCode = insCode;
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

	public long getCostsTotal() {
		return costsTotal;
	}

	public void setCostsTotal(long costsTotal) {
		this.costsTotal = costsTotal;
	}
}