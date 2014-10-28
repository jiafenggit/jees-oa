package com.iisquare.jees.oa.service;

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
import com.iisquare.jees.framework.util.ValidateUtil;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.dao.NoticeDao;
import com.iisquare.jees.oa.dao.NoticeTypeDao;
import com.iisquare.jees.oa.domain.Notice;

@Service
public class NoticeService extends ServiceBase {
	
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public NoticeDao noticeDao;
	@Autowired
	public NoticeTypeDao noticeTypeDao;
	
	public Map<String, String> getStatusMap(boolean bAll) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if(bAll) {
			map.put("-1", "已删除");
		}
		map.put("0", "禁用");
		map.put("1", "正常");
		return map;
	}
	
	public NoticeService() {}
	
	public Map<Object, Object> search(Map<String, Object> map, String orderBy, int page, int pageSize) {
		StringBuilder sb = new StringBuilder("select * from ")
			.append(noticeDao.tableName()).append(" where 1 = 1");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Object title = map.get("title");
		if(!DPUtil.empty(title)) {
			sb.append(" and title like :title");
			paramMap.put("title", DPUtil.stringConcat("%", title, "%"));
		}
		int typeId = ValidateUtil.filterInteger(DPUtil.parseString(map.get("typeId")), true, 0, null, 0);
		if(!DPUtil.empty(typeId)) {
			sb.append(" and type_id = :typeId");
			paramMap.put("typeId", typeId);
		}
		String statusStr = SqlUtil.buildSafeWhere(",", (Object[]) paramMap.get("status"));
		if(!DPUtil.empty(statusStr)) {
			sb.append(DPUtil.stringConcat(" and status in (", statusStr, ")"));
		}
		Object serial = map.get("serial");
		if(!DPUtil.empty(serial)) {
			sb.append(" and operate_id in (select ").append(memberDao.getPrimaryKey())
				.append(" from ").append(memberDao.tableName()).append(" where serial = :serial)");
			paramMap.put("serial", serial);
		}
		if(!DPUtil.empty(orderBy)) sb.append(" order by ").append(orderBy);
		String sql = sb.toString();
		int total = noticeDao.getCount(sql, paramMap, true);
		sql = DPUtil.stringConcat(sql, SqlUtil.buildLimit(page, pageSize));
		List<Map<String, Object>> rows = noticeDao.npJdbcTemplate().queryForList(sql, paramMap);
		rows = ServiceUtil.fillFields(rows, new String[]{"status"}, new Map<?, ?>[]{getStatusMap(true)}, null);
		rows = ServiceUtil.fillRelations(rows, noticeTypeDao, new String[]{"type_id"}, new String[]{"name"}, null);
		rows = ServiceUtil.fillRelations(rows, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return DPUtil.buildMap(new String[]{"total", "rows"}, new Object[]{total, rows});
	}
	
	public List<Notice> getList(Map<String, Object> where, Map<String, String> operators, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Notice> list = noticeDao.getList(where, operators, append, page, pageSize);
		return list;
	}
	
	public Notice getById(Object id) {
		return noticeDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = noticeDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillFields(map, new String[]{"status"}, new Map<?, ?>[]{getStatusMap(true)}, null);
			map = ServiceUtil.fillRelations(map, noticeTypeDao, new String[]{"type_id"}, new String[]{"name"}, null);
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public int insert(Notice persist) {
		return noticeDao.insert(persist);
	}
	
	public int update(Notice persist) {
		return noticeDao.update(persist);
	}
	
	public int delete(Object... ids) {
		return noticeDao.deleteByIds(ids);
	}
}
