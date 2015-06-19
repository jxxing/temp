package study.lessontemp;
/**
 * 
 * @author Administrator
 *
 */
public class NewTempClass {
	
	public NewTempClass(int a){
		
	}
	public static void main(String args[]){
		int v = 0;
		int i = 0;
		NewTempClass[] temps = getArrays();
	
		try{
		while(true){
			temps[i++] = new NewTempClass(v = 1);
			if(i>15643262){//15643263  最大
				System.out.println(i);System.out.println(" "+v);
			}//加上if后  v居然为1了，估计是System.out.println占用内容出错了
			v = 0;
		}
		}catch(OutOfMemoryError e){
			System.out.println("发生错误");
			System.out.println("d");
		}
		//System.out.println(temps[0]);
		System.out.println(v);
	}
	
	public static NewTempClass[] getArrays(){
		NewTempClass[] a = null;
		int low = 0;
		Integer d = new Integer(22);
		int high = Integer.MAX_VALUE;
		System.out.println(high);
		int mid = (low+high)/2;
		
		while(low<=high){
			try{
				a = new NewTempClass[mid];
				low = mid + 1;
				
			}catch(OutOfMemoryError e){
				high = mid -1;
			} mid = (low+high)/2;
			
		}System.out.println(mid);
		return a;
	}
}
