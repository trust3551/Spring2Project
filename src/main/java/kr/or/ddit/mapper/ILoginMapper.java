package kr.or.ddit.mapper;

import kr.or.ddit.vo.notice.NoticeMemberVO;

public interface ILoginMapper {

	public NoticeMemberVO loginCheck(NoticeMemberVO member);
	public NoticeMemberVO idCheck(String memId);
	public int signup(NoticeMemberVO memberVO);
	public void signupAuth(int memNo);
	public NoticeMemberVO findId(NoticeMemberVO memberVO);
	public NoticeMemberVO readByUserId(String username);
}
