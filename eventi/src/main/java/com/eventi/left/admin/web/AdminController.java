package com.eventi.left.admin.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.admin.service.AdminService;
import com.eventi.left.common.CodeVO;
import com.eventi.left.common.PagingVO;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.money.service.MoneyVO;
import com.eventi.left.punish.service.PunishVO;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService service;

	// 관리자페이지 이동
	@RequestMapping(value = "/adminMain")
	public String adminMain() {
		return "content/admin/adminMain";
	}
	
	// 자격증승인페이지 이동
	@RequestMapping(value = "/certList")
	public String certList() {
		return "content/admin/adminCert";
	}
	// 공모전페이지 이동
	@RequestMapping(value = "/adminContest")
	public String adminContest() {
		return "content/admin/adminContest";
	}
	// 신고회원페이지 이동
	@RequestMapping(value = "/adminBanMem")
	public String adminBanMem(Model model, CodeVO codeVO) {
		model.addAttribute("targetCat", service.getMemberCat(codeVO));
		return "content/admin/adminBanMem";
	}
	// 신고게시글페이지 이동
	@RequestMapping(value = "/adminBanBrd")
	public String adminBanBrd(Model model, CodeVO codeVO) {
		model.addAttribute("targetCat", service.getBoardCat(codeVO));
		return "content/admin/adminBanBrd";
	}
	// 회계페이지 이동
	@RequestMapping(value = "/adminMoney")
	public String adminMoney() {
		return "content/admin/adminMoney";
	}
	
	// 전체회원리스트 이동 및 회원 수 가져오기
	@GetMapping("/memberList")
	public String MemberList(Model model, MemberVO memberVO) {
		// 상단에 표시할 회원 수 모델에 저장
		model.addAttribute("countall", service.count(memberVO));
		memberVO.setUserState("Punish");
		model.addAttribute("countpunish", service.count(memberVO));
		memberVO.setUserState("Ban");
		model.addAttribute("countban", service.count(memberVO));
		memberVO.setUserState(null);
		return "content/admin/adminMember";
	}

	// 전체회원 리스트(ajax)
	@PostMapping("/memberListAjax")
	@ResponseBody
	public Map<String, Object> memberListAjax(MemberVO vo, PagingVO paging) {
		// 리턴할 최종Map(Member,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("mListAjax", service.memberList(vo, paging));
		result.put("paging", paging);
		return result;
	}
	
	//자격증 리스트
	@PostMapping("/certListAjax")
	@ResponseBody
	public Map<String, Object> certListAjax(CrtfVO vo, PagingVO paging) {
		// 리턴할 최종Map(Member,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("certList", service.certList(vo, paging));
		result.put("paging", paging);
		return result;
	}
	
	//자격증 업데이트
	@PostMapping("/crtfUpdate")
	@ResponseBody
	public int crtfAccept(CrtfVO vo) {
		int r = service.crtfUpdate(vo);
		return r;
	}
	
	//공모전 조회 Ajax
	@PostMapping("/contestAjax")
	@ResponseBody
	public Map<String, Object> contestAjax(ContestVO vo, PagingVO paging) {
		// 리턴할 최종Map(Member,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("contestList", service.contestList(vo, paging));
		result.put("paging", paging);
		System.out.println(result);
		return result;
	}
	
	//신고 회원 조회 Ajax
	@PostMapping("/punMemAjax")
	@ResponseBody
	public Map<String, Object> punMemAjax(PunishVO punishVO, PagingVO paging) {
		// 리턴할 최종Map(Member,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("punishList", service.punishList(punishVO, paging));
		result.put("paging", paging);
		return result;
	}
	
	//신고 게시물 조회 Ajax
	@PostMapping("/punBrdAjax")
	@ResponseBody
	public Map<String, Object> punishBrdList(PunishVO punishVO, PagingVO paging) {
		// 리턴할 최종Map(Member,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("punishBrdList", service.punishBrdList(punishVO, paging));
		result.put("paging", paging);
		return result;
	}
	
	//회원 신고 승인
	@PostMapping("/banMemAccept")
	@ResponseBody
	public int banMemAccept(PunishVO punishVO) {
		int r = service.banMemAccept(punishVO);
		return r;
	}
	
	//신고 거부
	@PostMapping("/banReject")
	@ResponseBody
	public int banReject(PunishVO punishVO) {
		int r = service.banReject(punishVO);
		return r;
	}
	
	//게시글 신고 승인
	@PostMapping("/banBrdAccept")
	@ResponseBody
	public int banBrdAccept(PunishVO punishVO) {
		int r = service.banBrdAccept(punishVO);
		return r;
	}

	//회계조회 Ajax
	@PostMapping("/moneyList")
	@ResponseBody
	public Map<String, Object> moneyList(MoneyVO moneyVO, PagingVO paging) {
		// 리턴할 최종Map(Member,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("moneyList", service.moneyList(moneyVO, paging));
		result.put("paging", paging);
		return result;
	}
	
	@PostMapping("/sendMoney")
	@ResponseBody
	public ResponseEntity<JsonNode> sendMoney(MoneyVO moneyVO){
		ResponseEntity<JsonNode> responseEntity = service.sendMoney(moneyVO);
		System.out.println(responseEntity);
		return responseEntity;
	}
	
	//정산 승인
		@PostMapping("/updateMoney")
		@ResponseBody
		public int updateMoney(MoneyVO moneyVO) {
			int r = service.updateMoney(moneyVO);
			return r;
		}
}
