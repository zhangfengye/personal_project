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
			var url ="${pageContext.request.contextPath}/studentServlet?method=del&id="+id;
			// alert(url);
			window.location.href=url;
		    // 删除的时候，需要把模糊查询的参数传递到后台的delete方法。
		}
	}
</script>
</head>
<body>
	<h3 class="subTitle">
		学生管理<a href="${pageContext.request.contextPath }/studentServlet?method=showUpdate">新增学生信息</a>
	</h3>
	<br>
	<table class="table3">
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>密码</td>
			<td>学校信息</td>
			<td>方向</td>
			<td>性别</td>
			<td>生日</td>
			<td>所属班级</td>
			<td>操作</td>
		</tr>
	<!-- el表达式获取 req，session，application中的标识的值 -->
	
	<c:forEach items="${studentList }" var="s">
		<tr>
			<td>${s.id }</td>
			<td>${s.name }</td>
			<td>${s.pwd }</td>
			<td>${s.school }</td>
			<td>${s.deptName }</td>
			<td>${s.sex }</td>
			<td>${s.born }</td>
			
			
			
			<td>
				<c:forEach items="${stuList }" var="cls">
			<h4><c:if test="${s.classId==cls.id }">${cls.name }</c:if></h4>
				</c:forEach>
			</td>
			<td>
<a href="${pageContext.request.contextPath}/studentServlet?method=showUpdate&id=${s.id }">修改</a>
			<a href="javaScript:del(${s.id })">删除</a></td>
		</tr>
	</c:forEach>
		
	</table>
</body>
</html>