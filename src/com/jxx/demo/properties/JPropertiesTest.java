package com.jxx.demo.properties;

import java.util.Properties;

import junit.framework.TestCase;
//import javax.servlet.ServletContext;

public class JPropertiesTest extends TestCase {
	JProperties jProperties;

	String key = "helloworld.title";

	String value = "Hello World!";

	public void testLoadProperties() throws Exception {
		
		
		String name = null;
		Properties p = new Properties();

		name = "E:\\mydoc\\MyProject\\src\\com\\jxx\\demo\\LocalStrings.properties";//绝对路径
		p = JProperties.loadProperties(name, JProperties.BY_PROPERTIES);
		assertEquals(value, p.getProperty(key));

		name = "com.jxx.demo.LocalStrings";
		p = JProperties.loadProperties(name, JProperties.BY_RESOURCEBUNDLE);
		assertEquals(value, p.getProperty(key));
		assertEquals(value, ((JProperties.ResourceBundleAdapter) p)
				.getString(key));

		name = "E:\\mydoc\\MyProject\\src\\com\\jxx\\demo\\LocalStrings.properties";
		p = JProperties.loadProperties(name,
				JProperties.BY_PROPERTYRESOURCEBUNDLE);
		assertEquals(value, p.getProperty(key));
		assertEquals(value, ((JProperties.ResourceBundleAdapter) p)
				.getString(key));

		name = "\\com\\jxx\\demo\\LocalStrings.properties";//
		p = JProperties.loadProperties(name, JProperties.BY_SYSTEM_CLASSLOADER);
		assertEquals(value, p.getProperty(key));

		name = "\\com\\jxx\\demo\\LocalStrings.properties";//相对程序的位置
		p = JProperties.loadProperties(name, JProperties.BY_CLASSLOADER);
		assertEquals(value, p.getProperty(key));

		name = "..\\LocalStrings.properties";//相对此类的位置
		p = JProperties.loadProperties(name, JProperties.BY_CLASS);
		assertEquals(value, p.getProperty(key));
	}

}