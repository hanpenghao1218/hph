package com.until;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

import com.impl.CheckImpl;

public class ZipUtil {
	/**
	 * 解压到指定目录
	 */
	public static void unZipFiles(String zipPath, String descDir) throws Exception {
		unZipFiles(new File(zipPath), descDir);
	}

	/**
	 * 解压文件到指定目录
	 */
	@SuppressWarnings("rawtypes")
	public static void unZipFiles(File zipFile, String descDir) throws Exception {
		System.out.println("解压" + zipFile.getAbsolutePath() + " to " + descDir);
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		// 解决zip文件中有中文目录或者中文文件
		ZipFile zip = new ZipFile(zipFile);
		for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + (zipEntryName.indexOf("FFPE") > 0 ? zipEntryName
					: zipEntryName.substring(zipEntryName.indexOf('/')))).replaceAll("\\*", "/");
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
//				System.out.println(outPath);
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		zip.close();
	}

	/**
	 * 解压到指定目录
	 */
	public static void unTarFiles(String zipPath, String descDir) throws Exception {
		unTarFiles(new File(zipPath), descDir);
	}

	/**
	 * 解压文件到指定目录
	 */
	public static void unTarFiles(File zipFile, String descDir) throws Exception {
		System.out.println("解压" + zipFile.getAbsolutePath() + " to " + descDir);
		// decompressing *.tar.gz files to tar
		TarArchiveInputStream fin = new TarArchiveInputStream(
				new GzipCompressorInputStream(new FileInputStream(zipFile)));
		File extraceFolder = new File(descDir);
		TarArchiveEntry entry;
		// 将 tar 文件解压到 extractPath 目录下
		while ((entry = fin.getNextTarEntry()) != null) {
			if (entry.isDirectory()) {
				continue;
			}
			File curfile = new File(extraceFolder, entry.getName());
			File parent = curfile.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
			// 将文件写出到解压的目录
			IOUtils.copy(fin, new FileOutputStream(curfile));
		}
	}

	public static void uzip(String dataFile) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(new File(dataFile)));
		String line;
		boolean flag = true;
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		String[] fileType = { "", ".results", ".supp" };
		while ((line = reader.readLine()) != null) {
			if (flag) {
				flag = false;
				line = line.startsWith(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }))
						? line.substring(1)
						: line;
			}
			if (line.indexOf("s3") > 0) {
				String[] data = line.split(",");
				boolean proton = data[4].indexOf("proton") > 0;
				List<String> list = map.get(proton ? "proton" : "illumina");
				list = list == null ? new ArrayList<String>() : list;
				if (!list.contains(data[4])) {
					list.add(data[4]);
				}
				map.put(proton ? "proton" : "illumina", list);
			}
		}
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			if (entry.getKey().equals("illumina")) {
				System.out.println("illumina目录删除：" + FileUtil.deleteDir("illumina" + File.separator));
				for (String str : entry.getValue()) {
					System.out.println(str);
					int index = str.lastIndexOf(File.separator) + 1;
					String path = str.substring(0, index);
					String name = str.substring(index);
					File[] files = new File(path).listFiles();
					for (File file : files) {
						if (file.isFile() && file.getName().startsWith(name)) {
							if (file.getName().endsWith(".zip")) {
								unZipFiles(file.getAbsoluteFile(), "illumina" + File.separator);
							} else {
								unTarFiles(file.getAbsoluteFile(), "illumina" + File.separator);
							}
						}
					}
				}
			} else if (entry.getKey().equals("proton")) {
				System.out.println("proton目录删除：" + FileUtil.deleteDir("proton" + File.separator));
				for (String str : entry.getValue()) {
					System.out.println(str);
					for (String type : fileType) {
						File file = new File(str + type + ".zip");
						if (file.exists() && file.exists()) {
							unZipFiles(file.getAbsoluteFile(), "proton" + File.separator);
						} else {
							file = new File(str + type + ".tar.gz");
							if (file.exists() && file.exists()) {
								unTarFiles(file.getAbsoluteFile(), "proton" + File.separator);
							} else {
								CheckImpl.Log.error(file.getAbsolutePath() + "文件不存在");
							}
						}
					}
					FileUtil.copyDir("proton" + File.separator + "rerun" + File.separator, "proton" + File.separator);
				}

			}
		}
		reader.close();
	}

	public static void main(String[] args) throws Exception {
//		unZipFiles("20210225P-FFPE.tar.gz", "proton");
//		uzip("20210222-20210228/testNO-20210222-20210228.csv");
		File file = new File("proton" + File.separator);
		System.out.println(file.getName() + "--" + file.exists());
		System.out.println(file.delete());

	}
}