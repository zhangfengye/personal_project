package com.hpe.onlinexam.service.admin;

import java.util.List;

import com.hpe.onlinexam.po.Course;

public interface ICourseService {
	void saveCourse(Course c);
	void updateCourse(Course c);
	void delCourse(int id);
	
	Course getCourseById(int id);
	List<Course> findAllCourse();
	
}
