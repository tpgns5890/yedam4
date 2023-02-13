package com.eventi.left.design.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.design.mapper.DesignMapper;
import com.eventi.left.design.service.DesignService;
import com.eventi.left.design.service.DesignVO;
import com.eventi.left.files.FileDto;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.member.service.MemberVO;

@Service
public class DesignServiceImpl implements DesignService {
	@Autowired
	DesignMapper mapper;
	@Autowired
	FilesMapper filesMapper;
	@Autowired
	UploadFileMethod newUp;

	@Override
	public List<DesignVO> designList(DesignVO vo, PagingVO paging) {

		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		if (user != null) {
			String sessionId = user.getUserId();
			vo.setUserId(sessionId);
		}

		paging.setTotalRecord(mapper.allCount(vo));
		paging.setPageUnit(6);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return mapper.designList(vo);
	}

	@Override
	// 공모전 1건에 지원자리스트!!
	public List<DesignVO> contestDesignList(DesignVO vo, PagingVO paging) {

		if (vo.getCaregory() != null && vo.getCaregory().equals("D2")) {
			paging.setPageUnit(5);
		} else if (vo.getCaregory() != null && vo.getCaregory().equals("D1")) {
			paging.setPageUnit(3);
		} else {
			paging.setPageUnit(4);
		}
		paging.setTotalRecord(mapper.entryDesign(vo));
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		return mapper.contestDesignList(vo);
	}

	@Override
	public int insert(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile) {
		// 대표사진세팅.
		vo.setCenterImg(uploadFile[0].getOriginalFilename());

		// 파일업로드,디자인등록
		int result = mapper.insert(vo);
		uploadFiles(uploadFile, vo);
		return result;
	}

	@Override
	public int update(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile) {
		return mapper.update(vo);
	}

	@Override
	public int delete(String dgnNo) {
		return mapper.delete(dgnNo);
	}

	@Override
	public String getSequence() {
		return mapper.getSequence();
	}

	@Override
	public List<DesignVO> userDesignList(DesignVO vo, PagingVO paging) {

		paging.setTotalRecord(mapper.count(vo));

		if (vo.getCaregory() != null && vo.getCaregory().equals("D2")) {
			paging.setPageUnit(4);
		} else if (vo.getCaregory() != null && vo.getCaregory().equals("D1")) {
			paging.setPageUnit(3);
		} else {
			paging.setPageUnit(6);
		}
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		return mapper.userDesignList(vo);
	}

	@Override
	public DesignVO getDesign(DesignVO vo) {
		return mapper.getDesign(vo);
	}
	
	//디자인 포트폴리오 한건 조회
	@Override
	public DesignVO oneDesign(DesignVO vo) {
		return mapper.oneDesign(vo);
	}

	@Override
	public List<DesignVO> saveGetDesign(DesignVO DesignVO) {
		return mapper.saveGetdesign(DesignVO);
	}

	@Override
	public List<DesignVO> dSave(DesignVO DesignVO) {
		return mapper.dSave(DesignVO);
	}

	// 공모전 임시저장 불러오기 수정
	@Override
	public int saveUpdateDesign(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile) {

		// 로그인회원아이디
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setUserId(user.getUserId());

		// 1번째 사진 대표사진세팅.
		vo.setCenterImg(uploadFile[0].getOriginalFilename());

		// 파일업로드,디자인등록
		uploadFiles(uploadFile, vo);
		return mapper.update(vo);
	}

	// 디자인 파일 업로드하는 메소드
	public void uploadFiles(MultipartFile[] uploadfile, DesignVO vo) {
		List<FileDto> list = new ArrayList<FileDto>();
		try {
			list = newUp.uploadFiles(uploadfile, vo.getDgnNo(), "T09");// 대상구분 디자인
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
