package spring.mvc.pj_sch.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.pj_sch.dao.BoardDAO;
import spring.mvc.pj_sch.dao.CustomerDAO;
import spring.mvc.pj_sch.dao.OrderDAO;
import spring.mvc.pj_sch.dao.ProductDAO;
import spring.mvc.pj_sch.dao.ReviewDAO;
import spring.mvc.pj_sch.dao.SalesDAO;
import spring.mvc.pj_sch.dto.BoardDTO;
import spring.mvc.pj_sch.dto.CustomerDTO;
import spring.mvc.pj_sch.dto.OrderDTO;
import spring.mvc.pj_sch.dto.ReplyDTO;
import spring.mvc.pj_sch.dto.ReviewDTO;
import spring.mvc.pj_sch.dto.SalesDTO;
import spring.mvc.pj_sch.dto.TotalDTO;
import spring.mvc.pj_sch.page.Paging;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	BoardDAO boardDAO;

	@Autowired
	ReviewDAO reviewDAO;
	
	@Autowired
	SalesDAO salesDAO;
	
	// ----------------------------------------- [ 주문관리 ] ---------------------------------------------------	
	
	@Override // 주문목록 조회
	public void orderList(HttpServletRequest req, Model model) {
		System.out.println("orderList() 서비스 실행");

		// 페이징 처리
		String pageNum = req.getParameter("pageNum"); // 페이지 번호
		Paging paging = new Paging(pageNum); // 페이지 객체
		int total = orderDAO.getOrderTotal(); // 주문 총 개수
		paging.setTotalCount(total); // 페이지 총 개수 설정
		int start = paging.getStartRow(); // 시작 번호
		int end = paging.getEndRow(); // 끝 번호
		
		// 전체 주문목록 조회
		List<OrderDTO> olist = orderDAO.getOrderList(start, end);
		
		// JSP로 데이터 전달
		model.addAttribute("olist", olist);
		model.addAttribute("paging", paging);
	}

	@Override // 주문승인
	public void orderConfirmAction(HttpServletRequest req, Model model) {
		System.out.println("orderConfirmAction() 서비스 실행");
		
		String pageNum = req.getParameter("pageNum"); // 페이지 번호
		
		int order_no = Integer.parseInt(req.getParameter("order_no")); // 주문번호
		int product_no = Integer.parseInt(req.getParameter("product_no")); // 상품번호
		int order_amount = Integer.parseInt(req.getParameter("order_amount")); // 주문수량
		
		// 재고를 감소시키고 주문상태를 '결제승인'으로 변경한다.
		productDAO.minusStock(order_amount, product_no);
		int updateResult = orderDAO.updateState(order_no, "결제승인");
		
		// JSP로 데이터 전달
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("updateResult", updateResult);
	}

	@Override // 주문취소
	public void orderCancelAction(HttpServletRequest req, Model model) {
		System.out.println("orderCancelAction() 서비스 실행");

		String pageNum = req.getParameter("pageNum"); // 페이지 번호
		int order_no = Integer.parseInt(req.getParameter("order_no")); // 주문번호

		// 주문상태를 결제취소로 변경한다.
		int updateResult = orderDAO.updateState(order_no, "결제취소");
		
		// JSP로 데이터 전달
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("updateResult", updateResult);
	}
	
	// ----------------------------------------- [ 환불관리 ] ---------------------------------------------------	

	@Override // 환불 조회
	public void refundList(HttpServletRequest req, Model model) {
		System.out.println("refundList() 서비스 실행");

		// 페이징 처리
		String pageNum = req.getParameter("pageNum");
		Paging paging = new Paging(pageNum);
		int total = orderDAO.getRefundTotal();
		paging.setTotalCount(total);
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		// 환불 목록
		List<OrderDTO> olist = orderDAO.getRefundList(start, end, "환불요청");
		
		// JSP로 데이터 전달
		model.addAttribute("olist", olist);
		model.addAttribute("paging", paging);
	}
	
	@Override // 환불승인
	public void refundConfirmAction(HttpServletRequest req, Model model) {
		System.out.println("confirmRefundAction() 서비스 실행");
		
		String pageNum = req.getParameter("pageNum"); // 페이지 번호
		int order_no = Integer.parseInt(req.getParameter("order_no")); // 주문번호
		int product_no = Integer.parseInt(req.getParameter("product_no")); // 상품번호
		int order_amount = Integer.parseInt(req.getParameter("order_amount")); // 주문수량
		
		// 재고를 증가시키고 주문상태를 '환불완료'로 변경한다.
		productDAO.plusStock(order_amount, product_no);
		int updateResult = orderDAO.updateState(order_no, "환불완료");
		
		// JSP로 데이터 전달
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("updateResult", updateResult);
	}

	@Override // 환불거부
	public void refundRejectAction(HttpServletRequest req, Model model) {
		System.out.println("cancelRefundAction() 서비스 실행");
		
		// 화면으로부터 값을 받아온다.
		String pageNum = req.getParameter("pageNum"); // 페이지 번호
		int order_no = Integer.parseInt(req.getParameter("order_no")); // 주문번호

		// 주문상태를 '환불거부'로 변경한다.
		int updateResult = orderDAO.updateState(order_no, "환불거부");
		
		// JSP로 데이터 전달
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("updateResult", updateResult);
	}
	
	// ----------------------------------------- [ 회원관리 ] ---------------------------------------------------	
	
	@Override // 회원정보 조회
	public void customerList(HttpServletRequest req, Model model) {
		System.out.println("selectCustomerList() 서비스 실행");
		
		// 페이징 처리
		String pageNum = req.getParameter("pageNum");
		Paging paging = new Paging(pageNum);
		int total = customerDAO.getCustomerTotal();
		paging.setTotalCount(total);
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		// 회원목록
		List<CustomerDTO> clist = customerDAO.getCustomerList(start, end);
		
		// JSP로 데이터 전달
		model.addAttribute("paging", paging);
		model.addAttribute("clist", clist);
	}

	@Override // 회원 탈퇴처리
	public void customerDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("customerDeleteAction() 서비스 실행");
		
		String pageNum = req.getParameter("pageNum"); // 페이지 번호
		String customer_id = req.getParameter("customer_id"); // 고객 아이디
		
		// 회원 삭제처리한다.
		int updateResult = customerDAO.deleteCustomer(customer_id);
		
		// JSP로 데이터 전달
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("updateResult", updateResult);
	}	
	
	// ----------------------------------------- [ 게시판 ] ---------------------------------------------------	
	
	@Override // 게시글 조회
	public void boardList(HttpServletRequest req, Model model) {
		System.out.println("boardList() 서비스 실행");
		
		String category = req.getParameter("board_category"); // 게시판 카테고리
		
		// 페이징 처리
		String pageNum = req.getParameter("pageNum");
		Paging paging = new Paging(pageNum);
		int total = boardDAO.getBoardTotal(category); // 카테고리에 맞는 게시글 총 개수
		paging.setTotalCount(total);
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		// 카테고리에 맞는 게시글목록
		List<BoardDTO> blist = boardDAO.getBoardList(start, end, category);
		
		// JSP로 데이터 전달
		model.addAttribute("paging", paging);
		model.addAttribute("blist", blist);
		model.addAttribute("board_category", category);
	}

	@Override // 게시글 상세조회
	public void boardDetail(HttpServletRequest req, Model model) {
		System.out.println("boardDetail() 서비스 실행");

		int board_no = Integer.parseInt(req.getParameter("board_no")); // 게시글 번호
		String state = req.getParameter("state"); // 게시글 상태
		
		// 게시글을 조회하고, 결과를 받아온다.
		BoardDTO dto = boardDAO.getBoardDetail(board_no);
		
		// 조회수 가져와서 1 증가
		int board_hits = boardDAO.getBoardHits(board_no) + 1;
		
		// 조회수 변경
		boardDAO.updateBoardHits(board_no, board_hits);
		
		// 게시글 수정이 아닌 경우(상세조회)
		if (state == null || !state.equals("update")) {
			dto.setBoard_contents(dto.getBoard_contents().replace("\r\n", "<br>"));
			
		// 게시글 수정인 경우
		} else if (state.equals("update")) {
			dto.setBoard_contents(dto.getBoard_contents().replace("<br>", "\r\n"));
		}
		
		// 답변목록
		List<ReplyDTO> rlist = boardDAO.getReplyList(board_no);
		
		// JSP로 데이터 전달
		model.addAttribute("board", dto);
		model.addAttribute("rlist", rlist);
	}
	
	@Override // 게시글 등록
	public void boardAddAction(MultipartHttpServletRequest req, Model model) {
		System.out.println("boardAddAction() 서비스 실행");

		// 파일처리 추가Start ------------------------------------------------------------------------------------
		MultipartFile file = req.getFile("board_img");
		System.out.println("file : " + file);
		
		// 파일을 열 경로
		String saveDir = req.getSession().getServletContext().getRealPath("/resources/upload/");
		System.out.println("saveDir : " + saveDir);
		
		// 파일이 저장될 경로(jsp의 IMG_UPLOAD_DIR)
		String realDir = "C:\\eclipse\\workspace\\spring_pj_sch\\src\\main\\webapp\\resources\\upload\\";
		System.out.println("realDir : " + realDir);
		if (file.getOriginalFilename() != "") {
			System.out.println("등록할 파일이 존재 : " + file.getOriginalFilename());
			try {
				file.transferTo(new File(saveDir + file.getOriginalFilename()));

				// 파일 IO Stream 생성 
				FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename()); // 읽기
				FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename()); // 저장
				
				int data = 0;
				
				// 파일이 존재하는 동안 데이터 쓰기
				while((data = fis.read()) != -1) {
					fos.write(data);
				}
				
				fis.close();
				fos.close();
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("파일 없음 : " + file.getOriginalFilename());
		}

		// 파일처리 추가End ---------------------------------------------------------------------------
		
		String category = req.getParameter("board_category"); // 게시글 카테고리
		
		// 게시글 데이터 객체
		BoardDTO dto = new BoardDTO();
		
		String fileName = file.getOriginalFilename();
		String img_name;
		
		// 첨부파일이 없으면
		if (fileName == null || fileName == "") {
			img_name = "noFile";
		
		// 첨부파일이 있으면
		} else {
			img_name = "/spring_pj_sch/resources/upload/" + fileName; // dto에 넣어줄 파일명 결과
		}
		
		System.out.println("img_name : " + img_name);
		
		dto.setBoard_category(category);
		dto.setBoard_title(req.getParameter("title"));
		dto.setCustomer_id("host");
		dto.setBoard_writer("관리자");
		dto.setBoard_contents(req.getParameter("contents").replace("\r\n", "<br>"));
		dto.setBoard_file_name(img_name);
		
		if (category.equals("ask")) {
			dto.setBoard_state("답변대기");
		} else {
			dto.setBoard_state("상태없음");
		}
		System.out.println("dto : " + dto);
		
		// 2. DAO를 생성하여 받아온 내용을 DB에 등록합니다.
		int insertResult = boardDAO.addBoard(dto);
		
		// JSP로 데이터 전달
		model.addAttribute("board_category", category);
		model.addAttribute("insertResult", insertResult);
	}
	
	@Override // 게시글 수정
	public void boardUpdateAction(MultipartHttpServletRequest req, Model model) {
		System.out.println("boardUpdateAction() 서비스 실행");

		// 파일처리 추가Start ------------------------------------------------------------------------------------
		MultipartFile file = req.getFile("b_img");
		System.out.println("file : " + file);
		
		// 1. 게시글 번호와 수정 내용을 받아옵니다.
		int board_no = Integer.parseInt(req.getParameter("board_no"));

		String upload_img_name = ""; // 수정된 파일명을 저장할 변수
		if (file != null) {
			upload_img_name = file.getOriginalFilename(); // 수정된 파일명
		}
		
		String hidden_img_name = req.getParameter("hidden_img_name"); // 기존 파일명
		System.out.println("upload_img_name : " + upload_img_name);
		System.out.println("hidden_img_name : " + hidden_img_name);
		
		String img_name = ""; // dto에 넣어줄 파일명 결과
		
		// 파일을 수정하지 않은 경우
		if (upload_img_name == "") {
			System.out.println("이미지를 수정하지 않았습니다. ==> " + hidden_img_name);
			img_name = hidden_img_name;
		
		// 수정한 경우
		} else {
			System.out.println("이미지를 수정하였습니다. ==> " + hidden_img_name + " -> " + upload_img_name);
			
			// 파일을 열 경로
			String saveDir = req.getSession().getServletContext().getRealPath("/resources/upload/");
			System.out.println("saveDir : " + saveDir);
			
			// 파일이 저장될 경로(jsp의 IMG_UPLOAD_DIR)
			String realDir = "C:\\eclipse\\workspace\\spring_pj_sch\\src\\main\\webapp\\resources\\upload\\";
			System.out.println("realDir : " + realDir);
			
			try {
				file.transferTo(new File(saveDir + file.getOriginalFilename()));

				// 파일 IO Stream 생성 
				FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename()); // 읽기
				FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename()); // 저장
				
				int data = 0;
				
				// 파일이 존재하는 동안 데이터 쓰기
				while((data = fis.read()) != -1) {
					fos.write(data);
				}
				
				fis.close();
				fos.close();
				
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			img_name = "/spring_pj_sch/resources/upload/" + upload_img_name;
		}
		
		// 파일처리 추가End ---------------------------------------------------------------------------
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_no(board_no);
		dto.setBoard_title(req.getParameter("title"));
		dto.setBoard_contents(req.getParameter("contents"));
		dto.setBoard_file_name(img_name);
		
		// 2. DAO를 생성하고, 받아온 내용을 DB에서 수정합니다.
		int updateResult = boardDAO.updateBoard(dto);
		
		// JSP로 데이터 전달
		model.addAttribute("updateResult", updateResult);
	}
	
	@Override // 게시글 삭제
	public void boardDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("boardDeleteAction() 서비스 실행");
	
		int board_no = Integer.parseInt(req.getParameter("board_no")); // 게시글 번호
		
		// 해당 번호의 게시글을 삭제
		int deleteResult = boardDAO.deleteBoard(board_no);
		
		// JSP로 데이터 전달
		model.addAttribute("deleteResult", deleteResult);
	}

	@Override // 답변 조회
	public void replyList(HttpServletRequest req, Model model) {
		System.out.println("replyList() 서비스 실행");
		
		int board_no = Integer.parseInt(req.getParameter("board_no")); // 게시글 번호
		
		// 답변 목록
		List<ReplyDTO> rlist = boardDAO.getReplyList(board_no);
		
		// JSP로 데이터 전달
		model.addAttribute("rlist", rlist);
	}
	
	@Override // 문의사항 답변 등록
	public void replyAddAction(HttpServletRequest req, Model model) {
		System.out.println("replyAddAction() 서비스 실행");

		int board_no = Integer.parseInt(req.getParameter("board_no")); // 게시글 번호
		String contents = req.getParameter("reply_contents"); // 답변 내용
		
		// 답글 데이터 객체
		ReplyDTO dto = new ReplyDTO();
		dto.setReply_writer("관리자");
		dto.setReply_contents(contents);
		dto.setBoard_no(board_no);
		
		// 문의사항 답변 등록 처리
		int insertResult = boardDAO.addReply(dto);
		
		// 답변상태를 '답변완료'로 변경
		int updateResult = boardDAO.updateBoardState(board_no, "답변완료");
		
		// JSP로 데이터 전달
		model.addAttribute("insertResult", insertResult);
		model.addAttribute("updateResult", updateResult);
	}

	@Override // 문의사항 답변 삭제
	public void replyDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("replyDeleteAction() 서비스 실행");
		
		int reply_no = Integer.parseInt(req.getParameter("reply_no")); // 답변 번호
		
		// 문의사항 답변 삭제 처리
		int deleteResult = boardDAO.deleteReply(reply_no);
		System.out.println("deleteResult : " + deleteResult);
		
		// JSP로 데이터 전달
		model.addAttribute("deleteResult", deleteResult);
	}

	// ----------------------------------------- [ 리뷰 ] ---------------------------------------------------	

	@Override // 리뷰 조회
	public void reviewList(HttpServletRequest req, Model model) {
		System.out.println("reviewListAction() 서비스 실행");
		
		// 페이징 처리
		String pageNum = req.getParameter("pageNum");
		Paging paging = new Paging(pageNum);
		int total = reviewDAO.getReviewAllTotal();
		paging.setTotalCount(total);
		int start = paging.getStartRow();
		int end = paging.getEndRow();

		// 리뷰 조회
		List<ReviewDTO> rlist = reviewDAO.getReviewList(start, end);
		
		// JSP로 데이터 전달
		model.addAttribute("paging", paging);
		model.addAttribute("rlist", rlist);
	}
	
	@Override // 리뷰 삭제
	public void reviewDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("reviewDeleteAction() 서비스 실행");

		String pageNum = req.getParameter("pageNum"); // 페이지 번호
		int review_no = Integer.parseInt(req.getParameter("review_no")); // 리뷰 번호

		// 리뷰 삭제 처리
		int deleteResult = reviewDAO.deleteReview(review_no);
		
		// JSP로 데이터 전달
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("deleteResult", deleteResult);
	}
	
	// ----------------------------------------- [ 결산 ] ---------------------------------------------------	
	
	@Override // 결산 조회
	public void salesList(HttpServletRequest req, Model model) {
		System.out.println("salesList() 서비스 실행");
		
		// 페이징 처리
		String pageNum = req.getParameter("pageNum");
		Paging paging = new Paging(pageNum);
		int total = salesDAO.getSalesTotal();
		paging.setTotalCount(total);
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		System.out.println("total : " + total);
		
		// 결산 목록
		List<SalesDTO> slist = salesDAO.getSalesList(start, end);
		System.out.println("slist size : " + slist.size());
		
		// 결산 총합 목록
		List<TotalDTO> tlist = salesDAO.getSalesCategory();
		
		// JSP로 데이터 전달
		model.addAttribute("paging", paging);
		model.addAttribute("slist", slist);
		model.addAttribute("tlist", tlist);
	}
}