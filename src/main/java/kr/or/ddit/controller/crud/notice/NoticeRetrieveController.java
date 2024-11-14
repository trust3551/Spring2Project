package kr.or.ddit.controller.crud.notice;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.notice.NoticeVO;
import kr.or.ddit.vo.notice.PaginationInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeRetrieveController {
	
		
		@Inject
		private INoticeService noticeService;

		// 스프링 시큐리티적용(ROLE_MEMBER, ROLE_ADMIN만 접근 가능)
		@PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')")
		@RequestMapping(value="/list.do", method = RequestMethod.GET)
		public String noticeList(
				@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
				@RequestParam(required = false, defaultValue = "title") String searchType,
				@RequestParam(required = false) String searchWord, Model model
				) {
			PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>(10, 5);

			// 검색 기능 추가
			if(StringUtils.isNotBlank(searchWord)) {
				pagingVO.setSearchWord(searchWord);
				pagingVO.setSearchType(searchType);
				model.addAttribute("searchWord", searchWord);
				model.addAttribute("searchType", searchType);
			}
			// startRow, endRow, startPage, endPage를 결정한다,.
			pagingVO.setCurrentPage(currentPage);
			
			// 총 게시글 수를 이용하여 총 페이지수를 결정하기 위해 총 게시글 수인 totalRecord를 얻어온다.
			int totalRecord = noticeService.selectNotice(pagingVO);
			// totalPage를 결정한다.
			pagingVO.setTotalRecord(totalRecord);
			// 총 데시글 수 및 총 페이지수, startRow, endRow의 값들을 이용하여 초기 1페이지에 들어있는 총 screenSize 개수만큼의
			// 리스트 데이터를 가져온다
			List<NoticeVO> dataList = noticeService.selectNoticeList(pagingVO);
			pagingVO.setDataList(dataList);
			model.addAttribute("pagingVO", pagingVO);
			
			return "notice/list";
		}
		
		@RequestMapping(value="/detail.do", method = RequestMethod.GET)
		public String noticeDetail(int boNo, Model model) {
			NoticeVO noticeVO = noticeService.selectNotice(boNo);
			model.addAttribute("noticeVO", noticeVO);
			return "notice/detail";
		}
}
