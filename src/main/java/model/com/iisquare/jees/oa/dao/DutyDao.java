package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.Duty;

@Repository
@Scope("prototype")
public class DutyDao extends DaoBase<Duty> {
	
	public DutyDao() {
		super(Duty.class);
	}
}
