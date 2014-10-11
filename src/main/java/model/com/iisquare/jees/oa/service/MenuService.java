package com.iisquare.jees.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.dao.MenuDao;

@Service
public class MenuService extends ServiceBase {
	
	@Autowired
	public MenuDao menuDao;
	@Autowired
	public MemberDao memberDao;
	
	public MenuService() {}
}
