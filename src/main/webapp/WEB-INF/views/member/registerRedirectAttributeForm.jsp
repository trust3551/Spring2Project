<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>4. RedirectAttribute</h1>
	<hr/>
	
	<form action="/redirectAttribute/register" method = "post">
		userId : <input type = "text" name = "userId" value = "hongkd"/><br/>
		password : <input type = "text" name = "password" value = "1234"/><br/>
		<input type="submit" value="전송"/><br/>
	</form>
</body>
</html>