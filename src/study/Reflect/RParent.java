package study.Reflect;

public class RParent implements RIter{

	public String cc;
	
	public String dd;
	
	private String ee;
	
	String ff;

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public String getEe() {
		return ee;
	}

	public void setEe(String ee) {
		this.ee = ee;
	}

	public String getFf() {
		return ff;
	}

	public void setFf(String ff) {
		this.ff = ff;
	}

	public RParent(String cc, String dd, String ee, String ff) {
		super();
		this.cc = cc;
		this.dd = dd;
		this.ee = ee;
		this.ff = ff;
	}

	public RParent() {
		super();
	}

	public RParent(String cc) {
		super();
		this.cc = cc;
	}

	public RParent(String cc, String dd) {
		super();
		this.cc = cc;
		this.dd = dd;
	}
	
	
}
