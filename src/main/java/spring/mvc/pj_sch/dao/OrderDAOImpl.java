package spring.mvc.pj_sch.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.pj_sch.dto.OrderDTO;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override // 전체  주문목록 조회
	public List<OrderDTO> getOrderList(int start, int end) {
		System.out.println("getOrderList() - dao");
		// 주문목록 조회에 사용할 데이터 맵
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.OrderDAO.getOrderList", map);
	}
	
	@Override // 특정고객 주문조회
	public List<OrderDTO> getCustomerOrderList(int start, int end, String customer_id) {
		System.out.println("getCustomerOrderList() - dao");
		// 특정고객 주문목록 조회에 사용할 데이터 맵
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("customer_id", customer_id);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.OrderDAO.getCustomerOrderList", map);
	}

	@Override // 환불 총 개수
	public int getRefundTotal() {
		System.out.println("getRefundTotal() - dao");
		return sqlSession.getMapper(OrderDAO.class).getRefundTotal();
	}
	
	@Override // 주문 상태에 따른 환불목록 조회
	public List<OrderDTO> getRefundList(int start, int end, String order_state) {
		System.out.println("getRefundList() - dao");
		// 특정고객 주문목록 조회에 사용할 데이터 맵
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("order_state", order_state);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.OrderDAO.getRefundList", map);
	}
	
	@Override // 바로구매 후 주문 등록
	public int insertOrder(OrderDTO dto) {
		System.out.println("insertOrder() - dao");
		return sqlSession.insert("spring.mvc.pj_sch.dao.OrderDAO.insertOrder", dto);
	}
	
	@Override // 장바구니 상품 전체 결제 후 주문 등록
	public int insertCartOrder(List<OrderDTO> olist) {
		System.out.println("insertCartOrder() - dao");
		
		int insertResult = 0;
		// 주문데이터를 하나씩 등록
		for (OrderDTO dto : olist) {
			insertResult += sqlSession.insert("spring.mvc.pj_sch.dao.OrderDAO.insertCartOrder", dto);
		}
		return insertResult;
	}

	@Override // 주문 상태변경
	public int updateState(int order_no, String order_state) {
		System.out.println("updateState() - dao");
		// 주문 상태변경에 사용할 데이터 맵
		Map<String, Object> map = new HashMap<>();
		map.put("order_no", order_no);
		map.put("order_state", order_state);
		return sqlSession.update("spring.mvc.pj_sch.dao.OrderDAO.updateState", map);
	}

	@Override // 전체 주문 총 개수
	public int getOrderAllTotal() {
		System.out.println("getOrderAllTotal() - dao");
		return sqlSession.getMapper(OrderDAO.class).getOrderAllTotal();
	}

	@Override // 주문 총 개수
	public int getOrderTotal() {
		System.out.println("getOrderTotal() - dao");
		return sqlSession.getMapper(OrderDAO.class).getOrderTotal();
	}
	
	@Override // 고객 주문 총 개수
	public int getCustomerOrderTotal(String customer_id) {
		System.out.println("getCustomerOrderTotal() - dao");
		return sqlSession.getMapper(OrderDAO.class).getCustomerOrderTotal(customer_id);
	}
}