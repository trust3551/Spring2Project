package kr.or.ddit.service.impl;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.IMemberMapper;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.vo.CrudMember;
import kr.or.ddit.vo.CrudMemberAuth;

@Service
public class MemberServiceImpl implements IMemberService {

	@Inject
	private IMemberMapper mapper;
	
	@Transactional(rollbackFor = IOException.class)
	@Override
	public void register(CrudMember member) throws IOException {
		// 한명의 회원을 등록 시 , 하나의 권한을 등록한다.
		mapper.create(member);
		
		// 트랜잭션 테스트를 위한 에러를 발생
		// RuntimeException 에러 계열이 아니기 때문에 롤백처리가 불가능함
		if(true) {
			throw new IOException();
		}
		
//		if(true) {
//			throw new NullPointerException();
//		}
		
		CrudMemberAuth memberAuth = new CrudMemberAuth();
		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_USER");	// 사용자
		
		mapper.createAuth(memberAuth);
	}

	@Override
	public List<CrudMember> list() {
		return mapper.list();
	}

	@Override
	public CrudMember read(int userNo) {
		return mapper.read(userNo);
	}

	@Override
	public void modify(CrudMember member) {
		// 일반적인 데이터를 수정
		mapper.modify(member);
		
		// 권한에 대한 데이터를 수정
		// 기존에 등록되어 있던 권한 일괄 삭제
		int userNo = member.getUserNo();
		mapper.deleteAuth(userNo);
		
		// 새롭게 수정되서 들어온 권한들을 다시 등록
		List<CrudMemberAuth> authList = member.getAuthList();
		for(int i = 0; i<authList.size(); i++) {
			CrudMemberAuth memberAuth = authList.get(i);
			String auth = memberAuth.getAuth();
			if(auth == null) {
				continue;
			}
			if(auth.trim().length()==0) {
				continue;
			}
			
			memberAuth.setUserNo(userNo);
			mapper.createAuth(memberAuth);
		}
		
	}

	@Override
	public void remove(int userNo) {
		// 1:N 의 관계성을 가지고 있는 테이블 구조에서는 N과 같은 자식 테이블이 먼저 지워지고 나서
		// 부모의 대한 데이터를 지울 수 있다.
		mapper.deleteAuth(userNo);	// 권한 삭제
		mapper.delete(userNo);		// 회원 삭제
	}

}
