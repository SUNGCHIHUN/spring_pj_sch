<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.board_category} 등록</title>
<link href="${path}/resources/css/common/page.css" rel="stylesheet">
<link href="${path}/resources/css/common/board2.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="container">
		<div id="section">
			<h1 align="center"> ${param.board_category} 등록 </h1>
			<hr>
			<div class="board">
				<form action="${path}/boardAddAction.do?board_category=${param.board_category}&${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<th> 제목 </th>
							<td colspan="2"><input type="text" class="inputTitle" name="title" required></td>
						</tr>
						
						<tr>
							<th>작성자</th>
							<td>${sessionName}</td>
						</tr>
						
						<tr>
							<th>내용</th>
							<td colspan="2">
								<textarea name="contents" cols="120" rows="20" required></textarea>
							</td>
						</tr>
						<tr>
							<th>파일</th>
							<td colspan="2">
								<input type="file" name="b_img" accept="image/*">
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<input type="submit" class="insertBtn" value="등록">
								<input type="button" class="cancelBtn" value="취소" onclick="history.go(-1);">
							</td>	
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>