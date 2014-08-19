package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.RoleMenuRel;

@Repository
@Scope("prototype")
public class RoleMenuRelDao extends DaoBase<RoleMenuRel> {
	
	public RoleMenuRelDao() {
		super(RoleMenuRel.class);
	}
}
