package singleton;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SingletonClass2 {
	Map map = new HashMap();
	SingletonClassInstance singletonClassInstance = new SingletonClassInstance();
	private static class SingletonClassInstance { 
	    private static final SingletonClass2 instance = new SingletonClass2(); 
	    
	    private String str = "";
	    
	    private String str(){
	    	return "";
	    }
	    
	    private static String str2(){
	    	return "";
	    }
	  } 

	  public static SingletonClass2 getInstance() { 
	    return SingletonClassInstance.instance; 
	  } 

	  private SingletonClass2() { 

	  } 
	  
	  private String outStr;
	  
	  private static String outSStr;
	  
	  private String outStr(){
		  
		  return "";
	  }
	  
	  private static void outStr33(){
		  
	  }
			 
	  
	  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		  
	      Class<?> cache = Integer.class.getDeclaredClasses()[0]; //1
	      Field myCache = cache.getDeclaredField("cache"); //2
	      myCache.setAccessible(true);//3
	 
	      Integer[] newCache = (Integer[]) myCache.get(cache); //4
	      System.out.println(newCache[128] + " " + newCache[132] + " " + newCache[133] ); //
	      newCache[132] = newCache[133]; //5
	 
	      int a = 2;
	      int b = a + a;
	      System.out.printf("%d + %d = %d", a, a, b); //
	    }
}
