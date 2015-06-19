package study.Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class RTest {

	public static void main(String[] args){
		Class dm = RSun.class;
		Field[] field1 = dm.getFields();
		Field[] field2 = dm.getDeclaredFields();
		/**
		 * 	getFields()获得某个类的所有的公共（public）的字段，包括父类。 

			getDeclaredFields()获得某个类的所有申明的字段，即包括public、private和proteced，
			但是不包括父类的申明字段。 

			同样类似的还有getConstructors()和getDeclaredConstructors()，
			getMethods()和getDeclaredMethods()
		 */
		System.out.println("getFields");
		for(int i=0;i<field1.length;i++){
			System.out.print(field1[i].getName()+" ");
		}
		System.out.println("");
		System.out.println("getDeclaredFields");
		for(int i=0;i<field2.length;i++){
			System.out.print(field2[i]+"-->");
		}
		
		Constructor[] con1 = dm.getConstructors();
		Constructor[] con2 = dm.getDeclaredConstructors();
		System.out.println("");
		System.out.println("getConstructors");
		for(int i=0;i<con1.length;i++){
			System.out.print(con1[i]+" ");
		}
		System.out.println("");
		System.out.println("getDeclaredConstructors");
		for(int i=0;i<con2.length;i++){
			System.out.print(con2[i]+" ");
		}
	}
}
