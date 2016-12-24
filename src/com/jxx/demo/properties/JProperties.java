package com.jxx.demo.properties;

import java.util.*;
import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class JProperties {

	public final static int BY_PROPERTIES = 1;

	public final static int BY_RESOURCEBUNDLE = 2;

	public final static int BY_PROPERTYRESOURCEBUNDLE = 3;

	public final static int BY_CLASS = 4;

	public final static int BY_CLASSLOADER = 5;

	public final static int BY_SYSTEM_CLASSLOADER = 6;

	public final static Properties loadProperties(final String name,
			final int type) throws IOException {
		Properties p = new Properties();
		InputStream in = null;
		if (type == BY_PROPERTIES) {//绝对地址
			in = new BufferedInputStream(new FileInputStream(name));
			assert (in != null);
			p.load(in);
		} else if (type == BY_RESOURCEBUNDLE) {//包括包的类名
			ResourceBundle rb = ResourceBundle.getBundle(name, Locale
					.getDefault());
			assert (rb != null);
			p = new ResourceBundleAdapter(rb);
		} else if (type == BY_PROPERTYRESOURCEBUNDLE) {//绝对地址
			in = new BufferedInputStream(new FileInputStream(name));
			assert (in != null);
			ResourceBundle rb = new PropertyResourceBundle(in);
			p = new ResourceBundleAdapter(rb);
		} else if (type == BY_CLASS) {//名称  相对这个类的位置
			assert (JProperties.class.equals(new JProperties().getClass()));
			in = JProperties.class.getResourceAsStream(name);
			assert (in != null);
			p.load(in);
			// return new JProperties().getClass().getResourceAsStream(name);
		} else if (type == BY_CLASSLOADER) {//相对路径 相对程序的位置  已例子为准
			assert (JProperties.class.getClassLoader().equals(new JProperties()
					.getClass().getClassLoader()));
			in = JProperties.class.getClassLoader().getResourceAsStream(name);
			assert (in != null);
			p.load(in);
			// return new
			// JProperties().getClass().getClassLoader().getResourceAsStream(name);
		} else if (type == BY_SYSTEM_CLASSLOADER) {//相对路径
			in = ClassLoader.getSystemResourceAsStream(name);
			assert (in != null);
			p.load(in);
		}

		if (in != null) {
			in.close();
		}
		return p;

	}

	// ---------------------------------------------- servlet used

	// ---------------------------------------------- support class

	public static class ResourceBundleAdapter extends Properties {
		public ResourceBundleAdapter(ResourceBundle rb) {
			assert (rb instanceof java.util.PropertyResourceBundle);
			this.rb = rb;
			java.util.Enumeration e = rb.getKeys();
			while (e.hasMoreElements()) {
				Object o = e.nextElement();
				this.put(o, rb.getObject((String) o));
			}
		}

		private ResourceBundle rb = null;

		public ResourceBundle getBundle(String baseName) {
			return ResourceBundle.getBundle(baseName);
		}

		public ResourceBundle getBundle(String baseName, Locale locale) {
			return ResourceBundle.getBundle(baseName, locale);
		}

		public ResourceBundle getBundle(String baseName, Locale locale,
				ClassLoader loader) {
			return ResourceBundle.getBundle(baseName, locale, loader);
		}

		public Enumeration getKeys() {
			return rb.getKeys();
		}

		public Locale getLocale() {
			return rb.getLocale();
		}

		public Object getObject(String key) {
			return rb.getObject(key);
		}

		public String getString(String key) {
			return rb.getString(key);
		}

		public String[] getStringArray(String key) {
			return rb.getStringArray(key);
		}

		protected Object handleGetObject(String key) {
			return ((PropertyResourceBundle) rb).handleGetObject(key);
		}

	}

}
