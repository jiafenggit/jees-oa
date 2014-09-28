package com.iisquare.jees.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.ServiceUtil;
import com.iisquare.jees.oa.dao.MemberDao;
import com.iisquare.jees.oa.dao.ResourceDao;
import com.iisquare.jees.oa.domain.Resource;

@Service
public class ResourceService extends ServiceBase {
	
	@Autowired
	public ResourceDao resourceDao;
	@Autowired
	public MemberDao memberDao;
	
	public ResourceService() {}
	
	public int getCount(Map<String, Object> where, Map<String, String> operators, String append) {
		return resourceDao.getCount(where, operators, append);
	}
	
	public List<Map<String, Object>> getList(String columns, Map<String, Object> where,
			Map<String, String> operators, String orderBy, int page, int pageSize) {
		String append = "order by " + orderBy;
		List<Map<String, Object>> list = resourceDao.getPage(columns, where, operators, append, page, pageSize);
		list = ServiceUtil.fillTextMap(memberDao, list,
				new String[]{"create_id", "update_id"}, new String[]{"name", "name"});
		return list;
	}
	
	public Resource getById(Object id) {
		return resourceDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = resourceDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillPropertyText(map, new Resource(), "status");
			map = ServiceUtil.fillTextMap(memberDao, map,
					new String[]{"create_id", "update_id"}, new String[]{"name", "name"});
		}
		return map;
	}
	
	public int insert(Resource role) {
		return resourceDao.insert(role);
	}
	
	public int update(Resource role) {
		return resourceDao.update(role);
	}
	
	public int delete(Object... ids) {
		return resourceDao.deleteByIds(ids);
	}
}
