package com.hpe.onlinexam.dao.admin;

import java.util.List;

import com.hpe.onlinexam.po.Teacher;

public interface ITeacherDao {
	void save(Teacher teacher);
	void update(Teacher teacher,int oldId);
	void delete(int id);
	Teacher getTeacherById(int id);
	
	List findAll();
	
}
