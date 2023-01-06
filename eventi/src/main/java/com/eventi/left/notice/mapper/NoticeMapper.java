package com.eventi.left.notice.mapper;

import java.util.List;

import com.eventi.left.notice.service.NoticeVO;

public interface NoticeMapper {

	public List<NoticeVO> getNoticeList(NoticeVO noticeVO); 
}
