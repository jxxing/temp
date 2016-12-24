package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MoveMatch {
	/*     _ _ _ _ _ 
	 *    |         |
	 *    |    5    |   
	 *    | 2     7 | 
	 *    |    4    |
	 *    |_ _ _ _ _|        火柴对应的位置
	 *    |         |
	 *    |         |   
	 *    | 1     6 | 
	 *    |    3    |
	 *    |_ _ _ _ _| 
	 *    
	 *    
	 *     _ _ _ _ _ 
	 *              |
	 *        5(1)  |   
	 *     2(0) 7(1)| 
	 *        4(1 ) |
	 *     _ _ _ _ _|        0    1     0   1   1   1   0   1
	 *    |          
	 *    |                标识位    1位     2位    3位    4位    5位   6位    7位  
	 *    | 1(1) 6(0)  
	 *    |    3(1)     
	 *    |_ _ _ _ _  
	 * 
	 */
		//所有数字和运算符用八位0和1表示,没8位中首位为0表示数字,首位是1表示操作符,
		//对于数字对应火柴位置如上图,如果火柴存在则对应的二进制位为1,否则为0.通过移动1和0来实现移动火柴
	String[] allNumbers = { 
			"01110111",// 0
			"00000011",// 1
			"01011101",// 2
			"00011111",// 3
			"00101011",// 4
			"00111110",// 5
			"01111110",// 6
			"00000111",// 7
			"01111111",// 8
			"00111111",// 9
	};
	String[] operator = { 
			"11000000",// +
			"10000000",// -
			"11100000",// =
	};
	String[] operatorChar = { "+",// +
			"-",// -
			"=",// =
	};
   
	//判断8为二进制字符串是否表示数字
	public boolean isDigit(String s) {
		
		if (s.startsWith("0")) {
			for (int i = 0; i < allNumbers.length; i++) {
				if (s.equals(allNumbers[i])) {
					return true;
				}
			}
		}
		return false;
	}
	//判断8为二进制字符串是否表示运算符号
	public boolean isOperator(String s) {
		return s.startsWith("1");
	}
    //8为二进制字符串转为数字
	public int translate2Digit(String s) {
		try {
			if (isDigit(s)) {
				for (int i = 0; i < allNumbers.length; i++) {
					if (s.equals(allNumbers[i])) {
						return i;

					}
				}
			} else {
				throw new NumberFormatException();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;

	}
	//8为二进制字符串转为字符
	public String translate2operator(String s) {
		try {
			if (isOperator(s)) {
				for (int i = 0; i < operator.length; i++) {
					if (s.equals(operator[i])) {
						return operatorChar[i];

					}
				}
			} else {
				throw new IsNotOperatorException();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";

	}
    //判断某个数字对应index位置上的火柴是否可以移动
	public boolean isMoveable(String s, int index) {
		if (!isDigit(s))
			return false;
		String temp = s.intern();
		char c = temp.charAt(index);
		if (c == '1') {
			temp = temp.substring(0, index) + "0" + temp.substring(index + 1);
			return isDigit(temp);
		}
		return false;
	}
   //判断某个数字的位置上受否可以接受一根火柴
	public boolean isAcceptable(String s, int index) {
		if (!isDigit(s))
			return false;
		String temp = s.intern();
		char c = temp.charAt(index);
		if (c == '0') {
			temp = temp.substring(0, index) + "1" + temp.substring(index + 1);
			return isDigit(temp);
		}
		return false;
	}
   //半段某个数字是否可以把indx位上的火柴移动到indexto上
	public boolean isSelfAcceptable(String s, int index, int indexto) {
		if (!isDigit(s))
			return false;
		String temp = s.intern();
		char c = temp.charAt(index);
		char c2 = temp.charAt(indexto);
		if (c == '1') {
			temp = temp.substring(0, index) + "0" + temp.substring(index + 1);
			if (c2 == '0') {
				temp = temp.substring(0, indexto) + "1"
						+ temp.substring(indexto + 1);
				return isDigit(temp);
			}
		}

		return false;
	}

	public void calculate(String string) {
		int result = -1;
		int length = string.length();
		int count = length / 8;
		//循环移动可以移动的火柴
		for (int i = 0; i < count; i++) {
			String source = string.substring(i * 8, (i + 1) * 8);
			if (isDigit(source)) {
				 //循环所有火柴位移动火柴
				for (int j = 1; j < 8; j++) {
					//判断j位上的火柴是否可以移动
					if (isMoveable(source, j)) {
						//循环多有可以接受火柴的数
						for (int k = 0; k < count; k++) {
							String target = string
									.substring(k * 8, (k + 1) * 8);
							//判断是否为数字
							if (isDigit(target)) {
								//接受火柴和移动火柴是否同时在同一个数字上
								if (k == i) {
									//循环所有的火柴位
									for (int l = 1; l < 8; l++) {
										//是否可以接受
										if (l != j
												&& isSelfAcceptable(target, j,
														l)) {
											String temp = string.intern();
											int indexMove = i * 8 + j;
											int indexto = k * 8 + l;
											//生成新的字符串
											temp = temp.substring(0, indexMove)
													+ "0"
													+ temp
															.substring(indexMove + 1);
											temp = temp.substring(0, indexto)
													+ "1"
													+ temp
															.substring(indexto + 1);
											if (print(temp) == 0)
												result = 0;

										}
									}
								} else {
									//循环所有的火柴位
									for (int l = 1; l < 8; l++) {
										//判断时候可以接受火柴
										if (isAcceptable(target, l)) {
											String temp = string.intern();
											int indexMove = i * 8 + j;
											int indexto = k * 8 + l;
											//生成新的结果
											temp = temp.substring(0, indexMove)
													+ "0"
													+ temp
															.substring(indexMove + 1);
											temp = temp.substring(0, indexto)
													+ "1"
													+ temp
															.substring(indexto + 1);
											if (print(temp) == 0)
												result = 0;
										}
									}

								}
							}

						}

					}
				}
			}

		}
		if (result == -1)
			System.out.println(-1);
	}

	class IsNotOperatorException extends Exception {

	}
 //打印结果 
	public int print(String s) {
		List list = new ArrayList();
		int length = s.length();
		int count = length / 8;
		long result = 0;
		long param = 0;
		String digits = "";
		String opreator = "";
		String tempOperator = "";
		//生成计算表达式
		for (int i = 0; i < count; i++) {
			String source = s.substring(i * 8, (i + 1) * 8);
			if (isDigit(source)) {
				digits += translate2Digit(source);
				//把操作数加入队列
				
				if(!opreator.equals("")){
				list.add(new String(opreator));
				}
				opreator = "";

			} else {
				
				if (digits.length() > 0 && digits.startsWith("0")) {
					return -1;
				}
				//计算结果
				if (tempOperator.equals("")) {
					result = Long.valueOf(digits).longValue();
				}
				if (tempOperator.endsWith("+")) {
					result += Long.valueOf(digits).longValue();
				}
				if (tempOperator.endsWith("-")) {
					result -= Long.valueOf(digits).longValue();
				}
				opreator = translate2operator(source);
				tempOperator = opreator;
				list.add(new String(digits));
				digits = "";
			}
		}
		list.add(digits);
		if (digits.length() > 0 && digits.startsWith("0")) {
			return -1;
		}

		if (result == Long.valueOf(digits).longValue()) {
			//输出表达式
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i));

			}
			System.out.println();
			return 0;
		}
		return -1;
	}

	public static void main(String[] args) throws Exception {
		MoveMatch test = new MoveMatch();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		System.out.println("Enter your value:");//9+5=9
		str = br.readLine();
		StringBuffer sb = new StringBuffer(800);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c)) {

				sb.append(test.allNumbers[Integer.valueOf(c + "").intValue()]);
			} else {
				if (c == '+') {
					sb.append(test.operator[0]);
				} else if (c == '-') {
					sb.append(test.operator[1]);
				} else if (c == '=') {
					sb.append(test.operator[2]);
				} else {
					throw new Exception();
				}
			}

		}
		test.calculate(sb.toString());
      
	}
}

