package com.eventi.left.contest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.contest.service.WinnerService;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.design.service.DesignService;

@Controller
@RequestMapping("/winner")
public class WinnerController {

	@Autowired
	WinnerService service;
	@Autowired
	DesignService dService;

	// 우승자 전체리스트
	@PostMapping("/List")
	@ResponseBody
	public List<WinnerVO> winnerList(WinnerVO vo) {
		return service.winnerList(vo.getCoNo());
	}

	// 우승자 등록확인
	@PostMapping("/check")
	@ResponseBody
	public boolean  winnerCheck(WinnerVO vo) {
		 List<WinnerVO>list = service.winnerList(vo.getCoNo());
		 if(list.get(0).getdNo() != null) {
			 return true;
		 }
		return false;
	}

	// 등록처리
	@PostMapping("/update")
	public String winnerUpdate(WinnerVO vo) {
		int r = service.updateWinner(vo);
		return "redirect:/contest/mySelect";
	}
	
	//마감된 공고문 우승자 조회
	@PostMapping("/select")
	@ResponseBody
	public List<WinnerVO> winnerSelect(String coNo) {
		return service.winnerSelect(coNo);
	}


}
