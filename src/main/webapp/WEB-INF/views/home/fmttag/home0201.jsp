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
	<p>2) type 속성이 지정되지 않으면 기본값은 number이다.</p>
	<p>coin : ${coin }</p>
	<p>number parse : <fmt:parseNumber value="${coin }" var = "coinNum"></fmt:parseNumber> </p>
	<p>결과 : ${coinNum }</p>
	<hr/>
	
	<p>3) type 속성이 currency</p>
	<p>coin2 : ${coin2 }</p>
	<p>currency parse : <fmt:parseNumber value="${coin2 }" type="currency" var ="coinCurrency"></fmt:parseNumber>
	<p>결과 : ${coinCurrency }</p>
	<hr/>
	
	<p>4) pattern 속성을 이용한 방법</p>
	<p>coin3 : ${coin3 }</p>
	<p>pattern parse : <fmt:parseNumber value = "${coin3 }" pattern = "0,000.00" var = "coinPattern"></fmt:parseNumber>
	<p>결과 : ${coinPattern }</p>
	
</body>
</html>