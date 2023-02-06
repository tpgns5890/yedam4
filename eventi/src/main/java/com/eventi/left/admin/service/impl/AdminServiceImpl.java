package com.eventi.left.admin.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.eventi.left.admin.mapper.AdminMapper;
import com.eventi.left.admin.service.AdminService;
import com.eventi.left.admin.service.VisitorVO;
import com.eventi.left.common.CodeVO;
import com.eventi.left.common.PagingVO;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.money.service.MoneyVO;
import com.eventi.left.punish.service.PunishVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired AdminMapper adminMapper;
	
	//회원전체조회
	@Override
	public List<MemberVO> memberList(MemberVO memberVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.count(memberVO));
		paging.setPageUnit(10);
		memberVO.setFirst(paging.getFirst());
		memberVO.setLast(paging.getLast());
		
		return adminMapper.memberList(memberVO);
	}

	//회원카운트
	@Override
	public int count(MemberVO memberVO) {
		return adminMapper.count(memberVO);
	}
	
	//자격증조회
	@Override
	public List<CrtfVO> certList(CrtfVO crtfVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.certCount(crtfVO));
		paging.setPageUnit(10);
		crtfVO.setFirst(paging.getFirst());
		crtfVO.setLast(paging.getLast());
		return adminMapper.certList(crtfVO);
	}

	//자격증 업테이트
	@Override
	public int crtfUpdate(CrtfVO crtfVO) {
		return adminMapper.crtfUpdate(crtfVO);
	}

	//공모전 조회
	@Override
	public List<HashMap<String, Object>> contestList(ContestVO contVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.contCount());
		paging.setPageUnit(10);
		contVO.setFirst(paging.getFirst());
		contVO.setLast(paging.getLast());
		return adminMapper.contestList(contVO);
	}
	
	//신고회원 조회
	@Override
	public List<PunishVO> punishList(PunishVO punishVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.punishCount(punishVO));
		paging.setPageUnit(10);
		punishVO.setFirst(paging.getFirst());
		punishVO.setLast(paging.getLast());
		return adminMapper.punishList(punishVO);
	}
	
	//회원 카테고리
	@Override
	public List<CodeVO> getMemberCat(CodeVO codeVO) {
		return adminMapper.getMemberCat(codeVO);
	}
	
	//게시글 카테고리
	@Override
	public List<CodeVO> getBoardCat(CodeVO codeVO) {
		return adminMapper.getBoardCat(codeVO);
	}

	//신고글 조회
	@Override
	public List<HashMap<String, Object>> punishBrdList(PunishVO punishVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.punishCount(punishVO));
		paging.setPageUnit(10);
		punishVO.setFirst(paging.getFirst());
		punishVO.setLast(paging.getLast());
		return adminMapper.punishBrdList(punishVO);
	}

	//회원신고 승인
	@Override
	public int banMemAccept(PunishVO punishVO) {
		adminMapper.banMember(punishVO.getTargetId());
		return adminMapper.banMemAccept(punishVO);
	}
	
	//회원신고 거절
	@Override
	public int banReject(PunishVO punishVO) {
		return adminMapper.banReject(punishVO);
	}

	//게시글신고 승인
	@Override
	public int banBrdAccept(PunishVO punishVO) {
		adminMapper.banMember(punishVO.getWriter());
		if(punishVO.getTargetCat()=="T01") {
			adminMapper.banBoard1(punishVO.getTargetId());
		}else if(punishVO.getTargetCat()=="T03") {
			adminMapper.banBoard2(punishVO.getTargetId());
		}else {
			adminMapper.banBoard3(punishVO.getTargetId());
		}
		return adminMapper.banBrdAccept(punishVO);
	}

	//회계조회
	@Override
	public List<MoneyVO> moneyList(MoneyVO moneyVO, PagingVO paging) {
		paging.setTotalRecord(adminMapper.moneyCount(moneyVO));
		paging.setPageUnit(10);
		moneyVO.setFirst(paging.getFirst());
		moneyVO.setLast(paging.getLast());
		return adminMapper.moneyList(moneyVO);
	}

	
	/* ajax 함수내에서 cors 우회가 불가능하여 서버쪽에서 request 요청을 만들어 CORS 해결 */
	/* RestTemplate 사용 */
	/* 송금 API */
	@Override
	public ResponseEntity<JsonNode> sendMoney(MoneyVO moneyVO){
		String url = "https://developers.nonghyup.com/ReceivedTransferOtherBank.nh";
		String bankName = moneyVO.getBankName();
		String bankAccount = moneyVO.getBankAccount();
		int moPrice = moneyVO.getMoPrice();
		
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String today = now.format(formatter);
		
		LocalTime nowTime = LocalTime.now();
		DateTimeFormatter tformatter = DateTimeFormatter.ofPattern("HHmmss");
		String formatedNow = nowTime.format(tformatter);
		String IsTuno = today+formatedNow;
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("ApiNm","ReceivedTransferOtherBank");
		header.put("Tsymd",today);
		header.put("Trtm",formatedNow);
		header.put("Iscd","001712");
		header.put("FintechApsno","001");
		header.put("ApiSvcCd","DrawingTransferA");
		header.put("IsTuno",IsTuno);
		header.put("AccessToken","2baa445640c38231cf67e1b099c3d4ec97a7e51a33dec5e5757cde32f5a86c4b");
		param.put("Header",header);
		param.put("Bncd", bankName);
		param.put("Acno", bankAccount);
		param.put("Tram", moPrice);
		param.put("DractOtlt", "이벤티");
		
		//header설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		ObjectMapper obMapper = new ObjectMapper();
		
		HttpEntity<String> request = null;
		try {
			request = new HttpEntity<String>(obMapper.writeValueAsString(param), headers);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//uri 생성
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);							
		
		//restTemplate
		RestTemplate restTpl = new RestTemplate();
		
		//result JsonNode 생성
		JsonNode responseEntity = restTpl.postForObject(builder.toUriString(), request, JsonNode.class);
		return ResponseEntity.ok(responseEntity);
	}

	//회계 update
	@Override
	public int updateMoney(MoneyVO moneyVO) {
		return adminMapper.updateMoney(moneyVO);
	}

	@Override
	public List<MoneyVO> winnerList(WinnerVO winnerVO) {
		return adminMapper.winnerList(winnerVO);
	}

	@Override
	public int sendToMoney(WinnerVO winnerVO) {
		int i = 0;
		List<MoneyVO> winners = adminMapper.winnerList(winnerVO);
		for (MoneyVO money : winners) {
			adminMapper.insertMoney(money);
			i++;
		}
		adminMapper.updateContest(winnerVO);
		return i;
	}

	@Override
	public List<VisitorVO> countVisit() {
		return adminMapper.countVisit();
	}

	//일별 정보 조회
	@Override
	public List<HashMap<String, Object>> getDailyInfo() {
		return adminMapper.getDailyInfo();
	}

	//관리자 문의사항 조회
	@Override
	public List<HashMap<String, Object>> getQnaList() {
		return adminMapper.getQnaList();
	}

	@Override
	public int getCrtf() {
		return adminMapper.getCrtf();
	}
	@Override
	public int getCont() {
		return adminMapper.getCont();
	}
	@Override
	public int getBmem() {
		return adminMapper.getBmem();
	}
	@Override
	public int getBbrd() {
		return adminMapper.getBbrd();
	}
	@Override
	public int getMon() {
		return adminMapper.getMon();
	}

	

}
