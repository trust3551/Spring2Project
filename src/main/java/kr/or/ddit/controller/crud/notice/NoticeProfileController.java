package kr.or.ddit.controller.crud.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeProfileController {

	@RequestMapping(value="/profile.do", method = RequestMethod.GET)
	public String noticeProfile() {
		return "notice/profile";
	}
}
