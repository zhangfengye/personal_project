package com.hpe.onlinexam.service.teacher;
import java.util.List;
import java.util.Map;

import com.hpe.onlinexam.po.Test;

public interface ITestService {
	void insertTest(Test t);
	void updateTest(Test t);
	
	Test getTestById(int id);
	
	List<Test> searchTest(Test test);
	// 通过教师查询 已发布的试卷
	List<Map<String,Object>> findListByTeaId(int id);
	// 通过学生查询 已发布的试卷
	List<Map<String,Object>> findListByStuId(int id);
}
