<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath(); %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<HEAD>
<TITLE>left</TITLE>
<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css"/>
<style>
#xxxx{}
.class1{}	
</style>	
</HEAD>
<BOTY>
<!-- 导航菜单 -->
<DIV id="nav_container" >
   <ul>
      <li><a href="<%=path %>/stuClassServlet?method=show" target="mainFrame">班级管理</a></li>
      <li><a href="<%=path %>/teacherServlet?method=show" target="mainFrame">教师管理</a></li>
      <li><a href="<%=path %>/courseServlet?method=show" target="mainFrame">课程管理</a></li>
      <li><a href="<%=path %>/TCViewServlet?method=show" target="mainFrame">教师课程关系管理</a></li>
      <li><a href="<%=path %>/studentServlet?method=show" target="mainFrame">学生管理</a></li>
      <li><a href="<%=path %>/loginOutServlet" target="mainFrame">退出系统</a></li>
      
   </ul>	
</DIV>
</BOTY>
</HTML>