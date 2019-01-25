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

<table border="1" width="1000" cellpadding="0" cellspacing="0" align="center">
<tr>	
	<td>KOSPI / KOSDAQ</td>
	<td>회사명</td>
	<td>점수</td>
	<td>관련 신문기사</td>
</tr>
<c:forEach items="${companies }" var="list">
<tr>
	<td>${list.market }</td>
	<td>${list.name }</td>
	<td>${list.score }</td>
	<td><input type="button" value="View More" onclick="topic_click()"></input></td>
</tr>
</c:forEach>
</table>

<script>
function topic_click() {
	alert("토픽");
}
</script>
</body>
</html>