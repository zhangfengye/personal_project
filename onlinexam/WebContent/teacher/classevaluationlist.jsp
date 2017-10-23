<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/style.css" type="text/css" rel="stylesheet"></link>
</head>
<body>
<h3 class="subTitle">班级评估
<table >
		<form action="${pageContext.request.contextPath }/classEvaluationServlet" method="post">
			<input type="hidden" name="method" value="show"/>
			<tr>
				<td>课程名称<input type="text" name="courseName" value="${papersview.courseName }"/></td>
				<td>班级名称<input type="text" name="className" value="${papersview.className }"/></td>
				<td><input type="submit" value="搜索"/></td>
			</tr>
		</form>	
		</table>
		</h3>
		<br><br><br>
	<table class="table3">
		<tr>
			<td>试题编号</td>
			<td>考试名称</td>
			<td>结束时间</td>
			<td>满分</td>
			<td>课程名称</td>
			<td>方向</td>
			<td>班级名称</td>
			<td>平均分值</td>
		</tr>
	<c:forEach items="${paperslist }" var="papersview">
		<tr>
			<td>${papersview.testId }</td>
			<td>${papersview.testName }</td>
			<td>${papersview.endDate }</td>
			<td>${papersview.scores }</td>
			<td>${papersview.courseName }</td>
			<td>${papersview.deptName }</td>
			<td>${papersview.className }</td>
			<td>${papersview.avgScore }</td>
		</tr>
	</c:forEach>
		
	</table>
</body>
</html>