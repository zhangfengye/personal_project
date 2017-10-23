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

<title>维护学生页面</title>
</head>
<body>
	<h3 class="subTitle">学生维护</h3>
	<br/>
	<form action="<%=path %>/studentServlet" method="post">
		<input type="hidden" name="method" value="update" />
		<input type="hidden" name="oldId" value="${student.id }" />
		学号:<input type="text" name="id" value="${student.id }" /><br/>
		姓名:<input type="text" name="name" value="${student.name }"/><br/>
		密码:<input type="text" name="pwd" value="${student.pwd }" /><br/>
		学校:<input type="text" name="school" value="${student.school }" /><br/>
		方向:<select name="deptName">
				<option  <c:if test="${student.deptName =='开发' }">selected="selected"</c:if> value="开发">开发</option>
				<option  <c:if test="${student.deptName =='测试' }">selected="selected"</c:if> value="测试">测试</option>
			</select><br/>
		性别:女<input type="radio" <c:if test="${student.sex =='女' }">checked="checked"</c:if> name="sex" value="女" />
			男<input type="radio" <c:if test="${student.sex =='男' }">checked="checked"</c:if> name="sex" value="男" /><br/>
		生日:<input type="text" name="born" value="${student.born }" /><br/>
		班级:			
		<select name="classId">
			<option value="" selected="selected">请选择班级</option>
			<c:forEach items="${stuList }" var="cls">
				<option <c:if test="${student.classId == cls.id }">selected="selected"</c:if>
				 value="${cls.id }">${cls.name }</option>
			</c:forEach>
		</select>
			
		<br/>
		
		<input type="submit" value="提交" />
	</form>
	
	
</body>
</html>