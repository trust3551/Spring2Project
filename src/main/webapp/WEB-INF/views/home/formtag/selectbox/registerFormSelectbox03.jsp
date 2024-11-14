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
	<p>1) 모델에 List 타입의 데이터를 생성하여 추가한 후 에 화면에 전달한다.</p>

	<form:form action = "/formtag/selectbox/result" method="post" modelAttribute="member">
		<table border="1">
			<tr>
				<td>소유차량</td>
				<td>
					<form:select path="carList" multiple="true">
						<form:option value="" label="---선택해주세요---"/>
						<form:options items="${carCodeList }" itemLabel="label" itemValue="value"/>
					</form:select>
				</td>
			</tr>		
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>