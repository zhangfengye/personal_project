package com.hpe.onlinexam.dao.admin;

import java.util.List;

import com.hpe.onlinexam.po.Course;

public interface ICourseDao {
	void save(Course c);
	void update(Course c);
	void delete(int id);
	
	Course getCourseById(int id);
	List findAll();
}
