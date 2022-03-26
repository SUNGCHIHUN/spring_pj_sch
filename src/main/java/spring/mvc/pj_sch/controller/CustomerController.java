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

import spring.mvc.pj_sch.service.CustomerService;

@Controller
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService service;

	// --------------------------------- [메인] -----------------------------------------
	
	@RequestMapping("main.do") // 메인 페이지 이동
	public String main(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => main.do 진입");
		return "common/main";
	}
	
	// --------------------------------- [로그인] -----------------------------------------

	@RequestMapping("login.do") // 로그인 페이지 이동
	public String login(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => login.do 진입");
		return "customer/login/login";
	}
	
//	@RequestMapping("loginAction.do") // 로그인 처리
//	public String loginAction(HttpServletRequest req, Model model) 
//			throws ServletException, IOException{
//		logger.info("url => loginAction.do 진입");
//		service.loginAction(req, model);
//		return "customer/login/loginAction";
//	}
	
	// --------------------------------- [회원가입] -----------------------------------------

	@RequestMapping("register.do") // 회원가입 페이지
	public String register(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => register.do 진입");
		return "customer/register/register";
	}

	@RequestMapping("confirmIdAction.do") // 아이디 중복 확인
	public String confirmIdAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => confirmIdAction.do 진입");
		service.confirmIdAction(req, model);
		return "customer/register/confirmIdAction";
	}

	@RequestMapping("registerAction.do") // 회원가입 처리
	public String registerAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => registerAction.do 진입");
		service.registerAction(req, model);
		return "customer/register/registerAction";
	}

	@RequestMapping("emailCheckAction.do") // 이메일 인증 처리
	public String emailCheck(HttpServletRequest req, Model model) {
		logger.info("url => emailCheckAction.do 진입");
		service.emailCheckAction(req, model);
		return "customer/register/emailCheckAction";
	}
	
	// --------------------------------- [회원정보] -----------------------------------------
	
//	@RequestMapping("customerAuth.do") // 회원인증 페이지 이동
//	public String customerAuth(HttpServletRequest req, Model model) 
//			throws ServletException, IOException{
//		logger.info("url => customerAuth.do 진입");
//		return "customer/info/customerAuth";
//	}

//	@RequestMapping("customerAuthAction.do") // 회원인증 처리
//	public String customerAuthAction(HttpServletRequest req, Model model) 
//			throws ServletException, IOException{
//		service.customerAuthAction(req, model);
//		logger.info("url => customerAuthAction.do 진입");
//		return "customer/info/customerAuthAction";
//	}

	@RequestMapping("customerDetail.do") // 회원정보 조회
	public String customerDetail(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => customerDetail.do 진입");
		service.customerDetail(req, model);
		return "customer/info/customerDetail";
	}
	
	@RequestMapping("customerUpdateAction.do") // 회원정보 수정 처리
	public String customerUpdateAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => customerUpdateAction.do 진입");
		service.customerUpdateAction(req, model);
		return "customer/info/customerUpdateAction";
	}

	@RequestMapping("customerDeleteAction.do") // 회원정보 삭제 처리
	public String customerDeleteAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => customerDeleteAction.do 진입");
		service.customerDeleteAction(req, model);
		return "customer/info/customerDeleteAction";
	}
	
	// --------------------------------- [로그아웃] -----------------------------------------

	@RequestMapping("logoutAction.do") // 로그아웃 처리
	public String logoutAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => logoutAction.do 진입");
		service.logoutAction(req, model);
		return "customer/logout/logoutAction";
	}

	// --------------------------------- [상품] -----------------------------------------

	@RequestMapping("productList.do") // 상품목록 조회
	public String productList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => productList.do 진입");
		service.productList(req, model);
		return "customer/product/productList";
	}

	@RequestMapping("productSearchList.do") // 상품 검색
	public String productSearchList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => productSearchList.do 진입");
		service.productSearchList(req, model);
		return "customer/product/productSearchList";
	}

	@RequestMapping("productDetail.do") // 상품 상세 조회
	public String productDetail(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => productDetail.do 진입");
		service.productDetail(req, model);
		return "customer/product/productDetail";
	}

	// --------------------------------- [구매하기] -----------------------------------------

	@RequestMapping("payList.do") // 구매하기 페이지 이동
	public String payList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => payList.do 진입");
		return "customer/pay/payList";
	}
	
	@RequestMapping("getCustomerAddress.do") // 본인 주소 가져오기
	public String getCustomerAddress(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => getCustomerAddress.do 진입");
		service.getCustomerAddress(req, model);
		return "customer/pay/getCustomerAddress";
	}

	@RequestMapping("payAction.do") // 결제하기
	public String payAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => payAction.do 진입");
		service.payAction(req, model);
		return "customer/pay/payAction";
	}
	
	@RequestMapping("pay.do") // 구매하기 페이지 이동
	public String pay(HttpServletRequest req, Model model) {
		logger.info("url => pay.do 진입");
		service.buyProduct(req, model);
		return "customer/pay/pay";
	}
	
	@RequestMapping("cartPay.do") // 장바구니 구매하기 페이지 이동
	public String cartPay(HttpServletRequest req, Model model) {
		logger.info("url => cartPay.do 진입");
		service.buyCartProduct(req, model);
		return "customer/pay/pay";
	}

	@RequestMapping("cartPayAction.do") // 장바구니 구매하기 결제처리
	public String cartPayAction(HttpServletRequest req, Model model) {
		logger.info("url => cartPayAction.do 진입");
		service.payCartAction(req, model);
		return "customer/pay/payAction";
	}

	// --------------------------------- [리뷰등록] -----------------------------------------

	@RequestMapping("reviewAddAction.do") // 리뷰등록 처리
	public String reviewAddAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => reviewAddAction.do 진입");
		service.reviewAddAction(req, model);
		return "customer/review/reviewAddAction";
	}
	
	@RequestMapping("reviewDeleteAction.do") // 리뷰삭제 처리
	public String reviewDeleteAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => reviewDeleteAction.do 진입");
		service.reviewDeleteAction(req, model);
		return "customer/review/reviewDeleteAction";
	}

	// --------------------------------- [게시판] -----------------------------------------
	
	@RequestMapping("boardList.do") // 게시판 목록 조회
	public String boardList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardList.do 진입");
		service.boardList(req, model);
		return "customer/board/boardList";
	}
	
	@RequestMapping("boardDetail.do") // 게시판 상세 조회
	public String boardDetail(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardDetail.do 진입");
		service.boardDetail(req, model);
		return "customer/board/boardDetail";
	}
	
	@RequestMapping("boardAdd.do") // 게시판 등록 페이지 이동
	public String boardAdd(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardAdd.do 진입");
		return "customer/board/boardAdd";
	}
	
	@RequestMapping("boardAddAction.do") // 게시판 등록 처리
	public String boardAddAction(MultipartHttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardAddAction.do 진입");
		service.boardAddAction(req, model);
		return "customer/board/boardAddAction";
	}
	
	@RequestMapping("boardUpdate.do") // 게시판 수정 페이지 이동
	public String boardUpdate(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardUpdate.do 진입");
		service.boardDetail(req, model);
		return "customer/board/boardUpdate";
	}

	@RequestMapping("boardUpdateAction.do") // 게시판 수정 처리
	public String boardUpdateAction(MultipartHttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardUpdateAction.do 진입");
		service.boardUpdateAction(req, model);
		return "customer/board/boardUpdateAction";
	}

	@RequestMapping("boardDeleteAction.do") // 게시판 삭제 처리
	public String boardDeleteAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => boardDeleteAction.do 진입");
		service.boardDeleteAction(req, model);
		return "customer/board/boardDeleteAction";
	}

	// --------------------------------- [주문] -----------------------------------------
	
	@RequestMapping("customerOrderList.do") // 고객 주문목록 조회
	public String customerOrderList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => customerOrderList.do 진입");
		service.customerOrderList(req, model);
		return "customer/order/customerOrderList";
	}

	@RequestMapping("cancelOrderAction.do") // 주문취소 요청 처리
	public String cancelOrderAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => cancelOrderAction.do 진입");
		service.cancelOrderAction(req, model);
		return "customer/order/cancelOrderAction";
	}
	
	@RequestMapping("refundAction.do") // 환불 요청 처리
	public String refundAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => refundAction.do 진입");
		service.refundAction(req, model);
		return "customer/order/refundAction";
	}
	
	@RequestMapping("cancelRefundAction.do") // 환불취소 요청 처리
	public String cancelRefundAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => cancelRefundAction.do 진입");
		service.cancelRefundAction(req, model);
		return "customer/order/cancelRefundAction";
	}

	// --------------------------------- [장바구니] -----------------------------------------

	@RequestMapping("cartList.do") // 장바구니 목록 조회
	public String cartList(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => cartList.do 진입");
		service.cartList(req, model);
		return "customer/cart/cartList";
	}
	
	@RequestMapping("cartAddAction.do") // 장바구니 등록 처리
	public String cartAddAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => cartAddAction.do 진입");
		service.cartAddAction(req, model);
		return "customer/cart/cartAddAction";
	}
	
	@RequestMapping("cartUpdateAction.do") // 장바구니 수정 처리
	public String cartUpdateAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => cartUpdateAction.do 진입");
		service.cartUpdateAction(req, model);
		return "customer/cart/cartUpdateAction";
	}
	
	@RequestMapping("cartDeleteAction.do") // 장바구니 삭제 처리
	public String cartDeleteAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => cartDeleteAction.do 진입");
		service.cartDeleteAction(req, model);
		return "customer/cart/cartDeleteAction";
	}

	@RequestMapping("cartAllDeleteAction.do") // 장바구니 비우기 처리
	public String cartAllDeleteAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => cartAllDeleteAction.do 진입");
		service.cartAllDeleteAction(req, model);
		return "customer/cart/cartList";
	}

	// --------------------------------- [간편 문의] -----------------------------------------

	@RequestMapping("askSimpleAdd.do") // 간편 문의 등록 페이지 이동
	public String askSimpleAdd(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => askSimpleAdd.do 진입");
		return "customer/board/askSimpleAdd";
	}
	
	@RequestMapping("askSimpleAddAction.do") // 간편 문의 등록 처리
	public String askSimpleAddAction(HttpServletRequest req, Model model) 
			throws ServletException, IOException{
		logger.info("url => askSimpleAddAction.do 진입");
		service.askSimpleAddAction(req, model);
		return "customer/board/askSimpleAddAction";
	}
}