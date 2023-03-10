package com.eventi.left.contest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import com.eventi.left.design.service.DesignVO;
import com.eventi.left.files.FileDto;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.likes.mapper.LikesMapper;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.member.mapper.MemberMapper;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.money.mapper.MoneyMapper;
import com.eventi.left.money.service.MoneyVO;

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
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	MoneyMapper moneyMapper;

	@Value("${spring.servlet.multipart.location}")
	String filePath;

	// 공모전 전체리스트
	@Override
	public List<ContestVO> contestList(ContestVO vo, PagingVO paging) {

		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		if (user != null) {
			String sessionId = user.getUserId();
			vo.setUserId(sessionId);
		}

		// 페이징 동적쿼리로 카테고리 입력시 개수파악(setFirst,setLast 세팅)
		paging.setTotalRecord(mapper.contestCount(vo));
		paging.setPageUnit(6); // 6개 출력 (default 10)
		paging.setPageSize(3);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		return mapper.contestList(vo);
	}

	// 공모전 1건 상세조회
	@Override
	public ContestVO getContest(String vo) {
		mapper.selectUpdate(vo); // 조회수 업데이트
		return mapper.getContest(vo);
	}

	// 공모전 등록
	@Override
	public int insertContest(ContestVO vo, WinnerVO wvo, MultipartFile[] uploadFile) {
		
		int hap = vo.getPay();// 공모전 등록비
		wvo.setCoNo(vo.getcNo());// 결제후 전달 된 매개변수값.
		
		// from입력값 등록
		String[] array = wvo.getWinnerPay();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && !array[i].equals("")) {
				wvo.setGrade(i + 1); // 등수
				wvo.setwPay(Integer.parseInt(array[i])); // 상금금액
				hap += Integer.parseInt(array[i]); // 합계계산
				wMapper.insertWinner(wvo);
			}
		}
		// 총 상금합계+등록비
		vo.setPay(hap);

		// 파일업로드,공모전등록
		int result = mapper.insertContest(vo);
		uploadFiles(uploadFile, vo);

		// 임시저장이 아닌경우만 이메일발송.
		if (vo.getSave().equals("N")) {
			// 수신동의 된 회원에게 이메일발송
			Thread thread = new Thread(new MembersSendMail(vo, result, 0));
			thread.start();
		}
		return result;
	}

	// 공모전 수정
	@Override
	public int updateContest(ContestVO vo, MultipartFile[] uploadFile) {

		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setWriter(user.getUserId());
		int result = 0;

		// 마감기한 연장신청
		if (vo.getDtExtns() != null) {
			ContestVO contest = mapper.getContest(vo.getcNo());
			// 1회만 연장(null인경우만)
			if (contest.getDtExtns() == null) {
				result = mapper.updateExtension(vo);
			} else {
				return 0;
			}

			// 제목,내용 수정할경우(그 외 공모전민감자료 수정불가)
		} else {
			// 파일업로드,공모전 수정
			uploadFiles(uploadFile, vo);
			result = mapper.updateContest(vo);

			// 현재(메인쓰레드) 다른쓰레드로 맡기고 실행(동시에 실행됌)
			Thread thread = new Thread(new MembersSendMail(vo, 0, result));// vo,insert,update
			thread.start();
		}
		return result;
	}

	// 공모전 임시저장 불러오기 수정
	@Override
	public int saveUpdateContest(ContestVO vo, MultipartFile[] uploadFile, WinnerVO wvo) {

		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setWriter(user.getUserId());

		// 공모전 우승금액
		// 1.index 기준으로 등수설정
		// 2.from입력값이 있다면 insert 및 합계계산후 총상금 지정.
		int hap = vo.getPay();
		String[] array = wvo.getWinnerPay();
		wvo.setCoNo(vo.getcNo());

		// 공모전 등록된 우승등수,금액 삭제 후 재등록.
		wMapper.deleteWinner(vo.getcNo());
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && !array[i].equals("")) {
				wvo.setGrade(i + 1); // 등수
				wvo.setwPay(Integer.parseInt(array[i])); // 상금금액
				hap += Integer.parseInt(array[i]); // 합계계산
				wMapper.insertWinner(wvo);
			}
		}
		// 공모전 합계+우승금액
		vo.setPay(hap);

		// 파일업로드,공모전 수정
		int result = mapper.saveUpdateContest(vo);
		uploadFiles(uploadFile, vo);

		// 임시저장이 아니고 불러오기 등록일경우 이메일발송.
		if (vo.getSave().equals("N")) {
			// 현재(메인쓰레드) 다른쓰레드로 맡기고 실행(동시에 실행됌)
			Thread thread = new Thread(new MembersSendMail(vo, result, 0)); // vo,insert,update
			thread.start();
		}

		return result;
	}

	// 공모전 우승금액,파일 삭제(공모전 고유번호)
	@Override
	public int deleteContest(ContestVO vo) {

		int result = 0;
		
		//삭제 리스트.
		List<String>cNoList=  vo.getCNoList();
		
		for(String cNo : cNoList) {
			// 작성자정보, 공모전 정보.
			ContestVO contest = mapper.getContest(cNo);
			MemberVO user = memberMapper.getMember(contest.getWriter());
			
			// 응모한 디자인이 있으면 삭제불가.
			if (dMapper.checkDesign(cNo) > 0) {
				return result;
			}else {
				LikesVO like = new LikesVO();
				like.setTargetNo(cNo);
				like.setCategory("T01"); 
				likeMapper.likeDelete(like);// 공모전 좋아요 삭제  
				fMapper.deleteFile(cNo); // 공모전 이미지 삭제
				wMapper.deleteWinner(cNo); // 공모전 상금 삭제
				
				// 출금요청 insert
				//if (moneyMapper.oneMoneySelect(vo.getcNo()) != null) {
					MoneyVO money = new MoneyVO();
					money.setBankName(user.getBankCode()); // 은행정보
					money.setBankAccount(user.getAccnt()); // 계좌번호
					money.setMoType("M2"); // 출금코드
					money.setMoPrice(contest.getPay());
					money.setUserId(contest.getWriter());
					money.setUserName(user.getName());
					money.setSettYN("N"); // 출금코드
					money.setTargetId(contest.getcNo());
					money.setMoCat("T01");
					moneyMapper.insertMoney(money);
				//}
				result += mapper.deleteContest(contest);
			}
		}
		return result;
	}

	// 공모전 시퀀스정보
	@Override
	public String getSequence() {
		return mapper.getSequence();
	}

	// 마이페이지 나의 공모전조회.
	@Override
	public List<ContestVO> myContestList(ContestVO vo, PagingVO paging) {
		// 로그인 회원정보
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		String sessionId = user.getUserId();
		vo.setWriter(sessionId);

		// 페이징처리
		paging.setTotalRecord(mapper.myContest(vo));
		paging.setPageUnit(10); // 10개 출력
		paging.setPageSize(3);
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		return mapper.myContestList(vo);
	}

	// 공모전 임시지정 조회.
	@Override
	public List<ContestVO> cSave(ContestVO vo) {
		return mapper.cSave(vo);
	}

	// 공모전 임시저장 1건 조회.
	@Override
	public ContestVO saveGetContest(ContestVO vo) {

		vo = mapper.saveGetContest(vo);
		vo.setWinnerVO(wMapper.winnerList(vo.getcNo()));
		return vo;
	}

	// 메소드--------------------------------------------------------------------
	// 공모전 파일 업로드하는 메소드
	public void uploadFiles(MultipartFile[] uploadfile, ContestVO vo) {
		List<FileDto> list = new ArrayList<FileDto>();
		try {
			list = newUp.uploadFiles(uploadfile, vo.getcNo(), "T01"); // 대상구분 공모전
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author 배수빈 메일발송을 위한 내부클래스 선언.
	 *
	 */
	class MembersSendMail implements Runnable {

		// 필드선언
		ContestVO vo;
		int insert;
		int update;

		// 생성자 선언
		public MembersSendMail(ContestVO vo, int insert, int update) {
			super();
			this.vo = vo;
			this.insert = insert;
			this.update = update;
		}

		// 이메일 발송
		public void mailing(String email, String setTitle, String setContent) {
			String setFrom = "yedam4eventi@gmail.com";
			String toMail = email;
			String title = setTitle;
			String content = setContent;
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
				helper.setFrom(setFrom);
				helper.setTo(toMail);
				helper.setSubject(title);
				helper.setText(content, true);
				mailSender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 실행할 메소드 : 공모전 등록,수정시 수신동의한 회원들에게 이메일발송
		@Override
		public void run() {
			// 수신동의한 멤버리스트.
			List<MemberVO> emailList = memberMapper.memberEmail();

			// 처리된결과가 있으면
			if (insert > 0) {
				for (MemberVO email : emailList) {
					String toMail = email.getUserEmail();
					String title = "[" + vo.getcNo() + "]" + "공모전 업로드";
					String content = vo.getTtl() + "공모전이 업로드되었습니다 많은 참가 부탁드립니다!.";
					mailing(toMail, title, content);
				}
			} else if (update > 0) {
				for (MemberVO email : emailList) {
					String toMail = email.getUserEmail();
					String title = "[" + vo.getcNo() + "]" + "공모전 수정";
					String content = vo.getTtl() + "공모전이 수정되었습니다.";
					mailing(toMail, title, content);
				}
			}

		}
	}

}