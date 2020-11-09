package com.bestinfo.canal.client.type;

import com.alibaba.fastjson.JSONObject;

public class GameData {
	/** 游戏 ID */
	public int gameId;
	/** 游戏名称 */
	public String gameName;
	/** 游戏状态：0—停售；1—开售 */
	public int gameStatus;
	/** 销售权限：0—无；1—有 */
	public int salePerm;
	/** 注销权限：0—无；1—有 */
	public int cancelPerm;
	/** 兑奖权限：0—无；1—有 */
	public int awardPerm;
	/** 游戏周期：0—长周期；1—短周期 */
	public int cycleType;
	/** 最大倍数 */
	public int maxMulti;
	/** 最大注数 */
	public int maxBetsPerTick;
	/** 单注金额（单位：分） */
	public long singleAmount;
	/** 单票最小金额（单位：分） */
	public long singleMinAmount;
	/** 单票最大金额（单位：分） */
	public long singleMaxAmount;
	/** 兑奖总天数 */
	public int cashDays;
	/** 发行费比例 */
	public double issueRate;
	/** 公益金比例 */
	public double pubWelRate;
	/** 计提奖金比例 */
	public double prizeRate;

	public GameData() {

	}

	public GameData(JSONObject json) {
		gameId = json.getIntValue("gameId");
		gameName = json.getString("gameName");
		gameStatus = json.getIntValue("gameStatus");
		salePerm = json.getIntValue("salePerm");
		cancelPerm = json.getIntValue("cancelPerm");
		awardPerm = json.getIntValue("awardPerm");
		cycleType = json.getIntValue("cycleType");
		maxMulti = json.getIntValue("maxMulti");
		maxBetsPerTick = json.getIntValue("maxBetsPerTick");
		singleAmount = json.getLongValue("singleAmount");
		singleMinAmount = json.getLongValue("singleMinAmount");
		singleMaxAmount = json.getLongValue("singleMaxAmount");
		cashDays = json.getIntValue("cashDays");
		issueRate = json.getDoubleValue("issueRate");
		pubWelRate = json.getDoubleValue("pubWelRate");
		prizeRate = json.getDoubleValue("prizeRate");
	}
}