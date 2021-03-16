package com.until;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.type.BaseData;

public class FileList {
	public static void run(BaseData baseData, String date) {
		try {
			date = Tools.isNotNull(date) ? date : Tools.getLastWeek();
			File fileRoot = new File(date);
			if (fileRoot.exists()) {
				File[] files = fileRoot.listFiles();
				String zipPath, fileName = date + File.separator + "testNO-" + date + ".csv";
				FileWriter writer = new FileWriter(fileName);
				writer.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));
				// 检测编号，患者姓名，项目名称，样本类型
				List<String> labList = new ArrayList<String>();
				List<String> illuminaList = new ArrayList<String>();
				List<String> protonList = new ArrayList<String>();
				Object id, project = null;
				for (File file : files) {
					System.out.println(file.getName());
					if (file.isFile() && file.getName().indexOf("P") > 0) {
						List<Object[]> datas = ExcelUntil.read(new FileInputStream(file), "序号");
						Object[] object = datas.get(0);
						String name = object[0].toString().toUpperCase().trim();
						name = (name.indexOf("CFTNA") > 0
								? name.substring(name.indexOf("P") + 1, name.indexOf("-")).trim()
								: name.substring(0, 8)) + "P-FFPE";
//						zipPath = "s3" + File.separator + "proton" + File.separator + name.substring(0, 4) + "-"
//								+ name.substring(4, 6) + File.separator + name;
						zipPath = "s3" + File.separator + "proton" + File.separator + file.getName().substring(0, 4)
								+ "-" + file.getName().substring(4, 6) + File.separator + file.getName().substring(0, 8)
								+ "P-FFPE";
						for (int i = 1; i < datas.size(); i++) {
							object = datas.get(i);
							if (Tools.isNotNull(object[2])) {
								id = object[2] instanceof Double ? new Double(object[2].toString()).intValue()
										: object[2];
								protonList
										.add(id + "," + object[3] + "," + object[5] + "," + object[9] + "," + zipPath);
							}
						}
					} else if (file.isFile() && file.getName().indexOf("I") > 0) {
						List<Object[]> datas = ExcelUntil.read(new FileInputStream(file), "序号");
						zipPath = "s3" + File.separator + "illumina" + File.separator + file.getName().substring(0, 4)
								+ "-" + file.getName().substring(4, 6) + File.separator
								+ file.getName().substring(0, 8);
						for (Object[] object : datas) {
							if (Tools.isNotNull(object[5])) {
								id = object[5] instanceof Double ? (int) object[5] : object[5];
								project = Tools.isNotNull(object[8]) ? object[8].toString().replace(",", "，") : project;
								illuminaList
										.add(id + "," + object[6] + "," + project + "," + object[9] + "," + zipPath);
							}
						}
					} else if (file.isFile() && file.getName().startsWith("检测中心下机表")) {
						List<Object[]> datas = ExcelUntil.read(new FileInputStream(file), "序号");
						zipPath = "results" + File.separator;
						for (Object[] object : datas) {
							if (Tools.isNotNull(object[1])) {
								id = object[1] instanceof Double ? (int) object[1] : object[1];
								labList.add(id + "," + object[2] + "," + object[3] + "," + object[4] + "," + zipPath
										+ id + "_" + object[5] + ".xlsx");
							}
						}
					}
				}
				for (String string : protonList) {
					writer.write(string);
					writer.write("\r\n");
				}
				for (String string : illuminaList) {
					writer.write(string);
					writer.write("\r\n");
				}
				for (String string : labList) {
					writer.write(string);
					writer.write("\r\n");
				}
				writer.close();
				Email.sendMail(baseData.Emails, date + "专患系统数据检测编号列表" + date, "检测编号列表见附件", fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}