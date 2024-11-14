<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>REGISTER</h2>
	<hr/>
	<form action="/item/register" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td> 상품명</td>
				<td><input type="text" name ="itemName"/></td>
			</tr>
			<tr>
				<td> 가격</td>
				<td><input type="text" name ="price"/></td>
			</tr>
			<tr>
				<td> 파일</td>
				<td><input type="file" name ="picture"/></td>
			</tr>
			<tr>
				<td> 개요</td>
				<td><textarea rows="10" cols="30" name="description"></textarea></td>
			</tr>
		</table>
		<div>
			<button type="submit">Register</button>
			<button type="button" onclick ="javascript:location.href='/item/list'">List</button>
		</div>	
		<sec:csrfInput/>
	</form>
</body>
</html>