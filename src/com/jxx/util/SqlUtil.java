package com.jxx.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlUtil {

	// 数据库链接对象
	private Connection conn;

	private StringBuffer sbf = new StringBuffer();

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException {
		SqlUtil util = new SqlUtil();
		FieldValue obj = new FieldValue();
		obj.setName("shaojr");
		// obj.setCode("007");
		obj.setBirthday(new Date());
		String sql = util.genInsertSql(obj, "Field_Value");
		System.out.println(sql);

	}

	public String genUpdateSql(Object obj, String tablename) {
		Class<?> cls = obj.getClass();
		String sql = "update " + tablename + " set ";
		sql = sql + getFieldValueForUpdate(cls, obj);
		return sql;
	}

	public String genInsertSql(Object obj, String tablename)
			throws IllegalArgumentException, IllegalAccessException {
		Class<?> cls = obj.getClass();
		StringBuffer sql = new StringBuffer();

		String[] strArr = getFieldValueArrays(cls, obj);
		String columns = strArr[0];
		String values = strArr[1];

		columns = columns.substring(0, columns.length() - 1);
		values = values.substring(0, values.length() - 1);

		sql.append("insert into ");
		sql.append(tablename);
		sql.append(" (" + columns + ") ");
		sql.append(" values ");
		sql.append(" (" + values + ") ");

		return sql.toString();
	}

	private String[] getFieldValueArrays(Class cls, Object obj) {
		String[] strArr = new String[2];
		strArr[0] = " ";
		strArr[1] = " ";
		Field[] fds = cls.getDeclaredFields();
		for (int i = 0; i < fds.length; i++) {
			Field field = fds[i];
			String fieldname = field.getName();

			String fieldValue = getFieldValue(obj, fieldname);
			if (fieldValue != null) {
				strArr[0] = strArr[0] + getJxField(fieldname) + ",";
				strArr[1] = strArr[1] + fieldValue + ",";
			}

		}
		return strArr;
	}

	private String getFieldValueForUpdate(Class cls, Object obj) {
		StringBuffer fieldValueStr = new StringBuffer();
		Field[] fds = cls.getDeclaredFields();

		for (int i = 0; i < fds.length; i++) {
			Field field = fds[i];
			String fieldname = field.getName();
			String DBFieldname = getJxField(fieldname);
			String fieldValue = getFieldValue(obj, fieldname);

			if (fieldValue != null && !fieldname.equals("id")) {
				fieldValueStr.append(DBFieldname + "=" + fieldValue + " ,");
			}
		}
		String fv = fieldValueStr.substring(0, fieldValueStr.length() - 1);
		return fv;
	}

	private String getFieldValue(Object owner, String fieldName) {

		Object obj = invokeMethod(owner, fieldName, null);

		if (obj == null) {
			return null;
		}
		if (obj instanceof Date) {
			// to_date('2005-01-01 13:14:20','yyyy-MM-dd HH24:mi:ss')
			String date = DateTimeUtil.dateToString((Date) obj,
					"yyyy-MM-dd HH:mm:ss");
			String value = "to_date('" + date + "','yyyy-MM-dd HH24:mi:ss')";
			return value;
		}

		return "'" + obj.toString() + "'";
	}

	private Object invokeMethod(Object owner, String fieldName, Object[] args) {
		Class<? extends Object> ownerClass = owner.getClass();

		String methodName = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);

		Method method = null;
		try {
			method = ownerClass.getMethod("get" + methodName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return "";
		}

		try {
			return method.invoke(owner);
		} catch (Exception e) {
			return "";
		}
	}

	public String getJxField(String columnInfo) {
		sbf.delete(0, sbf.length());
		String word = columnInfo;
		int last_index = 0;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (Character.isUpperCase(c)) {
				String temp = word.substring(last_index, i);
				if (StringUtil.isNotBlank(temp)) {
					sbf.append(temp + "_");
				}
				last_index = i;
			}
			if (i == (word.length() - 1)) {
				String temp = word.substring(last_index, word.length());
				if (StringUtil.isNotBlank(temp)) {
					sbf.append(temp);
				}
			}
		}

		return sbf.toString();
	}

	public SqlUtil(Connection conn) {
		super();
		this.conn = conn;
	}

	public SqlUtil() {
		super();
	}

	/**
	 * 查询结果返回列表
	 * @param sqlStr
	 * @param sqlParam
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public List queryListBySql(String sqlStr, List sqlParam, Class cls)
			throws Exception {
		List resultList = new ArrayList();
		// 数据连接操作对象
		PreparedStatement pstm = null;
		// 查询结果集
		ResultSet rs = null;
		// 元数据
		ResultSetMetaData rsm = null;
		if (this.conn != null && !this.conn.isClosed()) {
			pstm = conn.prepareStatement(sqlStr);
			if (sqlParam != null && sqlParam.size() > 0) {
				for (int i = 0; i < sqlParam.size(); i++) {
					pstm.setObject(i + 1, sqlParam.get(i));
				}
			}
			// 得到结果集
			rs = pstm.executeQuery();
			rsm = pstm.getMetaData();
			while (rs.next()) {
				Map<String, String> rsMap = new HashMap<String, String>();
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					String name = rsm.getColumnName(i);
					// 转化为小写
					rsMap.put(name.toLowerCase(), rs.getString(name));
				}
				Object obj = ReflectUtil.build(rsMap, cls);
				resultList.add(obj);
			}
		}
		return resultList;
	}

	/**
	 * 查询结果返回对象
	 * @param sqlStr
	 * @param sqlParam
	 * @param cls
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Object queryObjBySql(String sqlStr, List<String> sqlParam, Class cls)
			throws SQLException, Exception {
		Object obj = null;
		// 数据连接操作对象
		PreparedStatement pstm = null;
		// 查询结果集
		ResultSet rs = null;
		// 元数据
		ResultSetMetaData rsm = null;
		if (this.conn != null && !this.conn.isClosed()) {
			pstm = conn.prepareStatement(sqlStr);
			if (sqlParam != null && sqlParam.size() > 0) {
				for (int i = 0; i < sqlParam.size(); i++) {
					pstm.setObject(i + 1, sqlParam.get(i));
				}
			}
			// 得到结果集
			rs = pstm.executeQuery();
			rsm = pstm.getMetaData();
			if (rs.next()) {
				Map<String, String> rsMap = new HashMap<String, String>();
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					String name = rsm.getColumnName(i);
					// 转化为小写
					rsMap.put(name.toLowerCase(), rs.getString(name));
				}
				obj = ReflectUtil.build(rsMap, cls);
			}
		}
		return obj;
	}
	
	/**
	 * 执行sql语句
	 * @param sqlStr
	 * @param sqlParam
	 * @throws Exception
	 */
	public void executeSql(String sqlStr,List sqlParam) throws Exception{
		//数据连接操作对象
		PreparedStatement pstm = null;
		if(this.conn!=null && !this.conn.isClosed()){
			pstm = conn.prepareStatement(sqlStr);
			if(sqlParam!=null && sqlParam.size()>0){
				for(int i=0;i<sqlParam.size();i++){
					pstm.setObject(i+1, sqlParam.get(i));
				}
			}
			pstm.executeQuery();
		}	
	}
}

class FieldValue {
	private String code;

	private String name;

	private int age;

	private Date birthday;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
