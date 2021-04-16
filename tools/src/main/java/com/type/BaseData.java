package com.type;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.until.Email;

@Component
@ConfigurationProperties(prefix = "base-data")
public class BaseData implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 发件人邮箱 */
	public String sendUser;
	/** 发件人密码 */
	public String sendPwd;
	/** 发送标识 */
	public boolean send;
	/** 所有收件人 */
	public String[] Emails;
	/** 监管邮件接收人 */
	public String supervisor;
	/** CRM邮件接收人 */
	public String CRMEmail;
	public String[] CRMEmails;
	/** 检测中心邮件接收人 */
	public String labEmail;
	public String[] labEmails;
	/** 医学部邮件接收人 */
	public String medEmail;

	/**
	 * 样本类型
	 */
	public List<String> sampleList = Arrays.asList("血液", "组织切片", "新鲜组织", "胸水", "腹水", "福尔马林固定组织", "蜡卷", "蜡块", "染色片",
			"唾液", "口腔拭子", "骨髓", "穿刺样本", "脑脊液", "DNA", "血浆", "血细胞", "心包积液");

	/**
	 * 区域
	 */
	public List<String> areaList = Arrays.asList("北东区", "南区", "中区", "总部");

	/**
	 * 癌种列表
	 */
	public List<String> cancerList = Arrays.asList("肺癌", "乳腺癌", "结直肠癌", "胃癌", "肝癌", "胆道肿瘤", "前列腺癌", "食管癌", "肾癌",
			"头颈部肿瘤", "子宫内膜癌", "脑胶质瘤", "膀胱癌", "皮肤癌", "宫颈癌", "胃肠道间质瘤", "软组织肉瘤", "肾上腺肿瘤", "胰腺癌", "卵巢癌", "胸腺肿瘤", "睾丸癌",
			"阴茎癌", "结节性硬化症", "肾错构瘤", "多囊性肾病", "视网膜母细胞瘤", "Bloom综合征", "VHL综合征", "遗传性多发性软骨瘤", "神经纤维瘤", "多发性内分泌肿瘤",
			"嗜铬细胞瘤", "软组织肉瘤", "非小细胞肺癌", "小细胞肺癌", "非浸润性乳腺癌", "早期浸润性乳腺癌", "浸润性乳腺癌", "结直肠腺癌", "结直肠未分化癌", "结直肠腺鳞癌",
			"结直肠鳞状细胞癌", "结直肠小细胞癌", "结直肠类癌", "胃乳头状腺癌", "胃管状腺癌", "胃低分化腺癌", "胃粘液腺癌", "胃印戒细胞癌", "胃腺鳞癌", "胃鳞癌", "胃类癌",
			"胃未分化癌", "肝细胞癌", "肝内胆管癌", "混合型肝细胞癌和胆管癌", "胆管癌", "胆囊癌", "食管鳞状细胞癌（非特殊型）", "食管腺癌（非特殊型）", "食管腺鳞癌", "食管腺样囊性癌",
			"食管粘液表皮样癌", "食管未分化癌（非特殊型）", "食管神经内分泌肿瘤（NET，非特殊型）", "食管神经内分泌癌（NEC）", "食管混合性神经内分泌-非神经内分泌癌", "透明细胞肾细胞癌",
			"低度恶性潜能多房囊性肾细胞瘤", "乳头状肾细胞癌", "肾髓质癌", "遗传性平滑肌瘤病肾细胞癌综合征相关性肾细胞癌", "嫌色细胞肾细胞癌", "集合管癌", "MiT家族易位性肾细胞癌",
			"琥珀酸脱氢酶缺陷相关的肾细胞癌", "黏液性管状和梭形细胞癌", "管状囊性肾细胞癌", "获得性囊性肾癌相关性肾细胞癌", "透明细胞乳头状肾细胞癌", "未分类的肾细胞癌", "乳头状腺瘤", "嗜酸细胞瘤",
			"头颈部鳞状细胞癌", "口腔癌", "甲状腺癌", "甲状旁腺肿瘤", "鼻咽癌", "喉癌", "下咽癌", "口咽癌", "子宫内膜样癌", "子宫黏液性癌", "浆液性子宫内膜上皮内癌",
			"子宫透明细胞癌", "子宫混合细胞腺癌", "子宫浆液性癌", "子宫神经内分泌肿瘤", "子宫去分化癌", "子宫未分化癌", "神经上皮组织肿瘤", "尿路上皮癌", "膀胱鳞状细胞癌", "膀胱腺癌",
			"皮肤基底细胞癌", "皮肤鳞状细胞癌", "恶性黑色素瘤", "肺腺癌", "肺鳞癌", "肺腺鳞癌", "肺大细胞癌", "星形细胞性肿瘤", "少枝胶质性肿瘤", "来源未定的神经上皮肿瘤", "星形细胞瘤",
			"间变性星形细胞瘤", "胶质母细胞瘤", "少枝胶质细胞瘤", "间变性少枝胶质细胞瘤", "星形母细胞瘤", "极性成胶质细胞瘤", "大脑胶质瘤病");

	/**
	 * 项目和panel集合
	 */
	public static Map<String, String> projectMap = new HashMap<String, String>() {
		private static final long serialVersionUID = -1343692398038233569L;
		{
			put("肿瘤26个驱动基因检测NGS PANEL(组织)", "小型NGS检测Panel");
			put("肿瘤19个驱动基因检测NGS PANEL(血液)", "小型NGS检测Panel");
			put("乳腺癌12个驱动基因检测NGS PANEL(组织)", "小型NGS检测Panel");
			put("乳腺癌12个驱动基因检测NGS PANEL(血液)", "小型NGS检测Panel");
			put("结直肠癌14个驱动基因检测NGS PANEL(组织)", "小型NGS检测Panel");
			put("结直肠癌14个驱动基因检测NGS PANEL(血液)", "小型NGS检测Panel");
			put("乳腺癌12个驱动基因检测+BRCA1/2套餐(组织)", "NGS套餐");
			put("乳腺癌12个驱动基因检测+BRCA1/2套餐(血液)", "NGS套餐");
			put("结直肠癌14驱动基因检测+MSI套餐(组织)", "NGS套餐");
			put("胃肠道间质瘤10基因检测NGS panel(组织)", "NGS Panel");
			put("软组织肉瘤基因检测panel(组织)", "NGS Panel");
			put("甲状腺癌及甲状旁腺癌基因检测panel(组织)", "NGS Panel");
			put("肝胆胰肿瘤103基因检测panel(组织)", "中型NGS检测panel");
			put("肝胆胰肿瘤103基因检测panel(血液)", "中型NGS检测panel");
			put("肿瘤实惠105基因检测panel(组织)", "中型NGS检测panel");
			put("肿瘤实惠105基因检测panel(血液)", "中型NGS检测panel");
			put("肿瘤热点基因全覆盖检测PANEL(556组织)", "大型NGS检测Panel");
			put("肿瘤热点基因全覆盖检测PANEL(556血液)", "大型NGS检测Panel");
			put("肿瘤热点基因全覆盖PANEL(556组织)+PD-L1表达检测", "大型NGS检测Panel");
			put("肿瘤热点基因全覆盖PANEL(556血液)+PD-L1表达检测", "大型NGS检测Panel");
			put("超级全外显子检测PANEL(全外显子+TMB+融合基因+PD-L1检测)", "大型NGS检测Panel");
			put("肾癌靶向药物疗效与不良反应NGS PANEL(组织)", "小型NGS检测Panel");
			put("肾癌靶向药物疗效与不良反应NGS PANEL(血液)", "小型NGS检测Panel");
			put("遗传性肾癌基因检测panel", "遗传 panel");
			put("BRCA1、BRCA2外显子突变检测", "NGS Panel");
			put("TSC1、TSC2外显子突变检测", "遗传 panel");
			put("MMR外显子突变检测", "遗传 panel");
			put("多囊性肾病遗传基因突变检测", "遗传 panel");
			put("RB1基因全外显子检测", "遗传 panel");
			put("BLM基因全外显子检测", "遗传 panel");
			put("VHL基因全外显子检测", "遗传 panel");
			put("EXT1、EXT2基因全外显子检测", "遗传 panel");
			put("NF1、NF2基因全外显子检测", "遗传 panel");
			put("同源重组修复基因检测Panel(23基因)", "遗传 panel");
			put("遗传性乳腺癌及卵巢癌基因检测Panel(22基因)", "遗传 panel");
			put("遗传性结直肠癌基因检测Panel(14基因)", "遗传 panel");
			put("胰腺癌遗传基因检测Panel(16基因)", "遗传 panel");
			put("前列腺癌遗传基因检测Panel(16基因)", "遗传 panel");
			put("胃癌遗传基因检测Panel(16基因)", "遗传 panel");
			put("子宫内膜癌遗传基因检测Panel(23基因)", "遗传 panel");
			put("遗传性黑色素瘤基因检测Panel(2基因)", "遗传 panel");
			put("多发性内分泌肿瘤基因检测Panel(3基因)", "遗传 panel");
			put("遗传性甲状腺癌及甲状腺旁癌基因检测Panel(3基因)", "遗传 panel");
			put("嗜铬细胞瘤遗传基因检测Panel(10基因)", "遗传 panel");
			put("遗传性肿瘤基因检测-女性套餐", "遗传 panel");
			put("遗传性肿瘤基因检测-男性套餐", "遗传 panel");
			put("微卫星不稳定性(MSI)检测", "MSI");
			put("PD-L1蛋白表达水平检测", "IHC相关检测");
			put("肿瘤26基因+TMB+PD-L1 NGS Panel(组织)", "地级市panel");
			put("肿瘤26基因+TMB NGS Panel(血液)", "地级市panel");
			put("结直肠癌NCCN指南必选检测Panel(组织 KRAS/NRAS/BRAF/MSI)", "地级市panel");
			put("结直肠癌NCCN指南必选检测Panel(血液 KRAS/NRAS/BRAF/MSI)", "地级市panel");
			put("前列腺癌三合一检测NGS Panel(组织)", "地级市panel");
			put("前列腺癌三合一检测NGS Panel(血液)", "地级市panel");
			put("肿瘤TMB检测 NGS Panel (组织 409基因)", "地级市panel");
			put("肿瘤TMB检测 NGS Panel (血液 409基因)", "地级市panel");
			put("26基因+化疗+PD-L1(三峡专用)", "NGS套餐");
			put("结直肠癌12基因(北医三院专用)", "NGS套餐");
			put("BRCA1、BRCA2外显子突变检测(含大片段插入缺失检测)", "NGS Panel");
			put("全外显子检测PANEL", "NGS套餐");
		}
	};

	public void init() {
		Emails = (supervisor + CRMEmail + labEmail + medEmail).split(";");
		CRMEmails = (supervisor + CRMEmail).split(";");
		labEmails = (supervisor + labEmail).split(";");
		Email.init(this);
	}

	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getSendPwd() {
		return sendPwd;
	}

	public void setSendPwd(String sendPwd) {
		this.sendPwd = sendPwd;
	}

	public boolean isSend() {
		return send;
	}

	public void setSend(boolean send) {
		this.send = send;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getCRMEmail() {
		return CRMEmail;
	}

	public void setCRMEmail(String cRMEmail) {
		CRMEmail = cRMEmail;
	}

	public String getLabEmail() {
		return labEmail;
	}

	public void setLabEmail(String labEmail) {
		this.labEmail = labEmail;
	}

	public String getMedEmail() {
		return medEmail;
	}

	public void setMedEmail(String medEmail) {
		this.medEmail = medEmail;
	}
}