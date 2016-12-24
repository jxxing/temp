package thread;

public class TestTreadTress {


    public static void main(String[] args)
    {
        A a=new A();
        //因为printThreadInfo()方法抛出InterruptedException异常，所以这里必须使用try-catch块
        try {
            a.printThreadInfo();
            //a.wait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Thread t=Thread.currentThread();
        System.out.println("ThreadID:"+t.getId()+", ThreadName:"+t.getName());
    }
}

class A
{
    public synchronized void printThreadInfo() throws InterruptedException
    {
        Thread t=Thread.currentThread();
        System.out.println("ThreadID:"+t.getId()+", ThreadName:"+t.getName());
//        this.wait();//一直等待
        this.wait(1000);//等待1000ms
//        super.wait(1000);
    }
}

