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
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.dao.UploadDao;
import com.iisquare.jees.oa.domain.Upload;

@Service
public class UploadService extends ServiceBase {
	
	@Autowired
	public UploadDao uploadDao;
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public Configuration configuration;
	
	public UploadService() {}
	
	public Map<Object, Object> search(Map<String, Object> map, String orderBy, int page, int pageSize) {
		StringBuilder sb = new StringBuilder("select * from ")
			.append(uploadDao.tableName()).append(" where 1 = 1");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Object name = map.get("name");
		if(!DPUtil.empty(name)) {
			sb.append(" and name like :name");
			paramMap.put("name", DPUtil.stringConcat("%", name, "%"));
		}
		Object uri = map.get("uri");
		if(!DPUtil.empty(uri)) {
			sb.append(" and uri like :uri");
			paramMap.put("uri", DPUtil.stringConcat("%", uri, "%"));
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
		int total = uploadDao.getCount(sql, paramMap, true);
		sql = DPUtil.stringConcat(sql, SqlUtil.buildLimit(page, pageSize));
		List<Map<String, Object>> rows = uploadDao.npJdbcTemplate().queryForList(sql, paramMap);
		rows = ServiceUtil.fillRelations(rows, memberDao, new String[]{"operate_id"}, new String[]{"serial", "name"}, null);
		return DPUtil.buildMap(new String[]{"total", "rows"}, new Object[]{total, rows});
	}
	
	public Upload getById(Object id) {
		return uploadDao.getById(id);
	}
	
	public int insert(Upload persist) {
		return uploadDao.insert(persist);
	}
	
	public int delete(Object... ids) {
		return uploadDao.deleteByIds(ids);
	}
}
