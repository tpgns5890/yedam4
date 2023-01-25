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
		paging.setTotalRecord(mapper.count(vo));
		paging.setPageUnit(6);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return mapper.designList(vo);
	}

	@Override
	// 공모전 1건에 지원자리스트!!
	public List<DesignVO> contestDesignList(DesignVO vo, PagingVO paging) {
		paging.setTotalRecord(mapper.entryDesign(vo.getcNo()));
		paging.setPageUnit(6); // 4개 출력 (default 10)
		System.out.println(paging);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		return mapper.contestDesignList(vo);
	}

	@Override
	public int insert(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile) {

		vo.setCenterImg(uploadFile[0].getOriginalFilename()); // 대표사진세팅.
		int r = mapper.insert(vo);
		List<FileDto> list = new ArrayList<FileDto>();
		// 파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
		try {
			list = newUp.uploadFiles(uploadFile, vo.getDgnNo(), vo.getCaregory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public int update(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile) {
		return mapper.update(vo);
	}

	@Override
	public int delete(DesignVO vo) {
		return mapper.delete(vo);
	}

	@Override
	public String getSequence() {
		return mapper.getSequence();
	}

	@Override
	public List<DesignVO> userDesignList(DesignVO vo, PagingVO paging) {
		paging.setTotalRecord(mapper.count(vo));
		paging.setPageUnit(4);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		return mapper.userDesignList(vo);
	}

	@Override
	public DesignVO getDesign(String dgnNo) {
		return mapper.getDesign(dgnNo);
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
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setUserId(user.getUserId());
		vo.setCenterImg(uploadFile[0].getOriginalFilename()); // 대표사진세팅.
		
		// 1. 파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
		int result = mapper.update(vo);
		List<FileDto> list = new ArrayList<FileDto>();
		try {
			list = newUp.uploadFiles(uploadFile, vo.getDgnNo(), vo.getCaregory());
			filesMapper.deleteFile(vo.getDgnNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
