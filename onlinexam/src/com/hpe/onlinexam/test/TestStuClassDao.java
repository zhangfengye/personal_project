package com.hpe.onlinexam.test;

import java.util.List;

import com.hpe.onlinexam.dao.admin.IStuClassDao;
import com.hpe.onlinexam.dao.admin.StuClassDao;
import com.hpe.onlinexam.po.StuClass;

import junit.framework.TestCase;

/**
 * 班级 dao层 单元测试
 * @author Administrator
 *
 */
public class TestStuClassDao extends TestCase{
	// 测试 dao层中，先测试getStuClassById()方法。
	// 需要这样的规范 
	// 1、方法没有返回值void 2、方法名testXxxxxx(),无参数。
	// 3、使用断言，并且使用junit运行。
	public void testXxxx(){// int a = 2; 
		int a = 2;
		assertEquals(1, a);
	}
	
	/**
	 * dao层getStuClassById方法的测试方法
	 */
	public void testGetStuClassById(){
		IStuClassDao stuClassDao = null;// 声明
		stuClassDao = new StuClassDao();// 实现
		
		StuClass stu = stuClassDao.getStuClassById(1);
		System.out.println("开发2班=" + stu.getName());
		//assertEquals("开发2班", stu.getName());
	}
	
	public void testFindAll(){
		IStuClassDao stuClassDao = null;// 声明
		stuClassDao = new StuClassDao();// 实现
		List list = stuClassDao.findAll();
		System.out.println("集合的长度："+ list.size());
	}
	public void testSave(){
		IStuClassDao stuClassDao = null;// 声明
		stuClassDao = new StuClassDao();// 实现
		StuClass stuclass = new StuClass();
		stuclass.setName("超级班");
		stuclass.setDeptName("宇宙");
		stuClassDao.save(stuclass);		
	}
	public void testUpdate(){
		IStuClassDao stuClassDao = null;// 声明
		stuClassDao = new StuClassDao();// 实现
		StuClass stuclass = stuClassDao.getStuClassById(7);
		stuclass.setName("超级宇宙班");
		stuClassDao.update(stuclass);
	}
	
	
	
	
	
	
	
}
