<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
<script src="${path}/resources/js/customer/register.js" defer></script>
</head>
<body>
	<h2> 아이디 중복 확인</h2>
	<form action="${path}/confirmIdAction.do" name="confirm_form" method="post"
				onsubmit="return confirmIdCheck();">
		
		<%-- 아이디가 중복된 경우 --%>
		<c:if test="${dupChk == 1}">
			<table>
				<tr>
					<th colspan="2">'${strId}'는 사용할 수 없습니다.</th>
				</tr>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="확인">
						<input type="reset" value="취소" onclick="self.close();">
					</th>
				</tr>
			</table>
		</c:if>
		
		<%-- 아이디가 중복되지 않은 경우 --%>
		<c:if test="${dupChk != 1}">
			<table>
				<tr>
					<th colspan="2">'${strId}'는 사용 가능합니다.</th>
				</tr>
				<tr>
					<th colspan="2">
						<input type="button" value="확인" onclick="setId('${strId}')">
					</th>
				</tr>
			</table>
		</c:if>
	</form>
</body>
</html>