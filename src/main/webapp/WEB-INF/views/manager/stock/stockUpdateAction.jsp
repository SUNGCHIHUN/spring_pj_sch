<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정 처리</title>
</head>
<body>
	<%-- 상품수정 성공 --%>
	<c:if test="${updateResult != 0}">
		<script>
			alert("상품이 수정되었습니다!");
			window.location="${path}/stockList.st";
		</script>
	</c:if>

	<%-- 상품수정 실패 --%>
	<c:if test="${updateResult == 0}">
		<script>
			alert("상품수정 실패");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>