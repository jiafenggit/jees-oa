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
import com.iisquare.jees.oa.dao.ResourceDao;
import com.iisquare.jees.oa.domain.Resource;

@Service
public class ResourceService extends ServiceBase {
	
	@Autowired
	public ResourceDao resourceDao;
	@Autowired
	public MemberDao memberDao;
	
	public ResourceService() {}
	
	public Map<Object, Object> search(Map<String, String> map, String orderBy, int page, int pageSize) {
		StringBuilder sb = new StringBuilder("select * from ")
			.append(resourceDao.tableName()).append(" where 1 = 1");
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
		if(!DPUtil.empty(orderBy)) sb.append(" order by ").append(orderBy);
		String sql = sb.toString();
		int total = resourceDao.getCount(sql, paramMap, true);
		sql = DPUtil.stringConcat(sql, SqlUtil.buildLimit(page, pageSize));
		List<Map<String, Object>> rows = resourceDao.npJdbcTemplate().queryForList(sql, paramMap);
		rows = ServiceUtil.fillRelations(rows, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return DPUtil.buildMap(new String[]{"total", "rows"}, new Object[]{total, rows});
	}
	
	public Resource getById(Object id) {
		return resourceDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = resourceDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillProperties(map, new Resource(), new String[]{"status"}, new String[]{"statusText"}, true);
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public Resource getByRouter(String module, String controller, String action) {
		return resourceDao.getByFields(new String[]{"module", "controller", "action"},
				new Object[]{module, controller, action}, null, null);
	}
	
	public int insert(Resource resource) {
		return resourceDao.insert(resource);
	}
	
	public int update(Resource resource) {
		return resourceDao.update(resource);
	}
	
	public int delete(Object... ids) {
		return resourceDao.deleteByIds(ids);
	}
}
