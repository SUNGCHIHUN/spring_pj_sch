<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>치모 로그인</title>
<link href="${path}/resources/css/common/login.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<c:if test="${errMsg != null}">
      <script type="text/javascript">
         alert("${errMsg}");
      </script>
   </c:if>
	<div id="container">
		<div id="section">
			<div class="login">
				<form action="${path}/loginCheck.do" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<table>
						<tr>
							<th colspan="2"><img src="${path}/resources/images/navImage/logo.png" alt="로고"></th>
						</tr>
						
						<tr>
							<th colspan="2" align="center" style="padding: 10px;">
								CIMO 스토어에 오신 것을 환영합니다!
							</th>
						</tr>
						<tr>
							<td colspan="2"><input type="text" class="inputId" name="id" placeholder="아이디" autofocus required></td>
						</tr>
						<tr>
							<td colspan="2"><input type="password" class="inputPassword" name="password" placeholder="비밀번호" required></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" class="loginBtn" value="로그인"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="button" class="registerBtn" value="회원가입" onclick="location.href='${path}/register.do'"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>