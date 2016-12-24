package thread;

public class TestThreadFive implements Runnable {
	public TestThreadFive() {
		 
    }
 
    public TestThreadFive(String name) {
        this.name = name;
    }
 
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行     " + i);
        }
    }
 
    public static void main(String[] args) {
    	TestThreadFive h1=new TestThreadFive("线程A");
        Thread demo= new Thread(h1);
        TestThreadFive h2=new TestThreadFive("线程Ｂ");
        Thread demo1=new Thread(h2);
        demo.start();
        demo1.start();
//        h1.run();
//        h2.run();
    }
 
    private String name;
}
