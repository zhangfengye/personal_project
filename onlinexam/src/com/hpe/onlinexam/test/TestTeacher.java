package com.hpe.onlinexam.test;

import java.util.List;

import com.hpe.onlinexam.dao.admin.TeacherDaoImpl;
import com.hpe.onlinexam.po.Teacher;

import junit.framework.TestCase;

public class TestTeacher extends TestCase{		
	
	TeacherDaoImpl dao = new TeacherDaoImpl();

	public void testAddT(){
		Teacher t = new Teacher();
		t.setId(20140101);
		t.setName("小红");
		t.setDeptName("开发");
		t.setPwd("123");
		dao.save(t);
	}
	public void testUpdate(){
		Teacher t = new Teacher();
		t.setId(20140102);
		t.setName("小红");
		t.setDeptName("开发");
		t.setPwd("123");
		dao.update(t,20140101);
	}
	public void testGetTeacherByid(){
		Teacher t = dao.getTeacherById(8);
		System.out.println(t.getName());
	}
	
	public void testAll(){
		List list = dao.findAll();
		System.out.println(list.size());
	}

	
}
