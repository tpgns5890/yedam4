package com.eventi.left.prtfl.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.service.CodeService;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.prtfl.service.EventImgService;
import com.eventi.left.prtfl.service.EventImgVO;

@Controller
@RequestMapping("/eventImg")
public class EventImgController {
	@Autowired EventImgService eventImgService;
	@Autowired CodeService codeService;
	@Autowired FilesService filesService;
	
	//마이페이지 행사이미지 리스트 페이지 이동
	@GetMapping("/eventImgList")
	public String eventImgList(Model model, EventImgVO eventImgVO) {
		model.addAttribute("nextNo", eventImgService.getSeq());
		model.addAttribute("types", codeService.getType());
		return "content/prtfl/eventImgList";
	}
	
	//행사이미지 리스트
	@PostMapping("/eventImgList")
	@ResponseBody
	public Map<String, Object> eventImgListFrm(EventImgVO eventImgVO, PagingVO paging){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("imgs", eventImgService.getEvent(eventImgVO, paging));
		result.put("paging", paging);
		
		return result;
	}
	
	//행사이미지 등록
	@PostMapping("/eventImgInsert")
	public String eventImgInsert(EventImgVO eventImgVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		eventImgService.eventInsert(eventImgVO, filesVO, uploadFile);
		return "redirect:/eventImg/eventImgList";
	}

}
