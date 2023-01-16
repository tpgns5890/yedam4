package com.eventi.left.contest.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.common.mapper.CodeMapper;
import com.eventi.left.contest.mapper.ContestMapper;
import com.eventi.left.contest.mapper.WinnerMapper;
import com.eventi.left.contest.service.ContestService;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.design.mapper.DesignMapper;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.member.service.MemberVO;

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
	@Autowired
	DesignMapper dMapper;
	@Autowired
	UploadFileMethod newUp; //파일업로드 메소드

	@Value("${spring.servlet.multipart.location}")
	String filePath;

	// 공모전 전체리스트
	@Override
	public List<ContestVO> contestList(ContestVO vo, PagingVO paging) {
		// 페이징
		paging.setTotalRecord(mapper.contestCount(vo));
		paging.setPageUnit(12); // 12개 출력 (default 10)
		paging.setPageSize(2); // 확인위해서 세팅 삭제예정
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		return mapper.contestList(vo);

	}

	// 공모전 1건
	@Override
	public ContestVO getContest(ContestVO vo) {
		// 상세조회시 조회수 업데이트
		mapper.selectUpdate(vo);
		return mapper.getContest(vo);
	}

	// 공모전 등록
	@Override
	public int insertContest(ContestVO vo, FilesVO files, List<MultipartFile> uploadFile, WinnerVO wvo) {

//		// 사진 등록
//		String realFolder = "C:/test/";
//		File dir = new File(realFolder);
//		if (!dir.isDirectory()) {
//			dir.mkdirs();
//		}
//
//		// 넘어온 파일개수 리스트 VO저장
//		for (int i = 0; i < uploadFile.size(); i++) {
//
//			// 파일명이 0개 이상일경우만
//			if (uploadFile.get(i).getSize() > 0) {
//				// 원본 파일명
//				String originName = uploadFile.get(i).getOriginalFilename();
//				files.setFNm(originName);
//
//				// 동일 파일명 있을경우
//				int check = fMapper.NameCheck(files);
//				if (check != 0) {
//					String reName = "rename:" + uploadFile.get(i).getOriginalFilename();
//					files.setSevNm(reName);// 동일 파일명 이름대체
//				}
//
//				files.setTargetId(vo.getcNo()); // 공고번호
//				files.setCategory("TO1"); // 카테고리(공모전)
//				files.setSaveAddr(filePath); // 경로지정
//
//				fMapper.insertFile(files);
//			}
//
//		}

		// 공모전 우승금액
		// 1.index 기준으로 등수설정
		// 2.from입력값이 있다면 insert 및 합계계산후 총상금 지정.
		int hap = 0;
		String[] array = wvo.getWinnerPay();

		wvo.setCoNo(vo.getcNo()); // 공모전번호
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && !array[i].equals("")) {
				System.out.println(i + 1 + "등 : " + array[i] + "원");
				wvo.setGrade(i + 1); // 등수
				wvo.setWPay(Integer.parseInt(array[i])); // 상금금액
				hap += Integer.parseInt(array[i]); // 합계계산
				wMapper.insertWinner(wvo);
			}
		}
		vo.setPay(hap); // 총 상금합계.
		return mapper.insertContest(vo);
	}

	// 공모전 수정
	@Override
	public int updateContest(ContestVO vo) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setWriter(user.getUserId());
		return mapper.updateContest(vo);
	}

	// 공모전,우승상금 삭제
	@Override
	public int deleteContest(ContestVO contestVO) {
		// 응모한 디자인이 있으면 삭제불가.
		if (dMapper.contestDesignList(contestVO.getcNo()).size() > 0) {
			return 0;
		}

		wMapper.deleteWinner(contestVO.getcNo());
		return mapper.deleteContest(contestVO);
	}

	// 공모전 시퀀스정보
	@Override
	public String getSequence() {
		return mapper.getSequence();
	}

	// 공모전 마감일수
	@Override
	public Map<ContestVO, Integer> getDday(ContestVO vo) {

		// 전체리스트 조회
		List<ContestVO> contestList = mapper.contestList(vo);
		Map<ContestVO, Integer> contestMap = new HashMap<ContestVO, Integer>();

		String todayFm = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())); // 오늘날짜

		// 리스트 객체 담기
		for (ContestVO contest : contestList) {
			Date strDate = contest.getDtCls(); // 기준 날짜 데이터(("yyyy-MM-dd")의 형태)

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date date = new Date((strDate).getTime());
			Date today = null;

			try {
				today = new Date(dateFormat.parse(todayFm).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// D-day 계산
			long calculate = date.getTime() - today.getTime();

			int Ddays = (int) (calculate / (24 * 60 * 60 * 1000));
			contest.setdDay(Ddays);

			contestMap.put(contest, Ddays); // map 추가.
		}

		return contestMap;
	}

	@Override
	public List<ContestVO> myContestList(ContestVO vo, PagingVO paging) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		vo.setWriter(sessionId);

		paging.setTotalRecord(mapper.myContest(vo));
		paging.setPageUnit(10);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return mapper.myContestList(vo);
	}

}