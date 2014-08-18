package com.iisquare.jees.demo.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.demo.domain.Test;
import com.iisquare.jees.framework.model.DaoBase;

@Repository
@Scope("prototype")
public class TestDao extends DaoBase<Test> {
	
	public TestDao() {
		super(Test.class);
	}
}
