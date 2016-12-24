package threads;

import java.util.ArrayList;
import java.util.List;

//测试线程池
public class TestThreadPool {
	public static void main(String[] args) {
		// 创建3个线程的线程池
		ThreadPool t = ThreadPool.getThreadPool(50);
		List<Runnable> tastList = new ArrayList<Runnable>();
		for(int i = 0;i < 1000;i++){
			Task task = new Task();
			tastList.add(task);
		}
//		t.execute(new Runnable[] { new Task(), new Task(), new Task(), new Task(), new Task(), new Task()});
		t.execute(tastList);
		System.out.println(t);

		t.destroy();// 所有线程都执行完成才destory
		System.out.println("getFinishedTasknumber  " + t.getFinishedTasknumber());
		System.out.println(t);
	}

	// 任务类
	static class Task implements Runnable {
		private static volatile int i = 1;

		@Override
		public void run() {// 执行任务
			System.out.println("任务 " + (i++) + " 完成");
		}
	}
}
