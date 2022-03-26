<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>치모 - 상품상세</title>
<link rel="stylesheet" href="${path}/resources/css/manager/main.css">
<link rel="stylesheet" href="${path}/resources/css/manager/stock_detail.css">
</head>
<script>

	$(function() { // 페이지가 로드되면
	   $("#product_file").on("change", handleImgFileSelect);
	});
	
	// 파일첨부시 이미지 미리보기
	function handleImgFileSelect(e) {
	   var files = e.target.files;
	   var filesArr = Array.prototype.slice.call(files);
	   
	   filesArr.forEach(function(f){

		   var sel_file = f;
		   
		   var reader = new FileReader();
		   reader.onload = function(e) {
	      	$("#product_img").attr("src", e.target.result);
	  	   }
	   		
	   	   reader.readAsDataURL(f);
	   });
	}
	
</script>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
	<div id="container">
		<!-- 메뉴이름 -->
		<div id="title">
			<h1>상품상세</h1>
		</div>
		
		<div id="contents">
		<!-- 왼쪽 메뉴 -->
		<%@ include file="/WEB-INF/views/manager/common/left_menu.jsp" %>
			<!-- 오른쪽 컨텐츠 -->
			<div id="section">
				<form action="${path}/stockUpdateAction.st?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
					<input type="hidden" name="pageNum" value="${paging.pageNum}">
					<input type="hidden" name="product_no" value="${p_dto.product_no}">
					<table>
						<tr>
							<th>상품명</th>
							<td colspan="3"><input type="text" class="inputBox" name="product_name" size="60" value="${p_dto.product_name}" required></td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td>
								<select class="category" name="product_category">
									<option value="" <c:if test="${p_dto.product_category == ''}"> selected</c:if>>없음</option>
									<option value="energy" <c:if test="${p_dto.product_category == 'energy'}"> selected</c:if>>드링크</option>
									<option value="carbon" <c:if test="${p_dto.product_category == 'carbon'}"> selected</c:if>>탄산</option>
									<option value="water" <c:if test="${p_dto.product_category == 'water'}"> selected</c:if>>생수</option>
									<option value="coffee" <c:if test="${p_dto.product_category == 'coffee'}"> selected</c:if>>커피</option>
								</select>
							</td>
							<th>상태</th>
							<td>
								<select class="state" name="product_state">
									<option value="" <c:if test="${p_dto.product_state == ''}"> selected</c:if>>없음</option>
									<option value="sale" <c:if test="${p_dto.product_state == 'sale'}"> selected</c:if>>판매중</option>
									<option value="soldout" <c:if test="${p_dto.product_state == 'soldout'}"> selected</c:if>>품절</option>
								</select>								
							</td>
						</tr>
						<tr>
							<th>상품가격</th>
							<td><input type="number" class="inputBox" name="product_price" min=0 value="${p_dto.product_price}" required></td>
							<th>상품수량</th>
							<td><input type="number" class="inputBox" name="product_amount" min=0 value="${p_dto.product_amount}" required></td>
						</tr>
						<tr>
							<th>상품이미지</th>
							<td style="text-align: center;">
								<img id="product_img" src="${p_dto.product_img_name}" alt="상품이미지" width="200px" height="200px">
							</td>
							<td colspan="2">
								<%-- 기존 이미지 이름 --%>
								<input type="hidden" name="hidden_img_name" value="${p_dto.product_img_name}">
								<br>
								<input type="file" id="product_file" name="product_file" accept="image/*">
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<input type="submit" class="updateBtn" value="수정">
								<input type="button" class="cancelBtn" value="취소" onclick="history.go(-1);">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/manager/common/footer.jsp" %>
</body>
</html>