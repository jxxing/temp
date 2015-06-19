package annotation;

import java.lang.reflect.Method;

import org.junit.Test;

public class TestAnnotation  {
	@Test  
    public void test() throws ClassNotFoundException, SecurityException, NoSuchMethodException{  
            Class<?> cls = Class.forName("annotation.AnnotationDemo");  
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
    }  

}
