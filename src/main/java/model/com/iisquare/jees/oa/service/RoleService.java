package com.iisquare.jees.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.dao.RoleDao;
import com.iisquare.jees.oa.domain.Role;

@Service
public class RoleService extends ServiceBase {
	
	@Autowired
	public RoleDao roleDao;
	@Autowired
	public MemberDao memberDao;
	
	public RoleService() {}
	
	public int getCount() {
		return roleDao.getCount(null, null, null);
	}
	
	public List<Map<String, Object>> getList(String columns, Map<String, Object> where,
			Map<String, String> operators, String orderBy, int page, int pageSize) {
		String append = "order by " + orderBy;
		List<Map<String, Object>> list = roleDao.getPage(columns, where, operators, append, page, pageSize);
		list = ServiceUtil.fillPropertyText(list, new Role(), "status");
		list = ServiceUtil.fillTextMap(memberDao, list,
				new String[]{"create_id", "update_id"}, new String[]{"name", "name"});
		return list;
	}
	
	public Role getById(Object id) {
		return roleDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = roleDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillPropertyText(map, new Role(), "status");
			map = ServiceUtil.fillTextMap(memberDao, map,
					new String[]{"create_id", "update_id"}, new String[]{"name", "name"});
		}
		return map;
	}
	
	public int insert(Role role) {
		return roleDao.insert(role);
	}
	
	public int update(Role role) {
		return roleDao.update(role);
	}
}
