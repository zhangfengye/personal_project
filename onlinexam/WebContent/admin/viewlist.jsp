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
			var url ="${pageContext.request.contextPath}/TCViewServlet?method=del&id="+id
			+"&teaName=${tcview.teaName }&courseName=${tcview.courseName }&className=${tcview.className }&deptName=${tcview.deptName}";
			// alert(url);
			window.location.href=url;
		    // 删除的时候，需要把模糊查询的参数传递到后台的delete方法。
		}
	}
</script>
</head>
<body>
	<h3 class="subTitle">
		教务管理<a href="${pageContext.request.contextPath }/TCViewServlet?method=showUpdate">新增教务信息</a>
		<table >
		<form action="${pageContext.request.contextPath }/TCViewServlet" method="post">
			<input type="hidden" name="method" value="show"/>
			<tr>
				<td>教师姓名<input type="text" name="teaName" value="${tcview.teaName }"/></td>
				<td>课程<input type="text" name="courseName" value="${tcview.courseName }"/></td>
				<td>班级<input type="text" name="className" value="${tcview.className }"/></td>
				<td>班级方向
				<select name="deptName">
					<option value="">所有方向</option>
					<option <c:if test="${tcview.deptName=='开发' }">selected="selected"</c:if> value="开发">开发</option>
					<option <c:if test="${tcview.deptName=='测试' }">selected="selected"</c:if> value="测试">测试</option>
			    </select></td>
				<td><input type="submit" value="搜索"/></td>
			</tr>
		</form>	
		</table>
	</h3>
	<br><br><br>
	<table class="table3">
		<tr>
			<td>编号</td>
			<td>教师姓名</td>
			<td>课程</td>
			<td>班级</td>
			<td>班级所属方向</td>
			<td>操作</td>
		</tr>
	<c:forEach items="${tcvList }" var="tcview">
		<tr>
			<td>${tcview.id }</td>
			<td>${tcview.teaName }</td>
			<td>${tcview.courseName }</td>
			<td>${tcview.className }</td>
			<td>${tcview.deptName }</td>
			<td>
<a href="${pageContext.request.contextPath}/TCViewServlet?method=showUpdate&id=${tcview.id }">修改</a>
<a href="javaScript:del(${tcview.id })">删除</a>
			</td>
		</tr>
	</c:forEach>
		
	</table>
	
</body>
</html>