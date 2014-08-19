package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.RoleResourceRel;

@Repository
@Scope("prototype")
public class RoleResourceRelDao extends DaoBase<RoleResourceRel> {
	
	public RoleResourceRelDao() {
		super(RoleResourceRel.class);
	}
}
