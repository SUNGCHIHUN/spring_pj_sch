<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정 처리</title>
</head>
<body>
	<%-- 게시글 수정 성공 --%>
	<c:if test="${updateResult == 1}">
		<script>
			alert("게시글이 수정되었습니다.");
			window.location='${path}/boardDetail.do?board_no=${param.board_no}';
		</script>
	</c:if>
	<%-- 게시글 수정 실패 --%>
	<c:if test="${updateResult == 0}">
		<script>
			alert("게시글 수정을 실패하였습니다.");
			window.history.go(-1);
		</script>
	</c:if>
</body>
</html>