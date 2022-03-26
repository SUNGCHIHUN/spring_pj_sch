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

import spring.mvc.pj_sch.dao.ProductDAO;
import spring.mvc.pj_sch.dto.ProductDTO;
import spring.mvc.pj_sch.page.Paging;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	ProductDAO productDAO;
	
	@Override // 상품 등록
	public void stockAddAction(MultipartHttpServletRequest req, Model model) {
		System.out.println("stockAddAction() 서비스 실행");
		
		// 파일처리 추가Start ------------------------------------------------------------------------------------
		MultipartFile file = req.getFile("product_file");
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
		
		ProductDTO dto = new ProductDTO();
		
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
		
		dto.setProduct_name(req.getParameter("p_name"));
		dto.setProduct_category(req.getParameter("p_category"));
		dto.setProduct_price(Integer.parseInt(req.getParameter("p_price")));
		dto.setProduct_amount(Integer.parseInt(req.getParameter("p_amount")));
		dto.setProduct_img_name(img_name); // 플젝명/경로
		
		// DAO를 생성하여 상품을 DB에 저장합니다.
		int insertResult = productDAO.addProduct(dto);
		
		// JSP로 데이터 전달
		model.addAttribute("insertResult", insertResult);
	}

	@Override // 상품 상세
	public void stockDetail(HttpServletRequest req, Model model) {
		System.out.println("stockDetail() 서비스");
		
		// 화면에서 값을 받아옵니다.
		int product_no = Integer.parseInt(req.getParameter("product_no"));
		String pageNum = req.getParameter("pageNum");
		
		// 조회 결과 저장
		ProductDTO dto = productDAO.getProductDetail(product_no);
		
		// request 객체에 저장
		model.addAttribute("p_dto", dto);
		model.addAttribute("pageNum", pageNum);
	}
	
	@Override // 상품 수정
	public void stockUpdateAction(MultipartHttpServletRequest req, Model model) {
	System.out.println("stockUpdateAction() 서비스 실행");
	
		// 화면으로부터 값을 입력받는다.
		String pageNum = req.getParameter("pageNum");
		String hidden_img_name = req.getParameter("hidden_img_name"); // 기존 파일명

		System.out.println("hidden_img_name : " + hidden_img_name);
		
		MultipartFile file = req.getFile("product_file");
		
		// 아래 경로에서 파일 열기해서 추가할 이미지 선택
		String saveDir = req.getSession().getServletContext().getRealPath("/resources/upload/");

		// 저장경로(jsp의 IMG_UPLOAD_DIR) : spring_pj_sch 수정
		// 아래 경로 upload 폴더 우클릭 > resources/location 복사해서 가져온다.
		String realDir = "C:\\eclipse\\workspace\\spring_pj_sch\\src\\main\\webapp\\resources\\upload\\";
		
		String p_img = "";
		// 이미지를 수정하고자 할 경우 p_img에 등록
		if (file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			try {
				file.transferTo(new File(saveDir + file.getOriginalFilename()));
				
				FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename()); // 읽기
				FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename()); // 저장
				
				int data = 0;
				
				while ((data = fis.read()) != -1) {
					fos.write(data);
				}
	
				fos.close();
				fis.close();
				
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			p_img = "/spring_pj_sch/resources/upload/" + file.getOriginalFilename();
			System.out.println("새 이미지 : " + p_img);
		
		// 기존 이미지가 있는 경우(이미지 수정 안할 경우)
		} else {
			p_img = hidden_img_name;
			System.out.println(" 기존 이미지 : " + p_img);
		}
		
		System.out.println("p_img : " + p_img);
		
		ProductDTO dto = new ProductDTO();
		dto.setProduct_no(Integer.parseInt(req.getParameter("product_no")));
		dto.setProduct_name(req.getParameter("product_name"));
		dto.setProduct_category(req.getParameter("product_category"));
		dto.setProduct_state(req.getParameter("product_state"));
		dto.setProduct_price(Integer.parseInt(req.getParameter("product_price")));
		dto.setProduct_amount(Integer.parseInt(req.getParameter("product_amount")));
		dto.setProduct_img_name(p_img);
		
		System.out.println(dto);
		
		// DAO 생성
		int updateResult = productDAO.updateProduct(dto);
		
		// request 객체에 저장
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("updateResult", updateResult);
		
	}
	
	@Override // 상품 삭제
	public void stockDeleteAction(HttpServletRequest req, Model model) {
		System.out.println("stockDeleteAction() 서비스 실행");
		
		// 화면으로부터 값을 입력받는다.
		int product_no = Integer.parseInt(req.getParameter("product_no"));
		String pageNum = req.getParameter("pageNum");
		
		// DAO 생성
		int deleteResult = productDAO.deleteProduct(product_no);
		
		// request 객체에 저장
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("deleteResult", deleteResult);
		
	}

	@Override // 상품 조회
	public void stockList(HttpServletRequest req, Model model) {
		
		// 화면에서 값을 받아옵니다.
		String pageNum = req.getParameter("pageNum");
		
		// 페이지 처리
		Paging paging = new Paging(pageNum);
		int total = productDAO.getProductTotal();
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		// DB에서 상품 조회
		List<ProductDTO> plist = productDAO.getProductList(start, end);
		
		// request 객체에 저장
		model.addAttribute("paging", paging);
		model.addAttribute("plist", plist);
	}
}