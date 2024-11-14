package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	// 접근 거부가 일어났을 때, 사용자가 정의한 CustomAccessDeniedHandler 클래스가 동작한다.
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.info("CustomAccessDeniedHandler:handle() 실행!");
		
		response.sendRedirect("/accessError");
		
		
		/*
		 	* CustomAccessDeniedHandler 실행
		 	INFO : kr.or.ddit.security.CustomAccessDeniedHandler - CustomAccessDeniedHandler:handle() 실행!
		 	
		 	* CustomAccessDeniedHandler가 먼저 실행된 후, CommonController가 실행됨
		 	INFO : kr.or.ddit.security.CommonController - # access denied
			INFO : kr.or.ddit.security.CommonController - auth : null
		 */
	}

}
