<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>Read</h2>
	<hr/>
	<table border="1">
		<tr>
			<td>userId</td>
			<td>${member.userId}</td>
		</tr>
		<tr>
			<td>userName</td>
			<td>${member.userName}</td>
		</tr>
		<tr>
			<td>auth - 1</td>
			<td>${member.authList[0].auth}</td>
		</tr>
		<tr>
			<td>auth - 2</td>
			<td>${member.authList[1].auth}</td>
		</tr>
		<tr>
			<td>auth - 3</td>
			<td>${member.authList[2].auth}</td>
		</tr>
	
	</table>
	<form action="/crud/member/remove" method="post" id ="delForm">
		<input type ="hidden" name ="userNo" value="${member.userNo }">
	</form>
	
	<button type="button" id="modBtn">수정</button>
	<button type="button" id="delBtn">삭제</button>
	<button type="button" id="listBtn">목록</button>
</body>
<script type="text/javascript">
$(function(){
	var delForm = $("#delForm");
	var modBtn = $("#modBtn");
	var delBtn = $("#delBtn");
	var listBtn = $("#listBtn");
	
	modBtn.on("click", function(){
		delForm.attr("action", "/crud/member/modify");
		delForm.attr("method","get");
		delForm.submit();
		
	});
	
	delBtn.on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			delForm.submit();
		}
	});
	
	
	listBtn.on("click", function(){
		location.href="/crud/member/list";
	});
});


</script>
</html>