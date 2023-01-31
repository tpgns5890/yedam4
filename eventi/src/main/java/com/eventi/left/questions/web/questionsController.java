package com.eventi.left.questions.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.questions.service.QuestionsService;
import com.eventi.left.questions.service.QuestionsVO;

import groovyjarjarantlr4.v4.runtime.RuleVersion;

@Controller
@RequestMapping("/questions")
public class questionsController {

	@Autowired
	QuestionsService qService;

	// 공모전글에 대한 문의사항리스트
	@PostMapping("/qList")
	@ResponseBody
	public Map<String, Object> qestionsList(QuestionsVO vo, PagingVO paging) {
		// 리턴할 최종Map(contest,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("questions", qService.questionsList(vo, paging));
		result.put("paging", paging);
		return result;
	}

	// 게시글등록폼이동
	@RequestMapping(value = "/queInsert", method = RequestMethod.GET)
	public String proInsert(Model model) {
		model.addAttribute("nextNo", qService.getSeq());
		return "content/questions/queInsert";
	}

	// 등록처리
	@PostMapping("/insertQna")
	public String qestionsInsert(QuestionsVO vo) {
		qService.questionsInsert(vo); // 등록처리
		return "redirect:/questions/myqnaList";
	}

	// 공모전 문의 등록처리
	@PostMapping("/insertAjax")
	@ResponseBody
	public QuestionsVO qestionsContestInsert(QuestionsVO vo) {
		qService.questionsInsert(vo); // 등록처리
		return qService.getQuestions(vo);
	}
	
	// 나의 문의내역 전체조회
	@GetMapping("/myqnaList")
	public String qnaList(Model model, QuestionsVO vo, PagingVO paging) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setUserId(user.getUserId());
		System.out.println(vo);
		model.addAttribute("qnaList", qService.qnaList(vo, paging));
		model.addAttribute("paging", paging);
		return "content/questions/queList";
	}

	// 문의 1건 상세조회
	@RequestMapping(value = "/queDetail", method = RequestMethod.GET)
	public String queDetail(Model model, String qNo) {
		// 상세내용
		model.addAttribute("queDetail", qService.getQuestionsList(qNo));
		return "content/questions/queDetail";
	}

	// 댓글 수정
	@PostMapping("/update")
	@ResponseBody
	public QuestionsVO replyUpdate(QuestionsVO vo) {
		qService.questionsUpdate(vo);
		return qService.getQuestions(vo);
	}
	
	@PostMapping("queDetailAjax")
	@ResponseBody
	public List<QuestionsVO> getQuestionsList(String qNo){
		return qService.getQuestionsList(qNo);
	}

	// 댓글 삭제
	@PostMapping("/delete")
	@ResponseBody
	public int qestionsDelete(QuestionsVO vo) {
		return qService.questionsDelete(vo);
	}

}
