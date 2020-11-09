package com.bestinfo.canal.client.req;

import com.alibaba.fastjson.JSONObject;
import com.bestinfo.canal.client.type.GamePermission;

public class PermissionReq extends BaseReq {
	/** 站点编号 */
	public String stationCode = "";
	/** 游戏 ID */
	public int gameId;
	/** 游戏权限 */
	public GamePermission gamePermission;

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("stationCode", stationCode);
		json.put("gameId", gameId);
		json.put("gamePermission", JSONObject.toJSON(gamePermission));
		return json;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public GamePermission getGamePermission() {
		return gamePermission;
	}

	public void setGamePermission(GamePermission gamePermission) {
		this.gamePermission = gamePermission;
	}
}