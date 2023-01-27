package com.eventi.left.estimate.service;

import java.util.List;
import java.util.Map;

import com.eventi.left.common.PagingVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.rent.service.RentGdVO;

public interface EstService {
	// 견적요청서
	public EstVO getEst(String eno); // 1건조회

	public List<EstVO> getEstList(EstVO estVO, PagingVO paging); // 전체조회

	public int insertEst(EstVO estVO); // 견적요청서 등록

	public int deleteEst(EstVO estVO); // 견적요청서 삭제

	// 제안서
	public List<PropVO> getPropList(String eno); // 견적서별 전체조회
	
	public PropVO getProp(PropVO propVO); //해당업체의 제안서 조회

	public int insertProp(PropVO propVO); // 등록

	public Map<String, String> getCount(String eno, String userId); // 업체 제안서 채택/후기수 조회

	public List<RentGdVO> myGdList(RentGdVO rentGdVO); // 해당업체의 물품조회
	
	public int delProp(PropVO propVO); // 제안서 삭제
	
	public List<PropGdVO> getPropGdList(PropGdVO propGdVO); //제안서에 등록된 물품 조회
	
	public int chooesProp(PropVO propVO); //제안서 채택
	
	public int count(EstVO estVO); //전체 개수 count
	
}
