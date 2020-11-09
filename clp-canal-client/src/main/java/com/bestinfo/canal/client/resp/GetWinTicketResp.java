package com.bestinfo.canal.client.resp;

import com.alibaba.fastjson.JSONObject;

public class GetWinTicketResp extends BaseResp {
	/** 站点编号 */
	public String stationCode;
	/** 票号 */
	public int tickNo;
	/** 游戏期号 */
	public String issueNum;
	/** 总中奖金额（单位：分） */
	public long totalPrize;
	/** 税额（单位：分） */
	public long tax;
	/** 中奖明细：注序号#中奖奖等编号#中奖奖等名称#该奖等中奖个数#倍数#单注中奖金额（单位：分）#单注中奖税（单位：分）#开奖期号#| */
	public String winDetail;
	/** 兑奖时间（单位：秒） */
	public long awardTime;
	/** 截止兑奖时间（单位：秒） */
	public long endAwardTime;
	/** 票状态：0—未兑奖，1—站点兑奖，2—中心兑奖，3—已弃奖 */
	public int ticketStatus;
	/** 兑奖站点编号 */
	public String awardStationCode;
	/** 游戏 ID */
	public int gameId;
	/** 兑奖期号（如果没兑奖则给空） */
	public String cashIssue;

	public GetWinTicketResp() {
	}

	public GetWinTicketResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONObject data = json.getJSONObject("data");
			stationCode = data.getString("stationCode");
			tickNo = data.getIntValue("tickNo");
			issueNum = data.getString("issueNum");
			totalPrize = data.getLongValue("totalPrize");
			tax = data.getLongValue("tax");
			winDetail = data.getString("winDetail");
			awardTime = data.getLongValue("awardTime");
			endAwardTime = data.getLongValue("endAwardTime");
			ticketStatus = data.getIntValue("ticketStatus");
			awardStationCode = data.getString("awardStationCode");
			gameId = data.getIntValue("gameId");
			cashIssue = data.getString("cashIssue");
		}
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public int getTickNo() {
		return tickNo;
	}

	public void setTickNo(int tickNo) {
		this.tickNo = tickNo;
	}

	public String getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public long getTotalPrize() {
		return totalPrize;
	}

	public void setTotalPrize(long totalPrize) {
		this.totalPrize = totalPrize;
	}

	public long getTax() {
		return tax;
	}

	public void setTax(long tax) {
		this.tax = tax;
	}

	public String getWinDetail() {
		return winDetail;
	}

	public void setWinDetail(String winDetail) {
		this.winDetail = winDetail;
	}

	public long getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(long awardTime) {
		this.awardTime = awardTime;
	}

	public long getEndAwardTime() {
		return endAwardTime;
	}

	public void setEndAwardTime(long endAwardTime) {
		this.endAwardTime = endAwardTime;
	}

	public int getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(int ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getAwardStationCode() {
		return awardStationCode;
	}

	public void setAwardStationCode(String awardStationCode) {
		this.awardStationCode = awardStationCode;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getCashIssue() {
		return cashIssue;
	}

	public void setCashIssue(String cashIssue) {
		this.cashIssue = cashIssue;
	}
}