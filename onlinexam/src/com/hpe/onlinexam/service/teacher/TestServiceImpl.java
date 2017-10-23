package com.hpe.onlinexam.service.teacher;

import java.util.List;
import java.util.Map;

import com.hpe.onlinexam.dao.teacher.TestDaoImpl;
import com.hpe.onlinexam.po.Test;

public class TestServiceImpl implements ITestService{
	
	TestDaoImpl testDao = new TestDaoImpl();
	
	@Override
	public void insertTest(Test t) {
		testDao.insert(t);
	}

	@Override
	public void updateTest(Test t) {}

	@Override
	public Test getTestById(int id) {
		return testDao.getTestById(id);
	}

	@Override
	public List<Test> searchTest(Test test) {
		return testDao.searchTest(test);
	}
	
	@Override
	public List<Map<String, Object>> findListByTeaId(int id) {
		return testDao.findListByTeaId(id);
	}
	@Override
	public List<Map<String, Object>> findListByStuId(int id) {
		return testDao.findListByStuId(id);
	}
	
}
