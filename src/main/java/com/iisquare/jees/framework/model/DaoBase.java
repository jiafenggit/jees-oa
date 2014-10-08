package com.iisquare.jees.framework.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.iisquare.jees.framework.Configuration;
import com.iisquare.jees.framework.util.DPUtil;
import com.iisquare.jees.framework.util.ReflectUtil;
import com.iisquare.jees.framework.util.SqlUtil;

@Repository
@Scope("prototype")
public abstract class DaoBase<T> extends JdbcTemplate {
	private Class<T> entityClass;
	@Autowired
	protected Configuration configuration;
	protected String primaryKey = "id";
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public DaoBase(Class<T> clazz) {
		this.entityClass = clazz;
	}
	
	@SuppressWarnings("hiding")
	@Override
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper) throws DataAccessException {
		List<T> results = query(sql, rowMapper);
		return FrameworkDataAccessUtils.requiredSingleResult(results);
	}
	
	@SuppressWarnings("hiding")
	@Override
	public <T> T queryForObject(String sql, Object[] args, int[] argTypes, RowMapper<T> rowMapper)
			throws DataAccessException {

		List<T> results = query(sql, args, argTypes, new RowMapperResultSetExtractor<T>(rowMapper, 1));
		return FrameworkDataAccessUtils.requiredSingleResult(results);
	}
	
	@SuppressWarnings("hiding")
	@Override
	public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
		List<T> results = query(sql, args, new RowMapperResultSetExtractor<T>(rowMapper, 1));
		return FrameworkDataAccessUtils.requiredSingleResult(results);
	}
	
	@SuppressWarnings("hiding")
	@Override
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException {
		List<T> results = query(sql, args, new RowMapperResultSetExtractor<T>(rowMapper, 1));
		return FrameworkDataAccessUtils.requiredSingleResult(results);
	}
	
	/**
	 * 获取对应的数据库表名称
	 */
	public String tableName() {
		return DPUtil.stringConcat(configuration.getTablePrefix(), DPUtil.addUnderscores(entityClass.getSimpleName()));
	}
	
	/**
	 * 获取NamedParameterJdbcTemplate操作对象
	 */
	public NamedParameterJdbcTemplate npJdbcTemplate() {
		if(null == namedParameterJdbcTemplate) {
			namedParameterJdbcTemplate = new FrameworkNamedParameterJdbcTemplate(this);
		}
		return namedParameterJdbcTemplate;
	}
	
	/**
	 * 添加记录，返回自增长ID
	 */
	public int insert(Map<String, Object> values) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int result = npJdbcTemplate().update(SqlUtil.buildInsert(tableName(), values)
				, new MapSqlParameterSource(values), keyHolder);
		Number number = keyHolder.getKey();
		return result > 0 && null != number ? number.intValue() : result;
	}
	
	/**
	 * 添加记录，返回自增长ID
	 */
	public int insert(String[] fields, Object[] values) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int result = update(SqlUtil.buildInsert(tableName(), fields, true) , values, keyHolder);
		Number number = keyHolder.getKey();
		return result > 0 && null != number ? number.intValue() : result;
	}
	
	/**
	 * 添加记录，返回自增长ID
	 */
	public int insert(T object) {
		Map<String, Object> values = ReflectUtil.convertEntityToMap(object, true, null);
		if(null == values) return 0;
		return insert(values);
	}
	
	/**
	 * 更新记录，返回影响行数
	 */
	public int update(T object) {
		if(null == object) return 0;
		Map<String, Object> values = ReflectUtil.convertEntityToMap(object, true, null);
		return updateByIds(values, values.get(primaryKey));
	}
	
	/**
	 * 更新记录，返回影响行数
	 */
	public int update(Map<String, Object> values, String where) {
		String sql = SqlUtil.buildUpdate(tableName(), values, where);
		return npJdbcTemplate().update(sql, values);
	}
	
	/**
	 * 更新记录，返回影响行数
	 */
	public int update(String[] fields, Object[] values, String where) {
		String sql = SqlUtil.buildUpdate(tableName(), fields, where, true);
		return update(sql, values);
	}
	
	/**
	 * 更新记录，返回影响行数
	 */
	public int update(Map<String, Object> values,
			String[] whereFields, Object[] whereValues, String[] operators) {
		String[] fieldArray = DPUtil.collectionToStringArray(values.keySet());
		Object[] valueArray = DPUtil.collectionToArray(values.values());
		return update(fieldArray, valueArray, whereFields, whereValues, operators);
	}
	
	/**
	 * 更新记录，返回影响行数
	 */
	public int update(String[] fields, Object[] values,
			String[] whereFields, Object[] whereValues, String[] operators) {
		values = DPUtil.arrayMerge(values, whereValues);
		return update(fields, values, SqlUtil.buildWhere(whereFields, operators, true));
	}
	
	/**
	 * 根据ID更新记录，返回影响行数
	 */
	public int updateByIds(Map<String, Object> values, Object... ids) {
		String[] fieldArray = DPUtil.collectionToStringArray(values.keySet());
		Object[] valueArray = DPUtil.collectionToArray(values.values());
		return updateByIds(fieldArray, valueArray, ids);
	}
	
	/**
	 * 根据ID更新记录，返回影响行数
	 */
	public int updateByIds(String[] fields, Object[] values, Object... ids) {
		if(DPUtil.empty(ids)) return 0;
		String where = SqlUtil.buildWhereIn(primaryKey, ids);
		String sql = SqlUtil.buildUpdate(tableName(), fields, where, true);
		return update(sql, DPUtil.arrayMerge(values, ids));
	}
	
	/**
	 * 删除记录，返回影响行数
	 */
	public int delete(T object) {
		if(null == object) return 0;
		Map<String, Object> values = ReflectUtil.convertEntityToMap(object, true, null);
		return deleteByIds(values.get(primaryKey));
	}
	
	/**
	 * 删除记录，返回影响行数
	 */
	public int delete(String where) {
		String sql = SqlUtil.buildDelete(tableName(), where);
		return npJdbcTemplate().update(sql, new HashMap<String, Object>());
	}
	
	/**
	 * 删除记录，返回影响行数
	 */
	public int delete(Map<String, Object> where, Map<String, String> operators) {
		String sql = SqlUtil.buildDelete(tableName(), SqlUtil.buildWhere(where, operators));
		return npJdbcTemplate().update(sql, where);
	}
	
	/**
	 * 删除记录，返回影响行数
	 */
	public int delete(String[] whereFields, Object[] whereValues, String[] operators) {
		String sql = SqlUtil.buildDelete(tableName(), SqlUtil.buildWhere(whereFields, operators, true));
		return update(sql, whereValues);
	}
	
	/**
	 * 根据ID删除记录，返回影响行数
	 */
	public int deleteByIds(Object... ids) {
		if(DPUtil.empty(ids)) return 0;
		String where = SqlUtil.buildWhereIn(primaryKey, ids);
		String sql = SqlUtil.buildDelete(tableName(), where);
		return update(sql, ids);
	}
	
	/**
	 * 根据ID获取Entity对象
	 */
	public T getById(Object id) {
		List<T> list = getByIds(id);
		if(list.size() > 0) return list.get(0);
		return null;
	}
	
	/**
	 * 根据ID获取Map对像
	 */
	public Map<String, Object> getById(String columns, Object id) {
		List<Map<String, Object>> list = getByIds(columns, id);
		if(list.size() > 0) return list.get(0);
		return null;
	}
	
	/**
	 * 根据ID获取Entity对象列表
	 */
	public List<T> getByIds(Object... ids) {
		if(DPUtil.empty(ids)) return new ArrayList<T>(0);
		String where = SqlUtil.buildWhereIn(primaryKey, ids);
		int pageSize = (1 == ids.length) ? 1 : 0;
		String sql = SqlUtil.buildSelect(tableName(), "*", where, null, 1, pageSize);
		return query(sql, ids, new BeanPropertyRowMapper<T>(entityClass));
	}
	
	/**
	 * 根据ID获取Map对象列表
	 */
	public List<Map<String, Object>> getByIds(String columns, Object... ids) {
		if(DPUtil.empty(ids)) return new ArrayList<Map<String, Object>>(0);
		String where = SqlUtil.buildWhereIn(primaryKey, ids);
		int pageSize = (1 == ids.length) ? 1 : 0;
		String sql = SqlUtil.buildSelect(tableName(), columns, where, null, 1, pageSize);
		return queryForList(sql, ids);
	}
	
	/**
	 * 根据单个字段获取Entity对象
	 */
	public T getByField(String field, Object value, String operator, String append) {
		Map<String, Object> where = new HashMap<String, Object>(2);
		where.put(field, value);
		Map<String, String> operators = null;
		if(!DPUtil.empty(operator)) {
			operators = new HashMap<String, String>(2);
			operators.put(field, operator);
		}
		return getByFields(where, operators, append);
	}
	
	/**
	 * 根据单个字段获取Map对象
	 */
	public Map<String, Object> getByField(String columns,
			String field, Object value, String operator, String append) {
		Map<String, Object> where = new HashMap<String, Object>(2);
		where.put(field, value);
		Map<String, String> operators = null;
		if(!DPUtil.empty(operator)) {
			operators = new HashMap<String, String>(2);
			operators.put(field, operator);
		}
		return getByFields(columns, where, operators, append);
	}
	
	/**
	 * 根据多个字段获取Entity对象
	 */
	public T getByFields(Map<String, Object> where, Map<String, String> operators, String append) {
		 List<T> list = getPage(where, operators, append, 1, 1);
		 if(list.size() < 1) return null;
		return list.get(0);
	}
	
	/**
	 * 根据多个字段获取Entity对象
	 */
	public T getByFields(String[] whereFields, Object[] whereValues, String[] operators, String append) {
		 List<T> list = getPage(whereFields, whereValues, operators, append, 1, 1);
		 if(list.size() < 1) return null;
		return list.get(0);
	}
	
	/**
	 * 根据多个字段获取Map对象
	 */
	public Map<String, Object> getByFields(String columns,
			Map<String, Object> where, Map<String, String> operators, String append) {
		 List<Map<String, Object>> list = getPage(columns, where, operators, append, 1, 1);
		 if(list.size() < 1) return null;
		return list.get(0);
	}
	
	/**
	 * 根据多个字段获取Map对象
	 */
	public Map<String, Object> getByFields(String columns,
			String[] whereFields, Object[] whereValues, String[] operators, String append) {
		 List<Map<String, Object>> list = getPage(columns, whereFields, whereValues, operators, append, 1, 1);
		 if(list.size() < 1) return null;
		return list.get(0);
	}
	
	/**
	 * 获取分页Entity对象
	 */
	public List<T> getPage(Map<String, Object> where,
			Map<String, String> operators, String append, int page, int pageSize) {
		String sql = SqlUtil.buildSelect(tableName(), "*", SqlUtil.buildWhere(where, operators), append, page, pageSize);
		return npJdbcTemplate().query(sql, where, new BeanPropertyRowMapper<T>(entityClass));
	}
	
	/**
	 * 获取分页Entity对象
	 */
	public List<T> getPage(String[] whereFields,
			Object[] whereValues, String[] operators, String append, int page, int pageSize) {
		String sql = SqlUtil.buildSelect(tableName(), "*",
				SqlUtil.buildWhere(whereFields, operators, true), append, page, pageSize);
		return query(sql, whereValues, new BeanPropertyRowMapper<T>(entityClass));
	}
	
	/**
	 * 获取分页Map对象
	 */
	public List<Map<String, Object>> getPage(String columns, Map<String, Object> where,
			Map<String, String> operators, String append, int page, int pageSize) {
		String sql = SqlUtil.buildSelect(tableName(), columns,
				SqlUtil.buildWhere(where, operators), append, page, pageSize);
		return npJdbcTemplate().queryForList(sql, where);
	}
	
	/**
	 * 获取分页Map对象
	 */
	public List<Map<String, Object>> getPage(String columns, String[] whereFields,
			Object[] whereValues, String[] operators, String append, int page, int pageSize) {
		String sql = SqlUtil.buildSelect(tableName(), columns,
				SqlUtil.buildWhere(whereFields, operators, true), append, page, pageSize);
		return queryForList(sql, whereValues);
	}
	
	/**
	 * 获取Entity对象列表
	 */
	public List<T> getAll(Map<String, Object> where, Map<String, String> operators, String append) {
		return getPage(where, operators, append, 0, 0);
	}
	
	/**
	 * 获取Entity对象列表
	 */
	public List<T> getAll(String[] whereFields, Object[] whereValues, String[] operators, String append) {
		return getPage(whereFields, whereValues, operators, append, 0, 0);
	}
	
	/**
	 * 获取Map对象列表
	 */
	public List<Map<String, Object>> getAll(String columns,
			Map<String, Object> where, Map<String, String> operators, String append) {
		return getPage(columns, where, operators, append, 0, 0);
	}
	
	/**
	 * 获取Map对象列表
	 */
	public List<Map<String, Object>> getAll(String columns,
			String[] whereFields, Object[] whereValues, String[] operators, String append) {
		return getPage(columns, whereFields, whereValues, operators, append, 0, 0);
	}
	
	/**
	 * 获取查询记录结果条数
	 */
	public int getCount(Map<String, Object> where, Map<String, String> operators, String append) {
		String count = "COUNT(*)";
		String sql = SqlUtil.buildSelect(tableName(), count, SqlUtil.buildWhere(where, operators), append, 1, 1);
		return getCount(sql, where, false);
	}
	
	/**
	 * 获取查询记录结果条数
	 */
	public int getCount(String sql, Map<String, Object> paramMap, boolean bConvert) {
		if(bConvert) sql = SqlUtil.convertForCount(sql);
		Number number = npJdbcTemplate().queryForObject(sql, paramMap, Integer.class);
		return (number != null ? number.intValue() : 0);
	}
	
	/**
	 * 获取查询记录结果条数
	 */
	public int getCount(String[] whereFields, Object[] whereValues, String[] operators, String append) {
		String count = "COUNT(*)";
		String sql = SqlUtil.buildSelect(tableName(), count, SqlUtil.buildWhere(whereFields, operators, true), append, 1, 1);
		return getCount(sql, whereValues, false);
	}
	
	/**
	 * 获取查询记录结果条数
	 */
	public int getCount(String sql, Object[] paramValues, boolean bConvert) {
		if(bConvert) sql = SqlUtil.convertForCount(sql);
		Number number = queryForObject(sql, paramValues, Integer.class);
		return (number != null ? number.intValue() : 0);
	}
}
