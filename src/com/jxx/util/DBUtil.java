package com.jxx.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	/**
	 * 关闭数据库语句 ，3个参数
	 * @param stmt
	 * @param rs
	 * @param conn
	 */
	public static void closeRescoure(Statement stmt,ResultSet rs,Connection conn){
		try {
			if(rs !=null){
				rs.close();
				rs = null;
			}
			if(stmt != null){
				stmt.close();
				stmt = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 关闭数据库语句 ， 2个参数
	 * @param stmt
	 * @param rs
	 */
	public static void closeRescoure(Statement stmt,ResultSet rs){
		closeRescoure(stmt,rs,null);
	}
	
	/**
	 * 关闭数据库语句 ， 一个rs参数
	 * @param rs
	 */
	public static void closeRescoure(ResultSet rs){
		closeRescoure(null,rs,null);
	}
	
	/**
	 * 关闭数据库语句 ，一个stmt参数
	 * @param stmt
	 */
	public static void closeRescoure(Statement stmt){
		closeRescoure(stmt,null,null);
	}
	public static void main(String[] args) {

	}

}
