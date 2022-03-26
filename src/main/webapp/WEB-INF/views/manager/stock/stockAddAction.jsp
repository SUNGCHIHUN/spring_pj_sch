<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록 처리</title>
</head>
<body>
	<%-- 상품등록 성공 --%>
	<c:if test="${insertResult != 0}">
		<script>
			alert("상품이 등록되었습니다!");
			window.location="${path}/stockList.st";
		</script>
	</c:if>

	<%-- 상품등록 실패 --%>
	<c:if test="${insertResult == 0}">
		<script>
			alert("상품등록 실패");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>