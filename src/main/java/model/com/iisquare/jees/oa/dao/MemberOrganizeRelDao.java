package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.MemberOrganizeRel;

@Repository
@Scope("prototype")
public class MemberOrganizeRelDao extends DaoBase<MemberOrganizeRel> {
	
	public MemberOrganizeRelDao() {
		super(MemberOrganizeRel.class);
	}
}
