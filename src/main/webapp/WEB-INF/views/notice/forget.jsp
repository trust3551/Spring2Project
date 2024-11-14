<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-md-4"></div>
	<div class="col-md-4">
		<div class="card card-outline card-primary">
			<div class="card-header text-center">
				<p class="h4">
					<b>아이디찾기</b>
				</p>
			</div>
			<div class="card-body">
				<p class="login-box-msg">아이디 찾기는 이메일, 이름을 입력하여 찾을 수 있습니다.</p>
				<form action="/notice/id.do" method="post" id="idForm">
					<div class="input-group mb-3">
						<input type="text" class="form-control" id="memEmail" name="memEmail" placeholder="이메일을 입력해주세요.">
					</div>
					<div class="input-group mb-3">
						<input type="text" class="form-control" id="memName" name="memName" placeholder="이름을 입력해주세요.">
					</div>
					<div class="input-group mb-3">
						<p id="pid">
						
						</p>
					</div>
					<div class="row">
						<div class="col-12">
							<button type="button" class="btn btn-primary btn-block" id="idBtn">아이디찾기</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<br />
		<div class="card card-outline card-primary">
			<div class="card-header text-center">
				<p href="" class="h4">
					<b>비밀번호찾기</b>
				</p>
			</div>
			<div class="card-body">
				<p class="login-box-msg">비밀번호 찾기는 아이디, 이메일, 이름을 입력하여 찾을 수 있습니다.</p>
				<form action="/notice/pw.do" method="post" id="pwForm">
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="memId" placeholder="아이디를 입력해주세요.">
					</div>
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="memEmail" placeholder="이메일을 입력해주세요.">
					</div>
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="memName" placeholder="이름을 입력해주세요.">
					</div>
					<div class="input-group mb-3">
						<p>
							회원님의 비밀번호는 [0000] 입니다.
						</p>
					</div>
					<div class="row">
						<div class="col-12">
							<button type="button" class="btn btn-primary btn-block" id="pwBtn">비밀번호찾기</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<br/>
		<div class="card card-outline card-secondary">
			<div class="card-header text-center">
				<h4>MAIN MENU</h4>
				<button type="button" class="btn btn-secondary btn-block">로그인</button>
			</div>
		</div>
	</div>
	<div class="col-md-4"></div>
</div>
<script type="text/javascript">
$(function(){
	var idBtn = $("#idBtn");
	var pwBtn = $("#pwBtn");
	
	idBtn.on("click", function(){
		let email = $("#memEmail").val();
		let name = $("#memName").val();

	let data= {
		memEmail : email,
		memName : name
	};
		
	$.ajax({
		url : "/notice/id.do",
		type : "post",
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(data),
		success : function(res){
			let pid =$("#pid");
			pid.html("회원 ID는 " + res.memId + "입니다.");
			}
		});
	});
});

</script>
