<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/style.css" type="text/css" rel="stylesheet"></link>
<script type="text/javascript">
	function del(id){
		var flag = window.confirm("是否删除！");
		if(flag){
			var url ="${pageContext.request.contextPath}/courseServlet?method=del&id="+id;
			// alert(url);
			window.location.href=url;
		    // 删除的时候，需要把模糊查询的参数传递到后台的delete方法。
		}
	}
</script>
</head>
<body>
	<h3 class="subTitle">
		课程信息管理<a href="${pageContext.request.contextPath }/admin/courseupdate.jsp">新增课程信息</a>
	</h3>
	<br>
	<table class="table3">
		<tr>
			<td>编号</td>
			<td>课程名</td>
			<td>操作</td>
		</tr>
	<!-- el表达式获取 req，session，application中的标识的值 -->
	
	<c:forEach items="${courseList }" var="course">
		<tr>
			<td>${course.id }</td>
			<td>${course.name }</td>
			<td>
<a href="${pageContext.request.contextPath}/courseServlet?method=showUpdate&id=${course.id }">修改</a>
			<a href="javaScript:del(${course.id })">删除</a></td>
		</tr>
	</c:forEach>
		
	</table>
	
	
	
	
<%-- 		<!-- 打印 1 ---- 10  c标签  -->
		<%if(1==1){out.print("hello");} %>
		<c:if test="${1==1 }">hello1</c:if>
		<%	List list = new ArrayList();
			list.add("a");
			list.add("b");
			list.add("c");
			list.add("d");
			request.setAttribute("list", list); %>
		<c:forEach items="${list }" var="i">
			====${i }
		</c:forEach> --%>	
	
	
	
	
	
	
	
</body>
</html>