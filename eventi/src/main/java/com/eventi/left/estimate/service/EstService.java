package com.eventi.left.estimate.service;

import java.util.List;
import java.util.Map;

public interface EstService {
	// 견적요청서
	public EstVO getEst(String eno); // 1건조회
	public List<EstVO> getEstList(EstVO estVO); // 전체조회
	public EstVO insertEst(EstVO estVO); //견적요청서 등록
	
	// 제안서
	public List<PropVO> getPropList(String eno); // 견적서별 전체조회
	public PropVO insertProp(PropVO propVO); // 등록
	public Map<String, String> getCount(String eno, String userId); //업체 제안서 채택/후기수 조회
}
