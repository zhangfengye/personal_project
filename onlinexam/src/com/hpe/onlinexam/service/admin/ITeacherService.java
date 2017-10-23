package com.hpe.onlinexam.service.admin;

import java.util.List;

import com.hpe.onlinexam.po.Teacher;

public interface ITeacherService {
	void saveTeacher(Teacher t);  // 有一些业务的味道了
	void updateTeacher(Teacher t,int oldId);
	void delTeacher(int id);
	
	Teacher findTeacherById(int id);
	List<Teacher> findAllTeacher();
	
}
