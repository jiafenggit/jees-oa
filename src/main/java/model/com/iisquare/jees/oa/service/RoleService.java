package com.iisquare.jees.oa.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.DPUtil;
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
	
	public Map<Object, String> getStatusMap() {
		Map<Object, String> map = new LinkedHashMap<Object, String>();
		map.put(0, "禁用");
		map.put(1, "正常");
		return map;
	}
	
	public RoleService() {}
	
	public int getCount() {
		return roleDao.getCount(null, null, null);
	}
	
	public List<Map<String, Object>> getList(String columns, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Map<String, Object>> list = roleDao.getPage(columns, null, null, append, page, pageSize);
		list = ServiceUtil.fillFields(list, new String[]{"status"}, new Map<?, ?>[]{getStatusMap()}, null);
		list = ServiceUtil.fillRelations(list, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return list;
	}
	
	public Role getById(Object id) {
		return roleDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = roleDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillFields(map, new String[]{"status"}, new Map<?, ?>[]{getStatusMap()}, null);
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public int insert(Role persist) {
		return roleDao.insert(persist);
	}
	
	public int update(Role persist) {
		return roleDao.update(persist);
	}
	
	public int delete(Object... ids) {
		int count = roleDao.getCount(new String[]{"parent_id"}, new Object[]{ids}, new String[]{"in"}, null);
		if(count > 0) return -1;
		count = memberDao.getCount(new String[]{"role_id"}, new Object[]{ids}, new String[]{"in"}, null);
		if(count > 0) return -2;
		return roleDao.deleteByIds(ids);
	}
}
