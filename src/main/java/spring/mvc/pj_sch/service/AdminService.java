package spring.mvc.pj_sch.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AdminService {
	
	//-------------------------- [ 주문관리 ] --------------------------------	
	
	// 주문 조회
	void orderList(HttpServletRequest req, Model model);
	
	// 주문 승인
	void orderConfirmAction(HttpServletRequest req, Model model);
	
	// 주문 취소
	void orderCancelAction(HttpServletRequest req, Model model);
	
	//-------------------------- [ 환불관리 ] --------------------------------	

	// 환불 조회
	void refundList(HttpServletRequest req, Model model);
	
	// 환불 승인
	void refundConfirmAction(HttpServletRequest req, Model model);
	
	// 환불 거부
	void refundRejectAction(HttpServletRequest req, Model model);
	
	//-------------------------- [ 회원관리 ] --------------------------------	

	// 회원 조회
	void customerList(HttpServletRequest req, Model model);
	
	// 회원 탈퇴처리
	void customerDeleteAction(HttpServletRequest req, Model model);

	//-------------------------- [ 게시판 ] --------------------------------	
	
	// 게시글 조회
	void boardList(HttpServletRequest req, Model model);
	
	// 게시글 상세조회
	void boardDetail(HttpServletRequest req, Model model);
	
	// 게시글 등록
	void boardAddAction(MultipartHttpServletRequest req, Model model);
	
	// 게시글 수정
	void boardUpdateAction(MultipartHttpServletRequest req, Model model);

	// 게시글 삭제
	void boardDeleteAction(HttpServletRequest req, Model model);

	// 문의사항 답변 조회
	void replyList(HttpServletRequest req, Model model);

	// 문의사항 답변 등록
	void replyAddAction(HttpServletRequest req, Model model);
	
	// 문의사항 답변 삭제
	void replyDeleteAction(HttpServletRequest req, Model model);
	
	//-------------------------- [ 리뷰 ] --------------------------------
	
	// 리뷰 조회
	void reviewList(HttpServletRequest req, Model model);

	// 리뷰 삭제
	void reviewDeleteAction(HttpServletRequest req, Model model);

	//-------------------------- [ 결산 ] --------------------------------	

	// 결산내역 조회
	void salesList(HttpServletRequest req, Model model);

}