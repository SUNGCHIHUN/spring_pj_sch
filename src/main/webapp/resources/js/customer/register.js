/**
 * 회원가입 자바스크립트
 */

// 회원가입 
function registerCheck() {
	if (!document.register_form.id.value) {
		alert("아이디를 입력하세요.")
		document.register_form.id.focus();
		return false;
		
	} else if (!document.register_form.password.value) {
		alert("비밀번호를 입력하세요.");
		document.register_form.password.focus();
		return false;
		
	} else if (!document.register_form.rePassword.value) {
		alert("비밀번호 확인을 입력하세요.");
		document.register_form.rePassword.focus();
		return false;
		
	} else if (document.register_form.password.value != document.register_form.rePassword.value) {
		alert("비밀번호가 일치하지 않습니다.");
		document.register_form.rePassword.focus();
		return false;
		
	} else if (!document.register_form.name.value) {
		alert("이름을 입력하세요.");
		document.register_form.name.focus();
		return false;
		
	} else if (!document.register_form.tel1.value) {
		alert("핸드폰번호를 모두 입력하세요.");
		document.register_form.tel1.focus();
		return false;
		
	} else if (!document.register_form.tel2.value) {
		alert("핸드폰번호를 모두 입력하세요.");
		document.register_form.tel2.focus();
		return false;
		
	} else if (!document.register_form.tel3.value) {
		alert("핸드폰번호를 모두 입력하세요.");
		document.register_form.tel3.focus();
		return false;
		
	} else if (!document.register_form.email1.value) {
		alert("이메일 입력하세요.");
		document.register_form.email1.focus();
		return false;
		
	} else if (!document.register_form.email2.value) {
		alert("이메일 형식이 올바르지 않습니다.");
		document.register_form.email2.focus();
		return false;
	
	} else if (document.register_form.hiddenIdCheck.value == 0) {
		alert("아이디 중복확인이 필요합니다.");
		document.register_form.idCheckBtn.focus();
		return false;
	}
}

function selectEmail() {
	if (document.register_form.email3 == "0") {
		document.register_form.email2.value = "";
		document.register_form.email2.readOnly = false;
	} else {
		document.register_form.email2.value = document.register_form.email3.value;
		document.register_form.email2.readOnly = true;
	}
}

function idCheck() {
	if (!document.register_form.id.value) {
		alert("아이디를 입력하세요.")
		document.register_form.id.focus();
		return false;
	}
	
	var url = "/spring_pj_sch/confirmIdAction.do?id=" + document.register_form.id.value;
	window.open(url, "confirm", "menubar=no, resizable=no, scrollbars=no, width=600px, height=500px")
}

function confirmIdCheck() {
	if (!document.confirm_form.id.value){
		alert("아이디를 입력하세요.");
		document.confirm_form.id.focus();
		return false;
	}
}

function setId(id) {
	opener.document.register_form.id.value = id;
	opener.document.register_form.hiddenIdCheck.value = 1;
	self.close();
}

// 중복확인 하고 아이디를 바꾼 경우
function inputIdCheck() {
	// 중복확인이 필요한 상태로 설정
	document.register_form.hiddenIdCheck.value = 0;
}

// 회원탈퇴 확인창
function deleteCustomer(path) {
	// 확인을 누른 경우
	if(confirm("정말로 탈퇴하시겠습니까?")) {
		window.location=path + "/customerDeleteAction.do";
	}
}