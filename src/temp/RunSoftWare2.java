package temp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;import java.util.HashMap;

public class RunSoftWare2 { 
	public static void main (String[] args) {  
		excuteCommand("ipconfig");  
		excuteCommand("ping www.baidu.com"); 
	}        
	public static void  excuteCommand(String command)    {            
		Runtime r = Runtime.getRuntime();        Process p;            
		try {                
			//p = r.exec("cmd.exe /c start "+command);   
			p = r.exec(command);         
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream())); 
			String inline;               
			while ((inline = br.readLine()) != null) {                   
				System.out.println(inline);                                    
			}                br.close();           
		} catch (IOException e) {                // TODO
		}
	}
}
																																																																																																																																																																																																														// Auto-generated
																																																																																																																																																																																																														// catch
																																																																																																																																																																																																														// block
																																																																																																																																																																																																														// e.printStackTrace();
																																																																																																																																																																																																														// } }}
