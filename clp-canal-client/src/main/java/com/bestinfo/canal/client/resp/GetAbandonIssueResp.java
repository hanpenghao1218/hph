package com.bestinfo.canal.client.resp;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestinfo.canal.client.type.Abandon;

public class GetAbandonIssueResp extends BaseResp {
	/** 目标日期是否有弃奖：0—无弃奖；1—有弃奖 */
	public int hasAbandonIssue;
	/** 弃奖明细 */
	public List<Abandon> abandonList = new ArrayList<Abandon>();

	public GetAbandonIssueResp() {
	}

	public GetAbandonIssueResp(JSONObject json) {
		super(json);
		if (code == 0) {
			JSONObject data = json.getJSONObject("data");
			hasAbandonIssue = data.getIntValue("hasAbandonIssue");
			if (data.containsKey("abandonList")) {
				JSONArray abandon = data.getJSONArray("abandonList");
				for (int i = 0; i < abandon.size(); i++) {
					abandonList.add(new Abandon(abandon.getJSONObject(i)));
				}
			}
		}
	}

	public int getHasAbandonIssue() {
		return hasAbandonIssue;
	}

	public void setHasAbandonIssue(int hasAbandonIssue) {
		this.hasAbandonIssue = hasAbandonIssue;
	}

	public List<Abandon> getAbandonList() {
		return abandonList;
	}

	public void setAbandonList(List<Abandon> abandonList) {
		this.abandonList = abandonList;
	}
}