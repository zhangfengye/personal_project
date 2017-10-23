package com.hpe.onlinexam.service.admin;

import java.util.List;

import com.hpe.onlinexam.dao.admin.IStuClassDao;
import com.hpe.onlinexam.dao.admin.StuClassDao;
import com.hpe.onlinexam.po.StuClass;

public class StuClassServiceImpl implements IStuClassService {
	// null  需要指明具体的实现类（通过new，或者spring配置）
	IStuClassDao stuClassDao = new StuClassDao();//用接口声明，具体实现使用
	/**
	 * 保存班级信息
	 */
	@Override
	public void saveStuClass(StuClass stu) {
		stuClassDao.save(stu);
	}

	@Override
	public StuClass findStuClassById(int id) {
		return stuClassDao.getStuClassById(id);
	}

	@Override
	public List findAll() {
		return stuClassDao.findAll();
	}

	@Override
	public void updateStuClass(StuClass stu) {
		stuClassDao.update(stu);
	}

	@Override
	public void deleteStuClass(int id) {
		// TODO Auto-generated method stub
		stuClassDao.delete(id);
		
	}
}
