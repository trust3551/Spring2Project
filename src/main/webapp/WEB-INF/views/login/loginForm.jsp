<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>LOGIN PAGE</h1>
	<hr/>
	<form method="post" action="/intercept/login">
		아이디: <input type="text" name = "userId"/><br/>
		비밀번호: <input type="text" name = "userPw"/><br/>
		<input type="submit" value="로그인"/>
	</form>
	<font color="red">${message }</font>
</body>
</html>