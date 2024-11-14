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
	<h1>JSTL 태그들의 EX : c:catch</h1>
	<hr/>
	
	<c:catch var="ex">
		${member.hobbyArray[2] }
	</c:catch>
	
	<!-- 
		EL안에서 발생하는 에러 정보는 EL안에서 처리하므로 우리가 직접 핸들링 할 수 없다.
		에러가 발생했을때, 검증할 수 있는 포인트는 EL 표현문을 그룹으로 묶어서 하나씩 넣어보고 빼보면서
		페이지 결과를 확인한다.
	 -->
	
	<p>
		<c:if test="${ex != null }">
			${ex }
		</c:if>
	</p>	
</body>
</html>