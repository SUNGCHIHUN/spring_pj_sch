<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 결산</title>
<link rel="stylesheet" href="${path}/resources/css/manager/main.css">
<link rel="stylesheet" href="${path}/resources/css/manager/board_list.css">
<link rel="stylesheet" href="${path}/resources/css/manager/page.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

	// 카테고리별 매출액과 총액 설정
	var carbonTotal = 0;
	var coffeeTotal = 0;
	var drinkTotal = 0;
	var waterTotal = 0;
	var salesTotal = 0;

	if (${tlist.get(0).total_sales != ''})
		console.log('tlist.get(0).total_sales' + ${tlist.get(0).total_sales})
		
		carbonTotal = parseInt("${tlist.get(0).total_sales}");
	
	if (${tlist.get(1).total_sales != ''})
		coffeeTotal = parseInt("${tlist.get(1).total_sales}");
	
	if (${tlist.get(2).total_sales != ''})
		drinkTotal = parseInt("${tlist.get(2).total_sales}");
	
	if (${tlist.get(3).total_sales != ''})
		waterTotal = parseInt("${tlist.get(3).total_sales}");
	
	if (${tlist.get(4).total_sales != ''})
		salesTotal = parseInt("${tlist.get(4).total_sales}");

	
	$(function() {
		loadChart();
	});

	// 차트 불러오기
	function loadChart() {
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawVisualization);
	}
		
	// 차트 정보 설정
	function drawVisualization() {
		var data = google.visualization.arrayToDataTable([
			['매출액', '드링크', '탄산', '생수', '커피', '전체'],
			['-', drinkTotal, carbonTotal, waterTotal, coffeeTotal, salesTotal]
		]);
		
		var options = {
			title : '카테고리별 매출액',
			vAxis: {title: '매출액'},
			hAxis: {title: '상품 카테고리'},
			width: 900,
			height: 400,
			seriesType: 'bars',
			series: {5: {type: 'line'}}
		};
		
		var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
		chart.draw(data, options);
	}
	
</script>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
	<div id="container">
		<!-- 메뉴이름 -->
		<div id="title">
			<h1 align="center"> 결산내역 </h1>
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
							<th>카테고리</th>
							<th>상품명</th>
							<th>상품 가격</th>
							<th>구매량</th>
							<th>합계</th>
						</tr>
						<%-- 게시글이 없으면 --%>
						<c:if test="${sales.isEmpty()}">
							<tr>
								<td colspan="5">
									<div class="discription">
										<h2> 결산 내역이 존재하지 않습니다.</h2>
									</div>
								</td>
							</tr>
						</c:if>
						<c:forEach var="sales" items="${slist}" varStatus="status">
							<tr>
								<td>${sales.product_no}</td>
								<td>
									<c:choose>
										<c:when test="${sales.product_category == 'carbon'}">탄산</c:when>
										<c:when test="${sales.product_category == 'drink'}">드링크</c:when>
										<c:when test="${sales.product_category == 'water'}">생수</c:when>
										<c:when test="${sales.product_category == 'coffee'}">커피</c:when>
										<c:otherwise>없음</c:otherwise>
									</c:choose>
								</td>
								<td><a href="${path}/stockDetail.st?product_no=${sales.product_no}&pageNum=${paging.currentPage}">${sales.product_name}</a></td>
								<td><fmt:formatNumber value="${sales.product_price}" pattern="#,### 원" /></td>
								<td>${sales.total_amount}</td>
								<td><fmt:formatNumber value="${sales.total_price}" pattern="#,### 원" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="6" style="text-align: right; padding-right: 10px; border-bottom: none; font-size:20px;"> 
								총 매출 : <fmt:formatNumber value="${tlist.get(4).total_sales}" pattern="#,### 원" />
							</td>
						</tr>
						<%-- 페이징 처리 --%>
						<tr>
							<td colspan="8" align="center" style="border-bottom: none;">
								<%-- 이전버튼 활성화 여부 --%>
								<c:if test="${paging.startPage > 10}">
									<a href="${path}/salesList.ad?pageNum=${paging.prev}">[이전]</a>
								</c:if>
								
								<%-- 페이지 번호 처리 --%>
								<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
									<%-- 현재 페이지 번호는 눌리지 않게 처리 --%>
									<c:if test="${num == paging.currentPage}">
										<span style="font-weight: bold;">${num}</span>
									</c:if>
									
									<c:if test="${num != paging.currentPage}">
										<a href="${path}/salesList.ad?pageNum=${num}">${num}</a>
									</c:if>
								</c:forEach>
								
								<%-- 다음버튼 활성화 여부 --%>
								<c:if test="${paging.endPage < paging.pageCount}">
									<a href="${path}/salesList.ad?pageNum=${paging.next}">[다음]</a>
								</c:if>
							</td>
						</tr>
					</table>
					<%--구글 차트 --%>
					<div id="chart_box" style="height: 100%;">
						<div id="chart_div" style="padding-left: 40px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<%@ include file="/WEB-INF/views/manager/common/footer.jsp" %>
</body>
</html>