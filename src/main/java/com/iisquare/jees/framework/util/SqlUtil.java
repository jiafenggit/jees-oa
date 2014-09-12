package com.iisquare.jees.framework.util;

import java.util.HashMap;
import java.util.Map;

/**
 * SQL语句处理类
 * @author Ouyang <iisquare@163.com>
 *
 */
public class SqlUtil {
	
	private static final String regexSelectFrom = "^((?i)select)(.+)((?i)from)";
	private static final String sqlCountName = "COUNT(*)";
	
	public static String convertForCount(String sql) {
		return convertForCount(sql, null);
	}
	
	/**
	 * 将select语句转换为select COUNT(*)语句
	 * 如需要去重等操作，建议在where条件中处理
	 */
	public static String convertForCount(String sql, String countName) {
		StringBuilder sb = new StringBuilder("$1 ").append(sqlCountName);
		if(!DPUtil.empty(countName)) {
			sb.append(" as ").append(countName);
		}
		sb.append(" $3");
		sql = sql.replaceFirst(regexSelectFrom, sb.toString());
		return sql;
	}
	
	public static String convertWhereField(Object keys) {
		return DPUtil.stringConcat("w_", keys);
	}
	
	/**
	 * 仅支持占位符方式
	 */
	public static String buildWhereIn(String key, Object... values) {
		if(DPUtil.empty(values)) return null;
		StringBuilder where = new StringBuilder().append(key);
		int length = values.length;
		if(1 == length) {
			where.append("=").append("?");
		} else {
			where.append(" in (").append(DPUtil.implode(",", DPUtil.getFillArray(length, "?"))).append(")");
		}
		return where.toString();
	}
	
	public static Map<String, Object> convertWhereMap(Map<String, Object> where) {
		if(null == where) return null;
		Map<String, Object> map = new HashMap<String, Object>(DPUtil.parseInt(where.size() / 0.75f));
		for(Map.Entry<String, Object> item : where.entrySet()) {
			map.put(convertWhereField(item.getKey()), item.getValue());
		}
		return map;
	}
	
	public static String buildWhere(Object[] keys, Object[] operators, boolean bPlaceholder) {
		if(DPUtil.empty(keys)) return "";
		int length = keys.length;
		if(DPUtil.empty(operators)) {
			operators = DPUtil.getFillArray(length, "?");
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < length; i++) {
			if(i > 0) {
				sb.append(" and ");
			}
			sb.append(keys[i]).append(operators[i]);
			if(bPlaceholder) {
				sb.append("?");
			} else {
				sb.append(":").append(convertWhereField(keys[i]));
			}
		}
		return sb.toString();
	}
	
	public static String buildWhere(Map<String, Object> where, Map<String, String> operators) {
		if(DPUtil.empty(where)) return "";
		if(DPUtil.empty(operators)) {
			return buildWhere(where.keySet().toArray(), null, false);
		} else {
			/* Map内部元素顺序随机 */
			String[] keys = DPUtil.collectionToStringArray(where.keySet());
			int length = keys.length;
			String[] values = new String[length];
			for(int i = 0; i < length; i++) {
				values[i] = operators.get(keys[i]);
			}
			return buildWhere(keys, values, false);
		}
	}
	
	public static Map<String, Object> buidNamedMap(String[] keys, Object[] values) {
		Map<String, Object> map = new HashMap<String, Object>(DPUtil.parseInt(keys.length / 0.75f));
		for(int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
		return map;
	}
	
	public static String buildSelect(String tableName,
			String fields, String where, String append, int page, int pageSize) {
		StringBuilder sb = new StringBuilder("select ").append(fields).append(" from ").append(tableName);
		if(!DPUtil.empty(where)) {
			sb.append(" where ").append(where);
		}
		if(null != append) sb.append(" ").append(append);
		if(pageSize > 0) {
			if(page < 1) page = 1;
			page = (page - 1) * pageSize;
			sb.append(" limit ").append(page).append(", ").append(pageSize);
		}
		return sb.toString();
	}
	
	public static String buildInsert(String tableName, Object[] keys, boolean bPlaceholder) {
		StringBuilder sbFileds = new StringBuilder();
		StringBuilder sbValues = new StringBuilder();
		for(int i = 0; i < keys.length; i++) {
			if(i > 0) {
				sbFileds.append(", ");
				sbValues.append(", ");
			}
			sbFileds.append(keys[i]);
			if(bPlaceholder) {
				sbValues.append("?");
			} else {
				sbValues.append(":").append(keys[i]);
			}
		}
		StringBuilder sb = new StringBuilder("insert into ").append(tableName).append(" (")
				.append(sbFileds.toString()).append(") values (").append(sbValues.toString()).append(")");
		return sb.toString();
	}
	
	public static String buildInsert(String tableName, Map<String, Object> values) {
		return buildInsert(tableName, values.keySet().toArray(), false);
	}
	
	public static String buildUpdate(String tableName, Object[] keys, String where, boolean bPlaceholder) {
		StringBuilder sb = new StringBuilder("update ").append(tableName).append(" set ");
		for(int i = 0; i < keys.length; i++) {
			if(i > 0) {
				sb.append(", ");
			}
			sb.append(keys[i]).append("=");
			if(bPlaceholder) {
				sb.append("?");
			} else {
				sb.append(":").append(keys[i]);
			}
		}
		if(!DPUtil.empty(where)) {
			sb.append(" where ").append(where);
		}
		return sb.toString();
	}
	
	public static String buildUpdate(String tableName, Map<String, Object> values, String where) {
		return buildUpdate(tableName, values.keySet().toArray(), where, false);
	}
	
	public static String buildDelete(String tableName, String where) {
		StringBuilder sb = new StringBuilder("delete from ").append(tableName);
		if(!DPUtil.empty(where)) {
			sb.append(" where ").append(where);
		}
		return sb.toString();
	}
	
	public static String buildTruncate(String tableName) {
		StringBuilder sb = new StringBuilder("truncate tableName ").append(tableName);
		return sb.toString();
	}
}
