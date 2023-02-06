package com.eventi.left.member.mapper;

import java.util.List;

import com.eventi.left.admin.service.VisitorVO;
import com.eventi.left.common.CodeVO;
import com.eventi.left.member.service.BusiVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;

public interface MemberMapper {

	public int IdCheck(String inputId); // 아이디 중복 확인

	public int insertMember(MemberVO memberVO); // 일반회원 회원가입

	public int insertCrtf(CrtfVO crVO); //승급신청 자격증추가

	public MemberVO getMember(String userId);

	public String findId(String name, String email);

	public int findPwCheck(String id, String name, String email);

	public void updatePw(String userId, String userPassword);

	public List<CodeVO> getCountry();

	public List<CodeVO> getType();

	public int insertBusi(BusiVO busiVO);

	public List<MemberVO> memberEmail(); // 수신동의 멤버의 이메일리스트

	public int userStateUpdate(MemberVO memberVO); // 회원권한 변경.

	public int userUpdate(MemberVO memberVO); // 기본 회원정보 변경.
	
	public int bankUpdate(MemberVO memberVO); // 계좌번호 변경.

	public int busiUpdate(BusiVO busiVO); // 업체 추가 회원정보 변경.

	public MemberVO busiSelect(String userId); // 업체회원 조회
	
	public List<CrtfVO>crtfSelect(String userId); // 회원의 자격증리스트 조회

	public List<CodeVO> getBankCode(); //은행사 코드 가져오기
	
	public int visitCount();

}
