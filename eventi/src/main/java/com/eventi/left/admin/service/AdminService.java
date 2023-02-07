package com.eventi.left.admin.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.eventi.left.common.CodeVO;
import com.eventi.left.common.PagingVO;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.money.service.MoneyVO;
import com.eventi.left.punish.service.PunishVO;
import com.fasterxml.jackson.databind.JsonNode;

public interface AdminService {
	public int count(MemberVO memberVO);  //회원 수 카운트
	
	public List<MemberVO> memberList(MemberVO memberVO, PagingVO paging);  //회원 조회
	public List<CrtfVO> certList(CrtfVO crtfVO, PagingVO paging); //자격증 조회
	public List<HashMap<String,Object>> contestList(ContestVO contVO, PagingVO paging); // 공모전 조회
	public List<PunishVO> punishList(PunishVO punishVO, PagingVO paging); //회원신고 조회
	public List<HashMap<String,Object>> punishBrdList(PunishVO punishVO, PagingVO paging); //신고게시글 조회

	public List<CodeVO> getMemberCat(CodeVO codeVO); //회원 카테고리
	public List<CodeVO> getBoardCat(CodeVO codeVO); //게시물 카테고리

	public int crtfUpdate(CrtfVO crtfVO, MemberVO memberVO); //자격증 승인

	public int banMemAccept(PunishVO punishVO); //회원신고 승인
	public int banBrdAccept(PunishVO punishVO); //게시글신고 승인

	public int banReject(PunishVO punishVO); //신고 거절

	public List<MoneyVO> moneyList(MoneyVO moneyVO, PagingVO paging);

	public ResponseEntity<JsonNode> sendMoney(MoneyVO moneyVO); //송금 api호출

	public int updateMoney(MoneyVO moneyVO);

	public List<MoneyVO> winnerList(WinnerVO winnerVO);

	public int sendToMoney(WinnerVO winnerVO);

	public List<VisitorVO> countVisit();

	public List<HashMap<String,Object>> getDailyInfo();

	public List<HashMap<String, Object>> getQnaList();

	public int getCrtf();
	public int getCont();
	public int getBmem();
	public int getBbrd();
	public int getMon();



}
