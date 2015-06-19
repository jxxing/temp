package temp;

public class class1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(IDNumber("33108119900504121"));
		System.out.println(IDIden("331081199005041222"));
	}
	
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
	 * @param aa
	 * @return
	 */
	public static String IDNumber(String aa){
		String result = null;
		int sum = 0 ;
		int wi = 0;
		for(int i = 2; i<=18 ;i++){
			int temp,tempint;
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
	
	
	/**
	 * 例子
	 *
	 */
	public void exm(){
//		 TODO Auto-generated method stub
		String aa = "33108119900504121";//2
		//String aa = "33012219891004222";//3
		String bb = "7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2";
		int[] a = new int[17];
		int[] b = new int[17];
		String[] ss = bb.split(",");
		int sum = 0;
		for(int i = 0;i <ss.length;i++){
			a[i]=Integer.parseInt(ss[i]);
			b[i]=Integer.parseInt(String.valueOf(aa.charAt(i)));
			sum = sum +a[i]*b[i];
			System.out.println(a[i]+"xx"+b[i]);
		}
		System.out.println(sum);
		System.out.println(sum%11);
//		switch(m){
//		case 0: c='1';break;
//		case 1: c='0';break;
//		case 2: c='X';break;
//		case 3: c='9';break;
//		case 4: c='8';break;
//		case 5: c='7';break;
//		case 6: c='6';break;
//		case 7: c='5';break;
//		case 8: c='4';break;
//		case 9: c='3';break;
//		case 10: c='2';break;
//		}
    	sum=0;
		int wi;
		for(int i = 2; i<=18 ;i++){
			int temp,tempint;
			wi =(((int)Math.pow(2, (i-1))) % 11);
//			String str = String.valueOf(a[18-i]);
//			tempint =Integer.parseInt(str);
			tempint = Integer.parseInt(String.valueOf(aa.charAt(18-i)));
			temp = tempint * wi;
		    sum +=temp;
			System.out.println(wi+"xx"+tempint);
		}
		

		System.out.println(sum);
		System.out.println(sum%11);
	}
}
