package study.lesson30;

@Deprecated
public class FSun extends FParent{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FSun f = new FSun("a","");
		//FParent t = new FParent("a","");
	}
	
	public FSun(){
		System.out.println("子类构造器");
	}
	public FSun(String str1,String str2){
		super(str1,str2);
		System.out.println("子类构造器2个参数");
	}
	public FSun(String str1,String str2,String str3){
		System.out.println("子类构造器3个参数");
		
	}

	{
		System.out.println("子类实例化开始");
	}
	
	public String cc = initC();
	
	public String dd ;
	
	{
		System.out.println("子类实例化");
		super.initA();initA();
		super.initStaticA();initStaticA();initStaticAA();
	}
	static{
		System.out.println("子类静态开始");
	}
	public static String staticCC = initStaticC();
	public static String staticDD;
	public static String staticEE = "staticEE";
	
	static{
		System.out.println("子类静态");
		staticDD = "子类静态D";
		initStaticA();initStaticAA();
	}
	public String initC(){
		System.out.println("子类实例化C");
		return "c";
	}

	public static String initStaticC(){
		System.out.println("子类静态C");
		return "子类静态C";
	}
	public static String initStaticD(){
		System.out.println("子类静态D");
		return "子类静态D";
	}
	//重写了，父类输出时是子类的内容
	@Override
	public String initA(){
		System.out.println("子类实例化A，这里重写了父类，父类实例化时也会调用这个子类的方法");
		return "1";
	}
	//@Override
	public static String initStaticA(){
		System.out.println("子类静态方法AA，隐藏了父类，不是重写");
		return "3";
	}
}
