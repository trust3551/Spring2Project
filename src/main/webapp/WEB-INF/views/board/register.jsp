<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<h1>REGISTER</h1>
	
	
	<!-- 등록 페이지, 등록 / 목록 버튼 -->
	<form action="/board/post" method="post">
		<input type="submit" value="Register" name="register"/>
	</form>
	
	<a href="/board/get?list">List</a>
</body>
</html>