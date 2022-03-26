package spring.mvc.pj_sch.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import spring.mvc.pj_sch.dao.CustomerDAO;

// 로그인 실패
public class UserLoginFailureHandler implements AuthenticationFailureHandler{

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public UserLoginFailureHandler(SqlSession sqlSession, BCryptPasswordEncoder passwordEncoder) {
		this.sqlSession = sqlSession;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override // 로그인 실패시 자동 호출
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("=== UserLoginFailureHandler - onAuthenticationFailure ===");

		// 입력받은 값
		String strId = request.getParameter("id");
		String strPassword = request.getParameter("password");
	
		// 아이디가 존재하는지 확인
		int selectResult = sqlSession.getMapper(CustomerDAO.class).confirmId(strId);
		
		// 중복된 아이디가 아니라면
		if (selectResult != 0) {
			// 암호화된 비밀번호 가져오기
			String encryptPassword = sqlSession.getMapper(CustomerDAO.class).getPassword(strId);
			
			// 암호가 일치하면
			if (passwordEncoder.matches(strPassword, encryptPassword)) {
				System.out.println("이메일 인증 필요.");
				request.setAttribute("errMsg", "이메일 인증이 필요합니다.");
				
			// 일치하지 않으면
			} else {
				System.out.println("비밀번호 불일치");
				request.setAttribute("errMsg", "비밀번호가 일치하지 않습니다.");
			}
			
		// 아이디가 존재하지 않으면
		} else {
			System.out.println("아이디 불일치");
			request.setAttribute("errMsg", "아이디가 일치하지 않습니다.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/login/login.jsp");
	    dispatcher.forward(request, response);
	}
}