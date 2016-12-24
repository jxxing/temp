package com.jxx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

	public static Properties props = null;

	public PropertiesUtil(){
		
	}
	
	public PropertiesUtil(String fileName) {
		props = readProperties(PropertiesUtil.class.getClassLoader()
				.getResourceAsStream(fileName));
	}

	public static String getString(String name) {
		if (props == null) {
			return "";
		}
		String prop = props.getProperty(name);
		if (prop != null) {
			prop = prop.trim();
		} else {
			prop = "";
		}
		return prop;
	}

	public static int getInt(String key) {
		int rtInt = 0;
		try {
			String strValue = getString(key);
			rtInt = Integer.parseInt(strValue);
		} catch (Exception localException) {
		}
		return rtInt;
	}

	public static long getLong(String key) {
		long rtLong = 0L;
		try {
			String strValue = getString(key);
			rtLong = Long.parseLong(strValue);
		} catch (Exception localException) {
		}
		return rtLong;
	}

	public static void setProps(Properties prop) {
		props = prop;
	}

	public static void updateProp(Object key, Object value) {
		props.put(key, value);
	}

	public static void addProps(Map aProps) {
		if (aProps == null) {
			return;
		}
		Iterator iKeys = aProps.keySet().iterator();
		while (iKeys.hasNext()) {
			String key = (String) iKeys.next();
			String value = (String) aProps.get(key);
			props.put(key, value);
		}
	}

	// 读取properties的全部信息
	public static Properties readProperties(String filePath) {
		Properties propsMap = null;
		try {
			propsMap = readProperties(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return propsMap;
	}

	// 读取properties的全部信息
	public static Properties readProperties(File file) {
		Properties propsMap = null;
		try {
			propsMap = readProperties(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return propsMap;
	}

	public static Properties readProperties(InputStream inStream) {
		Properties propsMap = new Properties();

		Properties props = new Properties();
		try {
			// InputStream ini = new BufferedInputStream(new
			// FileInputStream(filePath));
			InputStreamReader in = new InputStreamReader(inStream, "UTF-8");
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				propsMap.put(key, Property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return propsMap;
	}

	// 写入properties信息
	public static void writeProperties(String filePath, Properties inputData) {
		Properties prop = new Properties();
		try {
			// InputStream fis = new FileInputStream(filePath);
			InputStreamReader in = new InputStreamReader(new FileInputStream(
					"award.properties"), "UTF-8");
			prop.load(in);

			// OutputStream fos = new FileOutputStream(filePath);
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(filePath), "UTF-8");
			Enumeration en = inputData.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = inputData.getProperty(key);
				prop.put(key, Property);
			}

			prop.store(writer, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		//PropertiesUtil propertiesUtil = new PropertiesUtil("LocalStrings.properties");
		//propertiesUtil.getString("cookies.title");
		PropertiesUtil.setProps(JProperties.loadProperties("LocalStrings.properties", JProperties.BY_CLASSLOADER));
		System.out.println(PropertiesUtil.getString("cookies.title"));
	}

}
