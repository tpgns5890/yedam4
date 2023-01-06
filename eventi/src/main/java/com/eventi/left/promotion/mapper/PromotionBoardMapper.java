package com.eventi.left.promotion.mapper;

import java.util.List;

import com.eventi.left.promotion.service.PromotionVO;

public interface PromotionBoardMapper {

	public List<PromotionVO> getPromotionList(PromotionVO promotionVO);
}
