package com.hpe.onlinexam.service.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hpe.onlinexam.dao.admin.BaseDao;
import com.hpe.onlinexam.dao.admin.TCDaoImpl;
import com.hpe.onlinexam.vo.TCView;

public class TCViewServiceImpl implements ITCViewService{

	TCDaoImpl tcviewDao = new TCDaoImpl();
	// 注入
	BaseDao baseDao = new BaseDao();
	
	@Override
	public void saveTCView(TCView v) {
		// 加入一个后台的 验证，避免数据重复。
		String sql = " select count(*) as count "
					+ " from teachercourse tc "
					+ " where tc.teaId = " + v.getTeaId() 
					+ " and tc.courseId = " + v.getCourseId() 
					+ " and classId =  " + v.getClassId();
		Map<String,Object> o = (Map<String, Object>) baseDao.getObject(sql);
		//BigDecimal,或者long类型去接收。
		long count = (Long)o.get("count");
		if(count >= 1){
			// 不保存
		}else{
			tcviewDao.save(v);
		}
	}

	@Override
	public void updateTCView(TCView v) {
		tcviewDao.update(v);
	}

	@Override
	public void delTCView(int id) {
		TCView v = new TCView();
		v.setId(id);
		tcviewDao.delete(v);		
	}

	@Override
	public List<TCView> searchTCView(TCView v) {
		return tcviewDao.findAllByTCView(v);
	}

	@Override
	public TCView findById(int id) {
		return tcviewDao.findTCViewById(id);
	}

}
