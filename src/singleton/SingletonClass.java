package singleton;


public class SingletonClass {

	private volatile static SingletonClass instance = null;

	public static SingletonClass getInstance() {
//		if (instance == null) {
//			synchronized (SingletonClass.class) {
//				if (instance == null) {
//					instance = new SingletonClass();
//				}
//			}
//			
//		}
		instance = new SingletonClass(); 
		return instance;
	}

	private SingletonClass() {
		System.out.println("private");
	}
	
	public static void hello(){
		System.out.println("hello");
	}
	
	public static void main(String[] args) {
		SingletonClass.hello();
	}
}
