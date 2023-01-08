package com.eventi.left.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	// 공모전 전체리스트
	@GetMapping("/List")
	public String contestList(Model model, ContestVO vo, Paging paging) {
		model.addAttribute("contestList", service.contestList(vo, paging));
		return "content/contest/contestList";
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
	public String contestInsert() {
		return "content/contest/contestInsertForm";
	}

	// 등록처리
	@PostMapping("/insert")
	public String contestInsertForm(ContestVO vo, RedirectAttributes rttr) {
		rttr.addFlashAttribute("result", "등록처리완료");
		service.insertContest(vo);
		return "content/contest/contestList";
	}

	// 수정화면이동
	@GetMapping("/update")
	public String contestupdate(Model model, ContestVO vo) {
		model.addAttribute("contest", service.getContest(vo));
		return "content/contest/contestUpdateForm";
	}

	// 수정처리
	@PostMapping("/update")
	public String contestupdateForm(ContestVO vo, RedirectAttributes rttr) {
		rttr.addFlashAttribute("result", "수정처리완료");
		service.updateContest(vo);
		return "content/contest/contestList";
	}

	// 삭제처리
	@DeleteMapping("/delete")
	public String contestDelete(String cNo, RedirectAttributes rttr) {
		rttr.addFlashAttribute("result", "삭제처리완료");
		service.deleteContest(cNo);
		return "content/contest/contestList";
	}

}
