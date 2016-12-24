package com.jxx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class FileUtil {
	public static String FS = File.separator;
	public static String FPS = File.pathSeparator;
	
	/**
	 * 拷贝文件 source 原文件 ,dest   目标文件
	 * @param source
	 * @param dest
	 */
	public static void copyFile(File source, File dest){
		InputStream inputStream = null;
		OutputStream outputStream = null;
		if(!source.exists()){
			System.out.println("源文件不存在！");
			return;
		}
		if(!dest.exists()){
			dest.getParentFile().mkdirs();
		}
		try {
			inputStream = new FileInputStream(source);
	        outputStream = new FileOutputStream(dest);
	        byte[] b = new byte[1024];
	        int len = 0;
	        while((len = inputStream.read(b))!=-1){
	        	outputStream.write(b, 0, len);
	        }
	        outputStream.flush();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			closeStream(inputStream,outputStream);
		}
	}
	
	/**
	 * 拷贝文件 source 原文件字符 ,dest   目标文件字符
	 * @param source
	 * @param dest
	 */
	public static void copyFile(String source, String dest){
		copyFile(new File(source),new File(dest));
	}
	
	/**
	 * 换取文件中的内容（string），用reader读取，可以用2张方式转化为reader
	 * @param file
	 * @return
	 */
	public static String getFileConnent(File file) {
		if(!file.exists()){
			System.out.println("源文件不存在！");
			return null;
		}
		InputStream inputStream = null;
//		FileReader fileReader=null;  
		BufferedReader bufferedReader=null;
		InputStreamReader inputStreamReader = null;
		byte[] b = new byte[1024];
		int count = 0;
		int temp = 0;
		String str = "";
		String read = null;String encoding="GBK";
		try {
			inputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(inputStream,encoding);//考虑到编码格式
            bufferedReader = new BufferedReader(inputStreamReader);
//byte数组长度控制不好 jiangxx 20140115
//			while ((temp = inputStream.read()) != (-1)) {
//				b[count++] = (byte) temp;
//			}
//			fileReader=new FileReader(file);  
//			bufferedReader=new BufferedReader(fileReader);//另外一种方式获取reader
			while((read=bufferedReader.readLine())!=null){  count++;
				str=str+read+"\r\n";  
		    }  
			System.out.println(count);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			close(inputStream,null,null,null);
			closeC(bufferedReader,null);
			closeC(inputStreamReader,null);
		}
//		str = new String(b);
		return str;
	}
	
	/**
	 * 换取文件（string）中的内容（string），调用getFileConnent(file)方法
	 * @param fileName
	 * @return
	 */
	public static String getFileConnent(String fileName) {
		return getFileConnent(new File(fileName));
	}
	
	/**
	 * byte数组长度控制不好 jiangxx 20140115,不推荐使用
	 * @param file
	 * @return
	 */
	@Deprecated	
	public static String inputStream(File file) {
		if(!file.exists()){
			System.out.println("源文件不存在！");
			return null;
		}
		StringBuffer sb = new StringBuffer();
		String str = null;
		InputStream inputStream = null;
		byte[] b = new byte[1024];
		try {
			inputStream = new FileInputStream(file);
			int count = 0;
			int temp = 0;
//			while ((temp = inputStream.read()) != (-1)&&count<1024) {//如果大于1024，不加上后面的count<1024会报错
//				b[count++] = (byte) temp;
//			}
			while(inputStream.read(b)!=-1){
				sb.append(new String(b));
				b = new byte[1024];//有多余的空间会不常见字符 20140116
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			closeStream(inputStream,null);
		}
		str = new String(b);//字数太少则会出现不常见字符
		return sb.toString();
	}
	
	/**
	 * 得到文件中的内容,byte数组长度控制不好 jiangxx 20140115,不推荐使用
	 * @param file
	 * @return
	 */
	@Deprecated	
	public static String inputStream(String fileName) {
		return inputStream(new File(fileName));
	}
	
	/**
	 * 得到文件中的内容
	 * @param file
	 * @return
	 */
	public static String fileReader(File file) {
		if(!file.exists()){
			System.out.println("源文件不存在！");
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Reader read = null;
		int temp = 0;
		try{
			read = new FileReader(file);
			while((temp = read.read())!=-1){
				sb.append((char)temp);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			closeC(read,null);
		}
		
		return sb.toString();
	}
	
	/**
	 * 得到文件中的内容
	 * @param fileName
	 * @return
	 */
	public static String fileReader(String fileName) {
		return fileReader(new File(fileName));
	}

	/**
	 * inputStream转成 Reader，得到文件中的内容，可以换编码，上述的没有实现 jiangxx 20140115
	 * 问题 utf-8 第一字符出现？ 20140115
	 * @param file
	 * @param charset
	 * @return
	 */
	public static String inputStreamReader(File file, String charset) {
		if(!file.exists()){
			System.out.println("源文件不存在！");
			return null;
		}
		InputStream inputStream = null;
		StringBuffer sb = new StringBuffer();
		Reader read = null;
		int temp = 0;
		try{
			inputStream = new FileInputStream(file);
			read = new InputStreamReader(inputStream,charset);
			while((temp = read.read()) != -1){
				//System.out.println(temp);System.out.println((char)temp);
				sb.append((char)temp);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			closeStream(inputStream,null);
			closeC(read,null);
		}
		return sb.toString();
	}
	
	/**
	 * inputStream转成 Reader，得到文件中的内容，可以换编码
	 * @param fileName
	 * @param charset
	 * @return
	 */
	public static String inputStreamReader(String fileName, String charset) {
		return inputStreamReader(new File(fileName),charset);
	}
	
	/**
	 * inputStream转成 Reader，得到文件中的内容，可以换编码
	 * @param fileName
	 * @return
	 */
	public static String inputStreamReader(String fileName) {
		return inputStreamReader(fileName,"GBK");
	}
	
	/**
	 * 整行读取文件中的内容
	 * 问题 utf-8 第一个字符出现？ 不知道为什么，所以读取文件最好为gbk
	 * @param file
	 * @param charset
	 * @return
	 */
	public static String readLines(File file, String charset){
		StringBuffer sb = new StringBuffer();
		if(!file.exists()){
			System.out.println("源文件不存在！");
			return null;
		}
		InputStream inputStream = null;
		Reader read = null;
		BufferedReader readb = null;
		String temp = null;
		try{
			inputStream = new FileInputStream(file);
			read = new InputStreamReader(inputStream,charset);
			readb = new BufferedReader(read);
			while((temp = readb.readLine())!= null){
				sb.append(temp);
				sb.append("\r\n");
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			closeStream(inputStream,null);
			closeC(read,null);
			closeC(readb,null);
		}
		return sb.toString();
	}
	
	/**
	 * 整行读取文件中的内容
	 * 问题 utf-8 第一个字符出现？
	 * @param file
	 * @param charset
	 * @return
	 */
	public static String readLines(String fileName, String charset){
		return readLines(new File(fileName),charset);
	}
	
	/**
	 * 整行读取文件中的内容
	 * @param file
	 * @param charset
	 * @return
	 */
	public static String readLines(String fileName){
		return readLines(fileName,"GBK");
	}
	
	/**
	 * 把内容写进文件中
	 * 编码：GBK
	 * @param file
	 * @param content
	 * @param isAppend
	 */
	public static void outStream(File file, String content,boolean isAppend) {

		OutputStream outputStream = null;
		byte[] b = null;
		try {
			outputStream = new FileOutputStream(file, isAppend);
			b = content.getBytes();
			int len = b.length;
			for (int i = 0; i < len; i++) {
				outputStream.write(b[i]);
			}
			//outputStream.write(content.getBytes());  //content.getBytes("GBK")
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			closeStream(null,outputStream);
		}
		
	}
	
	/**
	 * 把内容写进文件中
	 * 编码：GBK
	 * @param filename
	 * @param content
	 * @param isAppend  true 追加  false 覆盖
	 */	
	public static void outStream(String filename, String content,boolean isAppend) {
		outStream(new File(filename), content, isAppend);
	}
	
	/**
	 * 把内容写进文件中
	 * 编码：GBK
	 * @param filename
	 * @param content
	 */
	public static void outStream(String filename, String content) {
		outStream(filename, content, false);
	}
	

	/**
	 * 把内容写进文件中,writer
	 * 编码：GBK
	 * @param file
	 * @param content
	 * @param isAppend
	 */
	public static void fileWriter(File file, String content,boolean isAppend) {
		Writer writer = null;
		try{
			writer = new FileWriter(file,isAppend);
			writer.write(content);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			closeC(null,writer);
		}
	}
	
	/**
	 * 把内容写进文件中,writer
	 * 编码：GBK
	 * @param fileName
	 * @param content
	 * @param isAppend
	 */
	public static void fileWriter(String fileName, String content,boolean isAppend) {
		fileWriter(new File(fileName), content, isAppend);
	}
	
	/**
	 * 把内容写进文件中,writer
	 * 编码：GBK
	 * @param fileName
	 * @param content
	 */
	public static void fileWriter(String fileName, String content) {
		fileWriter(fileName, content, false);
	}
	
	/**
	 * 关闭输入，输出流
	 * @param in
	 * @param out
	 * @param reader
	 * @param writer
	 */
	public static void close(InputStream in,OutputStream out,Reader reader,Writer writer){
		try {
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
			if(reader != null){
				reader.close();
			}
			if(writer != null){
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭输入，输出流
	 * @param in
	 * @param out
	 */
	public static void closeStream(InputStream in,OutputStream out){
		try {
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭输入，输出流
	 * @param reader
	 * @param writer
	 */
	public static void closeC(Reader reader,Writer writer){
		try {
			if(reader != null){
				reader.close();
			}
			if(writer != null){
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//copyFile("D:\\log.log","D:\\log.log11");
		//System.out.println(FS+FPS);System.out.println(FPS);
		//System.out.println(getFileConnent("D:\\log.log"));
		//System.out.println(inputStream("D:\\log.log"));
		//System.out.println(fileReader("D:\\log.log"));
		//System.out.println(inputStreamReader("D:\\log.logtemp.txt"));
		System.out.println(readLines("D:\\logutf-8-2.txt","UTF-8"));
		//System.out.println(readLines("D:\\log.log2"));
		//outStream("D:\\log.logtemp.txt","dfdfdfdfdfdfdfdfdfdf都纷纷发反反复复反反复复",false);
		//fileWriter("D:\\log.logtemp.txt","ss都纷纷倒戈灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌灌",false);
	}

}
