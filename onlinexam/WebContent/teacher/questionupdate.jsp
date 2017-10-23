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
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/js/calendar-blue.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/calendar.js"></script>
<title>题库管理</title>
</head>
<body>
	<h3 class="subTitle">试题维护页面</h3>
	<br/>
	<form action="<%=path %>/questionServlet?method=update" method="post">
		<table class="table3">
		<input type="hidden" name="id" value="${question.id }"/>
		<input type="hidden" name="curpage" value="${curpage }"/>
			<tr>
				<td>题干：</td>
				<td><input type="text" name="queTitle" value="${question.queTitle }"/></td>
			</tr>
			<tr>
				<td>所属课程：</td>
				<td><select name="courseId">
					<c:forEach items="${courseList }" var="c">
						<option value="${c.id }">${c.name }</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>选项A：</td>
				<td><input type="text" name="choiceA" value="${question.choiceA }"/></td>
			</tr>
			<tr>
				<td>选项B：</td>
				<td><input type="text" name="choiceB" value="${question.choiceB }"/></td>
			</tr>
			<tr>
				<td>选项C：</td>
				<td><input type="text" name="choiceC" value="${question.choiceC }"/></td>
			</tr>
			<tr>
				<td>选项D：</td>
				<td><input type="text" name="choiceD" value="${question.choiceD }"/></td>
			</tr>
			<tr>
				<td>答案：</td>
				<td><input type="text" name="ans" value="${question.ans }"/></td>
			</tr>	
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交保存" /></td>
			</tr>					
		</table>
	</form>
	
</body> 
</html>