package spring.mvc.pj_sch.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.pj_sch.dto.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override // 상품목록 조회
	public List<ProductDTO> getProductList(int start, int end) {
		System.out.println("getProductList() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.ProductDAO.getProductList", map);
	}

	@Override // 카테고리별 조회
	public List<ProductDTO> getProductListCategory(int start, int end, String product_category) {
		System.out.println("getProductListCategory() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("product_category", product_category);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.ProductDAO.getProductListCategory", map);
	}
	
	@Override // 검색한 상품 총 개수
	public int getSearchTotal(String product_category, String keyword) {
		System.out.println("getSearchTotal() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("product_category", product_category);
		map.put("keyword", keyword);
		return sqlSession.selectOne("spring.mvc.pj_sch.dao.ProductDAO.getSearchTotal", map);
	}
	
	@Override // 상품 검색
	public List<ProductDTO> getSearchProductList(int start, int end, String product_category, String keyword) {
		System.out.println("getSearchProductList() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("product_category", product_category);
		map.put("keyword", keyword);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.ProductDAO.getSearchProductList", map);
	}

	@Override // 상품 상세 조회
	public ProductDTO getProductDetail(int product_no) {
		System.out.println("getProductDetail() - dao");
		return sqlSession.getMapper(ProductDAO.class).getProductDetail(product_no);
	}

	@Override // 상품 등록
	public int addProduct(ProductDTO dto) {
		System.out.println("addProduct() - dao");
		return sqlSession.getMapper(ProductDAO.class).addProduct(dto);
	}

	@Override // 상품 수정
	public int updateProduct(ProductDTO dto) {
		System.out.println("updateProduct() - dao");
		return sqlSession.getMapper(ProductDAO.class).updateProduct(dto);
	}

	@Override // 상품 삭제
	public int deleteProduct(int product_no) {
		System.out.println("deleteProduct() - dao");
		return sqlSession.getMapper(ProductDAO.class).deleteProduct(product_no);
	}

	@Override // 재고 감소
	public int minusStock(int product_amount, int product_no) {
		System.out.println("minusStock() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("product_amount", product_amount);
		map.put("product_no", product_no);
		return sqlSession.update("spring.mvc.pj_sch.dao.ProductDAO.minusStock", map);
	}
	
	@Override // 재고 증가
	public int plusStock(int product_amount, int product_no) {
		System.out.println("plusStock() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("product_amount", product_amount);
		map.put("product_no", product_no);
		return sqlSession.update("spring.mvc.pj_sch.dao.ProductDAO.plusStock", map);
	}
	
	@Override // 상품 총 개수
	public int getProductTotal() {
		System.out.println("getProductTotal() - dao");
		return sqlSession.getMapper(ProductDAO.class).getProductTotal();
	}
}