package spring.mvc.pj_sch.dto;

import java.sql.Date;

public class OrderDTO {
    private int order_no;
    private String customer_id;
    private int product_no;
    private String product_img_name;
    private String product_name;
    private int product_price;
    private int order_amount;
    private int total_price;
    private String zipcode;
    private String delivery_message;
    private Date order_day;
    private String order_state;
    private String payment;
	
	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
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

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public int getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(int order_amount) {
		this.order_amount = order_amount;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getDelivery_message() {
		return delivery_message;
	}

	public void setDelivery_message(String delivery_message) {
		this.delivery_message = delivery_message;
	}

	public Date getOrder_day() {
		return order_day;
	}

	public void setOrder_day(Date order_day) {
		this.order_day = order_day;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "OrderDTO [order_no=" + order_no + ", customer_id=" + customer_id + ", product_no=" + product_no
				+ ", product_img_name=" + product_img_name + ", product_name=" + product_name + ", product_price="
				+ product_price + ", order_amount=" + order_amount + ", total_price=" + total_price + ", zipcode="
				+ zipcode + ", delivery_message=" + delivery_message +", order_day=" + order_day + ", order_state=" 
				+ order_state + ", payment=" + payment + "]";
	}
}