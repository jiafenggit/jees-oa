package com.iisquare.jees.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.oa.dao.IconTypeDao;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.domain.IconType;

@Service
public class IconTypeService extends ServiceBase {
	
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public IconTypeDao iconTypeDao;
	
	public IconTypeService() {}
	
	public List<Map<String, Object>> getList(String columns, Map<String, Object> where,
			Map<String, String> operators, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Map<String, Object>> list = iconTypeDao.getPage(columns, where, operators, append, page, pageSize);
		list = ServiceUtil.fillProperties(list, new IconType(), new String[]{"status"}, new String[]{"statusText"}, true);
		list = ServiceUtil.fillRelations(list, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return list;
	}
	
	public IconType getById(Object id) {
		return iconTypeDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = iconTypeDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillProperties(map, new IconType(), new String[]{"status"}, new String[]{"statusText"}, true);
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public int insert(IconType resource) {
		return iconTypeDao.insert(resource);
	}
	
	public int update(IconType resource) {
		return iconTypeDao.update(resource);
	}
	
	public int delete(Object... ids) {
		for (Object id : ids) { // 下级不为空时，禁止删除
			int count = iconTypeDao.getCount(new String[]{"parent_id"}, new Object[]{id}, null, null);
			if(count > 0) return -1;
		}
		return iconTypeDao.deleteByIds(ids);
	}
}
