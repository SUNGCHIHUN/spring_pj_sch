package spring.mvc.pj_sch.dto;

public class TotalDTO {
	private String product_category; // 카테고리
	private int total_amount; // 총 판매개수
	private int total_sales; // 합계
	
	public TotalDTO() {}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	
	public int getTotal_sales() {
		return total_sales;
	}

	public void setTotal_sales(int total_sales) {
		this.total_sales = total_sales;
	}

	@Override
	public String toString() {
		return "TotalDTO [product_category=" + product_category + ", total_amount=" + total_amount + ", total_sales="
				+ total_sales + "]";
	}
}