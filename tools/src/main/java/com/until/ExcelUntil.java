package com.until;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel工具
 * 
 * @author XBox
 *
 */
public class ExcelUntil {

	/**
	 * 读取excel
	 * 
	 * @param path     文件路径
	 * @param firstRow 首行序号
	 * @param firstRow 首列序号
	 * @return
	 */
	public static List<Object[]> read(String path, int firstRow, int fistCell) {
		try {
			return read(new FileInputStream(path), firstRow, fistCell);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取excel
	 * 
	 * @param file     文件
	 * @param firstRow 首行序号
	 * @param firstRow 首列序号
	 * @return
	 */
	public static List<Object[]> read(File file, int firstRow, int fistCell) {
		try {
			return read(new FileInputStream(file), firstRow, fistCell);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取excel
	 * 
	 * @param path     文件路径
	 * @param firstRow 首行序号
	 * @param firstRow 首列序号
	 * @return
	 */
	public static List<Object[]> read(FileInputStream inputStream, int firstRow, int fistCell) {
		Workbook wb = null;
		// 读取Excel
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			wb = new XSSFWorkbook(inputStream);
			// 使用wb。getNumberOfSheets获得sheet数目。。
			// 获取sheet数目
			Sheet sheet = wb.getSheetAt(0);
			Row row = null;
			int maxlen = sheet.getRow(firstRow).getLastCellNum() - fistCell;
			// 循环读取
			for (int i = firstRow; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					// 获取每一列的值
					Object[] data = new Object[maxlen];
					for (int j = fistCell; j < Math.min(row.getLastCellNum(), maxlen) + fistCell; j++) {
						data[j - fistCell] = getCellValue(row.getCell(j));
					}
					list.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 读取excel
	 * 
	 * @param inputStream 文件流
	 * @param key         开始读取关键字
	 * @return
	 */
	public static List<Object[]> read(FileInputStream inputStream, String key) {
		Workbook wb = null;
		// 读取Excel
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			wb = new XSSFWorkbook(inputStream);
			// 使用wb。getNumberOfSheets获得sheet数目。。
			// 获取sheet数目
			Sheet sheet = wb.getSheetAt(0);
			Row row = null;
			int lastRowNum = sheet.getLastRowNum();
			// 循环读取
			boolean check = true, end = false;
			for (int i = 0; i <= lastRowNum; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					// 获取每一列的值
					Object[] data = null;
					for (int j = 0; j < row.getLastCellNum(); j++) {
						Object value = getCellValue(row.getCell(j));
						if (check) {
							if (value instanceof String && value.toString().indexOf("上机表") > 0) {
								data = new Object[1];
								data[0] = value;
							}
						} else if (data != null) {
							data[j] = value;
						}
						if (j == 0) {
							if (check) {
								if (value instanceof String && value.toString().equals(key)) {
									check = false;
									break;
								}
							} else {
								if (Tools.isNotNull(value)) {
									data = new Object[row.getLastCellNum()];
									data[0] = value;
								}
							}
						}
					}
					if (data != null) {
						list.add(data);
					}
					if (end) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private static Object getCellValue(Cell cell) {
		Object result = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case STRING:
				result = cell.getStringCellValue().replaceAll("[\\t\\n\\r]", "").replaceAll("\\s+", " ");
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					String temp = cell.getCellStyle().getDataFormatString();
					if (temp.equals("m/d/yy")) {
						result = Tools.format1.format(cell.getDateCellValue());
					} else if (temp.equals("General")) {
						DecimalFormat format = new DecimalFormat();
						format.applyPattern("#");
						result = format.format(cell.getNumericCellValue());
					}
				} else {
					result = cell.getNumericCellValue();
				}
				break;
			case BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			case FORMULA:
				result = cell.getNumericCellValue();
				break;
			case ERROR:
				result = cell.getErrorCellValue();
				break;
			case BLANK:
				result = " ";
				break;
			default:
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// 样本信息汇总表
		// 运营部分数据
//		List<Object[]> list = read("tmp/样本信息汇总表.xlsx", 3, 22);
//		Object[] objects = list.get(0);
//		Object[] objects1 = list.get(2);
//		for (int i = 0; i < objects.length; i++) {
//			if (Tools.isNotNull(objects1[i].toString())) {
//				System.out.println(i + "-" + objects[i] + "[" + objects1[i] + "]");
//			}
//		}
		
		List<Object[]> list = read(new FileInputStream(new File("poject.xlsx")), "序号");
		for (Object[] objects : list) {
//			System.out.println(Arrays.toString(objects));
			System.out.println("put(\""+objects[4].toString().trim()+"\", \""+objects[3].toString()+"\");");
		}
	}
}