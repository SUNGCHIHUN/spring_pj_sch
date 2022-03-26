<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>치모 - 상품목록</title>
<link rel="stylesheet" href="${path}/resources/css/manager/main.css">
<link rel="stylesheet" href="${path}/resources/css/manager/board_list.css">
<link rel="stylesheet" href="${path}/resources/css/manager/page.css">

<script>

	function detailProduct(no) {
		window.location='${path}/stockDetail.st?product_no=' + no + '&pageNum=${paging.pageNum}';
	}

	function deleteProduct(no) {
		if (confirm('삭제하시겠습니까?')) {
			window.location='${path}/stockDeleteAction.st?product_no=' + no + '&pageNum=${paging.pageNum}';
		}
	}

</script>

</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
	<!-- UserLoginSuccessHandler 에서 msg 넘김 -->
    <c:if test="${msg != null}">
       <script type="text/javascript">
          alert("${msg}");
       </script>
    </c:if>
	<div id="container">
		<!-- 메뉴이름 -->
		<div id="title">
			<h1>상품목록</h1>
		</div>
		
		<div id="contents">
			<!-- 왼쪽 메뉴 -->
			<%@ include file="/WEB-INF/views/manager/common/left_menu.jsp" %>
			<div id="section">
				<table>
					<tr>
						<th width="5%">번호</th>
						<th colspan="2" width="35%">상품정보</th>
						<th width="10%">상품가격</th>
						<th width="10%">재고</th>
						<th width="10%">카테고리</th>
						<th width="10%">상태</th>
						<th width="10%">관리</th>
					</tr>
					<c:forEach var="p" items="${plist}" varStatus="status">
						<tr id="plist_tr${status.index}">
							<td>${p.product_no}</td>
							<td><img src="${p.product_img_name}" alt="상품이미지" width="80px" height="80px"></td>
							<td>${p.product_name}</td>
							<td><fmt:formatNumber value="${p.product_price}" pattern="#,### 원" /></td>
							<td>${p.product_amount}</td>
							<td>
								<c:choose>
									<c:when test="${p.product_category eq 'energy'}">드링크</c:when>
									<c:when test="${p.product_category eq 'carbon'}">탄산</c:when>
									<c:when test="${p.product_category eq 'water'}">생수</c:when>
									<c:when test="${p.product_category eq 'coffee'}">커피</c:when>
									<c:otherwise> 없음</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${p.product_state eq 'sale'}">판매중</c:when>
									<c:when test="${p.product_state eq 'soldout'}">품절</c:when>
									<c:otherwise> 없음 </c:otherwise>
								</c:choose>
							</td>
							<td id="btn_td">
								<input type="button" name="btnUpdate" class="updateBtn" value="수정" onclick="detailProduct(${p.product_no})";> 
								<input type="button" name="btnDelete" class="deleteBtn" value="삭제" onclick="deleteProduct(${p.product_no})">
							</td>
						</tr>
					</c:forEach>
					<%-- 페이징 처리 --%>
					<tr>
						<td colspan="8" align="center" style="border-bottom: none;">
							<%-- 이전버튼 활성화 여부 --%>
							<c:if test="${paging.startPage > 10}">
								<a href="${path}/stockList.st?pageNum=${paging.prev}">[이전]</a>
							</c:if>
							
							<%-- 페이지 번호 처리 --%>
							<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
								<%-- 현재 페이지 번호는 눌리지 않게 처리 --%>
								<c:if test="${num == paging.currentPage}">
									<span style="font-weight: bold;">${num}</span>
								</c:if>
								
								<c:if test="${num != paging.currentPage}">
									<a href="${path}/stockList.st?pageNum=${num}">${num}</a>
								</c:if>
								
							</c:forEach>
							
							<%-- 다음버튼 활성화 여부 --%>
							<c:if test="${paging.endPage < paging.pageCount}">
								<a href="${path}/stockList.st?pageNum=${paging.next}">[다음]</a>
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