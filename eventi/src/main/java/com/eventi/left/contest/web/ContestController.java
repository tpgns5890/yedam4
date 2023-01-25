package com.eventi.left.contest.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.bboard.service.BboardVO;
import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.common.service.CodeService;
import com.eventi.left.contest.service.ContestService;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerService;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.design.service.DesignService;
import com.eventi.left.design.service.DesignVO;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.member.service.MemberService;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.questions.service.QuestionsService;
import com.eventi.left.questions.service.QuestionsVO;

import groovy.util.logging.Log4j;

@Controller
@RequestMapping("/contest")
@Log4j
public class ContestController {

	@Autowired
	ContestService service;
	@Autowired
	WinnerService wService;
	@Autowired
	FilesService fService;
	@Autowired
	DesignService dService;
	@Autowired
	CodeService codeService;
	@Autowired
	LikesService likeService;
	@Autowired
	QuestionsService qService;

	// 공모전 전체리스트(첫페이지)
	@GetMapping("/List")
	public String contestList() {
		return "content/contest/contestList";
	}

	// 공모전 전체리스트(ajax)
	@PostMapping("/List")
	@ResponseBody
	public Map<String, Object> changeList(ContestVO vo, PagingVO paging) {
		// 리턴할 최종Map(contest,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("contest", service.contestList(vo, paging));
		result.put("paging", paging);
		return result;
	}

	// 1.공모전 상세리스트(get)
	@GetMapping("/select")
	public String contestSelect(Model model, ContestVO cVo, PagingVO paging) {

		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		Map<String, Object> result = new HashMap<String, Object>();

		// 로그인된 경우
		if (user != null) {
			String sessionId = user.getUserId();
			// 상세리스트 1건의 회원의 좋아요 체크확인
			LikesVO likeCheck = new LikesVO();
			likeCheck.setTargetNo(cVo.getcNo());
			likeCheck.setUserId(sessionId);
			model.addAttribute("likeCheck", likeService.getLike(likeCheck));
		}

		model.addAttribute("fileList", fService.fileList(cVo.getcNo()));
		model.addAttribute("winnerList", wService.winnerList(cVo.getcNo()));
		model.addAttribute("contest", service.getContest(cVo.getcNo()));

		return "content/contest/contest";
	}

	// 2.공모전상세리스트(요청사항ajax)
	@PostMapping("/cntnSelect")
	@ResponseBody
	public ContestVO contestSelect(ContestVO vo) {
		ContestVO contest = service.getContest(vo.getcNo());
		return contest;
	}

	// 등록화면이동
	@GetMapping("/insert")
	public String contestInsert(Model model) {
		model.addAttribute("cNo", service.getSequence()); // 입력할 공모전번호
		return "content/contest/contestInsertForm";
	}

	// 등록처리
	@PostMapping("/insert")
	public String contestInsertForm(Model model, ContestVO vo, WinnerVO wvo, PagingVO paging, MultipartFile[] uploadFile) {

		service.insertContest(vo, wvo, uploadFile);

		// 임시저장일 경우 공모전 작성 리스트 이동
		if (vo.getSave().equals("Y")) {
			return "redirect:/contest/mySelect"; // 나의 공모전리스트.
		}
		// 등록인경우 결제 후 공모전상세페이지 이동.
		return "redirect:/contest/select?cNo=" + vo.getcNo();
	}

	// 수정화면이동
	@GetMapping("/update")
	public String contestupdate(Model model, ContestVO vo) {
		model.addAttribute("contest", service.getContest(vo.getcNo()));
		model.addAttribute("fileList", fService.fileList(vo.getcNo()));
		model.addAttribute("winnerList", wService.winnerList(vo.getcNo()));
		return "content/contest/contestUpdateForm";
	}

	// 수정처리
	@PostMapping("/update")
	public String contestupdateForm(ContestVO vo, MultipartFile[] uploadFile) {
		System.out.println(vo);
		service.updateContest(vo, uploadFile);
		return "redirect:/contest/select?cNo=" + vo.getcNo();
	}

	// 임시저장 불러오기 수정처리
	@PostMapping("/saveUpdate")
	public String saveUpdateContest(ContestVO vo, MultipartFile[] uploadFile, WinnerVO wvo) {
		service.saveUpdateContest(vo, uploadFile, wvo);
		return "redirect:/contest/select?cNo=" + vo.getcNo();
	}

	// 마감연장 수정처리
	@PutMapping("/updateExtension")
	@ResponseBody
	public int contestExtension(@RequestBody ContestVO vo, MultipartFile[] uploadFile) {
		return service.updateContest(vo, uploadFile);
	}

	// 삭제처리(링크처리는 get/ deleteMappging form)
	@PostMapping("/delete")
	@ResponseBody
	public int contestDelete(ContestVO vo) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setWriter(user.getUserId());
		return service.deleteContest(vo);
	}

	// 임시저장된 게시글 전체들고오기
	@PostMapping("/cSave")
	@ResponseBody
	public List<ContestVO> cSave(ContestVO vo) {
		return service.cSave(vo);
	}

	// 임시저장된 게시글 상세들고오기
	@PostMapping("/saveSelect")
	@ResponseBody
	public List<ContestVO> saveSelect(ContestVO vo) {
		return service.saveGetContest(vo);
	}

	// 마이페이지----------------------------------------------------------------------------

	// 1.공모전 좋아요리스트 페이지이동
	@GetMapping("/like")
	public String likeList() {
		return "content/myPage/myContestLike";
	}

	// 2.로그인회원의 전체 좋아요리스트
	@PostMapping("/ajaxlike")
	@ResponseBody
	public Map<String, Object> userlikeList(LikesVO vo, PagingVO paging) {

		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setUserId(user.getUserId());

		// 리턴할 최종Map
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("contest", likeService.userlikeList(vo, paging));
		result.put("paging", paging);

		return result;
	}

	// 나의 공모전게시글관리
	@GetMapping("/mySelect")
	public String mySelect(Model model, ContestVO vo, PagingVO paging) {
		model.addAttribute("contestList", service.myContestList(vo, paging));
		model.addAttribute("paging", paging);
		return "content/myPage/myCotestList";
	}

	// 나의공모전관리 지원자조회페이지 이동
	@GetMapping("/designRead")
	public String designRead(Model model, String cNo) {
		model.addAttribute("contest", service.getContest(cNo));
		model.addAttribute("winner", wService.winnerList(cNo));
		return "content/contest/cotestDesignList";
	}

	// 1.나의공모전 -> 응모디자인조회
	// 2.공모전리 -> 상세정보 참여작조회
	@PostMapping("/ajaxDesignRead")
	@ResponseBody
	public Map<String, Object> ajaxDesignRead(String cNo, PagingVO paging) {

		// 1건의 공모전에 접수된 디자인리스트
		DesignVO dVo = new DesignVO();
		dVo.setcNo(cNo);
		List<DesignVO> designs = dService.contestDesignList(dVo, paging);

		// 리턴할 최종Map
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("design", designs);
		result.put("paging", paging);

		return result; // 디자인리스트+파일리스트 반환.
	}

	// 공모전 나의 문의리스트 페이지이동(추가해야함)
	@GetMapping("/qnaList")
	public String ContestQnaList(Model model) {// , QuestionsVO vo, PagingVO paging
		// model.addAttribute("qnaList", qService.qnaList(vo, paging));
		return "content/myPage/mytQnaList";
	}

}
