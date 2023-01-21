package com.eventi.left.estimate.web;

import java.util.List;

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
import com.eventi.left.estimate.service.PropGdVO;
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
	public String estDetail(Model model, @RequestParam String eno, @RequestParam String userId) {
		model.addAttribute("est", estService.getEst(eno));
		model.addAttribute("propList", estService.getPropList(eno));
		model.addAttribute("count", estService.getCount(eno, userId));
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
	@PostMapping("/myGoodsList")
	@ResponseBody
	public List<RentGdVO> myGdList(@RequestBody RentGdVO rentGdvo) {
		return estService.myGdList(rentGdvo);
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
	
	//제안서 상세조회
	@PostMapping("/getProp")
	@ResponseBody
	public PropVO getProp(@RequestBody PropVO propVO) {
		return estService.getProp(propVO);
		}
	
	//제안서 삭제
	@PostMapping("/delProp")
	@ResponseBody
	public int delProp(@RequestBody PropVO propVO) {
		return estService.delProp(propVO);	
	}
	
	//제안서에 등록된 물품 조회
	@PostMapping("/propGdList")
	@ResponseBody
	public List<PropGdVO> getPropGdList(@RequestBody PropGdVO propGdVO) {
		return estService.getPropGdList(propGdVO);
		}
	
	//제안서 채택
	@PostMapping("/chooesProp")
	@ResponseBody
	public int chooesProp(@RequestBody PropVO propVO) {
		return estService.chooesProp(propVO);	
	}
}
