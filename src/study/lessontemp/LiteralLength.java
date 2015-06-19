package study.lessontemp;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class LiteralLength {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String fileName = "d:/Literal.java";
		StringBuilder prefix = new StringBuilder();
		
		//prefix.append("public class Literal {String s = \"");
		//prefix.append("public class Literal {static String s = \"");
		prefix.append("public class Literal {void getS(){ String s = \"");//成员变量，静态成员变量，局部变量 结果都是一样的 
		int low = 0;
		int high = 1000000;
		int mid = (low+high)/2;
		StringBuilder literal = new StringBuilder(high);
		int result;
		//String ch = "A";
		//String ch = "是";
		String ch = "ｇｏｎｇ";
		//String ch = "A";
		//取得系统编译器
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		//自定义错误输出流，取代System.err
		OutputStream err = new OutputStream(){
			@Override
			public void write(byte b[],int off,int len){
				
				
			}
			@Override
			public void write(int b){
				
			}
		};
		int max = 0;
		for(int i = 0; i < mid; i++){
			literal.append(ch);
		}
		while(low<=high){
			StringBuilder fileContent = new StringBuilder(literal.length() + prefix.length() * 2);
			fileContent.append(prefix);
			fileContent.append(literal);
			//fileContent.append("\";}");
			fileContent.append("\";}}");
			
			/**/
			FileWriter w = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(w);
			bw.write(fileContent.toString());
			bw.close();
			w.close();
			
			/*FileOutputStream out = new FileOutputStream(fileName);
			out.write(fileContent.toString().getBytes());
			out.flush();*/
		
			
			
			//编译生成的d:/Literal.java文件
			result = compiler.run(null, null, err, fileName);
			//代码点的数量
			int codePointCount = literal.codePointCount(0, literal.length());
			
			//如果是0，表明编译结果没有错误，可以尝试更长的字面常量
			if(result == 0){
				low = mid + 1;
				mid = (low + high)/2;
				max = codePointCount;
				for(int i = codePointCount; i < mid; i++){
					literal.append(ch);
				}
				System.out.println("长度：" + max + "编译成功，增加其长度至" + mid);
				
			}else{
				//如果是非0，表明编译结果有误，这说明字面常量过长。
				high = mid - 1;
				mid = (low + high)/2;
				System.out.println("长度" + codePointCount + "编译失败，减少其长度至" + mid);
				
				int start = ch.length() == 1 ? mid : mid * 2;
				literal.delete(start, literal.length());
			}
		}
		err.close();
		System.out.println("最大的字面常量长度是：" + max);
	}

}
