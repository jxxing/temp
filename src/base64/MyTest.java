package base64;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MyTest {
    static BASE64Encoder encoder = new BASE64Encoder();  
    static BASE64Decoder decoder = new BASE64Decoder();  

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String strImg = GetImageStr();  
//        GenerateImage(strImg);  
		byte[] data = null;
			data = "projNo=E20130726-002&itemNo=2&projName=工程名称E20130726-002&jsdw=国家宏观".getBytes();

        String str =  encoder.encode(data);
        System.out.println(str); 
        byte[] b= null;
		try {
			b = decoder.decodeBuffer("cHJvak5vJTNERTIwMTMwNzI2LTAwMiUyNml0ZW1ObyUzRDIlMjZwcm9qTmFtZSUzRCV1NURFNSV1N0EwQiV1NTQwRCV1NzlGMEUyMDEzMDcyNi0wMDIlMjZqc2R3JTNEJXU1NkZEJXU1QkI2JXU1QjhGJXU4OUMyJTIw/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        for(int i=0;i<b.length;++i)  
        {  
            if(b[i]<0)  
            {//调整异常数据   
                b[i]+=256;  
            }  
        } 
        try {
			System.out.println(new String(decoder.decodeBuffer(str)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println(new String(b));
	}
	
	public static String GetImageStr()  
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理   
        String imgFile = "d:\\aa.jpg";//待处理的图片   
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组   
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码   
        String str =  encoder.encode(data);
        System.out.println(str+"\n");
        System.out.println("-----------------------------------\n");
        return str;//返回Base64编码过的字节数组字符串   
    }  
    public static boolean GenerateImage(String imgStr)  
    {//对字节数组字符串进行Base64解码并生成图片   
        if (imgStr == null) //图像数据为空   
            return false;  
        try   
        {  
            //Base64解码   
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据   
                    b[i]+=256;  
                }  
            }  

            System.out.println(new String(b));
            //生成jpeg图片   
            String imgFilePath = "d:\\222.jpg";//新生成的图片   
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  


}
