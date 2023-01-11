package com.eventi.left.contest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventi.left.common.Paging;
import com.eventi.left.contest.service.ContestService;
import com.eventi.left.contest.service.ContestVO;

import groovy.util.logging.Log4j;

@Controller
@RequestMapping("/contest")
@Log4j
public class ContestController {

	@Autowired
	ContestService service;

	// 공모전 전체리스트(첫페이지)
	@GetMapping("/List")
	public String contestList(Model model, ContestVO vo, Paging paging) {
		model.addAttribute("contestList", service.contestList(vo, paging));
		return "content/contest/contestList";
	}

	// 공모전 전체리스트
	@PostMapping("/List")
	@ResponseBody
	public List<ContestVO> orderList(Model model, ContestVO vo, Paging paging) {
		System.out.println("정렬기준 :" + vo.getOrder());
		System.out.println("분류별 :" + vo.getCategory()); //값이 null..
		List<ContestVO> list = service.contestList(vo, paging);
		return list;
	}

	// 공모전 상세리스트
	@GetMapping("/select")
	public String contestSelect(Model model, ContestVO vo) {
		model.addAttribute("contest", service.getContest(vo));
		vo = service.getContest(vo);
		return "content/contest/contest";
	}

	// 등록화면이동
	@GetMapping("/insert")
	public String contestInsert(Model model) {
		model.addAttribute("cNo", service.getSequence()); // 입력할 공모전번호
		return "content/contest/contestInsertForm";
	}

	// 등록처리(완료)
	@PostMapping("/insert")
	@ResponseBody
	public ContestVO contestInsertForm(@RequestBody ContestVO vo) {
		service.insertContest(vo);
		return vo;
	}

	// 수정화면이동
	@GetMapping("/update")
	public String contestupdate(Model model, ContestVO vo) {
		model.addAttribute("contest", service.getContest(vo));
		return "content/contest/contestUpdateForm";
	}

	// 수정처리(완료)
	@PutMapping("/update")
	@ResponseBody
	public ContestVO contestupdateForm(@RequestBody ContestVO vo) {
		service.updateContest(vo);
		return vo;
	}

	// 삭제처리(링크처리는 get/ deleteMappging form)
	@GetMapping("/delete/{cNo}")
	public String contestDelete(@PathVariable("cNo") String cNo, RedirectAttributes rttr) {
		rttr.addFlashAttribute("result", "삭제처리완료");
		service.deleteContest(cNo);
		return "redirect:/contest/List"; // 페이징처리를 위해 전체리스트 메소드 실행후 페이지이동
	}

}
