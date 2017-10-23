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

<title>维护教务信息</title>
</head>
<body>
	<h3 class="subTitle">教务信息维护</h3>
	<br/>
	<form action="<%=path %>/TCViewServlet" method="post">
		<input type="hidden" name="method" value="update" />
		<input type="hidden" name="id" value="${tcview.id }" />
		教师:<select name="teaId">
				<option value="">请选择教师</option>
				<c:forEach items="${teaList }" var="t">
					<option 
		<c:if test="${t.id == tcview.teaId }">selected="selected"</c:if>
					 value="${t.id }" >${t.name }</option>
				</c:forEach>
			</select><br/>
		课程:<select name="courseId">
				<option value="">请选择课程</option>
				<c:forEach items="${courseList }" var="c">
					<option value="${c.id }"
		<c:if test="${c.id == tcview.courseId }">selected="selected"</c:if>
					>${c.name }</option>
				</c:forEach>
			</select><br/>
		班级:<select name="classId">
				<option value="">请选择班级</option>
				<c:forEach items="${classList }" var="cl">
					<option value="${cl.id }"
		<c:if test="${cl.id == tcview.classId }">selected="selected"</c:if>
					>${cl.name }</option>
				</c:forEach>
			</select><br/>
		<input type="submit" value="提交" />
	</form>
	
	
	
	
	
	
	
	
</body>
</html>