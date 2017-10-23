package com.hpe.onlinexam.dao.admin;

import java.util.List;
import com.hpe.onlinexam.po.StuClass;

/**
 *  班级dao层接口类
 * @author Administrator
 *
 */
public interface IStuClassDao {
	void save(StuClass stuclass);
	void update(StuClass stuclass);
	void delete(int id);
	StuClass getStuClassById(int id);
	List findAll();
	String findClassNamesByIds(String ids);
	
		
}
