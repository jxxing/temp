package annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@MyAnnotation1(value="this is annotation1")
//@MyAnnotation1("this is annotation1")
public class AnnotationDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<?> cls = null;
		try {
				cls = Class.forName("annotation.AnnotationDemo");
			 
	        boolean flag = cls.isAnnotationPresent(MyAnnotation1.class);  
	        if(flag){  
	                System.out.println("判断类是annotation");  
	                MyAnnotation1 annotation1 = cls.getAnnotation(MyAnnotation1.class);  
	                System.out.println(annotation1.value());  
	        }  
	          
	        Method method = cls.getMethod("sayHello");  
	        flag = method.isAnnotationPresent(MyAnnotation2.class) ;  
	        if(flag){  
	                System.out.println("判断方法也是annotation");  
	                MyAnnotation2 annotation2 = method.getAnnotation(MyAnnotation2.class);  
	                System.out.println(annotation2.description()+"/t"+annotation2.isAnnotation());  
	        }  
	        Constructor con = cls.getConstructor();
			Object objnn = con.newInstance(); 
	        method.invoke(cls.newInstance());
	        method.invoke(objnn);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

    @MyAnnotation2(description="this is annotation2",isAnnotation=true)
    public void sayHello(){
            System.out.println("hello world!");
    }
}
