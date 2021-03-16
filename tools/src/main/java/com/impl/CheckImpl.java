package com.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.type.BaseData;
import com.until.Email;
import com.until.ExcelUntil;
import com.until.FileUtil;
import com.until.Tools;

@Component
public class CheckImpl {
	public static final Logger Log = LoggerFactory.getLogger(CheckImpl.class);
	Map<String, List<String>> crmErr = new HashMap<String, List<String>>();
	Map<String, List<String>> labErr = new HashMap<String, List<String>>();
	BaseData baseData;
	String path, date, crmFileName, labFileName;

	public void run(BaseData baseData, String runDate) {
		try {
			this.baseData = baseData;
			Map<String, boolean[]> idMap = new HashMap<String, boolean[]>();// 检测单号
			Map<String, String> idDataMap = new HashMap<String, String>();// 检测单号
			List<Object[]> crmList = new ArrayList<Object[]>();// crm数据
			List<Object[]> labList = new ArrayList<Object[]>();// 检测中心数据
			date = Tools.isNotNull(runDate) ? runDate : Tools.getLastWeek();
			long checkTime = Tools.getMonday(date.split("-")[0]);
			File fileRoot = new File(date);
			path = fileRoot.getAbsolutePath();
			Log.info("数据校验任务执行开始" + path);
			crmFileName = path + File.separator + "专患数据问题整改-运营-" + date + ".csv";
			labFileName = path + File.separator + "专患数据问题整改-检测中心-" + date + ".csv";
			boolean crmCheck = true, labCheck = true, crmDone = false, labDone = false;
			File crmFile = new File("CRM" + File.separator + "CRM data summary-From 20210201 till now.xlsx");
			if (crmFile != null && crmFile.isFile()) {
				crmList = ExcelUntil.read(crmFile, 1, 0);
				File crmCheckFile = new File(crmFileName);
				if (crmCheckFile.exists() && crmCheckFile.isFile()) {
					crmCheck = crmCheckFile.lastModified() < crmFile.lastModified();
					List<String[]> list = FileUtil.readTxt(crmCheckFile, ",");
					crmDone = list.get(0)[0].equals("check");
				} else {
					crmCheck = crmFile.lastModified() > checkTime;
				}
			} else {
				crmCheck = false;
				Log.error(crmFile.getName() + "文件不存在");
			}
			File labFile = new File("lab" + File.separator + "lab data summary-From 20210201 till now.xlsx");
			if (labFile != null && labFile.isFile()) {
				labList = ExcelUntil.read(labFile, 3, 22);
				File labCheckFile = new File(labFileName);
				if (labCheckFile.exists() && labCheckFile.isFile()) {
					labCheck = labCheckFile.lastModified() < labFile.lastModified();
					List<String[]> list = FileUtil.readTxt(labCheckFile, ",");
					labDone = list.get(0)[0].equals("check");
				} else {
					labCheck = labFile.lastModified() > checkTime;
				}
			} else {
				labCheck = false;
				Log.error(labFile.getName() + "文件不存在");
			}
			File testFile = new File(path + File.separator + "testNO-" + date + ".csv");
			if (testFile.exists() && testFile.isFile()) {
				BufferedReader reader = new BufferedReader(new FileReader(testFile));
				String line;
				String[] lines;
				boolean flag = true;
				while ((line = reader.readLine()) != null) {
					if (flag) {
						flag = false;
						line = line.startsWith(Tools.UTF8_BOM) ? line.substring(1) : line;
					}
					lines = line.split(",");
					idMap.put(lines[0], new boolean[2]);
					idDataMap.put(lines[0], line);
				}
				reader.close();
			}
			File checkFile = new File(path + File.separator + "check");
			if (!checkFile.exists()) {
				if (idMap.size() == 0) {
					Log.warn("检测列表数据缺失,暂不执行比对");
				} else {
					Object[] crmObject = crmList.get(0), labObject = labList.get(0);
					if (!crmDone && crmCheck) {
						for (int i = 1; i < crmList.size(); i++) {
							Object[] object = crmList.get(i);
							String id = Tools.isNotNull(object[21]) ? object[21].toString()
									: "第" + (i + 2) + "行数据检测编号为空";
							if (Tools.isNotNull(object[54]) && object[54].equals("否")) {
								idMap.remove(id);
								idDataMap.remove(id);
							} else {
								if (Tools.isNotNull(object[21])) {
									if (idMap.containsKey(id)) {// 检测编号存在
										idMap.get(id)[0] = true;
										checkCrm(id, crmObject, object);
									}
								} else {
									checkCrm(id, crmObject, object);
								}
							}
						}
						Log.error("crmErr:" + crmErr.size());
					} else {
						Log.warn("运营部分数据暂不执行比对");
					}
					if (!labDone && labCheck) {
						for (int i = 1; i < labList.size(); i++) {
							Object[] object = labList.get(i);
							String id = Tools.isNotNull(object[0]) ? object[0].toString() : "第" + (i + 4) + "行数据检测编号为空";
							if (Tools.isNotNull(object[0])) {
								if (idMap.containsKey(id)) {// 检测编号存在
									idMap.get(id)[1] = true;
									checkLab(id, labObject, object);
								}
							} else {
								checkLab(id, labObject, object);
							}
						}
						Log.error("labErr:" + labErr.size());
					} else {
						Log.warn("检测中心分数据暂不执行比对");
					}

					try {
						FileWriter testWriter = new FileWriter(testFile);
						testWriter.write(Tools.UTF8_BOM);
						for (Map.Entry<String, String> entry : idDataMap.entrySet()) {
							testWriter.write(entry.getValue() + "\r\n");
						}
						testWriter.close();
						String[] fileName = new String[2];
						FileWriter crmWriter = new FileWriter(crmFileName);
						crmWriter.write(Tools.UTF8_BOM);
						for (Map.Entry<String, List<String>> entry : crmErr.entrySet()) {
							for (String str : entry.getValue()) {
								crmWriter.write(entry.getKey() + "," + str);
								crmWriter.write("\r\n");
							}
							if (fileName[0] == null) {
								fileName[0] = crmFileName;
							}
						}
						FileWriter labWriter = new FileWriter(labFileName);
						labWriter.write(Tools.UTF8_BOM);
						for (Map.Entry<String, List<String>> entry : labErr.entrySet()) {
							for (String str : entry.getValue()) {
								labWriter.write(entry.getKey() + "," + str);
								labWriter.write("\r\n");
							}
							if (fileName[1] == null) {
								fileName[1] = labFileName;
							}
						}
						/** 缺失数据 */
						for (Map.Entry<String, boolean[]> entry : idMap.entrySet()) {
							if (!crmDone && crmCheck) {
								if (!entry.getValue()[0]) {
									crmWriter.write(entry.getKey() + ",数据缺失\r\n");
									if (fileName[0] == null) {
										fileName[0] = crmFileName;
									}
								}
							}
							if (!labDone && labCheck) {
								if (!entry.getValue()[1]) {
									labWriter.write(entry.getKey() + ",数据缺失\r\n");
									if (fileName[1] == null) {
										fileName[1] = labFileName;
									}
								}
							}
						}
						if (fileName[0] == null && crmCheck) {
							crmWriter.write("check");
							crmDone = true;
						}
						if (fileName[1] == null && labCheck) {
							labWriter.write("check");
							labDone = true;
						}
						crmWriter.close();
						labWriter.close();
						if (Tools.isNotNull(fileName[0])) {
							Log.info("发送CRM错误邮件");
							Email.sendMail(baseData.CRMEmails, "专患系统数据校验结果" + date, "错误数据请见附件", fileName[0]);
						}
						if (Tools.isNotNull(fileName[1])) {
							Log.info("发送检测中心错误邮件");
							Email.sendMail(baseData.labEmails, "专患系统数据校验结果" + date, "错误数据请见附件", fileName[1]);
						}
						if (crmDone && labDone) {
							Files.copy(testFile.toPath(),
									new FileOutputStream(new File("testNO" + File.separator + testFile.getName())));
							Log.info("数据校验完成，没有错误");
							new File(path + File.separator + "check").createNewFile();
							Email.sendMail(baseData.Emails, "专患系统数据校验结果" + date, "校验完成没有异常", new String[0]);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				Log.info(date + "数据已完成校验，无需再次校验");
			}
			Log.info("数据校验任务执行完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int[] notnull = { 2, 5, 6, 8, 9, 13, 14, 18, 19, 21, 22, 23, 24, 25, 26, 30, 33, 34, 35, 47 };

	/**
	 * crm数据比较
	 * 
	 * @param id
	 * @param str
	 * @param object
	 */
	public void checkCrm(String id, Object[] str, Object[] object) {
		List<String> list = crmErr.get(id);
		if (list == null) {
			list = new ArrayList<String>();
		}
		for (int i : notnull) {
			if (Tools.isNotNull(object[i])) {
				String val = object[i].toString();
				switch (i) {
				case 2:// 区域[北东区、南区、中区、总部]
					if (!baseData.areaList.contains(val)) {
						list.add(str[i] + ",不在标准列表中[" + val + "]");
					}
					break;
				case 5:// 送样日期[yyyy/mm/dd]
					try {
						Tools.format1.parse(val);
					} catch (Exception e) {
						list.add(str[i] + ",[格式不对-" + val + "]");
					}
					break;
				case 6:// 收样时间[yyyy/mm/dd]
					try {
						Tools.format1.parse(val);
					} catch (Exception e) {
						list.add(str[i] + ",[格式不对-" + val + "]");
					}
					break;
				case 13:// 医院ID[CON+数字编号]
					if (!val.startsWith("CON") || !val.substring(3).matches("^[0-9]*$")) {
						list.add(str[i] + ",[格式不对-" + val + "]");
					}
					break;
				case 18:// 客户ID[D+数字编号]
					val = val.trim();
					if (!val.startsWith("D") || !val.substring(1).matches("^[0-9]*$")) {
						list.add(str[i] + ",[格式不对-" + val + "]");
					}
					break;
				case 21:// 检测编号[TSJY/TSCZ/ZXAS/ZXAC/TS+7位数字]
					if (val.length() == 11) {
						if (!((val.startsWith("TSJY") || val.startsWith("TSCZ") || val.startsWith("ZXAS")
								|| val.startsWith("ZXAC")) && val.substring(4).matches("^[0-9]*$"))) {
							list.add(str[i] + ",[格式不对-" + val + "]");
						}
					} else if (val.length() == 9) {
						if (!val.startsWith("TSJY") || !val.substring(2).matches("^[0-9]*$")) {
							list.add(str[i] + ",[格式不对-" + val + "]");
						}
					} else {
						list.add(str[i] + ",[格式不对-" + val + "]");
					}
					break;
				case 22:// 22-患者ID[4位英文字母+4位数字]
					if (val.length() != 8 || !val.substring(0, 4).matches("[a-zA-Z]+")
							|| !val.substring(4).matches("^[0-9]*$")) {
						list.add(str[i] + ",[格式不对-" + val + "]");
					}
					break;
				case 24:// 出生时间[yyyy-mm-dd]
					try {
						Tools.format2.parse(val);
					} catch (Exception e) {
						list.add(str[i] + ",[格式不对-" + val + "]");
					}
					break;
				case 25:// 性别[男/女]
					if (!val.equals("男") && !val.equals("女")) {
						list.add(str[i] + ",不在标准列表中[" + val + "]");
					}
					break;
				case 26:// 癌种[要与癌种列表中内容匹配]
					if (!baseData.cancerList.contains(val)) {
						list.add("癌种不在标准列表中,[" + object[26] + "]");
					}
					break;
				case 30:// 肿瘤样本[要与17类样本类型标准相符]
					break;
				case 33:// 样本ID[检测编号首位英文字母+检测编号数字+样本类型编码]
					break;
				case 34:// 检测项目产品编号[PRO+数字编号]
					if (!val.startsWith("PRO") || !val.substring(3).matches("^[0-9]*$")) {
						list.add(str[i] + ",[格式不对-" + val + "]");
					}
					break;
				case 35:// 检测项目[要与标准名称类表中内容匹配]
					if (!baseData.projectMap.containsKey(val)) {
						list.add("检测项目不在标准列表中,[" + object[35] + "]");
					} else {
						if (object[47] == null || !baseData.projectMap.get(val).equals(object[47].toString())) {
							list.add("PanelType不在标准列表中,[" + object[47] + "]");
						}
					}
					break;
				default:
					break;
				}
			} else {
				list.add(str[i] + ",[值为空]");
			}
		}
		if (list.size() > 0) {
			crmErr.put(id, list);
		}
	}

	/**
	 * 检测中心数据比较
	 * 
	 * @param id
	 * @param str
	 * @param object
	 */
	public void checkLab(String id, Object[] str, Object[] object) {
		List<String> list = labErr.get(id);
		if (list == null) {
			list = new ArrayList<String>();
		}
		if (object[1] == null || !baseData.sampleList.contains(object[1].toString().toUpperCase())) {
			list.add("样本类型不在标准列表中,[" + object[1] + "]");
		}
		if (object[4] == null || object[4].toString().length() == 0) {
			list.add("唯一样本id,[为空]");
		}
		if (list.size() > 0) {
			labErr.put(id, list);
		}
	}
}