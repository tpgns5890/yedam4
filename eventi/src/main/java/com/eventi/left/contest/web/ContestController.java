package com.eventi.left.contest.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
import com.eventi.left.money.service.MoneyService;
import com.eventi.left.money.service.MoneyVO;
import com.eventi.left.questions.service.QuestionsService;
import com.eventi.left.questions.service.QuestionsVO;
import com.eventi.left.review.service.ReviewService;

import groovy.util.logging.Log4j;

/**
 * 
 * @author 배수빈 공모전에 대한 컨트롤러
 *
 */
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
	@Autowired
	MemberService memberService;
	@Autowired
	MoneyService moneyService;
	@Autowired
	ReviewService reviewService;

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

		// 로그인된 경우
		if (user != null) {
			// 상세리스트 1건의 회원의 좋아요 체크확인
			LikesVO likeCheck = new LikesVO();
			likeCheck.setTargetNo(cVo.getcNo());
			likeCheck.setUserId(user.getUserId());
			model.addAttribute("sessionId", user.getUserId());
			model.addAttribute("likeCheck", likeService.getLike(likeCheck));
		}
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
		model.addAttribute("styles", codeService.getContestStyle()); // 공모전 스타일
		return "content/contest/contestInsertForm";
	}

	// 등록처리
	@PostMapping("/insert")
	public String contestInsertForm(Model model, ContestVO vo, WinnerVO wvo, MultipartFile[] uploadFile) {

		// 임시저장일 경우 공모전 작성 리스트 이동
		if (vo.getSave().equals("Y")) {
			vo.setcNo(service.getSequence());
			service.insertContest(vo, wvo, uploadFile);
			return "redirect:/contest/mySelect"; // 나의 공모전리스트.
		}

		// 결제 후 등록인경우 공모전상세페이지 이동.
		service.insertContest(vo, wvo, uploadFile);
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
	public ContestVO saveSelect(ContestVO vo) {
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
	public String mySelect(Model model, ContestVO vo, @ModelAttribute("paging") PagingVO paging) {

		List<ContestVO> list = service.myContestList(vo, paging);
		// 등록한 공모전 없는경우
		if (list.size() == 0) {
			model.addAttribute("result", "등록한 공모전이 없습니다.");
		} // 등록한 공모전 있는경우
		else {
			model.addAttribute("contestList", list); // 공모리스트
			MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		}
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
	public Map<String, Object> ajaxDesignRead(ContestVO vo, PagingVO paging) {

		// 1건의 공모전에 접수된 디자인리스트
		DesignVO dVo = new DesignVO();
		dVo.setcNo(vo.getcNo());
		dVo.setCaregory(vo.getCategory());
		dVo.setCount(vo.getCount()); //입력별 페이지개수..
		List<DesignVO> designs = dService.contestDesignList(dVo, paging);

		// 리턴할 최종Map
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("design", designs);
		result.put("paging", paging);

		return result; // 디자인리스트+파일리스트 반환.
	}

	// 공모전 나의 문의리스트 페이지이동(마이페이지)
	@GetMapping("/qnaList")
	public String ContestQnaList(Model model, QuestionsVO vo, PagingVO paging) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setUserId(user.getUserId());
		vo.setCategory("T01");

		List<QuestionsVO> list = qService.myQuestionsList(vo, paging);
		if (list.size() == 0) {
			model.addAttribute("result", "문의내역이 없습니다");
		} else {
			model.addAttribute("qnaList", list);
		}
		model.addAttribute("paging", paging);
		return "content/myPage/myQnaList";
	}

	// 공모전등록시 결제 완료후 -> insert
	@PostMapping("requestPay")
	@ResponseBody
	public MoneyVO ContestRequestPay(@RequestBody MoneyVO vo) {

		// 임시저장한 공모글을 불러오지 않았다면
		if (vo.getTargetId() == null || vo.getTargetId() == "") {
			// 공모전 결제후 입금(맥시멈 시퀀스값 설정)
			vo.setTargetId(service.getSequence()); 
		}
		moneyService.insertMoney(vo);
		return vo;
	}

	// 공모전 1건의 문의내역 리스트 출력
	@PostMapping("questionList")
	@ResponseBody
	public List<QuestionsVO> ContestQuestionList(ContestVO vo, PagingVO paging) {
		QuestionsVO question = new QuestionsVO();
		question.setTargetId(vo.getcNo());
		question.setCategory("T01");
		return qService.questionsList(question, paging);
	}

}
