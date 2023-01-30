package com.eventi.left.rent.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.service.CodeService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.rent.service.RentGdVO;
import com.eventi.left.rent.service.RentService;

@Controller
@RequestMapping("/rent")
public class RentController {
	@Autowired RentService rentService;
	@Autowired CodeService codeService;
	
	//내 물품 리스트 페이지 이동
	@GetMapping("/rentGdList")
	public String rentGdList() {
		return "content/rent/rentGdList";
	}
	
	//물품 리스트
	@PostMapping("rentGdList")
	@ResponseBody
	public Map<String, Object> rentGdList(RentGdVO rentGdVO, PagingVO paging){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rentGds", rentService.getRentGdList(rentGdVO, paging));
		result.put("paging", paging);
		
		return result;
	}
	
	//물품 등록폼 이동
	@GetMapping("/rentGdInsert")
	public String rentGdInsert(Model model, RentGdVO rentGdVO) {
		//option 물품카테고리 가져오기
		model.addAttribute("rents", codeService.getRentCat());
		model.addAttribute("nextNo", rentService.getSeq());
		return "content/rent/rentGdInsert";
	}
	
	//물품 등록
	@PostMapping("/rentGdInsert")
	public String rentGdInsertForm(RentGdVO rentGdVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		rentService.rentGdInsert(rentGdVO, filesVO, uploadFile);
		//내가 등록한 게시글 전체리스트 페이지로 이동
		return "redirect:/rent/rentGdList";
	}
}
