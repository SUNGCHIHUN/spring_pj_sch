<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 인증 처리</title>
</head>
<body>
	<c:if test="${updateCnt == 0}">
        <script type="text/javascript">
            alert("이메일 인증 필요!!");
        </script>
    </c:if>
    <c:if test="${updateCnt != 0}">
        <script type="text/javascript">
           alert("이메일 인증 성공!!");
           window.location="login.do";
        </script>
    </c:if>
</body>
</html>