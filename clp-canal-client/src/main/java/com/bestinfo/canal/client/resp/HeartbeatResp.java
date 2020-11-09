package com.bestinfo.canal.client.resp;

import com.alibaba.fastjson.JSONObject;

public class HeartbeatResp extends BaseResp {
	public HeartbeatResp() {
	}

	public HeartbeatResp(JSONObject json) {
		super(json);
	}
}