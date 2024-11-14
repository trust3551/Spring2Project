<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL 태그들의 EX : c:set</h1>
	<hr/>
	
	<p>1) c:set 태그를 이용하여 몸체에 데이터를 넣을수 있고, 원하는 변수를 설정할 수 있다.</p>
	<c:set value="${member.userId }" var="id"/>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${member.userId}</td>
		</tr>
	</table>
	
	<c:set var="memId">${member.userId }</c:set>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memId}</td>
		</tr>
	</table>
</body>
</html>