package com.iisquare.jees.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisquare.jees.framework.model.ServiceBase;
import com.iisquare.jees.oa.dao.TestDao;
import com.iisquare.jees.oa.domain.Test;

@Service
public class TestService extends ServiceBase {
	
	@Autowired
	public TestDao testDao;
	
	public TestService() {}
	
	public int insert(Test test) {
		return testDao.insert(test);
	}
	
	public int update(Test test) {
		return testDao.update(test);
	}
	
	public int deleteByIds(Object... ids) {
		return testDao.deleteByIds(ids);
	}
	
	public Test getById(Integer id) {
		return testDao.getById(id);
	}
	
	public int getCount(Map<String, Object> where, Map<String, String> operators, String append) {
		return testDao.getCount(where, operators, append);
	}
	
	public List<Test> getPage(Map<String, Object> where,
			Map<String, String> operators, String append, int page, int pageSize) {
		return testDao.getPage(where, operators, append, page, pageSize);
	}
}
