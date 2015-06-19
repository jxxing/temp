package encryp.allF;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class FileHashUtil {

	public static final char[] hexChar = { 
		    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	public static final String[] hashTypes = new String[] { "MD2", "MD5", "SHA1", "SHA-256", "SHA-384", "SHA-512" };
	
	public void MD5File(String fileName) throws Exception{
		//String fileName = args[0];
		System.out.println("需要获取hash的文件为：　" + fileName);
		java.util.List<MessageDigest> mds = new java.util.ArrayList<MessageDigest>();
		for (String hashType : hashTypes) {
			MessageDigest md = MessageDigest.getInstance(hashType);
			mds.add(md);
		}
		for (MessageDigest md : mds) {
			System.out.println(md.getAlgorithm() + " == " + toHexString(md.digest()));
		}
		//byte[] tempbyte = "".getBytes();
		for (MessageDigest md : mds) {
			md.update("".getBytes());
			System.out.println(md.getAlgorithm() + " == " + toHexString(md.digest()));
		}

		System.out.println("开始文件加密" );
		InputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			int numRead = 0;
			while ((numRead = fis.read(buffer)) > 0) {
				for (MessageDigest md : mds) {
					md.update(buffer, 0, numRead);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
//		for (MessageDigest md : mds) {
//			System.out.println(md.getAlgorithm() + " == " + toHexString(md.digest()));
//		}
	}
	

	public static void main(String[] args) throws Exception {
		String[] fileName = new String[] {"D:/log.log","D:/复件 log.log"};
		FileHashUtil files  = new FileHashUtil();
		for(int i=0;i<fileName.length;i++){
			files.MD5File(fileName[i]);
		} 
		
		
	}

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}

}
