package spring.mvc.pj_sch.dto;

import java.sql.Date;

public class ReplyDTO {
	private int reply_no;
	private String reply_writer;
	private String reply_contents;
	private Date reply_regist_day;
	private int board_no;
	
	public ReplyDTO() {}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public String getReply_writer() {
		return reply_writer;
	}

	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}

	public String getReply_contents() {
		return reply_contents;
	}

	public void setReply_contents(String reply_contents) {
		this.reply_contents = reply_contents;
	}

	public Date getReply_regist_day() {
		return reply_regist_day;
	}

	public void setReply_regist_day(Date reply_regist_day) {
		this.reply_regist_day = reply_regist_day;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	@Override
	public String toString() {
		return "ReplyDTO [reply_no=" + reply_no + ", reply_writer=" + reply_writer + ", reply_contents="
				+ reply_contents + ", reply_regist_day=" + reply_regist_day + ", board_no=" + board_no + "]";
	}
}