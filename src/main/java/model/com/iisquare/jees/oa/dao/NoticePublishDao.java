package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.NoticePublish;

@Repository
@Scope("prototype")
public class NoticePublishDao extends DaoBase<NoticePublish> {
	
	public NoticePublishDao() {
		super(NoticePublish.class);
	}
}
