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
<title>查看近期考试</title>
</head>
<body>
	<h3 class="subTitle">
		未到考试时间，请等待！
	</h3>
	<form name="form1" action="<%=path%>/papersServlet?method=showUpdate&id=${testId}" method="post"></form>
	<div id="show"></div>
<script type="text/javascript">
	var day = ${timeMap.d };  // 天
	var hou = ${timeMap.h };// 小时
	var min = ${timeMap.m };// 分钟
	var se = ${timeMap.s }; // 秒
	//alert(day + " " + hou + " " + min + " " + se);
	window.setTimeout(callback, 1000); // 1000毫秒=1秒
	function callback(){
		var show = document.getElementById("show");
		show.innerHTML = "考试在 " + day + "天，" + hou + "小时" + min + "分钟" + se + "秒"
					+"之后进行！";
		// 判断，什么条件下执行跳出循环。 计数器=0。
		if(min>=1 && se==0){// 如果分钟大于1并且，秒为0
			se = 59; // 秒重新开始计数
			min = min - 1; // 分钟减一
		}
		if(hou>=1 && min == 0){
			min = 59;
			hou = hou - 1;
		}
		if(day>=1 && hou == 0){
			hou = 23;
			day = day -1;
			se = 59;
		}
		se --;
		if(day <= 0 && hou <=0 && min<=0 && se <= 0){
			form1.submit();
			return;
		}
		window.setTimeout(callback, 1000);
	} 
</script>
</body>




</html>