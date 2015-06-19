package bo;

public class Test extends TestFather{
	private boolean islock1;
	
	private boolean isLock2;
	
	private Boolean islock3;
	
	private Boolean isLock4;

	public boolean isIslock1() {
		return islock1;
	}

	public void setIslock1(boolean islock1) {
		this.islock1 = islock1;
	}

	public boolean isLock2() {
		return isLock2;
	}

	public void setLock2(boolean isLock2) {
		this.isLock2 = isLock2;
	}

	public Boolean getIslock3() {
		return islock3;
	}

	public void setIslock3(Boolean islock3) {
		this.islock3 = islock3;
	}

	public Boolean getIsLock4() {
		return isLock4;
	}

	public void setIsLock4(Boolean isLock4) {
		this.isLock4 = isLock4;
	}

	public Test(boolean islock1, boolean isLock2, Boolean islock3,
			Boolean isLock4) {
		super();
		this.islock1 = islock1;
		this.isLock2 = isLock2;
		this.islock3 = islock3;
		this.isLock4 = isLock4;
	}

	public Test() {
		super();
	}

	protected Test(boolean islock1) {
		super();
		this.islock1 = islock1;
	}

	private Test(boolean islock1, boolean isLock2) {
		super();
		this.islock1 = islock1;
		this.isLock2 = isLock2;
	}

	Test(boolean islock1, boolean isLock2, Boolean islock3) {
		super();
		this.islock1 = islock1;
		this.isLock2 = isLock2;
		this.islock3 = islock3;
	}
	
	private String testPrivate(){
		return null;
	}
	
	
	public static String testStatic(){
		return null;
	}
	
	private int[] a = null;
	
}
