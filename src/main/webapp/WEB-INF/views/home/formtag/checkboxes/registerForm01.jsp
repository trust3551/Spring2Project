<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Spring Form</h4>
	<p>1) 모델에 Map 타입의 데이터를 생성하여 추가한 후 에 화면에 전달한다.</p>

	<form:form action = "/formtag/register" method="post" modelAttribute="member">
		<table border="1">
			<tr>
				<td>취미(hobbyList)</td>
				<td>
					<form:checkboxes items="${hobbyMap }" path="hobbyList"/><br/>
				</td>
			</tr>		
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>