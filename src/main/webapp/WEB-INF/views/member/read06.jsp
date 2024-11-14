<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read06</title>
</head>
<body>
	<h1>READ06</h1>
	<hr/>
	user.userId : ${user.userId }<br/>
	user.password : ${user.password }<br/>
	user.userName : ${user.userName }<br/>
	user.email : ${user.email }<br/>
	user.birthDay : ${user.birthDay }<br/>
	user.gender : ${user.gender }<br/>
	user.hobby : ${user.hobby }<br/>
	
	<p>hobbyArray</p>
	<c:forEach items = "${user.hobbyArray }" var="hobby">
		- <c:out value="${hobby }"/>
	</c:forEach>
	
	<p>hobbyList</p>
	<c:forEach items="${user.hobbyList }" var = "hobby">
		- <c:out value="${hobby }"/>
	</c:forEach>
	user.foreigner :  ${user.foreigner }<br/>
	user.developer :  ${user.developer }<br/>
	user.nationality :  ${user.nationality }<br/>
	user.address.postCode : ${user.address.postCode }<br/>
	user.address.location : ${user.address.location }<br/>
	
	user.cars : ${user.cars }<br/>
	
	<p>carArray</p>
	<c:forEach items="${user.carArray }" var = "car">
		- <c:out value="${car }"/>
	</c:forEach>
	
	<p>carList</p>
	<c:forEach items="${user.carList }" var = "car">
		- <c:out value="${car }"/>
	</c:forEach>
	
	<p>cardList</p>
	<c:forEach items="${user.cardList }" var = "card">
		- <c:out value="${card.no }"/>
		- <c:out value="${card.validMonth }"/><br/>
	</c:forEach>
	
	user.introduction : ${user.introduction }<br/>
	user.dateOfBirth : ${user.dateOfBirth }<br/>
</body>
</html>