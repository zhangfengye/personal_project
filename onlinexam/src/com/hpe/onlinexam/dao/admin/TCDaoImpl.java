package com.hpe.onlinexam.dao.admin;

import java.util.List;

import com.hpe.onlinexam.util.DBUtil;
import com.hpe.onlinexam.vo.TCView;

public class TCDaoImpl implements ITCDao {
	
	// 注入
	DBUtil dbutil = new DBUtil();
	
	@Override
	public void save(TCView v) {
		try {
			dbutil.execute(" insert into teachercourse(teaId,courseId,classId) "
					+ " values(?,?,?) ", new Object[]{v.getTeaId(),v.getCourseId(),v.getClassId()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(TCView v) {
		try {
			dbutil.execute(" update teachercourse set teaId=?,courseId=?,classId=? "
					+ " where id = ? ", 
					new Object[]{v.getTeaId(),v.getCourseId(),v.getClassId(),v.getId()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(TCView v) {
		try {
			dbutil.execute(" delete from teachercourse  where id = ? ", 
					new Object[]{v.getId()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TCView> findAll() {
		String sql = " select tc.id,tc.teaId,tc.courseId,tc.classId,      " +
				" 		t.`name` as teaName,                              " +
				" 		c.`name` as courseName,                           " +
				" 		sc.`name` className ,sc.deptName deptName         " +
				" from teachercourse tc , teacher t ,course c,stuclass sc " +
				" where tc.teaId = t.id and tc.courseId = c.id            " +
				"       and tc.classId = sc.id                            " ;
		List list = null;
		try {
			list = dbutil.getQueryList(TCView.class, sql, new Object[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TCView> findAllByTCView(TCView v) {
		String sql = " select tc.id,tc.teaId,tc.courseId,tc.classId,      " +
				" 		t.`name` as teaName,                              " +
				" 		c.`name` as courseName,                           " +
				" 		sc.`name` className ,sc.deptName deptName         " +
				" from teachercourse tc , teacher t ,course c,stuclass sc " +
				" where tc.teaId = t.id and tc.courseId = c.id            " +
				"       and tc.classId = sc.id                            " ;
		String teaName = v.getTeaName();
		String courseName = v.getCourseName();
		String className = v.getClassName();
		String deptName = v.getDeptName();   /// null , "" ," " ,"  a "="a"
		
		if(teaName != null && !teaName.trim().equals("")){
			sql += " and t.name like '%"+ teaName +"%'  ";
		}
		if(courseName != null && !courseName.trim().equals("")){
			sql += " and c.name like '%"+ courseName +"%'  ";
		}
		if(className != null && !className.trim().equals("")){
			sql += " and sc.name like '%"+ className +"%'  ";
		}
		if(deptName != null && !deptName.trim().equals("")){
			sql += " and sc.deptName = '"+ deptName + "' ";
		}
		sql += " order by sc.name ";
		List list = null;
		try {
			list = dbutil.getQueryList(TCView.class, sql, new Object[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<TCView> findAllByTCView(String teaName, 
										String courseName, 
										String className, 
										String deptName) { // 方法重载overload
		TCView v = new TCView();
		v.setClassName(className);
		v.setCourseName(courseName);
		v.setTeaName(teaName);
		v.setDeptName(deptName);
		return this.findAllByTCView(v);		
	}

	@Override
	public TCView findTCViewById(int id) {
		String sql = " select tc.id,tc.teaId,tc.courseId,tc.classId,      " +
				" 		t.`name` as teaName,                              " +
				" 		c.`name` as courseName,                           " +
				" 		sc.`name` className ,sc.deptName deptName         " +
				" from teachercourse tc , teacher t ,course c,stuclass sc " +
				" where tc.teaId = t.id and tc.courseId = c.id            " +
				"       and tc.classId = sc.id                            " +
				"       and tc.id = ?   " ;
		TCView tcview = null;
		try {
			tcview = (TCView)dbutil.getObject(TCView.class, sql, new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tcview;
	}

}
