package com.eventi.left.contest.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.Paging;
import com.eventi.left.common.mapper.CodeMapper;
import com.eventi.left.contest.mapper.ContestMapper;
import com.eventi.left.contest.mapper.WinnerMapper;
import com.eventi.left.contest.service.ContestService;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;

@Service
public class ContestServiceImpl implements ContestService {

	@Autowired
	ContestMapper mapper;
	@Autowired
	WinnerMapper wMapper;
	@Autowired
	FilesMapper fMapper;
	@Autowired
	CodeMapper codeMapper;

	// 공모전 전체리스트
	@Override
	public List<ContestVO> contestList(ContestVO vo, Paging paging) {
		// 페이징
		System.out.println(paging.getPage());
		paging.setTotalRecord(mapper.contestCount(vo));
		paging.setPageUnit(12); // 12개 출력 (default 10)
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		vo.setPage(paging.getStartPage());

		return mapper.contestList(vo);

	}

	// 공모전 1건
	@Override
	public ContestVO getContest(ContestVO contestVO) {
		// 상세조회시 조회수 업데이트
		mapper.selectUpdate(contestVO);
		return mapper.getContest(contestVO);
	}

	// 공모전 등록
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

			// 파일명이 0개 이상일경우만
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
		// 공모전 우승금액
		// 1.index 기준으로 등수설정
		// 2.from입력값이 있다면 insert 및 합계계산후 총상금 지정.
		int index = 1;
		int hap = 0;
		for (String data : wvo.getWinnerPay()) {
			if (data != null && !data.equals("")) {
				wvo.setGrade(index); // 등수
				wvo.setWPay(Integer.parseInt(data)); // 상금금액
				wvo.setCoNo(vo.getcNo()); // 공모전번호
				wMapper.insertWinner(wvo);
				hap += Integer.parseInt(data); // 합계계산
				index++;
			}
		}
		vo.setPay(hap); // 총 상금합계.
		return mapper.insertContest(vo);
	}

	// 공모전 수정
	@Override
	public int updateContest(ContestVO vo) {
		return mapper.updateContest(vo);
	}

	// 공모전 삭제
	@Override
	public int deleteContest(String cNo) {
		return mapper.deleteContest(cNo);
	}

	// 공모전 시퀀스정보
	@Override
	public String getSequence() {
		return mapper.getSequence();
	}

	// 공모전 마감일수
	@Override
	public Map<ContestVO, Integer> getDday(ContestVO vo) {

		//전체리스트 조회
		List<ContestVO> contestList = mapper.contestList(vo);
		Map<ContestVO, Integer> contestMap = new HashMap<ContestVO, Integer>();

		//리스트 객체 담기
		for (ContestVO contest : contestList) {
			Date strDate = contest.getDtCls(); // 기준 날짜 데이터(("yyyy-MM-dd")의 형태)
			String todayFm = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())); // 오늘날짜

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date date = new Date((strDate).getTime());
			Date today = null;

			try {
				today = new Date(dateFormat.parse(todayFm).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			//D-day 계산
			long calculate = date.getTime() - today.getTime();

			int Ddays = (int) (calculate / (24 * 60 * 60 * 1000));
			contest.setdDay(Ddays);

			contestMap.put(contest, Ddays); //map 추가.
		}

		return contestMap;
	}

}