package com.hpe.onlinexam.dao.admin;

import java.util.List;

import com.hpe.onlinexam.po.Student;

public interface IStudentDao {
	void save(Student s);
	void update(Student s,int oldId);
	void delete(int id );
	
	Student getObjectById(int id);
	List findAll();
}
