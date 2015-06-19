package study.lessontemp;
/**
 * 输出结果为
 * 0 0 0 0 0 
	0 1 1 1 1 
	0 1 2 2 2 
	0 1 2 3 3 
	0 1 2 3 4 
 * @author Administrator
 *
 */
public class Test {

	public static void main(String args[]){
		Test.print(5, 5);
	}
	
	public static void print(int imax ,int jmax){
		if(imax>jmax){
			System.out.println("退出");
			return;
		}
		int i = 0;
		int j = 0;
		int[][] a = new int[imax][jmax];
		/**
		 * 算一边
		 */
		for(;i<imax;i++){
			j=0;
			for(;j<jmax;j++){
				//System.out.print(i + " ");
				if(j>=i){
					a[i][j] = i;
				}else{
					a[i][j] = a[j][i];
				}
			}
		}
		/**
		 * 另一边
		 */
		/*i=0;
		for(;i<imax;i++){
			j=0;
			for(;j<jmax;j++){
				if(i>j){
					a[i][j] = a[j][i];
				}
			}
		}*/
		/**
		 * 输出
		 */
		i=0;
		for(;i<imax;i++){
			j=0;
			for(;j<jmax;j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		return;
	}
}
