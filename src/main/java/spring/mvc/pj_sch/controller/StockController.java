package spring.mvc.pj_sch.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.pj_sch.service.StockService;

@Controller
public class StockController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	StockService service;
	
	// --------------------------------- [재고 관리] -----------------------------------------

	@RequestMapping("stockList.st") // 재고 목록
	public String stockList(HttpServletRequest req, Model model) {
		logger.info("url => stockList 진입");
		service.stockList(req, model);
		return "manager/stock/stockList";
	}
	
	@RequestMapping("stockAdd.st") // 재고 등록 페이지
	public String stockAdd(HttpServletRequest req, Model model) {
		logger.info("url => stockAdd 진입");
		return "manager/stock/stockAdd";
	}
	
	@RequestMapping("stockAddAction.st") // 재고 등록 처리
	public String stockAddAction(MultipartHttpServletRequest req, Model model) {
		logger.info("url => stockAddAction 진입");
		service.stockAddAction(req, model);
		return "manager/stock/stockAddAction";
	}
	
	@RequestMapping("stockDetail.st") // 재고 상세 페이지
	public String stockDetail(HttpServletRequest req, Model model) {
		logger.info("url => stockDetail 진입");
		service.stockDetail(req, model);
		return "manager/stock/stockDetail";
	}

	@RequestMapping("stockUpdateAction.st") // 재고 수정 처리
	public String stockUpdateAction(MultipartHttpServletRequest req, Model model) {
		logger.info("url => stockUpdateAction 진입");
		service.stockUpdateAction(req, model);
		return "manager/stock/stockUpdateAction";
	}

	@RequestMapping("stockDeleteAction.st") // 재고 삭제 처리
	public String stockDeleteAction(HttpServletRequest req, Model model) {
		logger.info("url => stockDeleteAction 진입");
		service.stockDeleteAction(req, model);
		return "manager/stock/stockDeleteAction";
	}
}