package com.eventi.left.contest.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.Paging;
import com.eventi.left.common.service.CodeService;
import com.eventi.left.contest.service.ContestService;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerService;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.design.service.DesignService;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;

import groovy.util.logging.Log4j;

@Controller
@RequestMapping("/contest")
@Log4j
public class ContestController {

	@Autowired
	ContestService service;
	@Autowired
	WinnerService wService;
	@Autowired
	FilesService fService;
	@Autowired
	DesignService dService;
	@Autowired
	CodeService codeService;

	// 공모전 전체리스트(첫페이지)
	@GetMapping("/List")
	public String contestList(Model model, ContestVO vo, Paging paging) {
		model.addAttribute("contestList", service.contestList(vo, paging));
		return "content/contest/contestList";
	}

	// 공모전 전체리스트(
	@PostMapping("/List")
	@ResponseBody
	public List<ContestVO> orderList(ContestVO vo, Paging paging) {

		// 전체리스트 조회
		List<ContestVO> list = service.contestList(vo, paging);
		// 값 세팅후 리턴할 리스트 생성
		List<ContestVO> setList = new ArrayList<>();
		ContestVO setVo = new ContestVO();

		// 전체리스트 반복문
		for (ContestVO cVo : list) {
			// 공모전 마감일수 키,값 
			Map<ContestVO, Integer> getDdayList = service.getDday(vo);
			for (Map.Entry<ContestVO, Integer> entry : getDdayList.entrySet()) {
//				System.out.println("key :" + entry.getKey().getcNo() + " / value : " + entry.getKey().getdDay());
				
				//공고번호가 같은값의 D-day 세팅
				if(cVo.getcNo().equals(entry.getKey().getcNo())) {
					setVo.setdDay(entry.getKey().getdDay());
				}
			}
			// 코드별 문자열 변환후 세팅
			setVo = cVo;
			setVo.setCategory(codeService.codeSelect(cVo.getCategory()));
			setVo.setStyle(codeService.codeSelect(cVo.getStyle()));
			setList.add(setVo);
		}
		
		System.out.println("현재페이지:"+ paging.getPage());

//		System.out.println("정렬기준 :" + vo.getOrder());
//		System.out.println("분류별 :" + vo.getCategory()); 
		return setList;
	}

	// 1.공모전 상세리스트(get)
	@GetMapping("/select")
	public String contestSelect(Model model, ContestVO cVo) {

		// 객체생성 및 코드별 문자열 변환후 세팅
		ContestVO contest = service.getContest(cVo);
		contest.setCategory(codeService.codeSelect(contest.getCategory())); // 카테고리
		contest.setStyle(codeService.codeSelect(contest.getStyle())); // 스타일

		model.addAttribute("contest", contest); // 모델 넘겨주기.
		model.addAttribute("designList", dService.contestDesignList(cVo.getcNo()));
		model.addAttribute("fileList", fService.fileList(cVo.getcNo()));
		model.addAttribute("winnerList", wService.winnerList(cVo.getcNo()));

		return "content/contest/contest";
	}

	// 2.공모전 상세리스트(ajax)
	@PostMapping("/select")
	public ContestVO contestSelect(ContestVO vo) {
		vo = service.getContest(vo);
		return vo;
	}

	// 등록화면이동
	@GetMapping("/insert")
	public String contestInsert(Model model) {
		model.addAttribute("cNo", service.getSequence()); // 입력할 공모전번호
		return "content/contest/contestInsertForm";
	}

	// 등록처리(상금등록 추가중..)
	@PostMapping("/insert")
	public String contestInsertForm(ContestVO vo, FilesVO files, List<MultipartFile> uploadFile, WinnerVO wvo) {
		service.insertContest(vo, files, uploadFile, wvo);
		return "redirect:/contest/select?cNo=" + vo.getcNo();
	}

	// 수정화면이동
	@GetMapping("/update")
	public String contestupdate(Model model, ContestVO vo) {
		model.addAttribute("contest", service.getContest(vo));
		model.addAttribute("winner", wService.winnerList(vo.getcNo()));
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
	public String contestDelete(@PathVariable("cNo") String cNo) {
		service.deleteContest(cNo);
		return "redirect:/contest/List"; // 페이징처리를 위해 전체리스트 메소드 실행후 페이지이동
	}

}
