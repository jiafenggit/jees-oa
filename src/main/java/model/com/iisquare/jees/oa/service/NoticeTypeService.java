package com.iisquare.jees.oa.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.oa.dao.MemberDao;

@Service
public class NoticeTypeService extends ServiceBase {
	
	@Autowired
	public MemberDao memberDao;
	
	public Map<Object, String> getStatusMap() {
		Map<Object, String> map = new LinkedHashMap<Object, String>();
		map.put(0, "禁用");
		map.put(1, "正常");
		return map;
	}
	
	public NoticeTypeService() {}

}
