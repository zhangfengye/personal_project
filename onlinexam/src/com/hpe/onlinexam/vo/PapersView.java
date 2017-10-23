package com.hpe.onlinexam.vo;

import java.util.Date;

public class PapersView {
  private int testId;
  private String testName;
  private Date endDate;
  private double scores;
  private String courseName;
  private String deptName;
  private String className;
  private double avgScore;
public int getTestId() {
	return testId;
}
public void setTestId(int testId) {
	this.testId = testId;
}
public String getTestName() {
	return testName;
}
public void setTestName(String testName) {
	this.testName = testName;
}
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
public double getScores() {
	return scores;
}
public void setScores(double scores) {
	this.scores = scores;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getDeptName() {
	return deptName;
}
public void setDeptName(String deptName) {
	this.deptName = deptName;
}
public String getClassName() {
	return className;
}
public void setClassName(String className) {
	this.className = className;
}
public double getAvgScore() {
	return avgScore;
}
public void setAvgScore(double avgScore) {
	this.avgScore = avgScore;
}
}
