package com.jxx.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class XmlDomUtil {
	public static void main(String[] args) {
		String disc = "com.zrar.entity.GpZzCat";		
		int beginIndex =disc.lastIndexOf(".");		
		disc=disc.substring(beginIndex+1, disc.length());		
		System.out.println(disc);
		
	}
	
	public static <T> List<T> parseXml(String xml,Class cls){
		List<T> list = new ArrayList<T>();
		Object obj =null;
		
		Document doc = null;
				
		try {
			obj = cls.newInstance();
			
			String fullname=cls.getName();
	        String tableName=fullname.substring((fullname.lastIndexOf(".")+1), fullname.length());	        
			doc = DocumentHelper.parseText(xml);
			Element rootElt = doc.getRootElement(); // 获取根节点
						
			Iterator tableIt = rootElt.elementIterator(tableName);
			while (tableIt.hasNext()) {
				Element table = (Element) tableIt.next();
				//System.out.println(table.getName());
				Iterator colIt = table.elementIterator();
																			
					Map<String,String> columnDates=new HashMap<String, String>();
					while (colIt.hasNext()) {
						Element column = (Element) colIt.next();						
						columnDates.put(column.getName(), column.getTextTrim());
					}
										
					obj = ReflectUtil.MapToObject(cls, columnDates);
					list.add((T) obj);
								
			}
			//System.out.println("-------------------------------");						

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static List<Object> parseXml(String xml,Class[] classes){
		List<Object> list = new ArrayList<Object>();
		Object obj =null;
		
		Document doc = null;
		
		try {
			
			doc = DocumentHelper.parseText(xml);
			Element rootElt = doc.getRootElement(); // 获取根节点
			
			for(Class cls:classes){
				obj = cls.newInstance();
				String fullname=cls.getName();
		        String tableName=fullname.substring((fullname.lastIndexOf(".")+1), fullname.length());
				
				Iterator enterIt = rootElt.elementIterator(tableName);
				while (enterIt.hasNext()) {
					Element table = (Element) enterIt.next();
					//System.out.println(table.getName());
					Iterator colmIt = table.elementIterator();
																							
						Map<String,String> columnDates=new HashMap<String, String>();
						while (colmIt.hasNext()) {
							Element column = (Element) colmIt.next();							
							columnDates.put(column.getName(), column.getTextTrim());
						}						
						
						obj = ReflectUtil.MapToObject(cls, columnDates);
						list.add(obj);
										
				}
				
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String ObjectToXml(Object obj){
		String rootElment ="NewDataSet";
		return ObjectToXml(obj,rootElment);	 
	}
	
	public static String ObjectToXml(Object obj,String rootElmentName){
		String className=obj.getClass().getName();	
		Field[] fields = obj.getClass().getDeclaredFields();		
		
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement(rootElmentName);
		Element sonElement = rootElement.addElement(className);
		for(Field field :fields){
			String fieldName = field.getName();
			String fieldValue = ReflectUtil.getFieldValue(obj, fieldName);						
			sonElement.addElement(fieldName).setText(StringUtil.null2Empty(fieldValue));
		}		
		document.setXMLEncoding("UTF-8");	
		return document.asXML();
	}
	
	public static String ListToXml(List<Object> objList ){
		String rootElmentName="NewDataSet";		
		return ListToXml(objList,rootElmentName);
	}
	
	public static String ListToXml(List<Object> objList,String rootElmentName){
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement(rootElmentName);
			
		for(Object obj:objList){
			String className=obj.getClass().getName();	
			Element sonElement = rootElement.addElement(className);
			
			Field[] fields = obj.getClass().getDeclaredFields();
			
			for(Field field :fields){
				String fieldName = field.getName();
				String fieldValue = ReflectUtil.getFieldValue(obj, fieldName);						
				sonElement.addElement(fieldName).setText(StringUtil.null2Empty(fieldValue));
			}		
						
		}
		document.setXMLEncoding("UTF-8");
		return document.asXML();
	}
}
