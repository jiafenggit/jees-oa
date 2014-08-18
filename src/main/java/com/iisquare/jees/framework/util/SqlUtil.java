package com.iisquare.jees.framework.util;

import java.util.Arrays;
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
	
	public static String[] fillOperators(int length, String operator) {
		String str[] = new String[length];
		Arrays.fill(str, operator);
		return str;
	}
	
	public static String buildWhere(Object[] keys, Object[] operators) {
		if(DPUtil.empty(keys)) return "";
		if(DPUtil.empty(operators)) {
			operators = fillOperators(keys.length, "=");
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < keys.length; i++) {
			if(i > 0) {
				sb.append(" and ");
			}
			sb.append(keys[i]).append(operators[i]).append(":").append(keys[i]);
		}
		return sb.toString();
	}
	
	public static String buildWhere(Map<String, Object> where, Map<String, String> operators) {
		if(DPUtil.empty(where)) return "";
		if(DPUtil.empty(operators)) {
			return buildWhere(where.keySet().toArray(), null);
		} else {
			/* Map内部元素顺序随机 */
			String[] keys = DPUtil.collectionToStringArray(where.keySet());
			int length = keys.length;
			String[] values = new String[length];
			for(int i = 0; i < length; i++) {
				values[i] = operators.get(keys[i]);
			}
			return buildWhere(keys, values);
		}
	}
	
	public static Map<String, Object> buidNamedMap(String[] keys, Object[] values) {
		Map<String, Object> map = new HashMap<String, Object>(DPUtil.parseInt(keys.length / 0.75f));
		for(int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
		return map;
	}
	
	public static String buildSelect(String tableName, String fields, String where, String append, int page, int pageSize) {
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
	
	public static String buildInsert(String tableName, Object[] keys) {
		StringBuilder sbFileds = new StringBuilder();
		StringBuilder sbValues = new StringBuilder();
		for(int i = 0; i < keys.length; i++) {
			if(i > 0) {
				sbFileds.append(", ");
				sbValues.append(", ");
			}
			sbFileds.append(keys[i]);
			sbValues.append(":").append(keys[i]);
		}
		StringBuilder sb = new StringBuilder("insert into ").append(tableName).append(" (")
				.append(sbFileds.toString()).append(") values (").append(sbValues.toString()).append(")");
		return sb.toString();
	}
	
	public static String buildInsert(String tableName, Map<String, Object> values) {
		return buildInsert(tableName, values.keySet().toArray());
	}
	
	public static String buildUpdate(String tableName, Object[] keys, String where) {
		StringBuilder sb = new StringBuilder("update ").append(tableName).append(" set ");
		for(int i = 0; i < keys.length; i++) {
			if(i > 0) {
				sb.append(", ");
			}
			sb.append(keys[i]).append("=").append(":").append(keys[i]);
		}
		if(!DPUtil.empty(where)) {
			sb.append(" where ").append(where);
		}
		return sb.toString();
	}
	
	public static String buildUpdate(String tableName, Map<String, Object> values, String where) {
		return buildUpdate(tableName, values.keySet().toArray(), where);
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
