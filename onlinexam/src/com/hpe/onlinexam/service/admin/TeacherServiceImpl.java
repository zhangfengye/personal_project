package com.hpe.onlinexam.service.admin;

import java.util.List;

import com.hpe.onlinexam.dao.admin.TeacherDaoImpl;
import com.hpe.onlinexam.po.Teacher;

/**
 * 教师操作 业务类
 * @author Administrator
 *
 */
public class TeacherServiceImpl implements ITeacherService {
	// 教师持久化操作类，全局变量
	TeacherDaoImpl teacherDao = new TeacherDaoImpl(); 
	
	@Override
	public void saveTeacher(Teacher t) {
		teacherDao.save(t);
	}

	@Override
	public void updateTeacher(Teacher t, int oldId) {
		teacherDao.update(t, oldId);
	}

	@Override
	public void delTeacher(int id) {
		teacherDao.delete(id);
	}

	@Override
	public Teacher findTeacherById(int id) {
		return teacherDao.getTeacherById(id);
	}

	@Override
	public List<Teacher> findAllTeacher() {
		return teacherDao.findAll();
	}

}
