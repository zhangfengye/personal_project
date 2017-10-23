package com.hpe.onlinexam.dao.admin;

import java.util.List;

import com.hpe.onlinexam.po.StuClass;
import com.hpe.onlinexam.util.DBUtil;

public class StuClassDao implements IStuClassDao {
	public DBUtil dbutil = new DBUtil(); 
	@Override
	public void save(StuClass stuclass) {
		String sql = " insert into stuclass(name,deptName) "
						+ " values(?,?) ";
		try {
			dbutil.execute(sql,new Object[]{stuclass.getName(),
										stuclass.getDeptName()});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(StuClass stuclass) {
		String sql = " update stuclass set name=?,deptName=?"
				+ "  where id = ? ";
		try{
			dbutil.execute(sql, new Object[]{stuclass.getName(),
					stuclass.getDeptName(),stuclass.getId()});
		}catch(Exception e){	
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql="delete from stuclass where id=?";
		try {
			dbutil.execute(sql, new Object[]{id});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public StuClass getStuClassById(int id) {
		String sql = " select id,name,deptName from stuclass where id = ? ";
		StuClass stu = null;
		try{
			stu = (StuClass)dbutil.getObject(StuClass.class, sql, new Object[]{id});
		}catch(Exception e){
			e.printStackTrace();
		}
		return stu;
	}

	@Override
	public List findAll() {
		String sql = " select id,name,deptName from stuclass ";
		List list = null;
		try{
			list = (List)dbutil.getQueryList(StuClass.class, sql, new Object[]{});
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String findClassNamesByIds(String ids) {
		return null;
	}

}
