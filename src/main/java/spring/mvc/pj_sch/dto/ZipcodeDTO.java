package spring.mvc.pj_sch.dto;

public class ZipcodeDTO {
	private String zipcode;
	private String sido;
	private String gugum;
	private String dong;
	private String bunji;
	private String detail_address;
	 
	public ZipcodeDTO() {}
	
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugum() {
		return gugum;
	}

	public void setGugum(String gugum) {
		this.gugum = gugum;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getBunji() {
		return bunji;
	}

	public void setBunji(String bunji) {
		this.bunji = bunji;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	@Override
	public String toString() {
		return "ZipcodeDTO [zipcode=" + zipcode + ", sido=" + sido + ", gugum=" + gugum
				+ ", dong=" + dong + ", bunji=" + bunji + ", detail_address=" + detail_address + "]";
	}
}