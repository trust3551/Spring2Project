<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
	<div class="card">
		<div class="card-header">배운거 연습해보기!
		
		</div>
		<div class="card-body">
			<select id="selectBox" onchange="choice()">
				<option value="jpg">JPG</option>
				<option value="png">PNG</option>
				<option value="gif">GIF</option>
			</select>
			<hr>
			<c:choose>
				<div class="card">
					<div class="card-header">${image }</div>
					<c:when test="${empty imageFileList }">
						사진없음
					</c:when>
					<c:otherwise>
						<c:forEach items="${imageFileList }" var="image">
							<div class="card-body">
								<img height="40" width="80" alt=""
									src="/resources/image/${image }">
							</div>
							<div class="card-footer">다운로드</div>
						</c:forEach>
					</c:otherwise>
				</div>
			</c:choose>
		</div>
	</div>
	<div class="card-footer">
		
		
		
		</div>
</body>
</html>