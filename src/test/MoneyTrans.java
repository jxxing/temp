package test;


public class MoneyTrans {
	
	private static String[] ChinaDigit = {"零","一","二","三","四","五","六","七","八","九"};
	private static String[] UNIT = {"","","十","百","千"};
	private static String[] BIGUNIT = {"","万","亿","兆"};
	private static long MAX = 10000000000000000L - 1;
	private char[] digit;
	public String trans(long n) throws Exception{
		if(n > MAX){
			throw new Exception("数字过大，最多可处理到千兆位");
		}
		StringBuffer buff = new StringBuffer();
		boolean isPrePartZero = false;
		digit = String.valueOf(n).toCharArray();
		int length = digit.length;		
		int pos = (length - 1)/4;
		int headLength = (length - 1)%4 + 1;	
		//  xxxxxxxxxx -->  xx|xxxx|xxxx
		buff.append(partTrans(0,headLength) + BIGUNIT[pos--]);
		for (int i = headLength;i < length ; i = i + 4) {
			String part = partTrans(i , i + 4);
			if(part.length() == 0){
				isPrePartZero = true;
			}else{
				if(isPrePartZero && !part.startsWith(ChinaDigit[0])){
					buff.append(ChinaDigit[0]);
				}
				buff.append(part +  BIGUNIT[pos]) ;
				isPrePartZero = false;
			}
			pos--;
		}
		return buff.toString();
	}
	
	private  String partTrans(int start, int end) {
		StringBuffer buff = new StringBuffer();
		boolean isPreDigitZero = false;
		for (int i = start; i < end; i++) {
			int cur = digit[i] - '0';
			if(cur != 0 ){
				if(isPreDigitZero){
					buff.append(ChinaDigit[0]);
				}
				buff.append(ChinaDigit[cur] + UNIT[end - i]);
				isPreDigitZero = false;
			}
			else {			
				isPreDigitZero = true;
			}			
		}		
		return buff.toString();		
	}

	public static void main(String[] args) throws Exception {		
		MoneyTrans transtor = new MoneyTrans();    
		String money = transtor.trans(90010001101L);
		System.out.println(money);
	}

}



