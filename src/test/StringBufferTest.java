package test;

public class StringBufferTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
        StringBuffer sb1 = new  StringBuffer("wwwwww");
        StringBuffer sb2 = new  StringBuffer("wwwwww");
        StringBuffer sb3 = new  StringBuffer("wwwwww");
        long s1 = System.currentTimeMillis();
        for (int i = 0; i < 1500000; i++) {
               sb1.setLength(0);
      }
        long s11 = System.currentTimeMillis();
        System.out.println("StringBuffer-setLength:" + (s11 - s1));
  
        s1 = System.currentTimeMillis();
        for (int i = 0; i < 1500000; i++) {
         sb2.delete(0, sb2.length());
        }
        s11 = System.currentTimeMillis();
        System.out.println("StringBuffer--delete:" + (s11 - s1));
        s1 = System.currentTimeMillis();
        for (int i = 0; i < 1500000; i++) {
         sb3 = new StringBuffer("");
        }
        s11 = System.currentTimeMillis();
        System.out.println("StringBuffer--new StringBuffer:" + (s11 - s1));
        
        
        String a = "abc";
        
        String b = a;
        
        a = "xyz";
        
        System.out.println(a);
        System.out.println(b);
        
       }


}
