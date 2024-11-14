package kr.or.ddit.controller.intercept;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.vo.CrudMember;

@Controller
@RequestMapping("/intercept")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@PostMapping("/login")
	public String login(CrudMember member, Model model) {
		// 넘겨받은 아이디와 비밀번호가 admin/1234 일때 로그인 성공으로 간주
		String userId = member.getUserId();		// 아이디
		String userPw = member.getUserPw();		// 비밀번호
		if(userId.equals("admin") && userPw.equals("1234")) {	// 로그인 성공
			model.addAttribute("user", member);
		}
		return "login/success";
	}
}
