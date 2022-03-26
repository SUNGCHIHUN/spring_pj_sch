<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 등록 처리</title>
</head>
<body>
	<%-- 장바구니 등록 성공 --%>
	<c:if test="${insertResult != 0 || updateResult != 0}">
		<script>
			alert("장바구니에 등록되었습니다.");
			window.location="${path}/productDetail.do?product_no=${product_no}";
		</script>
	</c:if>
	
	<%-- 장바구니 등록 실패 --%>
	<c:if test="${insertResult == 0 || updateResult == 0}">
		<script>
			alert("장바구니에 등록을 하지 못했습니다.");
			window.history.go(-1);
		</script>
	</c:if>
</body>
</html>