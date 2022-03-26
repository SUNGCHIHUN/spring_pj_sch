package spring.mvc.pj_sch.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.pj_sch.dto.CustomerDTO;
import spring.mvc.pj_sch.dto.ZipcodeDTO;
import spring.mvc.pj_sch.util.SettingValues;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override // 아이디 중복 확인
	public int confirmId(String strId) {
		System.out.println("confirmId() - dao");
		return sqlSession.getMapper(CustomerDAO.class).confirmId(strId);
	}

	@Override // 우편번호 정보 저장
	public int addZipcode(ZipcodeDTO dto2) {
		System.out.println("addZipcode() - dao");
		return sqlSession.getMapper(CustomerDAO.class).addZipcode(dto2);
	}
	
	@Override // 회원정보 등록
	public int addCustomer(CustomerDTO dto) {
		System.out.println("addCustomer() - dao");
		return sqlSession.getMapper(CustomerDAO.class).addCustomer(dto);
	}

//	@Override // 아이디, 비밀번호 체크
//	public int idPasswordCheck(String strId, String strPassword) {
//		System.out.println("idPasswordCheck() - dao");
//		Map<String, Object> map = new HashMap<>();
//		map.put("customer_id", strId);
//		map.put("customer_password", strPassword);
//		return sqlSession.selectOne("spring.mvc.pj_sch.dao.CustomerDAO.idPasswordCheck", map);
//	}

	@Override // 회원정보 수정
	public int updateCustomer(CustomerDTO dto) {
		System.out.println("updateCustomer() - dao");
		return sqlSession.getMapper(CustomerDAO.class).updateCustomer(dto);
	}

	@Override // 우편번호 조회
	public ZipcodeDTO getAddress(String zipcode) {
		System.out.println("getAddress() - dao");
		return sqlSession.getMapper(CustomerDAO.class).getAddress(zipcode);
	}
	
	@Override // 회원정보 조회
	public CustomerDTO getCustomerDetail(String customer_id) {
		System.out.println("selectCustomer() - dao");
		return sqlSession.getMapper(CustomerDAO.class).getCustomerDetail(customer_id);
	}

	@Override // 회원정보 삭제
	public int deleteCustomer(String customer_id) {
		System.out.println("deleteCustomer() - dao");
		return sqlSession.getMapper(CustomerDAO.class).deleteCustomer(customer_id);
	}

	@Override // 회원 우편번호 가져오기
	public String getCustomerZipcode(String zipcode) {
		System.out.println("selectCustomerZipcode() - dao");
		return sqlSession.getMapper(CustomerDAO.class).getCustomerZipcode(zipcode);
	}
	
	@Override // 주소정보 가져오기
	public ZipcodeDTO getZipcodeInfo(String zipcode) {
		System.out.println("selectZipcodeInfo() - dao");
		return sqlSession.getMapper(CustomerDAO.class).getZipcodeInfo(zipcode);
	}

	@Override // 회원정보 전체 조회
	public List<CustomerDTO> getCustomerList(int start, int end) {
		System.out.println("selectCustomerList() - dao");
		// 회원정보 전체조회할 때 사용할 데이터
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("spring.mvc.pj_sch.dao.CustomerDAO.getCustomerList", map);
	}

	@Override // 전체 회원 수
	public int getCustomerTotal() {
		System.out.println("selectCustomerTotal() - dao");
		return sqlSession.getMapper(CustomerDAO.class).getCustomerTotal();
	}

	@Override // 관리자 아이디, 비밀번호 확인
	public int adminCheck(String strId, String strPassword) {
		System.out.println("adminCheck() - dao");
		Map<String, Object> map = new HashMap<>();
		map.put("strId", strId);
		map.put("strPassword", strPassword);
		return sqlSession.selectOne("spring.mvc.pj_sch.dao.CustomerDAO.adminCheck", map);
	}

	@Override // 시큐리티 - 권한 가져오기
	public String getAuthority(String customer_id) {
		System.out.println("getAuthority() - dao");
		return sqlSession.getMapper(CustomerDAO.class).getAuthority(customer_id);
	}
	
	@Override // 시큐리티 - 비밀번호 가져오기
	public String getPassword(String customer_id) {
		System.out.println("getPassword() - dao");
		return sqlSession.getMapper(CustomerDAO.class).getPassword(customer_id);
	}

	@Override // 시큐리티- 이메일 인증을 위한 이메일 전송
	public void sendEmail(String email, String key) {
	    final String username = SettingValues.ADMIN;      // 본인 이메일
		final String password = SettingValues.PW;      // 본인 비밀번호
		final String host = "smtp.gmail.com";
		 
		// SMTP(메일 서버) 설정
		 
		// 아래 import는 pom.xml에 mail API를 설정해야 가능
		// import java.util.Properties;
		Properties props = new Properties();         
		props.put("mail.smtp.user", username);         // SMTP에서 사용할 메일 주소
		props.put("mail.smtp.password", password);      // 비밀번호
		props.put("mail.smtp.host", host);            // host 서버 : gmail로 설정
		props.put("mail.smtp.port", "25");            // 25번 포트 사용
		props.put("mail.debug", "true");            // 디버그 설정
		props.put("mail.smtp.auth", "true");         // 인증 : true
		props.put("mail.smtp.starttls.enable", "true");   // tls 사용 허용
		props.put("mail.smtp.ssl.enable", "true");      // ssl 허용
		props.put("mail.smtp.ssl.trust", host);         // ssl 신뢰 가능으로 설정(보안레벨)
		  
		// propert값 설정
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		 
		// import javax.mail.Session;
		// import javax.mail.Authenticator
		// import javax.mail.PasswordAuthentication
		Session session = Session.getInstance(props, new Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		      return new PasswordAuthentication(username, password);
		   }
		});
		
		// import javax.mail.Message
		// import javax.mail.internet.MimeMessage;
		// import javax.mail.internet.InternetAddress;
		// import javax.mail.Transport
		 
		// emailCheck.do를 컨트롤러에 작성
		try {
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress("admin@CosmoJspPJ.com"));
		    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		
	 		// localhost => 본인 IP => 원격 발표시 IP 수정
	 		// 링크를 클릭해서 "이메일 인증 성공" => enabled를 1로 update함
	 		String content ="회원가입을 축하드립니다. 인증하기를 눌러 회원가입을 완료하세요.<br><br>"
			    + "<a href='http://localhost:8081/spring_pj_sch/emailCheckAction.do?key=" + key + "' style='color: blue; font-size: 20px'>인증하기</a>";
			message.setSubject("지모 회원가입 이메일 인증");
			message.setContent(content, "text/html; charset=utf-8");
			
			Transport.send(message);
			System.out.println("<<<< Email SEND >>>>");
		} catch(Exception e) {
		    e.printStackTrace();
		}
	}

	@Override // 시큐리티 - 이메일 인증을 위한 key 확인
	public int getKey(String key) {
		System.out.println("selectKey() - dao");
		return sqlSession.getMapper(CustomerDAO.class).getKey(key);
	}

	@Override // 시큐리티 - 이메일 인증에 성공한 경우 enabled 1로 변경
	public int updateGrade(String key) {
		System.out.println("updateGrade() - dao");
		return sqlSession.getMapper(CustomerDAO.class).updateGrade(key);
	}

	@Override // 시큐리티 - 회원 이름 가져오기
	public String getCustomerName(String customer_id) {
		System.out.println("getCustomerName() - dao");
		return sqlSession.getMapper(CustomerDAO.class).getCustomerName(customer_id);
	}
}