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
<title>题库信息</title>
</head>
<body>
	<h3 class="subTitle">
		查看题库信息 <a href="<%=path%>/questionServlet?method=showUpdate">新增试题</a>
	</h3>
	<br>
	<table class="table3">
		<tr>
			<td>编号</td>
			<td>a</td>
			<td>b</td>
			<td>c</td>
			<td>d</td>
			<td>e</td>
			<td>操作</td>
		</tr>
	<c:forEach items="${page.date }" var="question">
		<tr>
			<td>${question.id }</td> 
			<td>a</td>
			<td>b</td>
			<td>c</td>
			<td>d</td>
			<td>e</td>
			<td>
<a href="<%=path%>/questionServlet?method=showUpdate&id=${question.id}&curpage=${page.curPage }">修改试题</a></td>
		</tr>
	</c:forEach>
		
	</table>
	<p><a href="questionServlet?method=show&curpage=1">首页</a> 
	   <c:if test="${page.curPage > 1}">
	   <a href="questionServlet?method=show&curpage=${page.curPage - 1 }">上一页</a>  
	   </c:if>
	   <c:if test="${page.curPage <= 1}">
	          上一页
	   </c:if>    
	   <c:if test="${page.curPage < page.totalPage}">
	   <a href="questionServlet?method=show&curpage=${page.curPage + 1 }">下一页</a>
	   </c:if>
	   <c:if test="${page.curPage >= page.totalPage}">
	           下一页
	   </c:if>
	   <a href="questionServlet?method=show&curpage=${page.totalPage }">末页</a>
	        第${page.curPage }页- 总共${page.totalPage }页 - 共${page.rows }条记录
    </p>
	
	
	
	
	
	
	
	
</body>
</html>