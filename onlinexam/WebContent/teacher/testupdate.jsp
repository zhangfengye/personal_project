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
<title>发布试卷页面</title>
</head>
<body>
	<h3 class="subTitle">试卷发布</h3>
	<br/>
	<form action="<%=path %>/testServlet?method=showUpdate" method="post">
		<table class="table3">
			<tr>
				<td>试卷名：</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>考试课程：</td>
				<td><select name="courseId">
					<c:forEach items="${courseList }" var="c">
						<option value="${c.id }">${c.name }</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>考试时间：</td>
				<td><input type="text" name="endDate" id="endDate" /></td>
			</tr>
			<tr>
				<td>考试班级：</td>
				<td><c:forEach items="${stuclaList }" var="s">
					<input type="checkbox" name="classIds" value="${s.id }"/>
					${s.name }&nbsp;&nbsp;
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>考试时长：</td>
				<td><input type="text" name="testTime"/></td>
			</tr>
			<tr>
				<td>单选题:</td>
				<td><input type="text" name="num"/>道小题</td>
			</tr>
			<tr>
				<td>分值：</td>
				<td><input type="text" name="scores"/></td>
			</tr>	
			<tr>
				<td colspan="2" align="center"><input type="submit" value="发布" /></td>
			</tr>					
		</table>
	</form>
	
</body>
<script>
	//alert("提示！"); Calendar.setup();
	Calendar.setup({
		inputField : "endDate",
		ifFormat : "%Y-%m-%d %H:%M:%S",
		showsTime : true,
		timeFormat : "24"
	});	
</script>



</html>