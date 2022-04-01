<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 -  ${board_category}</title>
<link rel="stylesheet" href="${path}/resources/css/manager/main.css">
<link rel="stylesheet" href="${path}/resources/css/manager/board_list.css">
<link rel="stylesheet" href="${path}/resources/css/manager/page.css">
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
	<div id="container">
		<!-- 메뉴이름 -->
		<div id="title">
			<h1 align="center">  ${board_category} </h1>
		</div>
		
		<div id="contents">
			<!-- 왼쪽 메뉴 -->
			<%@ include file="/WEB-INF/views/manager/common/left_menu.jsp" %>
			<div id="section">
				<input type="hidden" name="pageNum" value="${paging.currentPage}">
				<div class="board">	
					<table>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>파일</th>
							<th>작성자</th>
							<th>등록일</th>
							<%-- 공지사항인 경우 조회수 표시 --%>
							<c:if test="${board_category == '공지사항'}">
								<th>조회수</th>							
							</c:if>
							<%-- 문의사항인 경우 답변상태 표시 --%>
							<c:if test="${board_category == '문의사항'}">
								<th>문의상태</th>
							</c:if>
						</tr>
						<c:forEach var="board" items="${blist}" varStatus="status">
							<%-- 게시글이 없으면 --%>
							<c:if test="${blist.isEmpty()}">
								<tr>
									<td colspan="6">
										<div class="discription">
											<h2>현재 게시글이 존재하지 않습니다.</h2>
										</div>
									</td>
								</tr>
							</c:if>
							<tr>
								<td>${board.board_no}</td>
								<td><a href="${path}/boardDetail.ad?board_no=${board.board_no}&pageNum=${paging.currentPage}">${board.board_title}</a></td>
								<td>
									<%-- 파일이 있는 경우 --%>
									<c:if test="${board.board_file_name != null && board.board_file_name != 'noFile'}">
										<img alt="#" width="20px" height="20px">
									</c:if>
									<%-- 파일이 없는 경우 --%>
									<c:if test="${board.board_file_name == null || board.board_file_name == 'noFile'}">
										--
									</c:if>
								</td>
								<td>${board.board_writer}</td>
								<td>${board.board_regist_day}</td>
								<%-- 공지사항인 경우 조회수 표시 --%>
								<c:if test="${board.board_category == '공지사항'}">
									<td>${board.board_hits}</td>							
								</c:if>
								<%-- 문의사항인 경우 문의상태 표시 --%>
								<c:if test="${board.board_category == '문의사항'}">
									<td>${board.board_state}</td>
								</c:if>
							</tr>
						</c:forEach>
						<%-- 공지사항인 경우 글쓰기 버튼 추가 --%>
						<c:if test="${board_category eq '공지사항'}">
							<tr>
								<td colspan="8" align="left" style="border-bottom: none;">
									<input type="button" class="btnWrite" value="글쓰기" onclick='window.location="${path}/boardAdd.ad?board_category=${board_category}"'>
								</td>
							</tr>
						</c:if>
						<%-- 페이징 처리 --%>
						<tr>
							<td colspan="8" align="center" style="border-bottom: none;">
								<%-- 이전버튼 활성화 여부 --%>
								<c:if test="${paging.startPage > 10}">
									<a href="${path}/boardList.ad?board_category=${board_category}&pageNum=${paging.prev}">[이전]</a>
								</c:if>
								
								<%-- 페이지 번호 처리 --%>
								<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
									<%-- 현재 페이지 번호는 눌리지 않게 처리 --%>
									<c:if test="${num == paging.currentPage}">
										<span style="font-weight: bold;">${num}</span>
									</c:if>
									
									<c:if test="${num != paging.currentPage}">
										<a href="${path}/boardList.ad?board_category=${board_category}&pageNum=${num}">${num}</a>
									</c:if>
									
								</c:forEach>
								
								<%-- 다음버튼 활성화 여부 --%>
								<c:if test="${paging.endPage < paging.pageCount}">
									<a href="${path}/boardList.ad?board_category=${board_category}&pageNum=${paging.next}">[다음]</a>
								</c:if>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/manager/common/footer.jsp" %>
</body>
</html>