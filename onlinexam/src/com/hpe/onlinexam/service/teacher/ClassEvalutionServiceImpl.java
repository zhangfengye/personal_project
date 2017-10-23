package com.hpe.onlinexam.service.teacher;

import java.util.List;

import com.hpe.onlinexam.dao.teacher.ClassEvaluationDao;
import com.hpe.onlinexam.dao.teacher.ClassEvaluationDaoImpl;
import com.hpe.onlinexam.vo.PapersView;

public class ClassEvalutionServiceImpl implements IClassEvalutionService {

	@Override
	public List<PapersView> findClassEvalution(PapersView pv) {
		// TODO Auto-generated method stub
		ClassEvaluationDao ced=new ClassEvaluationDaoImpl();
		List<PapersView> list = ced.searchEvaluation(pv);
		return list;
	}

}
