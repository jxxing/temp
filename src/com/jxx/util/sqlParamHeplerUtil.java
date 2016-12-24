package com.jxx.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;


public class sqlParamHeplerUtil {
	public static PreparedStatement javaParam2SQLParam(Object[] param,
			PreparedStatement ps, String tableName) throws Exception {
		if ((param != null) && (param.length != 0)) {
			try {
				for (int i = 0; i < param.length; i++) {
					Object pa = param[i];
					if (pa == null) {
						ps.setObject(i + 1, null);
					} else if (Boolean.class.isInstance(pa)) {
						ps.setBoolean(i + 1, Boolean
								.parseBoolean(pa.toString()));
					} else if (Byte.class.isInstance(pa)) {
						ps.setByte(i + 1, Byte.parseByte(pa.toString()));
					} else if (Byte.class.isInstance(pa)) {
						ps.setBytes(i + 1, (byte[]) pa);
					} else if ((Character.class.isInstance(pa))
							|| (String.class.isInstance(pa))) {
						String st = String.valueOf(pa);
						ps.setString(i + 1, st);
					} else if (Short.class.isInstance(pa)) {
						ps.setShort(i + 1, Short.parseShort(pa.toString()));
					} else if (Integer.class.isInstance(pa)) {
						ps.setInt(i + 1, Integer.parseInt(pa.toString()));
					} else if (Long.class.isInstance(pa)) {
						ps.setLong(i + 1, Long.parseLong(pa.toString()));
					} else if (Float.class.isInstance(pa)) {
						ps.setFloat(i + 1, Float.parseFloat(pa.toString()));
					} else if (Double.class.isInstance(pa)) {
						ps.setDouble(i + 1, Double.parseDouble(pa.toString()));
					} else if (BigDecimal.class.isInstance(pa)) {
						ps.setBigDecimal(i + 1, new BigDecimal(pa.toString()));
					} else if ((java.sql.Date.class.isInstance(pa))
							|| (java.util.Date.class.isInstance(pa))) {
						try {
							ps.setDate(i + 1, (java.sql.Date) pa);
						} catch (Exception e) {
							Timestamp sqlDate = new Timestamp(
									((java.util.Date) pa).getTime());
							ps.setTimestamp(i + 1, sqlDate);
						}
					} else if (Time.class.isInstance(pa)) {
						ps.setTime(i + 1, Time.valueOf(pa.toString()));
					} else if (Timestamp.class.isInstance(pa)) {
						ps
								.setTimestamp(i + 1, Timestamp.valueOf(pa
										.toString()));
					} else {
						throw new Exception("Java程序中包含不支持的自动映射数据类型："
								+ pa.getClass().getName());
					}
				}
			} catch (Exception ex) {
				System.err
						.println("异常信息：特定数据库访问及其他相关错误！\r\n" + ex.getMessage());
				ex.printStackTrace();
				throw ex;
			}
		}

		return ps;
	}

	public static Object sQLParam2JavaParam(Class pojoClass, ResultSet rs,
			ResultSetMetaData rsmd) throws Exception {
		Object pojo = null;
		int index = 0;
		try {
			index = rsmd.getColumnCount();
			pojo = pojoClass.newInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			for (int i = 1; i <= index; i++) {
				String setMethodName = rsmd.getColumnName(i);

				setMethodName = StringUtil.unformat(setMethodName);
				setMethodName = "set"
						+ setMethodName.substring(0, 1).toUpperCase()
						+ setMethodName.substring(1);

				int dbType = rsmd.getColumnType(i);

				Method method = null;

				if (dbType == -6) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { Byte.TYPE });
					method.invoke(pojo, new Object[] { Byte.valueOf(rs
							.getByte(i)) });
				} else if (dbType == 5) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { Short.TYPE });
					method.invoke(pojo, new Object[] { Short.valueOf(rs
							.getShort(i)) });
				} else if ((dbType == 4) || (dbType == 2)) {
					try {
						method = pojoClass.getMethod(setMethodName,
								new Class[] { Integer.TYPE });
						method.invoke(pojo, new Object[] { Integer.valueOf(rs
								.getInt(i)) });
					} catch (NoSuchMethodException e) {
						method = pojoClass.getMethod(setMethodName,
								new Class[] { Double.TYPE });
						method.invoke(pojo, new Object[] { Double.valueOf(rs
								.getDouble(i)) });
					}
				} else if (dbType == -5) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { Long.TYPE });
					method.invoke(pojo, new Object[] { Long.valueOf(rs
							.getLong(i)) });
				} else if ((dbType == 6) || (dbType == 7)) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { Float.TYPE });
					method.invoke(pojo, new Object[] { Float.valueOf(rs
							.getFloat(i)) });
				} else if (dbType == 8) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { Double.TYPE });
					method.invoke(pojo, new Object[] { Double.valueOf(rs
							.getDouble(i)) });
				} else if (dbType == 3) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { Double.TYPE });
					method.invoke(pojo, new Object[] { Double.valueOf(rs
							.getDouble(i)) });
				} else if (dbType == -7) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { Boolean.TYPE });
					method.invoke(pojo, new Object[] { Boolean.valueOf(rs
							.getBoolean(i)) });
				} else if ((dbType == 1) || (dbType == 12) || (dbType == -1)) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { String.class });
					method.invoke(pojo, new Object[] { rs.getString(i) });
				} else if (dbType == 2005) {
					String clobStr = "";
					Clob cl = rs.getClob(i);
					method = pojoClass.getMethod(setMethodName,
							new Class[] { String.class });
					method.invoke(pojo, new Object[] { clobToString(cl) });
				} else if (dbType == 91) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { java.util.Date.class });
					java.util.Date date = rs.getDate(i);
					method.invoke(pojo, new Object[] { date });
				} else if (dbType == 92) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { java.util.Date.class });
					method.invoke(pojo, new Object[] { rs.getTime(i) });
				} else if (dbType == 93) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { java.util.Date.class });
					method.invoke(pojo, new Object[] { rs.getTimestamp(i) });
				} else if ((dbType == -2) || (dbType == -3) || (dbType == -4)
						|| (dbType == 2004)) {
					method = pojoClass.getMethod(setMethodName,
							new Class[] { Byte.class });
					method.invoke(pojo, new Object[] { rs.getBytes(i) });
				} else {
					throw new Exception("数据库中包含不支持的自动映射数据类型：" + dbType);
				}
			}
		} catch (InstantiationException ex) {
			throw new Exception(
					"异常信息：指定的类对象无法被 Class 类中的 newInstance 方法实例化！\r\n"
							+ ex.getMessage());
		} catch (NoSuchMethodException ex) {
			throw new Exception("异常信息：无法找到某一特定的方法！\r\n" + ex.getMessage());
		} catch (IllegalAccessException ex) {
			throw new Exception("异常信息：对象定义无法访问，无法反射性地创建一个实例！\r\n"
					+ ex.getMessage());
		} catch (InvocationTargetException ex) {
			throw new Exception("异常信息：由调用方法或构造方法所抛出异常的经过检查的异常！\r\n"
					+ ex.getMessage());
		} catch (SecurityException ex) {
			throw new Exception("异常信息：安全管理器检测到安全侵犯！\r\n" + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			throw new Exception("异常信息：向方法传递了一个不合法或不正确的参数！\r\n"
					+ ex.getMessage());
		} catch (SQLException ex) {
			throw new Exception("异常信息：获取数据库连接对象错误！\r\n" + ex.getMessage());
		} catch (Exception ex) {
			throw new Exception("异常信息：程序兼容问题！\r\n" + ex.getMessage());
		}

		return pojo;
	}

	public static String clobToString(Clob cl) throws Exception {
		String res = "";
		Reader is = null;
		if (cl == null)
			return "";
		try {
			is = cl.getCharacterStream();
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {
				sb.append(s);
				s = br.readLine();
				if (s != null) {
					sb.append("\r\n");
				}
			}
			res = sb.toString();

			String str1 = res;
			return str1;
		} catch (Exception e) {
			throw e;
		} finally {
			is.close();
		}
	}

	public static Map<String, Object> sQLParam2JavaParam(Map<String, Object> m,
			ResultSet rs, ResultSetMetaData rsmd) throws Exception {
		int index = 0;
		try {
			index = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			for (int i = 1; i <= index; i++) {
				String fieldName = rsmd.getColumnName(i);

				String javaName = StringUtil.unformat(fieldName);

				int dbType = rsmd.getColumnType(i);

				Method method = null;

				if (dbType == -6)
					m.put(javaName, Byte.valueOf(rs.getByte(i)));
				else if (dbType == 5)
					m.put(javaName, Short.valueOf(rs.getShort(i)));
				else if ((dbType == 4) || (dbType == 2))
					m.put(javaName, Integer.valueOf(rs.getInt(i)));
				else if (dbType == -5)
					m.put(javaName, Long.valueOf(rs.getLong(i)));
				else if ((dbType == 6) || (dbType == 7))
					m.put(javaName, Float.valueOf(rs.getFloat(i)));
				else if (dbType == 8)
					m.put(javaName, Double.valueOf(rs.getDouble(i)));
				else if (dbType == 3)
					m.put(javaName, rs.getBigDecimal(i));
				else if (dbType == -7)
					m.put(javaName, Boolean.valueOf(rs.getBoolean(i)));
				else if ((dbType == 1) || (dbType == 12) || (dbType == -1)
						|| (dbType == 2005))
					m.put(javaName, rs.getString(i));
				else if (dbType == 91) {
					m.put(javaName, rs.getDate(i));
				} else if (dbType == 92) {
					m.put(javaName, rs.getTime(i));
				} else if (dbType == 93) {
					m.put(javaName, rs.getTimestamp(i));
				} else if ((dbType == -2) || (dbType == -3) || (dbType == -4)
						|| (dbType == 2004))
					m.put(javaName, rs.getBytes(i));
				else
					throw new Exception("数据库中包含不支持的自动映射数据类型：" + dbType);
			}
		} catch (InstantiationException ex) {
			throw new Exception(
					"异常信息：指定的类对象无法被 Class 类中的 newInstance 方法实例化！\r\n"
							+ ex.getMessage());
		} catch (NoSuchMethodException ex) {
			throw new Exception("异常信息：无法找到某一特定的方法！\r\n" + ex.getMessage());
		} catch (IllegalAccessException ex) {
			throw new Exception("异常信息：对象定义无法访问，无法反射性地创建一个实例！\r\n"
					+ ex.getMessage());
		} catch (InvocationTargetException ex) {
			throw new Exception("异常信息：由调用方法或构造方法所抛出异常的经过检查的异常！\r\n"
					+ ex.getMessage());
		} catch (SecurityException ex) {
			throw new Exception("异常信息：安全管理器检测到安全侵犯！\r\n" + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			throw new Exception("异常信息：向方法传递了一个不合法或不正确的参数！\r\n"
					+ ex.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new Exception("异常信息：获取数据库连接对象错误！\r\n" + ex.getMessage());
		} catch (Exception ex) {
			throw new Exception("异常信息：程序兼容问题！\r\n" + ex.getMessage());
		}

		return m;
	}
}
