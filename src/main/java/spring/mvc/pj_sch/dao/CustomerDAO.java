package spring.mvc.pj_sch.dao;

import java.util.List;

import spring.mvc.pj_sch.dto.CustomerDTO;
import spring.mvc.pj_sch.dto.ZipcodeDTO;

public interface CustomerDAO {

	// 아이디 중복 확인
	int confirmId(String strId);

	// 우편번호 정보 저장
	int addZipcode(ZipcodeDTO dto2);

	// 회원정보 등록
	int addCustomer(CustomerDTO dto);
	
	// 아이디, 비밀번호 체크
//	int idPasswordCheck(String strId, String strPassword);
	
	// 회원정보 수정
	int updateCustomer(CustomerDTO dto);	
	
	// 우편번호 조회
	ZipcodeDTO getAddress(String zipcode);
	
	// 회원정보 조회
	CustomerDTO getCustomerDetail(String strId);
	
	// 회원정보 삭제
	int deleteCustomer(String strId);

	// 회원 우편번호 가져오기
	String getCustomerZipcode(String strId);

	// 주소정보 가져오기
	ZipcodeDTO getZipcodeInfo(String zipcode);
	
	// 회원정보 전체 조회
	List<CustomerDTO> getCustomerList(int start, int end);
			
	// 전체 회원 수
	int getCustomerTotal();
	
	// 관리자 아이디, 비밀번호 확인
	int adminCheck(String strId, String strPassword);
	
	// 시큐리티 - 권한 가져오기
	String getAuthority(String customer_id);

	// 시큐리티 - 비밀번호 가져오기
	String getPassword(String customer_id);
	
	// 시큐리티 - 이메일 인증을 위한 이메일 전송
	void sendEmail(String email, String key);
	
	// 시큐리티 - 이메일 인증을 위한 key 확인
	int getKey(String key);
	
	// 시큐리티 - 이메일 인증에 성공한 경우 enabled 1로 변경
	int updateGrade(String key);
	
	// 시큐리티 - 회원 이름 가져오기
	String getCustomerName(String customer_id);
}