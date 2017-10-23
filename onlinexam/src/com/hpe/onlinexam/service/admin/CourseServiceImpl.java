package com.hpe.onlinexam.service.admin;

import java.util.List;

import com.hpe.onlinexam.dao.admin.CourseDaoImpl;
import com.hpe.onlinexam.dao.admin.CourseDaoOracleImpl;
import com.hpe.onlinexam.dao.admin.ICourseDao;
import com.hpe.onlinexam.dao.admin.IStuClassDao;
import com.hpe.onlinexam.dao.admin.StuClassDao;
import com.hpe.onlinexam.po.Course;

public class CourseServiceImpl implements ICourseService {
	
	// 注入 课程持久化类
	ICourseDao courseDao = new CourseDaoImpl();
	// 注入 班级持久化类
	IStuClassDao stuclassDao = new StuClassDao();
	
	/**
	 * 保存课程信息
	 */
	@Override
	public void saveCourse(Course c) {
		courseDao.save(c);
		System.out.println("业务层，课程保存成功。");
	}

	@Override
	public void updateCourse(Course c) {
		courseDao.update(c);
		System.out.println("业务类，课程修改成功！");
	}

	@Override
	public void delCourse(int id) {
		// TODO Auto-generated method stub
		courseDao.delete(id);
	}

	@Override
	public Course getCourseById(int id) {
		Course c = courseDao.getCourseById(id);
		return c;
	}

	@Override
	public List<Course> findAllCourse() {
		List list = courseDao.findAll();
		System.out.println(" 业务类，查询所有的课程信息！ ");
		return list;
	}

}
