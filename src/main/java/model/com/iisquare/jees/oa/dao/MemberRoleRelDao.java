package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.MemberRoleRel;

@Repository
@Scope("prototype")
public class MemberRoleRelDao extends DaoBase<MemberRoleRel> {
	
	public MemberRoleRelDao() {
		super(MemberRoleRel.class);
	}
}
