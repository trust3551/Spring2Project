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
	<p>dateStyle 속성을 short로 지정하여 문자열을 Date 객체로 변환한다.</p>
	<p>dateValue : ${dateValueShort }</p>
	<fmt:parseDate value="${dateValueShort }" type="date" dateStyle="short" var="dateShort"/>
	<p>결과 : ${dateShort }</p>
	
	<p>dateStyle 속성을 medium로 지정하여 문자열을 Date 객체로 변환한다.</p>
	<p>dateValue : ${dateValueMedium }</p>
	<fmt:parseDate value="${dateValueMedium }" type="date" dateStyle="medium" var="dateMedium"/>
	<p>결과 : ${dateMedium }</p>
	
	<p>dateStyle 속성을 long로 지정하여 문자열을 Date 객체로 변환한다.</p>
	<p>dateValue : ${dateValueLong }</p>
	<fmt:parseDate value="${dateValueLong }" type="date" dateStyle="long" var="dateLong"/>
	<p>결과 : ${dateLong }</p>

	
	<p>dateStyle 속성을 full로 지정하여 문자열을 Date 객체로 변환한다.</p>
	<p>dateValue : ${dateValueFull }</p>
	<fmt:parseDate value="${dateValueFull }" type="date" dateStyle="full" var="dateFull"/>
	<p>결과 : ${dateFull }</p>
</body>
</html>