<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>
<span id="clock"></span> Trend Analysis
</h1>
<hr>

 

<c:forEach items="${trends }" var="list">
	<h1>${list.topic } (${list.score }점)</h1>
	<c:forEach items="${list.keywords }" var="keywords">
		<form name="mForm" action="/companies" method="post">
		<input type="hidden" name="keyword" value="${keywords }">
		<ul>
			<li>
			<h3>
			${keywords }
			<input type="submit" value="관련기업 보기">
			</h3>
			</li>
		</ul>
		</form>
	</c:forEach>
	<hr>
</c:forEach>
<script>
function topic_click(topic) {
	alert(topic);
	document.mForm.submit();
}

function printTime() {
	var clock = document.getElementById("clock");
	//id값인 <span>태그를 변수clock에 저장
	var now = new Date(); 
	
	clock.innerHTML = 
	now.getFullYear() + "년 " + 
	(now.getMonth() + 1) + "월 " +
	now.getDate() + "일 " + 
	now.getHours() + "시 " + 
	now.getMinutes() + "분 " + 
	now.getSeconds() + "초";
	setTimeout("printTime()", 1000); 
	//1초마다 printTime()함수 호출
}
window.onload = function() { //html 로딩시 콜백함수 호출
	printTime();
}


</script>
</body>
</html>