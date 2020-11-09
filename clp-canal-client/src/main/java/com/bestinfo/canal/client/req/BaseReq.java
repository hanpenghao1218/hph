package com.bestinfo.canal.client.req;

import com.alibaba.fastjson.JSONObject;

public class BaseReq {
	/** 转json串 */
	public JSONObject toJson() {
		return (JSONObject) JSONObject.toJSON(this);
	}
}