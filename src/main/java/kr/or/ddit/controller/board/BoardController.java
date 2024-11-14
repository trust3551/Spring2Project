package kr.or.ddit.controller.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Board;



@Controller
@RequestMapping("/board")
public class BoardController {
	
	/*
	 * [3장. 컨트롤러 요청 매핑]
	 * 
	 * 1. 요청 경로 매핑
	 * 
	 * 		- @RequestMapping의 value 속성에 요청 경로를 설정한다.
	 * 		- 요청 경로는 반드시 설정해야 하는 필수 정보이다.
	 * 		- 속성이 하나일 때는 속성명을 생략할 수 있다. (default는 value속성)
	 * 		- 컨트롤러의 클래스 레벨과 메소드 레벨로 지정할 수 있다.
	 * 		- 클래스 레벨로 요청 경로를 지정하면 메소드 레벨에서 지정한 경로의 기본 경로로 취급된다.
	 * 		- 클래스 레벨의 요청 경로에 메소드 레벨의 요청 경로를 덧붙인 형태가 최종 경로가 된다.
	 * 		
	 */
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public void registerForm() {
		log.info("registerForm() 실행 ...!");
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public void modifyForm() {
		log.info("modifyForm() 실행 ...!");
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void list() {
		log.info("list() 실행 ...!");
	}
	
	
	/*
	 * 2. 경로 패턴 매핑
	 * 
	 * 		- 요청 경로를 동적으로 표현이 가능한 경로 패턴을 지정할 수 있다.
	 * 		- URL 경로 상의 변하는 값을 경로 변수로 취급한다.
	 * 		- 경로 변수에 해당하는 값을 파라미터 변수에 설정할 수 있다.
	 * 
	 */
	
	@RequestMapping(value="/read/{boardNo}")
	public String read(@PathVariable("boardNo") int boardNo) {
		log.info("read() 실행...!");
		log.info("경로 상의 포함된 boardNo : " + boardNo);
		return "board/read";
	}
	
	/*
	 * 	3. HTTP 메소드 매핑
	 * 
	 * 		- method 속성을 사용하여 HTTP 메소드를 매핑 조건으로 지정할 수 있다.
	 * 		- 화면으로 응답하는 경우에는 HTTP 메소드로 GET 방식과 POST 방식 두가지를 사용할수 있다.
	 */
	
	@RequestMapping(value="/formHome", method = RequestMethod.GET)
	public String formHome() {
		log.info("formHome() 실행...!");
		return "formHome";
	}
	
	// register 경로에 GET 방식
	@RequestMapping(value="/http/register", method = RequestMethod.GET)
	public String registerFormHttp() {
		log.info("registerFormHttp() 실행 ...!");
		return "success";
	}
	
	// register 경로에 POST 방식
	@RequestMapping(value="/http/register", method = RequestMethod.POST)
	public String registerHttp() {
		log.info("registerHttp() 실행 ...!");
		return "success";
	}
	
	// modify 경로에 GET 방식
	@RequestMapping(value="/http/modify", method= RequestMethod.GET)
	public String modifyFormHttp() {
		log.info("modifyFormHttp() 실행 ...!");
		return "success";
	}
	
	// modify 경로에 POST 방식
	@RequestMapping(value="/http/modify", method= RequestMethod.POST)
	public String modifyHttp() {
		log.info("modifyHttp() 실행 ...!");
		return "success";
	}
	
	// remove 경로에 POST 방식
	@RequestMapping(value="/http/remove", method= RequestMethod.POST)
	public String removeHttp() {
		log.info("removeHttp() 실행 ...!");
		return "success";
	}
	
	// list 경로에 GET 방식
	@RequestMapping(value="/http/list", method= RequestMethod.GET)
	public String listHttp() {
		log.info("listHttp() 실행 ...!");
		return "success";
	}
	
	/*
	 *  4. Params 매핑
	 *  
	 *  	- 요청 파라미터를 매핑 조건으로 지정하는 경우에는 Params 속성을 사용한다.
	 *  	- 버튼이나 링크에 따라 호출할 메소드를 바꿔야 할 때 사용한다.
	 *  
	 *  	*** Params 매핑 테스트 페이지
	 *  		> board/list, modify, read, register, remove.jsp 를 이용
	 * 
	 */
	
	// /board/get 경로, GET방식, "register" 요청 파라미터에 대한 처리
	@RequestMapping(value="/get", method=RequestMethod.GET, params = "register")
	public String registerFormParamsGet() {
		log.info("registerFormParamsGet() 실행 ... !");
		return "board/register";
	}
	
	// /board/post 경로, POST방식, "register" 요청 파라미터에 대한 처리
	@RequestMapping(value="/post", method=RequestMethod.POST, params = "register")
	public String registerParamsPost() {
		log.info("registerParamsPost() 실행...!");
		return "board/list";
	}
	
	// /board/get 경로, GET 방식, "modify" 요청 파라미터에 대한 처리
	@RequestMapping(value="/get", method = RequestMethod.GET, params = "modify")
	public String modifyFormParamsGet() {
		log.info("modifyFormParamsGet() 실행 ...!");
		return "board/modify";
	}
	
	// /board/get 경로, POST 방식, "modify" 요청 파라미터에 대한 처리
	@RequestMapping(value="/post", method = RequestMethod.POST, params = "modify")
	public String modifyFormParamsPost() {
		log.info("modifyFormParamsPost() 실행 ...!");
		return "board/list";
	}
	
	// /board/get 경로, GET방식, "remove" 요청 파라미터에 대한 처리
	@RequestMapping(value="/get", method = RequestMethod.GET, params = "remove")
	public String removeFormParamsGet() {
		log.info("removeFormParamsGet() 실행 ...!");
		return "board/remove";
	}
	
	// /board/post 경로, POST방식, "remove" 요청 파라미터에 대한 처리
	@RequestMapping(value="/post", method = RequestMethod.POST, params = "remove")
	public String removeFormParamsPost() {
		log.info("removeFormParamsPost() 실행 ...!");
		return "board/list";
	}
	
	// /board/get 경로, GET방식, "list" 요청 파라미터에 대한 처리
	@RequestMapping(value="/get", method = RequestMethod.GET, params = "list")
	public String listParamsGet() {
		log.info("listParamsGet() 실행 ...!");
		return "board/list";
	}
	
	// /board/get 경로, GET방식, "list" 요청 파라미터에 대한 처리
	@RequestMapping(value="/get", method = RequestMethod.GET, params = "read")
	public String readParamsGet() {
		log.info("readParamsGet() 실행 ...!");
		return "board/read";
	}
	
	/*
	 * 5. Headers 매핑
	 * 
	 * 		- 요청 헤더를 매핑 조건으로 지정하는 경우에는 headers 속성을 사용한다.
	 * 
	 */
	
	// headers 매핑 테스트 페이지
	@RequestMapping(value="/ajaxHome", method = RequestMethod.GET)
	public String ajaxHome() {
		log.info("ajaxHome() 실행...!");
		return "ajaxHome";
	}
	
	@RequestMapping(value="/{boardNo}", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyPut(@PathVariable("boardNo")int boardNo,
			@RequestBody Board board){
		log.info("modifyPut() 실행...!");
		log.info("경로 상에 포함된 boardNo : " + boardNo);
		log.info("넘겨받은 데이터 : " + board.getBoardNo());
		log.info("넘겨받은 데이터 : " + board.getTitle());
		log.info("넘겨받은 데이터 : " + board.getContent());
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	// headers 속성에 설정되어 있는 header 정보를 매핑으로 요청
	@RequestMapping(value="/{boardNo}", method = RequestMethod.PUT, headers = "X-HTTP-Method-Override=PUT")
	public ResponseEntity<String> modifyByHeader(@PathVariable("boardNo")int boardNo, 
			@RequestBody Board board){
		log.info("modifyByHeader() 실행 ...!");
		log.info("boardNo : " + boardNo);
		log.info("넘겨받은 데이터 : " + board.getBoardNo());
		log.info("넘겨받은 데이터 : " + board.getTitle());
		log.info("넘겨받은 데이터 : " + board.getContent());
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	/*
	 *  6. Content-Type 매핑
	 *  
	 *  	- 요청의 Content-Type 헤더 값을 매핑 조건으로 지정하는 경우에는 consumes 속성을 사용한다.
	 */
	
	// consumes 속성값을 지정하지 않으면 기본값으로 "application/json" 미디어 타입으로 지정된다.
	@RequestMapping(value="/{boardNo}", method = RequestMethod.POST)
	public ResponseEntity<String> modifyContentType(@PathVariable("boardNo") int boardNo){
		log.info("modifyContentType() 실행 ...!");
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	@RequestMapping(value="/{boardNo}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> modifyContentTypeByJson(@PathVariable("boardNo") int boardNo){
		log.info("modifyContentTypeByJson() 실행 ...!");
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	@RequestMapping(value="/{boardNo}", method = RequestMethod.PUT, consumes = "application/xml")
	public ResponseEntity<String> modifyContentTypeByXml(@PathVariable("boardNo") int boardNo,
			@RequestBody Board board){
		log.info("modifyContentTypeByXml() 실행 ...!");
		log.info("boardNo : " + board.getBoardNo());
		log.info("title : " + board.getTitle());
		log.info("content : " + board.getContent());
		ResponseEntity<String> entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		return entity;
	}
	
	/*
	 *  7. Accept 매핑
	 *  
	 *  	- 요청 Accpet 헤더 값을 매핑 조건으로 지정하는 경우에는 produces 속성을 사용한다.
	 * 
	 */
	
	// produces 속성값을 지정하지 않으면 기본값인 "application/json" 미디어 타입으로 지정된다.
	@RequestMapping(value="/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<Board> readAccept(@PathVariable("boardNo") int boardNo){
		log.info("readAccept() 실행 ...!");
		
		Board board = new Board();
		board.setTitle("제목1");
		board.setContent("내용1");
		board.setWriter("작성자1");
		
		ResponseEntity<Board> entity = new ResponseEntity<Board>(board, HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value="/{boardNo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Board> readToJson(@PathVariable("boardNo") int boardNo){
		log.info("readToJson() 실행...!");
		
		String addStr = "_json";
		Board board = new Board();
		board.setTitle("제목1" + addStr);
		board.setContent("내용1" + addStr);
		board.setWriter("작성자1"+ addStr);
		
		ResponseEntity<Board> entity = new ResponseEntity<Board>(board, HttpStatus.OK);
		return entity;
	}
	
	@RequestMapping(value="/{boardNo}", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<Board> readToXml(@PathVariable("boardNo")int boardNo){
		log.info("readToXml() 실행 ...!");
		
		String addStr = "_xml";
		Board board = new Board();
		board.setTitle("제목1" + addStr);
		board.setContent("내용1" + addStr);
		board.setWriter("작성자1"+ addStr);
		
		ResponseEntity<Board> entity = new ResponseEntity<Board>(board, HttpStatus.OK);
		return entity;
	}
	
	// c:import를 진행할 때 함께 사용한 테스트 페이지
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String boardSearch(String keyword, Model model) {
		model.addAttribute("keyword", keyword);
		return "board/search";
	}
	
}
