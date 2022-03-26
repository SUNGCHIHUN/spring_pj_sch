package spring.mvc.pj_sch.dto;

import java.sql.Date;

public class CustomerDTO {
	private String customer_id; // 고객 아이디
	private String customer_password; // 고객 비밀번호
	private String customer_name; // 고객 이름
	private String customer_email; // 고객 이메일
	private String customer_tel; // 고객 핸드폰
	private String zipcode; // 우편번호
	private String customer_address; // 고객 상세주소
	private Date customer_regist_day; // 고객 가입일
	private Date customer_delete_day; // 고객 탈퇴일
	private String customer_state; // 가입상태
	// 시큐리티
	private String key; // 회원가입시 이메일 key 추가
	private String authority; // 권한 [ROLE_USER : 사용자 | ROLE_ADMIN : 관리자]
	private String enabled; // 계정 사용가능 여부 [0: 사용불가 | 1: 사용가능] - 이메일 인증시 1
	
	public CustomerDTO() {}

	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_password() {
		return customer_password;
	}
	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_email() {
		return customer_email;
	}
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	public String getCustomer_tel() {
		return customer_tel;
	}
	public void setCustomer_tel(String customer_tel) {
		this.customer_tel = customer_tel;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public Date getCustomer_regist_day() {
		return customer_regist_day;
	}
	public void setCustomer_regist_day(Date customer_regist_day) {
		this.customer_regist_day = customer_regist_day;
	}
	public Date getCustomer_delete_day() {
		return customer_delete_day;
	}
	public void setCustomer_delete_day(Date customer_delete_day) {
		this.customer_delete_day = customer_delete_day;
	}
	public String getCustomer_state() {
		return customer_state;
	}
	public void setCustomer_state(String customer_state) {
		this.customer_state = customer_state;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "CustomerDTO [customer_id=" + customer_id + ", customer_password=" + customer_password
				+ ", customer_name=" + customer_name + ", customer_email=" + customer_email + ", customer_tel="
				+ customer_tel + ", zipcode=" + zipcode + ", customer_address=" + customer_address
				+ ", customer_regist_day=" + customer_regist_day + ", customer_delete_day=" + customer_delete_day
				+ ", customer_state=" + customer_state + ", key=" + key + ", authority=" + authority + ", enabled="
				+ enabled + "]";
	}
}