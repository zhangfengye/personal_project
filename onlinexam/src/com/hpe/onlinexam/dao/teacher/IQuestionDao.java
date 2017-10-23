package com.hpe.onlinexam.dao.teacher;

import java.util.List;

import com.hpe.onlinexam.po.Question;
import com.hpe.onlinexam.util.Page;

public interface IQuestionDao {
	void insert(Question q);
	void delete(int id);
	void update(Question q);
	
	List<Question> findByIds(String[] ids);
	Question findById(int id);
	
	List<Question> findByCourseId(int id);
	// 分页查询
	Page findPage(Page page);
	
}
