package spring.mvc.pj_sch.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.pj_sch.service.AdminService;

@Controller
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService service;
	
	// --------------------------------- [주문 관리] -----------------------------------------
	
	@RequestMapping("orderList.ad") // 주문관리 페이지 이동
	public String orderList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => orderList 진입");
		service.orderList(req, model);
		return "manager/order/orderList";
	}
	
	@RequestMapping("orderConfirmAction.ad") // 주문승인 처리
	public String orderConfirmAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => orderConfirmAction 진입");
		service.orderConfirmAction(req, model);
		return "manager/order/orderConfirmAction";
	}
	
	@RequestMapping("orderCancelAction.ad") // 주문취소 처리
	public String orderCancelAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => orderCancelAction 진입");
		service.orderCancelAction(req, model);
		return "manager/order/orderCancelAction";
	}
	
	// --------------------------------- [환불 관리] -----------------------------------------

	@RequestMapping("refundList.ad") // 환불조회
	public String refundList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => refundList 진입");
		service.refundList(req, model);
		return "manager/refund/refundList";
	}

	@RequestMapping("refundConfirmAction.ad") // 환불승인 처리
	public String refundConfirmAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => refundConfirmAction 진입");
		service.refundConfirmAction(req, model);
		return "manager/refund/refundConfirmAction";
	}

	@RequestMapping("refundRejectAction.ad") // 환불거부 처리
	public String refundRejectAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => refundRejectAction 진입");
		service.refundRejectAction(req, model);
		return "manager/refund/refundRejectAction";
	}

	// --------------------------------- [회원 관리] -----------------------------------------
	@RequestMapping("customerList.ad") // 회원조회
	public String customerList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => customerList 진입");
		service.customerList(req, model);
		return "manager/member/customerList";
	}

	@RequestMapping("customerDeleteAction.ad") // 회원탈퇴 처리
	public String customerDeleteAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => customerDeleteAction 진입");
		service.customerDeleteAction(req, model);
		return "manager/member/customerDeleteAction";
	}

	// --------------------------------- [게시판 관리] -----------------------------------------	
	
	@RequestMapping("boardList.ad") // 게시판 목록 조회
	public String boardList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardList 진입");
		service.boardList(req, model);
		return "manager/board/boardList";
	}

	@RequestMapping("boardDetail.ad") // 게시판 상세 조회
	public String boardDetail(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardDetail 진입");
		service.boardDetail(req, model);
		return "manager/board/boardDetail";
	}

	@RequestMapping("boardAdd.ad") // 게시글 등록 페이지
	public String boardAdd(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardAdd 진입");
		return "manager/board/boardAdd";
	}

	@RequestMapping("boardAddAction.ad") // 게시글 등록 처리
	public String boardAddAction(MultipartHttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardAddAction 진입");
		service.boardAddAction(req, model);
		return "manager/board/boardAddAction";
	}

	@RequestMapping("boardUpdateAction.ad") // 게시글 수정 처리
	public String boardUpdateAction(MultipartHttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardUpdateAction 진입");
		service.boardUpdateAction(req, model);
		return "manager/board/boardUpdateAction";
	}

	@RequestMapping("boardDeleteAction.ad") // 게시글 삭제 처리
	public String boardDeleteAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardDeleteAction 진입");
		service.boardDeleteAction(req, model);
		return "manager/board/boardDeleteAction";
	}

	@RequestMapping("replyList.ad") // 게시글 답글 조회
	public String replyList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => replyList 진입");
		service.replyList(req, model);
		return "manager/board/replyList";
	}

	@RequestMapping("replyAddAction.ad") // 게시글 답글 등록 처리
	public String replyAddAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => replyAddAction 진입");
		service.replyAddAction(req, model);
		return "manager/board/replyAddAction";
	}

	@RequestMapping("replyDeleteAction.ad") // 게시글 답글 삭제 처리
	public String replyDeleteAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => replyDeleteAction 진입");
		service.replyDeleteAction(req, model);
		return "manager/board/replyDeleteAction";
	}

	// --------------------------------- [리뷰 관리] -----------------------------------------	

	@RequestMapping("reviewList.ad") // 리뷰 조회
	public String reviewList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => reviewList 진입");
		service.reviewList(req, model);
		return "manager/review/reviewList";
	}
	
	@RequestMapping("reviewDeleteAction.ad") // 리뷰 삭제
	public String reviewDeleteAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => reviewDeleteAction 진입");
		service.reviewDeleteAction(req, model);
		return "manager/review/reviewDeleteAction";
	}	
	
	// --------------------------------- [결산] -----------------------------------------	

	@RequestMapping("salesList.ad") // 결산 조회
	public String salesList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => salesList 진입");
		service.salesList(req, model);
		return "manager/sales/salesList";
	}	
}