package com.iisquare.jees.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.oa.dao.IconTypeDao;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.domain.Icon;
import com.iisquare.jees.oa.domain.IconType;
import com.iisquare.jees.oa.domain.Role;

@Service
public class IconTypeService extends ServiceBase {
	
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public IconTypeDao iconTypeDao;
	
	public IconTypeService() {}
	
	public List<Map<String, Object>> getList(String columns, Map<String, Object> where,
			Map<String, String> operators, String orderBy, int page, int pageSize) {
		String append = "order by " + orderBy;
		List<Map<String, Object>> list = iconTypeDao.getPage(columns, where, operators, append, page, pageSize);
		list = ServiceUtil.fillPropertyText(list, new Role(), "status");
		list = ServiceUtil.fillTextMap(memberDao, list,
				new String[]{"create_id", "update_id"}, new String[]{"name", "name"});
		return list;
	}
	
	public IconType getById(Object id) {
		return iconTypeDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = iconTypeDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillPropertyText(map, new Icon(), "status");
			map = ServiceUtil.fillTextMap(memberDao, map,
					new String[]{"create_id", "update_id"}, new String[]{"name", "name"});
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
