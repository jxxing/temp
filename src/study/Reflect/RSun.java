package study.Reflect;

public class RSun extends RParent implements RIter{

	private String aaa;
	
	private String bbb;
	
	String ccc;
	
	public String ddd;
	
	public String eee;

	public String getAaa() {
		return aaa;
	}

	public void setAaa(String aaa) {
		this.aaa = aaa;
	}

	public String getBbb() {
		return bbb;
	}

	public void setBbb(String bbb) {
		this.bbb = bbb;
	}

	public String getCcc() {
		return ccc;
	}

	public void setCcc(String ccc) {
		this.ccc = ccc;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getEee() {
		return eee;
	}

	public void setEee(String eee) {
		this.eee = eee;
	}

	public RSun(String cc, String dd, String ee, String ff) {
		super(cc, dd, ee, ff);
	}
	
	
	public RSun() {
		super();
	}
	
	private RSun(String cc, String dd) {
		this.cc = cc;
		this.dd = dd;
	}
}
