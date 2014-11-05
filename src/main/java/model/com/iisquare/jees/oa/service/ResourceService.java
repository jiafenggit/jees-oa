package com.iisquare.jees.oa.service;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.iisquare.jees.oa.dao.ResourceDao;
import com.iisquare.jees.oa.dao.RoleDao;
import com.iisquare.jees.oa.dao.RoleResourceRelDao;
import com.iisquare.jees.oa.domain.Resource;

@Service
public class ResourceService extends ServiceBase {
	
	@Autowired
	public ResourceDao resourceDao;
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public RoleResourceRelDao roleResourceRelDao;
	@Autowired
	public RoleDao roleDao;
	
	public Map<String, String> getStatusMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("-1", "禁用验证");
		map.put("0", "启用验证");
		return map;
	}
	
	public ResourceService() {}
	
	public List<Map<String, Object>> getList(Map<String, Object> map, String orderBy, int page, int pageSize) {
		StringBuilder sb = new StringBuilder("select * from ")
			.append(resourceDao.tableName()).append(" where 1 = 1");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!DPUtil.empty(map.get("no_refer"))) { // 排除非独立设置的权限
			sb.append(" and refer_id = 0");
		}
		boolean menuEnable = !DPUtil.empty(map.get("menu_enable"));
		if(menuEnable) { // 读取菜单项
			sb.append(" and menu_list_enable = 1");
		}
		if(!DPUtil.empty(orderBy)) sb.append(" order by ").append(orderBy);
		String sql = sb.toString();
		sql = DPUtil.stringConcat(sql, SqlUtil.buildLimit(page, pageSize));
		List<Map<String, Object>> list = resourceDao.npJdbcTemplate().queryForList(sql, paramMap);
		if(menuEnable) { // 组合菜单地址
			for (Map<String, Object> row : list) {
				StringBuilder menuUrl = new StringBuilder();
				if(!DPUtil.empty(row.get("menu_pick_enable"))) {
					Object module = row.get("module");
					if(!DPUtil.empty(module)) menuUrl.append(module).append("/");
					Object controller = row.get("controller");
					if(!DPUtil.empty(controller)) menuUrl.append(controller).append("/");
					Object action = row.get("action");
					if(!DPUtil.empty(action)) menuUrl.append(action);
				}
				row.put("menu_url", menuUrl.toString());
			}
		}
		list = ServiceUtil.fillFields(list, new String[]{"status"}, new Map<?, ?>[]{getStatusMap()}, null);
		list = ServiceUtil.fillRelations(list, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return list;
	}
	
	public List<Object> getIdArrayByRoleId(Object[] roleIdArray) {
		String roleIdStr = SqlUtil.buildSafeWhere(",", roleIdArray);
		if(DPUtil.empty(roleIdStr)) return new ArrayList<Object>(0);
		String sql = DPUtil.stringConcat("select resource_id from ", roleResourceRelDao.tableName(),
				" where role_id in (", roleIdStr, ")");
		List<Map<String, Object>> list = roleResourceRelDao.queryForList(sql);
		return ServiceUtil.getFieldValues(list, "resource_id");
	}
	
	public Resource getById(Object id) {
		return resourceDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = resourceDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillFields(map, new String[]{"status"}, new Map<?, ?>[]{getStatusMap()}, null);
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	/**
	 * 获取最终引用资源对象
	 */
	public Resource getReferRoot(Resource resource) {
		if(null == resource) return null;
		Resource refer = resourceDao.getById(resource.getReferId());
		if(null == refer) return resource;
		return getReferRoot(refer);
	}
	
	public Resource getByRouter(Integer exceptId, String module, String controller, String action) {
		if(null == exceptId) exceptId = 0;
		return resourceDao.getByFields(new String[]{resourceDao.getPrimaryKey(), "module", "controller", "action"},
				new Object[]{exceptId, module, controller, action}, new String[]{"!=", "=", "=", "="}, null);
	}
	
	public int insert(Resource persist) {
		return resourceDao.insert(persist);
	}
	
	public int update(Resource persist) {
		return resourceDao.update(persist);
	}
	
	public int delete(Object... ids) {
		String idStr = SqlUtil.buildSafeWhere(",", ids);
		if(DPUtil.empty(idStr)) return 0;
		int count = resourceDao.getCount(new String[]{"parent_id"}, new Object[]{idStr}, new String[]{"in"}, null);
		if(count > 0) return -1;
		return resourceDao.deleteByIds(ids);
	}
}
