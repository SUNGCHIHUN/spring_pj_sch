package spring.mvc.pj_sch.dao;

import java.util.List;

import spring.mvc.pj_sch.dto.OrderDTO;

public interface OrderDAO {

	// 전체 주문목록 조회
	List<OrderDTO> getOrderList(int start, int end);
	
	// 특정고객 주문조회
	List<OrderDTO> getCustomerOrderList(int start, int end, String customer_id);
	
	// 환불 총 개수
	int getRefundTotal();
	
	// 주문 상태에 따른 환불목록 조회
	List<OrderDTO> getRefundList(int start, int end, String order_state);
	
	// 바로구매 주문 등록
	int insertOrder(OrderDTO dto);
	
	// 장바구니 전체 결제 후 주문 등록
	int insertCartOrder(List<OrderDTO> olist);

	// 상태 변경
	int updateState(int order_no, String state);
	
	// 전체 주문 총 개수
	int getOrderAllTotal();

	// 주문 총 개수
	int getOrderTotal();
	
	// 고객 주문 총 개수
	int getCustomerOrderTotal(String customer_id);
}