package com.eventi.left.contest.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.Paging;
import com.eventi.left.contest.mapper.ContestMapper;
import com.eventi.left.contest.mapper.WinnerMapper;
import com.eventi.left.contest.service.ContestService;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;

@Service
public class ContestServiceImpl implements ContestService {

	@Autowired ContestMapper mapper;
	@Autowired WinnerMapper wMapper;
	@Autowired FilesMapper fMapper;

	@Override
	public List<ContestVO> contestList(ContestVO vo, Paging paging) {
		paging.setTotalRecord(mapper.contestCount(vo));
		paging.setPageUnit(12); // 12개 출력 (default 10)
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return mapper.contestList(vo);

	}

	@Override
	public ContestVO getContest(ContestVO vo) {
		mapper.selectUpdate(vo);
		return mapper.getContest(vo);
	}

	@Override
	public int insertContest(ContestVO vo, FilesVO files, List<MultipartFile> uploadFile, WinnerVO wvo) {

		// 사진 등록
		String realFolder = "/files/contest";
		File dir = new File(realFolder);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		// 넘어온 파일개수 리스트 VO저장
			for (int i = 0; i < uploadFile.size(); i++) {
				
				//파일명이 0개 이상일경우만
				if (uploadFile.get(i).getSize() > 0) {
				// 원본 파일명
				String originName = uploadFile.get(i).getOriginalFilename();
				files.setFNm(originName);

				// 동일 파일명 있을경우
				int check = fMapper.NameCheck(files);
				if (check != 0) {
					String reName = "rename:" + uploadFile.get(i).getOriginalFilename();
					files.setSevNm(reName);// 동일 파일명 이름대체
				}

				files.setTargetId(vo.getcNo()); // 공고번호
				files.setCategory("TO1"); // 카테고리(공모전)
				files.setSaveAddr(realFolder); // 경로지정

				fMapper.insertFile(files);
			}

		}
			// 우승등수별(1~3등 개수만큼)등록
				for(int i =1; i < wvo.getWinnerPay().length; i++){
					//등수설정
					if(i == 1) {
						wvo.setGrade(1);
						
					}else if(i == 2) {
						wvo.setGrade(2);
						
					}else {
						wvo.setGrade(3);
					}
					wvo.setWPay(wvo.getWinnerPay()[i]);
					wMapper.insertWinner(wvo);
					
				}
				
				
				
			
		return mapper.insertContest(vo);
	}

	@Override
	public int updateContest(ContestVO vo) {
		return mapper.updateContest(vo);
	}

	@Override
	public int deleteContest(String cNo) {
		return mapper.deleteContest(cNo);
	}

	@Override
	public String getSequence() {
		return mapper.getSequence();
	}

}
