package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import bo.GpExpResume;

public class ReflectTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
//	public static void main(String[] args) throws Exception {
//		GpExpResume gpExpResume = new GpExpResume();
//		//System.out.println(getProperty(gpExpResume,"aaaa"));
//		//System.out.println(getPropertyNew("bo.GpExpResume","aaaa"));
////		reflectObject(new Test());
//		String[] bb = new String[]{"1"};
//		System.out.println(bb[0]);
//	}
	
	public static void main(String...args){
//		Integer a = 1;
//		List<Integer> aa = new ArrayList<Integer>();
//		aa.add(1);
//		aa.add("a");
		
//		callMe1(new String[0]);
////		callMe1();
//		callMe2();
//		callMe2(new String[2]);
		GpExpResume test = new GpExpResume();
		getProperty(test,"aaaa");
	}
	
	public static void callMe1(String[] args) {  
		  System.out.println(args.getClass() == String[].class);  
		  for(String s : args) {  
		    System.out.println(s);  
		  }  
		}  
		    
		public static void callMe2(String... args) {  
		  System.out.println(args.getClass() == String[].class);  
		  for(String s : args) {  
		    System.out.println(s);  
		  }  
		}

	public static Object getProperty(Object owner,String fieldName){
		Class ownerClass = owner.getClass();

		Field field = null;
		Field[] fields = ownerClass.getFields();
		for(Field fieldsss : fields){
			System.out.println("sss "+fieldsss.getName());
		}
		
		Field[] fieldss = ownerClass.getDeclaredFields();
		for(Field fieldsss : fieldss){
			System.out.println("ssd "+fieldsss.getName());
		}
		try {
			field = ownerClass.getField(fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		Class[] arg = new Class[1];
		String ccc = "ccc";
		arg[0] = ccc.getClass();
		try {
			Method method = ownerClass.getMethod("setAaaa", new Class[]{String.class});
			method.invoke(owner,"aaaaaaa");
			
			Method methodg = ownerClass.getMethod("getAaaa", null);
			Object obj = methodg.invoke(owner);
			System.out.println(obj);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		Object property = field.getName();
		return property;
	}
	
	
	public static Object getPropertyNew(String owner,String fieldName) throws Exception{
		Class ownerClass = null;
		try {
			ownerClass = Class.forName(owner);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Field field = null;
		Field[] fields = ownerClass.getDeclaredFields();
		for(Field fieldsss : fields){
//			System.out.println(fieldsss);
		}
		System.out.println(fieldName);
		field = ownerClass.getField(fieldName);
		System.out.println(field);
	
		/*System.out.println(field);
		System.out.println(field.getClass()+"1");
		System.out.println(field.toString());
		System.out.println(field.getDeclaringClass());
		System.out.println(field.getDeclaringClass().getClass());
		System.out.println(field.getDeclaringClass().getName());
		System.out.println(field.getClass().getClass());
		System.out.println(field.getClass().getName());
		System.out.println(field.getType().getName());*/
		

		/**
		 * newInstance: 弱类型。低效率。只能调用无参构造。
		 * new: 强类型。相对高效。能调用任何public构造
		 * Class.newInstance() 只能够调用无参的构造函数，即默认的构造函数；
		 * Constructor.newInstance() 可以根据传入的参数，调用任意构造构造函数。
		 * Class.newInstance() 抛出所有由被调用构造函数抛出的异常。
		 * Class.newInstance() 要求被调用的构造函数是可见的，也即必须是public类型的; 
		 * Constructor.newInstance() 在特定的情况下，可以调用私有的构造函数
		 */
		Class[] arg = new Class[1];
		String ccc = "cccc";
		arg[0] = ccc.getClass();
		Constructor con = null;
		con = ownerClass.getConstructor();
		System.out.println(con);
		con = ownerClass.getDeclaredConstructor();
		System.out.println(con.getDeclaringClass());
		Object objnn = con.newInstance(); 
		Method method = ownerClass.getMethod("setAaaa", new Class[]{String.class});
//			Method method = ownerClass.getMethod("setAaaa", String.class);
		method.invoke(objnn, ccc);
		
//			Method methodg = ownerClass.getMethod("getAaaa", new Class[]{});
//			Method methodg = ownerClass.getMethod("getAaaa");
		Method methodg = ownerClass.getMethod("getAaaa",null);
//			Method methodg = ownerClass.getMethod("getAaaa", new Class[0]);
		
//			Object obj = methodg.invoke(objnn,new Object[]{});
//			Object obj = methodg.invoke(objnn,null);
		Object obj = methodg.invoke(objnn);
		System.out.println(obj);
	
		
		Object property = field.getName();
		return property;
	}
	
	public static void reflectObject(Object obj) throws Exception{
		Class objClass = obj.getClass();
		System.out.println("objClass:"+objClass);
		System.out.println("objClass的name:"+objClass.getName());
		Class superClass = objClass.getSuperclass();
		System.out.println("objClass的superClass的name:"+superClass.getName());
		
		//构造函数相关
//		constructorObject(objClass);
		
		//参数相关
//		fieldObject(objClass);

		//一般函数相关
		methodObject(objClass);
	}
	
	public static void constructorObject(Class objClass){
		Constructor[] constructors = objClass.getConstructors();//取public构造函数,注意构造函数不会继承，所有不会有父类的构造函数
//		Constructor[] constructors = objClass.getDeclaredConstructors();
		//取全部构造函数public=1， =0，protected=4,private=2,public static=9,public static final =25,public final=17,private static final=26 
		System.out.println("按定义先后列出");
		for(Constructor constructor : constructors){//按定义先后列出
			System.out.println(constructor);
			System.out.print(constructor.getName());
			System.out.print(" ");
			System.out.print(constructor.getModifiers());
			System.out.print(" ");
			System.out.println(Modifier.toString(constructor.getModifiers()));
		}
		
		System.out.println("取无参构造函数");
		Constructor con;
		try {
			con = objClass.getConstructor();//取无参构造函数
			System.out.println(con);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void fieldObject(Class objClass){
		Field[] fields = objClass.getDeclaredFields();//同构造函数
//		Field[] fields = objClass.getFields();//取public构造函数，注意有父类的参数
		for(Field field : fields){
//			System.out.println(field);
//			System.out.print(field.getName());
//			System.out.print(" ");
//			System.out.print(field.getModifiers());
//			System.out.print(" ");
//			System.out.println(Modifier.toString(field.getModifiers()));
		}
		
		System.out.println("成员变量类型");
		for(Field field : fields){
			Class type = field.getType();
			if (type.isArray()) { //如果是数组类型则需要特别处理
				System.out.println("array:"+type.getComponentType()+" name:"+type.getComponentType().getName());
			}else{
				System.out.println("other:"+type);
			}
		}
	}
	
	public static void methodObject(Class objClass){
		Method[] methods = objClass.getDeclaredMethods();//同参数
//		Method[] methods = objClass.getMethods();//取public构造函数，注意有父类的参数

		System.out.println("函数名称");
		for(Method method : methods){
//			System.out.println(method);
//			System.out.print(method.getName());
//			System.out.print(" ");
//			System.out.print(method.getModifiers());
//			System.out.print(" ");
//			System.out.println(Modifier.toString(method.getModifiers()));
		}

		System.out.println("函数形参");
		for(Method method : methods){
			System.out.println(method);
			Class[] paramTypes = method.getParameterTypes(); //获取构造方法中的参数
			for(Class paramType : paramTypes){
				System.out.println(paramType);
				System.out.println(paramType.getName());
			}

		}

		System.out.println("返回类型");
		for(Method method : methods){
			Class rusultType = method.getReturnType();
			System.out.println(rusultType);
			System.out.println(rusultType.getName());
		}
	}
}
