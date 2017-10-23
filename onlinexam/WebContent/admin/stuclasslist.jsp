<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.hpe.onlinexam.po.StuClass"%>
<%@page import="java.util.List"%>
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
<script type="text/javascript">
	function del(id){
		var flag = window.confirm("是否删除！");
		if(flag){
			var url ="${pageContext.request.contextPath}/stuClassServlet?method=del&id="+id;
			// alert(url);
			window.location.href=url;
		    // 删除的时候，需要把模糊查询的参数传递到后台的delete方法。
		}
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<h3 class="subTitle">
		班级管理&nbsp;&nbsp;&nbsp;&nbsp;<a href="admin/stuclasssave.jsp">新增班级</a>
	</h3>
	<br/>
	<!-- 列表： 班级id  班级名字  班级方向  操作 -->
	<table class="table3">
		<tr><td>班级id</td><td>班级名字</td><td>班级方向</td>
			<td>操作</td></tr>
		<%
		List<StuClass> list = 
			(List<StuClass>)request.getAttribute("stuClassList");
		for(StuClass stu : list){
		%>
			<tr><td><%=stu.getId() %></td>
			    <td><%=stu.getName()%></td>
			    <td><%=stu.getDeptName() %></td>
			    <td><a href="stuClassServlet?method=showUpdate&id=<%=stu.getId() %>">修改</a> <a href="javaScript:del(<%=stu.getId() %>)">删除</a></td></tr>
		<%
		}
		%>
		
	</table>
	
	
	
	
	
	
	
	
	
	
</body>
</html>