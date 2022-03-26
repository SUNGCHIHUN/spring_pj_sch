package spring.mvc.pj_sch.dto;

import java.sql.Date;

public class BoardDTO {
	private int board_no;
	private String board_category;
	private String board_title;
	private String customer_id;
	private String board_writer;
	private String board_contents;
	private String board_file_name;
	private Date board_regist_day;
	private int board_hits;
	private String board_state;
	
	public BoardDTO() {}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBoard_category() {
		return board_category;
	}

	public void setBoard_category(String board_category) {
		this.board_category = board_category;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	
	public String getBoard_contents() {
		return board_contents;
	}

	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}

	public String getBoard_file_name() {
		return board_file_name;
	}

	public void setBoard_file_name(String board_file_name) {
		this.board_file_name = board_file_name;
	}

	public Date getBoard_regist_day() {
		return board_regist_day;
	}

	public void setBoard_regist_day(Date board_regist_day) {
		this.board_regist_day = board_regist_day;
	}

	public int getBoard_hits() {
		return board_hits;
	}

	public void setBoard_hits(int board_hits) {
		this.board_hits = board_hits;
	}

	public String getBoard_state() {
		return board_state;
	}

	public void setBoard_state(String board_state) {
		this.board_state = board_state;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [board_no=" + board_no + ", board_category=" + board_category + ", board_title=" + board_title
				+ ", customer_id=" + customer_id + ", board_contents=" + board_contents + ", board_file_name="
				+ board_file_name + ", board_regist_day=" + board_regist_day + ", board_hits=" + board_hits + "]";
	}
}