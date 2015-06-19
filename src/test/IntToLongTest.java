package test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;

public class IntToLongTest {

	public static void main(String args[]){
		long l1 = 24 * 60 * 60 * 1000 * 1000;//溢出
		long l2 = 24l * 60 * 60 * 1000 * 1000;
		long l3 = 256 * 256 * 256 * 128 - 1;//2^31-1 = 2147483647
		System.out.println(l3);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(Integer.toBinaryString((int)l1));
		System.out.println("00000000" + Integer.toBinaryString((int)l1));
		System.out.println(Long.toBinaryString(l2));
		
		System.out.println(Integer.toBinaryString(0xcafebabe));
		System.out.println(0xcafebabe);//-889275714 int 类型，第一位为符号位，为负
		System.out.println(0x7afebabe);//2063514302
		System.out.println(0x100000000L);//4294967296
		System.out.println(Long.toHexString(4294967296L - 889275714 ));//Long 类型的0xcafebabe 为正。值为 3405691582
		System.out.println(4294967296L - 889275714);
		System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));
		
	
		System.out.println((byte) -1);System.out.println((byte) 0xffffff);
		System.out.println((char)(byte) -1);System.out.println((char)(byte) 0xffffff);
		System.out.println((int)(char)(byte) -1);//是65535 ？？
		System.out.println((int)(char)(byte) 0xffffff);
		System.out.println(Short.MAX_VALUE);
		char c = '2';
		int i = c & 0xffff;
		int i2 = (short)(c & 0xffff);
		System.out.println(i);
		System.out.println(i2);
		
		
		int x = 1984;//0x7c0
		int y = 2001;//0x7d1
		int tempx;
		int tempy;
		System.out.println(tempx = x ^ y);//17
		System.out.println(tempy = tempx ^ y);//1984
		//System.out.println(tempx = tempx ^ tempy);//2001
		System.out.println(tempx = x ^ tempy);//0
		x ^= y ^= x ^= y;//?
		System.out.println("X = " + x + ",Y = " + y);
		
		
		char xx = 'X';
		int ii = 0;
		System.out.println(true ? xx : 0); //X 如果是 一个int类型的常量，另一个可以是原先的类型
		System.out.println(false ? ii : xx);//88  不是常量，则为两者的提升
		
		System.out.print("H" + "a");//Ha
		System.out.println('H' + 'a');//169
		System.out.println('H');//H
		System.out.println(String.valueOf('H' + 'a'));//169
		System.out.println(String.valueOf(169));
		System.out.println(String.valueOf('H') + String.valueOf('a'));
		System.out.println('H');//H
		System.out.println('a');//a
		System.out.println("2 + 2 = " + 2 + 2);//aa
		System.out.printf("%c%c",'H','a');//Ha
		System.out.println();
		
		String letters = "ABC";
	    char[] numbers = {'1', '2', '3'};
	    System.out.println(numbers);//123
	    System.out.println(letters + " easy as " + numbers);//ABC easy as [C@1270b73  numbers 相当于 numbers.toString()
	    System.out.println(letters + " easy as " + numbers.toString() + " " +String.valueOf(numbers));//ABC easy as [C@1270b73 123
	
	    Object numbers2 = new char[] { '1', '2', '3' };
        System.out.print(letters + " easy as ");
        System.out.println(numbers2);
        System.out.println(String.valueOf(numbers2));
        
        final String pig = "length: 10";
        final String dog = "length: " + pig.length();
        System.out. println("Animals are equal: " + (pig == dog));//Animals are equal: true
        System.out. println("Animals are equal: " + pig == dog);
        
        
        System.out.println("a\u0022.length()+\u0022b");
        // \u0022 是双引号的Unicode转义字符
        System.out.println("a\u0022.length()+\u0022b".length());//5
        
        System.out.print("Hell");
        System.out.println("o world");//6
        //      Note: \u000A //如果没有再次注释及报错
        char cs = 0x000A;
        System.out.println("a"+cs+"b");//7
        
        System.out.println("byte转换开始");//7
        byte bytes[] = new byte[256];
        for(int bi = 0;bi < 256;bi++){
        	bytes[bi] = (byte)bi;
    		System.out.print(bytes[bi]+" --> ");
    		System.out.print(Integer.toString(bytes[bi], 16)+" ");
        }
        //String str = new String(bytes);
        String str = null;
		try {
			str = new String(bytes,"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(str);
        for(int si = 0,sn = str.length();si < sn;si++){
        	if(si % 10 == 0){
        		System.out.print((int)str.charAt(si)+" ");
        		System.out.println(str.charAt(si));
        	}else{
        		System.out.print((int)str.charAt(si)+" ");
        		System.out.print(str.charAt(si)+" ");
        	}
        }
        System.out.println(java.nio.charset.Charset.defaultCharset());
        
        System.out.println(IntToLongTest.class.getName());
        System.out.println(IntToLongTest.class.getName().replaceAll(".", "/") + ".class");//    //////////////////.class
        System.out.println("dfdf.fddf.dfdf.ddd".replaceAll("\\.", "/") + ".class");//dfdf/fddf/dfdf/ddd.class
        //System.out.println(IntToLongTest.class.getName().replaceAll("\\.", File.separator) + ".class");
        System.out.println("dfdf.fddf.dfdf.ddd".replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + ".class");//dfdf.fddf.dfdf.ddd.class
        System.out.println(File.separator);// \
        System.out.println("dfdf.fddf.dfdf.ddd".replace(".", File.separator) + ".class");//dfdf.fddf.dfdf.ddd.class
        System.out.println("dfdf.fddf.dfdf.ddd".replace('.', File.separatorChar) + ".class");//dfdf.fddf.dfdf.ddd.class
        System.out.println("dfdf.fddf.dfdf.ddd".replace(".", "/") + ".class");//dfdf.fddf.dfdf.ddd.class
        
	}
}
