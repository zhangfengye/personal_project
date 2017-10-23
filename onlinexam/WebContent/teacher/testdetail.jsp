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

<title>试卷预览页面</title>
</head>
<body>
	<h3 class="subTitle">发布试卷</h3>
	<br/>
	<form action="<%=path %>/testServlet?method=update" method="post">	
		<p>${name}</p>
		<p>课程Id：${courseId }</p>
		<p>考试班级Id集合： ${classIds } 考试时长${testTime }分钟 分值${scores }分</p>
		<p>一、单选题（共${num }题，每题${scores / num }分）</p>
	<c:forEach items="${questionList }" var="q" varStatus="i">
		<p>${i.index + 1}、${q.queTitle }</p>
		<p><input type="radio" name="id_choice" value="A"/>A.${q.choiceA }      
		   <input type="radio" name="id_choice" value="B"/>B.${q.choiceB } </p>
		<p><input type="radio" name="id_choice" value="C"/>C.${q.choiceC }      
		   <input type="radio" name="id_choice" value="D"/>D.${q.choiceD } </p>	
	  	   <input type="hidden" name="id" value="${q.id }"/>
	</c:forEach>
	
		<input type="submit" value="提交保存" />
		<input type="hidden" name="name" value="${name }"/>
		<input type="hidden" name="courseId" value="${ courseId}"/>
		<input type="hidden" name="endDate" value="${endDate }"/>
		<input type="hidden" name="classIds" value="${classIds }"/>
		<input type="hidden" name="testTime" value="${testTime }"/>
		<input type="hidden" name="scores" value="${scores }"/>		
	</form>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>