package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.or.ddit.vo.notice.NoticeMemberVO;

public class CustomUser extends User{
	private NoticeMemberVO member;
	
	// 첫번째 생성자는 User객체로 부모에게 전달
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities){
			super(username, password, authorities);
	}
	
	public CustomUser(NoticeMemberVO member) {
		// Java 스트림을 사용한 경우(람다 표현식)
		// - 자바 버번 8부터 추가된 기능
		// map : 컬렉션(List, Map, Set 등), 배열 등의 설정되어 있는 각 타입의 값들을 하나씩 참조하여 람다식으로 반복 처리할 수 있게 해준다.
		// collect : Stream()을 돌려 발생되는 데이터를 가공 처리하고 원하는 형태의 자료형으로 변환을 돕는다.
		
		super(member.getMemId(), member.getMemPw(),
				member.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())).
				collect(Collectors.toList()));
		
			// 위 람다표현식을 아래 기존 java식으로 표현하게되면 아래와 같다.
//			for(int i = 0; i< member.getAuthList().size(); i++) {
//				String authority = member.getAuthList().get(i).getAuth(); 	// 권한 문자열
//				SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
//				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//				authorities.add(simpleGrantedAuthority);
//			}
		
		
		
		this.member = member;
	}

	public NoticeMemberVO getMember() {
		return member;
	}

	public void setMember(NoticeMemberVO member) {
		this.member = member;
	}
	
}

