package com.iisquare.jees.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iisquare.jees.framework.model.DaoBase;

/**
 * 通用业务辅助处理类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class ServiceUtil {

	/**
	 * 填充属性信息
	 */
	public static Map<String, Object> fillPropertyText(Map<String, Object> map, Object entity, String... fields) {
		for (String field : fields) {
			String property = DPUtil.upUnderscores(field);
			ReflectUtil.setPropertyValue(entity, property, null, new Object[]{map.get(field)});
			String key = DPUtil.stringConcat(field, "_text");
			Object value = ReflectUtil.getPropertyValue(entity, DPUtil.stringConcat(property, "Text"));
			map.put(key, value);
		}
		return map;
	}
	
	/**
	 * 填充属性信息
	 */
	public static List<Map<String, Object>> fillPropertyText(List<Map<String, Object>> list, Object entity, String... fields) {
		for (Map<String, Object> map : list) {
			fillPropertyText(map, entity, fields); // 此处map为内存地址（传址）
		}
		return list;
	}
	
	/**
	 * 填充关联记录对应的字段值
	 */
	public static Map<String, Object> fillTextMap(DaoBase<?> daoBase,
			Map<String, Object> map, String[] relFields, String[] fields) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(1);
		list.add(map);
		list = fillTextMap(daoBase, list, relFields, fields);
		return list.get(0);
	}
	
	/**
	 * 填充关联记录对应的字段值
	 */
	public static List<Map<String, Object>> fillTextMap(DaoBase<?> daoBase,
			List<Map<String, Object>> list, String[] relFields, String[] fields) {
		List<Object> ids = getFieldValues(list, relFields);
		if(DPUtil.empty(ids)) return list;
		/* 避免在循环中查询数据库 */
		List<Map<String, Object>> infoList = daoBase.getByIds("*", DPUtil.collectionToArray(ids));
		Map<Object, Map<String, Object>> indexMap = indexMapList(infoList, daoBase.getPrimaryKey());
		int length = relFields.length;
		for (Map<String, Object> item : list) {
			for (int i = 0; i < length; i++) {
				String relField = relFields[i];
				String field = fields[i];
				String key = DPUtil.stringConcat(relField, "_text");
				Object value = null;
				Map<String, Object> map = indexMap.get(item.get(relField));
				if(null != map) {
					value = map.get(field);
				}
				item.put(key, value);
			}
		}
		return list;
	}
	
	/**
	 * 获取对应字段的值列表
	 */
	public static List<Object> getPropertyValues(List<?> list, String... properties) {
		List<Object> valueList = new ArrayList<Object>(list.size());
		for (Object object : list) {
			for (String property : properties) {
				Object value = ReflectUtil.getPropertyValue(object, property);
				if(null == value) continue;
				valueList.add(value);
			}
		}
		return valueList;
	}
	
	/**
	 * 获取对应字段的值列表
	 */
	public static List<Object> getFieldValues(List<Map<String, Object>> list, String... fields) {
		List<Object> valueList = new ArrayList<Object>(list.size());
		for (Map<String, Object> item : list) {
			for (String field : fields) {
				Object value = item.get(field);
				if(null == value) continue;
				valueList.add(value);
			}
		}
		return valueList;
	}
	
	/**
	 * 将List数据格式化为以对应字段值为下标的Map
	 */
	public static Map<Object, Object> indexObjectList(List<?> infoList, String property) {
		Map<Object, Object> map = new HashMap<Object, Object>(DPUtil.parseInt(infoList.size() / 0.75f));
		for (Object item : infoList) {
			map.put(ReflectUtil.getPropertyValue(item, property), item);
		}
		return map;
	}
	
	/**
	 * 将List数据格式化为以对应字段值为下标的Map
	 */
	public static Map<Object, List<Object>> indexesObjectList(List<Object> list, String property) {
		Map<Object, List<Object>> map = new HashMap<Object, List<Object>>(DPUtil.parseInt(list.size() / 0.75f));
		for (Object item : list) {
			Object key = ReflectUtil.getPropertyValue(item, property);
			if(null == key) continue;
			List<Object> subList = map.get(key);
			if(null == subList) {
				subList = new ArrayList<Object>();
			}
			subList.add(item);
			map.put(key, subList);
		}
		return map;
	}
	
	/**
	 * 将List数据格式化为以对应字段值为下标的Map
	 */
	public static Map<Object, Map<String, Object>> indexMapList(List<Map<String, Object>> list, String field) {
		Map<Object, Map<String, Object>> map = new HashMap<Object, Map<String, Object>>(DPUtil.parseInt(list.size() / 0.75f));
		for (Map<String, Object> item : list) {
			map.put(item.get(field), item);
		}
		return map;
	}
	
	/**
	 * 将List数据格式化为以对应字段值为下标的Map
	 */
	public static Map<Object, List<Map<String, Object>>> indexesMapList(List<Map<String, Object>> list, String field) {
		Map<Object, List<Map<String, Object>>> map = new HashMap<Object, List<Map<String, Object>>>(DPUtil.parseInt(list.size() / 0.75f));
		for (Map<String, Object> item : list) {
			Object key = item.get(field);
			if(null == key) continue;
			List<Map<String, Object>> subList = map.get(key);
			if(null == subList) {
				subList = new ArrayList<Map<String, Object>>();
			}
			subList.add(item);
			map.put(key, subList);
		}
		return map;
	}
	
	/**
	 * 格式化层级关系
	 */
	public static List<Map<String, Object>> formatRelation(List<Map<String, Object>> list, Object root) {
		return formatRelation(list, "id", "parent_id", "children", root);
	}
	
	/**
	 * 格式化层级关系
	 */
	public static List<Map<String, Object>> formatRelation(List<Map<String, Object>> list,
			String primaryKey, String parentKey, String childrenKey, Object root) {
		Map<Object, List<Map<String, Object>>> parentMap = new HashMap<Object, List<Map<String, Object>>>();
		for (Map<String, Object> item : list) {
			Object parentValue = item.get(parentKey);
			List<Map<String, Object>> listSub = parentMap.get(parentValue);
			if(null == listSub) {
				listSub = new ArrayList<Map<String, Object>>();
			}
			listSub.add(item);
			parentMap.put(parentValue, listSub);
		}
		return processFormatRelation(parentMap, primaryKey, childrenKey, root);
	}
	
	/**
	 * 层级关系处理方法
	 */
	private static List<Map<String, Object>> processFormatRelation(
			Map<Object, List<Map<String, Object>>> parentMap, String primaryKey, String childrenKey, Object root) {
		List<Map<String, Object>> list = parentMap.get(root);
		if(null == list) {
			return new ArrayList<Map<String, Object>>();
		}
		for(Map<String, Object> map : list) {
			map.put(childrenKey, processFormatRelation(parentMap, primaryKey, childrenKey, map.get(primaryKey)));
		}
		return list;
	}
}
