<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 삭제 처리</title>
</head>
<body>
	<%-- 리뷰삭제 성공 --%>
	<c:if test="${deleteResult == 1}">
		<script>
			window.location='${path}/reviewList.ad?pageNum=${pageNum}';
		</script>
	</c:if>
	
	<%-- 리뷰삭제 실패 --%>
	<c:if test="${deleteResult == 0}">
		<script>
			alert("리뷰 삭제 실패");
			window.history.go(-1);
		</script>
	</c:if>
</body>
</html>