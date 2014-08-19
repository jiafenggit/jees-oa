package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.Organize;

@Repository
@Scope("prototype")
public class OrganizeDao extends DaoBase<Organize> {
	
	public OrganizeDao() {
		super(Organize.class);
	}
}
