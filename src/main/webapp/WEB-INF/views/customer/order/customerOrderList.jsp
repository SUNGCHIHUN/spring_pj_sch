<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>치모 주문목록</title>
<link href="${path}/resources/css/common/page.css" rel="stylesheet">
<link href="${path}/resources/css/customer/order.css" rel="stylesheet">
<script>

	// 주문 취소
	function orderCancel(order_no) {
		if(confirm("주문을 취소하시겠습니까?")) {
			location.href="${path}/cancelOrderAction.do?order_no=" + order_no;
		}		
	}
	
	// 배송 상세조회
	function deliveryDetail(billing_number) {
		window.open('${path}/deliveryDetail.do?billing_number=' + billing_number, '_blank', 'width=650,height=600');
	}
	
	// 환불 요청
	function refund(order_no) {
		if(confirm("환불을 요청하시겠습니까?")) {
			location.href="${path}/refundAction.do?order_no=" + order_no;
		}		
	}

	// 환불 취소
	function refundCancel(order_no) {
		if(confirm("환불을 취소하시겠습니까?[이후 환불요청 불가]")) {
			location.href="${path}/cancelRefundAction.do?order_no=" + order_no;
		}		
	}

</script>

</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="container">
		<div id="section1">
			<div class="section_menu">
				<ul>
					<li><a href="${path}/customerDetail.do">회원정보</a></li>
					<li><a href="${path}/customerOrderList.do"><b>주문내역</b></a></li>
				</ul>
			</div>
		</div>	
		<div id="section2">
			<h3 style="text-align:center;">주문리스트</h3>
			<div class="order_list">
				<table>
					<tr>
						<th>
							주문일자
						</th>
						<th>이미지</th>
						<th>상품정보</th>
						<th>수량</th>
						<th>상품구매금액</th>
						<th>합계</th>
						<th>주문상태</th>
					</tr>
					<c:forEach var="order" items="${olist}">
						<%-- 주문 내역이 없는 경우 --%>
						<c:if test="${olist.size() == 0}">
							<tr>
								<td colspan="8" style="height: 200px;">
									구매내역이 존재하지 않습니다.
								</td>
							</tr>
						</c:if>

						<%-- 주문 내역이 없는 경우 --%>
						<c:if test="${olist.size() != 0}">
							<tr>
								<td>
									${order.order_day}
								</td>
								<td><a href="${path}/productDetail.do?product_no=${order.product_no}"><img src="${order.product_img_name}" alt="상품이미지"></a></td>
								<td>${order.product_name}</td>	
								<td>${order.order_amount}</td>
								<td>
									<fmt:formatNumber value="${order.product_price}" pattern="#,### 원" />
								</td>
								<td>
									<fmt:formatNumber value="${order.product_price * order.order_amount}" pattern="#,### 원" />
								</td>
								<td>
									<c:choose>
										<c:when test="${order.order_state == '결제대기'}">
											<input type="button" value="취소" class="cancelBtn" onclick="orderCancel('${order.order_no}')"><br>
											구매요청
										</c:when>
										<c:when test="${order.order_state == '결제승인'}">
											<input type="button" value="환불" class="refundBtn" onclick="refund('${order.order_no}')"><br>
											구매완료
										</c:when>
										<c:when test="${order.order_state == '환불요청'}">
											<input type="button" value="취소" class="cancelBtn" onclick="refundCancel('${order.order_no}')"><br>
											환불요청
										</c:when>
										<c:otherwise> ${order.order_state}</c:otherwise>
									</c:choose>
									
									
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
			<%-- 페이징 처리 --%>
			<div id="page">
				<%-- 이전버튼 활성화 여부 --%>
				<c:if test="${paging.startPage > 10}">
					<a href="${path}/orderList.do?pageNum=${paging.prev}">[이전]</a>
				</c:if>
				
				<%-- 페이지 번호 처리 --%>
				<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
					<%-- 현재 페이지 번호는 눌리지 않게 처리 --%>
					<c:if test="${num == paging.currentPage}">
						<span style="font-weight: bold;">${num}</span>
					</c:if>
					
					<c:if test="${num != paging.currentPage}">
						<a href="${path}/orderList.do?pageNum=${num}">${num}</a>
					</c:if>
				</c:forEach>
				
				<%-- 다음버튼 활성화 여부 --%>
				<c:if test="${paging.endPage < paging.pageCount}">
					<a href="${path}/orderList.do?pageNum=${paging.next}">[다음]</a>
				</c:if>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>