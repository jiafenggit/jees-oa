package com.iisquare.jees.oa.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.oa.dao.SettingDao;
import com.iisquare.jees.oa.domain.Member;
import com.iisquare.jees.oa.domain.Setting;

@Service
public class SettingService extends ServiceBase {
	
	@Autowired
	public SettingDao settingDao;
	
	public SettingService() {}
	
	public String get(String type, String name) {
		Map<String, Object> where = new HashMap<String, Object>(3);
		where.put("type", type);
		where.put("name", name);
		Setting setting = settingDao.getByFields(where, null, null);
		if(null == setting) return "";
		return setting.getContent();
	}
	
	public boolean set(String type, String name, String content, String remark, Member member) {
		if(null == member) return false;
		Map<String, Object> where = new HashMap<String, Object>(3);
		where.put("type", type);
		where.put("name", name);
		Setting setting = settingDao.getByFields(where, null, null);
		long time = System.currentTimeMillis();
		if(null == setting) {
			setting = new Setting();
			setting.setName(name);
			setting.setContent(content);
			if(!DPUtil.empty(remark)) {
				setting.setRemark(remark);
			}
			setting.setIdCreate(member.getId());
			setting.setIdUpdate(member.getId());
			setting.setSort(0);
			setting.setTimeCreate(time);
			setting.setTimeUpdate(time);
			return insert(setting) > 0;
		} else {
			setting.setContent(content);
			if(!DPUtil.empty(remark)) {
				setting.setRemark(remark);
			}
			setting.setIdUpdate(member.getId());
			setting.setTimeUpdate(time);
			return update(setting) > 0;
		}
	}
	
	public int insert(Setting setting) {
		return settingDao.insert(setting);
	}
	
	public int update(Setting setting) {
		return settingDao.update(setting);
	}
}
