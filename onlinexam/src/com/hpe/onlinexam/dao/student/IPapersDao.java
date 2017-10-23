package com.hpe.onlinexam.dao.student;

import java.util.List;
import java.util.Map;

import com.hpe.onlinexam.po.Papers;

public interface IPapersDao {
	void insert(Papers p);
	Papers findById(int id);
	// 根据班级分组查询
	
	// 根据学生信息，查询。
	public List<Map<String,Object>> getPaperByStudentId(int studentId,int testId);

}
