<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.board_category}</title>
<link href="${path}/resources/css/common/page.css" rel="stylesheet">
<link href="${path}/resources/css/common/board2.css" rel="stylesheet">
<script>
	
	function boardList() {
		window.location='${path}/boardList.do?board_category=${board.board_category}';
	}
	
	function boardDelete() {
		if(confirm('삭제하시겠습니까?')) {
			window.location='${path}/boardDeleteAction.do?board_no=${board.board_no}&board_category=${board.board_category}&pageNum=${param.pageNum}';
		}
	}
	
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="container">
		<div id="section">
			<h1 align="center"> ${board_category} </h1>
			<hr>
			<div class="board">
				<form action="${path}/boardUpdate.do?board_no=${board.board_no}&state=update&" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<table>
						<tr>
							<th>제목</th>
							<td>${board.board_title}</td>
							<th>조회수</th>
							<td>
								<c:if test="${board.board_category == '공지사항'}">
									${board.board_hits}
								</c:if>
								<c:if test="${board.board_category == '문의사항'}">
									-
								</c:if>
							</td>
						</tr>
						
						<tr>
							<th>작성자</th>
							<td>${board.board_writer}</td>
							<th>등록일</th>
							<td>${board.board_regist_day}</td>
						</tr>
						
						<tr style="height: 300px;">
							<th>내용</th>
							<td colspan="3" style="vertical-align: top;">
								<br>
								${board.board_contents}
							</td>
						</tr>
						
						<tr>
							<th>파일</th>
							<%-- 첨부 파일이 있으면 --%>
							<c:if test="${board.board_file_name != 'noFile' && board.board_file_name ne null}">
								<td colspan="3">
									<a href="${board.board_file_name}" download="">
										<%-- 뒤의 이름 부분만 자르기 --%>
										<c:set var="fileName" value="${fn:split(board.board_file_name, '/')}"/>
										${fileName[fn:length(fileName)-1]}
									</a>
								</td>
							</c:if>
						</tr>
						
						<%-- 문의사항인 경우 답변 창 표시 --%>
						<c:if test="${board.board_category == '문의사항'}">
							<%-- 답변이 있는 경우 --%>
							<c:forEach var="reply" items="${rlist}">
								<tr>
									<th>답변</th>
									<td colspan="2">${reply.reply_contents}</td>
									<td style="text-align: right;">
										<ul style="text-align: right;">
											<li>${reply.reply_writer}</li>
											<li>${reply.reply_regist_day}</li>
										</ul>
									</td>
								</tr>
							</c:forEach>
							
						</c:if>
						<tr>
							<td colspan="3" style="text-align: left; padding-left: 20px; border-top: 1px solid lightgray;">
								<input type="button" class="listBtn" value="목록" onclick="boardList()">
							</td>
							
							<%-- 문의사항 게시판인 경우 --%>
							<c:choose>
								<c:when test="${board.board_category == '문의사항'}">
									<%-- 로그인한 유저의 아이디와 게시글 작성한 아이디가 동일하면 --%>	
									<c:if test="${sessionId == board.customer_id}">
										<td colspan="3">
											<input type="submit" class="updateBtn" value="수정">
											<input type="button" class="deleteBtn" value="삭제" onclick="boardDelete()">
										</td>	
									</c:if>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>