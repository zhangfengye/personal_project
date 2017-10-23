package com.hpe.onlinexam.dao.admin;

import java.util.List;

import com.hpe.onlinexam.vo.TCView;

/**
 * 关系表的持久化接口
 * @author Administrator
 *
 */
public interface ITCDao {
	void save(TCView v);
	void update(TCView v);
	void delete(TCView v);
	
	List<TCView> findAll();
	
	// 模糊查询
	List<TCView> findAllByTCView(TCView v);

	List<TCView> findAllByTCView(
			String teaName,
			String courseName,
			String className,
			String deptName);
	
	TCView findTCViewById(int id);
}
