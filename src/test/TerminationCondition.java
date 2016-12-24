package test;

class Book{
	boolean checkedOut = false;
	
	Book(boolean checkOut){
		checkedOut = checkOut;
	}
	
	void checkIn(){
		checkedOut = false;
	}
	
	@Override
	protected void finalize(){
		if(checkedOut){
			System.out.println("Error");
			try {
				super.finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
}

public class TerminationCondition {

	public static void main(String[] args) {
		Book novel = new Book(true);
		novel.checkIn();
		new Book(true);
		System.gc();
	}

}
