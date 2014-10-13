package com.iisquare.jees.oa.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.dao.MenuDao;
import com.iisquare.jees.oa.domain.Menu;

@Service
public class MenuService extends ServiceBase {
	
	@Autowired
	public MenuDao menuDao;
	@Autowired
	public MemberDao memberDao;
	
	public Map<Object, String> getStatusMap() {
		Map<Object, String> map = new LinkedHashMap<Object, String>();
		map.put(0, "禁用");
		map.put(1, "正常");
		return map;
	}
	
	public Map<Object, String> getTargetMap() {
		Map<Object, String> map = new LinkedHashMap<Object, String>();
		map.put("_iframe", "Tab框架打开");
		map.put("_blank", "新窗口打开");
		map.put("_tab", "Tab内容打开");
		map.put("_self", "当前页打开");
		return map;
	}
	
	public MenuService() {}
	
	public List<Map<String, Object>> getList(String columns, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Map<String, Object>> list;
		list = menuDao.getPage(columns, null, null, append, page, pageSize);
		list = ServiceUtil.fillFields(list, new String[]{"status", "target"}, new Map<?, ?>[]{getStatusMap(), getTargetMap()}, null);
		list = ServiceUtil.fillRelations(list, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return list;
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = menuDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillFields(map, new String[]{"status", "target"}, new Map<?, ?>[]{getStatusMap(), getTargetMap()}, null);
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public Menu getById(Object id) {
		return menuDao.getById(id);
	}
	
	public int insert(Menu persist) {
		return menuDao.insert(persist);
	}
	
	public int update(Menu persist) {
		return menuDao.update(persist);
	}
	
	public int delete(Object... ids) {
		int count = menuDao.getCount(new String[]{"parent_id"}, new Object[]{ids}, new String[]{"in"}, null);
		if(count > 0) return -1;
		return menuDao.deleteByIds(ids);
	}
}
