package com.hpe.onlinexam.service.teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hpe.onlinexam.dao.teacher.QuestionDaoImpl;
import com.hpe.onlinexam.po.Question;
import com.hpe.onlinexam.util.Page;

public class QuestionServiceImpl implements IQuestionService{
	QuestionDaoImpl queDao = new QuestionDaoImpl();
	@Override
	public void insert(Question q) {
		queDao.insert(q);
	}

	@Override
	public void delete(int id) {
		
	}

	@Override
	public void update(Question q) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Question> findByIds(String[] ids) {
		return queDao.findByIds(ids);
	}

	@Override
	public Question findQuestionById(int id) {
		return queDao.findById(id);
	}
	// 根据制定题目个数，随机获取题目。
	@Override
	public List<Question> findQuestion(int courseId,int num){
		// 通过courseid获取 符合条件的科目。
		List list = queDao.findByCourseId(courseId);
		
		// 随机，重新洗牌。通过集合类里的方法。suffer
		Collections.shuffle(list);
		
		// 洗牌之后的数据集合中，获取num个数的题目。
		if(list.size() < num){// 题库数量不足。
			return null;
		}
		
		List resList = new ArrayList();// 最后返回的集合
		for (int i = 0; i < num; i++) {
			resList.add(list.get(i));
		}
		
		return resList;
	}
	
	@Override
	public Page findPage(Page page) {		
		return queDao.findPage(page);
	}
	
	
	
	
	
	
	
}
