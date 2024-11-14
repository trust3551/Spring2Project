<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read04</title>
</head>
<body>
	<h1>READ04</h1>
	<hr/>
	
	<p>carArray</p>
	<c:forEach items="${carArray }" var="car">
		<c:out value="${car }"/>
	</c:forEach>
	
	<p>carList</p>
	<c:forEach items="${carList }" var="car">
		<c:out value="${car }"/>
	</c:forEach>
	
	<p>hobbyArray</p>
	<c:forEach items="${hobbyArray }" var="hobby">
		<c:out value="${hobby }"/>
	</c:forEach>
	
	<p>hobbyList</p>
	<c:forEach items="${hobbyList }" var="hobby">
		<c:out value="${hobby }"></c:out>
	</c:forEach>
	
</body>
</html>