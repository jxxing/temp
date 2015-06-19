package study.lesson30.cto51;

public class SubClass extends SuperClass implements Interface {
	static {   
        System.out.println("SubClass static");   
    }   
   
    private SuperClass su = new SuperClass("initialization variable");   
   
    SubClass() {   
        super("super");   
        new SuperClass("new SuperClass");   
    }   
}
