package spring.mvc.pj_sch.dto;

public class SalesDTO {
	private int product_no; // 상품번호
	private String product_category; // 상품 카테고리
	private String product_name; // 상품명
	private String product_price; // 상품 가격
	private int total_amount; // 상품 총 구매개수
	private int total_price; // 상품판매액 합계
	private int total_sales; // 총 매출액
	
	public SalesDTO() {}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_price() {
		return product_price;
	}

	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public int getTotal_sales() {
		return total_sales;
	}

	public void setTotal_sales(int total_sales) {
		this.total_sales = total_sales;
	}

	@Override
	public String toString() {
		return "SalesDTO [product_no=" + product_no + ", product_category=" + product_category + ", product_name="
				+ product_name + ", product_price=" + product_price + ", total_amount=" + total_amount
				+ ", total_price=" + total_price + ", total_sales=" + total_sales + "]";
	}
}