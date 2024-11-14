package kr.or.ddit.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/*
	 * [4장 : 컨트롤러 응답]
	 * 	
	 * 		1. void 타입
	 * 			- 호출하는 URL과 동일한 뷰 이름을 나타내기 위해 사용한다.
	 */
	// 요청 경로 (/goHome0101)와 동일한 뷰(/goHome0101.jsp)를 가리킨다.
	@RequestMapping(value="/goHome0101", method = RequestMethod.GET)
	public void goHome0101() {
		log.info("goHome0101() 실행...!");
	}
	// 요청 경로(/sub/goHome0102)와 동일한 뷰(/sub/goHome0102.jsp)를 가리킨다.
	@RequestMapping(value="/sub/goHome0102", method = RequestMethod.GET)
	public void goHome0102() {
		log.info("goHome0102() 실행 ...!");
	}
	
	/*
	 * 	2. String 타입
	 * 		- 뷰 파일의 경로와 파일 이름을 나타내기 위해 사용한다.
	 */
	// 반환값이 home0201이므로 뷰(/home0201.jsp)를 가리킨다.
	@RequestMapping(value="/goHome0201", method = RequestMethod.GET)
	public String goHome0201() {
		log.info("goHome0201() 실행...!");
		return "home0201";
	}
	
	// 반환값이 /sub/home0203이므로 뷰(/sub/home0203.jsp)를 가리킨다. 
	@RequestMapping(value="/sub/goHome0203", method = RequestMethod.GET)
	public String goHome0202() {
		log.info("goHome0203() 실행...!");
		return "sub/home0203";
	}
	
	// 반환값이 "redirect:/"로 시작하면 페이지 이동방식은 리다이렉트 방식으로 처리한다.
	@RequestMapping(value="/sub/goHome0204", method= RequestMethod.GET)
	public String goHome0204() {
		log.info("goHome0204() 실행 ...!");
		return "redirect:/sub/goHome0203";
	}
	
	// '/'로 시작하면 웹 애플리케이션의 컨텍스트 경로에 영향을 받지 않는 절대경로를 의미한다.
	// 해당경로 : D드라이브 > workspace > .metadata > .plugins > ,,, > Spring2Project > WEB-INF > views > sub >
	// 			xxxx.jsp
	@RequestMapping(value="/sub/goHome0205", method = RequestMethod.GET)
	public String goHome0205() {
		log.info("goHome0205() 실행...!");
		return "/sub/home0205";
	}
	
	/*
	 *	3. 자바빈즈 클래스 타입(VO)
	 *
	 * 		- JSON 객체 타입의 데이터를 만들어서 반환하는 용도로 사용한다.
	 * 		
	 * 		@ResponseBody를 지정하지 않으면 HTTP 404에러가 발생한다.
	 * 		@ResponseBody가 객체를 리턴하여 객체를 응답 데이터로 보내는 역할을 한다.
	 * 		@ResponseBody의 리턴 default 데이터 형식은 json이다.
	 * 		@ResponseBody 대신에 @RestController를 이용하여 대체할 수 있다.
	 * 
	 * 		** @ResponseBody가 없다면, 리턴하고 있는 데이터의 형식이 페이지 정보가 아니기 때문에,
	 * 			viewResolver가 잡아 페이지를 처리하는것처럼 반응해서 404에러가 발생한다.
	 * 			(페이지를 찾아보려고 했으나 없음.. 그래서 404에러가 발생함)
	 */	
	@ResponseBody
	@RequestMapping(value="/goHome0301", method = RequestMethod.GET)
	public Member goHoem0301() {
		log.info("goHome0301() 실행 ...!");
		/*
		 * 객체를 응답으로 내보낼 때, @ResponseBody에 의한 데이터 형식은 json이다.
		 * 이때, jackson-databind 라이브러리가 설정되어 있지 않은 상태에서 요청 시, 406 에러가 발생한다.
		 * @ResponseBody를 설정하지 않고 객체를 응답으로 내보낼 시, 데이터로 인식되는게 아닌 페이지로 인식이 되므로
		 * 404에러가 발생하므로 어노테이션을 꼭 붙여줘야한다
		 */
		Member member = new Member();
		return member;
	}
	
	/*
	 * 	4. 컬렌션 List 타입
	 * 	
	 * 		- JSON 객체 배열 타입의 데이터를 만들어서 반환하는 용도로 사용한다.
	 */
	// 반환값이 컬렉션 List 타입이면 JSON 객체 배열 타입으로 자동 변환된다.
	@ResponseBody
	@RequestMapping(value = "/goHome0401", method = RequestMethod.GET)
	public List<Member> goHome0401(){
		log.info("goHome0401() 실행 ...!");
		
		// 회원 자바빈즈 클래스(객체)를 넣을 리스트 생성
		List<Member> list = new ArrayList<Member>();
		Member member = new Member();		// 첫번째 회원 자바빈즈 객체
		Member member2 = new Member();		// 두번째 회원 자바빈즈 객체
		list.add(member);
		list.add(member2);
		
		return list;
	}
	
	/*
	 * 	5. 컬렉션 Map 타입
	 * 	
	 * 		- Map 형태의 컬렉션 자료를 JSON 객체 타입의 데이터로 만들어서 반환하는 용도로 사용한다.
	 * 
	 */
	// 반환값이 컬렉션 Map 타입이면 JSON 객체 타입으로 자동으로 변환된다.
	@ResponseBody
	@RequestMapping(value="/goHome0501", method = RequestMethod.GET)
	public Map<String, Member> goHome0501(){
		log.info("goHome0501() 실행...!");
		Map<String, Member> map = new HashMap<String, Member>();
		Member member = new Member();
		Member member2 = new Member();
		map.put("key1", member);
		map.put("key2", member2);
		return map;
	}
	
	/*
	 * 	6. ResponseEntity<Void> 타입
	 * 	
	 * 		- response 할 때 HTTP 헤더 정보와 내용을 가공하는 용도로 사용한다.
	 * 
	 * 		** @ResponseBody와 ResponseEntity
	 * 		- @ResponseBody는 HTTP 응답 본문을 만들어 응답을 내보내주는 역할을 한다.
	 * 		- ResponseEntity는 응답본문, 헤더, 상캐도르를 컨트롤 할 수 있다.
	 * 		- ResponseEntity는  HttpEntity를 상속받고 있는데 HttpEntity는 응답 본문을 컨트롤 할 수 있도록 Body(응답본문)와
	 * 			HttpHeaders를 필드로 설정되어 있다.
	 */
	// Void 클래스는 인스턴스화할 수 없는 자리 표시자 클래스이고 아무런 형태가 아닌데 제네릭타입의 뭔가는 채워야겠어서 채우는
	// Placeholder 같은 느낌의 타입입니다.
	@ResponseBody
	@RequestMapping(value = "/goHome0601", method = RequestMethod.GET)
	public ResponseEntity<Void> goHome0601(){
		log.info("goHome0601() 실행 ...!");
		return new ResponseEntity<Void>(HttpStatus.OK);
		// 내가 요청한 url로 응답이 나가면서 응답데이터로 아무런 값이 전달되지 않는다.
		// 해당 URL 요청 후, 브라우저에서 개발자 도구 이용해서 네트워크 탭을 확인해보면 응답으로 URL과 상태코드 200이 응답으로 나간걸
		// 확인할수 있는데, 이때 상태코드 200만 정상 응답으로 나간걸 확인할 수 있다.
		// Void는 가공된 데이터를 응답으로 내보내는게 아니라, 해당 요청에 의해서 header 정보를 변경하고자 할 때, 시스템 백단에서 
		// 알아서 처리 할 수 있는 기능을 만들어볼때 가용할 수 있겠다.
	}
	
	/*
	 * 	7. ResponseEntity<String> 타입
	 * 
	 * 		- response 할 때 HTTP 헤더 정보와 문자열 데이터를 전달하는 용도로 사용한다.
	 * 
	 */
	// "SUCCESS" 메세지와 200 OK 상태코드를 전송한다.
	@ResponseBody
	@RequestMapping(value="/goHome0701", method = RequestMethod.GET)
	public ResponseEntity<String> goHome0701(){
		log.info("goHome0701() 실행 ...!");
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	/*
	 * 	8. ResponseEntity<자바빈즈 클래스> 타입
	 * 
	 * 		- response 할 때 HTTP 헤더 정보와 객체 데이터를 전달하는 용도로 사용한다.
	 * 
	 */
	// 객체의 JSON 타입의 데이터와 200 OK 상태코드를 전송한다.
	@ResponseBody
	@RequestMapping(value="/goHome0801", method = RequestMethod.GET)
	public ResponseEntity<Member> goHmoe0801(){
		log.info("goHome0801() 실행...!");
		Member member = new Member();
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	/*
	 * 	9. ResponseEntity<List> 타입
	 * 
	 * 		- response 할 때 HTTP 헤더 정보와 객체 배열 데이터를 전달하는 용도로 사용한다.
	 * 
	 */
	// 객체의 JSON 객체 배열 타입의 데이터와 200 OK 상태코드를 전송한다.
	@ResponseBody
	@RequestMapping(value="/goHome0901", method = RequestMethod.GET)
	public ResponseEntity<List<Member>> goHome0901(){
		log.info("goHome0901() 실행...!");
		List<Member> list = new ArrayList<Member>();
		Member member = new Member();
		Member member2 = new Member();
		list.add(member);
		list.add(member2);
		return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
	}
	
	/*
	 *  10. ResponseEntity<Map> 타입
	 *  
	 *  	- response 할 떄 HTTP 헤더 정보와 객체 데이터를 Map 형태로 전달하는 용도로 사용한다.
	 * 
	 */
	// 객체의 JSON 객체 타입의 데이터와 200 OK 상태코드를 전송한다.
	@ResponseBody
	@RequestMapping(value="/goHome1001", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Member>> goHome1001(){
		log.info("goHome1001() 실행 ...!");
		Map<String, Member> map = new HashMap<String, Member>();
		Member member = new Member();
		Member member2 = new Member();
		map.put("key1", member);
		map.put("key2", member2);
		
		return new ResponseEntity<Map<String,Member>>(map, HttpStatus.OK);
	}
	
	/*
	 *  11. ResponseEntity<byte[]> 타입
	 *  
	 *  	- response 할 때 HTTP 헤더 정보와 바이너리 파일 데이터를 전달하는 용도로 사용한다.
	 *  	- 파일을 처리하기 위해서 의존라이브러리인 commons-io를 추가한다. (pom.xml)
	 *  
	 *  	무료/유료 이미지 다운로드 홈페이지를 사용해보면 이미지 미리보기 또는 미리보기 후 다운로드 할 수 있는 기능이 제공됩니다.
	 *  	이와 같은 리턴타입의 형태로 설정해서 내보내는 것과 같다.
	 */
	@ResponseBody
	@RequestMapping(value="/goHome1101", method = RequestMethod.GET)
	public ResponseEntity<byte[]>goHome1101(){
		log.info("goHome1101() 실행 ...!");
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		try {
			in = new FileInputStream("C:\\Users\\PC-14\\Desktop\\images\\som1.jpg");
			headers.setContentType(MediaType.IMAGE_JPEG);
			// IOUtils.toByteArray(in) : InputStream의 내용을 byte[]로 가져옵니다.
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			try {
				in.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	@ResponseBody
	@RequestMapping(value="/goHome1102", method = RequestMethod.GET)
	public ResponseEntity<byte[]> goHome1102(){
		log.info("goHome1102() 실행...!");
		InputStream in = null;
		ResponseEntity<byte[]>entity = null;
		
		String fileName = "DDIT_downloadFile.jpg";
		HttpHeaders headers = new HttpHeaders();
		
		try {
			in = new FileInputStream("C:\\Users\\PC-14\\Desktop\\images\\som1.jpg");
			// MediaType.APPLICATION_OCTET_STREAM은 이진 파일을 위한 기본값입니다.
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);;
			headers.add("Content-Disposition", "attachment; filename=\"" +
					new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			try {
				in.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	
	
	
}
