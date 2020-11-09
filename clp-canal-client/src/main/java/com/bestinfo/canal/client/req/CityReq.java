package com.bestinfo.canal.client.req;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bestinfo.canal.client.type.CountyData;

public class CityReq extends BaseReq {
	/** 地市机构编码 */
	public String insCode = "";
	/** 地市机构编码 */
	public String insName = "";
	/** 地市机构编码 */
	public List<CountyData> countDataList = new ArrayList<CountyData>();

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("insCode", insCode);// 地市机构编码
		json.put("insName", insName);// 地市机构名称
		JSONArray countyData = new JSONArray();
		for (CountyData county : countDataList) {
			JSONObject data = new JSONObject();
			data.put("insCode", county.getInsCode());// 县区机构编码
			data.put("insName", county.getInsName());// 县区机构名称
			countyData.add(data);
		}
		json.put("countyData", countyData.toString());// 县区机构集合
		return json;
	}
	
	public String getInsCode() {
		return insCode;
	}

	public void setInsCode(String insCode) {
		this.insCode = insCode;
	}

	public String getInsName() {
		return insName;
	}

	public void setInsName(String insName) {
		this.insName = insName;
	}

	public List<CountyData> getCountDataList() {
		return countDataList;
	}

	public void setCountDataList(List<CountyData> countDataList) {
		this.countDataList = countDataList;
	}
}