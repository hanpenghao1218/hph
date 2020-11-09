package com.bestinfo.canal.client.resp;

import com.alibaba.fastjson.JSONObject;

public class GetCancelTicketResp extends BaseResp {
	/** 销售站点编号 */
	public String stationCode;
	/** 票流水号 */
	public int tickNo;
	/** 促销类型 0—不促销,1—促销类型1,2—促销类型2,3—促销类型3 */
	public int promotionType;
	/** 投注金额（单位：分） */
	public long betMoney;
	/** 票面注数：复式票最多为1注，单式票最多为5注,而且玩法必须保持一致 */
	public int betMulti;
	/** 销售期号 */
	public String issueNum;
	/** 0—销售注销，1—冲正注销，2—中心注销，3—投注有效 */
	public int ticketStatus;
	/** 注销票站点编号 */
	public String cancelStationCode;
	/** 注销时间（单位：秒） */
	public long cancelTime;
	/** 多期期数 */
	public int multiIssues;
	/** 投注内容 */
	public String betNum;
	/** 游戏 ID */
	public int gameId;
	/** 出票时间（单位：秒） */
	public long sellTime;
	/** 验票码 */
	public String checkCode;
	/** 特征码 */
	public String featureCode;
	/** 积分卡号 */
	public String scoreCard;
	/** 注销权限：0—禁止，1—允许 */
	public int cancelPerm;

	public GetCancelTicketResp() {
	}

	public GetCancelTicketResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONObject data = json.getJSONObject("data");
			stationCode = data.getString("stationCode");
			tickNo = data.getIntValue("tickNo");
			promotionType = data.getIntValue("promotionType");
			betMoney = data.getLongValue("betMoney");
			betMulti = data.getIntValue("betMulti");
			issueNum = data.getString("issueNum");
			ticketStatus = data.getIntValue("ticketStatus");
			cancelStationCode = data.getString("cancelStationCode");
			cancelTime = data.getLongValue("cancelTime");
			multiIssues = data.getIntValue("multiIssues");
			betNum = data.getString("betNum");
			gameId = data.getIntValue("gameId");
			sellTime = data.getLongValue("sellTime");
			checkCode = data.getString("checkCode");
			featureCode = data.getString("featureCode");
			scoreCard = data.getString("scoreCard");
			cancelPerm = data.getIntValue("cancelPerm");
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

	public String getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public int getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(int ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getCancelStationCode() {
		return cancelStationCode;
	}

	public void setCancelStationCode(String cancelStationCode) {
		this.cancelStationCode = cancelStationCode;
	}

	public long getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(long cancelTime) {
		this.cancelTime = cancelTime;
	}

	public int getMultiIssues() {
		return multiIssues;
	}

	public void setMultiIssues(int multiIssues) {
		this.multiIssues = multiIssues;
	}

	public String getBetNum() {
		return betNum;
	}

	public void setBetNum(String betNum) {
		this.betNum = betNum;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public long getSellTime() {
		return sellTime;
	}

	public void setSellTime(long sellTime) {
		this.sellTime = sellTime;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public String getScoreCard() {
		return scoreCard;
	}

	public void setScoreCard(String scoreCard) {
		this.scoreCard = scoreCard;
	}

	public int getCancelPerm() {
		return cancelPerm;
	}

	public void setCancelPerm(int cancelPerm) {
		this.cancelPerm = cancelPerm;
	}
}