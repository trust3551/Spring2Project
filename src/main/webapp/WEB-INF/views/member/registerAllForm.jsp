<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>
	<!-- 
		문제) 아래 구성처럼 페이지를 작성해주세요.
		유저ID : __________
		패스워드 : __________
		이름 : __________
		E-Mail : __________
		생년월일 : __________
		성별 : ◎ 남자 ◎ 여자
		개발자 여부 : □ 개발자
		외국인 여부 : □ 외국인
		국적 : [ 선택해주세요  ▼ ]
		소유차량 : [ 선택해주세요 ▼ ]
		취미 : □ 운동 □ 음악감상 □ 영화감상 □ 독서 □ 기타
		우편번호 : __________
		주소 : __________
		카드1 - 번호 : __________
		카드1 - 유효년월 : __________
		카드2 - 번호 : __________
		카드2 - 유효년월 : __________
		자기소개 : ____________________
		
		OPTION
		**** 성별 : 남자(male), 여자(femail)
		**** 개발자 : 체크 시 Y
		**** 외국인 : 체크 시 true
		**** 국적 : 총 5개이상의 국적을 선택할 수 있도록 option을 제공해주세요.(value는 영문으로 설정)
		**** 소유차량 : 총 5개이상의 차량을 선택할 수 있도록 option을 제공해주세요.(value는 영문으로 설정)
		              > 여러 차량을 선택할 수 있도록 해주세요.
		**** 취미 : 운동(sports),음악(music),영화(movie),독서(book),기타(etc)
		**** 우편번호와 주소는 Address 객체의 필드로 설정해주세요.
		**** 카드1,2는 Card 객체의 필드로 설정해주세요. 
	 -->
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="row">
				<h3>RegisterAllForm</h3>
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<div class="card-title">폼 양식 이용하여 데이터 전송하기</div>
						</div>				
						<div class="card-body">
							<form action="/registerUser" method="post">
								<table class="table table-bordered">
									<tr>
										<td>유저 ID</td>
										<td><input type="text" class="form-control" name="userId"/></td>
									</tr>
									<tr>
										<td>패스워드</td>
										<td><input type="password" class="form-control" name="password"/></td>
									</tr>
									<tr>
										<td>이름</td>
										<td><input type="text" class="form-control" name="userName"/></td>
									</tr>
									<tr>
										<td>E-Mail</td>
										<td><input type="text" class="form-control" name="email"/></td>
									</tr>
									<tr>
										<td>생년월일</td>
										<td><input type="text" class="form-control" name="dateOfBirth"/></td>
									</tr>
									<tr>
										<td>성별</td>
										<td>
											<div class="form-check form-check-inline">
												<input class="form-check-input" type="radio"
													name="gender" id="radio1" value="male">
												<label class="form-check-label" for="radio1">남자</label>
											</div>
											<div class="form-check form-check-inline">
												<input class="form-check-input" type="radio"
													name="gender" id="radio2" value="female">
												<label class="form-check-label" for="radio2">여자</label>
											</div>
										</td>
									</tr>
									<tr>
										<td>개발자 여부</td>
										<td>
											<div class="form-check">
												<input type="checkbox" class="form-check-input" name="developer" value="Y"/> 개발자
											</div>
										</td>
									</tr>
									<tr>
										<td>외국인 여부</td>
										<td>
											<div class="form-check">
												<input type="checkbox" class="form-check-input" name="foreigner" value="true"/> 외국인
											</div>
										</td>
									</tr>
									<tr>
										<td>국적</td>
										<td>
											<select name="nationality" class="form-control" id="nation" onchange="f_contry()">
												<option value="korea">대한민국</option>
												<option value="germany">독일</option>
												<option value="austrailia">호주</option>
												<option value="canada">캐나다</option>
												<option value="usa">미국</option>
											</select><br/>
											<div id="nationResult">
												
											</div>
										</td>
									</tr>
									<tr>
										<td>소유차량</td>
										<td>
											<select name="carList" class="form-control" multiple="multiple">
												<option value="jeep">JEEP</option>
												<option value="bmw">BMW</option>
												<option value="audi">AUDI</option>
												<option value="volvo">VOLVO</option>
												<option value="volvo">KIA</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>취미</td>
										<td>
											<div class="form-check form-check-inline">
												<input class="form-check-input" name="hobbyList" type="checkbox"id="hobby1" value="sports"> 
												<label class="form-check-label" for="hobby1">운동</label>
											</div>
											<div class="form-check form-check-inline">
												<input class="form-check-input" name="hobbyList" type="checkbox"id="hobby2" value="music"> 
												<label class="form-check-label" for="hobby2">음악감상</label>
											</div>
											<div class="form-check form-check-inline">
												<input class="form-check-input" name="hobbyList" type="checkbox"id="hobby3" value="movie"> 
												<label class="form-check-label" for="hobby3">영화시청</label>
											</div>
											<div class="form-check form-check-inline">
												<input class="form-check-input" name="hobbyList" type="checkbox"id="hobby4" value="book"> 
												<label class="form-check-label" for="hobby4">독서</label>
											</div>
											<div class="form-check form-check-inline">
												<input class="form-check-input" name="hobbyList" type="checkbox"id="hobby5" value="etc"> 
												<label class="form-check-label" for="hobby5">기타</label>
											</div>
										</td>
									</tr>
									<tr>
										<td>우편번호</td>
										<td><input type="text" class="form-control" name="address.postCode"/></td>
									</tr>
									<tr>
										<td>주소</td>
										<td><input type="text" class="form-control" name="address.location"/></td>
									</tr>
									<tr>
										<td>카드1 - 번호</td>
										<td><input type="text" class="form-control" name="cardList[0].no"/></td>
									</tr>
									<tr>
										<td>카드1 - 유효년월</td>
										<td><input type="text" class="form-control" name="cardList[0].validMonth"/></td>
									</tr>
									<tr>
										<td>카드2 - 번호</td>
										<td><input type="text" class="form-control" name="cardList[1].no"/></td>
									</tr>
									<tr>
										<td>카드2 - 유효년월</td>
										<td><input type="text" class="form-control" name="cardList[1].validMonth"/></td>
									</tr>
									<tr>
										<td>소개</td>
										<td>
											<textarea rows="5" cols="10" class="form-control" name="introduction"></textarea>
										</td>
									</tr>
									<tr>
										<td>
											<input type="submit" class="btn btn-primary" value="등록"/>
											<input type="reset" class="btn btn-danger" value="리셋"/>
										</td>
									</tr>
								</table>
							</form>
							<br/><br/><br/><br/><br/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-3"></div>
	</div>
<script type="text/javascript">
function f_contry() {
// 	let a = document.querySelector("#nationResult"); // 자바스크립트 쓸때 사용
	var nationResult = $("#nationResult");
	var nation = $("#nation").val();
	var name = "";
	var html = "";
	
	if(nation == "korea"){
		name = "대한민국";
	}
	else if(nation == "germany"){
		name = "독일";
	}
	else if(nation == "austrailia"){
		name = "호주";
	}
	else if(nation == "canada"){
		name = "캐나다";
	}
	else if(nation == "usa"){
		name = "미국";
	}
	
	
	html += "<img height='150px' width='300px' src='${pageContext.request.contextPath}/resources/image/" + nation + ".jpg' />"; 
	html += "<div>";
	html += "<span style='color : red'>" + name + "</span>"+ "을/를 선택하셨습니다.";
	html += "</div>";
	
	nationResult.html(html);  	// innerHTML이랑 같은 코드
	
// 	a.innerHTML = html;   // 자바스크립트 쓸때 사용
	
	console.log(nation);
	
}

</script>
</body>
</html>



