package kr.or.ddit.controller.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.FileMember;
import kr.or.ddit.vo.Member;
import kr.or.ddit.vo.MultiFileMember;
import kr.or.ddit.vo.TestMember;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	/*
	 *	[ 5장 : 컨트롤러 요청 처리 ] 
	 *
	 *		1. 컨트롤러 메소드 매개변수
	 *
	 *			- Model : 이동 대상에 전달할 데이터를 가지고 있는 인터페이스
	 *			- RedirectAttributes : 리다이렉트 대상에 전달할 데이터를 가지고 있는 인터페이스
	 *			- 자바빈즈 클래스 : 요청 파라미터를 가지고 있는 자바빈즈 클래스
	 *			- MultipartFile : 멀티파트 요청을 사용해 업로드된 파일 정보를 가지고 있는 인터페이스
	 *			- BindingResult : 도메인 클래스의 입력값 검증 결과를 가지고 있는 인터페이스
	 *			- Locale : 클라이언트 Locale
	 *			- Principal : 클라이언트 인증을 위한 사용자 정보를 가지고 있는 인터페이스
	 *
	 *		2. 요청 처리
	 */
	
	// 요청 처리 페이지
	@RequestMapping(value="/registerForm", method = RequestMethod.GET)
	public String registerForm() {
		log.info("registerForm() 실행...!");
		return "member/registerForm";
	}
	
	// 1) URL 경로 상의 쿼리 파라미터 정보로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="register", method = RequestMethod.GET)
	public String registerByParameter(String userId, String password) {
		log.info("registerByParameter() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return "success";
	}
	
	// 2) URL 경로 상의 경로 변수로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register/{userId}", method = RequestMethod.GET)
	public String registerByPath(@PathVariable String userId) {
		log.info("registerByPath() 실행 ...!");
		log.info("userId : " + userId);
		return "success";
	}
	
	// 3) HTML form 필드명과 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있다. <br/>
    // 그리고, 매개변수의 위치는 순서에 상관없고 오직 매개변수명이 일치하면 요청 데이터를 취득할 수 있따.
	@RequestMapping(value="/register01", method = RequestMethod.POST)
	public String register01(String userId, String password) {
		log.info("register01() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return "success";
	}
	
	// 4) HTML Form 필드값이 숫자일 경우에는 숫자로 타입 변환하여 데이터를 취득할 수 있다.
	// 	 받고자 하는 데이터의 형식에 맞는 타입을 내가 직접 설명할 수 있다.
	@RequestMapping(value="/register02", method = RequestMethod.POST)
	public String register02(String userId, String password, int coin) {
		log.info("register02() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		log.info("coin : " + coin);
		return "success";
	}
	
	/*
	 *  3. 요청 데이터 처리 어노테이션
	 *  
	 *  	- @PathVariable : URL에서 경로 변수 값을 가져오기 위한 어노테이션
	 *  	- @RequestParam : 요청 파라미터 값을 가져오기 위한 어노테이션
	 *  	- @RequestHeader : 요청 헤더 값을 가져오기 위한 어노테이션
	 *  	- @RequestBody : 요청 본문 내용을 가져오기 위한 어노테이션
	 *  	- @CookieValue : 쿠키 값을 가져오기 위한 어노테이션
	 */
	
	@RequestMapping(value="/register/{userId}/{coin}", method = RequestMethod.GET)
	public String registerByPathVariable(
			@PathVariable String userId, @PathVariable int coin) {
		log.info("registerByPathVariable() 실행 ...!");
		log.info("userId : " + userId);
		log.info("coin : " + coin);
		return "success";
	}
	
	// 2) @RequestParam 어노테이션을 사용하여 특정한 HTML Form 필드명을 지정하여 요청을 처리한다.
	// 데이터를 받는 필드명은 동일하게 userId로 하되, 사용하는 변수명은 username 으로 달리 설정할 수 있다.
	// 우리가 페이징을 구현할 때 page를 넘기는 방법을 이녀석을 사용함.
	@RequestMapping(value="/register0202", method = RequestMethod.POST)
	public String register0202(@RequestParam("userId")String username) {
		log.info("register0202() 실행 ...!");
		log.info("username : " + username);
		return "success";
	}
	
	/*
	 * 4. 요청 처리 자바빈즈
	 */
	
	// 1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/beans/register01", method = RequestMethod.POST)
	public String registerJavaBeans01(Member member) {
		log.info("resiterJavaBeans01() 실행 ...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		log.info("member.coin : " + member.getCoin());
		return "success";
	}
	
	// 2) 폼 텍스트 필드 요소 값을 자바빈즈 매개변수와 기본 데이터 타입인 정수 타입 매개변수로 처리한다.
	@RequestMapping(value="/beans/register02", method = RequestMethod.POST)
	public String registerJavaBeans02(Member member, int coin) {
		log.info("resiterJavaBeans02() 실행 ...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		log.info("member.coin : " + member.getCoin());
		log.info("coin : " + coin);
		return "success";
	}
	
	/*
	 *  5. Date 타입 처리
	 *  
	 *  	- 스프링 MVC는 Date 타입의 데이터를 처리하는 여러 방법을 제공한다.
	 */
	// 1) Date 타입의 데이터 형식은 어떻게 설정해서 보내야 에러 없이 받을 수 있을까?
	@RequestMapping(value="/registerByGet01", method = RequestMethod.GET)
	public String registerByGet01(String userId, Date dateOfBirth){
		log.info("registerByGet01() 실행...!");
		log.info("userId : " + userId);
		log.info("dateOfBirth : " + dateOfBirth);
		return "success";
	}
	
	// 2) Member 매개변수와 폼 방식 요청 전달받은 값이 날짜 문자열 형식으로 설정 시 , DAte 탕비으로 받을 수 있는가?
	// VO와 같은 자바빈즈 클래스도 Date 타입으로 선언된 필드를 통해 데이터를 받을 때에는 형식이 맞지 않으면 데이터가 들어오지 않는다.(400에러)
	// 그렇기 때문에 내가 원하는 포멧으로 Date타입의 데이터를 받기 위해서는 누군가의 도움이 필요하다. (6번에서 방법을 확인해보자)
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(Member member){
		log.info("register() 실행...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		log.info("member.dateOfBirth : " + member.getDateOfBirth());
		return "success";
	}
	
	/*
	 *  6. @DateTimeFormat 어노테이션 
	 *  
	 *  	- @DateTimeFormat 어노테이션의 pattern 속성값에 원하는 날짜형식을 지정할 수 있다.
	 */
	
	// 1) Member 매개변수와 폼 방식 요청 전달받은 값이 날짜 문자열 형식으로 설정 시, Date 타입으로 받는가?
	// 기본 타입에 해당하는 Date 타입으로 데이터를 받거나 Member 객체 안의 Date타입으로 설정해둔 필드로 받을때
	// @DateTimeFormat을 이용하여 pattern 속성으로 내가 받고자 하는 데이터 형식을 만들었을때 데이터 바인딩 할 수 있다.
	@RequestMapping(value="registerByGet03", method = RequestMethod.POST)
//	public String registerByGet03(String userId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
	public String registerByGet03(Member member) {
		log.info("registerByGet03() 실행 ..!");
//		log.info("userId : " + userId);
//		log.info("dateOfBirth : " + dateOfBirth);
		log.info("member.userId : " + member.getUserId());
		log.info("member.dateOfBirth : " + member.getDateOfBirth());
		
		return "success";
	}
	
	/*
	 * 7. 폼 방식 요청 처리 
	 */
	
	// 1) 폼 텍스트 필드 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerUserId", method = RequestMethod.POST)
	public String registerUserId(String userId) {
		log.info("registerUserId() 실행 ...!");
		log.info("userId : " + userId);
		return "success";
	}
	
	// 2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerMemberUserId", method = RequestMethod.POST)
	public String registerMemberUserId(Member member) {
		log.info("registerUserId() 실행 ...!");
		log.info("member.userId : " + member.getUserId());
		return "success";
	}
	
	// 3) 폼 비밀번호 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerPassword", method = RequestMethod.POST)
	public String registerPassword(Member member) {
		log.info("registerPassword() 실행 ...!");
		log.info("password : " + member.getPassword());
		return "success";
	}
	
	// 4) 폼 라디오버튼 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerRadio", method = RequestMethod.POST)
	public String registerRadio(String gender) {
		log.info("registerRadio() 실행 ...!");
		log.info("gender : " + gender);
		return "success";
	}
	
	// 5) 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerSelect", method = RequestMethod.POST)
	public String registerSelect(String nationality) {
		log.info("registerSelect() 실행...!");
		log.info("nationality : " + nationality);
		return "success";
	}
	
	// 6) 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value = "/registerMultipleSelect", method = RequestMethod.POST)
	public String registerMultipleSelect01(String cars) {
		// 복수 선택을 통해 넘겨받은 selectbox 데이터는 기본 데이터 타입인 문자열로 받을 경우
		// cars : jeep,bmw, audi와 같이 ','로 묶인 하나의 문자열로 들어온다.
		log.info("registerMultipleSelect01() 실행...!");
		log.info("cars : " + cars);
		return "success";
	}
		
	// 7) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	//    (값이 여러개일 때 (multiple) -> String[])
	@RequestMapping(value = "/registerMultipleSelect02", method = RequestMethod.POST)
	public String registerMultipleSelect02(String[] carsArray) {
		log.info("registerMultipleSelect02() 실행!");
		log.info("carsArray.length : " + carsArray.length);

		if (carsArray != null) {
			log.info("carArray is not null");
			for (int i = 0; i < carsArray.length; i++) {
				log.info("carArray[" + i + "] : " + carsArray[i]);
			}
		} else {
			log.info("carArray is null");
		}
		return "success";
	}
		
	// ****
	// 8) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@RequestMapping(value = "/registerMultipleSelect03", method = RequestMethod.POST)
	public String registerMultipleSelect03(ArrayList<String> carList) {
		// ****
		// 컬렉션 리스트로는 기본 파라미터 자리를 통해서 데이터를 받을 수 없다.(selectbox)
		// 데이터를 가져오려면 배열 형태를 이용하거나 객체를 이용하여 데이터를 얻어온다.
		log.info("registerMultipleSelect03() 실행!");
		log.info("carsArray.length : " + carList.size());

		if (carList != null) {
			log.info("carArray is not null");
			for (int i = 0; i < carList.size(); i++) {
				log.info("carArray[" + i + "] : " + carList.get(i));
			}
		} else {
			log.info("carList is null");
		}
		return "success";
	}
	
	// 9) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value = "/registerCheckbox01", method = RequestMethod.POST)
	public String registerCheckbox01(String hobby) {
		log.info("registerCheckbox01() 실행...!");
		log.info("hobby : " + hobby);
		return "success";
	}
	
	// 10) 폼 체크박스 요소값을 문자열 배열 타입인 매개변수로 처리한다.
	@RequestMapping(value = "/registerCheckbox02", method = RequestMethod.POST)
	public String registerCheckbox02(String[] hobbyArray) {
		log.info("registerCheckbox02() 실행!");
		log.info("carsArray.length : " + hobbyArray.length);

		if (hobbyArray != null) {
			log.info("carArray is not null");
			for (int i = 0; i < hobbyArray.length; i++) {
				log.info("carArray[" + i + "] : " + hobbyArray[i]);
			}
		} else {
			log.info("carArray is null");
		}
		return "success";
	}
	
	// ****
	// 11) 폼 체크박스 요소값을 문자열 배열 타입인 매개변수로 처리한다.
	@RequestMapping(value = "/registerCheckbox03", method = RequestMethod.POST)
	public String registerCheckbox03(ArrayList<String> hobbyList) {
		// ****
		// 받는 타입을 List로 하게되면 No primary or default constructor found for interface java.util.List[ 에러발생
		// 스프링에서는 List 타입으로 데이터를 받는데에 문제가 있다.(아무런 헬퍼 없이 기본 데이터 타입으로 받을때 데이터 바인딩이 안됨)
		// 리스트와 같은 형태의 값을 받으려면 물론 가능한 방법이 있지만, String[]로 여러데이터를 받거나 객체를 이용하여 데이터를 바인딩
		// 하는 방법이 있다. (아무래도 객체가 제일 편하고 빠르겠죠?)
		log.info("registerCheckbox03() 실행!");
		log.info("carsArray.length : " + hobbyList.size());

		if (hobbyList != null) {
			log.info("carArray is not null");
			for (int i = 0; i < hobbyList.size(); i++) {
				log.info("carArray[" + i + "] : " + hobbyList.get(i));
			}
		} else {
			log.info("carList is null");
		}
		return "success";
	}
	
	// 12) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox04", method = RequestMethod.POST)
	public String registerCheckbox04(String developer) {
		log.info("registerCheckbox04() 실행 ...!");
		log.info("developer : " + developer);
		return "success";
	}
	
	// 13) 폼 체크박스 요소값을 기본 데이터 타입인 불리언 타입 매개변수로 처리한다.
	@RequestMapping(value = "/registerCheckbox05", method = RequestMethod.POST)
	public String registerCheckbox05(boolean foreigner) {
		log.info("registerCheckbox05() 실행 ...!");
		log.info("foreigner : " + foreigner);
		return "success";
	}
	
	// 14) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value = "/registerAddress", method = RequestMethod.POST)
	public String registerAddress(Address address) {
		log.info("registerAddress() 실행 ...!");
		log.info("adress.postCode : " + address.getPostCode());
		log.info("adress.location : " + address.getLocation());
		return "success";
	}
	
	// 15) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value = "/registerMemberAddress", method = RequestMethod.POST)
	public String registerMemberAddress(Member member) {
		// 넘겨받은 데이터 postCode와 location은 Member 클래스 내에 Address라는 클래스의 필드로 존재한다.
		// 그렇다면 Member라는 클래스로 데이터를 받고자 할때에 제일 먼저 만나는건 Member라는 자바빈즈 클래스 객체고
		// Member의 문을 열고 들어갔을때 postCode와 location은 Address라는 공간을 거쳐야만 찾을 수 있는 필드기 때문에
		// 제일먼저 address라는 키가 필요하고 그 다음에 Address클래스 내에 존재하는 postCode와 location이라는 키가
		// 다음 step으로 필요하다.
		// 그래서, form태그 내에 name설정을 Member안에서 필드로 존재하는 Address의 필드명인 address.postCode와
		// address.location이라는 name의 키값으로 설정해야만 Address에 데이터가 자동 바인딩되고 Address 클래스가
		// 자동인스턴스가 만들어진다.
		log.info("registerMemberAddress() 실행 ...!");
		
		Address address = member.getAddress();
		
		if(address != null) {
			log.info("member.adress.postCode : " + member.getAddress().getPostCode());
			log.info("member.adress.location : " + member.getAddress().getLocation());
		}
		return "success";
	}
	
	// 16) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다. (중첩된 자바빈즈 클래스 객체가 들어있는 List)
	@RequestMapping(value="/registerMemberCardList", method = RequestMethod.POST)
	public String registerMemberCardList(Member member) {
		log.info("registerMemberCardList() 실행 ...!");
		
		List<Card> cardList = member.getCardList();
		
		if(cardList != null) {
			log.info("cardList.size() : " + cardList.size());
			
			for(int i = 0; i < cardList.size(); i++) {
				Card card = cardList.get(i);
				log.info("card.no : " + card.getNo());
				log.info("card.validMonth : " + card.getValidMonth());
			}
		}else {
			log.info("cardList is null");
		}
		return "success";
	}
	
	// 17) 폼 텍스트 영역 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerTextarea", method= RequestMethod.POST)
	public String registerTextarea(String introduction) {
		log.info("registerTextarea() 실행 ...!");
		log.info("introduction : " + introduction);
		return "success";
	}
	
	// 회원가입에 필요한 전체 폼 페이지(문제)
	// 요청 페이지
	@RequestMapping(value="/registerAllForm", method = RequestMethod.GET)
	public String registerAllForm() {
		log.info("registerAllForm() 실행...!");
		
		return "member/registerAllForm";
	}
	
	// registerAllForm 페이지에서 데이터 입력 후 데이터를 전송할 메소드
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String registerUser(TestMember member, Model model) {
		log.info("member : " + member);
		
		model.addAttribute("member", member);
		return "member/registerAllResult";
	}
	
	/*
	 *	8. 파일업로드 폼 방식 요청 처리
	 *
	 *		- 파일 업로드 폼 방식 요청 처리를 위한 의존 라이브러리 추가
	 *		- pom.xml 내, commons-io, commons-fileupload 라이브러리 의존관계 등록
	 *		- web.xml에 모든 경로에 대한 MultipartFilter를 등록
	 * 
	 */
	
	// 3) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile03", method = RequestMethod.POST)
	public String registerFile03(Member member, MultipartFile picture) {
		log.info("registerFile03() 실행 ...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		return "success";
	}
	
	// 4) 파일 업로드 폼 파일 요소값과 텍스트 필드 요소 값을 FileMember 타입의 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile04", method = RequestMethod.POST)
	public String registerFile04(FileMember fileMember) {
		log.info("rsgisterFile04() 실행 ...!");
		log.info("fileMember.userId : " + fileMember.getUserId() );
		log.info("fileMember.paswword : " + fileMember.getPassword() );
		
		MultipartFile picture = fileMember.getPicture();
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		return "success";
	}
	
	// 5) 여러 개의 파일업로드를 폼 파일 요소값을 여러 개의 MultipartFile 매개변수로 처리한다.
	@RequestMapping(value="/registerFile05", method = RequestMethod.POST)
	public String registerFile05(MultipartFile picture, MultipartFile picture2) {
		log.info("rsgisterFile05() 실행 ...!");
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		log.info("originalName2 : " + picture2.getOriginalFilename());
		log.info("size2 : " + picture2.getSize());
		log.info("contentType2 : " + picture2.getContentType());
		
		return "success";
	}
	
	// 6) 여러 개의 파일업로드를 폼 파일 요소값을 MultipartFile 타입의 요솔르 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	// 기본 타입 컬렉션 리스트로는 파일 데이터를 받을 수 없다.
	@RequestMapping(value="registerFile06", method = RequestMethod.POST)
	public String registerFile06(List<MultipartFile> pictureList) {
		log.info("rsgisterFile06() 실행 ...!");
		log.info("pictureList.size() : " + pictureList.size());
		
		for(int i = 0; i < pictureList.size(); i++) {
			log.info("originalName : " + pictureList.get(i).getOriginalFilename());
			log.info("size : " + pictureList.get(i).getSize());
			log.info("contentType : " + pictureList.get(i).getContentType());
		}
		return "success";
	}
	
	// 7) 여러개의 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultiFileMember 타입의 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile07", method = RequestMethod.POST)
	public String registerFile07(MultiFileMember multiFileMember) {
		log.info("rsgisterFile07() 실행 ...!");
		List<MultipartFile> pictureList = multiFileMember.getPictureList();
		log.info("pictureList.size() : " + pictureList.size());
		
		for(int i = 0; i < pictureList.size(); i++) {
			MultipartFile picture = pictureList.get(i);
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
			log.info("-------------------------------------------");
		}
		return "success";
	}
	
	// 8) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 타입의 배열 매개변수로 처리한다.
	@RequestMapping(value="/registerFile08", method = RequestMethod.POST)
	public String registerFile08(MultipartFile[] pictureArray) {
		log.info("rsgisterFile08() 실행 ...!");
		log.info("pictureArray.length : " + pictureArray.length);
		
		for(MultipartFile picture : pictureArray) {
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
			log.info("-------------------------------------------");
		}
		return "success";
	}

}
