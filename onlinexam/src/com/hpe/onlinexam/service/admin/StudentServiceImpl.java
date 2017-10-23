package com.hpe.onlinexam.service.admin;

import java.util.List;

import com.hpe.onlinexam.dao.admin.StudentDaoImpl;
import com.hpe.onlinexam.dao.admin.TeacherDaoImpl;
import com.hpe.onlinexam.po.Student;

/**
 * 学生操作 业务类
 * @author Administrator
 *
 */
public class StudentServiceImpl implements IStudentService {
	// 学生持久化操作类，全局变量
	StudentDaoImpl studentDao = new StudentDaoImpl(); 
	
	@Override
	public void saveStudent(Student t) {
		studentDao.save(t);
	}

	@Override
	public void updateStudent(Student t, int oldId) {
		studentDao.update(t, oldId);
	}

	@Override
	public void delStudent(int id) {
		studentDao.delete(id);
	}

	@Override
	public Student findStudentById(int id) {
		return studentDao.getObjectById(id);
	}

	@Override
	public List<Student> findAllStudent() {
		return studentDao.findAll();
	}

}
