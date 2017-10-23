<%@page import="com.hpe.onlinexam.po.Teacher"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />

<title>维护教师页面</title>
</head>
<body>
	<h3 class="subTitle">修改教师信息</h3>
	<% Teacher teacher =  (Teacher)request.getAttribute("teacher"); %>
	<br/>
	<form action="<%=path %>/teacherServlet" method="post">
		<input type="hidden" name="method" value="update" />
		<input type="hidden" name="oldId" value="<%=teacher.getId()%>"/>
		id编号:<input type="text" name="id" value="<%=teacher.getId()%>"/><br/>
		name教师姓名:<input type="text" name="name" value="<%=teacher.getName()%>"/><br/>
		pwd密码:<input type="text" name="pwd" value="<%=teacher.getPwd()%>"/><br/>
		deptName方向:<select name="deptName">
						<option <%if(teacher.getDeptName().equals("开发")){%>
								selected="selected"
								<%} %> value="开发">开发</option>
						<option <%if(teacher.getDeptName().equals("测试")){%>
								selected="selected"
								<%} %> value="测试">测试</option>
					</select><br/>
		<input type="submit" value="提交" />
	</form>
	
	
	
	
	
	
	
	
</body>
</html>