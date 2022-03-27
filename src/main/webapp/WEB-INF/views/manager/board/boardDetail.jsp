<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - ${board.board_category}</title>
<link rel="stylesheet" href="${path}/resources/css/manager/main.css">
<link rel="stylesheet" href="${path}/resources/css/manager/board_detail.css">
<link rel="stylesheet" href="${path}/resources/css/manager/page.css">

<script>
	$(function() {
		replyList();
	});

	// 답변 조회
	function replyList() {
		$.ajax({
			type:'POST',
			url:'${path}/replyList.ad?${_csrf.parameterName}=${_csrf.token}',
			data: 'board_no=${board.board_no}',
			success: function(result) {
				console.log(result);
				
				$('.reply').html(result);
			},
			error: function() {
				alert('답변 조회 error 발생!');
			}
		});
	}

	// 답변 등록
	function replyAdd() {
		
		// 답변을 적지 않은 경우
		if ($('#replyContents').val() == '') {
			alert('내용을 입력하세요.');
			return false;
		}
		
		var param = {
				'board_no': '${board.board_no}',
				'reply_writer': '${sessionName}',
				'reply_contents': $('#replyContents').val()
		};
		
		$.ajax({
			type:'POST',
			url:'${path}/replyAddAction.ad?${_csrf.parameterName}=${_csrf.token}',
			data: param,
			success: function() {
				$('#replyContents').val('');
				
				replyList();
			},
			error: function() {
				alert('답변 조회 error 발생!');
			}
		});
	}
	
	// 답변 삭제
	function replyDelete(no) {
		if (confirm('삭제하시겠습니까?')) {
			$.ajax({
				type:'POST',
				url:'${path}/replyDeleteAction.ad?${_csrf.parameterName}=${_csrf.token}',
				data: 'reply_no=' + no,
				success: function() {
					replyList();
				},
				error: function() {
					alert('답변 삭제 error 발생!');
				}
			});
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
			<h1 align="center"> ${board.board_category} </h1>
		</div>
		
		<div id="contents">
			<!-- 왼쪽 메뉴 -->
			<%@ include file="/WEB-INF/views/manager/common/left_menu.jsp" %>
			<div id="section">
				<div class="board">
					<form action="${path}/boardUpdate.ad?board_no=${board.board_no}&state=update&${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
						<table>
							<tr>
								<th>제목</th>
								<td style="padding-left: 20px;">${board.board_title}</td>
								<th>조회수</th>
								<td>${board.board_hits}</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td style="padding-left: 20px;">${board.board_writer}</td>
								<th>등록일</th>
								<td style="text-align: right;">${board.board_regist_day}</td>
							</tr>
							
							<tr style="height: 300px;">
								<th>내용</th>
								<td colspan="3"style="padding-left: 20px; vertical-align: top;">
									<br>
									${board.board_contents}
								</td>
							</tr>
							
							<tr>
								<th>파일</th>
								<%-- 첨부 파일이 있으면 --%>
								<c:if test="${board.board_file_name != 'noFile'}">
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
							<tr>
								<td style="text-align: left; padding-left: 20px; border-bottom: none;">
									<input type="button" class="listBtn" value="목록" onclick='window.location="${path}/boardList.ad?board_category=${board.board_category}&pageNum=${param.pageNum}"'>
								</td>
								<%-- 공지사항 게시판인 경우 수정 삭제 버튼 표시 --%>
								<c:if test="${board.board_category == '공지사항'}">
									<td colspan="3" style="text-align: right; padding-right: 20px; border-bottom: none;">
										<input type="submit" class="updateBtn" value="수정">
										<input type="button" class="deleteBtn" value="삭제" onclick="window.location='${path}/boardDeleteAction.ad?board_no=${board.board_no}&board_category=${board.board_category}'">
									</td>	
								</c:if>
							</tr>
						</table>
					</form>
				</div>
				<c:if test="">
				
				</c:if>
				<%-- 문의사항인 경우 답변 양식 출력 --%>
				<c:if test="${board.board_category == '문의사항'}">
					<div id="replyList">
							<div class="reply"></div>
					</div>
					<br>
					<div id="replyAdd">
						<table>
							<tr>
								<th width="120px" style="border-bottom: none;">답변</th>
								<td width="800px" style="border-bottom: none;">
									<textarea cols="90" rows="8" id="replyContents" required></textarea>
								</td>	
								<td width="100px" style="border-bottom: none;">
									<input type="button" value="답변하기" class="replyBtn" onclick="replyAdd()">
								</td>
							</tr>	
						</table>					
					</div>
				</c:if>
			</div>
		</div>
	</div>	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/manager/common/footer.jsp" %>
</body>
</html>