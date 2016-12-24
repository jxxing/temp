package com.jxx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	/**
	 * null,"" --> true,other false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * null,"",空字符 --> true,other false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}

	/**
	 * 非空 -->true, other false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * Gc_Name --> gcname
	 * 
	 * @param colName
	 * @return
	 */
	public static String transToObjectProperty(String colName) {
		// String temp ="GC_NAME_big";
		String temp = colName;
		String pro = "";

		String[] colArrs = temp.split("_");
		for (int i = 0; i < colArrs.length; i++) {
			String arr = colArrs[i];
			// System.out.println("arr split:"+arr.toLowerCase());
			pro = pro + arr.toLowerCase();
		}

		return pro;
	}

	/**
	 * null --> " ",other param
	 * 
	 * @param param
	 * @return
	 */
	public static String checkNotNull(String param) {
		if (param == null || param.trim().isEmpty()) {
			param = " ";
		}
		return param;
	}

	/**
	 * null --> "",other str
	 * 
	 * @param str
	 * @return
	 */
	public static String null2Empty(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	/**
	 * null,"" --> true ,other false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullorEmpty(String str) {
		if (str == null) {
			return true;
		}
		str = str.trim();

		return str.length() < 1;
	}

	/**
	 * null,"" --> defaultStr, other str
	 * 
	 * @param str
	 * @return
	 */
	public static String empty2Default(String str, String defaultStr) {
		if (isNullorEmpty(str)) {
			return defaultStr;
		}
		return str;
	}

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	/**
	 * 得到yyyyMMddHHmmssSSS + 随机 100到999的三位数
	 * 
	 * @return
	 */
	public static String getOnlyOneKeyString() {
		String re = "";
		synchronized (dateFormat) {
			try {
				Thread.sleep((long) 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Date now = new Date();
			int ii = (int) (Math.random() * 900) + 100;
			re = dateFormat.format(now) + "" + ii;
		}

		return re;
	}

	public static void main(String[] args) {
		/*
		 * for(int i=0;i<10;i++){ String str =""; try { str =
		 * T9DigestUtility.md5Hex("123456".getBytes("UTF-8")); } catch
		 * (UnsupportedEncodingException e) { e.printStackTrace(); }
		 * System.out.println(str); }
		 */
		System.out.println(getOnlyOneKeyString());
		String ss = "施工一级或者地方";
		String[] strArray = ss.split("或者");
		System.out.println("strArray.length:" + strArray.length);
		for (String s : strArray) {
			System.out.println(s);
		}
	}

	public static String unformat(String str) {
		String st = null;
		if (str == null) {
			return null;
		}
		StringBuffer words = new StringBuffer();

		String[] temps = str.split("_");
		for (String string : temps) {
			String temp = string.trim();
			String s = temp.substring(1, temp.length());
			String ss = temp.substring(0, 1).toUpperCase() + s.toLowerCase();
			words.append(ss);
		}
		st = words.toString().substring(0, 1).toLowerCase()
				+ words.toString().substring(1, words.toString().length());
		return st;
	}

	public static String format(String str, String str2) {
		String result = "";
		if ((str2 != null) && (str.startsWith(str2))) {
			String s = stringCutOut(str, str2);
			result = format(s);
		} else {
			result = format(str);
		}
		return result;
	}

	public static String format(String str) {
		StringBuffer words = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (Character.isUpperCase(ch)) {
				String s = str.substring(0, i);
				String ss = s.toUpperCase();
				if (!"".equals(ss)) {
					words.append(ss + "_");
				}
				str = str.substring(i);
				i = 0;
			}
		}

		words.append(str.toUpperCase());
		return words.toString();
	}

	private static String stringCutOut(String str, String str2) {
		String result = "";
		int index = str2.length();
		result = str.substring(index);

		return result;
	}
}
