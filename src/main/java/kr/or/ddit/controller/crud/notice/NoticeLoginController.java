package kr.or.ddit.controller.crud.notice;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.notice.NoticeMemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeLoginController {
		
		@Inject
		private INoticeService noticeService;
	
		@RequestMapping(value="/login.do", method = RequestMethod.GET)
		public String noticeLogin(Model model) {
			model.addAttribute("bodyText", "login-page");
			return "conn/login";
		}
		
		@PostMapping("/loginCheck.do")
		public String loginCheck(
				HttpServletRequest req,
				NoticeMemberVO member, Model model) {
			String goPage = "";
			Map<String, String>errors = new HashMap<String, String>();
			if(StringUtils.isBlank(member.getMemId())) {
				errors.put("memId","아이디를 입력해주세요.");
			}
			if(StringUtils.isBlank(member.getMemPw())) {
				errors.put("memPw","비밀번호를 입력해주세요.");
			}
			if(errors.size()>0) {	// 에러가 발생
				model.addAttribute("errors", errors);
				model.addAttribute("member", member);
				model.addAttribute("bodyText", "login-page");
				goPage = "conn/login";
			}else {				// 정상적인 데이터가 입력됨
				NoticeMemberVO memberVO = noticeService.loginCheck(member);
				if(memberVO != null) {	// 회원(로그인 성공)
					// 로그인 성공 후 전달받은 memberVO의 데이터를 이용해서 세션처리
					HttpSession session = req.getSession();
					session.setAttribute("SessionInfo", memberVO);
					goPage = "redirect:/notice/list.do";	// 게시판 목록
				}else {
					model.addAttribute("message", "로그인 정보를 정확하게 입력해주세요.");
					model.addAttribute("member", member);
					model.addAttribute("bodyText", "login-page");
					goPage = "conn/login";
				}
			}
			return goPage;
		}
		
		
		@RequestMapping(value="/signup.do", method = RequestMethod.GET)
		public String signupForm(Model model) {
			model.addAttribute("bodyText", "register-page");
			return "conn/register";
		}
		
		@ResponseBody
		@PostMapping("/idCheck.do")
		public ResponseEntity<ServiceResult> idCheck(
				@RequestBody Map<String, String> map){
			log.info("# 넘겨받은 아이디 : " + map.get("memId"));
			ServiceResult result = noticeService.idCheck(map.get("memId"));
			return new ResponseEntity<ServiceResult>(result, HttpStatus.OK) ;
		}
		@PostMapping("/signup.do")
		public String signup(
				RedirectAttributes ra, HttpServletRequest req,
				NoticeMemberVO memberVO, Model model) {
			String goPage="";
			Map<String, String> errors = new HashMap<String, String>();
			if(StringUtils.isBlank(memberVO.getMemId())) {
				errors.put("memId", "아이디를 입력해주세요!");
			}
			if(StringUtils.isBlank(memberVO.getMemId())) {
				errors.put("memPw", "비밀번호를 입력해주세요!");
			}
			if(StringUtils.isBlank(memberVO.getMemId())) {
				errors.put("memName", "이름을 입력해주세요!");
			}
			if(errors.size() > 0 ) {	// 에러 발생
				model.addAttribute("errors", errors);
				model.addAttribute("member", memberVO);
				model.addAttribute("bodyText", "register-page");
				goPage="conn/register";
			}else {				// 정상적인 데이터
				ServiceResult result = noticeService.signup(req, memberVO);
				if(result.equals(ServiceResult.OK)) {
					ra.addFlashAttribute("message", "회원가입을 완료하였습니다!");
					goPage = "redirect:/notice/login.do";
				}else {
					model.addAttribute("message", "서버에러, 다시 시도해주세요!");
					model.addAttribute("member", memberVO);
					model.addAttribute("bodyText", "register-page");
					goPage = "conn/register";
				}
			}
			return goPage;
		}
		
		@GetMapping("/forget.do")
		public String loginForget() {
			return "conn/forget";
		}
		
		// 아이디 찾기
		@PostMapping("/id.do")
		public ResponseEntity<NoticeMemberVO> idForgetProcess(@RequestBody NoticeMemberVO memberVO) {
			log.info("check : " + memberVO.getMemEmail());
			log.info("check : " + memberVO.getMemName());
			NoticeMemberVO member = noticeService.findId(memberVO);
			log.info("check : " + member);
			
			return new ResponseEntity<NoticeMemberVO>(member,HttpStatus.OK);
		}
		
		// 비밀번호 찾기
		@PostMapping("/pw.do")
		public void pwForgetProcess() {
			
		}

		
		
}
