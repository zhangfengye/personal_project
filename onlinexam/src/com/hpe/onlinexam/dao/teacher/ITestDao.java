package com.hpe.onlinexam.dao.teacher;

import java.util.List;
import java.util.Map;

import com.hpe.onlinexam.po.Test;

public interface ITestDao {
	void insert(Test test);
	void delete(int id);
	
	Test getTestById(int id);
	
	List<Test> searchTest(Test test);
	
	// 通过教师查询 已发布的试卷
	List<Map<String,Object>> findListByTeaId(int id);
	
	// 根据学生id查询 将要考试的试卷。
	List<Map<String,Object>> findListByStuId(int id);
	
	
	
	
	
}
