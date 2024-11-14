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
	<p>1) 모델에 기본 생성자로 생성한 폼 객체를 추가한 후에 화면에 전달한다.</p>
	<form:form action = "/formtag/hidden/result" method="post" modelAttribute="member">
		<form:hidden path = "userId"/>
		<table border="1">
			<tr>
				<td>이름</td>
				<td>
					<form:input path="userName"/>
					<font color="red">
						<form:errors path="userName"/>
					</font>
				</td>
			</tr>		
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>