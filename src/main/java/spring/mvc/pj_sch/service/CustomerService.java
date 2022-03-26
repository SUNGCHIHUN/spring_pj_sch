package spring.mvc.pj_sch.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface CustomerService {
	
	// 세션 초기화
//	void sessionCheck(HttpServletRequest req, Model model);
	
//-------------------------- [ 로그인 ] --------------------------------
	
	// 로그인 처리
//	void loginAction(HttpServletRequest req, Model model);
	
	// 로그아웃 처리
	void logoutAction(HttpServletRequest req, Model model);

//-------------------------- [ 회원가입 ] --------------------------------		

	// 아이디 중복 체크
	void confirmIdAction(HttpServletRequest req, Model model);

	// 회원가입 처리
	void registerAction(HttpServletRequest req, Model model);

	// 시큐리티 - 이메일 인증 처리
	void emailCheckAction(HttpServletRequest req, Model model);
	
//-------------------------- [ 회원정보 ] --------------------------------	
	
	// 회원인증
//	void customerAuthAction(HttpServletRequest req, Model model);
	
	// 회원정보 조회
	void customerDetail(HttpServletRequest req, Model model);
	
	// 회원정보 수정
	void customerUpdateAction(HttpServletRequest req, Model model);
	
	// 회원 탈퇴
	void customerDeleteAction(HttpServletRequest req, Model model);

//-------------------------- [ 상품 ] --------------------------------	
	
	// 상품 조회
	void productList(HttpServletRequest req, Model model);
	
	// 상품 검색처리
	void productSearchList(HttpServletRequest req, Model model);
		
	// 상품상세 조회
	void productDetail(HttpServletRequest req, Model model);
	
	// 상품 구매하기
	void buyProduct(HttpServletRequest req, Model model);

	// 상품 구매 처리	
	void payAction(HttpServletRequest req, Model model);
	
	
//-------------------------- [ 상품리뷰 ] --------------------------------	

	// 상품리뷰 등록
	void reviewAddAction(HttpServletRequest req, Model model);
	
	// 상품리뷰 삭제
	void reviewDeleteAction(HttpServletRequest req, Model model);
	
//-------------------------- [ 장바구니 ] --------------------------------	
	
	// 장바구니 조회
	void cartList(HttpServletRequest req, Model model);
		
	// 장바구니 담기
	void cartAddAction(HttpServletRequest req, Model model);
	
	// 장바구니 상품수정
	void cartUpdateAction(HttpServletRequest req, Model model);
	
	// 장바구니 상품삭제
	void cartDeleteAction(HttpServletRequest req, Model model);
	
	// 장바구니 비우기
	void cartAllDeleteAction(HttpServletRequest req, Model model);
	
	// 장바구니 상품구매
	void buyCartProduct(HttpServletRequest req, Model model);
	
	// 장바구니 상품 결제처리
	void payCartAction(HttpServletRequest req, Model model);

	// 고객 배송지 정보 가져오기
	void getCustomerAddress(HttpServletRequest req, Model model);
	
//-------------------------- [ 게시판 ] --------------------------------	

	// 게시판 조회
	void boardList(HttpServletRequest req, Model model);
	
	// 게시판 상세조회
	void boardDetail(HttpServletRequest req, Model model);
	
	// 게시판 등록
	void boardAddAction(MultipartHttpServletRequest req, Model model);
	
	// 게시판 수정
	void boardUpdateAction(MultipartHttpServletRequest req, Model model);
	
	// 게시판 삭제
	void boardDeleteAction(HttpServletRequest req, Model model);
	
	// 간단 문의 등록
	void askSimpleAddAction(HttpServletRequest req, Model model);
	
//-------------------------- [ 주문 ] --------------------------------	
	
	// 주문목록 조회
	void customerOrderList(HttpServletRequest req, Model model);
	
	// 주문 취소
	void cancelOrderAction(HttpServletRequest req, Model model);
	
	// 환불 요청
	void refundAction(HttpServletRequest req, Model model);

	// 환불 취소
	void cancelRefundAction(HttpServletRequest req, Model model);
}