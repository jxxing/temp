package com.jxx.util;

public class CommomUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(IDNumber("33108119900504121"));
		System.out.println(IDIden("331081199005041212"));
	}

	/**
	 * 判断身份证第十八位是不是有效位
	 * @param aa 身份证字符串
	 * @return ture or flase
	 */
	public static boolean IDIden(String aa){
		boolean result = false;
		if(aa.length()!=18){
			return false;
		}
		String tempa = aa.substring(0,17);
		String tempb = aa.substring(17);
		if(tempb.equals(IDNumber(tempa))){
			return true;
		}
		
		return result;
	}
	/**
	 * 根据前17位，算出第十八位
	 * 7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2
	 * @param aa 身份证前十七位
	 * @return
	 */
	public static String IDNumber(String aa){
		String result = null;
		int sum = 0 ;
		for(int i = 2; i<=18 ;i++){
			int temp,tempint,wi;
			wi =(((int)Math.pow(2, (i-1))) % 11);
//			String str = String.valueOf(a[18-i]);
//			tempint =Integer.parseInt(str);
			tempint = Integer.parseInt(String.valueOf(aa.charAt(18-i)));
			temp = tempint * wi;
		    sum +=temp;
			//System.out.println(wi+"xx"+tempint);
		}
		int m = sum%11;
		char c = 0 ;
		switch(m){
		case 0: c='1';break;
		case 1: c='0';break;
		case 2: c='X';break;
		case 3: c='9';break;
		case 4: c='8';break;
		case 5: c='7';break;
		case 6: c='6';break;
		case 7: c='5';break;
		case 8: c='4';break;
		case 9: c='3';break;
		case 10: c='2';break;
		}
		result = String.valueOf(c);
		//System.out.println(sum);
		return result;
	}
}
