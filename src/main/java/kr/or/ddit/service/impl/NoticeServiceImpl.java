package kr.or.ddit.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.mapper.ILoginMapper;
import kr.or.ddit.mapper.INoticeMapper;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.notice.NoticeFileVO;
import kr.or.ddit.vo.notice.NoticeMemberVO;
import kr.or.ddit.vo.notice.NoticeVO;
import kr.or.ddit.vo.notice.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService {

	@Inject
	private ILoginMapper loginMapper;
	
	@Inject
	private INoticeMapper noticeMapper;
	
	@Inject
	private PasswordEncoder pe;
	
	@Override
	public NoticeMemberVO loginCheck(NoticeMemberVO member) {
		return loginMapper.loginCheck(member);
	}

	@Override
	public ServiceResult idCheck(String memId) {
		ServiceResult result = null;
		NoticeMemberVO member = loginMapper.idCheck(memId);
		if(member != null) {
			result = ServiceResult.EXIST; 		// 아이디와 일치하는 회원정보 존재 
		}else {
			result = ServiceResult.NOTEXIST;	// 아이디와 일치하는 회원정보 없음
		}
		return result;
	}

	@Override
	public ServiceResult signup(HttpServletRequest req, NoticeMemberVO memberVO) {
		ServiceResult result = null;
		
		// 넘겨받은 memberVO안에 들어있는 프로필 이미지 파일로 파일 업로드를 먼저 진행한다.
		
		// 프로필 이미지 파일을 저장할 경로
		String uploadPath = req.getServletContext().getRealPath("/resources/profile");
		
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String profileImg = "";	// 회원정보에 추가할 프로필 이미지 경로
		
		try {
			MultipartFile profileImgFile = memberVO.getImgFile();	// 넘겨받은 회원정보에서 프로필 데이터 가져오기
			
			// 넘겨받은 파일 데이터가 존재할 때
			if(profileImgFile != null && profileImgFile.getOriginalFilename() != null &&
					!profileImgFile.getOriginalFilename().equals("")) {
				String fileName = UUID.randomUUID().toString(); 	// UUID 파일명 생성
				fileName += "_" + profileImgFile.getOriginalFilename();	// UUID_ 원본파일명
				uploadPath += "/" + fileName; 	// 업로드 하기 위한 경로 생성(/resources/profile/UUID_원본파일명)
				profileImgFile.transferTo(new File(uploadPath));	// 파일복사
				profileImg = "/resources/profile/" + fileName;	// 파일 복사가 일어난 파일의 위치로 접근하기 위한 URI 설정
			}
			memberVO.setMemProfileimg(profileImg);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 비밀번호 암호화(스프링 시큐리티 적용시)
		memberVO.setMemPw(pe.encode(memberVO.getMemPw()));
		
		int status = loginMapper.signup(memberVO);
		if(status > 0) {	// 등록성공
			// 항명의 회원이 등록될 때 하나의 권한을 무조건 가질 수 있도록 권한 등록(스프링 시큐리티 적용 시 사용 예정)
			loginMapper.signupAuth(memberVO.getMemNo());
			result = ServiceResult.OK;
		}else {				// 등록 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult insertNotice(HttpServletRequest req, NoticeVO noticeVO) {
		ServiceResult result = null;
		
		int status = noticeMapper.insertNotice(noticeVO);
		
		if(status > 0) {	// 등록성공
			
			// 공지사항 게시글 등록 시 업로드 한 파일 목록을 가져온다
			List<NoticeFileVO> noticeFileList = noticeVO.getNoticeFileList();
			
			// 공지사항 파일 업로드 처리
			noticeFileUpload(noticeFileList, noticeVO.getBoNo(), req);
			
			result = ServiceResult.OK;
		}else {				// 등록실패	
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	private void noticeFileUpload(List<NoticeFileVO> noticeFileList, int boNo, HttpServletRequest req) {
		// 공지사항 게시판에서 등록된 파일은 기본 '/resources/notice/' 경로로 설정
		String savedPath = "/resources/notice/";
		
		if(noticeFileList != null && noticeFileList.size() > 0) {	// 넘겨받은 파일 데이터가 존재할 때
			// 파일을 하나씩 꺼내서 파일 복사를 진행한다.
			for(NoticeFileVO noticeFileVO : noticeFileList) {
				String saveName = UUID.randomUUID().toString();		// UUID의 랜덤 파일명 생성
				saveName += "_" + noticeFileVO.getFileName();
				// '/resouces/notice/2/'와 같은 폴더 구조를 생성
				String saveLocate = req.getServletContext().getRealPath(savedPath + boNo);
				File file = new File(saveLocate);
				if(!file.exists()) {
					file.mkdirs();
				}
				saveLocate += "/" + saveName;	// 파일 복사를 위한 경로 설정
				
				// 파일 데이터를 추가하기 위한 준비
				noticeFileVO.setBoNo(boNo);
				noticeFileVO.setFileSavepath(saveLocate);
				noticeMapper.insertNoticeFile(noticeFileVO);
				
				File saveFile = new File(saveLocate);
				try {
					noticeFileVO.getItem().transferTo(saveFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	// 파일복사
			}
		}
	}

	@Override
	public NoticeVO selectNotice(int boNo) {
		noticeMapper.incrementHit(boNo); 	// 조회수 증가
		return noticeMapper.selectNotice(boNo);
	}

	@Override
	public ServiceResult updateNotice(HttpServletRequest req, NoticeVO noticeVO) {
		ServiceResult result = null;
		int status = noticeMapper.updateNotice(noticeVO);
		if(status > 0 ) {		// 수정성공
			// 게시글 정보에서 파일 목록 가져오기
			List<NoticeFileVO> noticeFileList = noticeVO.getNoticeFileList();
			
			try {
				// 공지사항 파일 업로드
				noticeFileUpload(noticeFileList, noticeVO.getBoNo(), req);
				
				// 기존에 등록되어 있는 파일 목록들 중, 수정하기 위해 X버튼을 눌러 삭제 처리로 넘겨준 파일 번호들
				Integer[] delNoticeNo = noticeVO.getDelNoticeNo();
				if(delNoticeNo != null) {
					for(int i = 0; i < delNoticeNo.length; i++) {
						// 삭제할 파일 번호 목록들 중, 파일번호에 해당하는 공지사항 파일 정보를 가져온다.
						NoticeFileVO noticeFileVO = noticeMapper.selectNoticeFile(delNoticeNo[i]);
						noticeMapper.deleteNoticeFile(delNoticeNo[i]);	// 파일번호에 해당하는 파일 데이터를 삭제
						File file = new File(noticeFileVO.getFileSavepath());
						file.delete(); 	// 기존 파일이 업로드 되어 있던 경로에 파일 삭제
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			result = ServiceResult.OK;
		}else {					// 수정 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteNotice(HttpServletRequest req, int boNo) {
		ServiceResult result = null;
		
		// 공지사항 파일 데이터를 삭제하기 위한 준비
		// 게시글 번호에 해당하는 공지사항 게시글 정보 가져오기(파일정보를 가져옴)
		NoticeVO noticeVO = noticeMapper.selectNotice(boNo);
		noticeMapper.deleteNoticeFirlByBoNo(boNo);	// 게시글 번호에 해당하는 파일 데이터 삭제
		
		int status = noticeMapper.deleteNotice(boNo);
		if(status > 0 ) {		// 삭제 성공
			
			// 공지사항 게시글 정보에서 파일 목록 가져오기
			List<NoticeFileVO> noticeFileList = noticeVO.getNoticeFileList();
			
			try {
				if(noticeFileList != null) {
					// D:\JSP_SPRING\workspace_spring2\.metadata\....\resources\notice\134
					// alsfksjad-asdfhjksdfhkla-sdhjfksdahf _dditFile.jpg
					String[] filePath = noticeFileList.get(0).getFileSavepath().split("/");
					String path = filePath[0];
					deleteFolder(req, path);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			result = ServiceResult.OK;
		}else {					// 삭제 실패
			result = ServiceResult.FAILED;
		}
		return result;
	}

	private void deleteFolder(HttpServletRequest req, String path) {
		// UUID_원본파일명 전 폴더 경로를 folder 파일객체로 잡는다.
		File folder = new File(path);
		try {
			if(folder.exists()) {	// 경로가 존재한다면
				File[] fileList = folder.listFiles();	// 폴더 안에 있는 파일들의 목록을 가져온다.
				
				for(int i =0; i< fileList.length; i++) {
					if(fileList[i].isFile()) {	// 폴더 안의 있는 파일이 파일일때
						fileList[i].delete();
					}else {	// 폴더안의 있는 파일이 폴더일 때 재귀함수 호출(폴더안으로 접근 후 파일인지 폴더인지 체크 후 삭제)
						deleteFolder(req, fileList[i].getPath());
					}
				}
				folder.delete();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int selectNotice(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeMapper.selectNoticeCount(pagingVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeMapper.selectNoticeList(pagingVO);
	}

	@Override
	public NoticeFileVO noticeDownload(int fileNo) {
		NoticeFileVO noticeFileVO = noticeMapper.noticeDownload(fileNo);
		if(noticeFileVO == null) {
			throw new RuntimeException();
		}
		
		// 다운로드 횟수 증가
		noticeMapper.incrementNoticeFileDowncount(fileNo);
		return noticeFileVO;
	}

	@Override
	public NoticeMemberVO findId(NoticeMemberVO memberVO) {
		
		return loginMapper.findId(memberVO);
	}
}
