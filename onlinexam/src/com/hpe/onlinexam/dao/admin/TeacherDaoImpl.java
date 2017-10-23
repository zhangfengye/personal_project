package com.hpe.onlinexam.dao.admin;


import java.util.List;

import com.hpe.onlinexam.po.Teacher;
import com.hpe.onlinexam.util.DBUtil;
/**
 * 教师持久化类
 * @author Administrator
 *
 */
public class TeacherDaoImpl implements ITeacherDao {
	
	private DBUtil dbutil = new DBUtil();
	
	@Override
	public void save(Teacher teacher) {
		try {
			System.out.println("TeacherDaoImpl______save()");
			dbutil.execute(" insert into teacher(id,name,pwd,deptName) "
					+ " values(?,?,?,?) ",new Object[]{teacher.getId(),teacher.getName(),teacher.getPwd(),teacher.getDeptName()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Teacher t,int oldId) {
		try {
			dbutil.execute(" update teacher set id=?,name=?,pwd=?,deptName=? "
					+ " where id = ? ",
					new Object[]{t.getId(),t.getName(),t.getPwd(),t.getDeptName(),oldId});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("TeacherDaoImpl______update()");
	}

	@Override
	public void delete(int id) {
		try {
			dbutil.execute("delete from teacher where id=?",new Object[]{id});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Teacher getTeacherById(int id) {
		Teacher t = null;
		try {
			t = (Teacher)dbutil.getObject(Teacher.class, " select * from teacher where id = ?  ", new Object[]{id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("TeacherDaoImpl___getTeacherById()"+t);
		return t;
	}

	@Override
	public List findAll() {
		List list = null;
		try{
			list = dbutil.getQueryList(Teacher.class, " select * from teacher order by id ", new Object[]{});
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("TeacherDaoImpl___getTeacherById()"+list);
		return list;
	}


}
