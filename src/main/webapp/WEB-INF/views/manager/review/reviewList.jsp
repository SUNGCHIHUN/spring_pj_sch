<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 리뷰 관리</title>
<link rel="stylesheet" href="${path}/resources/css/manager/main.css">
<link rel="stylesheet" href="${path}/resources/css/manager/board_list.css">
<link rel="stylesheet" href="${path}/resources/css/manager/page.css">
<script>
	function deleteReview(no) {
		if (confirm('삭제하시겠습니까?')) {
			window.location='${path}/reviewDeleteAction.ad?review_no=' + no + '&pageNum=${paging.currentPage}';
		}
	}
</script>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
	<div id="container">
		<!-- 메뉴이름 -->
		<div id="title">
			<h1 align="center"> 리뷰관리 </h1>
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
							<th>상품번호</th>
							<th>리뷰내용</th>
							<th>작성자</th>
							<th>별점</th>
							<th>등록일</th>
							<th>관리</th>
						</tr>
						<%-- 게시글이 없으면 --%>
						<c:if test="${rlist.isEmpty()}">
							<tr>
								<td colspan="6">
									<div class="discription">
										<h2>현재 게시글이 존재하지 않습니다.</h2>
									</div>
								</td>
							</tr>
						</c:if>
						<c:forEach var="review" items="${rlist}">
							
							<tr>
								<td>${review.review_no}</td>
								<td>[<a href="${path}/stockDetail.st?product_no=${review.product_no}&pageNum=${paging.currentPage}">${review.product_no}</a>]</td>
								<td>${review.review_contents}</td>
								<td>${review.review_writer}</td>
								<td>
									<c:choose>
										<c:when test="${review.review_star == 5}"> ★★★★★ </c:when>
										<c:when test="${review.review_star == 4}"> ★★★★☆ </c:when>
										<c:when test="${review.review_star == 3}"> ★★★☆☆  </c:when>
										<c:when test="${review.review_star == 2}"> ★★☆☆☆ </c:when>
										<c:when test="${review.review_star == 1}"> ★☆☆☆☆ </c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</td>
								<td>${review.review_regist_day}</td>
								<td>
									<input type="button" class="deleteBtn" value="삭제" onclick="deleteReview('${review.review_no}')">
								</td>
							</tr>
						</c:forEach>

						<%-- 페이징 처리 --%>
						<tr>
							<td colspan="8" align="center" style="border-bottom: none;">
								<%-- 이전버튼 활성화 여부 --%>
								<c:if test="${paging.startPage > 10}">
									<a href="${path}/reviewList.ad?pageNum=${paging.prev}">[이전]</a>
								</c:if>
								
								<%-- 페이지 번호 처리 --%>
								<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
									<%-- 현재 페이지 번호는 눌리지 않게 처리 --%>
									<c:if test="${num == paging.currentPage}">
										<span style="font-weight: bold;">${num}</span>
									</c:if>
									
									<c:if test="${num != paging.currentPage}">
										<a href="${path}/reviewList.ad?pageNum=${num}">${num}</a>
									</c:if>
									
								</c:forEach>
								
								<%-- 다음버튼 활성화 여부 --%>
								<c:if test="${paging.endPage < paging.pageCount}">
									<a href="${path}/reviewList.ad?pageNum=${paging.next}">[다음]</a>
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