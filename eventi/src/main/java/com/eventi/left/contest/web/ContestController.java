package com.eventi.left.contest.web;

import java.util.ArrayList;
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

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.common.service.CodeService;
import com.eventi.left.contest.service.ContestService;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerService;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.design.service.DesignService;
import com.eventi.left.design.service.DesignVO;
import com.eventi.left.files.FileDto;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.member.service.MemberVO;

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

	// 공모전 전체리스트(첫페이지)
	@GetMapping("/List")
	public String contestList(Model model, ContestVO vo, PagingVO paging) {
		model.addAttribute("contestList", service.contestList(vo, paging));
		return "content/contest/contestList";
	}

	// 공모전 전체리스트(ajax)
	@PostMapping("/List")
	@ResponseBody
	public Map<String, Object> changeList(ContestVO vo, PagingVO paging) {

		// 전체리스트 조회
		List<ContestVO> contest = service.contestList(vo, paging);

		// 리턴할 최종Map(contest,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("contest", contest);
		result.put("paging", paging);
		return result;
	}

	// 1.공모전 상세리스트(get)
	@GetMapping("/select")
	public String contestSelect(Model model, ContestVO cVo) {

		// 객체생성 및 코드별 문자열 변환후 세팅
		ContestVO contest = service.getContest(cVo);
		contest.setCategory(codeService.codeSelect(contest.getCategory())); // 카테고리
		contest.setStyle(codeService.codeSelect(contest.getStyle())); // 스타일

		LikesVO like = new LikesVO();
		like.setTargetNo(cVo.getcNo());
		contest.setLikes(likeService.countLike(like)); // 좋아요개수
		model.addAttribute("contest", contest); // 모델 넘겨주기.
		model.addAttribute("designList", dService.contestDesignList(cVo.getcNo()));
		model.addAttribute("fileList", fService.fileList(cVo.getcNo()));
		model.addAttribute("winnerList", wService.winnerList(cVo.getcNo()));

		return "content/contest/contest";
	}

	// 2.공모전 상세리스트(ajax)
	@PostMapping("/select")
	@ResponseBody
	public ContestVO contestSelect(ContestVO vo) {
		vo = service.getContest(vo);
		return vo;
	}

	// 등록화면이동
	@GetMapping("/insert")
	public String contestInsert(Model model) {
		model.addAttribute("cNo", service.getSequence()); // 입력할 공모전번호
		return "content/contest/contestInsertForm";
	}

	// 등록처리
	@PostMapping("/insert")
	public String contestInsertForm(Model model, ContestVO vo, WinnerVO wvo, PagingVO paging) {

		service.insertContest(vo, wvo);

		// 임시저장일 경우 공모전 작성 리스트 이동
		if (vo.getSave().equals("Y")) {

			// 전체데이터 받기
			List<ContestVO> contests = service.myContestList(vo, paging);
			model.addAttribute("contestList", contests);// 모델에 담기.
			return "redirect:/contest/mySelect"; // 나의 공모전리스트.
		}

		// 등록인경우 결제 후 공모전상세페이지 이동.
		return "redirect:/contest/select?cNo=" + vo.getcNo();
	}

	// 수정화면이동
	@GetMapping("/update")
	public String contestupdate(Model model, ContestVO vo) {
		model.addAttribute("contest", service.getContest(vo));
		model.addAttribute("fileList", fService.fileList(vo.getcNo()));
		model.addAttribute("winnerList", wService.winnerList(vo.getcNo()));
		return "content/contest/contestUpdateForm";
	}

	// 수정처리(완료)
	@PutMapping("/update")
	@ResponseBody
	public int contestupdateForm(@RequestBody ContestVO vo) {

		return service.updateContest(vo);
	}

	// 삭제처리(링크처리는 get/ deleteMappging form)
	@PostMapping("/delete")
	@ResponseBody
	public int contestDelete(ContestVO vo) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		vo.setWriter(sessionId);

		return service.deleteContest(vo);
	}

	// 공모전 디자인 응모내역 리스트
	@PostMapping("/design")
	@ResponseBody
	public List<DesignVO> contestSelect(String cNo) {
		return dService.contestDesignList(cNo);
	}

	// 나의 공모전리스트
	@GetMapping("/mySelect")
	public String mySelect(Model model, ContestVO vo, PagingVO paging) {
		model.addAttribute("contestList", service.myContestList(vo, paging));
		model.addAttribute("paging", paging);
		return "content/myPage/myCotestList";
	}

	// 공모전 좋아요리스트
	@GetMapping("/like")
	public String likeList(Model model) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();

		model.addAttribute("likeList", likeService.userlikeList(null, sessionId));
		return "content/myPage/myLikeLIst";
	}

	// 로그인회원의 전체 좋아요리스트
	@PostMapping("/ajaxlike")
	@ResponseBody
	public Map<String, Object> userlikeList(LikesVO LikesVO, PagingVO paging) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		Map<String, Object> result = new HashMap<String, Object>();

		if (user == null) {
			return result;
		}
		String sessionId = user.getUserId();

		// 리턴할 최종Map(contest,paging VO)

		result.put("contest", likeService.userlikeList(LikesVO, sessionId));
		result.put("paging", paging);

		return result;
	}

	// 일반회원 디자인 응모하기 폼 이동. DesignServic
	@GetMapping("/designInsertForm")
	public String designInsert(Model model, DesignVO vo) {
		model.addAttribute("cNo" , vo.getCNo());  //html 링크에서 받아오는 값.
		model.addAttribute("dNo", dService.getSequence());
		return "content/contest/contestApplyForm";
	}

}
