package kr.or.ddit.controller.crud.notice;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.notice.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeModifyController {
	
	@Inject
	private INoticeService noticeService;

	@GetMapping("/update.do")
	public String noticeUpdateForm(int boNo, Model model) {
		NoticeVO noticeVO = noticeService.selectNotice(boNo);
		model.addAttribute("noticeVO", noticeVO);
		model.addAttribute("status", "u");
		return "notice/form";
	}
	
	@PostMapping("/update.do")
	public String noticeUpdate(
			RedirectAttributes ra, HttpServletRequest req,
			NoticeVO noticeVO, Model model) {
		String goPage = "";
		ServiceResult result = noticeService.updateNotice(req, noticeVO);
		if(result.equals(ServiceResult.OK)) {	// 수정 성공
			ra.addFlashAttribute("message", "수정이 완료되었습니다!");
			goPage = "redirect:/notice/detail.do?boNo=" + noticeVO.getBoNo();
		}else {									// 수정 실패
			model.addAttribute("message", "수정에 실패하였습니다!");
			model.addAttribute("noticeVO", noticeVO);
			model.addAttribute("status", "u");
			goPage = "notice/form";
		}
		return goPage;
	}
	
	@PostMapping("/delete.do")
	public String noticeDelete(
			RedirectAttributes ra, HttpServletRequest req,
			int boNo, Model model) {
		String goPage = "";
		ServiceResult result = noticeService.deleteNotice(req, boNo);
		if(result.equals(ServiceResult.OK)) {	// 삭제 성공
			ra.addFlashAttribute("message","삭제가 완료되었습니다!");
			goPage = "redirect:/notice/list.do";
		}else {
			ra.addFlashAttribute("message","서버에러, 다시 시도해주세요!");
			goPage = "redirect:/notice/detail.do?boNo=" + boNo;
		}
		return goPage;
	}
}
