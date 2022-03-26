<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>left_menu.jsp</title>
<link rel="stylesheet" href="${path}/resources/css/manager/left_menu.css">
</head>
<body>
	<div class="left_menu">
		<div class="title">
			<h2>관리자 메뉴</h2>
			<div class="line"></div>
		</div>
		<div class="menu">
			<ul>
				<li>재고관리</li>
				<li><a href="stockList.st">상품목록</a></li>
				<li><a href="stockAdd.st">상품등록</a></li>
			</ul>
			<ul>
				<li>주문관리</li>
				<li><a href="orderList.ad">주문</a></li>
				<li><a href="refundList.ad">환불</a></li>
			</ul>
			<ul>
				<li>회원관리</li>
				<li><a href="customerList.ad">회원목록</a></li>
			</ul>
			<ul>
				<li>게시판</li>
				<li><a href="boardList.ad?board_category=공지사항">공지사항</a></li>
				<li><a href="boardList.ad?board_category=문의사항">문의사항</a></li>
			</ul>
			<ul>
				<li>리뷰관리</li>
				<li><a href="reviewList.ad">리뷰</a></li>
			</ul>
			<ul>
				<li>매출관리</li>
				<li><a href="salesList.ad">결산</a></li>
			</ul>
			<ul>
				<li>세션</li>
				<li><a href="logoutAction.do">로그아웃</a></li>
			</ul>
		</div>
	</div>
</body>
</html>