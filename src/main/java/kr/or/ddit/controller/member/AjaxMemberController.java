package kr.or.ddit.controller.member;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ajax")
public class AjaxMemberController {
	
	/*
	 *  9. Ajax 방식 요청 처리
	 */
	
	// ajax 방식 요청 처리 페이지
	@RequestMapping(value="/registerForm", method = RequestMethod.GET)
	public String ajaxRegisterForm() {
		log.info("ajaxRegisterForm() 실행 ...!");
		return "member/ajaxRegisterForm";
	}
	
	// 3) 객체 타입의 JSON 요청 데이터 @RequestBody 어노테이션을 지정하여 자바빈즈 매개변수로 처리한다.
	@ResponseBody
	@RequestMapping(value="/register03", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister03(@RequestBody Member member){
		log.info("ajaxRegister03() 실행 ...!");
		
		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// ****
	// 4) 객체 타입의 JSON 요청 데이터는 문자열 매개변수로 처리할 수 없다.
	@ResponseBody
	@RequestMapping(value="/register04", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister04(String userId){
		// 클라이언트에서 단일 데이터를 비동기 방식으로 보낼 때에, 보내는 쪽에서는 {userId : aaaa, password : 1234}와 같은 데이터로
		// JSON 객체 형태의 데이터가 넘어오고, 받는 쪽의 컨트롤러에서는 @RequestBody 어노테이션을 이용하여 Map<String, String>과 같은
		// 타입으로 데이터를 받는다.
		// 물론 기본 타입의 해당하는 String으로도 받을 수 있지만, 보내는 쪽에서 JSON 객체의 형태를 태워 보내는게 아니라 값만보내는 형식이기
		// 때문에 우리가 직관성을 가지고 파라미터를 가용하는데에 제한이 있을수 있다.(그렇기 때문에 단일 데이터는 웬만해선 Map활용)
		// 여러개의 데이터를 보낼 때에는 당연 객체를 이용하는것이 훨씬 유용
		log.info("ajaxRegister04() 실행 ...!");
		log.info("userId : " + userId );
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	//5) 요청 URL에 쿼리 파라미터를 붙여서 전달하면 문자열 매개변수로 처리한다.
	// 비동기 통신을 통해서 넘겨받는 데이터 형식이 쿼리스트링으로 구성되어 넘어오는 데이터는 기본 타입 String으로 데이터를 바인딩할 수 있다.
	@RequestMapping(value="/register05", method = RequestMethod.POST)
	public ResponseEntity<String> register05(String userId, String password){
		log.info("register05() 실행 ...!");
		log.info("userId : " + userId );
		log.info("password : " + password );
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 7) 객체 배열 타입의 JSON 요청 데이터를 자바빈즈 요소를 가진 리스트 컬렉션 매개변수에 @RequestBody 어노테이션을 지정하여 처리한다.
	@RequestMapping(value="/register07", method = RequestMethod.POST)
	public ResponseEntity<String> register07(
			@RequestBody List<Member> memberList){
		log.info("register07() 실행 ...!");
		
		for(Member member : memberList) {
			log.info("member.userId : " + member.getUserId());
			log.info("member.password : " + member.getPassword());
		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 8) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/register08", method = RequestMethod.POST)
	public ResponseEntity<String> register08(@RequestBody Member member){
		log.info("register08() 실행 ...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		
		Address address = member.getAddress();
		
		if(address != null) {
			log.info("address.postCode : " + address.getPostCode());
			log.info("address.location : " + address.getLocation());
		}else {
			log.info("address is null");
		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 9) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.(리스ㅡㅌ)
		@RequestMapping(value="/register09", method = RequestMethod.POST)
		public ResponseEntity<String> register09(@RequestBody Member member){
			log.info("register09() 실행 ...!");
			log.info("member.userId : " + member.getUserId());
			log.info("member.password : " + member.getPassword());
			
			List<Card> cardList = member.getCardList();
			
			if(cardList != null) {
				log.info("cardList.size() : " +cardList.size());
				
				for(Card card : cardList) {
					log.info("card.no : " + card.getNo());
					log.info("card.validMonth : " + card.getValidMonth());
				}
			}else {
				log.info("cardList is null");
			}
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
}
