package test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class IntegerTest {

	public static void main(String[] args){
		int i = 16;
		i |= (i >>  1);
        i |= (i >>  2);
		System.out.println(i);
        i |= (i >>  4);
		System.out.println(i);
        i |= (i >>  8);
		System.out.println(i);
        i |= (i >> 16);
		System.out.println(i);
        System.out.println(i - (i >>> 1));
//		System.out.println(Integer.highestOneBit(16)); 

		Double dob ;
		BigDecimal big;
		Calendar ca = Calendar.getInstance();
		Date date = new Date();
		int[] aaaa  = new int[2];
		aaaa[0] = 1;
		aaaa[1] = 2;
		int[] aa  = new int[]{1,2};
		int[] aaa = {1,2};

		System.out.println("120aaaaa".compareTo("a12")); 
		System.out.println("120"); 
		System.out.println((byte)Integer.parseInt("8f", 16));
		System.out.println(new Byte((byte)1));
		System.out.println((byte)10000);//?
		System.out.println((byte)1000);//? 1000-1024
		byte bbb = -128;
		System.out.println(Integer.toBinaryString(bbb));
		int fnum = -44;
		System.out.println(fnum & 0xf0);
		System.out.println(Integer.valueOf(fnum));
		System.out.println(Integer.parseInt("11"));
		
		i =11;
		
		String j = "111";
		
		i = Integer.valueOf(j);
		System.out.println(i);
		i = Integer.parseInt(j);
		System.out.println(i);
		
		int a = 12345;
		int b = 54321;
		int c = a+b;
		System.out.println(c);
		System.out.println(12345+5432l);//1
		System.out.println((byte) -1);
		System.out.println((char)(byte) -1);
		System.out.println((int)(char)(byte) -1);//2
		
		char x = 'X';
		int ii = 0;
		System.out.println(true ? x : 0); 
		System.out.println(false ? ii : x); //3
		
		 String letters = "ABC";
        char[] numbers = {'1', '2', '3'};
        System.out.println(numbers);
        System.out.println(letters + " easy as " + numbers.toString()+ " " +String.valueOf(numbers));//4
        
        Object numbers2 = new char[] { '1', '2', '3' };
        System.out.print(letters + " easy as ");
        System.out.println(numbers2);

        
        final String pig = "length: 10";
        final String dog = "length: " + "10";
        System.out. println("Animals are equal: "
                            + (pig == dog));//4

        System.out.println("a\u0022.length()+\u0022b");
        // \u0022 是双引号的Unicode转义字符
        System.out.println("a\u0022.length()+\u0022b".length());//5

        System.out.print("Hell");
        System.out.println("o world");//6
        //      Note: \u000A //如果没有再次注释及报错
        char cs = 0x000A;
        System.out.println(cs);//7


	}
}
