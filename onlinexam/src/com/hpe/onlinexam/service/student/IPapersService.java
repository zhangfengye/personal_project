package com.hpe.onlinexam.service.student;

import java.util.List;
import java.util.Map;

import com.hpe.onlinexam.po.Papers;

public interface IPapersService {
	void insertPapers(Papers p);
	Papers getPagersById(int id);
	public List<Map<String,Object>> getPaperByStudentId(int studentId,int testId);
}
