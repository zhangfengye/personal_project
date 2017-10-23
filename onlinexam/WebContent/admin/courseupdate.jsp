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

<title>维护课程页面</title>
</head>
<body>
	<h3 class="subTitle">课程信息维护</h3>
	<br/>
	<form action="<%=path %>/courseServlet" method="post">
		<input type="hidden" name="method" value="update" />
		<input type="hidden" name="id" value="${course.id }" />
		课程名:<input type="text" name="name" value="${course.name }"/><br/>
		<input type="submit" value="提交" />
	</form>
	
	
	
	
	
	
	
	
</body>
</html>