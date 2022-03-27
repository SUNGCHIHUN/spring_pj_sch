<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>치모 문의 간편등록</title>
<link href="${path}/resources/css/common/board2.css" rel="stylesheet">
<script>

	//입력체크
	function checkContent() {
		if ($('.inputTitle2').val() == '') {
			alert('제목을 입력하세요.');
			$('.inputTitle2').focus();
			return false;
			
		} else if ($('.contents').val() == '') {
			alert('내용을 입력하세요.');
			$('.contents').focus();
			return false;
		}
	}
</script>
</head>
<body>
	<div id="container">
		<div id="section">
			<h1 align="center"> 문의사항 등록 </h1>
			<hr>
			<div class="board">
				<form action="${path}/askSimpleAddAction.do?board_category='문의사항'" onsubmit="return checkContent();" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<table style="width:600px;">
						<tr>
							<th> 제목 </th>
							<td colspan="2"><input type="text" class="inputTitle2" name="title"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${sessionName}</td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan="2">
								<textarea class="contents" name="contents" cols="66" rows="20"></textarea>
							</td>
						</tr>
						<tr style="text-decoration: none;">
							<td colspan="3">
								<input type="submit" class="insertBtn" value="등록"> &nbsp;
								<input type="button" class="cancelBtn" value="취소" onclick="window.close();">
							</td>	
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>