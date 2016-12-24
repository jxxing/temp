package test;

import bo.Test;

public class ZhiLiTest {

	public static void main(String[] args) {
		int[][] a = new int[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				System.out.println(a[i][j]);
			}
		}
		a[0][3] = 1;
		a[3][3] = 0;
		a[1][1] = 0;
		
		int A,B,C,D = 0;
		Test test = new Test();
		test.d = 1;
	}
}
