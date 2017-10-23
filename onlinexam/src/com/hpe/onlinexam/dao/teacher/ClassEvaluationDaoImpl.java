package com.hpe.onlinexam.dao.teacher;

import java.util.List;

import com.hpe.onlinexam.util.DBUtil;
import com.hpe.onlinexam.vo.PapersView;
import com.hpe.onlinexam.vo.TCView;

public class ClassEvaluationDaoImpl implements ClassEvaluationDao {
	DBUtil dbutil = new DBUtil();
	@Override
	public List<PapersView> searchEvaluation(PapersView pv) {
		// TODO Auto-generated method stub
		
		String sql1=""
				+ "SELECT papersview.testId,"
				+ "papersview.testName,"
				+ "papersview.endDate,"
				+ "papersview.scores,"
				+ "papersview.courseName,"
				+ "papersview.deptName,"
				+ "papersview.className,"
				+ "avg(score) avgScore"
				+ " FROM"
				+ " (SELECT"
				+ " t.id testId,"
				+ "t.`name` testName,"
				+ "t.endDate,"
				+ "p.score,"
				+ "t.scores,"
				+ "c.`name` courseName,"
				+ "s.deptName,"
				+ "cls.`name` className"
				+ " FROM"
				+ " papers p,"
				+ "course c,"
				+ "test t,"
				+ "student s,"
				+ "stuclass cls"
				+ " WHERE "
				+ "p.testId = t.id "
				+ "AND p.courseId = c.id "
				+ "AND p.studentId = s.id "
				+ "AND cls.id = s.classId) AS papersview";
		String sql3=" GROUP BY className,testName ORDER BY className;";
		String sql=sql1;
		if(pv.getCourseName()!=null&&!pv.getCourseName().trim().equals("")){
			 sql=sql+" where papersview.courseName like '%"+pv.getCourseName()+"%'";
		}
		if(sql.equals(sql1)){
			if(pv.getClassName()!=null&&!pv.getClassName().trim().equals("")){
				 sql=sql+" where papersview.className like '%"+pv.getClassName()+"%'";
			}
		}else{
			if(pv.getClassName()!=null&&!pv.getClassName().trim().equals("")){
				 sql=sql+" and papersview.className like '%"+pv.getClassName()+"%'";
			}
		}
		sql=sql+sql3;
		List list = null;
		try {
			list = dbutil.getQueryList(PapersView.class, sql, new Object[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
