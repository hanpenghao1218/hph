package com.bestinfo.canal.client.type;

import com.alibaba.fastjson.JSONObject;

public class WinLevel {
	/** 省名称 */
	public String channelName;
	/** 一等奖中奖注数 */
	public long winCount;

	public WinLevel() {

	}

	public WinLevel(JSONObject json) {
		channelName = json.getString("channelName");
		winCount = json.getLongValue("winCountL1");
	}

	public long getWinCount() {
		return winCount;
	}

	public void setWinCount(long winCount) {
		this.winCount = winCount;
	}
}