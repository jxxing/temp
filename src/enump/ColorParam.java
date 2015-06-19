package enump;


public enum ColorParam {
	red("00","red1"), green("11","green1"), yellow("22","yellow1"), blue("33","blue1");
	
	private String idss;
	private String name;
	
	ColorParam(String str){
		name = str;
	}
	
	
	
	public String getIdss() {
		return idss;
	}



	public void setIdss(String idss) {
		this.idss = idss;
	}



	public void setName(String name) {
		this.name = name;
	}



	private ColorParam(String idss, String name) {
		this.idss = idss;
		this.name = name;
	}



	public static ColorParam getByName(String name) {
		for (ColorParam _enum : values()) {
			if (_enum.getName().equals(name)) {
				return _enum;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
}
