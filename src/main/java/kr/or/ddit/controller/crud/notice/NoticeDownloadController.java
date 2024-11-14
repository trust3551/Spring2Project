package kr.or.ddit.controller.crud.notice;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.notice.NoticeFileVO;

@Controller
@RequestMapping("/notice")
public class NoticeDownloadController {
	
	@Inject
	private INoticeService noticeService;
	
	// 파일 다운로드 요청
	@GetMapping("/download.do")
	public View noticeDownload(int fileNo, ModelMap model) {
		NoticeFileVO noticeFileVO = noticeService.noticeDownload(fileNo);
		
		Map<String, Object> noticeFileMap = new HashMap<String, Object>();
		noticeFileMap.put("fileName", noticeFileVO.getFileName());
		noticeFileMap.put("fileSize", noticeFileVO.getFileSize());
		noticeFileMap.put("fileSavepath", noticeFileVO.getFileSavepath());
		model.addAttribute("noticeFileMap", noticeFileMap);
		
		// 리턴되는 noticeDownloadView는 jsp 페이지로 존재하는 페이지 name을 요청하는게 아니라,
		// 클래스를 요청하는것인데 해당 클래스가 스프링에서 제공하는 AbstractView 클래스를 상속받은 클래스입니다.
		// 해당 클래스는 AbstractView를 상속받아 재정의 메소드를 통해서 함수를 재정의할 때 View로 취급될 수 있게 합니다.
		return new NoticeDownloadView();
	}
}
