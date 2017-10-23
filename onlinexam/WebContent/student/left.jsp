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
      <li><a href="<%=path %>/testServlet?method=show" target="mainFrame">查看以往考试信息</a></li>
      <li><a href="<%=path %>/papersServlet?method=show" target="mainFrame">进行考试</a></li>
       <li><a href="<%=path %>/loginOutServlet" target="mainFrame">退出系统</a></li>
   </ul>	
</DIV>
</BOTY>
</HTML>