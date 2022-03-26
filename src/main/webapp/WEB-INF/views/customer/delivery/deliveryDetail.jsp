<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송 상세조회</title>
</head>
<body>
	<div id="container">
		<div id="contents">
			<div id="board" align="left">
				<h1>배송 상세조회</h1>
				<table border="1" style="width:600px; text-align:center; border-collapse: collapse; border-bottom: none;">
					<caption>배송현황</caption>
					<tr style="border-bottom: 2px solid black;">
						<th>배송현황</th>
						<th>일자</th>
						<th>현재위치</th>
						<th>배송기사</th>
						<th>기사 전화번호</th>
					</tr>
					<c:forEach var="delivery" items="${dlist}">
						<tr>
							<td>${delivery.delivery_state}</td>
							<td>${delivery.delivery_day}</td>
							<td>${delivery.current_location}</td>
							<td>${delivery.deliver_name}</td>
							<td>${delivery.deliver_tel}</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="6" style="text-align:center; border: none;">
							<a href="javascript:window.open('', '_self').close();" style="font-size:20px; text-decoration: none;">닫기</a>
						</td>
					</tr>
				</table>
				<br>
				
			</div>	
		</div>
	</div>
</body>
</html>