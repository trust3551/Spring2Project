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
	<h1>JSTL 태그들의 EX : c:remove</h1>
	<hr/>
	
	<p>1) c:remove를 이용하여 설정된 변수의 값을 삭제할 수 있다.</p>
	<c:set value="${member.userId }" var="memberId"/>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memberId}</td>
		</tr>
	</table>
	
	<c:set target="${member }" property="userId" value="hongkildong"/>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${member.userId}</td>
		</tr>
	</table>
	
	<!-- memberId에 저장된 값을 삭제한다. -->
	<c:remove var="memberId"/>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memberId}</td>
		</tr>
	</table>
</body>
</html>