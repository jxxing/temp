package test;

public class ObjetcTest {

	public static void main(String...args){
		final A a = new A();
		a.a = "a";
		
		A b = a;
		b.a = "b";
		System.out.println(a.a);
	}
}

class A {
	String a = "a";
}
