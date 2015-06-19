package bo;


public class TestFather {

	private String fatherStrOne = null;
	
	public String fatherStrTwoPublic = null;

	public String getFatherStrOne() {
		return fatherStrOne;
	}

	public void setFatherStrOne(String fatherStrOne) {
		this.fatherStrOne = fatherStrOne;
	}
	
	

	public String getFatherStrTwoPublic() {
		return fatherStrTwoPublic;
	}

	public void setFatherStrTwoPublic(String fatherStrTwoPublic) {
		this.fatherStrTwoPublic = fatherStrTwoPublic;
	}

	public TestFather(String fatherStrOne) {
		super();
		this.fatherStrOne = fatherStrOne;
	}

	public TestFather() {
		super();
	}
	
	
}
