package spring.mvc.pj_sch.dao;

import java.util.List;

import spring.mvc.pj_sch.dto.ProductDTO;

public interface ProductDAO {
	
	// 상품 조회
	List<ProductDTO> getProductList(int start, int end);
	
	// 카테고리별 조회
	List<ProductDTO> getProductListCategory(int start, int end, String product_category);
	
	// 검색한 상품  총 개수
	int getSearchTotal(String product_category, String keyword);
	
	// 상품 검색
	List<ProductDTO> getSearchProductList(int start, int end, String product_category, String keyword);
	
	// 상품 상세 조회
	ProductDTO getProductDetail(int product_no);
	
	// 상품 등록
	int addProduct(ProductDTO dto);
	
	// 상품 수정
	int updateProduct(ProductDTO dto);
	
	// 상품 삭제
	int deleteProduct(int product_no);

	// 재고 증가
	int plusStock(int product_amount, int product_no);
		
	// 재고 감소
	int minusStock(int product_amount, int product_no);
	
	// 상품 총 개수 조회
	int getProductTotal();
}