package spring.mvc.pj_sch.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.pj_sch.dto.ShelfDTO;

@Repository
public class ShelfDAOImpl implements ShelfDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override // 장바구니 리스트 조회
	public List<ShelfDTO> getCartList(String customer_id) {
		System.out.println("getCartList() - dao");
		return sqlSession.getMapper(ShelfDAO.class).getCartList(customer_id);
	}
	
	@Override // 장바구니 개별 조회
	public ShelfDTO getCartItem(String customer_id, int product_no) {
		System.out.println("getCartItem() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("customer_id", customer_id);
		map.put("product_no", product_no);
		return sqlSession.selectOne("spring.mvc.pj_sch.dao.ShelfDAO.getCartItem", map);
	}
	
	@Override // 장바구니 상품 수량 변경
	public int updateCartItemAmount(int shelf_no, int amount) {
		System.out.println("updateCartItemAmount() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("shelf_no", shelf_no);
		map.put("amount", amount);
		return sqlSession.update("spring.mvc.pj_sch.dao.ShelfDAO.updateCartItemAmount", map);
	}
	
	@Override // 장바구니 등록 처리
	public int addCartItem(ShelfDTO dto) {
		System.out.println("addCartItem() - dao");
		return sqlSession.getMapper(ShelfDAO.class).addCartItem(dto);
	}

	@Override // 장바구니 중복 등록 처리
	public int addDupCartItem(int shelf_no, int amount) {
		System.out.println("addDupCartItem() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("shelf_no", shelf_no);
		map.put("amount", amount);
		return sqlSession.update("spring.mvc.pj_sch.dao.ShelfDAO.addDupCartItem", map);
	}

	@Override // 장바구니 개별 삭제
	public int deleteCartItem(String[] shelf_no_arr) {
		System.out.println("deleteCart() - dao");
		return sqlSession.getMapper(ShelfDAO.class).deleteCartItem(shelf_no_arr);
	}

	@Override // 장바구니 비우기
	public int deleteCartAll() {
		System.out.println("deleteCartAll() - dao");
		return sqlSession.getMapper(ShelfDAO.class).deleteCartAll();
	}

	@Override // 장바구니 선택 후 결제하기 목록
	public List<ShelfDTO> getPayList(String[] shelf_no_arr) {
		System.out.println("getPayList() - dao");
		return sqlSession.getMapper(ShelfDAO.class).getPayList(shelf_no_arr);
	}
}