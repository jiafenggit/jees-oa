package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.Test;

@Repository
@Scope("prototype")
public class IconDao extends DaoBase<Test> {
	
	public IconDao() {
		super(Test.class);
	}
}
