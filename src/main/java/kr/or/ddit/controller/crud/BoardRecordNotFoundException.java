package kr.or.ddit.controller.crud;

// 사용자가 정의한 에러 출력 컨트롤러
public class BoardRecordNotFoundException extends Exception{
	
	// 부모인 Exception 으로 사용자가 정의한 메세지를 전달한다.
	public BoardRecordNotFoundException(String msg) {
		super(msg);
	}
}
