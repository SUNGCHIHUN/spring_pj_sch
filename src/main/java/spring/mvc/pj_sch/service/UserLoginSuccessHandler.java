package spring.mvc.pj_sch.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import spring.mvc.pj_sch.dao.CustomerDAO;
import spring.mvc.pj_sch.vo.UserVO;

// 로그인 성공
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	SqlSession sqlSession;
	
	public UserLoginSuccessHandler(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("=== UserLoginSuccessHandler - onAuthenticationSuccess() 진입 ===");
	
		UserVO vo = (UserVO)authentication.getPrincipal();
		System.out.println("인증된 정보 : " + vo);

		// 이름을 가져와서 메세지 생성
		String name = sqlSession.getMapper(CustomerDAO.class).getCustomerName(authentication.getName());
		String msg = name + "님 환영합니다.";
	
		// 권한 가져오기
		String authority = sqlSession.getMapper(CustomerDAO.class).getAuthority(authentication.getName());
		
		// 값 넘겨주기
		request.setAttribute("msg",  msg);
		request.getSession().setAttribute("sessionId",  authentication.getName());
		request.getSession().setAttribute("sessionName", name);
		request.getSession().setAttribute("authority", authority);
		
		// 고객 권한이면
		if (authority.equals("ROLE_USER")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/main.do");
			dispatcher.forward(request, response);
			
		// 관리자 권한이면
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/stockList.st");
			dispatcher.forward(request, response);
		}
	}
}