package com.jxx.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	/**
	 * date 转化为 string ，2个参数
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString (Date date,String format){
		String dateStr = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateStr = dateFormat.format(date);
		
		return dateStr;
	}
	
	/**
	 * date 转化为 string ， 一个参数
	 * @param date
	 * @return
	 */
	public static String dateToString (Date date){
		return dateToString(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * date 转化为 string ，没有参数
	 * @return
	 */
	public static String dateToString (){
		return dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * string 转化为 date ，2个参数
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String dateStr, String format) {
		Date dateD = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			dateD = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateD;
	}
	
	/**
	 * string 转化为 date , 一个参数
	 * @param dateStr
	 * @return
	 */
	public static Date stringToDate(String dateStr) {
		return stringToDate(dateStr,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * string 转化为 sqldate ，2个参数
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date stringToSqlDate(String dateStr, String format) {
		return new java.sql.Date(stringToDate(dateStr,format).getTime());
	}
	/**
	 * string 转化为 date , 一个参数
	 * @param dateStr
	 * @return
	 */
	public static Date stringToSqlDate(String dateStr) {
		return stringToSqlDate(dateStr,"yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * string 转化为 Timestamp ，2个参数
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Timestamp stringToTimestamp(String dateStr, String format) {
		return stringToTimestamp(dateStr);
	}
	
	/**
	 * string 转化为 Timestamp ，一个参数
	 * @param dateStr
	 * @return
	 */
	public static Timestamp stringToTimestamp(String dateStr) {
		Timestamp ts = null;
		ts = Timestamp.valueOf(dateStr);  
		return ts;
	}
	
	/**
	 * timestamp 转化为 string 2个参数
	 * @param ts
	 * @param format
	 * @return
	 */
	public static String timestampToString(Timestamp ts, String format) {
		String dateStr = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateStr = dateFormat.format(ts); 
//		dateStr = ts.toString();
		return dateStr;
	}
	
	/**
	 * timestamp 转化为 string ，一个参数
	 * @param ts
	 * @return
	 */
	public static String timestampToString(Timestamp ts) {
		return timestampToString(ts,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * timestamp 转化为 Date ，2个参数
	 * @param ts
	 * @param format
	 * @return
	 */
	public static Date timestampToDate(Timestamp ts, String format) {
		return timestampToDate(ts);
	}
	
	/**
	 * timestamp 转化为 Date ，一个参数
	 * @param ts
	 * @return
	 */
	public static Date timestampToDate(Timestamp ts) {
		Date date = null;
		date = new Date(ts.getTime());
		return date;
	}
	
	/**
	 * date 转化为 Timestamp ， 2个参数
	 * @param date
	 * @param format
	 * @return
	 */
	public static Timestamp dateToTimestamp(Date date, String format) {
		return dateToTimestamp(date);
	}
	
	/**
	 * date 转化为 Timestamp ，一个参数
	 * @param date
	 * @return
	 */
	public static Timestamp dateToTimestamp(Date date) {
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}
	
	public static void main(String[] args) {
		System.out.println(timestampToString(new Timestamp(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss"));
		System.out.println(stringToTimestamp("2014-01-15 09:36:36","yyyy-MM-dd HH:mm:ss"));
		System.out.println(dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
		System.out.println(dateToString());
		System.out.println(stringToDate("2014-01-15 09:36:36"));
		System.out.println(dateToTimestamp(new Date()));
		System.out.println(timestampToDate(new Timestamp(System.currentTimeMillis())));
	}

}
