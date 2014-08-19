package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.Menu;

@Repository
@Scope("prototype")
public class MenuDao extends DaoBase<Menu> {
	
	public MenuDao() {
		super(Menu.class);
	}
}
