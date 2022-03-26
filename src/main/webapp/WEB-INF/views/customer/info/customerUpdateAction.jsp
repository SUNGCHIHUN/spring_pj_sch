<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정 처리</title>
<script src="${path}/resources/js/common/alert_error.js"></script>
</head>
<body>
	<%-- 회원수정 성공 --%>
	<c:if test="${updateResult == 1}">
		<%-- 회원정보가 수정되었기 때문에 세션을 해제하여 다시 인증 요청 --%>
		<c:remove var="authResult" scope="session" />
		<script>
			alert("회원정보가 수정되었습니다.");
			window.location="${path}/customerAuth.do";
		</script>
	</c:if>

	<%-- 회원수정 실패 --%>
	<c:if test="${updateResult != 1}">
		<script>
			errorAlert(updateError);
			window.history.back();
		</script>
	</c:if>
</body>
</html>