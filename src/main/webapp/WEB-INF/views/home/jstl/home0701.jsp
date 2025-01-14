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
	<h1>JSTL 태그들의 EX : c:forEach, c:forTokens</h1>
	<hr/>
	
	<p>c:forEach를 이용한 데이터 출력</p>
	<c:forEach items="${member.hobbyArray }" var="hobby">
		${hobby }<br/>
	</c:forEach>
	
	<!-- 
		delims 속성에 지정된 구분자를 사용하여 items 속성에 전달된 문자열을 나누고 var 속성에 명시한 변수에
		나뉘어진 문자열을 지정한다.
	 -->
	
	<p>c:forTokens를 이용한 데이터 출력</p>
	<c:forTokens items="${member.hobby }" delims="," var="hobby">
		${hobby }<br/>
	</c:forTokens>
	

</body>
</html>