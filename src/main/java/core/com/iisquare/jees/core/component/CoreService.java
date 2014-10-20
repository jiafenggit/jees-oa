package com.iisquare.jees.core.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;

@Service
public abstract class CoreService extends ServiceBase {
	
	public List<Map<String, Object>> fillList(List<Map<String, Object>> list) {
		return list;
	}
	
	public Map<String, Object> fillMap(Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>(1);
		list.add(map);
		return fillList(list).get(0);
	}
}
