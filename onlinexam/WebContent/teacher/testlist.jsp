<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/css/style.css" type="text/css" rel="stylesheet"></link>
<title>查看近期考试</title>
</head>
<body>
	<h3 class="subTitle">
		近期考试试卷 
	</h3>
	<br>
	<table class="table3">
		<tr>
			<td>编号</td>
			<td>试卷名</td>
			<td>截至时间</td>
			<td>考试时长</td>
			<td>总分值</td>
			<td>课程名称</td>
			<td>出题人姓名</td>
			<td>班级名称</td>
		</tr>
	<c:forEach items="${testList }" var="m">
		<tr>
			<td>${m['id'] }</td>
			<td>${m.testName }</td>
			<td>${m.endDate }</td>
			<td>${m.testTime }</td>
			<td>${m.scores }</td>
			<td>${m.courseName }</td>
			<td>${m.teacherName }</td>
			<td>${m.className }</td>
		</tr>
	</c:forEach>
		
	</table>
</body>
</html>