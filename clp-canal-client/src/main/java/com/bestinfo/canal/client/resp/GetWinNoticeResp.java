package com.bestinfo.canal.client.resp;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestinfo.canal.client.type.NoticeDetail;
import com.bestinfo.canal.client.type.WinLevel;
import com.bestinfo.canal.client.type.WinPools;

public class GetWinNoticeResp extends BaseResp {
	/** 开奖号码 */
	public String drawNum;
	/** 游戏期号 */
	public String issueNum;
	/** 游戏 ID */
	public int gameId;
	/** 总销量（单位：分） */
	public long totalSales;
	/** 总中奖额（单位：分） */
	public long totalPrize;
	/** 本期选十固定奖应付奖金（单位：分）*/
	public long prePaymentFloat;
	/** 本期选十固定奖实付奖金（单位：分） */
	public long actuallyAmountFloat;
	/** 本期非选十固定奖应付奖金（单位：分） */
	public long prePaymentOther;
	/** 选十奖池触发风控玩法名称，多个玩法名称用#号分割 */
	public String riskPlayNameFloat;
	/** 本期非选十固定奖实付奖金（单位：分） */
	public long actuallyAmountOther;
	/** 非选十奖池触发风控玩法名称，多个玩法名称用#号分割 */
	public String riskPlayNameOther;
	/** 奖等明细 */
	public List<NoticeDetail> noticeDetailList = new ArrayList<NoticeDetail>();
	/** 奖池信息 */
	public List<WinPools> winPoolsList = new ArrayList<WinPools>();
	/** 一等奖中奖信息 */
	public List<WinLevel> winLevelList = new ArrayList<WinLevel>();
	/** 奖等明细+奖池信息 */
	public String data;

	public GetWinNoticeResp() {
	}

	public GetWinNoticeResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONObject data = json.getJSONObject("data");
			drawNum = data.getString("drawNum");
			issueNum = data.getString("issueNum");
			gameId = data.getIntValue("gameId");
			totalSales = data.getLongValue("totalSales");
			totalPrize = data.getLongValue("totalPrize");
			prePaymentFloat = data.getLongValue("prePaymentFloat");
			actuallyAmountFloat = data.getLongValue("actuallyAmountFloat");
			prePaymentOther = data.getLongValue("prePaymentOther");
			riskPlayNameFloat = data.getString("riskPlayNameFloat");
			actuallyAmountOther = data.getLongValue("actuallyAmountOther");
			riskPlayNameOther = data.getString("riskPlayNameOther");
			if (data.containsKey("noticeDetail")) {
				JSONArray noticeDetail = data.getJSONArray("noticeDetail");
				for (int i = 0; i < noticeDetail.size(); i++) {
					noticeDetailList.add(new NoticeDetail(noticeDetail.getJSONObject(i)));
				}
			}
			if (data.containsKey("winPools")) {
				JSONArray winPools = data.getJSONArray("winPools");
				for (int i = 0; i < winPools.size(); i++) {
					winPoolsList.add(new WinPools(winPools.getJSONObject(i)));
				}
			}
			if (data.containsKey("winLevelFloat")) {
				JSONArray winLevel = data.getJSONArray("winLevelFloat");
				for (int i = 0; i < winLevel.size(); i++) {
					winLevelList.add(new WinLevel(winLevel.getJSONObject(i)));
				}
			}
		}
	}

	public String getDrawNum() {
		return drawNum;
	}

	public void setDrawNum(String drawNum) {
		this.drawNum = drawNum;
	}

	public String getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public long getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(long totalSales) {
		this.totalSales = totalSales;
	}

	public long getTotalPrize() {
		return totalPrize;
	}

	public void setTotalPrize(long totalPrize) {
		this.totalPrize = totalPrize;
	}

	public long getPrePaymentFloat() {
		return prePaymentFloat;
	}

	public void setPrePaymentFloat(long prePaymentFloat) {
		this.prePaymentFloat = prePaymentFloat;
	}

	public long getActuallyAmountFloat() {
		return actuallyAmountFloat;
	}

	public void setActuallyAmountFloat(long actuallyAmountFloat) {
		this.actuallyAmountFloat = actuallyAmountFloat;
	}

	public long getPrePaymentOther() {
		return prePaymentOther;
	}

	public void setPrePaymentOther(long prePaymentOther) {
		this.prePaymentOther = prePaymentOther;
	}

	public String getRiskPlayNameFloat() {
		return riskPlayNameFloat;
	}

	public void setRiskPlayNameFloat(String riskPlayNameFloat) {
		this.riskPlayNameFloat = riskPlayNameFloat;
	}

	public long getActuallyAmountOther() {
		return actuallyAmountOther;
	}

	public void setActuallyAmountOther(long actuallyAmountOther) {
		this.actuallyAmountOther = actuallyAmountOther;
	}

	public String getRiskPlayNameOther() {
		return riskPlayNameOther;
	}

	public void setRiskPlayNameOther(String riskPlayNameOther) {
		this.riskPlayNameOther = riskPlayNameOther;
	}

	public List<NoticeDetail> getNoticeDetailList() {
		return noticeDetailList;
	}

	public void setNoticeDetailList(List<NoticeDetail> noticeDetailList) {
		this.noticeDetailList = noticeDetailList;
	}

	public List<WinPools> getWinPoolsList() {
		return winPoolsList;
	}

	public void setWinPoolsList(List<WinPools> winPoolsList) {
		this.winPoolsList = winPoolsList;
	}

	public List<WinLevel> getWinLevelList() {
		return winLevelList;
	}

	public void setWinLevelList(List<WinLevel> winLevelList) {
		this.winLevelList = winLevelList;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}