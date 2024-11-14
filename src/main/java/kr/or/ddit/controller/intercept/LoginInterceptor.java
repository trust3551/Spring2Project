package kr.or.ddit.controller.intercept;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.vo.CrudMember;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static final String USER_INFO = "userInfo";
	
	//지정된 컨트롤러의 동작 이전에 가로채는 역할로 사용한다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("## preHandle...!");
		
		String requstURL = request.getRequestURL().toString();	// http://localhost/~~
		String requstURI = request.getRequestURI();
		
		log.info("requestURL : " + requstURL);
		log.info("requestURI : " + requstURI);
		
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean : " + method.getBean());	// kr.or.ddit.controller.login.LoginController@2314ju234
		// public java.lang.String kr.or.ddit.controller.login.LoginController.loginForm()
		log.info("method : " + methodObj);
		
		HttpSession session = request.getSession();
		if(session.getAttribute(USER_INFO) != null) {
			session.removeAttribute(USER_INFO);
		}
		return true;
	}
	// 타겟 실행 이후 실행 메소드
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("## postHandle...!");
		
		String requstURL = request.getRequestURL().toString();	// http://localhost/~~
		String requstURI = request.getRequestURI();
		
		log.info("requestURL : " + requstURL);
		log.info("requestURI : " + requstURI);
		
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean : " + method.getBean());	// kr.or.ddit.controller.login.LoginController@2314ju234
		// public java.lang.String kr.or.ddit.controller.login.LoginController.loginForm()
		log.info("method : " + methodObj);
		
		HttpSession session = request.getSession();
		
		ModelMap modelMap= modelAndView.getModelMap();
		Object member = modelMap.get("user");
		
		// 넘겨받은 데이터가 null이 아닌경우 세션에 등록한다.
		if(member != null && !((CrudMember)member).getUserId().equals("")) {
			log.info("member.userId : " + ((CrudMember)member).getUserId());
			session.setAttribute("userInfo", member);
			response.sendRedirect("/");
		}else {
			if(requstURI.equals("/intercept/login")&& request.getMethod().equals("POST")) {
				request.setAttribute("message", "접속정보에 문제가 생겼습니다!");
			}
			request.getRequestDispatcher("/WEB-INF/views/login/loginForm.jsp").forward(request, response);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		log.info("## afterCompletion...!");
		
		String requstURL = request.getRequestURL().toString();	// http://localhost/~~
		String requstURI = request.getRequestURI();
		
		log.info("requestURL : " + requstURL);
		log.info("requestURI : " + requstURI);
		
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		
		log.info("Bean : " + method.getBean());	// kr.or.ddit.controller.login.LoginController@2314ju234
		// public java.lang.String kr.or.ddit.controller.login.LoginController.loginForm()
		log.info("method : " + methodObj);
		super.afterCompletion(request, response, handler, ex);
	}

}
