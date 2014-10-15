package com.iisquare.jees.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.DPUtil;
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
	
	public List<Map<String, Object>> getList(String columns, boolean bNoRefer, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Map<String, Object>> list;
		if(bNoRefer) {
			list = resourceDao.getList(columns, new String[]{"refer_id"}, new Object[]{0}, null, append, page, pageSize);
		} else {
			list = resourceDao.getList(columns, null, new Object[]{}, append, page, pageSize);
		}
		list = ServiceUtil.fillRelations(list, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return list;
	}
	
	public Resource getById(Object id) {
		return resourceDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = resourceDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public Resource getByRouter(Integer exceptId, String module, String controller, String action) {
		if(null == exceptId) exceptId = 0;
		return resourceDao.getByFields(new String[]{resourceDao.getPrimaryKey(), "module", "controller", "action"},
				new Object[]{exceptId, module, controller, action}, new String[]{"!=", "=", "=", "="}, null);
	}
	
	public int insert(Resource persist) {
		return resourceDao.insert(persist);
	}
	
	public int update(Resource persist) {
		return resourceDao.update(persist);
	}
	
	public int delete(Object... ids) {
		String idStr = DPUtil.safeImplode(",", ids);
		if(DPUtil.empty(idStr)) return 0;
		int count = resourceDao.getCount(new String[]{"parent_id"}, new Object[]{idStr}, new String[]{"in"}, null);
		if(count > 0) return -1;
		return resourceDao.deleteByIds(ids);
	}
}
