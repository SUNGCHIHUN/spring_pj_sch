<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 관리자 - 주문목록 </title>
<link rel="stylesheet" href="${path}/resources/css/manager/main.css">
<link rel="stylesheet" href="${path}/resources/css/manager/board_list.css">
<link rel="stylesheet" href="${path}/resources/css/manager/page.css">

<script>

	function confirmOrder(order_no, product_no, order_amount) {
		if (confirm('승인하시겠습니까?')) {
			window.location='${path}/orderConfirmAction.ad?order_no=' + order_no + '&product_no=' + product_no + '&order_amount=' + order_amount + '&pageNum=${paging.pageNum}';
		}	}

	function cancelOrder(no) {
		if (confirm('취소하시겠습니까?')) {
			window.location='${path}/orderCancelAction.ad?order_no=' + order_no + '&pageNum=${paging.pageNum}';
		}
	}
	
</script>

</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
	<div id="container">
		<!-- 메뉴이름 -->
		<div id="title">
			<h1>주문관리</h1>
		</div>
		
		<div id="contents">
			<!-- 왼쪽 메뉴 -->
			<%@ include file="/WEB-INF/views/manager/common/left_menu.jsp" %>
			<div id="section">
				<table>
					<tr>
						<th width="10%">주문일자</th>
						<th colspan="2" width="35%">상품정보</th>
						<th width="10%">가격</th>
						<th width="5%">수량</th>
						<th width="10%">합계</th>
						<th width="10%">구매자</th>
						<th width="10%">상태</th>
						<th width="10%">관리</th>
					</tr>
					<c:forEach var="o" items="${olist}" varStatus="status">
						<tr id="olist_tr${status.index}">
							<td>${o.order_day}</td>
							<td><img src="${o.product_img_name}" alt="상품이미지" width="80px" height="80px"></td>
							<td>${o.product_name}</td>
							<td><fmt:formatNumber value="${o.product_price}" pattern="#,### 원" /></td>
							<td>${o.order_amount}</td>
							<td><fmt:formatNumber value="${o.product_price * o.order_amount}" pattern="#,### 원" /></td>
							<td>${o.customer_id}</td>
							<td>${o.order_state}</td>
							<td id="btn_td">
								<c:if test="${o.order_state eq '결제대기' }">
									<input type="button" name="btnConfirm" class="confirmBtn" value="승인" onclick="confirmOrder(${o.order_no}, ${o.product_no}, ${o.order_amount})"> 
									<input type="button" name="btnCancel" class="cancelBtn" value="취소" onclick="cancelOrder(${o.order_no})">
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<%-- 페이징 처리 --%>
					<tr>
						<td colspan="9" align="center" style="border-bottom: none;">
							<%-- 이전버튼 활성화 여부 --%>
							<c:if test="${paging.startPage > 10}">
								<a href="${path}/orderList.ad?pageNum=${paging.prev}">[이전]</a>
							</c:if>
							
							<%-- 페이지 번호 처리 --%>
							<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
								<%-- 현재 페이지 번호는 눌리지 않게 처리 --%>
								<c:if test="${num == paging.currentPage}">
									<span style="font-weight: bold;">${num}</span>
								</c:if>
								
								<c:if test="${num != paging.currentPage}">
									<a href="${path}/orderList.ad?pageNum=${num}">${num}</a>
								</c:if>
							</c:forEach>
							
							<%-- 다음버튼 활성화 여부 --%>
							<c:if test="${paging.endPage < paging.pageCount}">
								<a href="${path}/orderList.ad?pageNum=${paging.next}">[다음]</a>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/manager/common/footer.jsp" %>
</body>
</html>