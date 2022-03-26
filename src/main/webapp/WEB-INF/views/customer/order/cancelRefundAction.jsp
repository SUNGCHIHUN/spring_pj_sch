<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환불 취소 처리</title>
</head>
<body>
	<%-- 주문취소 성공 --%>
	<c:if test="${updateResult == 1}">
		<script>
			alert("환불이 취소되었습니다.");
			location.href="${path}/customerOrderList.do";		
		</script>
	</c:if>
	
	<%-- 주문취소 실패 --%>
	<c:if test="${updateResult != 1}">
		<script>
			alert("환불취소 실패!");
			history.go(-1);
		</script>
	</c:if>

</body>
</html>