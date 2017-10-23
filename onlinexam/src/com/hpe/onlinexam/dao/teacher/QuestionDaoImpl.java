package com.hpe.onlinexam.dao.teacher;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.hpe.onlinexam.po.Question;
import com.hpe.onlinexam.util.DBUtil;
import com.hpe.onlinexam.util.Page;
import com.hpe.onlinexam.util.ToolUtil;

public class QuestionDaoImpl implements IQuestionDao{
	//注入
	static DBUtil dbutil = new DBUtil();
	@Override
	public void insert(Question q) {
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Question q) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Question> findByIds(String[] ids) {
		String idss = ToolUtil.arraytoString(ids);// 2，3，4
		String sql = " select * from questions where id in ("+idss+") ";
		List list = null;
		try {
			list = dbutil.getQueryList(Question.class,sql, new Object[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} 
	
	@Override
	public Question findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Question> findByCourseId(int id){
		String sql = " select * from questions where courseId =  ? ";
		List list = null;
		try {
			list = dbutil.getQueryList(Question.class,sql
					, new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Page findPage(Page page) {
		page = dbutil.getQueryPage(Question.class,
				" select * from questions order by id ",
				new Object[]{}, page);		
		return page;
	}
	
	public static void main(String[] args) {
		QuestionDaoImpl dao = new QuestionDaoImpl();
		Page page = new Page();
		page.setCurPage(2);// 查询第一页数据
		page = dao.findPage(page);
		System.out.println("当前页：" + page.getCurPage());
		System.out.println("num："+page.getPageNumber());
		System.out.println("总记录数："+page.getRows());
		System.out.println("总页数："+page.getTotalPage());
		System.out.println("集合的长度："+page.getDate().size());
		List date = page.getDate();
		for (Iterator iterator = date.iterator(); iterator.hasNext();) {
			Question q = (Question) iterator.next();
			System.out.println(q.getId());
		}
	}
	
	
	
	
	
}
