<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰등록 처리</title>
</head>
<body>
	<%-- 리뷰등록 성공 --%>
	<c:if test="${insertResult == 1}">
		<script>
			window.location="${path}/productDetail.do?product_no=${product_no}";
		</script>
	</c:if>
	
	<c:if test="${insertResult != null}">
		<script>
			alert("리뷰등록을 실패하였습니다.");
			window.history.go(-1);
		</script>
	</c:if>
</body>
</html>