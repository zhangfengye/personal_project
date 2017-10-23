package com.hpe.onlinexam.dao.admin;

import java.util.List;

import com.hpe.onlinexam.po.Student;
import com.hpe.onlinexam.util.DBUtil;

public class StudentDaoImpl implements IStudentDao {

	private DBUtil dbutil = new DBUtil();
	
	@Override
	public void save(Student t) {
		try {
			System.out.println("StudentDaoImpl______save()");
			dbutil.execute(" insert into student(id,name,pwd,school,deptName,sex,born,classId) "
					+ " values(?,?,?,?,?,?,?,?) ",
					new Object[]{t.getId(),t.getName(),t.getPwd(),t.getSchool(),t.getDeptName(),t.getSex(),t.getBorn(),t.getClassId()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Student t,int oldId) {
		try {
			dbutil.execute(" update student set id=?,name=?,pwd=?,school=?,deptName=?,sex=?,born=?,classId=? "
					+ " where id = ? ",
					new Object[]{t.getId(),t.getName(),t.getPwd(),t.getSchool(),t.getDeptName(),t.getSex(),t.getBorn(),t.getClassId(),oldId});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("StudentDaoImpl______update()");
	}

	@Override
	public void delete(int id) {
		try {
			dbutil.execute("delete from student where id=?",new Object[]{id});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Student getObjectById(int id) {
		Student t = null;
		try {
			t = (Student)dbutil.getObject(Student.class, " select * from student where id = ?  ", new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public List findAll() {
		List list = null;
		try{
			list = dbutil.getQueryList(Student.class, " select * from student order by id ", new Object[]{});
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
