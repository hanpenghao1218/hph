package com.bestinfo.canal.notice.type;

import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

@Configuration
@Data
public class BaseResp {
	private int code = 0;
	private String msg = "成功";
	private Object data = new Object();

	public BaseResp() {

	}

	public BaseResp(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String toJson() {
		return JSONObject.toJSON(this).toString();
	}
}