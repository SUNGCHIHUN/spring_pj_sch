<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 수량 수정 처리</title>
</head>
<body>
	<%-- 장바구니 수정 성공 --%>
	<c:if test="${updateResult == 1}">
		<c:redirect url="cartList.do"/>
	</c:if>
	
	<%-- 장바구니 수정 실패 --%>
	<c:if test="${updateResult != 1}">
		<script>
			alert("수량을 수정하지 못했습니다.");
			window.history.go(-1);
		</script>
	</c:if>
</body>
</html>