/**
 *  alert 에러 출력
 */

var idError = "아이디가 존재하지 않습니다.";
var passwordError = "비밀번호가 일치하지 않습니다.";
var selectError = "조회 실패";
var insertError = "등록 실패";
var updateError = "수정 실패";
var deleteError = "삭제 실패";
var unknownError = "알 수 없는 에러가 발생하였습니다."
var needLoginError = "로그인이 필요합니다."
// 오류 출력 메서드
function errorAlert(error) {
	alert(error);
}