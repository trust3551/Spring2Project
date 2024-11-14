<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h2>REGISTER2</h2>
	<hr/>
	<form:form action="/crud/board/register" method="post" modelAttribute="board">
		<c:set value="등록" var="btnText"/>
		<c:if test="${status eq 'u' }">
			<c:set value="수정" var="btnText"/>
			<input type="hidden" name ="boardNo" value="${board.boardNo }"/>
		</c:if>
		<table>
			<tr>
				<td>제목</td>
				<td>
					<form:input path="title"/>
					<input type="text" id="title" name="title" value="${board.title }"/>
				</td>
			</tr>
			<tr>
				<td>작성자</td>
<%-- 				<c:choose> --%>
<%-- 				<c:when test=""></c:when> --%>
				<td><input type="text" id="writer" name="writer" value="${board.writer }" /></td>
<%-- 				</c:choose> --%>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="30" name="content" id="content">${board.content }</textarea>
				</td>
			</tr>
		</table>
		<div>
			<input type="button" id="addBtn" value="${btnText }"/>
			<input type="button" id="listBtn" value="목록"/>
		</div>
	</form:form>
</body>
<script type="text/javascript">
$(function(){
	var board = $("#board");
	var addBtn = $("#addBtn");
	var listBtn = $("#listBtn");
	
	// 등록 버튼을 클릭
	addBtn.on("click", function(){
		let title = $("#title").val();			// 제목의 값을 가져온다
		let writer = $("#writer").val();		// 작성자의 값을 가져온다
		let content = $("#content").val();		// 내용의 값을 가져온다
		
		if(title == null || title == ""){
			alert("제목을 입력해주세요.");
			return false;
		}
		
		if(writer == null || writer == ""){
			alert("작성자를 입력해주세요.");
			return false;
		}
		
		if(content == null || content == ""){
			alert("내용을 입력해주세요.");
			return false;
		}
		
		// 등록과 수정일때 넘어갈 경로를 달리 설정한다.
		if($(this).val() == "수정"){
			board.attr("action", "/crud/board/modify");
		}
		board.submit(); // 서버 전송
	});	
	
	// 목록 버튼을 클릭
	listBtn.on("click", function(){
		location.href = "/crud/board/list";
	});
});
</script>
</html>