package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.NoticeType;

@Repository
@Scope("prototype")
public class NoticeTypeDao extends DaoBase<NoticeType> {
	
	public NoticeTypeDao() {
		super(NoticeType.class);
	}
}
