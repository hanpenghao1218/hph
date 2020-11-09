package com.bestinfo.canal.client.resp;

import com.alibaba.fastjson.JSONObject;

public class GetIssueResp extends BaseResp {
	/** 游戏 ID */
	public int gameId;
	/** 游戏期号 */
	public String issueNum;
	/** 开奖号码，用英文分号（#）分隔，未开奖期开奖号码为空 */
	public String drawNum;
	/** 期状态：0—未开始，1—销售中，2—已截期，3—已获得开奖号码，4—已获得开奖公告，5—已派奖 */
	public int status;
	/** 开始销售时间戳 */
	public long startSaleTime;
	/** 结束销售时间戳 */
	public long endSaleTime;
	/** 开奖时间戳 */
	public long drawTime;
	/** 截止兑奖时间戳 */
	public long endCashTime;
	/** 剩余可投注的多期期数 */
	public int leftIssue;
	/** 是否最后一期：1—否，2—是 */
	public int isLastIssue;
	/** 客户端时间 */
	public long clientTime;
	/** 服务器时间 */
	public long serverTime;
	/** 交易耗时 */
	public long costSecond;

	public GetIssueResp() {
	}

	public GetIssueResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONObject data = json.getJSONObject("data");
			gameId = data.getIntValue("gameId");
			issueNum = data.getString("issueNum");
			drawNum = data.getString("drawNum");
			status = data.getIntValue("status");
			startSaleTime = data.getLongValue("startSaleTime");
			endSaleTime = data.getLongValue("endSaleTime");
			drawTime = data.getLongValue("drawTime");
			endCashTime = data.getLongValue("endCashTime");
			leftIssue = data.getIntValue("leftIssue");
			isLastIssue = data.getIntValue("isLastIssue");
			clientTime = data.getLongValue("clientTime");
			serverTime = data.getLongValue("serverTime");
			costSecond = data.getLongValue("costSecond");
		}
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

	public String getDrawNum() {
		return drawNum;
	}

	public void setDrawNum(String drawNum) {
		this.drawNum = drawNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getStartSaleTime() {
		return startSaleTime;
	}

	public void setStartSaleTime(long startSaleTime) {
		this.startSaleTime = startSaleTime;
	}

	public long getEndSaleTime() {
		return endSaleTime;
	}

	public void setEndSaleTime(long endSaleTime) {
		this.endSaleTime = endSaleTime;
	}

	public long getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(long drawTime) {
		this.drawTime = drawTime;
	}

	public long getEndCashTime() {
		return endCashTime;
	}

	public void setEndCashTime(long endCashTime) {
		this.endCashTime = endCashTime;
	}

	public int getLeftIssue() {
		return leftIssue;
	}

	public void setLeftIssue(int leftIssue) {
		this.leftIssue = leftIssue;
	}

	public int getIsLastIssue() {
		return isLastIssue;
	}

	public void setIsLastIssue(int isLastIssue) {
		this.isLastIssue = isLastIssue;
	}

	public long getClientTime() {
		return clientTime;
	}

	public void setClientTime(long clientTime) {
		this.clientTime = clientTime;
	}

	public long getServerTime() {
		return serverTime;
	}

	public void setServerTime(long serverTime) {
		this.serverTime = serverTime;
	}

	public long getCostSecond() {
		return costSecond;
	}

	public void setCostSecond(long costSecond) {
		this.costSecond = costSecond;
	}
}