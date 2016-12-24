package com.jxx.demo.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {

	public void readPropertiesFile(String fileName)
			throws FileNotFoundException {
		String str = "";
		Properties prop = new Properties();

		InputStream stream = null;

		// 读取这个类在同一包中的properties文件
		// stream = getClass().getClassLoader().getResourceAsStream(fileName);
		System.out.println("path1:" + getClass().getResource(fileName));

		// 读取SRC下的的properties文件
		String path = getClass().getResource("/").getPath();
		System.out.println("path2:" +path);
		System.out.println("path3:" +getClass().getResource("/"));
//		stream = new BufferedInputStream(new FileInputStream(new File(path
//				+ fileName)));
		stream = getClass().getClassLoader().getResourceAsStream(fileName);
		try {
			prop.load(stream);
			str = prop.getProperty("localname");
			System.out.println("localname:" + str);
			System.out.println("properties:" + prop);
			stream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		new ReadPropertiesFile().readPropertiesFile("../LocalStrings.properties");

	}

}