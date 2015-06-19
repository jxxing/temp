package encryp.md5;

import java.security.MessageDigest;

public class MD5Utils {

	private String inStr;
	private MessageDigest md5;

	/**
	 * Constructs the MD5 object and sets the string whose MD5 is to be
	 * computed.
	 * 
	 * @param inStr
	 *            the <code>String</code> whose MD5 is to be computed
	 */
	public MD5Utils(String inStr) {
		this.inStr = inStr;
		try {
			this.md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Computes the MD5 fingerprint of a string.
	 * 
	 * @return the MD5 digest of the input <code>String</code>
	 */
	public String compute() {
		// convert input String to a char[]
		// convert that char[] to byte[]
		// get the md5 digest as byte[]
		// bit-wise AND that byte[] with 0xff
		// prepend "0" to the output StringBuffer to make sure that we don't end
		// up with
		// something like "e21ff" instead of "e201ff"

		char[] charArray = this.inStr.toCharArray();

		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		
		byte[] md5Bytes = null;
		
		
		md5Bytes = this.md5.digest(byteArray);//先String 转char 装byte 跟String 转byte 结果不同
		System.out.println("加密后的字符1:" + new String(md5Bytes));
		System.out.println("加密后的字符1:" + toHexString(md5Bytes));

		this.md5.update(this.inStr.getBytes());
		System.out.println("加密后的字符2:" + new String(this.md5.digest()));
		System.out.println("加密后的字符2:" + toHexString(this.md5.digest()));
		
		
		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	public static void main(String[] args) {
		MD5Utils md5 = new MD5Utils("郭XX-精品相声技术");
		String postString = md5.compute();
		System.out.println("加密后的字符:" + postString);
		if (postString.equals("900150983cd24fb0d6963f7d28e17f72")) {
			System.out.println("true");
		} else
			System.out.println("false");
	}
	
	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}
	public static final char[] hexChar = { 
	    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
