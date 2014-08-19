package com.iisquare.jees.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.oa.dao.MemberDao;

@Service
public class MenuService extends ServiceBase {
	
	@Autowired
	public MemberDao memberDao;
	
	public MenuService() {}
	
}
