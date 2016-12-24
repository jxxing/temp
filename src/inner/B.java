package inner;

public class B {
	public A getA(final int numa){  
		return new A(numa){  
			public int getNum(){  
				System.out.println(num);
				return numa;  
			}  
		};  
	} 
	
	public static void main(String[] args) {
		A a = new B().getA(11);
		System.out.println(a.getNum());
	}
}  
class A {  
	protected int num;  
	public A(int num){  
		this.num = num;  
	}  
	public A(){  

	}
	public int getNum() {
		return 0;
	}
}  