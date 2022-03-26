<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지모 푸터</title>
<link href="${path}/resources/css/common/footer.css" rel="stylesheet">
<script>
	
	function askSimpleAdd() {
		if (${sessionId == ""}) {
			alert("로그인이 필요합니다.");	
			window.location="${path}/login.do";
		} else {
			window.open('${path}/askSimpleAdd.do', '_blank', 'width=700px; height=700px;');
		}
	}
</script>
</head>
<body>
	<div id="footer">
		<div class="footer_menu">
			<ul class="footer_menu1">
				<li><h3>고객지원</h3></li>
				<li>고객지원센터 바로가기</li>
				<li>페이스북</li>
				<li>인스타그램</li>
				<li>카카오 플러스친구</li>
				<li>고객 1:1 맞춤 상담</li>
				<li>카카오톡 상담</li>
			</ul>
			
			<ul class="footer_menu2">
				<li><h3>카테고리</h3></li>
				<li>전체</li>
				<li>드링크</li>
				<li>탄산</li>
				<li>생수</li>
				<li>커피</li>
				<li>머그컵</li>
				<li>캔</li>
				<li>기타 음료</li>
				<li>아울렛</li>
				<li>in Drink Store</li>
			</ul>
			
			<ul class="footer_menu3">
				<li><h3>커뮤니티</h3></li>
				<li>공지사항</li>
				<li>이벤트</li>
				<li>리뷰</li>
				<li>Q&A</li>
			</ul>
			
			<ul class="footer_menu4">
				<li><h3>쇼핑몰정보</h3></li>
				<li>회사소개</li>
				<li>이용약관</li>
				<li>개인정보취급방침</li>
				<li>이용안내</li>
			</ul>

			<ul class="footer_menu5">
				<li><h3>무통장입금</h3></li>
				<li>농협은행 111-111111-1111</li>
				<li>국민은행 222222-22-222222</li>
				<li>신한은행 333-3333-33333</li>
				<li>우리은행 4444-444-444444</li>
				<li>하나은행 5555-555555-55555</li>
				<li>예금주 : (주)치모</li>
				<li>입금자명, 금액이 다를 경우</li>
				<li>입금확인이 되지 않을 수 있습니다.</li>
			</ul>
		</div>
		
		<div class="footer_info">
			(주)치모<br>
			주소 : 40209 경상북도 울릉군 북면 나리 112, 1F | 대표이사 : 성치훈 | 사업자 등록번호 : 123-21-12321 | 음료판매업 신고번호 : 2022-서울강남-08282호<br>
			이메일 : contact@cimo.kr | 고객센터 : 1588-8282 | 개인정보관리 책임자 : 성치훈<br>
			<br>
			2022 © CIMO Inc. All rights reserved.
			<br> _
		</div>
		
		<div class="footer_ask">
			<a href="javascript:askSimpleAdd();"><img src="${path}/resources/images/footerImage/ask.png"></a>
		</div>
	</div>
</body>
</html>