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
import com.iisquare.jees.oa.dao.IconDao;
import com.iisquare.jees.oa.dao.IconTypeDao;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.domain.Icon;

@Service
public class IconService extends ServiceBase {
	
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public IconDao iconDao;
	@Autowired
	public IconTypeDao iconTypeDao;
	
	public IconService() {}
	
	public Map<Object, Object> search(Map<String, String> map, String orderBy, int page, int pageSize) {
		StringBuilder sb = new StringBuilder("select * from ")
			.append(iconDao.tableName()).append(" where 1 = 1");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Object name = map.get("name");
		if(!DPUtil.empty(name)) {
			sb.append(" and name like :name");
			paramMap.put("name", DPUtil.stringConcat("%", name, "%"));
		}
		String type = map.get("type");
		if(!DPUtil.empty(type)) {
			sb.append(" and type_id in (select ").append(iconTypeDao.getPrimaryKey())
				.append(" from ").append(iconTypeDao.tableName()).append(" where name = :type)");
			paramMap.put("type", type);
		}
		Object url = map.get("url");
		if(!DPUtil.empty(url)) {
			sb.append(" and url like :url");
			paramMap.put("url", DPUtil.stringConcat("%", url, "%"));
		}
		if(!DPUtil.empty(orderBy)) sb.append(" order by ").append(orderBy);
		String sql = sb.toString();
		int total = iconDao.getCount(sql, paramMap, true);
		sql = DPUtil.stringConcat(sql, SqlUtil.buildLimit(page, pageSize));
		List<Map<String, Object>> rows = iconDao.npJdbcTemplate().queryForList(sql, paramMap);
		rows = ServiceUtil.fillRelations(rows, iconTypeDao, new String[]{"type_id"}, new String[]{"name"}, null);
		rows = ServiceUtil.fillRelations(rows, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return DPUtil.buildMap(new String[]{"total", "rows"}, new Object[]{total, rows});
	}
	
	public List<Icon> getList(Map<String, Object> where, Map<String, String> operators, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Icon> list = iconDao.getPage(where, operators, append, page, pageSize);
		return list;
	}
	
	public Icon getById(Object id) {
		return iconDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = iconDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillProperties(map, new Icon(), new String[]{"status"}, new String[]{"statusText"}, true);
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public int insert(Icon resource) {
		return iconDao.insert(resource);
	}
	
	public int update(Icon resource) {
		return iconDao.update(resource);
	}
	
	public int delete(Object... ids) {
		return iconDao.deleteByIds(ids);
	}
}
