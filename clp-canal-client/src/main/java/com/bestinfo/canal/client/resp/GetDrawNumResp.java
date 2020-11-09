package com.bestinfo.canal.client.resp;

import com.alibaba.fastjson.JSONObject;

public class GetDrawNumResp extends BaseResp {
	/** 游戏期号 */
	public String issueNum;
	/** 开奖号码，按照;分隔 */
	public String drawNum;

	public GetDrawNumResp() {
	}

	public GetDrawNumResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONObject data = json.getJSONObject("data");
			issueNum = data.getString("issueNum");
			drawNum = data.getString("drawNum");
		}
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
}