package com.hpe.onlinexam.dao.admin;

import com.hpe.onlinexam.util.DBUtil;

public class BaseDao {
	private DBUtil dbutil = new DBUtil();
	/**
	 * 通过传递sql，查询指定数据。适用于返回一条数据。
	 * @param sql
	 * @return
	 */
	public Object getObject(String sql){
		Object o = null;
		try {
			o = dbutil.getObject(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
}
