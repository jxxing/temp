package test;

public class StringTest {

	public static void main(String[] ar) {
		System.out.println(Math.random());
		String aaaa = "0.444";
		System.out.println(aaaa.substring(aaaa.length()-4));

		String s = null;
		//System.out.println(s.isEmpty());

		String s1 = "{~";
		String s2 = "|_";

		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s1.equals(s2));

		String s3 = "的";
		String s4 = "";
		System.out.println(s3.length());// 0
		System.out.println(s4.length());// 0

		System.out.println(s3.hashCode());// 0

		System.out.println(s4.hashCode());// 0

	}
}
