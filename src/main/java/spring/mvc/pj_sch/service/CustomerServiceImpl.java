package spring.mvc.pj_sch.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.pj_sch.dao.BoardDAO;
import spring.mvc.pj_sch.dao.CustomerDAO;
import spring.mvc.pj_sch.dao.OrderDAO;
import spring.mvc.pj_sch.dao.ProductDAO;
import spring.mvc.pj_sch.dao.ReviewDAO;
import spring.mvc.pj_sch.dao.ShelfDAO;
import spring.mvc.pj_sch.dto.BoardDTO;
import spring.mvc.pj_sch.dto.CustomerDTO;
import spring.mvc.pj_sch.dto.OrderDTO;
import spring.mvc.pj_sch.dto.ProductDTO;
import spring.mvc.pj_sch.dto.ReplyDTO;
import spring.mvc.pj_sch.dto.ReviewDTO;
import spring.mvc.pj_sch.dto.ShelfDTO;
import spring.mvc.pj_sch.dto.ZipcodeDTO;
import spring.mvc.pj_sch.page.Paging;
import spring.mvc.pj_sch.util.EmailCheckHandler;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	ReviewDAO reviewDAO;

	@Autowired
	ShelfDAO shelfDAO;

	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	BoardDAO boardDAO;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
//-------------------------- [ 세션 ] --------------------------------
	
//	@Override // 세션 초기화
//	public void sessionCheck(HttpServletRequest req, Model model) {
//		System.out.println("sessionCheck() 서비스 실행");
//		
//		// 로그인 상태 세션
//		if (req.getSession().getAttribute("loginResult") == null) {
//			req.getSession().setAttribute("loginResult", 2);
//		}
//		
//		// 로그인 고객 아이디 세션
//		if (req.getSession().getAttribute("sessionId") == null)
//			req.getSession().setAttribute("sessionId", "");
//
//		// 로그인 고객 이름 세션
//		if (req.getSession().getAttribute("sessionName") == null)
//			req.getSession().setAttribute("sessionName", "");
//
//		// 회원정보 조회 인증여부 세션
//		if (req.getSession().getAttribute("authResult") == null)
//			req.getSession().setAttribute("authResult", 0);
//	
//	}

//-------------------------- [ 로그인 ] --------------------------------
	
//	@Override // 로그인 처리
//	public void loginAction(HttpServletRequest req, Model model) {
//		System.out.println("loginAction() 서비스 실행");
//		// 로그인 성공 여부
//		int loginResult = 0;
//		
//		// 받아온 회원 아이디, 비밀번호
//		String strId = req.getParameter("id");
//		String strPassword = req.getParameter("password");
//		
//		System.out.println("입력받은 아이디 : " + strId);
//		System.out.println("입력받은 비밀번호 : " + strPassword);
//		
//		String strName = "";
//		// 관리자 아이디를 입력한 경우
//		if (strId.equals("host")) {
//			System.out.println("관리자 아이디 입력받음");
//			// DB에서 로그인 정보 확인
//			loginResult = customerDAO.adminCheck(strId, strPassword);
//			if (loginResult == 1) {
//				loginResult = 99;
//				strName = "관리자";
//			}
//			
//			// 일반 아이디를 입력한 경우
//		} else {
//			System.out.println("일반적인 아이디 입력받음");
//			// DB에서 로그인 정보 확인
//			loginResult = customerDAO.idPasswordCheck(strId, strPassword);
//			
//			CustomerDTO dto = customerDAO.getCustomerDetail(strId);
//			// 회원이 존재하면 세션 이름 설정
//			if (dto != null) strName = dto.getCustomer_name();
//		}
//		
//		System.out.println("로그인 성공 여부 : " + loginResult);
//		
//		// 로그인 여부에 따라 세션 아이디 설정
//		if (loginResult == 1 || loginResult == 99) {
//			req.getSession().setAttribute("sessionId", strId);
//			req.getSession().setAttribute("sessionName", strName);
//		}
//		
//		// 회원수정을 위한 인증여부 세션 설정
//		req.getSession().setAttribute("authResult", 0);
//		
//		// 로그인 성공 여부 설정
//		req.getSession().setAttribute("loginResult", loginResult);
//	}
	
	@Override
	public void logoutAction(HttpServletRequest req, Model model) {
		System.out.println("logoutAction() 서비스 실행");
		
		// 세션 해제
		req.getSession().invalidate();
		
	}
	
	@Override // 아이디 중복 체크
	public void confirmIdAction(HttpServletRequest req, Model model) {
		System.out.println("confirmIdAction() 서비스 실행");
		// 아이디 중복 여부
		int dupChk = 0;
		
		// 입력받은 아이디값
		String strId = req.getParameter("id");
		
		// DB에서 아이디 중복 조회 및 결과 저장
		dupChk = customerDAO.confirmId(strId);
		
		// 결과 반환 (중복 = 1, 신규 = 0)
		model.addAttribute("dupChk", dupChk);
		
		// 입력받은 아이디 request 객체에 설정
		model.addAttribute("strId", strId);
	}
	
//-------------------------- [ 회원가입 ] --------------------------------		

	@Override	// 회원가입 처리
	public void registerAction(HttpServletRequest req, Model model) {
		System.out.println("registerAction() 서비스 실행");

		// 회원가입 데이터를 dto에 저장
		CustomerDTO dto = new CustomerDTO();
		dto.setCustomer_id(req.getParameter("id"));
		
		// 비밀번호 암호화
		String encryptPassword = passwordEncoder.encode(req.getParameter("password"));
		
		dto.setCustomer_password(encryptPassword);
		dto.setCustomer_name(req.getParameter("name"));

		String email = req.getParameter("email1") + "@" + req.getParameter("email2");
		dto.setCustomer_email(email);
		
		String tel = req.getParameter("tel1") + "-" + req.getParameter("tel2") + "-" + req.getParameter("tel3");
		dto.setCustomer_tel(tel);
		
		String zipcode = req.getParameter("zipcode");
		
		dto.setZipcode(zipcode);
		dto.setCustomer_address(req.getParameter("address2"));

		String address1 = req.getParameter("address1");
		String [] data = address1.split(" ");
		
		ZipcodeDTO dto2 = new ZipcodeDTO();
		dto2.setZipcode(zipcode);
		dto2.setSido(data[0]);
		dto2.setGugum(data[1]);
		dto2.setDong(data[2]);
		dto2.setBunji(data[3]);
		
		// 우편번호 DB에 주소 저장
		int insertResult = customerDAO.addZipcode(dto2);
		System.out.println("insertResult = " + insertResult);
		
		// 시큐리티 - 이메일 인증
		String key = EmailCheckHandler.getKey();
		dto.setKey(key);
		
		// DB에서 회원가입 수행
		int registerResult = customerDAO.addCustomer(dto);
		
		if (registerResult == 1) customerDAO.sendEmail(email, key);
		
		// 회원가입 결과 reuqest 객체에 설정
		model.addAttribute("insertResult", insertResult);
		model.addAttribute("registerResult", registerResult);
	}

	@Override // 이메일 인증 처리
	public void emailCheckAction(HttpServletRequest req, Model model) {
		String key = req.getParameter("key");
	    int selectCnt = customerDAO.getKey(key);
	    System.out.println("selectCnt : " + selectCnt);
	  
	    // 키가 일치하면
	    if(selectCnt == 1) {
	        int updateCnt = customerDAO.updateGrade(key);
	        System.out.println("updateCnt : " + updateCnt);
	        model.addAttribute("updateCnt", updateCnt);
	    }
	}
	
//-------------------------------------- [ 회원정보 ] --------------------------------------------	

//	@Override // 회원인증
//	public void customerAuthAction(HttpServletRequest req, Model model) {
//		System.out.println("customerAuthAction() 서비스 실행");
//		
//		// 로그인 성공 여부d
//		int loginResult = 0;
//		
//		// 회원의 인증 상태를 세션에서 받아옴
//		int authResult = 0;
//		
//		// 세션이 없으면
//		if (req.getSession().getAttribute("authResult") == null) {
//			// 세션 생성
//			req.getSession().setAttribute("authResult", authResult);
//		} 
//		
//		// 인증이 안되있으면
//		if (authResult == 0) {
//
//			// 인증을 한 적이 없으면 회원을 조회하여 인증을 수행한다.
//			String strId = (String)req.getSession().getAttribute("sessionId");
//			String strPassword = req.getParameter("password");
//			loginResult = customerDAO.idPasswordCheck(strId, strPassword);
//			
//		}
//
//		// 인증 결과를 request, session 객체에 설정해준다.
//		model.addAttribute("loginResult", loginResult);
//		req.getSession().setAttribute("authResult", authResult);
//		
//	}
	
	@Override // 회원정보 조회
	public void customerDetail(HttpServletRequest req, Model model) {
		System.out.println("selectCustomerAction() 서비스 실행");

		// 1. 세션 아이디를 받아온다.
		String strId = (String)req.getSession().getAttribute("sessionId");
		System.out.println("sessionId : " + strId);
		
		// 2. 해당 회원의 정보를 조회한다.
		CustomerDTO dto = customerDAO.getCustomerDetail(strId);

		// 상세 주소를 우편번호를 통해 가져온다.
		ZipcodeDTO dto2 = customerDAO.getAddress(dto.getZipcode());
		String address1 = dto2.getSido() + " " + dto2.getGugum() +  " " + dto2.getDong() +  " " + dto2.getBunji();
		
		// 3. request 객체에 저장
		model.addAttribute("dto", dto);
		model.addAttribute("address1", address1);
		
	}

	@Override // 회원정보 수정
	public void customerUpdateAction(HttpServletRequest req, Model model) {
		System.out.println("updateCustomerAction() 서비스 실행");
		
		// 1. 회원정보 데이터를 받아서 DTO에 저장
		CustomerDTO dto = new CustomerDTO();
		System.out.println("id : " + (String)req.getSession().getAttribute("sessionId"));
		dto.setCustomer_id((String)req.getSession().getAttribute("sessionId"));
		
		// 비밀번호 암호화
		String password = req.getParameter("password");
		String encryptPassword = passwordEncoder.encode(password);
		dto.setCustomer_password(encryptPassword);
		
		dto.setCustomer_name(req.getParameter("name"));

		String zipcode = req.getParameter("zipcode");
		
		int insertResult = 0;
		
		// 새로운 우편번호인 경우
		if (customerDAO.getZipcodeInfo(zipcode) == null) {
			System.out.println("새 우편번호 등록: " + zipcode);
			String address1 = req.getParameter("address1"); 

			String [] data = address1.split(" ");
		
			ZipcodeDTO dto2 = new ZipcodeDTO();
			dto2.setZipcode(zipcode);
			dto2.setSido(data[0]);
			dto2.setGugum(data[1]);
			dto2.setDong(data[2]);
			dto2.setBunji(data[3]);
			
			insertResult = customerDAO.addZipcode(dto2);
			System.out.println("insertResult = " + insertResult);
			
		}
		dto.setZipcode(zipcode);
		
		dto.setCustomer_address(req.getParameter("address2"));
		
		String tel = req.getParameter("tel1") + "-" + 
						req.getParameter("tel2") + "-" + 
						req.getParameter("tel3");
		dto.setCustomer_tel(tel);
		
		String email = req.getParameter("email1") + "@" + req.getParameter("email2");
		dto.setCustomer_email(email);
		
		// 2. DAO를 생성하여 DTO를 가지고 DB update를 수행한다.
		int updateResult = customerDAO.updateCustomer(dto);
		
		// 3. 수정 결과를 request객체에 담아준다. 
		model.addAttribute("updateResult", updateResult);
		System.out.println("updateResult : " + updateResult);
	}

	@Override // 회원정보 탈퇴 처리
	public void customerDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("deleteCustomerAction() 서비스 실행");
		
		// 1. 회원 세션 아이디를 받아온다.
		String strId = (String)req.getSession().getAttribute("sessionId");
		
		// 2. DAO를 생성하여 로그인한 고객 아이디로 DB delete를 수행한다.
		int deleteResult = customerDAO.deleteCustomer(strId);
		
		// 3. 삭제 결과를 request객체에 담아준다.
		model.addAttribute("deleteResult", deleteResult);
		model.addAttribute("loginResult", 2);
		
	}

//-------------------------------------- [ 상품 ] --------------------------------------------	

	@Override // 상품조회
	public void productList(HttpServletRequest req, Model model) {
		System.out.println("productList() 서비스 실행");
		
		// 1. 카테고리를 받아온다.
		String pageNum = req.getParameter("pageNum");
		String category = req.getParameter("product_category");
		System.out.println("category : " + category);
		
		// 2. DAO와 결과를 담을 바구니 생성
		List<ProductDTO> plist;
		
		// 페이징 처리
		Paging paging = new Paging(pageNum);
		int total = productDAO.getProductTotal();
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		// 카테고리가 없으면
		if (category == null || category == "") {
			// DB에서 전체 조회
			plist = productDAO.getProductList(start, end);
		// 카테고리가 있으면
		} else {
			// 해당 카테고리로 DB에서 조회
			plist = productDAO.getProductListCategory(start, end, category);
		}
		
		// 3. 조회된 상품정보를 request객체에 저장
		model.addAttribute("plist", plist);
		
		// 카테고리 한글로 변환
		String ko_category = "";
		if (category == null) ko_category="전체";
		else if(category.equals("drink")) ko_category = "드링크";
		else if(category.equals("carbon")) ko_category = "탄산음료";
		else if(category.equals("water")) ko_category = "생수";
		else if(category.equals("coffee")) ko_category = "커피";
		
		model.addAttribute("paging", paging);
		model.addAttribute("product_category", category);
		model.addAttribute("ko_category", ko_category);
		
	}

	@Override // 상품 검색 데이터 조회
	public void productSearchList(HttpServletRequest req, Model model) {
		System.out.println("productSearchList() - dao");
	
		// 검색한 단어, 상품 카테고리, 페이지 번호
		String keyword = req.getParameter("keyword");
		String category = req.getParameter("product_category");
		String pageNum = req.getParameter("pageNum");
		System.out.println("keyword : " + keyword);
		System.out.println("category : " + category);
		
		Paging paging = new Paging(pageNum);
		int total = productDAO.getSearchTotal(category, keyword); 
		System.out.println("total : " + total);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<ProductDTO> plist = productDAO.getSearchProductList(start, end, category, keyword);
		
		// 카테고리 한글로 변환
		String ko_category = "";
		if (category == null || category == "") ko_category = "전체";
		else if(category.equals("drink")) ko_category = "드링크";
		else if(category.equals("carbon")) ko_category = "탄산음료";
		else if(category.equals("water")) ko_category = "생수";
		else if(category.equals("coffee")) ko_category = "커피";
		
		model.addAttribute("ko_category", ko_category);
		model.addAttribute("product_category", category);
		model.addAttribute("keyword", keyword);
		model.addAttribute("paging", paging);
		model.addAttribute("plist", plist);
	}
	
	@Override // 상품 상세조회
	public void productDetail(HttpServletRequest req, Model model) {
		System.out.println("productDetail() 서비스 실행");
		
		// 1. 선택한 상품번호와 리뷰 페이지번호, 카테고리를 받아온다.
		int product_no = Integer.parseInt(req.getParameter("product_no"));
		String pageNum = req.getParameter("pageNum");
		String category = req.getParameter("product_category");
		
		// 2. 해당 상품의 상세 내역을 받아온다.
		ProductDTO dto = productDAO.getProductDetail(product_no);
		
		// 3. 해당 상품의 리뷰를 받아온다.

		// 페이징 처리
		Paging paging = new Paging(pageNum);
		int total = reviewDAO.getReviewTotal(product_no);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<ReviewDTO> rlist = reviewDAO.getReviewList2(start, end, product_no);
		
		// 카테고리 한글로 변환
		String ko_category = "";
		if (category == null || category == "") ko_category = "전체";
		else if(category.equals("drink")) ko_category = "드링크";
		else if(category.equals("carbon")) ko_category = "탄산음료";
		else if(category.equals("water")) ko_category = "생수";
		else if(category.equals("coffee")) ko_category = "커피";
		
		model.addAttribute("ko_category", ko_category);
		
		// 3. 조회된 상품 상세정보, 리뷰를 request에 저장
		model.addAttribute("paging", paging);
		model.addAttribute("p_dto", dto);
		model.addAttribute("rlist", rlist);
		
	}
	
	@Override // 상품 구매하기 처리
	public void payAction(HttpServletRequest req, Model model) {
		System.out.println("payAction() 서비스 실행");
		
		// 1-1. 화면에서 데이터를 받아온다.
		String customer_id = (String)req.getSession().getAttribute("sessionId");
		String zipcode = req.getParameter("zipcode");
		String delivery_message = req.getParameter("msg");
		String payment = req.getParameter("payment");

		// 2. 주문에 넣을 데이터들을 담을 큰 바구니 생성
		int product_no = Integer.parseInt(req.getParameter("product_no"));
		int order_amount = Integer.parseInt(req.getParameter("amount"));
		
		OrderDTO dto = new OrderDTO();
		dto.setCustomer_id(customer_id);
		dto.setProduct_no(product_no);
		dto.setOrder_amount(order_amount);
		dto.setZipcode(zipcode);
		dto.setDelivery_message(delivery_message);
		dto.setPayment(payment);
	
		// 3. DAO를 생성하여 DB에서 주문목록을 등록한다.
		int insertResult = 0;
		insertResult = orderDAO.insertOrder(dto);
		
		
		// 3. request에 결과를 저장한다.
		model.addAttribute("insertResult", insertResult);
	}

//-------------------------------------- [ 상품리뷰 ] --------------------------------------------	

	@Override // 상품리뷰 등록
	public void reviewAddAction(HttpServletRequest req, Model model) {
		System.out.println("reviewAddAction() 서비스 실행");
		
		// 1. 고객이 입력한 리뷰 내용과 정보들을 받아온다.
		ReviewDTO dto = new ReviewDTO();
		dto.setCustomer_id((String)req.getSession().getAttribute("sessionId"));
		dto.setReview_writer((String)req.getSession().getAttribute("sessionName"));
		dto.setReview_contents(req.getParameter("review"));
		dto.setProduct_no(Integer.parseInt(req.getParameter("product_no")));
		dto.setReview_star(Integer.parseInt(req.getParameter("star")));
		
		// 2. DAO를 생성하여 DB에서 해당 리뷰번호의 리뷰를 등록한다.
		int insertResult = reviewDAO.addReview(dto);
		
		// 3. 결과를 request 객체에 저장한다.
		model.addAttribute("insertResult", insertResult);
		
		// 4. 해당 상품 상세로 돌아가기 위한 상품번호  request 객체에 저장
		model.addAttribute("product_no", req.getParameter("product_no"));
	}

	@Override // 상품리뷰 삭제
	public void reviewDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("reviewDeleteAction() 서비스 실행");
		
		// 1. 리뷰 번호와 해당 리뷰가 있는 상품의 번호를 받아온다.
		int review_no = Integer.parseInt(req.getParameter("review_no"));
		String product_no = req.getParameter("product_no");
		System.out.println("product_no : " + product_no);
		
		// 2. DAO를 생성하고 DB에서 해당 리뷰번호에 대한 삭제를 처리한다.
		int deleteResult = reviewDAO.deleteReview(review_no);
		
		// 3. 리뷰삭제 결과와 리뷰가 있던 상품 번호를 request 객체에 저장한다.
		model.addAttribute("deleteResult", deleteResult);
		model.addAttribute("product_no", product_no);
	}
	
//-------------------------------------- [ 장바구니 ] --------------------------------------------	

	@Override // 장바구니 조회
	public void cartList(HttpServletRequest req, Model model) {
		System.out.println("cartList() 서비스 실행");
		
		// 1. 현재 고객 아이디를 받아옵니다.
		String customer_id = (String)req.getSession().getAttribute("sessionId");
		
		// 2. DAO를 생성하여 해당 고객의 장바구니를 DB에서 조회합니다.
		List<ShelfDTO> slist = shelfDAO.getCartList(customer_id);
		
		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("slist", slist);
	}
	
	@Override // 장바구니 등록
	public void cartAddAction(HttpServletRequest req, Model model) {
		System.out.println("cartAddAction() 서비스 실행");
		
		// 1. 상품번호와 수량을 받아옵니다.
		String customer_id = (String)req.getSession().getAttribute("sessionId");
		int product_no = Integer.parseInt(req.getParameter("product_no"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		ShelfDTO dto = new ShelfDTO();
		dto.setCustomer_id(customer_id);
		dto.setProduct_no(product_no);
		dto.setAmount(amount);
		
		// 2-1. DAO를 생성하여 장바구니에 동일한 상품이 있는지 확인합니다.
		ShelfDTO oldDTO = shelfDAO.getCartItem(customer_id, product_no);
		
		int insertResult = 0;
		int updateResult = 0;
		// 2-2. 있으면 기존 장바구니 상품의 수량만 증가시킵니다.
		if (oldDTO != null) {
			updateResult = shelfDAO.addDupCartItem(oldDTO.getShelf_no(), amount);
			model.addAttribute("updateResult", updateResult);
		
			// 2-2. 없는 경우, 장바구니 DB에 해당 정보를 새로 등록합니다.
		} else {
			
			insertResult = shelfDAO.addCartItem(dto);
			model.addAttribute("insertResult", insertResult);
		}
		
		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("product_no", product_no);
	}

	@Override // 장바구니 수정
	public void cartUpdateAction(HttpServletRequest req, Model model) {
		System.out.println("cartUpdateAction() 서비스 실행");
		
		// 1. 장바구니의 번호와 변경할 수량을 받아옵니다.
		int shelf_no = Integer.parseInt(req.getParameter("shelf_no"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		System.out.println("shelf_no : " + shelf_no);
		System.out.println("amount : " + amount);
		
		// 2. DAO를 생성하여 해당 장바구니의 상품 수량을 DB에서 변경합니다.
		int updateResult = 0;
		updateResult = shelfDAO.updateCartItemAmount(shelf_no, amount);
		
		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("updateResult", updateResult);
	}
	
	@Override // 장바구니 개별 삭제
	public void cartDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("cartDeleteAction() 서비스 실행");
		
		// 1. 장바구니의 번호들을 받아옵니다.
		String[] shelf_no_arr = req.getParameterValues("shelf_no");

		// 2. DAO를 생성하여 해당 장바구니를 DB에서 삭제합니다.
		int deleteResult = 0;
		deleteResult = shelfDAO.deleteCartItem(shelf_no_arr);
		
		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("deleteResult", deleteResult);
	}
	
	@Override // 장바구니 비우기
	public void cartAllDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("cartAllDeleteAction() 서비스 실행");
		
		// 1. DAO를 생성하여 장바구니를 DB에서 삭제합니다.
		int deleteResult = 0;
		deleteResult = shelfDAO.deleteCartAll();
		System.out.println("deleteResult : " + deleteResult);
		// 2. request 객체에 결과를 저장합니다.
		model.addAttribute("deleteResult", deleteResult);
	}

	@Override // 장바구니 결제하기 페이지 이동
	public void buyCartProduct(HttpServletRequest req, Model model) {
		System.out.println("buyCartProduct() 서비스 실행");
		
		// 1. 현재 로그인 고객의 아이디를 받아옵니다.
//		String customer_id = (String)req.getSession().getAttribute("sessionId");
		String[] data = req.getParameterValues("shelf_no_arr");
		String[] shelf_no_arr = data[0].split(",");
		// 2. DAO를 생성하여 해당 유저의 장바구니 목록을 불러옵니다.
		List<ShelfDTO> slist = shelfDAO.getPayList(shelf_no_arr);
		
		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("slist", slist);
	}

	@Override // 장바구니 결제하기
	public void payCartAction(HttpServletRequest req, Model model) {
		System.out.println("장바구니 구매 서비스 수행중");
		String customer_id = (String)req.getSession().getAttribute("sessionId");
		String zipcode = req.getParameter("zipcode");
		String delivery_message = req.getParameter("msg");
		String payment = req.getParameter("payment");

		String[] data = req.getParameterValues("shelf_no_arr");
		String[] shelf_no_arr = data[0].split(",");
		
		List<OrderDTO> olist = new ArrayList<>();
		List<ShelfDTO> slist = new ArrayList<>();
		
		// 현재 고객의 장바구니 목록 조회
		slist = shelfDAO.getPayList(shelf_no_arr);
		
		// 큰 바구니에 담을 DTO를 생성하여 데이터를 등록 후 olist에 추가
		for (ShelfDTO shelf : slist) {	
			OrderDTO dto = new OrderDTO();
			dto.setCustomer_id(customer_id);
			dto.setProduct_no(shelf.getProduct_no());
			dto.setOrder_amount(shelf.getAmount());
			dto.setZipcode(zipcode);
			dto.setDelivery_message(delivery_message);
			dto.setPayment(payment);
			
			olist.add(dto);
		}
		
		// 2-1. 장바구니 상품 전체 주문 등록
		int insertResult = orderDAO.insertCartOrder(olist);
		
		// 2-2. 주문 등록에 성공하면 장바구니 내역 삭제
		int deleteResult = shelfDAO.deleteCartItem(shelf_no_arr);
		
		model.addAttribute("insertResult", insertResult);
		model.addAttribute("deleteResult", deleteResult);
	}
	
	@Override // 본인 배송지 조회
	public void getCustomerAddress(HttpServletRequest req, Model model) {
		System.out.println("getCustomerAddress() 서비스 실행");
		
		// 1. 현재 로그인 고객의 아이디를 받아옵니다.
		String customer_id = (String)req.getSession().getAttribute("sessionId");
		
		// 2-1. DAO를 생성하여 해당 고객의 zipcode를 받아옵니다.
		String zipcode = customerDAO.getCustomerZipcode(customer_id);
		
		// 2-2. zipcode로 주소정보를 받아옵니다.
		ZipcodeDTO dto = customerDAO.getZipcodeInfo(zipcode);
		
		// 2-3. 고객의 정보를 받아옵니다.
		CustomerDTO dto2 = customerDAO.getCustomerDetail(customer_id);

		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("z_dto", dto);
		model.addAttribute("c_dto", dto2);
	}
	
//-------------------------------------- [ 게시판 ] --------------------------------------------	

	@Override // 게시판 조회
	public void boardList(HttpServletRequest req, Model model) {
		System.out.println("selectBoardListAction() 서비스 실행");
		
		// 1. 게시판의 카테고리와 페이지번호를 받아온다.
		String category = req.getParameter("board_category");
		String pageNum = req.getParameter("pageNum");
		
		// 페이징 처리
		Paging paging = new Paging(pageNum);
		int total = boardDAO.getBoardTotal(category);
		System.out.println("total : " + total);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<BoardDTO> blist = boardDAO.getBoardList(start, end, category);

		// 3. 조회 결과 게시판 목록들을 request객체에 저장한다.
		model.addAttribute("paging", paging);
		model.addAttribute("blist", blist);
		
		// 카테고리
		model.addAttribute("board_category", category);
	}

	@Override // 게시판 상세조회
	public void boardDetail(HttpServletRequest req, Model model) {
		System.out.println("boardDetail() 서비스 실행");
		
		// 1. 게시글 번호와 업데이트인지를 받아온다.
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		String state = req.getParameter("state");
		
		// 2. DAO 생성하여 DB에서 게시글을 조회하고, 결과를 받아온다.
		BoardDTO dto = boardDAO.getBoardDetail(board_no);
		
		// 수정이 아닌 경우 <br>을 변경
		if (state == null || !state.equals("update")) {
			dto.setBoard_contents(dto.getBoard_contents().replace("\r\n", "<br>"));
		}
		
		// 답글을 가져온다.
		List<ReplyDTO> rlist = boardDAO.getReplyList(board_no);
		
		// 조회수 가져와서 1 증가
		int board_hits = boardDAO.getBoardHits(board_no) + 1;
		
		// 조회수 변경
		boardDAO.updateBoardHits(board_no, board_hits);
		
		// 3. 결과를 request 객체에 저장하여 돌려준다.
		model.addAttribute("board", dto);
		model.addAttribute("rlist", rlist);
	}

	@Override // 게시판 등록
	public void boardAddAction(MultipartHttpServletRequest req, Model model) {
		System.out.println("boardAddAction() 서비스 실행");
		
		// 파일처리 추가Start ------------------------------------------------------------------------------------
		MultipartFile file = req.getFile("b_img");
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
		}

		System.out.println("파일 없음 : " + file.getOriginalFilename());
		// 파일처리 추가End ---------------------------------------------------------------------------

		// 1. 카테고리와 게시글 내용들을 받아와 dto에 저장합니다.
		String category = req.getParameter("board_category");
		
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
		dto.setCustomer_id((String)req.getSession().getAttribute("sessionId"));
		dto.setBoard_writer((String)req.getSession().getAttribute("sessionName"));
		dto.setBoard_contents(req.getParameter("contents"));
		dto.setBoard_file_name(img_name);

		if (category.equals("문의사항")) {
			dto.setBoard_state("답변대기");
		}
		
		// 2. DAO를 생성하여 받아온 내용을 DB에 등록합니다.
		int insertResult = boardDAO.addBoard(dto);
		
		// 3. 결과를 받아 request 객체에 저장합니다.
		model.addAttribute("insertResult", insertResult);
	}

	@Override // 게시판 수정
	public void boardUpdateAction(MultipartHttpServletRequest req, Model model) {
		System.out.println("boardUpdateAction() 서비스 실행");
		
		// 파일처리 추가Start ------------------------------------------------------------------------------------
		MultipartFile file = req.getFile("b_img");
		System.out.println("file : " + file);
		
		// 1. 게시글 번호와 수정 내용을 받아옵니다.
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		String upload_img_name = file.getOriginalFilename(); // 수정된 파일명
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
		
		// 3. 결과를 request 객체에 저장
		model.addAttribute("updateResult", updateResult);
	}

	@Override // 게시판 삭제
	public void boardDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("boardDeleteAction() 서비스 실행");
		
		// 1. 게시글 번호를 받아옵니다.
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		
		// 2. DAO를 생성하고, 해당 번호의 게시글을 DB에서 삭제합니다.
		int deleteResult = boardDAO.deleteBoard(board_no);
		
		// 3. 결과를 request 객체에 저장합니다.
		model.addAttribute("deleteResult", deleteResult);
	}

	@Override // 간단 문의등록 처리
	public void askSimpleAddAction(HttpServletRequest req, Model model) {
		System.out.println("askSimpleAddAction() 서비스 실행");
		
		// 1. 카테고리와 게시글 내용들을 받아와 dto에 저장합니다.
		String category = req.getParameter("board_category");
		System.out.println("category : " + category);
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_category(category);
		dto.setBoard_title(req.getParameter("title"));
		dto.setCustomer_id((String)req.getSession().getAttribute("sessionId"));
		dto.setBoard_writer((String)req.getSession().getAttribute("sessionName"));
		dto.setBoard_contents(req.getParameter("contents"));
		dto.setBoard_state("답변대기");
		
		// 2. DAO를 생성하여 받아온 내용을 DB에 등록합니다.
		int insertResult = boardDAO.simpleAddBoard(dto);
		System.out.println("insertResult : " + insertResult);
		
		// 3. 결과를 받아 request 객체에 저장합니다.
		model.addAttribute("insertResult", insertResult);
	}
	
//--------------------------------------- [ 주문 ] ---------------------------------------------

	@Override // 주문목록 조회
	public void customerOrderList(HttpServletRequest req, Model model) {
		System.out.println("customerOrderList() 서비스 실행");
		
		// 1. 고객 아이디와 페이지번호를 받아옵니다.
		String customer_id = (String)req.getSession().getAttribute("sessionId");
		String pageNum = req.getParameter("pageNum");
		
		// 페이징 처리
		Paging paging = new Paging(pageNum);
		int total = orderDAO.getCustomerOrderTotal(customer_id);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<OrderDTO> olist = orderDAO.getCustomerOrderList(start, end, customer_id);
		
		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("paging", paging);
		model.addAttribute("olist", olist);
	}
	
	@Override // 주문 취소
	public void cancelOrderAction(HttpServletRequest req, Model model) {
		System.out.println("cancelOrderAction() 서비스 실행");
		
		// 1. 주문번호를 받아옵니다.
		int order_no = Integer.parseInt(req.getParameter("order_no"));
		String state = "주문취소";
		
		// 2. DAO를 생성하여 DB에서 해당 주문상태를 변경합니다.
		int updateResult = orderDAO.updateState(order_no, state);
		
		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("updateResult", updateResult);
	}

	@Override // 환불
	public void refundAction(HttpServletRequest req, Model model) {
		System.out.println("refundAction() 서비스 실행");
		
		// 1. 주문번호를 받아옵니다.
		int order_no = Integer.parseInt(req.getParameter("order_no"));
		String state = "환불요청";
		
		// 2. DAO를 생성하여 DB에서 해당 주문상태를 변경합니다.
		int updateResult = orderDAO.updateState(order_no, state);
		
		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("updateResult", updateResult);
	}

	@Override // 환불 취소
	public void cancelRefundAction(HttpServletRequest req, Model model) {
		System.out.println("cancelRefundAction() 서비스 실행");

		// 1. 주문번호를 받아옵니다.
		int order_no = Integer.parseInt(req.getParameter("order_no"));
		String state = "환불취소";
		
		// 2. DAO를 생성하여 DB에서 해당 주문상태를 변경합니다.
		int updateResult = orderDAO.updateState(order_no, state);
		
		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("updateResult", updateResult);
	}

	@Override // 바로구매시 넘겨줄 상품정보 조회
	public void buyProduct(HttpServletRequest req, Model model) {
		System.out.println("buyProduct() 서비스 실행");
		
		// 1. 화면으로부터 상품번호와 구매수량을 받아옵니다.
		int product_no = Integer.parseInt(req.getParameter("product_no"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		// 2. DAO를 생성하여 DB에서 상품정보를 저장합니다.
		ProductDTO p_dto = productDAO.getProductDetail(product_no);
		
		// 3. request 객체에 결과를 저장합니다.
		model.addAttribute("p_dto", p_dto);
		model.addAttribute("amount", amount);
	}
}