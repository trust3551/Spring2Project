<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="register-box">
	<div class="card card-outline card-danger mt-4 mb-4" id="card-signup">
		<div class="card-header text-center">
			<a href="" class="h1"><b>DDIT</b>BOARD</a>
		</div>
		<div class="card-body">
			<p class="login-box-msg">회원가입</p>

			<form action="/notice/signup.do" method="post" id="signupForm" enctype="multipart/form-data">
				<div class="input-group mb-3 text-center">
					<img class="profile-user-img img-fluid img-circle" id="profileImg"
						src="${pageContext.request.contextPath}/resources/dist/img/AdminLTELogo.png" alt="User profile picture"
						style="width: 150px;">
				</div>
				<div class="input-group mb-3">
					<label for="inputDescription">프로필 이미지</label>
				</div>
				<div class="input-group mb-3">
					<div class="custom-file">
						<input type="file" class="custom-file-input" name="imgFile"
							id="imgFile"> <label class="custom-file-label"
							for="imgFile">프로필 이미지를 선택해주세요</label>
					</div>
				</div>
				<div class="input-group mb-3">
					<label for="inputDescription">프로필 정보</label>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memId" name="memId"
						placeholder="아이디를 입력해주세요"> <span
						class="input-group-append">
						<button type="button" class="btn btn-secondary btn-flat"
							id="idCheckBtn">중복확인</button>
					</span> <span class="error invalid-feedback" style="display: block;"></span>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memPw" name="memPw"
						placeholder="비밀번호를 입력해주세요"> <span
						class="error invalid-feedback" style="display: block;"></span>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memName" name="memName"
						placeholder="이름을 입력해주세요"> <span
						class="error invalid-feedback" style="display: block;"></span>
				</div>
				<div class="input-group mb-3">
					<div class="form-group clearfix">
						<div class="icheck-primary d-inline">
							<input type="radio" id="memGenderM" name="memGender" value="M"
								checked="checked"> <label for="memGenderM">남자&nbsp;</label>
						</div>
						<div class="icheck-primary d-inline">
							<input type="radio" id="memGenderF" name="memGender" value="F">
							<label for="memGenderF">여자 </label>
						</div>
					</div>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memEmail"
						name="memEmail" placeholder="이메일을 입력해주세요">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memPhone"
						name="memPhone" placeholder="전화번호를 입력해주세요">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memPostCode"
						name="memPostcode"> <span class="input-group-append">
						<button type="button" class="btn btn-secondary btn-flat" onclick="DaumPostcode()">우편번호 찾기</button>
					</span>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memAddress1"
						name="memAddress1" placeholder="주소를 입력해주세요">
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="memAddress2"
						name="memAddress2" placeholder="상세주소를 입력해주세요">
				</div>
				<div class="input-group mb-3">
					<div id="map" style="width: 100%; height:300px; display: none; "></div>
				</div>
				<div class="row">
					<div class="col-8">
						<div class="icheck-primary">
							<input type="checkbox" id="memAgree" name="memAgree" value="Y">
							<label for="memAgree">개인정보처리방침</label>
						</div>
					</div>
					<div class="col-4">
						<button type="button" class="btn btn-dark btn-block"
							id="signupBtn">가입하기</button>
					</div>
					<button type="button" class="btn btn-secondary btn-block mt-4"
						onclick="javascript:location.href='/notice/login.do'">뒤로가기</button>
				</div>
				<sec:csrfInput/>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bdc117a0b64778f0e4023650e96c9436&libraries=services"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(function(){
	var idCheckBtn = $("#idCheckBtn");		// 중복확인 버튼
	var memAddress2 = $("#memAddress2");	// 상세주소 Element
	var signupBtn = $("#signupBtn");		// 가입하기 버튼
	var signupForm = $("#signupForm");		// 가입하기 Form
	var imgFile = $("#imgFile");			// 프로필 이미지를 선택하기 위한 input element
	
	
	var idCheckFlag = false;			// 중복확인 flag(진행안함)
	
	// 프로필 이미지를 선택했을 때
	imgFile.on("change", function(){
		var file = event.target.files[0];	// Open 파일로 선택한 이미지 파일
		
		if(isImageFile(file)){	// 이미지 라면
			var reader = new FileReader();
			reader.onload = function(e){
				$("#profileImg").attr("src",e.target.result);
			}
			reader.readAsDataURL(file);
		}else{					// 이미지 파일이 아닐때
			alert("이미지 파일을 선택해주세요!");
		}
	});
	
	// 아이디 중복확인
	idCheckBtn.on("click", function(){
		let id = $("#memId").val();	// 아이디 값
		
		if(id == null || id == ""){
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		let data = {
			memId : id
		}
		
		$.ajax({
			url : "/notice/idCheck.do",
			type : "post",
			contentType : "application/json;charset=utf-8",
			beforeSend: function(xhr){	// 데이터 전송 전 헤더에  csrf값 설정
				xhr.setRequestHeader(header, token);				
			},
			data : JSON.stringify(data),
			success : function(res){
				// 결과로 넘어오는 데이터가 ServiceResult 
				// Exist, NotExist에 따라서 
				// '사용가능한 아이디 입니다' 또는 '이미 사용중인 아이디입니다'를 출력
				// 중복확인 시, idCheckFlag 라는 스위치가 발동(true로 변환)
				var err = $(".error")[0];	// error 클래스를 가지고 있는 element 중 id 요소에 해당하는 Element
				if(res == "NOTEXIST"){		// 아이디 사용가능
					alert("사용 가능한 아이디입니다!");
					$(err).html("사용 가능한 아이디입니다!").css("color", "green");
					idCheckFlag = true;		// 아이디 중복 체크 진행함
				}else{						// 아이디 사용 불가능
					alert("이미 사용중인 아이디입니다!");
					$(err).html("이미 사용중인 아이디입니다!").css("color", "red");
					inCheckFlag = false; 	// 아이디 중복 체크 진행했지만, 사용 불가능함
				}
			}
		});
	});
	
	var mapContainer;
	var map;
	
	// 상세 주소 입력후 포커스 아웃될때, 지도가 표시됨
	memAddress2.on("focusout", function(){
		var address1 = $("#memAddress1").val();	// 기본주소 값
		var address2 = $("#memAddress2").val();	// 상세주소 값
		
		if(address1 != null || address1 != ""){
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
			mapContainer.style.display = "block";

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 

		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(address1 + " " + address2, function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {

		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });

		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">HOME</div>'
		        });
		        infowindow.open(map, marker);

		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});
		
			$("#card-signup").css("top","140px");
		}
	});
	signupBtn.on("click",function(){
		// 아이디, 비밀번호, 이름까지만 일반적인 데이터 검증
		// 개인정보 처리방침 동의를 체크했을때만 가입하기가 가능하도록
		// 중복확인 처리가 true일때
		var agreeFlag = false; // 개인정보 처리방침 동의 체크 안함
		var memId = $("#memId").val();		// 아이디값
		var memPw = $("#memPw").val();		// 비밀번호 값
		var memName = $("#memName").val();	// 이름값
		
		if(memId = null || memId == ""){
			alert("아이디를 입력해주세요!");
			return false;
		}
		if(memPw = null || memPw == ""){
			alert("비밀번호를 입력해주세요!");
			return false;
		}
		if(memName = null || memName == ""){
			alert("이름을 입력해주세요!");
			return false;
		}
		
		// 개인정보 처리방침을 동의하게되면 Y값이 넘어오므로, 동의 여부는 true로 설정한다.
		var memAgree = $("#memAgree:checked").val();
		if(memAgree == "Y"){
			agreeFlag = true;
		}
		
		// 개인정보 처리방침을 동의했습니까?
		// 아이디 중복체크를 이행하고 오셨나요?
		// 하고 오셨다고요? 그럼 가입하기를 진행핧게요
		// 예? 안했어요? 그럼 가입하기 어렵지? 안해줌
		if(agreeFlag){
			if(idCheckFlag){
				signupForm.submit();
			}else{
				alert("아이디 동의 체크해주세요!");
			}
		}else{
			alert("개인정보 동의를 체크해주세요!");
		}
	});	
});

// 이미지 파일인지 확인
function isImageFile(file){
	var ext = file.name.split(".").pop().toLowerCase();	// 파일명에서 확장자를 추출
	return ($.inArray(ext, ["jpg","jpeg","png","gif"]) === -1 ) ? false : true;
}


function DaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
            } 
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('memPostCode').value = data.zonecode;
            document.getElementById("memAddress1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("memAddress2").focus();
        }
    }).open();
}
</script>
