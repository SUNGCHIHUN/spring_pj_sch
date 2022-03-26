<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ko_category} 수정</title>
<link href="${path}/resources/css/common/board2.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="container">
		<div id="section">
			<h1 align="center"> ${ko_category} 수정 </h1>
			<hr>
			<div class="board">
				<form action="${path}/boardUpdateAction.do?board_no=${board.board_no}?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
					<input type="hidden" name="hidden_img_name" value="${board.board_file_name}">
					<table>
						<tr>
							<th> 제목 </th>
							<td colspan="2"><input type="text" name="title" size="50" value="${board.board_title}"></td>
						</tr>
						
						<tr>
							<th>작성자</th>
							<td>${board.board_writer}</td>
						</tr>
						
						<tr>
							<th>내용</th>
							<td colspan="2">
								<textarea name="contents" cols="120" rows="20">${board.board_contents}</textarea>
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
								<input type="submit" value="수정">
								<input type="button" value="취소" onclick="history.go(-1);">
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