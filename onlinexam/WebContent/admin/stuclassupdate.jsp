<%@page import="com.hpe.onlinexam.po.StuClass"%>
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

<title>维护班级信息页面</title>
</head>
<body>
	<h3 class="subTitle">
		修改班级
	</h3>
<% StuClass stuclass = (StuClass)request.getAttribute("stuClass"); %>
<form action="stuClassServlet" method="post">
	<input type="hidden" name="method" value="update" />
	<input type="hidden" name="id" value="<%=stuclass.getId()%>"/><br/>
	<p>name:<input type="text" name="name" value="<%=stuclass.getName() %>" /></p>
	<p>deptName:<select name="deptName">
				<option<%if(stuclass.getDeptName().equals("开发")){
						out.print(" selected='selected'");} 
						%> value="开发">01开发方向</option>
				<option <%if(stuclass.getDeptName().equals("测试")){
						out.print(" selected='selected'");} 
						%>value="测试">02测试方向</option>
			</select></p>
	<p><input type="submit" value="提交"/></p>
</form>
</div>





</body>
</html>