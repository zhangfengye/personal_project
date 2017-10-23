package com.hpe.onlinexam.po;

/**
 * 班级的实体类  对应 stuclass表
 * @author Administrator
 *
 */
public class StuClass {
	private int id; 
	private String name; // 班级
	private String deptName; // 方向
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
