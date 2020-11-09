package com.bestinfo.canal.client.type;

import com.alibaba.fastjson.JSONObject;

/**
 * 奖池信息
 * 
 * @author XBox
 *
 */
public class WinPools {
	/** 奖池名称 */
	public String poolName;
	/** 奖池余额（单位：分） */
	public long poolSumMoney;
	/** 销售金额（单位：分） */
	public long poolSaleMoney;

	public WinPools() {

	}

	public WinPools(JSONObject json) {
		poolName = json.getString("poolName");
		poolSumMoney = json.getLongValue("poolSumMoney");
		poolSaleMoney = json.getLongValue("poolSaleMoney");
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public long getPoolSumMoney() {
		return poolSumMoney;
	}

	public void setPoolSumMoney(long poolSumMoney) {
		this.poolSumMoney = poolSumMoney;
	}

	public long getPoolSaleMoney() {
		return poolSaleMoney;
	}

	public void setPoolSaleMoney(long poolSaleMoney) {
		this.poolSaleMoney = poolSaleMoney;
	}
}