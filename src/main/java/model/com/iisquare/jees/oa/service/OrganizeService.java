package com.iisquare.jees.oa.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.oa.dao.MemberDao;

@Service
public class OrganizeService extends ServiceBase {
	
	@Autowired
	public MemberDao memberDao;
	
	public Map<String, String> getStatusMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("-1", "已删除");
		map.put("1", "正常");
		return map;
	}
	
	public OrganizeService() {}
	
}
