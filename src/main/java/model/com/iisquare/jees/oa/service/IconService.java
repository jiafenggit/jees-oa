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
import com.iisquare.jees.oa.dao.IconDao;
import com.iisquare.jees.oa.dao.NoticeTypeDao;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.domain.Icon;

@Service
public class IconService extends ServiceBase {
	
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public IconDao iconDao;
	@Autowired
	public NoticeTypeDao iconTypeDao;
	
	public Map<String, String> getStatusMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "禁用");
		map.put("1", "正常");
		return map;
	}
	
	public IconService() {}
	
	public List<Map<String, Object>> getList(String columns, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Map<String, Object>> list = iconDao.getList(columns, null, new Object[]{}, append, page, pageSize);
		list = ServiceUtil.fillFields(list, new String[]{"status"}, new Map<?, ?>[]{getStatusMap()}, null);
		list = ServiceUtil.fillRelations(list, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return list;
	}
	
	public List<Icon> getList(String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Icon> list = iconDao.getList(null, new Object[]{}, append, page, pageSize);
		return list;
	}
	
	public Icon getById(Object id) {
		return iconDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = iconDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillRelations(map, iconDao, new String[]{"parent_id"}, new String[]{"name"}, null);
			map = ServiceUtil.fillFields(map, new String[]{"status"}, new Map<?, ?>[]{getStatusMap()}, null);
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public int insert(Icon persist) {
		return iconDao.insert(persist).intValue();
	}
	
	public int update(Icon persist) {
		return iconDao.update(persist);
	}
	
	public int delete(Object... ids) {
		String idStr = SqlUtil.buildSafeWhere(",", ids);
		if(DPUtil.empty(idStr)) return 0;
		int count = iconDao.getCount(DPUtil.stringConcat("parent_id in (", idStr, " )"), new Object[]{}, null).intValue();
		if(count > 0) return -1;
		return iconDao.deleteByIds(ids);
	}
}
