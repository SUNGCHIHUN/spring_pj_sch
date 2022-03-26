package spring.mvc.pj_sch.dao;

import java.util.List;

import spring.mvc.pj_sch.dto.ShelfDTO;

public interface ShelfDAO {
	
	// 장바구니 리스트 조회
	List<ShelfDTO> getCartList(String customer_id);
	
	// 장바구니 개별 조회
	ShelfDTO getCartItem(String customer_id, int product_no);
	
	// 장바구니 상품 수량 변경
	int updateCartItemAmount(int shelf_no, int amount);
	
	// 장바구니 등록 처리
	int addCartItem(ShelfDTO dto);
	
	// 장바구니 중복 등록 처리
	public int addDupCartItem(int shelf_no, int amount);
	
	// 장바구니 개별 삭제
	int deleteCartItem(String[] shelf_no_arr);
	
	// 장바구니 비우기
	int deleteCartAll();

	// 장바구니에서 선택한 목록
	List<ShelfDTO> getPayList(String[] shelf_no_arr);
}