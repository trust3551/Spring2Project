package kr.or.ddit.controller.crud.notice;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.CustomUser;
import kr.or.ddit.vo.notice.NoticeMemberVO;
import kr.or.ddit.vo.notice.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	
	@Inject
	private INoticeService noticeService;
	
	@Inject
	private TelegramBotSendController tsc;

	@RequestMapping(value="/form.do", method= RequestMethod.GET)
	public String noticeForm() {
		return "notice/form";
	}
	
	@PostMapping("/insert.do")
	public String noticeInsert(
			HttpServletRequest req, RedirectAttributes ra,
			NoticeVO noticeVO, Model model) throws Exception {
		String goPage = "";		// 이동할 페이지 정보
		
		// 넘겨받은 데이터 검증 후, 에러가 발생한 데이터에 대한 에러정보 담을 공간
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(noticeVO.getBoTitle())) {	// 제목 데이터가 누락되었을때
			errors.put("boTitle", "제목을 입력해주세요!");
		}
		if(StringUtils.isBlank(noticeVO.getBoContent())) {	// 내용 데이터가 누락되었을때
			errors.put("boContent", "내용을 입력해주세요!");
		}
		
		if(errors.size() > 0 ) {	// 에러발생
			model.addAttribute("errors", errors);
			model.addAttribute("noticeVO", noticeVO);
			goPage = "notice/form";
		}else {					// 정상적인 데이터가 전달
			// [방법1] SESSION을 이용한 방법
			// HttpSession :: 로그인 처리 후 세션정보에서 얻어온 회원정보를 활용하기 위한 준비
//			HttpSession session = req.getSession();
			// SessionInfo 키를 이용하여 세션에서 회원정보 가져오기
//			NoticeMemberVO memberVO = (NoticeMemberVO)session.getAttribute("SessionInfo");
			
			// [방법2] Security 적용시 SecurityContextHolder 이용한 방법
			CustomUser user = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			NoticeMemberVO memberVO = user.getMember();
			
			if(memberVO != null) {	// 로그인 후 게시글 작성
				noticeVO.setBoWriter(memberVO.getMemId());
				ServiceResult result = noticeService.insertNotice(req, noticeVO);
				if(result.equals(ServiceResult.OK)) {
					
					// 텔레그램 봇 API를 이용한 실시간 알림
					tsc.send(memberVO.getMemName(), noticeVO.getBoTitle(), "notice");
					
					ra.addFlashAttribute("message","게시글 등록 완료!");
					goPage = "redirect:/notice/detail.do?boNo=" + noticeVO.getBoNo();
				}else {
					model.addAttribute("message", "서버에러, 다시 시도해주세요!");
					goPage = "notice/form";
				}		
			}else {					// 로그인 안함
				ra.addFlashAttribute("message", "로그인 후 사용 가능합니다!");
				goPage = "redirect:/notice/login.do";
			}
		}
		return goPage;
	}
}
