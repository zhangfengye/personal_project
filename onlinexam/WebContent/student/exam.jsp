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

<title>学生考试页面</title>
</head>
<body>
	<h3 class="subTitle">正在考试</h3>
	<br/>
	<form name="form1" action="<%=path %>/papersServlet?method=update" method="post">	
		<p>${test.name}</p>
		<p>课程Id：${test.courseId }</p>
		<p>考试班级： 当前学生班级 考试时长${test.testTime }分钟 分值${test.scores }分</p>
		<p>一、单选题（共${queListSize }题，每题${test.scores / queListSize }分）</p>
	<c:forEach items="${queList }" var="q" varStatus="i">
		<p>${i.index + 1}、${q.queTitle }</p>
		<p><input type="radio" name="${q.id }_choice" value="A"/>A.${q.choiceA }      
		   <input type="radio" name="${q.id }_choice" value="B"/>B.${q.choiceB } </p>
		<p><input type="radio" name="${q.id }_choice" value="C"/>C.${q.choiceC }      
		   <input type="radio" name="${q.id }_choice" value="D"/>D.${q.choiceD } </p>	
	  	   <input type="hidden" name="id" value="${q.id }"/>
	</c:forEach>
	
		<input type="submit" value="提交保存" />
		<input type="hidden" name="name" value="${test.name }"/>
		<input type="hidden" name="testId" value="${test.id }"/>
		<input type="hidden" name="courseId" value="${test.courseId}"/>
		<input type="hidden" name="endDate" value="${test.endDate }"/>
		<input type="hidden" name="classIds" value="${test.classIds }"/>
		<input type="hidden" name="testTime" value="${test.testTime }"/>
		<input type="hidden" name="scores" value="${test.scores }"/>	
		<input type="hidden" name="countTime" id="countTime" value=""/>	<!-- 计数器，记录答卷时长 -->	
	</form>	
	<p><input type="text" value="" size="13" name="time" id="time"/></p>
</body>
<script type="text/javascript">
	var hou = ${timeMap.h};
	var min = ${timeMap.m} ;// 分钟
	var se = ${timeMap.s}; // 秒
	// 不能使用alert提示。
	//alert("考试剩余：" + hou + "小时 " + min + "分钟 " + se + "秒");
	window.setTimeout(callback, 1000); // 1000毫秒=1秒
	function callback(){
		// 显示 倒计时。在time控件中。
		var input = document.getElementById("time");
		input.value = "考试剩余:"+hou+":" + min + ":" + se + "";
		
		// 判断，什么条件下执行跳出循环。 计数器=0。
		if(min>=1 && se==0){// 如果分钟大于1并且，秒为0
			se = 59; // 秒重新开始计数
			min = min - 1; // 分钟减一
		}
		if(hou>=1 && min == 0){
			min = 59;
			hou = hou - 1;
		}
		if(hou == 0 && min == 9 && se ==59){
			//alert("提示：还有十分钟交卷。");// 不能使用alert
		}
		se --;
		if( min <= 0 && se <=0){
			//alert("必须提交试卷！");
			form1.submit();
			return;
		}
		window.setTimeout(callback, 1000);
	}
	// 计数器
	var count = 0;
	window.setTimeout(countTime,1000);
	function countTime(){
		count ++;
		window.setTimeout(countTime,1000);
		var input1 = document.getElementById("countTime");
		input1.value = count;
	}
	
</script>






</html>