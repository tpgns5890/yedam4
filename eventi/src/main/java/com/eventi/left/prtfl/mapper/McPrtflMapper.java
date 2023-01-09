package com.eventi.left.prtfl.mapper;

import java.util.List;

import com.eventi.left.prtfl.service.McPrtflVO;

public interface McPrtflMapper {
	public List<McPrtflVO> mcAll(McPrtflVO McPrtflVO);
	public McPrtflVO mcSelect(McPrtflVO mcPrtflVO);
}
