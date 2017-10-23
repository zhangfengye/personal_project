package com.hpe.onlinexam.dao.teacher;

import java.util.List;

import com.hpe.onlinexam.vo.PapersView;

public interface ClassEvaluationDao {
    List<PapersView> searchEvaluation(PapersView pv);
    
}
