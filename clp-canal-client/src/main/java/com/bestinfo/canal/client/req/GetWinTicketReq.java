package com.bestinfo.canal.client.req;

public class GetWinTicketReq extends BaseReq {
	/** 票面特征码 */
	public String featureCode = "";

	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}
}