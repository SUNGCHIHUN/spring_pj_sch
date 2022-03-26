<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 전체 삭제처리</title>
</head>
<body>
	<%-- 장바구니 전체삭제 성공 --%>
	<c:if test="${deleteResult != 0}">
		<c:redirect url="cartList.do"/>
	</c:if>
	
	<%-- 장바구니 전체삭제 실패 --%>
	<c:if test="${deleteResult == 0}">
		<script>
			alert("장바구니 전체 삭제 실패!");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>