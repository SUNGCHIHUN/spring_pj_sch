<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객주소 조회</title>
</head>
<body>
	<table>
		<tr>
			<td style="padding: 0; border: none; text-align: left;">
				<h2>배송지 정보</h2>
			</td>
		</tr>
		<tr>
			<th>받으시는 분 &nbsp; &nbsp; &nbsp; <input type="button" class="selectCustomerAddressBtn" value="본인"></th>
			<td><input type="text" class="inputOrder" name="order" value="${c_dto.customer_name}"></td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input type="text" class="inputZipcode" id="zipcode" name="zipcode" value="${z_dto.zipcode}" readonly> 
				<input type="button" value="우편번호" class="zipcodeBtn" onclick="setAddress();"><br>
				<input type="text" class="inputAddress" id="address1" name="address1" value="${z_dto.sido} ${z_dto.gugum} ${z_dto.dong} ${z_dto.bunji}" readonly><br><br>
				<input type="text" class="inputAddress" id="address2" name="address2" value="${c_dto.customer_address}">
			</td>
		</tr>
		<tr>
			<th>핸드폰</th>
			<td>
				<c:forEach var="tel" items="${fn:split(c_dto.customer_tel, '-')}" varStatus="status">
					<input type="text" class="inputTel" name="tel${status.count}" value="${tel}">
					<c:if test="${status.count != 3}"> - </c:if> 
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>배송메시지</th>
			<td><input type="text" class="inputMsg" name="msg" value="안전하게 와주세요.!"></td>
		</tr>
	</table>
</body>
</html>