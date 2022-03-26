<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변 리스트</title>
</head>
<body>
	<c:forEach var="reply" items="${rlist}">
		<table>
			<tr>
				<th width="100px">답변</th>
				<td width="700px">${reply.reply_contents}</td>
				<td width="200px" style="text-align: right;">
					<ul style="text-align: right;">
						<li>${reply.reply_writer}</li>
						<li>${reply.reply_regist_day }</li>
						<li>
							<input type="button" value="삭제" class="deleteReplyBtn" onclick='replyDelete("${reply.reply_no}")'>
						</li>
					</ul>
				</td>
			</tr>
		</table>
	</c:forEach>
</body>
</html>