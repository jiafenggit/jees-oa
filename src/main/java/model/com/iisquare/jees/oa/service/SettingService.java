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
import com.iisquare.jees.framework.util.ServletUtil;
import com.iisquare.jees.framework.util.SqlUtil;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.dao.SettingDao;
import com.iisquare.jees.oa.domain.Member;
import com.iisquare.jees.oa.domain.Setting;

@Service
public class SettingService extends ServiceBase {
	
	@Autowired
	public SettingDao settingDao;
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public Configuration configuration;
	
	public SettingService() {}
	
	public Map<Object, Object> search(Map<String, Object> map, String orderBy, int page, int pageSize) {
		StringBuilder sb = new StringBuilder("select * from ")
			.append(settingDao.tableName()).append(" where 1 = 1");
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
		Object content = map.get("content");
		if(!DPUtil.empty(content)) {
			sb.append(" and content like :content");
			paramMap.put("content", DPUtil.stringConcat("%", content, "%"));
		}
		Object remark = map.get("remark");
		if(!DPUtil.empty(remark)) {
			sb.append(" and remark like :remark");
			paramMap.put("remark", DPUtil.stringConcat("%", remark, "%"));
		}
		Object serial = map.get("serial");
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
		Object timeStart = map.get("timeStart");
		if(!DPUtil.empty(timeStart)) {
			sb.append(" and operate_time >= :timeStart");
			paramMap.put("timeStart", DPUtil.dateTimeToMillis(timeStart, configuration.getDateTimeFormat()));
		}
		Object timeEnd = map.get("timeEnd");
		if(!DPUtil.empty(timeStart)) {
			sb.append(" and operate_time <= :timeEnd");
			paramMap.put("timeEnd", DPUtil.dateTimeToMillis(timeEnd, configuration.getDateTimeFormat()));
		}
		if(!DPUtil.empty(orderBy)) sb.append(" order by ").append(orderBy);
		String sql = sb.toString();
		int total = settingDao.getCount(sql, paramMap, true).intValue();
		sql = DPUtil.stringConcat(sql, SqlUtil.buildLimit(page, pageSize));
		List<Map<String, Object>> rows = settingDao.npJdbcTemplate().queryForList(sql, paramMap);
		rows = ServiceUtil.fillRelations(rows, memberDao, new String[]{"operate_id"}, new String[]{"serial", "name"}, null);
		return DPUtil.buildMap(new String[]{"total", "rows"}, new Object[]{total, rows});
	}
	
	public String get(String type, String name) {
		Map<String, Object> where = new HashMap<String, Object>(3);
		where.put("type", type);
		where.put("name", name);
		Setting setting = settingDao.getByFields(where, null, null);
		if(null == setting) return "";
		return setting.getContent();
	}
	
	public int set(String type, String name, String content, String remark, ControllerBase controllerBase, Member member) {
		if(null == member) return 0;
		Map<String, Object> where = new HashMap<String, Object>(3);
		where.put("type", type);
		where.put("name", name);
		Setting setting = settingDao.getByFields(where, null, null);
		long time = System.currentTimeMillis();
		if(null == setting) {
			setting = new Setting();
			setting.setType(type);
			setting.setName(name);
		}
		setting.setContent(content);
		if(null != remark) {
			setting.setRemark(remark);
		}
		setting.setOperateId(member.getId());
		setting.setOperateIp(ServletUtil.getRemoteAddr(controllerBase.getRequest()));
		setting.setOperateTime(time);
		if(DPUtil.empty(setting.getId())) {
			return insert(setting);
		} else {
			return update(setting);
		}
	}
	
	public int insert(Setting persist) {
		return settingDao.insert(persist).intValue();
	}
	
	public int update(Setting persist) {
		return settingDao.update(persist);
	}
	
	public Setting getById(Object id) {
		return settingDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = settingDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"operate_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public int delete(Object... ids) {
		return settingDao.deleteByIds(ids);
	}
}
