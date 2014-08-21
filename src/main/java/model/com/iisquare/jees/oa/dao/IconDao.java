package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.Icon;

@Repository
@Scope("prototype")
public class IconDao extends DaoBase<Icon> {
	
	public IconDao() {
		super(Icon.class);
	}
}
