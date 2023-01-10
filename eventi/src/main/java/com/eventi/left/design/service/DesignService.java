package com.eventi.left.design.service;

import java.util.List;

import com.eventi.left.common.Paging;

public interface DesignService {
	List<DesignVO> designList(DesignVO vo, Paging paging);
}	
