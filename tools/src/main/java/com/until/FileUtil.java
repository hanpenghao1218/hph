package com.until;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	/**
	 * 读取txt
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static List<String[]> readTxt(File file,String regex) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		List<String[]> list = new ArrayList<String[]>();
		boolean flag = true;
		while ((line = reader.readLine()) != null) {
			if (flag) {
				flag = false;
				line = line.startsWith(Tools.UTF8_BOM) ? line.substring(1) : line;
			}
			list.add(line.split(regex));
		}
		reader.close();
		return list;
	}

	/**
	 * 拷贝文件夹下所有内容
	 * 
	 * @param sourcePath 原文件夹
	 * @param newPath    目标文件夹
	 * @throws Exception
	 */
	public static void copyDir(String sourcePath, String newPath) throws Exception {
		File file = new File(sourcePath);
		if (file.exists() && file.isDirectory()) {
			String[] filePath = file.list();

			if (!(new File(newPath)).exists()) {
				(new File(newPath)).mkdir();
			}

			for (int i = 0; i < filePath.length; i++) {
				if ((new File(sourcePath + File.separator + filePath[i])).isDirectory()) {
					copyDir(sourcePath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
				}

				if (new File(sourcePath + File.separator + filePath[i]).isFile()) {
					copyFile(sourcePath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
				}

			}
		}
	}

	/**
	 * 拷贝文件
	 * 
	 * @param sourcePath 原文件
	 * @param newPath    目标文件位置
	 * @throws Exception
	 */
	public static void copyFile(String oldPath, String newPath) throws Exception {
		File oldFile = new File(oldPath);
		File file = new File(newPath);
		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(file);
		;

		byte[] buffer = new byte[2097152];
		int readByte = 0;
		while ((readByte = in.read(buffer)) != -1) {
			out.write(buffer, 0, readByte);
		}

		in.close();
		out.close();
	}

	/**
	 * 删除文件夹
	 * 
	 * @param file
	 */
	public static boolean deleteDir(String dir) {
		return deleteDir(new File(dir));
	}

	/**
	 * 删除文件夹
	 * 
	 * @param file
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	public static void main(String[] args) throws Exception {
//		copyDir("proton//q", "proton/");
		System.out.println(deleteDir("proton/"));
	}
}