package com.hpe.onlinexam.service.admin;

import java.util.List;

import com.hpe.onlinexam.po.Student;

public interface IStudentService {
	void saveStudent(Student t);  // 有一些业务的味道了
	void updateStudent(Student t,int oldId);
	void delStudent(int id);
	
	Student findStudentById(int id);
	List<Student> findAllStudent();
}
