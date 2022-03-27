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
			<h1 align="center"> ${ko_category} 글쓰기 </h1>
		</div>
		
		<div id="contents">
			<!-- 왼쪽 메뉴 -->
			<%@ include file="/WEB-INF/views/manager/common/left_menu.jsp" %>
			<div id="section">
				<div class="board">
					<form action="${path}/boardAddAction.ad?board_category=${param.board_category}&${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
						<table>
							<tr>
								<th> 제목 </th>
								<td colspan="2" style="padding-left: 20px;">
									<input type="text" class="inputTitle" size="127" name="title" required>
								</td>
							</tr>
							
							<tr>
								<th>작성자</th>
								<td style="padding-left: 20px;">${sessionName}</td>
							</tr>
							
							<tr>
								<th>내용</th>
								<td colspan="2" style="padding-left: 20px;">
									<textarea name="contents" cols="120" rows="20" required></textarea>
								</td>
							</tr>
							<tr>
								<th>파일</th>
								<td colspan="2" style="padding-left: 20px;">
									<input type="file" name="board_img" accept="image/*,.txt">
								</td>
							</tr>
							<tr>
								<td colspan="3" style="text-align: right; border-bottom: none;">
									<input type="submit" class="insertBtn" value="등록">
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