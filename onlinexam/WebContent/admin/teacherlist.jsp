<%@page import="com.hpe.onlinexam.po.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% String path = request.getContextPath(); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet"></link>
<script type="text/javascript">
	function del(id){
		var flag = window.confirm("是否删除！");
		if(flag){
			var url ="${pageContext.request.contextPath}/teacherServlet?method=del&id="+id;
			// alert(url);
			window.location.href=url;
		    // 删除的时候，需要把模糊查询的参数传递到后台的delete方法。
		}
	}
</script>
</head>
<body>
	<h3 class="subTitle">
		教师信息管理<a href="<%=path %>/admin/teacheradd.jsp">新增教师信息</a>
	</h3>
	<br>
	<% List list = (List)request.getAttribute("teacherList"); %>
	<table class="table3">
		<tr>
		<td>教师编号</td>
		<td>教师姓名</td>
		<td>所属方向</td>
		<td>操作</td></tr>
		<%for(int i =0;i < list.size();i ++){ 
			Teacher t = (Teacher)list.get(i);
		%>
		<tr><td><%=t.getId() %></td>
			<td><%=t.getName() %></td>
			<td><%=t.getDeptName() %></td>
			<td><a href="<%=path%>/teacherServlet?method=showUpdate&id=<%=t.getId()%>">修改</a> <a href="javaScript:del(<%=t.getId() %>)">删除</a></td></tr>
		<%} %>
	</table>
	
	
	
	
	
	
	
</body>
</html>