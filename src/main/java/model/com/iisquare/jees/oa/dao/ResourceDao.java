package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.Resource;

@Repository
@Scope("prototype")
public class ResourceDao extends DaoBase<Resource> {
	
	public ResourceDao() {
		super(Resource.class);
	}
}
