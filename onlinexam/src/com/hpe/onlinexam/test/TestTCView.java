package com.hpe.onlinexam.test;

import java.util.List;

import com.hpe.onlinexam.dao.admin.TCDaoImpl;
import com.hpe.onlinexam.vo.TCView;

public class TestTCView {
	public static void main(String[] args) {
		TCDaoImpl dao = new TCDaoImpl();
		TCView v = new TCView();
		v.setTeaName("帅");
		v.setClassName("     ");
		v.setCourseName("java");
		v.setDeptName(null);
		
		List list = dao.findAllByTCView(v);
		System.out.println(list.size());
	}
	
	
	
	
	
}
