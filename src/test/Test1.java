package test;

public class Test1 {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    private static final int RUNNING    = -1 << COUNT_BITS;

	public static void main(String[] args) {
	    
	    
		System.out.println(COUNT_BITS);
		System.out.println(RUNNING);//-536870912
		System.out.println(RUNNING | 0);//-536870912
		System.out.println(CAPACITY);//536870911
		System.out.println(~CAPACITY);//-536870912
		System.out.println((RUNNING) & (~CAPACITY));//-536870912
		System.out.println((RUNNING) & (CAPACITY));//0
	}
}
