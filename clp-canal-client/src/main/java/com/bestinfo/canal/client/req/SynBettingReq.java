package com.bestinfo.canal.client.req;

public class SynBettingReq extends BaseReq {
	/** 流水号 */
	public String flowid = "";
	/** 游戏 ID */
	public int gameId;
	/** 游戏期号 */
	public String issueNum = "";
	/** 站点编号 */
	public String stationCode = "";
	/** 票号，按站按期从 1 开始递增，只需唯一可不连续，最大值不能超过 1 百万 */
	public int tickNo;
	/** 终端编号 */
	public String terminalSerial = "";
	/** 渠道订单编号 */
	public String channelOrderId = "";
	/** 促销类型（缺失时默认 0）：0—不促销，1—促销类型 1,2—促销类型 2，3—促销类型 3 */
	public int promotionType;
	/** 投注金额（单位：分） */
	public long betMoney;
	/** 票面注数：复式票最多为 1 注，单式票最多为 5 注，而且玩法、倍数必须保持一致 */
	public int betMulti;
	/** 投注内容 */
	public String betNum = "";
	/** 连续销售期数，可为空，不填默认为 */
	public int multiIssues = 1;
	/** 选号方式：0—其它方式，1—手选，2—机选，3—手选加机选，4—投注单，5—外接设备，6—重打票 */
	public int choiceMode;
	/** 积分账户 */
	public String scoreCard = "";

	public String getFlowid() {
		return flowid;
	}

	public void setFlowid(String flowid) {
		this.flowid = flowid;
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

	public int getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(int promotionType) {
		this.promotionType = promotionType;
	}

	public long getBetMoney() {
		return betMoney;
	}

	public void setBetMoney(long betMoney) {
		this.betMoney = betMoney;
	}

	public int getBetMulti() {
		return betMulti;
	}

	public void setBetMulti(int betMulti) {
		this.betMulti = betMulti;
	}

	public String getBetNum() {
		return betNum;
	}

	public void setBetNum(String betNum) {
		this.betNum = betNum;
	}

	public int getMultiIssues() {
		return multiIssues;
	}

	public void setMultiIssues(int multiIssues) {
		this.multiIssues = multiIssues;
	}

	public int getChoiceMode() {
		return choiceMode;
	}

	public void setChoiceMode(int choiceMode) {
		this.choiceMode = choiceMode;
	}

	public String getScoreCard() {
		return scoreCard;
	}

	public void setScoreCard(String scoreCard) {
		this.scoreCard = scoreCard;
	}
}