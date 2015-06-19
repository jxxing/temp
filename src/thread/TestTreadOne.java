package thread;

class Demo extends Object{
    public void finalize(){
        System.out.println("demo ok");
    }
}

public class TestTreadOne {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Demo();
        new Demo();
        new Demo();
        System.gc();
        System.out.println("Hello World!"); 
        new Demo();
        new Demo();
        System.out.println("Hello World2!"); 
        System.gc();
    }

}
