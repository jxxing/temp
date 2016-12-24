package com.jxx.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ReflectUtil {

	// 封装属性
	private static Map<String, Field> fieldMap = new HashMap<String, Field>();

	// 封装属性的set方法
	private static Map<String, Method> methodMap = new HashMap<String, Method>();

	private static String dateFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Map to object
	 * @param cls
	 * @param values
	 * @return
	 */
	public static Object MapToObject(Class<?> cls, Map values) {
		Object obj = null;
		try {
			obj = cls.newInstance();
			Field[] fields = obj.getClass().getDeclaredFields();

			Method[] methods = obj.getClass().getDeclaredMethods();

			for (Field field : fields) {
				String attri = field.getName();
				fieldMap.put(attri.toLowerCase(), field);
				for (Method method : methods) {
					String meth = method.getName();
					// 匹配set方法
					if (meth != null
							&& "set".equals(meth.substring(0, 3))
							&& Modifier.isPublic(method.getModifiers())
							&& ("set" + Character.toUpperCase(attri.charAt(0)) + attri
									.substring(1)).equals(meth)) {
						methodMap.put(attri.toLowerCase(), method);
						break;
					}
				}
			}

			// 2、属性赋值
			for (Iterator it = values.keySet().iterator(); it.hasNext();) {
				String name = (String) it.next();
				Object value = values.get(name);

				if (value == null)
					continue;

				Field field = fieldMap.get(name.toLowerCase());
				if (field == null)
					continue;

				Method method = methodMap.get(name.toLowerCase());
				if (method == null)
					continue;

				fillWithObject(obj, field, method, value);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * @param bean
	 * @param field
	 * @param method
	 * @param value
	 */
	private static void fillWithObject(Object bean, Field field, Method method,
			Object value) {
		if (value == null || "null".equalsIgnoreCase(value.toString())
				|| StringUtil.isBlank(value.toString()))
			return;
		try {
			Object[] oo = new Object[1];
			String fieldType = field.getType().getName();

			if ("java.lang.String".equals(fieldType)) {
				oo[0] = (String) value;
			} else if ("java.lang.Integer".equals(fieldType)) {
				if (value instanceof Integer) {
					oo[0] = value;
				} else {
					oo[0] = Integer.valueOf(value.toString());
				}
			} else if ("int".equals(fieldType)) {
				oo[0] = (int) Integer.valueOf(value.toString());
			} else if ("java.lang.Float".equals(fieldType)) {
				oo[0] = Float.valueOf(value.toString());
			} else if ("float".equals(fieldType)) {
				oo[0] = (float) Float.valueOf(value.toString());
			} else if ("java.lang.Double".equals(fieldType)) {
				oo[0] = Double.valueOf(value.toString());
			} else if ("double".equals(fieldType)) {
				oo[0] = (double) Double.valueOf(value.toString());
			} else if ("java.math.BigDecimal".equals(fieldType)) {
				oo[0] = new BigDecimal(value.toString());
			} else if ("java.util.Date".equals(fieldType)) {
				if (value instanceof Date) {
					oo[0] = (Date) value;
				} else {
					oo[0] = DateTimeUtil.stringToDate(value.toString(),
							getDateFormat());
				}
			} else if ("java.sql.Timestamp".equals(fieldType)) {
				if (value instanceof Timestamp) {
					oo[0] = (Timestamp) value;
				} else {
					oo[0] = DateTimeUtil.stringToTimestamp(value.toString(),
							getDateFormat());
				}

			} else if ("java.lang.Boolean".equals(fieldType)) {
				if (value instanceof Boolean) {
					oo[0] = (Boolean) value;
				} else {
					oo[0] = Boolean.valueOf(value.toString());
				}
			} else if ("boolean".equals(fieldType)) {
				oo[0] = (boolean) Boolean.valueOf(value.toString());
			} else if ("java.lang.Long".equals(fieldType)) {
				oo[0] = Long.valueOf(value.toString());
			} else if ("long".equals(fieldType)) {
				oo[0] = (long) Long.valueOf(value.toString());
			}

			method.invoke(bean, oo);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * map to object 2
	 * @param request
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static Object build(Map request, Class cls)throws Exception{
		Object obj = cls.newInstance();
		Field[] fieldArray = cls.getDeclaredFields();
		//遍历类中的所有属性
		for (int i = 0; i < fieldArray.length; ++i) {
			Field field = fieldArray[i];
			//属性对象类型
			String fieldName = field.getName();
			String paramName = fieldName.toLowerCase();//小写;
			Object paramObj = request.get(paramName);
			if (paramObj == null) {
				continue;
			}
			String paramValue = null;
			if (paramObj instanceof String)
				paramValue = (String)paramObj;
			else {
				paramValue = ((String[])paramObj)[0];
			}
			setValue(obj, fieldName, field.getType(), paramValue);
		}
		return obj;
	}
	

	/**
	 * method (it*)
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static Iterator<Object> itObject(Object obj, String fieldName)
			throws Exception {
		String methodName = "it" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		return (Iterator) exeMethod(obj, methodName, new Class[0],
				new Object[0]);
	}
	
	/**
	 * method (get*)
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static Object getValue(Object obj, String fieldName)
			throws Exception {
		String methodName = "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		return exeMethod(obj, methodName, new Class[0], new Object[0]);
	}

	/**
	 * method (set*)
	 * @param obj
	 * @param fieldName
	 * @param fieldType
	 * @param fieldValue
	 * @return
	 * @throws Exception
	 */
	 public static boolean setValue(Object obj, String fieldName, Class fieldType, String fieldValue)
	    throws Exception
	  {
	    Class cls = obj.getClass();
	    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	    if (fieldType.equals(Integer.TYPE)) {
	      if (StringUtil.isNullorEmpty(fieldValue)) {
	        return false;
	      }
	      fieldValue = fieldValue.trim();
	      exeMethod(obj, methodName, new Class[] { Integer.TYPE }, new Object[] { new Integer(fieldValue) });
	      return true;
	    }if (fieldType.equals(Integer.class)) {
	      if (StringUtil.isNullorEmpty(fieldValue)) {
	        return false;
	      }
	      fieldValue = fieldValue.trim();
	      exeMethod(obj, methodName, new Class[] { Integer.class }, new Object[] { new Integer(fieldValue) });
	      return true;
	    }if (fieldType.equals(Long.TYPE)) {
	      if (StringUtil.isNullorEmpty(fieldValue)) {
	        return false;
	      }
	      fieldValue = fieldValue.trim();
	      exeMethod(obj, methodName, new Class[] { Long.TYPE }, new Object[] { new Long(fieldValue) });
	      return true;
	    }if (fieldType.equals(Long.class)) {
	      if (StringUtil.isNullorEmpty(fieldValue)) {
	        return false;
	      }
	      fieldValue = fieldValue.trim();
	      exeMethod(obj, methodName, new Class[] { Long.class }, new Object[] { new Long(fieldValue) });
	      return true;
	    }if (fieldType.equals(Double.TYPE)) {
	      if (StringUtil.isNullorEmpty(fieldValue)) {
	        return false;
	      }
	      fieldValue = fieldValue.trim();
	      exeMethod(obj, methodName, new Class[] { Double.TYPE }, new Object[] { new Double(fieldValue) });
	      return true;
	    }if (fieldType.equals(Double.class)) {
	      if (StringUtil.isNullorEmpty(fieldValue)) {
	        return false;
	      }
	      fieldValue = fieldValue.trim();
	      exeMethod(obj, methodName, new Class[] { Double.class }, new Object[] { new Double(fieldValue) });
	      return true;
	    }if (fieldType.equals(String.class)) {
	      exeMethod(obj, methodName, new Class[] { String.class }, new Object[] { fieldValue });
	      return true;
	    }if (fieldType.equals(java.util.Date.class)) {
	      exeMethod(obj, methodName, new Class[] { java.util.Date.class }, new Object[] { DateTimeUtil.stringToDate(fieldValue) });
	      return true;
	    }if (fieldType.equals(java.sql.Date.class)) {
	      exeMethod(obj, methodName, new Class[] { java.sql.Date.class }, new Object[] { DateTimeUtil.stringToSqlDate(fieldValue) });
	      return true;
	    }if (fieldType.equals(Boolean.TYPE)) {
	      exeMethod(obj, methodName, new Class[] { Boolean.TYPE }, new Object[] { Boolean.valueOf(fieldValue) });
	      return true;
	    }if (fieldType.equals(Boolean.class)) {
	      exeMethod(obj, methodName, new Class[] { Boolean.class }, new Object[] { Boolean.valueOf(fieldValue) });
	      return true;
	    }
	    return false;
	  }

	
	 /**
	  * 发射方法 取object
	  * @param classObj
	  * @param method
	  * @param paramTypeArray
	  * @param params
	  * @return
	  * @throws Exception
	  */
	public static Object exeMethod(Object classObj, String method,
			Class[] paramTypeArray, Object[] params) throws Exception {
		Class classDef = classObj.getClass();
		try {
			Method methodObj = classDef.getMethod(method, paramTypeArray);
			return methodObj.invoke(classObj, params);
		} catch (Exception e) {
			throw e;
		}

	}

	private static String getDateFormat() {
		return dateFormat;
	}

	public static void setDateFormat(String dateFormat) {
		ReflectUtil.dateFormat = dateFormat;
	}

}
