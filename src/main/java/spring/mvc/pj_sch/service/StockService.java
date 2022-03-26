package spring.mvc.pj_sch.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface StockService {
	
	// 상품 등록
	void stockAddAction(MultipartHttpServletRequest req, Model model);
	
	// 상품 상세페이지
	void stockDetail(HttpServletRequest req, Model model);
	
	// 상품 수정
	void stockUpdateAction(MultipartHttpServletRequest req, Model model);
	
	// 상품 삭제
	void stockDeleteAction(HttpServletRequest req, Model model);
	
	// 상품 조회
	void stockList(HttpServletRequest req, Model model);
}