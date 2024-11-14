package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.info("CustomLogoutSuccessHandler:onLogoutSuccess() 실행!");
		
		// 보통 로그아웃을 진행하면 로그인 페이지로 넘어가지만, 우리는 /로 넘어가도록 설정함
		response.sendRedirect("/");
	}

}
