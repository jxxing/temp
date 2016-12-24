package thread;

class Hello implements Runnable{

	@Override
	public void run() {
		for(int i = 0;i<10;i++){
			System.out.println(Thread.currentThread() +" dd" + Thread.currentThread().getName());
		}
		
	}
	
}

public class TestThreadTwo {
	public static void main(String[] args) {
		Hello hello = new Hello();
		Thread thread1 = new Thread(hello);
		thread1.setDaemon(true);
		System.out.println(Thread.currentThread().getName() + "..dddd.");
		for(int i = 0;i < 20;i++){
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
			if(i>18){
				try {
					thread1.start();
					thread1.join();System.out.println(Thread.currentThread().getName() + "..dddff." + i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "..ddddddddddddddddd." + i);
		}System.out.println(Thread.currentThread() + Thread.currentThread().getName());
		System.out.println("hello over");
	}
}
