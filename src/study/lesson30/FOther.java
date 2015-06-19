package study.lesson30;

public class FOther implements ISon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@Deprecated
		/**
		 * 用 @Deprecated注解的程序元素，不鼓励程序员使用这样的元素，通常是因为它很危险或存在更好的选择。在使用不被赞成的程序元素或在不被赞成的代码中执行重写时，编译器会发出警告
		 */
		@SuppressWarnings("all")
		/**
		 * deprecation	使用了不赞成使用的类或方法时的警告
			unchecked	执行了未检查的转换时的警告，例如当使用集合时没有用泛型 (Generics) 来指定集合保存的类型。
			fallthrough	当 Switch 程序块直接通往下一种情况而没有 Break 时的警告。
			path	在类路径、源文件路径等中有不存在的路径时的警告。
			serial	当在可序列化的类上缺少 serialVersionUID 定义时的警告。
			finally	任何 finally 子句不能正常完成时的警告。
			all	关于以上所有情况的警告。
		 * 
		 */
//		FOther other = new FOther();
		String aa = new FSun("","").initA();
//		public static final int y = 2005; //如果一个static field是编译时常量（compile-time constant），则对它的引用不会引起定义它的类的初始化
	}
	
	public String w = initW();
	public static int staticII ;
	public static String staticWW ;
	
	public String s;
	public int i;
	{
		System.out.println("实例化模块");
		System.out.println(s + "实例化" + i);
		System.out.println(w);
	}
	
	static{
		System.out.println(staticWW + "静态" + staticII);
	}
	@Deprecated
	public String initW(){
		System.out.println("实例化W11");
		return "实例化W";
	}

}
