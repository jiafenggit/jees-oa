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
import com.iisquare.jees.oa.dao.DutyDao;
import com.iisquare.jees.oa.dao.MemberOrganizeRelDao;
import com.iisquare.jees.oa.domain.Duty;

@Service
public class DutyService extends ServiceBase {
	
	@Autowired
	public DutyDao dutyDao;
	@Autowired
	public MemberDao memberDao;
	@Autowired
	public MemberOrganizeRelDao memberOrganizeRelDao;
	
	public Map<String, String> getStatusMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("0", "禁用");
		map.put("1", "正常");
		return map;
	}
	
	public DutyService() {}
	
	public List<Map<String, Object>> getList(String columns, String orderBy, int page, int pageSize) {
		String append = null;
		if(!DPUtil.empty(orderBy)) append = DPUtil.stringConcat(" order by ", orderBy);
		List<Map<String, Object>> list = dutyDao.getList(columns, null, new Object[]{}, append, page, pageSize);
		list = ServiceUtil.fillFields(list, new String[]{"status"}, new Map<?, ?>[]{getStatusMap()}, null);
		list = ServiceUtil.fillRelations(list, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		return list;
	}
	
	public Duty getById(Object id) {
		return dutyDao.getById(id);
	}
	
	public Map<String, Object> getById(Object id, boolean bFill) {
		Map<String, Object> map = dutyDao.getById("*", id);
		if(null != map && bFill) {
			map = ServiceUtil.fillFields(map, new String[]{"status"}, new Map<?, ?>[]{getStatusMap()}, null);
			map = ServiceUtil.fillRelations(map, memberDao, new String[]{"create_id", "update_id"}, new String[]{"serial", "name"}, null);
		}
		return map;
	}
	
	public int insert(Duty persist) {
		return dutyDao.insert(persist);
	}
	
	public int update(Duty persist) {
		return dutyDao.update(persist);
	}
	
	public int delete(Object... ids) {
		String idStr = DPUtil.safeImplode(",", ids);
		if(DPUtil.empty(idStr)) return 0;
		int count = dutyDao.getCount(DPUtil.stringConcat("parent_id in (", idStr, " )"), new Object[]{}, null);
		if(count > 0) return -1;
		count = memberOrganizeRelDao.getCount(DPUtil.stringConcat("duty_id in (", idStr, " )"), new Object[]{}, null);
		if(count > 0) return -2;
		return dutyDao.deleteByIds(ids);
	}
}
