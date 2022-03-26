<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 삭제 처리</title>
</head>
<body>
	<%-- 장바구니 삭제 성공 --%>
	<c:if test="${deleteResult >= 1}">
		<c:redirect url="cartList.do"/>
	</c:if>
	
	<%-- 장바구니 삭제 실패 --%>
	<c:if test="${deleteResult < 1}">
		<script>
			alert("상품을 삭제하지 못했습니다.");
			window.history.go(-1);
		</script>
	</c:if>
</body>
</html>