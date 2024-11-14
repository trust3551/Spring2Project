<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  
		문제) 아래 구성처럼 페이지를 작성해주세요.
		유저ID : _____________
		패스워드 : ____________
		이름 : ___________
		E-Mail : ___________
		생년월일 : ___________
		성별 : ◎ 남자 ◎ 여자
		개발자 여부 : □ 개발자 
		외국인 여부 : □ 외국인
		국적 : [ 선택해주세요 ▼]
		소유차량 : [ 선택해주세요 ▼]
		취미 : □ 운동 □ 음악감상 □ 영화감상 □ 독서 □ 기타
		우편번호 : ________
		주소 : _________
		카드1 - 번호 : ____________
		카드1 - 유효년월 : ____________
		카드2 - 번호 : ____________
		카드2 - 유효년월 : ____________
		자기소개 : _______________
		
		OPTION
		**** 성별 : 남자(male), 여자(female)
		**** 개발자 : 체크시  Y
		**** 외국인 : 체크시 true
		**** 국적 : 총 5개이상의 국적을 선택할 수 있도록 option을 제공해주세요.(value는 영문으로 설정)
		**** 소유차량 : 총 5개이상의 차량을 선택할 수 있도록 option을 제공해주세요. (value는 영문으로 설정)
						> 여러 차량을 선택할 수 있도록 해주세요.
		**** 취미 : 운동(sports),음악(music),영화(movie),독서(book),기타(etc)
		**** 우편번호와 주소는 Address 객체의 필드로 설정해주세요.
		**** 카드 1,2는 Card 객체의 필드로 설정해주세요.
		 
	-->
	<h3>회원가입</h3>
	
	<!-- 자기소개 -->
	<form action="/registerUser" method="post">
	
		유저Id : <input type = "text" name="userId" value="" /><br/>
		패스워드 : <input type="text" name="password" value="" /><br/>
		이름 : <input type="text" name="userName" value="" /><br/>
		E-Mail : <input type="text" name="email" value="" /><br/>
		생년월일 : <input type="text" name="birthDay" value=""/><br/>
		
	
	
		성별 :
		<input type = "radio" name="gender" value="male" checked="checked" />남자
		<input type = "radio" name="gender" value="female"  />여자<br/>
	
		개발자 여부 : 
		<input type="checkbox" name = "developer" value="Y">개발자<br/>
	
	
		외국인 여부 :
		<input type="checkbox" name = "foreigner" value="true">외국인<br/>
		
		국적 : 
		<select name = "nationality">
			<option value = "KOR">대한민국</option>
			<option value = "GER">독일</option>
			<option value = "AST">호주</option>
			<option value = "CAN">캐나다</option>
			<option value = "USA">미국</option>
		</select><br/>
		
		소유차량 : 
		<select name = "carList" multiple="multiple">
			<option value = "jeep">JEEP</option>
			<option value = "bmw">BMW</option>
			<option value = "audi">AUDI</option>
			<option value = "volvo">VOLVO</option>
			<option value = "kia">KIA</option>
		</select><br/>
		
		취미 :
		<input type="checkbox" name = "hobbyList" value="sports">운동
		<input type="checkbox" name = "hobbyList" value="music">음악감상
		<input type="checkbox" name = "hobbyList" value="movie">영화감상
		<input type="checkbox" name = "hobbyList" value="book">독서
		<input type="checkbox" name = "hobbyList" value="another">기타<br/>
		
		우편번호 : <input type="text" name="post" value="" /><br/>
		주소 : <input type="text" name="address" value=""/><br/>
		
		카드1-번호 : <input type = "text" name="cardList[0].no" /><br/>
		카드1-유효년월 : <input type = "text" name="cardList[0].validMonth" /><br/>
		카드2-번호 : <input type = "text" name="cardList[1].no" /><br/>
		카드2-유효년월 : <input type = "text" name="cardList[1].validMonth" /><br/>
	
		자기소개 : <br />
		<textarea rows="10" cols="30" name="introduction"></textarea>
		<br/><br/>
		<input type="submit" value="전송" />
	</form>
	
	
	
</body>
</html>