package com.until;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	public static long week = 24 * 3600 * 1000 * 7;
	/** 日期格式化yyyyMMdd */
	public static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	/** 日期格式化yyyy/MM/dd */
	public static SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
	/** 日期格式化yyyy-MM-dd */
	public static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	/** 日期格式化yyyy-MM-dd HH:mm:ss */
	public static SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String UTF8_BOM = new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });

	/**
	 * 获取某天所在周日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		String begDate = format.format(cal.getTime());
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String endDate = format.format(cal.getTime());
		return begDate + "-" + endDate;
	}

	public static long getMonday(String date) throws Exception {
		return getMonday(format.parse(date));
	}

	/**
	 * 取某个时间所在周一0点
	 * 
	 * @param date
	 * @return
	 */
	public static long getMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		System.out.println(formatTime.format(cal.getTime()));
		return cal.getTime().getTime();
	}

	/**
	 * 取本周一0点
	 * 
	 * @return
	 */
	public static long getThisMonday() {
		return getMonday(new Date());
	}

	/**
	 * 取当前周
	 * 
	 * @return
	 */
	public static String getThisWeek() {
		return getWeek(new Date());
	}

	/**
	 * 取上周
	 * 
	 * @return
	 */
	public static String getLastWeek() {
		return getWeek(new Date(System.currentTimeMillis() - week));
	}

	public static boolean isNotNull(Object val) {
		return val != null && val.toString().trim().length() > 0;
	}

	public static boolean isNull(Object val) {
		return val == null || val.toString().trim().length() == 0;
	}

	/**
	 * 取string中的数字
	 * 
	 * @param str
	 * @return
	 */
	public static int getNum(String str) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return Integer.valueOf(m.replaceAll("").trim());
	}

	/**
	 * 判断该字符串是否为中文
	 * @param string
	 * @return
	 */
	public static boolean isChinese(String string){
	    int n = 0;
	    for(int i = 0; i < string.length(); i++) {
	        n = (int)string.charAt(i);
	        if(!(19968 <= n && n <40869)) {
	            return false;
	        }
	    }
	    return true;
	}

	public static void main(String[] args) {
//		System.out.println(getLastWeek());
//		getMonday(new Date(System.currentTimeMillis() - 24 * 3600 * 1000));
		System.out.println(getNum("1++"));
	}
}