package com.eventi.left.design.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.common.service.CodeService;
import com.eventi.left.contest.service.ContestService;
import com.eventi.left.design.service.DesignService;
import com.eventi.left.design.service.DesignVO;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.member.service.MemberService;
import com.eventi.left.member.service.MemberVO;

/**
 * 
 * @author 배수빈 디자인에 대한 컨트롤러
 *
 */
@Controller
@RequestMapping("/design")
public class DesignController {
	@Autowired
	DesignService service;
	@Autowired
	ContestService cService;
	@Autowired
	FilesService fService;
	@Autowired
	MemberService memberService;
	@Autowired
	LikesService likeService;

	/**
	 * 
	 * @param model
	 * @param vo
	 * @param paging
	 * @return 전체 디자인 리스트 조회
	 */
	@GetMapping("/designList")
	public String bfList(Model model, DesignVO vo, PagingVO paging) {
		model.addAttribute("designList", service.designList(vo, paging));
		model.addAttribute("paging", paging);
		return "content/design/designList";
	}

	// 회원의 디자인 응모하기 폼 이동.
	@GetMapping("/designInsertForm")
	public String designInsert(Model model, DesignVO vo) {

		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();

		model.addAttribute("user", memberService.getMember(sessionId));
		model.addAttribute("contest", cService.getContest(vo.getcNo()));
		model.addAttribute("dNo", service.getSequence());
		return "content/contest/contestApplyForm";
	}

	// 디자인 응모 처리(마이페이지 응모리스트 페이지.)
	@PostMapping("/designInsert")
	public String designInsert(Model model, DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile, RedirectAttributes rttr) {

		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		vo.setUserId(sessionId);

		// 등록처리후, 로그인유저의 디자인내역리스트 이동.
		service.insert(vo, filesVO, uploadFile);
		// 기본 현수막 조회로 인해 카테고리 정보를 넘겨주어야 등록된 디자인을 확인가능.
		rttr.addAttribute("category",vo.getCaregory());
		
		return "redirect:/design/designMypage";
	}

	 // 디자인응모리스트(마이페이지)
	   @GetMapping("/designMypage")
	   public String designMypage(Model model, DesignVO vo, PagingVO paging, String category) {
		   
		   //등록된 카테고리를 담아서 마이페이지 이동.
		   System.out.println("===================");
		   System.out.println(vo.getCaregory());
		   System.out.println("===================");

	      // 로그인 회원정보
	      MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
	      String sessionId = user.getUserId();
	      vo.setUserId(sessionId);

	      List<DesignVO>design = service.userDesignList(vo, paging);
	      if(design.size() == 0){
	         model.addAttribute("result", "등록한 디자인이 없습니다.");
	      //등록한 공모전 있는경우
	      }else {
	         model.addAttribute("design", design);
	         model.addAttribute("catagory", design.get(0).getCaregory());
	      }
	      
	      model.addAttribute("paging", paging);
	      return "content/myPage/myDesignList";
	   }

	// 디자인 수정처리X

	// 디자인 삭제처리(링크처리는 get/ deleteMappging form)
	@PostMapping("/delete")
	@ResponseBody
	public int designDelete(String dgnNo) {
		return service.delete(dgnNo);
	}

	// 1.디자이너 : 응모관리 -> 디자인조회
	@GetMapping("/select")
	public String userDesignSelect(Model model, String dgnNo) {
		DesignVO design = new DesignVO();
		design.setDgnNo(dgnNo);
		// 디자인 1건조회
		design = service.getDesign(design);
		List<FilesVO> files = new ArrayList<>();

		// 1건에 대한 파일리스트 받고 디자인vo 세팅 후 모델담기.
		files = fService.fileList(design.getDgnNo());
		design.setFiles(files);
		model.addAttribute("design", design);

		return "content/myPage/design";
	}

	// 1.공모전작성자 : 공모전지원자조회 -> 디자인조회
	@PostMapping("/ajaxSelect")
	@ResponseBody
	public DesignVO designSelect(DesignVO vo) {	
		// 디자인 1건조회
		DesignVO design = service.getDesign(vo);
		List<FilesVO> files = new ArrayList<>();

		// 1건에 대한 파일리스트 받고
		files = fService.fileList(design.getDgnNo());
		design.setFiles(files);

		return design;
	}
	
	//디자인 포트폴리오 상세보기
	@PostMapping("/oneSelect")
	@ResponseBody
	public DesignVO oneSelect(DesignVO vo) {
		// 디자인 1건조회
		DesignVO design = service.oneDesign(vo);
		List<FilesVO> files = new ArrayList<>();

		// 1건에 대한 파일리스트 받고
		files = fService.fileList(design.getDgnNo());
		design.setFiles(files);

		return design;
	}

	// 임시저장된 게시글 전체들고오기
	@PostMapping("/dSave")
	@ResponseBody
	public List<DesignVO> dSave(DesignVO vo) {
		return service.dSave(vo);
	}

	// 임시저장된 게시글 상세들고오기
	@PostMapping("/saveSelect")
	@ResponseBody
	public List<DesignVO> saveGetDesign(DesignVO vo) {
		return service.saveGetDesign(vo);
	}

	// 임시저장 불러오기 수정처리
	@PostMapping("/saveUpdate")
	public String saveUpdateContest(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile) {
		service.saveUpdateDesign(vo, filesVO, uploadFile);
		return "redirect:/design/designMypage";
	}

	// 로그인회원의 관심 디자인리스트
	@PostMapping("/ajaxlike")
	@ResponseBody
	public Map<String, Object> designlikeList(LikesVO vo, PagingVO paging) {

		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setUserId(user.getUserId());

		// 리턴할 최종Map
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("design", likeService.designlikeList(vo, paging));
		result.put("paging", paging);
		return result;
	}

	// 지은(수정)
	// 마이페이제에서 디자인 등록
	@Autowired
	CodeService codeService;

	@GetMapping("/myDesignInsert")
	public String myDesignInsert(Model model, DesignVO designVO) {

		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();

		model.addAttribute("user", memberService.getMember(sessionId));
		model.addAttribute("contest", cService.getContest(designVO.getcNo()));
		model.addAttribute("dNo", service.getSequence());

		model.addAttribute("designCat", codeService.getdesignCat());
		return "content/design/designInsert";
	}

	@PostMapping("/myDesignInsert")
	public String myDesignInsert(Model model, DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile) {

		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		vo.setUserId(sessionId);

		// 등록처리후, 로그인유저의 디자인내역리스트 이동.
		service.insert(vo, filesVO, uploadFile);
		return "redirect:/design/designList";
	}
	
	//디자인 리스트조회 폼
	@GetMapping("/myDesignList")
	public String myDesignList() {
		return "content/prtfl/myDesignList";
	}

}
