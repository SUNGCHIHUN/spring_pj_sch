<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록 처리</title>
</head>
<body>
	<%-- 게시글 등록 성공 --%>
	<c:if test="${insertResult == 1}">
		<script>
			alert("게시글이 등록되었습니다.");
			window.location='${path}/boardList.ad?board_category=${board_category}';
		</script>
	</c:if>
	<%-- 게시글 등록 실패 --%>
	<c:if test="${insertResult == 0}">
		<script>
			alert("게시글 등록을 실패하였습니다.");
			window.history.go(-1);
		</script>
	</c:if>
</body>
</html>