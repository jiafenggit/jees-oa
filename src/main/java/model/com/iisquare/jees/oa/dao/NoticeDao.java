package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.Notice;

@Repository
@Scope("prototype")
public class NoticeDao extends DaoBase<Notice> {
	
	public NoticeDao() {
		super(Notice.class);
	}
}
