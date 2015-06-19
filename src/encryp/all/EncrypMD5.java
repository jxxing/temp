package encryp.all;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypMD5 {
	
	public byte[] eccrypt(String info) throws NoSuchAlgorithmException{
		//根据MD5算法生成MessageDigest对象
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] srcBytes = info.getBytes();
		//使用srcBytes更新摘要
		md5.update(srcBytes);
		//完成哈希计算，得到result
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}
	
	
	public static void main(String args[]) throws NoSuchAlgorithmException{
		String msg = "郭XX-精品相声技术";
		EncrypMD5 md5 = new EncrypMD5();
		byte[] resultBytes = md5.eccrypt(msg);
		
		System.out.println("密文是：" + new String(resultBytes));
		System.out.println("密文是：" + toHexString(resultBytes));
		System.out.println("密文是：" + byteToString(resultBytes));
		System.out.println("明文是：" + msg);
	}
	
	public static String  byteToString(byte[] bt){
		int blen = bt.length;
		StringBuffer result = new StringBuffer(blen * 2);
		for(byte b:bt){
			int itemp = b;
			System.out.println(itemp);
			if(itemp<0){
				itemp += 256;
			}
			if(itemp<16){
				result.append("0");
			}
			result.append(Integer.toString(itemp, 16));
			System.out.println("byteToString整理后："+result.toString());
		}
		
		return result.toString();
	}
	
	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);//无符号右移
			sb.append(hexChar[b[i] & 0x0f]);
			System.out.println("toHexString整理后："+sb.toString());
		}
		return sb.toString();
	}
	public static final char[] hexChar = { 
	    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
