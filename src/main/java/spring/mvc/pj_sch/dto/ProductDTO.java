package spring.mvc.pj_sch.dto;

import java.sql.Date;

public class ProductDTO {
	private int product_no; // 상품번호
    private String product_name; // 상품명
    private int product_price; // 상품가격
    private int product_amount; // 상품수량
    private Date product_regist_day; // 상품등록일
    private String product_img_name; // 상품이미지명
    private String product_category; // 상품카테고리
    private String product_state; // 상품 상태
	
    public ProductDTO() {}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getProduct_amount() {
		return product_amount;
	}

	public void setProduct_amount(int product_amount) {
		this.product_amount = product_amount;
	}

	public Date getProduct_regist_day() {
		return product_regist_day;
	}

	public void setProduct_regist_day(Date product_regist_day) {
		this.product_regist_day = product_regist_day;
	}

	public String getProduct_img_name() {
		return product_img_name;
	}

	public void setProduct_img_name(String product_img_name) {
		this.product_img_name = product_img_name;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public String getProduct_state() {
		return product_state;
	}

	public void setProduct_state(String product_state) {
		this.product_state = product_state;
	}

	@Override
	public String toString() {
		return "ProductDTO [product_no=" + product_no + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_amount=" + product_amount + ", product_regist_day=" + product_regist_day
				+ ", product_img_name=" + product_img_name + ", product_category=" + product_category
				+ ", product_state=" + product_state + "]";
	}
}