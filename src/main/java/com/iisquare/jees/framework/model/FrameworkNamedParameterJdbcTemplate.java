package com.iisquare.jees.framework.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class FrameworkNamedParameterJdbcTemplate extends
		NamedParameterJdbcTemplate {

	public FrameworkNamedParameterJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}

	public FrameworkNamedParameterJdbcTemplate(
			JdbcOperations classicJdbcTemplate) {
		super(classicJdbcTemplate);
	}

	@Override
	public <T> T queryForObject(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper)
			throws DataAccessException {

		List<T> results = getJdbcOperations().query(getPreparedStatementCreator(sql, paramSource), rowMapper);
		return FrameworkDataAccessUtils.requiredSingleResult(results);
	}
}
