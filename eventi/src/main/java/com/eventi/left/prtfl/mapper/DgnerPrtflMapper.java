package com.eventi.left.prtfl.mapper;

import java.util.List;

import com.eventi.left.design.service.DesignVO;
import com.eventi.left.prtfl.service.DgnerPrtflVO;

public interface DgnerPrtflMapper {
	//디자이너상세조회
	public DgnerPrtflVO dgnerSelect(DgnerPrtflVO dgnerPrtflVO);
	
	//디자이너별 디자인 리스트
	public List<DesignVO> desginList(DesignVO designVO);
	
	//디자이너별 디자인 리스트 개수
	public int count(DesignVO designVO);
	
	//디자이너 포트폴리오 입력
	public int dgnerInsert(DgnerPrtflVO dgnerPrtflVO);

	//조회소 +1
	public int inqUpdate(DgnerPrtflVO dgnerPrtflVO);
}
