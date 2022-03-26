<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 관리자 - 회원조회 </title>
<link rel="stylesheet" href="${path}/resources/css/manager/main.css">
<link rel="stylesheet" href="${path}/resources/css/manager/board_list.css">
<link rel="stylesheet" href="${path}/resources/css/manager/page.css">
<script>

	function deleteCustomer(id) {
		if (confirm('탈퇴처리하겠습니까?')) {
			window.location='${path}/customerDeleteAction.ad?customer_id=' + id + '&pageNum=${paging.pageNum}';
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
			<h1>회원관리</h1>
		</div>
		
		<div id="contents">
			<!-- 왼쪽 메뉴 -->
			<%@ include file="/WEB-INF/views/manager/common/left_menu.jsp" %>
			<div id="section">
				<table>
					<tr>
						<th width="5%">번호</th>
						<th width="10%">아이디</th>
						<th width="5%">이름</th>
						<th width="7%">우편번호</th>
						<th width="10%">상세주소</th>
						<th width="10%">핸드폰</th>
						<th width="10%">이메일</th>
						<th width="10%">가입일</th>
						<th width="10%">탈퇴일</th>
						<th width="5%">관리</th>
					</tr>
					<c:forEach var="c" items="${clist}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${c.customer_id}</td>
							<td>${c.customer_name}</td>
							<td>${c.zipcode}</td>
							<td>${c.customer_address}</td>
							<td>${c.customer_tel}</td>
							<td>${c.customer_email}</td>
							<td>${c.customer_regist_day}</td>
							<td><span style="color: red;">${c.customer_delete_day}</span></td>
							<td id="btn_td">
								<%-- 탈퇴하지 않은 경우 --%>
								<c:if test="${c.customer_state eq 'register'}">
									<input type="button" name="btnDelete" class="deleteBtn" value="탈퇴" onclick="deleteCustomer('${c.customer_id}')">
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<%-- 페이징 처리 --%>
					<tr>
						<td colspan="9" align="center" style="border-bottom: none;">
							<%-- 이전버튼 활성화 여부 --%>
							<c:if test="${paging.startPage > 10}">
								<a href="${path}/customerList.ad?pageNum=${paging.prev}">[이전]</a>
							</c:if>
							
							<%-- 페이지 번호 처리 --%>
							<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
								<%-- 현재 페이지 번호는 눌리지 않게 처리 --%>
								<c:if test="${num == paging.currentPage}">
									<span style="font-weight: bold;">${num}</span>
								</c:if>
								
								<c:if test="${num != paging.currentPage}">
									<a href="${path}/customerList.ad?pageNum=${num}">${num}</a>
								</c:if>
								
							</c:forEach>
							
							<%-- 다음버튼 활성화 여부 --%>
							<c:if test="${paging.endPage < paging.pageCount}">
								<a href="${path}/customerList.ad?pageNum=${paging.next}">[다음]</a>
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