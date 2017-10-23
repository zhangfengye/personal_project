package com.hpe.onlinexam.po;

/**
 * 课程实体类，对应课程表
 * @author Administrator
 *
 */
public class Course {
	private int id;
	private String name;
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
}
