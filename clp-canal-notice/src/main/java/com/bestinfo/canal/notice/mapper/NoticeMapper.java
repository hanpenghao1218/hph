package com.bestinfo.canal.notice.mapper;

import org.apache.ibatis.annotations.Param;

public interface NoticeMapper {
	Integer insertCMS(@Param("releaseTime") String releaseTime, @Param("title") String title, @Param("msgLen") int msgLen,
			@Param("msg") String msg, @Param("notifyType") int notifyType, @Param("deadline") String deadline);
}