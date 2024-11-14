<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		[아래 결과처럼 출력해주세요.]
		ID : a001
		PW : 1234
		이름 : 조현준
		E-MAIL : ddit@n.com
		생년월일 : 2024-08-27 (날짜는 어떤 포멧도 상관없이 데이터만 출력되면 인정)
		성별 : 남자
		개발자여부 : 개발자(개발자가 아닌경우 '-'로 출력 )
		외국인여부 : 외국인(외국인 / 내국인)
		국적 : 대한민국
		소유차량 : 소유차량 없음(소유차량 없음 / BMW AUDI / VOLVO AUDI JEEP / ...)
		취미 : 취미 없음(취미 없음 / 운동 영화감상 음악감상 / 운동 / ...)
		우편번호 : 56289
		주소 : 대전광역시 중구 오류동 82 DDIT
		카드1- 번호 : 1451-5644-2455-2452
		카드1- 유효년월 : 2024-08-27 (날짜는 어떤 포멧도 상관없이 데이터만 출력되면 인정)
		카드2- 번호 : 1451-5644-2455-2452
		카드2- 유효년월 : 2024-08-27 (날짜는 어떤 포멧도 상관없이 데이터만 출력되면 인정)
		소개 : 반갑습니다! 잘부탁드립니다!
		
		*** JSTL을 이용하여 결과를 출력해주세요.
		*** 넘겨받은 데이터 모두 영문이 아닌 한글처리가 된 결과를 출력해주세요.
		
	 -->
		ID : ${member.userId}<br/>
        PW : ${member.password}<br/>
       	이름 : ${member.userName}<br/>
       	E-MAIL : ${member.email}<br/>
       	<fmt:formatDate value="${member.birthDay}" var="birthDay"/>
       	생년월일 : ${birthDay}<br/>
       	개발자여부 : 
       	<c:choose>
       		<c:when test="${member.developer == 'Y'}">개발자</c:when>
       		<c:otherwise>-</c:otherwise>
       	</c:choose><br/>
       	외국인여부 :
       	<c:choose>
       		<c:when test="${member.foreigner == 'true'}">외국인</c:when>
       		<c:otherwise>내국인</c:otherwise>
       	</c:choose><br/>
       	국적:
       	<c:if test="${member.nationality == 'KOR'}">대한민국</c:if>
       	<c:if test="${member.nationality == 'GER'}">독일</c:if>
       	<c:if test="${member.nationality == 'AST'}">호주</c:if>
       	<c:if test="${member.nationality == 'CAN'}">캐나다</c:if>
       	<c:if test="${member.nationality == 'USA'}">미국</c:if><br/>
       	
       	소유차량 :
	<c:choose>
		<c:when test="${empty member.carList}">차량없음
       	</c:when>
		<c:otherwise>
			<c:forEach var="car" items="${member.carList}">
				<c:if test="${car == 'jeep' }">지프</c:if>
				<c:if test="${car == 'bmw' }">비엠</c:if>
				<c:if test="${car == 'audi' }">아우디</c:if>
				<c:if test="${car == 'volvo' }">볼보</c:if>
				<c:if test="${car == 'kia' }">기아</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose><br/> 
		취미 : 
	<c:choose>
		<c:when test="${empty member.hobbyList}">취미없음
       	</c:when>
		<c:otherwise>
			<c:forEach var="hobby" items="${member.hobbyList}">
				<c:if test="${hobby == 'sports' }">운동</c:if>
				<c:if test="${hobby == 'music' }">음악감상</c:if>
				<c:if test="${hobby == 'movie' }">영화감상</c:if>
				<c:if test="${hobby == 'book' }">독서</c:if>
				<c:if test="${hobby == 'another' }">기타</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose><br/> 
       	우편번호 : ${member.post}<br/>
       	주소 : ${member.address}<br/>
       	카드1- 번호 : ${member.cardList[0].no}<br/>
       	<fmt:formatDate value="${member.cardList[0].validMonth}" var="validMonth1"/>
       	카드1- 유효년월 : ${validMonth1}<br/>
       	카드2- 번호 : ${member.cardList[1].no}<br/>
       	<fmt:formatDate value="${member.cardList[0].validMonth}" var="validMonth2"/>
       	카드2- 유효년월 : ${validMonth2}<br/>
       	자기소개 : ${member.introduction}<br/>
</body>
</html>