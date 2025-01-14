<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>1) type 속성을 지정하거나 pattern 속성을 지정하여 숫자를 형식화한다.</p>
	<p>coin : ${coin }</p>
	<p>number : <fmt:formatNumber value="${coin }" type="number"> </fmt:formatNumber></p>
	<p>currency : <fmt:formatNumber value="${coin }" type="currency"> </fmt:formatNumber></p>
	<p>percent : <fmt:formatNumber value="${coin }" type="percent"> </fmt:formatNumber></p>
	<p>pattern : <fmt:formatNumber value="${coin }" pattern="000000.00"> </fmt:formatNumber></p>
</body>
</html>