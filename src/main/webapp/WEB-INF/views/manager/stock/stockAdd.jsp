<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>치모 - 상품등록</title>
<link rel="stylesheet" href="${path}/resources/css/manager/main.css">
<link rel="stylesheet" href="${path}/resources/css/manager/stock_detail.css">
<script>

	$(function() { // 페이지가 로드되면
	   $("#product_file").on("change", handleImgFileSelect);

		/* 
			$("#product_file").change(function(e){
			 
		    alert($('input[type=file]')[0].files[0].name); //파일이름
	        alert($("#pdImg")[0].files[0].type); // 파일 타임
	        alert($("#pdImg")[0].files[0].size); // 파일 크기
		    // 첨부한 이름 값을 저장
			$("#product_file").attr("value", $("#product_file")[0].files[0].name);
		 	
		    // 이미지 경로도 변경해줌
		    $("#product_img").prop("src", $("#product_file")[0].files[0].name); 
		
		*/
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
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/manager/common/header.jsp" %>
	<div id="container">
		<!-- 메뉴이름 -->
		<div id="title">
			<h1>상품등록</h1>
		</div>
		
		<div id="contents">
		<!-- 왼쪽 메뉴 -->
		<%@ include file="/WEB-INF/views/manager/common/left_menu.jsp" %>
			<!-- 오른쪽 컨텐츠 -->
			<div id="section">
				<form action="${path}/stockAddAction.st?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<th>상품명</th>
							<td colspan="3"><input type="text" class="inputBox" name="p_name" required></td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td>
								<select class="category" name="p_category">
									<option value="">없음</option>
									<option value="energy">드링크</option>
									<option value="carbon">탄산</option>
									<option value="water">생수</option>
									<option value="coffee">커피</option>
								</select>
							</td>
							<th>판매상태</th>
							<td>
								<select class="state" name="product_state">
									<option value="">없음</option>
									<option value="sale">판매중</option>
									<option value="soldout">품절</option>
								</select>								
							</td>
						</tr>
						<tr>
							<th>상품가격</th>
							<td><input type="number" class="inputBox" min=0 name="p_price" required></td>
							<th>상품수량</th>
							<td><input type="number" class="inputBox" min=0 name="p_amount" required></td>
						</tr>
						<tr>
							<th>상품이미지</th>
							<td style="text-align: center;">
								<img id="product_img" width="200px" height="200px">
							</td>
							<td colspan="2">
								<%-- 기존 이미지 이름 --%>
								<input type="hidden" name="hidden_img_name" value="${p_dto.product_img_name}">
								<br>
								<input type="file" id="product_file" name="product_file" accept="image/*" required>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<input type="submit" class="insertBtn" value="등록">
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