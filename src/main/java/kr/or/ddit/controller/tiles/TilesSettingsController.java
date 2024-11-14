package kr.or.ddit.controller.tiles;

public class TilesSettingsController {
	/*
	 * -------------------------- 부트스트랩을 이용한 CRUD를 진행합니다 --------------------------------------
	 * 
	 * 		## 페이지 모듈화를 위한 Tiles를 함께 사용하여 CRUD를 진행합니다.
	 * 
	 * 		1. Tiles란?
	 * 		- 어떤 jsp를 템플릿으로 사용하고 템플릿의 각 역영을 어떤 내용을 채울지에 대한 정보를 설정한다.
	 * 		- 하나의 화면들을 만들다보면 공통적이고 반복적으로 생성해야 하는 header, footer와 같은 영역들이 존재한다.
	 * 		우리는 그러한 공통부들을 분리하여 반복적으로 컴포넌트들을 사용하는게 아닌 공통적인 부분은 한번만 가져다 쓰고
	 * 		이렇게, header/footer/menu 등 공통적인 소스를 분리하여 한 화면에서 동적으로 레이아웃을 한곳에 배치하여 설정하고  관리할수
	 * 		있도록 도와주는 페이지 모듈화를 돕는 프레임워크이다.
	 * 
	 * 		- 아래 jsp 들을 이용하여 페이지 모듈화를 진행
	 * 		배경지가 될 temlpate.jsp
	 * 		헤더영역을 담당하는 headt.jsp
	 * 		푸터 영역을 담당하는 footer.jsp
	 * 		동적 컨텐츠를 담당하는 content source
	 * 
	 * 		** 그 외에 페이지는 구현하고자 하는 페이지 레이아웃에 따라 페이지가 구성될 때 추가적으로 영역을 분리하여 작성하면 된다.
	 * 
	 * 		2. Tiles Laytout 구현 설명
	 * 		
	 * 			1) Tilse 의존 관계 등록
	 * 				- titles-core
	 * 				- titles-api
	 * 				- titles-servlet
	 * 				- titles-jsp
	 * 				** 의존 관계 등록 후 Maven > Update Projects로 반영
	 * 
	 * 			2) servlet-context.xml 수정
	 * 				- viewResolver order 순서 2순위로 지정
	 * 				- tilesViewResolver Bean 등록 진행
	 * 
	 * 			3) tiles 설정 위한 xml 생성
	 * 				- /WEB-INF/spring/ tiles-config.xml
	 * 
	 * 			4)  tiles xml에 설정한 layout 설정대로 페이지 생성(jsp)
	 * 
	 * 			***** option 설정
	 * 			1. Mybatipse : Eclipse Plugin for Mybatis
	 * 				- 이클립스나 STS 등의 IDE에서 마이바티스 매퍼와 xml을 손쉽게 관리할 수 있는 플러그인
	 * 				- 기본적으로는 Help > marketplace를 통해서 mybatipse 플러그인을 설치한다.
	 * 					그렇지만, 왠지모르게 플러그인이 향후 에러가 발생한다.(아닐수도 있지만, 발생하는 일이 생김)
	 * 					그래서 되는 마이너 하위버전으로 수동 설치를 진행함(되도록이면 정상작동하는 플러그인을 사용하면 좋을테니말이죠?)
	 * 
	 * 				# Mybatipse plugins 수동 설치 방법
	 * 				- 이클립스 실행 폴더 내, feature 폴더에 jar 파일 추가
	 * 				- 이클립스 실행 폴더 내, plugins 폴더에 jar 파일 추가
	 * 
	 * 			2. log4jdbc.log4j2 설정
	 * 				- log4jdbc.log4j2 라이브러리 설정
	 * 				- log4jdbc.log4j2.properties 설정
	 * 				- dateSourece property 수정
	 * 
	 * 			3. 공지사항 게시판 실습
	 * 
	 * 				3-1) 공지사항 데이터베이스 준비
	 * 					- notice 테이블
	 * 					- noticefile 테이블
	 * 					- noticemember 테이블
	 * 					- noticemember_auth 테이블
	 * 			
	 * 				3-2) 게시판 작성
	 * 					- 게시판 목록, 상세보기 컨트롤러 만들기(notice/NoticeRetrieveController)
	 * 					- 게시판 목록 화면 컨트롤러 메소드 만들기(noticeList:get)
	 * 					- 게시판 목록 화면 만들기 (notice/list.jsp)
	 * 
	 * 					- 게시판 등록 컨트롤러 만들기(notice/NoticeInsertController)
	 * 					- 게시판 등록 화면 컨트롤러 메소드 만들기(noticeForm:get)
	 * 					- 게시판 등록 화면 만들기 (notice/form.jsp)
	 * 
	 * 					- 게시판 등록 기능 컨트롤러 만들기(noticeInsert:post)
	 * 					- 게시판 등록 기능 서비스 인터페이스 메소드 만들기
	 * 					- 게시판 등록 기능 서비스 클래스 메소드 만들기
	 * 					- 게시판 등록 기능 Mapper 인터페이스 메소드 만들기
	 * 					- 게시판 등록 기능 Mapper xml 쿼리 만들기
	 * 					- 여기까지 확인
	 * 
	 * 					- 게시판 상세 화면 컨트롤러 메소드 만들기(noticeDetail:get)
	 * 					- 게시판 상세 화면 서비스 인터페이스 메소드 만들기
	 * 					- 게시판 상세 화면 서비스 클래스 메소드 만들기
	 * 					- 게시판 상세 화면 Mapper 인터페이스 메소드 만들기
	 * 					- 게시판 상세 화면 Mapper xml 쿼리 만들기
	 * 					- 게시판 상세 화면 만들기 (notice/detail.jsp)
	 * 					- 여기까지 확인
	 * 
	 * 					- 게시판 상세 화면 컨트롤러 메소드 만들기(noticeUpdateForm:get)
	 * 					- 게시판 수정 화면 만들기(notice/form.jsp)
	 * 					- 여기까지 확인
	 * 
	 * 					- 게시판 수정 기능 컨트롤러 메소드 만들기(noticeUpdate:post)
	 * 					- 게시판 수정 기능 서비스 인터페이스 메소드 만들기
	 * 					- 게시판 수정 기능 서비스 클래스 메소드 만들기
	 * 					- 게시판 수정 기능 Mapper 인터페이스 메소드 만들기
	 * 					- 게시판 수정 기능 Mapper xml 쿼리 만들기
	 * 					- 여기까지 확인
	 * 
	 * 					- 게시판 삭제 기능 컨트롤러 메소드 만들기(noticeDelete:post)
	 * 					- 게시판 삭제 기능 서비스 인터페이스 메소드 만들기
	 * 					- 게시판 삭제 기능 서비스 클래스 메소드 만들기
	 * 					- 게시판 삭제 기능 Mapper 인터페이스 메소드 만들기
	 * 					- 게시판 삭제 기능 Mapper xml 쿼리 만들기
	 * 					- 여기까지 확인
	 * 
	 * 
	 */
}
