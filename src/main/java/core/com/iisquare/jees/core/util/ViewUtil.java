package com.iisquare.jees.core.util;

import java.util.List;
import java.util.Map;

/**
 * 视图辅助工具类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class ViewUtil {
	
	public static void collapseAll(List<Map<String, Object>> list) {
		collapseAll(list, "children");
	}
	
	/**
	 * 增加树形结构的展开状态
	 * @param list
	 * @param childrenKey
	 */
	@SuppressWarnings("unchecked")
	public static void collapseAll(List<Map<String, Object>> list, String childrenKey) {
		if(null == list) return ;
		for (Map<String, Object> map : list) {
			List<Map<String, Object>> listSub = (List<Map<String, Object>>) map.get(childrenKey);
			if(null == listSub || listSub.size() < 1) return ;
			map.put("state", "closed");
			collapseAll(listSub, childrenKey);
		}
	}
}
