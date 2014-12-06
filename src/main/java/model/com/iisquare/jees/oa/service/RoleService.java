package com.iisquare.jees.oa.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.framework.util.SqlUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.dao.MemberRoleRelDao;
import com.iisquare.jees.oa.dao.RoleDao;
import com.iisquare.jees.oa.dao.RoleMenuRelDao;
import com.iisquare.jees.oa.dao.RoleResourceRelDao;
import com.iisquare.jees.oa.domain.Role;
import com.iisquare.jees.oa.domain.RoleMenuRel;
import com.iisquare.jees.oa.domain.RoleResourceRel;

@Service
public class RoleService extends ServiceBase {
	
	@Autowired
	public RoleDao roleDao;
	@Autowired
	public RoleResourceRelDao roleResourceRelDao;
	@Autowired
	public RoleMenuRelDao roleMenuRelDao;
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public MemberRoleRelDao memberRoleRelDao;
	
	public Map<String, String> getStatusMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "禁用");
		map.put("1", "正常");
		return map;
	}
	
	public RoleService() {}
	
	/**
	 * 根据用户主键值获取角色主键值数组
	 * @param memberId 用户主键值
	 * @param statusArray 角色状态数组，若为null，则仅获取正常状态记录
	 * @return
	 */
	public Object[] getIdListByMemberId(Object memberId, Object[] statusArray) {
		String statusStr = (null == statusArray) ? "1" : SqlUtil.buildSafeWhere(",", statusArray);
		if(null == memberId || DPUtil.empty(statusStr)) return new Object[]{};
		String primaryKey = roleDao.getPrimaryKey();
		String sql = DPUtil.stringConcat("select ", primaryKey, " from ", roleDao.tableName(),
				" where ", primaryKey, " in (select role_id from ", memberRoleRelDao.tableName(),
				" where member_id = ?) and status in (", statusStr, ")");
		List<Map<String, Object>> list = roleResourceRelDao.queryForList(sql, memberId);
		return DPUtil.collectionToArray(ServiceUtil.getFieldValues(list, primaryKey));
	}
	
	public List<Map<String, Object>> getList(String columns, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Map<String, Object>> list = roleDao.getList(columns, null, new Object[]{}, append, page, pageSize);
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
		return roleDao.insert(persist).intValue();
	}
	
	public int update(Role persist) {
		return roleDao.update(persist);
	}
	
	public int delete(Object... ids) {
		String idStr = SqlUtil.buildSafeWhere(",", ids);
		if(DPUtil.empty(idStr)) return 0;
		int count = roleDao.getCount(DPUtil.stringConcat("parent_id in (", idStr, " )"), new Object[]{}, null).intValue();
		if(count > 0) return -1;
		count = memberDao.getCount(DPUtil.stringConcat("role_id in (", idStr, " )"), new Object[]{}, null).intValue();
		if(count > 0) return -2;
		return roleDao.deleteByIds(ids);
	}
	
	public List<Map<String, Object>> getResourceRelList(Object roleId) {
		return roleResourceRelDao.getList("*", new String[]{"role_id"}, new Object[]{roleId}, null, null, 0, 0);
	}
	
	public List<Map<String, Object>> getMenuRelList(Object roleId) {
		return roleMenuRelDao.getList("*", new String[]{"role_id"}, new Object[]{roleId}, null, null, 0, 0);
	}
	
	public boolean updatePower(Object id, Object[] resourceIds, Object[] menuIds) {
		Role persist = getById(id);
		if(null == persist) return false;
		Integer roleId = persist.getId();
		if(null != resourceIds) {
			roleResourceRelDao.delete(new String[]{"role_id"}, new Object[]{roleId}, null);
			for (Object object : resourceIds) {
				Integer resourceId = ValidateUtil.filterInteger(DPUtil.parseString(object), false, 1, null, null);
				if(null == resourceId) continue ;
				RoleResourceRel roleResourceRel = new RoleResourceRel();
				roleResourceRel.setRoleId(roleId);
				roleResourceRel.setResourceId(resourceId);
				roleResourceRelDao.insert(roleResourceRel);
			}
		}
		if(null != menuIds) {
			roleMenuRelDao.delete(new String[]{"role_id"}, new Object[]{roleId}, null);
			for (Object object : menuIds) {
				Integer menuId = ValidateUtil.filterInteger(DPUtil.parseString(object), false, 1, null, null);
				if(null == menuId) continue ;
				RoleMenuRel roleMenuRel = new RoleMenuRel();
				roleMenuRel.setRoleId(roleId);
				roleMenuRel.setMenuId(menuId);
				roleMenuRelDao.insert(roleMenuRel);
			}
		}
		return true;
	}
}
