package com.eventi.left.prtfl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.prtfl.service.McMoveService;
import com.eventi.left.prtfl.service.McMoveVO;

@Controller
@RequestMapping("/move")
public class McMoveController {
	@Autowired McMoveService mcMoveService;
	@Autowired FilesService filesService;
	
	//사회자별 동영상 전체 리스트
	@GetMapping("/moveList")
	public String moveList(Model model, McMoveVO mcMoveVO, PagingVO paging) {
		System.out.println("+++++++++++++++++++++++" + mcMoveVO);
		model.addAttribute("moves", mcMoveService.moveList(mcMoveVO, paging));
		model.addAttribute("paging", paging);
		model.addAttribute("nextNo", mcMoveService.getSeq());
		
		return "content/prtfl/mcMoveList";
	}
	
	//동영상 추가
	@PostMapping("/moveInsert")
	public String moveInsert(McMoveVO mcMoveVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		mcMoveService.moveInsert(mcMoveVO, filesVO, uploadFile);
		return "redirect:/move/moveList?userId=" + mcMoveVO.getUserId();
	}
}
