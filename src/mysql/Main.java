package mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hys.main.Conn.MySqlConn;
import com.mysql.jdbc.Statement;

public class Main {
	public static void main(String[] args) {
		String database = com.hys.main.common.PropertiesRead.getPath("Conn.properties", "Conn_database");
		Connection conn = null;
		if(database.trim().toLowerCase().equals("mysql")){
			conn = com.hys.main.Conn.MySqlConn.getconn();
		}
	
//		Main.CreateJava("protocol_log", conn);
		Statement state = null;
		try {
		conn = MySqlConn.getconn();
		StringBuffer sql = new StringBuffer();
		System.out.println(System.currentTimeMillis());
		for(int i=5;i<40;i++){
			sql.append("insert into `test` values (");
			sql.append("'" + i + "','" + i + "name','" + i + "value'");
			sql.append(");");
		}
		System.out.println(System.currentTimeMillis());
		System.out.println(sql.toString());
	     
			state = (Statement) conn.createStatement();
		    state.executeUpdate(sql.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		      MySqlConn.close(null, state, conn);
		    }
	}
	
	public static void CreateJava(String tableName, Connection conn)
	  {
	    StringBuffer sql = new StringBuffer();
	    sql.append("SELECT DISTINCT column_name cloumnName,data_type type,column_comment comment,column_key FROM Information_schema.COLUMNS WHERE TABLE_NAME LIKE '" + tableName + "'");
	    Statement state = null;
	    ResultSet rs = null;
	    try {
	      conn = MySqlConn.getconn();
	      state = (Statement) conn.createStatement();
	      rs = state.executeQuery(sql.toString());
	      while (rs.next()) {
	        String columnName = rs.getString("cloumnName").toLowerCase();
	        
	        String type = rs.getString("type").toLowerCase();
	        
	        String comment = rs.getString("comment").toLowerCase();
	        
	        String column_key = rs.getString("column_key").toLowerCase();
//	        System.out.println(column_key);
	        
	        String name = "";
	        String[] str = columnName.split("_");
	        StringBuffer sb = new StringBuffer();
	        if (str.length > 1){
		          String[] arrayOfString2;
		          int length = (arrayOfString2 = str).length; 
		          for (int i = 0; i < length; i++) { 
		        	  String first = "";
		        	  if(i==0){
		        		  first = arrayOfString2[i].substring(0, 1);
		        	  }else{
		        		  first = arrayOfString2[i].substring(0, 1).toUpperCase();
		        	  }
		        	  String rest = arrayOfString2[i].substring(1, arrayOfString2[i].length());
		        	  String newStr = new StringBuffer(first).append(rest).toString();
		        	  sb.append(newStr);
		           }
	        }else{
	        	sb.append(str[0]);
	        }

      	  
	        if("char".equals(type)||"varchar".equals(type)){
	        	System.out.println("/**");
	        	System.out.println(" * " + comment);
	        	System.out.println(" */");
	        	System.out.println("private String " + sb.toString() + ";");
	        	System.out.println("");
	        }
	        if("decimal".equals(type)){
	        	System.out.println("/**");
	        	System.out.println(" * " + comment);
	        	System.out.println(" */");
	        	System.out.println("private double " + sb.toString() + ";");
	        	System.out.println("");
	        }
	        if("tinyint".equals(type)){
	        	System.out.println("/**");
	        	System.out.println(" * " + comment);
	        	System.out.println(" */");
	        	System.out.println("private int " + sb.toString() + ";");
	        	System.out.println("");
	        }
	        
	        if("int".equals(type)){
	        	System.out.println("/**");
	        	System.out.println(" * " + comment);
	        	System.out.println(" */");
	        	System.out.println("private long " + sb.toString() + ";");
	        	System.out.println("");
	        }
	        if("datetime".equals(type)||"date".equals(type)){
	        	System.out.println("/**");
	        	System.out.println(" * " + comment);
	        	System.out.println(" */");
	        	System.out.println("private Date " + sb.toString() + ";");
	        	System.out.println("");
	        }
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      MySqlConn.close(rs, state, conn);
	    }


	  }
}
