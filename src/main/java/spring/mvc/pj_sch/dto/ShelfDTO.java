package spring.mvc.pj_sch.dto;

public class ShelfDTO {
	private int shelf_no; // 장바구니 번호
	private String customer_id; // 고객 아이디
	private int product_no; // 상품 번호
	private String product_img_name; // 상품 이미지명
	private String product_name; // 상품명
	private String product_price; // 상품가격
	private int amount; // 장바구니에 담은 수량
	
	public ShelfDTO() {}

	public int getShelf_no() {
		return shelf_no;
	}

	public void setShelf_no(int shelf_no) {
		this.shelf_no = shelf_no;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public String getProduct_img_name() {
		return product_img_name;
	}

	public void setProduct_img_name(String product_img_name) {
		this.product_img_name = product_img_name;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ShelfDTO [shelf_no=" + shelf_no + ", customer_id=" + customer_id + ", product_no=" + product_no
				+ ", product_img_name=" + product_img_name + ", product_name=" + product_name + ", product_price="
				+ product_price + ", amount=" + amount + "]";
	}
}