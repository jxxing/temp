package test;

public class ObjectArgsTest {

	public static void main(String[] args){
		byte[] a = {1,2,3};
		char[] b = {'1','2',3};//System.out.println 有特定的方法，如果用object作为参数传值，结果不一样
		System.out.print(a.getClass().getName()+" ");
		System.out.println(a);
		System.out.print(b.getClass().getName()+" ");
		System.out.println(b);
		test("fdf",1,"ddf",'d',true,a,b);
	}
	
	public static void test(Object...objects){
		for(int i = 0;i<objects.length;i++){
			Object obj = objects[i];
			System.out.print(obj.getClass().getName()+" ");
//			System.out.println(obj instanceof Character );
			System.out.println(obj);
		}
	}
}
