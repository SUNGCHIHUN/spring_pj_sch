<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - ${ko_category} 글쓰기</title>
<link rel="stylesheet" href="${path}/resources/css/manager/main.css">
<link rel="stylesheet" href="${path}/resources/css/manager/board_detail.css">
<link rel="stylesheet" href="${path}/resources/css/manager/page.css">

<script>
	
</script>

</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
	<div id="container">
		<!-- 메뉴이름 -->
		<div id="title">
			<h1 align="center"> ${board_category} </h1>
		</div>
		
		<div id="contents">
			<!-- 왼쪽 메뉴 -->
			<%@ include file="/WEB-INF/views/manager/common/left_menu.jsp" %>
			<div id="section">
				<div class="board">
					<form action="${path}/boardUpdateAction.ad?board_no=${board.board_no}&pageNum=${pageNum}&${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
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
									<input type="hidden" name="hidden_board_img" value="${board.board_file_name}">
									<input type="file" name="board_img" value="파일명">
								</td>
							</tr>
								
							<tr>
								<td colspan="3" style="text-align: right; border-bottom: none;">
									<input type="submit" class="updateBtn" value="수정">
									<input type="button" class="cancelBtn" value="취소" onclick="history.go(-1);">
								</td>	
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/manager/common/footer.jsp" %>
</body>
</html>