package test;

public class SplitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "aaa,bbb,ccc;ddd,eee;fff";
		String[] strs = new String[10];
		strs= 	str.split(",|;");
		for(int i = 0 ;i<strs.length; i++){
			System.out.println(strs[i]);
		}
	}

}
