package study.lesson30;

public class FParent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FParent t = new FParent();
		FParent tt = new FParent("aaa","bbb");
	}

	public FParent(){
		System.out.println("父类构造器");
	}
	
	public FParent(String str1,String str2){
		System.out.println("父类构造器2个参数");
		aa = str1;
		bb = str2;
	}
	
	public String aa = initA();
	public String bb;
	{
		System.out.println("父类实例化B");
		bb = "2";
	}
	
	public static String staticAA = initStaticA();
	
	public String initA(){
		System.out.println("父类实例化A");
		return "1";
	}
	
	public static String staticBB ;
	static{
		System.out.println("父类静态方法");
	}
	public static String initStaticA(){
		System.out.println("父类静态方法A");
		return "3";
	}
	
	public static String initStaticAA(){
		System.out.println("父类静态方法AA，要被子类继承了");
		return "3";
	}
}
