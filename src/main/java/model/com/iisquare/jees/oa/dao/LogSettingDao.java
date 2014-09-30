package com.iisquare.jees.oa.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.model.DaoBase;
import com.iisquare.jees.oa.domain.LogSetting;

@Repository
@Scope("prototype")
public class LogSettingDao extends DaoBase<LogSetting> {
	
	public LogSettingDao() {
		super(LogSetting.class);
	}
}
