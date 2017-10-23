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
	<h3 class="subTitle">新增教师信息</h3>
	<br/>
	<form action="<%=path %>/teacherServlet" method="post">
		<input type="hidden" name="method" value="update" />
		id编号:<input type="text" name="id" value=""/><br/>
		name教师姓名:<input type="text" name="name" value=""/><br/>
		pwd密码:<input type="text" name="pwd" value=""/><br/>
		deptName方向:<select name="deptName">
						<option value="开发">开发</option>
						<option value="测试">测试</option>
					</select><br/>
		<input type="submit" value="提交" />
	</form>
	
	
	
	
	
	
	
	
</body>
</html>