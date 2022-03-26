package spring.mvc.pj_sch.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import spring.mvc.pj_sch.dao.CustomerDAO;
import spring.mvc.pj_sch.dto.CustomerDTO;
import spring.mvc.pj_sch.vo.UserVO;

// 로그인 인증 클래스 - /loginCheck.do로 이동하면 loadUserByUsername() 메서드 자동 호출
public class UserAuthenticationService implements UserDetailsService {

	@Autowired
	SqlSession sqlSession;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public UserAuthenticationService(SqlSession sqlSession, BCryptPasswordEncoder passwordEncoder) {
		this.sqlSession = sqlSession;
		this.passwordEncoder = passwordEncoder;
	}
	
	/* 핵심코드
	    * 로그인 인증을 처리하는 메서드
	    * 1) 매개변수 username을 id로 수정
	    * 2) 매개변수로 전달된 아이디와 일치하는 레코드를 읽어온다.
	    * 3) 테이블의 암호화된 비밀번호와 사용자가 입력한 비밀번호를 내부적으로 비교처리
	    * 4) 정보가 없으면 예외처리를 발생시키고, 있으면 해당정보를 dto로 리턴한다.
	    */
	
	@Override // username에 해당하는 회원정보 조회
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("=== UserAuthenticationService - loaduserByUsername() 서비스 수행 ===");
		// username은 id를 의미
		// 회원정보 데이터를 조회
		CustomerDTO dto = sqlSession.getMapper(CustomerDAO.class).getCustomerDetail(username);

		// 회원 정보가 없으면 예외처리
		if (dto == null) throw new UsernameNotFoundException(username);
		
		// 권한 설정
		List<GrantedAuthority> authority = new ArrayList<>();
		authority.add(new SimpleGrantedAuthority(dto.getAuthority())); // 기본값 'ROLE_USER'
		
		// 관리자 로그인인 경우
		if (username.equals("host")) {
			System.out.println("입력한 관리자 아이디 : " + username);
			
			// 샘플 관리자 비밀번호 인코딩
			String encryptPassword = passwordEncoder.encode(dto.getCustomer_password());
			
			return new UserVO(dto.getCustomer_id(), encryptPassword, dto.getEnabled().equals("1"),
					true, true, true, authority);
		}
		
		System.out.println("일반 로그인 아이디 : " + username);

		// UserVO 클래스 먼저 생성후 return
	    // 시큐리티 로그인에서 체크 : id, password, authority(ROLE_USER / ROLE_ADMIN), enabled(이메일인증시 "1"로 update치며, 이메일인증후 시큐리티 적용)
		return new UserVO(dto.getCustomer_id(), dto.getCustomer_password(), dto.getEnabled().equals("1"),
				true, true, true, authority);
	}
}