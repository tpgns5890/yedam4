package com.eventi.left.contest.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.eventi.left.files.FileDto;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.likes.mapper.LikesMapper;
import com.eventi.left.likes.service.LikesVO;
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
	UploadFileMethod newUp; // 파일업로드 메소드
	@Autowired
	LikesMapper likeMapper;

	@Value("${spring.servlet.multipart.location}")
	String filePath;

	// 공모전 전체리스트
	@Override
	public List<ContestVO> contestList(ContestVO vo, PagingVO paging) {
		// 페이징 동적쿼리로 카테고리 입력시 개수파악(setFirst,setLast 세팅)
		paging.setTotalRecord(mapper.contestCount(vo));
		paging.setPageUnit(6); // 12개 출력 (default 10)
		paging.setPageSize(5);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		List<ContestVO> list = mapper.contestList(vo); // 전체리스트 조회
		List<ContestVO> resultList = new ArrayList<>(); // 반환 리스트

		// 현재날짜
		String todayFm = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())); // 오늘날짜

		// 객체별 날짜계산
		for (ContestVO cVo : list) {
			Date strDate;

			// 기준 날짜 데이터(("yyyy-MM-dd")의 형태)
			if (cVo.getDtExtns() != null) {
				strDate = cVo.getDtExtns(); // 연장일.
			} else {
				strDate = cVo.getDtCls(); // 마감연장일이 없다면
			}
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
			cVo.setdDay(Ddays);

			resultList.add(cVo);
		}

		return resultList;
	}

	// 공모전 1건
	@Override
	public ContestVO getContest(String vo) {
		// 상세조회시 조회수 업데이트
		mapper.selectUpdate(vo);
		return mapper.getContest(vo);
	}

	// 공모전 등록
	@Override
	public int insertContest(ContestVO vo, WinnerVO wvo) {

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
				wvo.setwPay(Integer.parseInt(array[i])); // 상금금액
				hap += Integer.parseInt(array[i]); // 합계계산
				wMapper.insertWinner(wvo);
			}
		}
		vo.setPay(hap); // 총 상금합계.
		return mapper.insertContest(vo);
	}

	// 공모전 수정
	@Override
	public int updateContest(ContestVO vo,MultipartFile[] uploadFile) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setWriter(user.getUserId());
		
		// 마감연장 할경우
		if (vo.getDtExtns() != null) {
			ContestVO contest = mapper.getContest(vo.getcNo());
			if (contest.getDtExtns() == null) {
				return mapper.updateExtension(vo);
			} else {
				return 0;
			}
		}
		// 공모전수정
		int r = mapper.updateContest(vo);
			List<FileDto> list= new ArrayList<FileDto>();
			//파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
			try {
				list = newUp.updateFiles(uploadFile, vo.getcNo(), vo.getCategory());
			} catch (Exception e) {
				e.printStackTrace();
			}
	     return r;
	}

	// 공모전,우승상금 삭제
	@Override
	public int deleteContest(ContestVO vo) {
		// 응모한 디자인이 있으면 삭제불가.
		if (dMapper.entryDesign(vo.getcNo()) > 0) {
			return 0;
		}
		fMapper.deleteFile(vo.getcNo()); // 공모전 이미지 삭제
		wMapper.deleteWinner(vo.getcNo()); // 공모전 상금 삭제

		return mapper.deleteContest(vo);
	}

	// 공모전 시퀀스정보
	@Override
	public String getSequence() {
		return mapper.getSequence();
	}


	//마이페이지 나의 공모전조회.
	@Override
	public List<ContestVO> myContestList(ContestVO vo, PagingVO paging) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		vo.setWriter(sessionId);
		
		//페이징처리
		paging.setTotalRecord(mapper.myContest(vo));
		paging.setPageUnit(10); // 12개 출력 (default 10)
		paging.setPageSize(5);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		

		paging.setTotalRecord(mapper.myContest(vo));
		paging.setPageUnit(10);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		return mapper.myContestList(vo);
	}

}