package spring.mvc.pj_sch.dao;

import java.util.List;

import spring.mvc.pj_sch.dto.SalesDTO;
import spring.mvc.pj_sch.dto.TotalDTO;

public interface SalesDAO{
	
	// 결산 조회
	List<SalesDTO> getSalesList(int start, int end);
	
	// 결산 내역 개수 조회
	int getSalesTotal();
	
	// 카테고리 총 합계 조회
	List<TotalDTO> getSalesCategory();
}