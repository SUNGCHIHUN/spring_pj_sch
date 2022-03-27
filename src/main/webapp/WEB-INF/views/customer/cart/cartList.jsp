<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>치모 장바구니</title>
<link href="${path}/resources/css/common/main.css" rel="stylesheet">
<link href="${path}/resources/css/customer/cart.css" rel="stylesheet">
<script>
	
	var total_price = 0; // 가격 전체 합계 (배송비 미포함)
	var no = ""; // 상품 장바구니 번호 (구분용)
	var price = 0; // 상품 가격
	var amount = 0; // 장바구니에 담긴 상품 개수
	var total_count = 0; // 체크선택한 상품 개수
	var free_price = 0; // 무료까지 남은 가격
	var delivery_price = 0; // 배송비
	var final_total_price = 0; // 배송비 합한 전체 가격
	
	var shelf_no_arr = []; // 선택한 장바구니 번호를 담을 변수
	
	$(function() {
		setTotal();
		printValue();
	});
	
	// 값 초기화
	function initValue() {
		total_price = 0;
		no = "";
		price = 0;
		amount = 0;
		total_count = 0;
		free_price = 100000;
		delivery_price = 0;
		final_total_price = 0;
		
		shelf_no_arr = [];
	}
	
	// 배송비 계산
	function calcDeliveryPrice() {
		// 배송비 결정
		if(total_price >= 100000) {
			delivery_price = 0;
			
		} else if(total_price == 0) {
			delivery_price = 0;
			
		} else {
			delivery_price = 3000;
		}
		
		// 무료배송까지 남은 금액 설정
		free_price = 100000 - total_price;
		if (free_price < 0) free_price = 0;
	}
	
	// 결과값 출력
	function printValue() {
		$(".total_count").html(total_count + " 종 선택");
		$(".total_price").html(total_price.toLocaleString() + "원");
		$(".free_price").html(free_price.toLocaleString() + "원");
		$(".progress_price").attr("value", total_price);
		$(".delivery_price").html(delivery_price.toLocaleString() + "원");
		$(".final_total_price").html(final_total_price.toLocaleString() + "원");
	}
	
	// 금액 계산
	function setTotal() {
		// 초기화
		initValue();
		
		// 체크박스 이름이 cartBox를 순서대로 돌며 값을 확인 후 설정
		$("input[name=cartBox]").each(function(index, element) {
			// 체크 되어있는 상품만 값 설정
			if ($(element).is(":checked")) {
				no = $(element).val(); // 장바구니 번호를 식별용으로 사용
				price = $("#product_price" + no).val();
				amount = $("#amount" + no).val();
				total_price += (parseInt(price) * parseInt(amount));
				shelf_no_arr.push(no);
				total_count += 1;
			}
		});
		
		calcDeliveryPrice()
		
		// 최종 합계 계산
		final_total_price = total_price + delivery_price;
	}
	
	// 전체선택 / 해제
	function checkAllDel() {
		
		// 눌렀을 때 체크상태이면
		if ($("input[name=cartBox]").is(":checked")) {
			console.log("checked = false");
			
			// 전체 체크해제 처리
			$("input[name=cartBox]:checked").each(function(index, element) {
				$(element).prop("checked", false);
				shelf_no_arr = [];
			});

			initValue();
			printValue();
			
		// 체크상태가 아니면
		} else {
			console.log("checked = true");
			
			// 전체 선택처리
			$("input[name=cartBox]").each(function(index, element) {
				$(element).prop("checked", true);
				shelf_no_arr.push($(element).val());
			});
		}
		setTotal();
		printValue();
	}
	
	// 장바구니 상품 낱개 선택 시 금액 계산
	function checkItem() {
		initValue();
		
		// 체크 선택
		$("input[name=cartBox]").each(function(index, element) {
			// 체크된 상태라면
			if ($(element).is(":checked")) {
				no = $(element).val();
				price = $("#product_price" + no).val();
				amount = $("#amount" + no).val();
				total_price += (parseInt(price) * parseInt(amount));
				shelf_no_arr.push(no);
				total_count++;
			}

			// 체크가 하나도 되있지 않은 경우
			if ($("input[name=cartBox]:checked").length == 0) {
				$("#check_all_del").prop("checked", false);
				shelf_no_arr = [];
			}
			
			// 낱개로 한개 이상 선택한 경우
			if ($("input[name=cartBox]:checked").length >= 1)
				$("#check_all_del").prop("checked", true);
		});
		
		console.log(shelf_no_arr);
		calcDeliveryPrice()
		
		// 최종 금액
		final_total_price = total_price + delivery_price;
		printValue();
	}
	
	// 수정 버튼
	function modifyAmount(shelf_no) {
		var amount = $("#amount"+ shelf_no).val();
		location.href="${path}/cartUpdateAction.do?shelf_no="+ shelf_no +"&amount=" + amount;
	}
	
	// 삭제 버튼
	function deleteCartItem(shelf_no) {
		location.href="${path}/cartDeleteAction.do?shelf_no=" + shelf_no;
	}
	
	// 전체 삭제 버튼
	function deleteCartAll() {
		if (confirm('장바구니를 비우시겠습니까?')) {
			location.href="${path}/cartAllDeleteAction.do";
		}
	}
	
	// 구매하기
	function pay() {
		var check = false; // 장바구니 상품 선택 상태
		
		// 상품을 하나라도 선택했다면 true
		$("input[name=cartBox]").each(function(index, element){
			if ($(element).is(":checked")) {
				check = true;
				return false;
			}
		});
		
		// 상품을 하나도 선택하지 않은 경우
		if (check == false) {
			alert("장바구니의 상품을 하나 이상 선택해 주세요.");
			return false;
		}
		
		document.cart_list.action="${path}/cartPay.do?shelf_no_arr=" + shelf_no_arr + "&buyState=2";
		document.cart_list.submit();
	}
	
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="container">
		<div id="section1">
			<h1 style="padding: 20px 0;"> 장바구니에 들어 있는 제품입니다.</h1>
		</div>
		<div id="section2">
			<form name="cart_list" method="post" onsubmit="return pay();">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<input type="hidden" name="buy_state" value="2"> 
				<div class="cart_list">
					<table>
						<tr>
							<td colspan="8" style="border: none;">
								<ul>
									<li style="text-align:left; font-size:20px; font-weight:bold;">무료배송까지 남은 금액은 <span class="free_price"></span> 입니다.</li>
									<li><progress class="progress_price" value="${shelf.product_price * shelf.amount}" max=100000></progress></li>
								</ul>
							</td>
						</tr>
						<tr>
							<th><input type="checkbox" id="check_all_del" name="check_all_del" checked onclick="checkAllDel()"></th>
							<th colspan="2">상품정보</th>
							<th>판매가</th>
							<th>수량</th>
							<th>합계</th>
							<th>선택</th>
						</tr>
						<c:set var="total" value="0" />
						<c:forEach var="shelf" items="${slist}" varStatus="status">
							<tr>
								<td>
									<input type="checkbox" id="product" name="cartBox" value="${shelf.shelf_no}" checked onclick="checkItem();">
								</td>
								
								<td><img src="${shelf.product_img_name}" alt="상품이미지"></td>
								
								<td>
									${shelf.product_name}
									<input type="hidden" id="product_name${status.count}" name="product_name${status.count}" value="${shelf.product_name}">
								</td>
								
								<td>
									<fmt:formatNumber value="${shelf.product_price}" pattern="#,### 원" />
									<input type="hidden" id="product_price${shelf.shelf_no}" name="product_price${shelf.shelf_no}" value="${shelf.product_price}">
								</td>
								
								<td>
									<ul style="margin-right: 30px;">
										<li>
											<input type="number" id="amount${shelf.shelf_no}" name="amount${shelf.shelf_no}" class="inputAmount" value="${shelf.amount}" min=0 max=9999>
										</li>
										<li>
											<input type="button" id="modifyBtn" class="modifyBtn" value="변경" onclick="modifyAmount('${shelf.shelf_no}');">
										</li>
									</ul>
								</td>
								
								<td>
									<fmt:formatNumber value="${shelf.product_price * shelf.amount}" pattern="#,### 원" />
								</td>
								
								<td>
									<input type="button" id="deleteBtn" class="deleteBtn" value="삭제" onclick="deleteCartItem('${shelf.shelf_no}');">
								</td>
							</tr>
						</c:forEach>
						
						<%-- 장바구니에 상품이 있는 경우에만 전체삭제 버튼 표시 --%>
						<c:if test="${slist.size() != 0}">
							<tr>
								<td style="border-bottom: none;"></td>
								
								<td colspan="5" style="text-align: center; border-bottom: none;">
									<div class="total_count" style="font-size: 20px;"></div>
								</td>
								
								<td style="text-align: right; padding-right: 10px; border-bottom: none;">
									<input type="button" id="deleteAllBtn" class="deleteBtn" value="전체삭제" onclick="deleteCartAll();">
								</td>
							</tr>
						</c:if>
						
						<%-- 장바구니에 상품이 없는 경우 메세지 출력 --%>
						<c:if test="${slist.size() == 0}">
							<tr>
								<td colspan="7" style="height: 200px;">장바구니에 담긴 상품이 없습니다.</td>
							</tr>
						</c:if>
					</table>
				</div>
				<div class="cart_total" style="text-align: center;">
					<table>
						<tr>
							<th style="border-right: 1px solid gray;">총 상품금액</th>
							<th style="border-right: 1px solid gray;">배송비</th>
							<th>결제예정금액</th>
						</tr>
						<tr>
							<td style="border-right: 1px solid gray;" class="total_price"></td>
							<td style="border-right: 1px solid gray;" class="delivery_price"></td>
							<td class="final_total_price"></td>
						</tr>
						<%-- 장바구니에 체크된 상품이 있는 경우만 결제하기 버튼 표시 --%>
						<c:if test="${slist.size() != 0}">
							<tr>
								<td colspan="3" style="border-bottom: none; padding-top: 50px;">
									<input type="submit" id="payBtn" class="payBtn" value="결제하기">
								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>