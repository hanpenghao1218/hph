package com.bestinfo.canal.client.type;

import com.alibaba.fastjson.JSONObject;

/**
 * 奖等明细
 * 
 * @author XBox
 *
 */
public class NoticeDetail {
	/** 奖等编号 */
	public int grade;
	/** 奖等名称 */
	public String gradeName;
	/** 注数 */
	public int count;
	/** 单注中奖金额（单位：分） */
	public long bonus;

	public NoticeDetail() {

	}

	public NoticeDetail(JSONObject json) {
		grade = json.getIntValue("grade");
		gradeName = json.getString("gradeName");
		count = json.getIntValue("count");
		bonus = json.getLongValue("bonus");
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getBonus() {
		return bonus;
	}

	public void setBonus(long bonus) {
		this.bonus = bonus;
	}
}