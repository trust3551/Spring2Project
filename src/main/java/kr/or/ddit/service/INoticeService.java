package kr.or.ddit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.notice.NoticeFileVO;
import kr.or.ddit.vo.notice.NoticeMemberVO;
import kr.or.ddit.vo.notice.NoticeVO;
import kr.or.ddit.vo.notice.PaginationInfoVO;

public interface INoticeService {
	public NoticeMemberVO loginCheck(NoticeMemberVO member);
	public ServiceResult idCheck(String string);
	public ServiceResult signup(HttpServletRequest req, NoticeMemberVO memberVO);
	public ServiceResult insertNotice(HttpServletRequest req, NoticeVO noticeVO);
	public NoticeVO selectNotice(int boNo);
	public ServiceResult updateNotice(HttpServletRequest req, NoticeVO noticeVO);
	public ServiceResult deleteNotice(HttpServletRequest req, int boNo);
	public int selectNotice(PaginationInfoVO<NoticeVO> pagingVO);
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);
	public NoticeFileVO noticeDownload(int fileNo);
	public NoticeMemberVO findId(NoticeMemberVO memberVO);
}
