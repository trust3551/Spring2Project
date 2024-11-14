<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>
</head>
<body>
	<h1>MODIFY</h1>
	
	<!--  수정, 수정/목록 -->
	<form action="/board/post" method="post">
		<input type="submit" value="Modify" name="modify"/>
	</form>
	
	<a href="/board/get?list">List</a>
</body>
</html>