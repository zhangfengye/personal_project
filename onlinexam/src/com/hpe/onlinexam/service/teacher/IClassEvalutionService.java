package com.hpe.onlinexam.service.teacher;

import java.util.List;

import com.hpe.onlinexam.vo.PapersView;

public interface IClassEvalutionService {
List<PapersView> findClassEvalution(PapersView pv);
}
