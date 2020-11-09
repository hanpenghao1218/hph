package com.bestinfo.canal.client.type;

import com.alibaba.fastjson.JSONObject;

/**
 * 弃奖明细
 * 
 * @author XBox
 *
 */
public class Abandon {
	/** 弃奖游戏期号 */
	public String issueNum;
	/** 开奖游戏期号 */
	public String drawIssueNum;

	public Abandon() {
	}

	public Abandon(JSONObject json) {
		issueNum = json.getString("issueNum");
		drawIssueNum = json.getString("drawIssueNum");
	}

	public String getIssueNum() {
		return issueNum;
	}

	public void setIssueNum(String issueNum) {
		this.issueNum = issueNum;
	}

	public String getDrawIssueNum() {
		return drawIssueNum;
	}

	public void setDrawIssueNum(String drawIssueNum) {
		this.drawIssueNum = drawIssueNum;
	}
}