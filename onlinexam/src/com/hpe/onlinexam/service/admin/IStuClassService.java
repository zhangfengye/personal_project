package com.hpe.onlinexam.service.admin;

import java.util.List;

import com.hpe.onlinexam.po.StuClass;

/**
 * 班级业务接口类
 * @author Administrator
 *
 */
public interface IStuClassService {
	void saveStuClass(StuClass stu);
	StuClass findStuClassById(int id);
	List findAll();
	void updateStuClass(StuClass stu);
	void deleteStuClass(int id);
}
