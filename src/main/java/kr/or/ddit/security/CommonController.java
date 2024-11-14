package kr.or.ddit.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

	private static final Logger log = LoggerFactory.getLogger(CommonController.class);
	
	// 접근이 거절되었을 때 동작한 handler
	@GetMapping("/accessError")	// 리턴 뷰 페이지 값이 없을 때는 url을 기반으로 뷰를 찾아감 > 이 이름으로 페이지 만들어줄 것
	public void accessDenied(Authentication auth, Model model) {
		log.info("# access denied");
		log.info("auth : " + auth);
		
		
		/*
		 	auth 출력 정보
		 	org.springframework.security.authentication.UsernamePasswordAuthenticationToken@4i8yddd:
		 	Principal : org.springframework.security.core.Userdetails.User@84h8f4f
		 	Username : member;
		 	Password : [PROTECTED];
		 	Enabled : true;
		 	AccountNonExpired : true;
		 	credentialsNonExpired : true;
		 	Granted Authorities : ROLE_MEMBER;
		 	Credentials : [PROTECTED];
		 	Authenticated : true;
		 	Details : org.springframework.security.web.authentication.WebAuthenticationDetails@23r89u34d:
		 	SessionId : HFVJKN23KLJKQX
		 */
		
		model.addAttribute("msg", "Acces Denied");
	}
	
}
