<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>REGISTER</h2>
	<hr/>
	<form action="/crud/member/register" method="post" id="member">
		<table>
			<tr>
				<td>userId</td>
				<td><input type="text" id="userId" name="userId"/></td>
			</tr>
			<tr>
				<td>userPw</td>
				<td><input type="text" id="userPw" name="userPw"/></td>
			</tr>
			<tr>
				<td>userName</td>
				<td><input type="text" id="userName" name="userName"/></td>
			</tr>
		</table>
		<div>
			<input type="button" id="addBtn" value="등록"/>
			<input type="button" id="listBtn" value="목록"/>
		</div>
	</form>
</body>
<script type="text/javascript">
$(function(){
	var board = $("#board");
	var addBtn = $("#addBtn");
	var listBtn = $("#listBtn");
	
	// 등록 버튼을 클릭
	addBtn.on("click", function(){
		var id = $("#userId").val();		// 아이디 값
		var pw = $("#userPw").val();		// 비밀번호 값
		var name = $("#userName").val();	// 이름 값
		
		if(id == null || id == ""){
			alert("아이디를 입력해주세요!");
			return false;
		}
		if(pw == null || pw == ""){
			alert("비밀번호를 입력해주세요!");
			return false;
		}
		if(name == null || name == ""){
			alert("이름을 입력해주세요!");
			return false;
		}
		member.submit(); 		// 데이터 전송
	});
	
	// 목록 버튼을 클릭
	listBtn.on("click", function(){
		location.href = "/crud/member/list";
	});
});
</script>
</html>