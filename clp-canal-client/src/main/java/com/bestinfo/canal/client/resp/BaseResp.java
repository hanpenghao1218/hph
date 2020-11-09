package com.bestinfo.canal.client.resp;

import com.alibaba.fastjson.JSONObject;

public class BaseResp {
	/** 错误码 */
	public int code = 0;
	/** 错误描述 */
	public String msg = "成功";

	public BaseResp() {

	}

	public BaseResp(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public BaseResp(JSONObject json) {
		code = json.getIntValue("code");
		msg = json.getString("msg");
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}