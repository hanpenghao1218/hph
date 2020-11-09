package com.bestinfo.canal.client.resp;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestinfo.canal.client.type.GameData;

public class GetSellingGameResp extends BaseResp {
	List<GameData> gameDataList = new ArrayList<GameData>();

	public GetSellingGameResp() {
	}

	public GetSellingGameResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONArray data = json.getJSONArray("data");
			for (int i = 0; i < data.size(); i++) {
				gameDataList.add(new GameData(data.getJSONObject(i)));
			}
		}
	}

	public List<GameData> getGameDataList() {
		return gameDataList;
	}

	public void setGameDataList(List<GameData> gameDataList) {
		this.gameDataList = gameDataList;
	}
}