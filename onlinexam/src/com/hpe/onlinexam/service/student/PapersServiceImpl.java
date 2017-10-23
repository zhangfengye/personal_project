package com.hpe.onlinexam.service.student;

import java.util.List;
import java.util.Map;

import com.hpe.onlinexam.dao.student.PapersDaoImpl;
import com.hpe.onlinexam.po.Papers;

public class PapersServiceImpl implements IPapersService {
	// 注入
	PapersDaoImpl papersDao = new PapersDaoImpl();
	
	@Override
	public void insertPapers(Papers p) {
		papersDao.insert(p);
	}

	@Override
	public Papers getPagersById(int id) {
		return papersDao.findById(id);
	}
	public List<Map<String,Object>> getPaperByStudentId(int studentId,int testId){
		return papersDao.getPaperByStudentId(studentId, testId);
	}

}
