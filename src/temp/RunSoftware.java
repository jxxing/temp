package temp;

import java.awt.Desktop;
import java.io.IOException;

public class RunSoftware {
	public static void main(String[] args) {
		try {
			///c  就是 要在 cmd 中运行的命令：/C      执行字符串指定的命令然后终断。。。。。。
			//Runtime.getRuntime().exec("cmd.exe /c start iexplore www.sina.com.cn");
//			Runtime.getRuntime().exec("cmd.exe /c start %SystemRoot%/system32/mshearts.exe");

//			Runtime.getRuntime().exec("D:/Program Files/Kingsoft/Klive/kuaipan.exe");
			Runtime.getRuntime().exec("cmd.exe /c start D:/Program\" \"Files/Kingsoft/Klive/kuaipan.exe");
			//Runtime.getRuntime().exec("sh/ your command")//linux
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
