package com.hpe.onlinexam.dao.student;

import java.util.List;
import java.util.Map;

import com.hpe.onlinexam.po.Papers;
import com.hpe.onlinexam.util.DBUtil;

public class PapersDaoImpl implements IPapersDao{
	DBUtil dbutil = new DBUtil();
	
	@Override
	public void insert(Papers p) {
		
	}

	@Override
	public Papers findById(int id) {
		return null;
	}
	public List<Map<String,Object>> getPaperByStudentId(int studentId,int testId){
		String sql = " select * from papers p where p.studentId = ? and  p.testId = ?  order by createDate ";
		List<Map<String,Object>> list = null;
		try {
			list = dbutil.getQueryList(sql, new Object[]{studentId,testId});
		} catch (Exception e) {
			e.printStackTrace();

		}
		return list;
	}
}
