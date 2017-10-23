package com.hpe.onlinexam.dao.teacher;

import java.util.List;
import java.util.Map;

import com.hpe.onlinexam.po.Test;
import com.hpe.onlinexam.util.DBUtil;
import com.hpe.onlinexam.util.ToolUtil;

public class TestDaoImpl implements ITestDao {

	//注入
	DBUtil dbutil = new DBUtil();
	
	@Override
	public void insert(Test t) {
		String sql = " insert into test(name,courseId,endDate,questions,teacherId,classIds,testTime,scores) "
				+ "    value(?,?,?,?,?,?,?,?) ";
		try {
			dbutil.execute(sql, new Object[]{t.getName(),t.getCourseId(),t.getEndDate(),t.getQuestions(),
					t.getTeacherId(),t.getClassIds(),t.getTestTime(),t.getScores()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = " delete from test where id =? ";
		try {
			dbutil.execute(sql, new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Test getTestById(int id) {
		Test test = null;
		try {
			test = (Test)dbutil.getObject(Test.class, 
										" select * from test where id = ? ", 
										new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}

	@Override
	public List<Test> searchTest(Test test) {
		String sql = " select * from test where 1=1 ";
		if(true){
			// 模糊查询 条件判断
		}
		List list = null;
		try {
			list = dbutil.getQueryList(Test.class, sql, new Object[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> findListByTeaId(int id) {
		String sql = ""+
				" select t.id,t.`name` testName ,t.endDate, t.testTime, " +
				"        t.scores, c.`name` courseName, tea.`name` teacherName,      " +
				" 		 stucls.`name` className                                     " +
				" from test t,course c,teacher tea,stuClass stucls                   " +
				" where t.courseId = c.id and t.teacherId = tea.id                   " +
				" 			and FIND_IN_SET(stucls.id,t.classIds)                    " +
				" 			and t.teacherId = ?                                   " +
				" 			and  t.endDate > ?                                    " +
				"  order by stucls.name                                              ";
		List list = null;
		String currTime = ToolUtil.getCurrentTime();
		try {
			list = dbutil.getQueryList(sql, new Object[]{id,currTime});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> findListByStuId(int id) {
		String sql = "" +
				" select student.name stuName, t.id,t.`name` testName ,t.endDate,    " +
				" 				t.testTime,                                          " +
				"        t.scores, c.`name` courseName, tea.`name` teacherName,      " +
				" 			 stucls.id classId ,stucls.`name` className              " +
				" from test t,course c,teacher tea,stuClass stucls  ,student student " +
				" where t.courseId = c.id and t.teacherId = tea.id                   " +
				" 			and FIND_IN_SET(stucls.id,t.classIds)                    " +
				" 			and  t.endDate > ?                   " +
				" 		  and student.classId = stucls.id                            " +
				" 	    and student.id = ?                                      " +
				"  order by t.endDate                                              ";
		List list = null;
		String currTime = ToolUtil.getCurrentTime();
		try {
			list = dbutil.getQueryList(sql, new Object[]{currTime,id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	
	
	
}
