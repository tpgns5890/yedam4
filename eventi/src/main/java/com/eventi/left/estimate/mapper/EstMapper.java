package com.eventi.left.estimate.mapper;

import java.util.List;

import com.eventi.left.estimate.service.EstVO;
import com.eventi.left.estimate.service.PropVO;

public interface EstMapper {
	//견적요청서
	public EstVO getEst(EstVO estVO); //1건조회
	public List<EstVO> getEstList(EstVO estVO); //전체조회
	
	//제안서
	public PropVO getPropList(PropVO propVO); //전체조회
	public PropVO insertProp(PropVO propVO);  //등록
}
