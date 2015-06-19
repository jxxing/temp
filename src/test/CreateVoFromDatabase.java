/**
 *  Copyright 2012 杭州中软安人网络通信有限公司
 *  All Rights Reserved
 *  Created on Jun 1, 2012 11:21:11 PM by liwei
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 传入表名，即可在控制台输入java对象属性，可以提高效率
 * @author liwei
 * @date Jun 1, 2012
 */
public class CreateVoFromDatabase {
	
	
	public static void main(String args[]) {
		createVO("person");
	}
	
	private static void createVO(String tableName)
	{
		
		Connection con;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.118.210.237:1521:zjgp02", "jxgp02",
					"jxgp02");
			// 创建状态
			Statement stmt = con.createStatement();
			String sql = " SELECT  col.column_name,col.data_type, com.comments FROM user_col_comments com " +
					"JOIN user_tab_cols col ON com.table_name = col.table_name AND com.column_name = " +
					"col.column_name and col.table_name = '"+tableName.toUpperCase()+"' ORDER BY col.column_id  ";
			ResultSet rs = stmt.executeQuery(sql);//
			System.out.println(sql);
			while (rs.next())
			{
			
			 String  columnName = rs.getString("COLUMN_NAME");
			 String  dataType = rs.getString("DATA_TYPE");
			 String  comments = rs.getString("COMMENTS");
			 getName(columnName,dataType,comments);
			}
				

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void getName(String columnName,String dataType,String comments)
	{
		   StringBuilder sb = new StringBuilder();

		   String[] fieldNames = columnName.toLowerCase().split("_");
		   String type = "";
		   //将字段首字母大写
		   for(String fieldName : fieldNames){
		    String firstWord = fieldName.substring(0, 1).toUpperCase();
		    sb.append(firstWord);
		    sb.append(fieldName.substring(1, fieldName.length()));
		   }
		   String head = sb.toString().substring(0, 1).toLowerCase();
		   String tail = sb.substring(1,sb.length());
		   if(dataType.equals("VARCHAR2"))
		   {
			   type = "String";
		   }
		   if(dataType.equals("NUMBER"))
		   {
			   type = "int";
		   }
		   if(dataType.equals("CLOB"))
		   {
			   type = "String";
		   }
		   if(dataType.equals("DATE"))
		   {
			   type = "Date";
		   }
		   System.out.println("/**"+"\n"+" *"+comments+"\n */");
		   
		   String message = "private " +type +" "+ head+tail+ ";";
		   System.out.println(message+"\n");
	}
}
