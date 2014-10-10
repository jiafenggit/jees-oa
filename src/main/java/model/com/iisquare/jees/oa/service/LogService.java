package com.iisquare.jees.oa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.Configuration;
import com.iisquare.jees.framework.controller.ControllerBase;
import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.framework.util.SqlUtil;
import com.iisquare.jees.oa.dao.LogDao;
import com.iisquare.jees.oa.dao.LogSettingDao;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.domain.Log;
import com.iisquare.jees.oa.domain.LogSetting;

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
	
	public int record(ControllerBase controllerBase, String name, String type, String content) {
		Log log = new Log();
		log.setName(name);
		log.setType(type);
		log.setModule(controllerBase._MODULE_);
		log.setController(controllerBase._CONTROLLER_);
		log.setAction(controllerBase._ACTION_);
		log.setRequestUrl(controllerBase._REQUEST_.getRequestURI());
		return insert(log);
	}
	
	public int insert(Log log) {
		return logDao.insert(log);
	}
	
	public List<Map<String, Object>> fillSetting(List<Map<String, Object>> list) {
		String primaryKey = logSettingDao.getPrimaryKey();
		List<Object> idList = ServiceUtil.getFieldValues(list, primaryKey);
		List<Map<String, Object>> settingList = logSettingDao.getByIds("*", DPUtil.collectionToArray(idList));
		settingList = ServiceUtil.fillRelations(settingList, memberDao, new String[]{"operate_id"}, new String[]{"serial", "name"}, null);
		Map<Object, Map<String, Object>> indexMap = ServiceUtil.indexMapList(settingList, primaryKey);
		for (Map<String, Object> item : list) {
			item.put("log_setting", indexMap.get(item.get(primaryKey)));
		}
		return list;
	}
	
	public Map<Object, Object> search(Map<String, String> map, String orderBy, int page, int pageSize) {
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
		String serial = map.get("serial");
		if(!DPUtil.empty(serial)) {
			sb.append(" and operate_id in (select ").append(memberDao.getPrimaryKey())
				.append(" from ").append(memberDao.tableName()).append(" where serial = :serial)");
			paramMap.put("serial", serial);
		}
		Object operateIp = map.get("operateIp");
		if(!DPUtil.empty(operateIp)) {
			sb.append(" and operate_ip = :operateIp");
			paramMap.put("operateIp", operateIp);
		}
		String timeStart = map.get("timeStart");
		if(!DPUtil.empty(timeStart)) {
			sb.append(" and operate_time >= :timeStart");
			paramMap.put("timeStart", DPUtil.dateTimeToMillis(timeStart, configuration.getDateTimeFormat()));
		}
		String timeEnd = map.get("timeEnd");
		if(!DPUtil.empty(timeStart)) {
			sb.append(" and operate_time <= :timeEnd");
			paramMap.put("timeEnd", DPUtil.dateTimeToMillis(timeEnd, configuration.getDateTimeFormat()));
		}
		if(!DPUtil.empty(orderBy)) sb.append(" order by ").append(orderBy);
		String sql = sb.toString();
		int total = logDao.getCount(sql, paramMap, true);
		sql = DPUtil.stringConcat(sql, SqlUtil.buildLimit(page, pageSize));
		List<Map<String, Object>> rows = logDao.npJdbcTemplate().queryForList(sql, paramMap);
		rows = ServiceUtil.fillRelations(rows, memberDao, new String[]{"operate_id"}, new String[]{"serial", "name"}, null);
		return DPUtil.buildMap(new String[]{"total", "rows"}, new Object[]{total, rows});
	}
	
	public LogSetting getSettingById(Object id) {
		return logSettingDao.getById(id);
	}
	
	public int saveSetting(LogSetting logSetting) {
		if(null == logSettingDao.getById(logSetting.getId())) {
			return logSettingDao.insert(logSetting);
		} else {
			return logSettingDao.update(logSetting);
		}
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = logDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"operate_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public int delete(Object... ids) {
		return logDao.deleteByIds(ids);
	}
	
	public void truncate() {
		String sql = DPUtil.stringConcat("truncate table ", logDao.tableName());
		logDao.update(sql);
	}
}
