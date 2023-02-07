package com.eventi.left.prtfl.web;

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
import com.eventi.left.common.SessionUtil;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.member.service.MemberVO;
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
		model.addAttribute("moves", mcMoveService.moveList(mcMoveVO, paging));
		model.addAttribute("paging", paging);
		model.addAttribute("nextNo", mcMoveService.getSeq());
		
		return "content/prtfl/mcMoveList";
	}
	
	//포트폴리오에서 가져올 경우
	@PostMapping("/moveList")
	@ResponseBody
	public Map<String, Object> moveLists(McMoveVO mcMoveVO, PagingVO paging){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("videos", mcMoveService.moveList(mcMoveVO, paging));
		result.put("paging", paging);
		
		return result;
	}
	
	//동영상 추가
	@PostMapping("/moveInsert")
	public String moveInsert(McMoveVO mcMoveVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		mcMoveService.moveInsert(mcMoveVO, filesVO, uploadFile);
		return "redirect:/move/moveList?userId=" + mcMoveVO.getUserId();
	}
	
	//동영상 삭제
	@GetMapping("/moveDelete")
	public String moveDelete(McMoveVO mcMoveVO, FilesVO filesVO) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		
		String targetId = mcMoveVO.getMoveNo();
		filesService.deleteFile(targetId);
		mcMoveService.moveDelete(mcMoveVO);
		return "redirect:/move/moveList?userId=" + user.getUserId();
	}
}
