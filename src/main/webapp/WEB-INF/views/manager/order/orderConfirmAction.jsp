<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문승인 처리</title>
</head>
<body>
	<%-- 주문승인 성공 --%>
	<c:if test="${updateResult != 0}">
		<script>
			window.location="${path}/orderList.ad?pageNum=${pageNum}";
		</script>
	</c:if>

	<%-- 주문승인 실패 --%>
	<c:if test="${updateResult == 0}">
		<script>
			alert("주문승인 실패");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>