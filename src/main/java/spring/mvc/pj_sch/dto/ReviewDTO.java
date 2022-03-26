package spring.mvc.pj_sch.dto;

import java.sql.Date;

public class ReviewDTO {
	private int review_no;
	private String customer_id;     
	private String review_writer;
	private String review_contents;
	private Date review_regist_day;  
    private int product_no;
    private int review_star;
	
	public ReviewDTO() {}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getReview_writer() {
		return review_writer;
	}

	public void setReview_writer(String review_writer) {
		this.review_writer = review_writer;
	}
	
	public String getReview_contents() {
		return review_contents;
	}

	public void setReview_contents(String review_contents) {
		this.review_contents = review_contents;
	}

	public Date getReview_regist_day() {
		return review_regist_day;
	}

	public void setReview_regist_day(Date review_regist_day) {
		this.review_regist_day = review_regist_day;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public int getReview_star() {
		return review_star;
	}

	public void setReview_star(int review_star) {
		this.review_star = review_star;
	}

	@Override
	public String toString() {
		return "ReviewDTO [review_no=" + review_no + ", customer_id=" + customer_id + ", review_contents="
				+ review_contents + ", review_regist_day=" + review_regist_day + ", product_no=" + product_no
				+ ", review_star=" + review_star + "]";
	}
}