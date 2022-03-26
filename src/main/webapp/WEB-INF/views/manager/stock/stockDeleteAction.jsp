<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품삭제 처리</title>
</head>
<body>
	<%-- 상품삭제 성공 --%>
	<c:if test="${deleteResult != 0}">
		<script>
			alert("상품이 삭제되었습니다!");
			window.location="${path}/stockList.st";
		</script>
	</c:if>

	<%-- 상품삭제 실패 --%>
	<c:if test="${deleteResult == 0}">
		<script>
			alert("상품삭제 실패");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>