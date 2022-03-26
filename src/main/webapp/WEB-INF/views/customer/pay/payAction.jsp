<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매 처리</title>
</head>
<body>
	<%-- 주문을 등록 성공하면 --%>
	<c:if test="${insertResult >= 1}">
		<script>
			alert("${insertResult}건 결제 성공!");
			location.href="${path}/customerOrderList.do";
		</script>
	</c:if>

	<%-- 주문 등록에 실패하면 --%>
	<c:if test="${insertResult < 1}">
		<script>
			alert("결제 실패!");
			history.go(-1);
		</script>
	</c:if>
	
</body>
</html>