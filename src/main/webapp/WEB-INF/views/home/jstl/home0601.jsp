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
	<h1>JSTL 태그들의 EX : c:when, c:otherwise</h1>
	<hr/>
	
	<!-- 주석은 choose안에 주면 에러가 발생하므로 choose위에다가 달아야한다. -->
	<c:choose>
		<c:when test="${member.gender == 'M' }">
			<p>남자</p>
		</c:when>
		<c:otherwise>
			<p>여자</p>		
		</c:otherwise>
	</c:choose>
</body>
</html>