package com.iisquare.jees.oa.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.framework.util.SqlUtil;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.dao.MenuDao;
import com.iisquare.jees.oa.dao.RoleMenuRelDao;
import com.iisquare.jees.oa.domain.Menu;

@Service
public class MenuService extends ServiceBase {
	
	@Autowired
	public MenuDao menuDao;
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public RoleMenuRelDao roleMenuRelDao;
	
	public Map<String, String> getStatusMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "禁用");
		map.put("1", "正常");
		return map;
	}
	
	public Map<String, String> getGoalMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("_iframe", "Tab框架打开");
		map.put("_blank", "新窗口打开");
		map.put("_tab", "Tab内容打开");
		map.put("_self", "当前页打开");
		return map;
	}
	
	public MenuService() {}
	
	public List<Map<String, Object>> getList(String columns, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Map<String, Object>> list;
		list = menuDao.getList(columns, null, new Object[]{}, append, page, pageSize);
		list = ServiceUtil.fillFields(list, new String[]{"status", "goal"}, new Map<?, ?>[]{getStatusMap(), getGoalMap()}, null);
		list = ServiceUtil.fillRelations(list, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return list;
	}
	
	public List<Map<String, Object>> getListByRoleId(Object[] roleIdArray) {
		String roleIdStr = SqlUtil.buildSafeWhere(",", roleIdArray);
		if(DPUtil.empty(roleIdStr)) return new ArrayList<Map<String, Object>>(0);
		String sql = DPUtil.stringConcat("select * from ", menuDao.tableName(),
				" where ", menuDao.getPrimaryKey(), " in (select menu_id from ", roleMenuRelDao.tableName(),
				" where role_id in (", roleIdStr, ")) and status = 1 order by sort desc");
		return menuDao.queryForList(sql);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = menuDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillFields(map, new String[]{"status", "goal"}, new Map<?, ?>[]{getStatusMap(), getGoalMap()}, null);
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public Menu getById(Object id) {
		return menuDao.getById(id);
	}
	
	public int insert(Menu persist) {
		return menuDao.insert(persist);
	}
	
	public int update(Menu persist) {
		return menuDao.update(persist);
	}
	
	public int delete(Object... ids) {
		String idStr = SqlUtil.buildSafeWhere(",", ids);
		if(DPUtil.empty(idStr)) return 0;
		int count = menuDao.getCount(DPUtil.stringConcat("parent_id in (", idStr, " )"), new Object[]{}, null);
		if(count > 0) return -1;
		return menuDao.deleteByIds(ids);
	}
}
