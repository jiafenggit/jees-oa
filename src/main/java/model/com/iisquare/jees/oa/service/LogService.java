package com.iisquare.jees.oa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.Configuration;
import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.framework.util.SqlUtil;
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.dao.LogDao;
import com.iisquare.jees.oa.dao.LogSettingDao;
import com.iisquare.jees.oa.dao.MemberDao;

@Service
public class LogService extends ServiceBase {
	
	@Autowired
	public LogDao logDao;
	@Autowired
	public LogSettingDao logSettingDao;
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public Configuration configuration;
	
	public LogService() {}
	
	public Map<Object, Object> search(Map<String, String> map, int page, int pageSize) {
		StringBuilder sb = new StringBuilder("select * from ")
			.append(logDao.tableName()).append(" where 1 = 1");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Object name = map.get("name");
		if(!DPUtil.empty(name)) {
			sb.append(" and name like :name");
			paramMap.put("name", DPUtil.stringConcat("%", name, "%"));
		}
		Object type = map.get("type");
		if(!DPUtil.empty(type)) {
			sb.append(" and type = :type");
			paramMap.put("type", type);
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
		Object sessionId = map.get("sessionId");
		if(!DPUtil.empty(sessionId)) {
			sb.append(" and session_id = :sessionId");
			paramMap.put("sessionId", sessionId);
		}
		String createId = map.get("createId");
		if(!DPUtil.empty(createId)) {
			sb.append(" and create_id = :createId");
			paramMap.put("createId", ValidateUtil.filterInteger(createId, true, 0, null));
		}
		Object createIp = map.get("createIp");
		if(!DPUtil.empty(createIp)) {
			sb.append(" and create_ip = :createIp");
			paramMap.put("createIp", createIp);
		}
		String timeStart = map.get("timeStart");
		if(!DPUtil.empty(timeStart)) {
			sb.append(" and create_time >= :timeStart");
			paramMap.put("timeStart", DPUtil.dateTimeToMillis(timeStart, configuration.getDateTimeFormat()));
		}
		String timeEnd = map.get("timeEnd");
		if(!DPUtil.empty(timeStart)) {
			sb.append(" and create_time <= :timeEnd");
			paramMap.put("timeEnd", DPUtil.dateTimeToMillis(timeEnd, configuration.getDateTimeFormat()));
		}
		String sql = sb.toString();
		int total = logDao.getCount(sql, paramMap, true);
		sql = DPUtil.stringConcat(sql, SqlUtil.buildLimit(page, pageSize));
		List<Map<String, Object>> rows = logDao.npJdbcTemplate().queryForList(sql, paramMap);
		rows = ServiceUtil.fillTextMap(memberDao, rows,
				new String[]{"create_id"}, new String[]{"name"});
		return DPUtil.buildMap(new String[]{"total", "rows"}, new Object[]{total, rows});
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = logDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillTextMap(memberDao, map,
					new String[]{"create_id"}, new String[]{"name"});
		}
		return map;
	}
	
	public int delete(Object... ids) {
		return logDao.deleteByIds(ids);
	}
	
	public int truncate() {
		String sql = DPUtil.stringConcat("truncate table ", logDao.tableName());
		return logDao.update(sql);
	}
}
