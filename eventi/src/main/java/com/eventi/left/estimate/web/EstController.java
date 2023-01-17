package com.eventi.left.estimate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.common.SessionUtil;
import com.eventi.left.estimate.service.EstService;
import com.eventi.left.estimate.service.EstVO;
import com.eventi.left.estimate.service.PropVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.rent.service.RentGdVO;
import com.eventi.left.rent.service.RentService;

@Controller
public class EstController {
	
	@Autowired
	EstService estService;
	
	//견적요청서 전체조회
	@RequestMapping(value = "/estList")
	public String estList(Model model) {
		model.addAttribute("estList", estService.getEstList(null));
		return "content/estimate/estList";
	}
	//견적요청서 작성페이지
	@RequestMapping(value = "/estForm")
	public String estFormType(Model model) {
		return "content/estimate/estForm";
	}
	//견적서 상세페이지
	@RequestMapping(value = "/estDetail", method = {RequestMethod.GET, RequestMethod.POST})
	public String estDetail(Model model, @RequestParam String eno, @RequestParam String userId, @RequestParam String myId) {
		model.addAttribute("est", estService.getEst(eno));
		model.addAttribute("propList", estService.getPropList(eno));
		model.addAttribute("count", estService.getCount(eno, userId));
		model.addAttribute("myGdList", estService.myGdList(myId));
		return "content/estimate/estDetail";
	}
	
	//견적서 등록
	@PostMapping("/insertEst")
	@ResponseBody
	public String insertEst(@RequestBody EstVO estVO) {
		estService.insertEst(estVO);
		return estVO.getEno();
		}
	
	//견적요청서 작성완료페이지
	@RequestMapping(value = "/estFormDone")
	public String estFormDone(Model model, String eno) {
		model.addAttribute("est", estService.getEst(eno));
		return "content/estimate/estFormDone";
		}
		
	//해당업체의 물품조회
	@RequestMapping(value = "/myGoodsList")
	public String myGdList(Model model, @RequestParam String userId) {
		return "content/estimate/estDetail";
		}
	
	//견적서 삭제
	@PostMapping("/deleteEst")
	@ResponseBody
	public int deleteEst(@RequestBody EstVO estVO) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		estVO.setUserId(sessionId);
		
		return estService.deleteEst(estVO);	
	}
	
	//제안서 등록
	@PostMapping("/insertProp")
	@ResponseBody
	public int insertProp(@RequestBody PropVO propVO) {
		return estService.insertProp(propVO);
		}
}
