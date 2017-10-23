package com.hpe.onlinexam.dao.admin;

import java.util.List;

import com.hpe.onlinexam.po.Course;
import com.hpe.onlinexam.util.DBUtil;

public class CourseDaoImpl implements ICourseDao {
	
	DBUtil dbutil = new DBUtil();
	
	@Override
	public void save(Course c) {
		try {
			dbutil.execute(" insert into course(name) "
					+ " values(?) ",
					new Object[]{c.getName()});
			System.out.println("课程保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Course c) {
		try {
			dbutil.execute(" update course set name=? where id=? ", 
						new Object[]{c.getName(),c.getId()});
			System.out.println("课程修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
try {
	dbutil.execute("delete from course where id=?",new Object[]{id});
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

	/**
	 * 根据id查询课程
	 * @param id int类型
	 * @return Course
	 */
	@Override
	public Course getCourseById(int id) {
		Course c = null;
		try {
			c = (Course)dbutil.getObject(Course.class, 
					" select * from course where id=? ",
					new Object[]{id});
			System.out.println("查询课程，课程的名字="+c.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	/**
	 * 查询所有的课程信息
	 * @param null
	 * @return list
	 */
	@Override
	public List findAll() {
		List list = null;
		try {
			list = dbutil.getQueryList(Course.class, 
					" select * from course order by id ",
					new Object[]{});
			System.out.println("查询课程信息"+list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	

}
