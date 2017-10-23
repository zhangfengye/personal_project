package com.hpe.onlinexam.service.teacher;

import java.util.List;

import com.hpe.onlinexam.po.Question;
import com.hpe.onlinexam.util.Page;

public interface IQuestionService {
	void insert(Question q);
	void delete(int id);
	void update(Question q);
	
	List<Question> findByIds(String[] ids);
	Question findQuestionById(int id);
	
	// 根据制定题目个数，随机获取题目。
	List<Question> findQuestion(int courseId,int num);
	
	// 分页查询
	Page findPage(Page page);
}
