package com.eventi.left.estimate.service.impl;

import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.eventi.left.common.PagingVO;
import com.eventi.left.estimate.mapper.EstMapper;
import com.eventi.left.estimate.service.EstService;
import com.eventi.left.estimate.service.EstVO;
import com.eventi.left.estimate.service.PropGdVO;
import com.eventi.left.estimate.service.PropVO;
import com.eventi.left.member.mapper.MemberMapper;
import com.eventi.left.rent.service.RentGdVO;

@Service
public class EstServiceImpl implements EstService {

	@Autowired
	EstMapper estMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//견적요청서 단건조회
	@Override
	public EstVO getEst(String eno) {
		return estMapper.getEst(eno);
	}
	//견적요청서 전체조회
	@Override
	public List<EstVO> getEstList(EstVO estVO,PagingVO paging) {
		paging.setTotalRecord(estMapper.count(estVO));
		paging.setPageUnit(10);
		estVO.setFirst(paging.getFirst());
		estVO.setLast(paging.getLast());
		return estMapper.getEstList(estVO);
	}
	//견적요청서 등록
	@Override
	public int insertEst(EstVO estVO) {
		 return estMapper.insertEst(estVO);
	}
	//제안서 견적서별 전체조회
	@Override
	public List<PropVO> getPropList(String eno) {
		return estMapper.getPropList(eno);
	}
	//업체 제안서 채택/후기수 조회 
	@Override
	public Map<String, String> getCount(String eno, String userId) {
		return estMapper.getCount(eno, userId);
	}
	
	// 제안서 등록
	@Override
	public int insertProp(PropVO propVO) {
		return estMapper.insertProp(propVO);
	}
	
	//해당업체의 물품조회
	@Override
	public List<RentGdVO> myGdList(RentGdVO rentGdVO) {
		return estMapper.myGdList(rentGdVO);
	}
	
	//견적요청서 삭제
	@Override
	public int deleteEst(EstVO estVO) {
		return estMapper.deleteEst(estVO);
	}
	
	//해당업체의 제안서 조회
	@Override
	public PropVO getProp(PropVO propVO) {
		return estMapper.getProp(propVO);
	}
	
	//제안서 삭제
	@Override
	public int delProp(PropVO propVO) {
		return estMapper.delProp(propVO);
	}
	
	//제안서에 등록된 물품 조회
	@Override
	public List<PropGdVO> getPropGdList(PropGdVO propGdVO) {
		return estMapper.getPropGdList(propGdVO);
	}
	
	//제안서 채택
	@Override
	public int chooesProp(PropVO propVO) {
		var userId = propVO.getUserId();
		var email = memberMapper.getMember(userId).getUserEmail();
		String title = "제안서가 채택되었습니다.";
		String content = "등록하신 제안서가 채택되었습니다.";
		mailing(email, title, content);
		return estMapper.chooesProp(propVO);
	}
	
	/* 이메일 보내기 */
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
	
	//전체 개수 count
	@Override
	public int count(EstVO estVO) {
		return estMapper.count(estVO);
	}
}
