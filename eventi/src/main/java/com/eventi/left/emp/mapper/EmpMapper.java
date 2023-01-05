package com.eventi.left.emp.mapper;

import java.util.List;

import com.eventi.left.likes.service.LikesVO;

public interface EmpMapper { 
	public LikesVO getEmp(LikesVO empVO); 
	public List<LikesVO> getEmpList(LikesVO empVO); 
	public void empInsert(LikesVO empVO);
}
