package com.iisquare.jees.oa.service;

import java.util.HashMap;
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
import com.iisquare.jees.oa.domain.Menu;

@Service
public class MenuService extends ServiceBase {
	
	@Autowired
	public MenuDao menuDao;
	@Autowired
	public MemberDao memberDao;
	
	public MenuService() {}
	
	public Map<Object, Object> search(Map<String, String> map, int page, int pageSize) {
		StringBuilder sb = new StringBuilder("select * from ")
			.append(menuDao.tableName()).append(" where 1 = 1");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Object name = map.get("name");
		if(!DPUtil.empty(name)) {
			sb.append(" and name like :name");
			paramMap.put("name", DPUtil.stringConcat("%", name, "%"));
		}
		Object module = map.get("module");
		if(!DPUtil.empty(module)) {
			sb.append(" and module = :module");
			paramMap.put("module", module);
		}
		Object controller = map.get("controller");
		if(!DPUtil.empty(controller)) {
			sb.append(" and controller = :controller");
			paramMap.put("controller", controller);
		}
		Object action = map.get("action");
		if(!DPUtil.empty(action)) {
			sb.append(" and action = :action");
			paramMap.put("action", action);
		}
		Object referId = map.get("referId");
		if(null != referId && !"".equals(referId)) {
			sb.append(" and refer_id = :referId");
			paramMap.put("referId", DPUtil.parseInt(referId));
		}
		String sql = sb.toString();
		int total = menuDao.getCount(sql, paramMap, true);
		sql = DPUtil.stringConcat(sql, SqlUtil.buildLimit(page, pageSize));
		List<Map<String, Object>> rows = menuDao.npJdbcTemplate().queryForList(sql, paramMap);
		rows = ServiceUtil.fillTextMap(memberDao, rows,
				new String[]{"create_id", "update_id"}, new String[]{"name", "name"});
		return DPUtil.buildMap(new String[]{"total", "rows"}, new Object[]{total, rows});
	}
	
	public int getCount(Map<String, Object> where, Map<String, String> operators, String append) {
		return menuDao.getCount(where, operators, append);
	}
	
	public List<Map<String, Object>> getList(String columns, Map<String, Object> where,
			Map<String, String> operators, String orderBy, int page, int pageSize) {
		String append = "order by " + orderBy;
		List<Map<String, Object>> list = menuDao.getPage(columns, where, operators, append, page, pageSize);
		list = ServiceUtil.fillTextMap(memberDao, list,
				new String[]{"create_id", "update_id"}, new String[]{"name", "name"});
		return list;
	}
	
	public Menu getById(Object id) {
		return menuDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = menuDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillPropertyText(map, new Menu(), "status");
			map = ServiceUtil.fillTextMap(memberDao, map,
					new String[]{"create_id", "update_id"}, new String[]{"name", "name"});
		}
		return map;
	}
	
	public int insert(Menu role) {
		return menuDao.insert(role);
	}
	
	public int update(Menu role) {
		return menuDao.update(role);
	}
	
	public int delete(Object... ids) {
		return menuDao.deleteByIds(ids);
	}
}
