<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 처리</title>
<script src="${path}/resources/js/customerJS/errorMsg.js"></script>
</head>
<body>
	<%-- 탈퇴 성공이면 --%>
	<c:if test="${deleteResult == 1}">
		<c:remove var="sessionId" scope="session" />
		<c:remove var="authResult" scope="session" />
		<script>
			alert("회원탈퇴되었습니다.");
			window.location="${path}/login.do";
		</script>
	</c:if>
	<%-- 탈퇴 실패이면 --%>
	<c:if test="${deleteResult != 1}">
		<script>
			errorAlert(deleteError);
			history.go(-1);
		</script>
	</c:if>
</body>
</html>