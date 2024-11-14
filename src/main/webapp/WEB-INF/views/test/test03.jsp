<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-4">
					<div class="card">
						<div class="card-header">
							<div class="card-title">상품 등록</div>
						</div>
						<div class="card-body">
							<form method="post">
								<table class="table table-bordered">
									<tr>
										<td colspan="2">
											<img src="" id="carImage" alt="" style="width:100%;"/>
										</td>
									</tr>
									<tr>
										<td>상품이미지</td>
										<td>
											<input type="file" id="inputImage" class="form-control" name="prodImageFile"/>
										</td>
									</tr>
									<tr>
										<th>상품ID</th>
										<td>
											<input type="text" id="prodId" class="form-control" name="prodId"/>
										</td>
									</tr>
									<tr>
										<th>상품명</th>
										<td>
											<input type="text" id="prodName" class="form-control" name="prodName"/>
										</td>
									</tr>
									<tr>
										<th>상품금액</th>
										<td>
											<input type="text" id="prodPrice" class="form-control" name="prodPrice"/>
										</td>
									</tr>
									<tr>
										<th>상품색상</th>
										<td>
											<div class="form-check form-check-inline" >
												<input class="form-check-input" type="radio" name="prodColor" id="white" value="white" checked> 
												<label class="form-check-label" for="new">
													<span class="badge badge-light">White</span>
												</label>
											</div>
											<div class="form-check form-check-inline">
												<input class="form-check-input" type="radio" name="prodColor" id="red" value="red"> 
												<label class="form-check-label" for="red">
													<span class="badge badge-danger">Red</span>
												</label>
											</div>
											<div class="form-check form-check-inline">
												<input class="form-check-input" type="radio" name="prodColor" id="blue" value="blue"> 
												<label class="form-check-label" for="blue">
													<span class="badge badge-primary">Blue</span>
												</label>
											</div>
										</td>
									</tr>
									<tr>
										<th>상품설명</th>
										<td>
											<textarea rows="10" cols="20" id="prodDescription" name="prodDescription" class="form-control"></textarea>
										</td>
									</tr>
								</table>
							</form>
						</div>
						<div class="card-footer text-right">
							<button type="button" class="btn btn-primary" id="addBtn">등록</button>
							<button type="reset" class="btn btn-danger" id="resetBtn">취소</button>
						</div>
					</div>		
				</div>
				<div class="col-md-8">
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">
										<h5>상품 목록</h5>
									</div>
								</div>
								<div class="card-body">
									<table class="table table-bordered">
										<thead>
											<tr>
												<td>번호</td>
												<td>상품ID</td>
												<td>상품명</td>
												<td>상품금액</td>
												<td>상품색상</td>
												<td>등록일</td>
											</tr>
										</thead>
										<tbody id="listBody">
											<tr>
												<td colspan="6" class="text-center">조회하신 상품이 존재하지 않습니다.</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="col-md-12 pt-4">
							<div class="card">
								<div class="card-header">
									<div class="card-title">
										<h5>상품 상세</h5>
									</div>
								</div>
								<div class="card-body">
									<table class="table table-bordered text-center">
										<tr>
											<td width="50%" rowspan="6">
												<img width="350" height="300" src="" alt="" id="detail_img"/>
											</td>
											<td width="20%">상품 ID</td>
											<td id="detail_id"></td>
										</tr>
										<tr>
											<td>상품명</td>
											<td id="detail_name"></td>
										</tr>
										<tr>
											<td>상품금액</td>
											<td id="detail_price"></td>
										</tr>
										<tr>
											<td>상품색상</td>
											<td id="detail_color"></td>
										</tr>
										<tr>
											<td>상품 등록일</td>
											<td id="detail_regdate"></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>		
		</div>
	</div>
</body>
<script type="text/javascript">
window.onload = function(){
	   var inputImage = $('#inputImage');
	   var addBtn = $('#addBtn');
	   list_render();
	   let img_src;
	   
	   //이미지
	   inputImage.on("change",function(event){
	         var files = event.target.files;
	         var file = files[0];
	         var reader = new FileReader();
	         img_src = "/resources/car/"+file.name;
	         $("#carImage").attr("src",img_src);    // attr 값변경 속성변경
	      });
	   
	   addBtn.on("click", function(){
		   let prodImageFile = $("#inputImage")[0].files;
		   let prodId = $("#prodId").val();
		   let prodName = $("#prodName").val();
		   let prodPrice = $("#prodPrice").val();
		   let prodColor = $("input[name=prodColor]:checked").val();
		   let prodDescription = $("#prodDescription").val();

		   let formData = new FormData();
		   formData.append("prodId", prodId);
		   formData.append("prodName", prodName);
		   formData.append("prodPrice", prodPrice);
		   formData.append("prodColor", prodColor);
		   formData.append("prodDescription", prodDescription);
		   formData.append("prodImageFile", prodImageFile[0]);
		   formData.append("prodThumbnail", img_src);

		   $.ajax({
			  url : "/test03/upload" ,
			  type : "post",
			  contentType : false,
			  processData : false,
			  data : formData,
			  success : function(res){
					console.log(res);
					
					list_render();
			  }
		   });
	   });
}

function list_render(){
	   $.ajax({
			url : "/test03/list",
	   		type : "post",
	   		success : function(res){
				console.log(res);
				
	   			let tbody_html = "";
				if(res != null){
					res.forEach((c) => {
						tbody_html +=`
							<tr>
								<td>\${c.prodNo}</td>
								<td>\${c.prodId}</td>
								<td>\${c.prodName}</td>
								<td>\${c.prodPrice}</td>
								<td>\${c.prodColor}</td>
								<td>\${c.prodRegDate}</td>
							</tr>`;
					});
					document.querySelector("#listBody").innerHTML = tbody_html;
				   	document.querySelector('#listBody').querySelectorAll('tr').forEach((tr) => {
					  	tr.addEventListener('click', function(){
							fn_select(this.children[1].innerText);
					  }); 
				   });
				}
	   		}
	   });
};

function fn_select(prodId){
	$.ajax({
		url : "/test03/select",
   		type : "post",
   		data : {prodId:prodId},
   		success : function(res){
			console.log(res);
			$('#detail_img').attr("src",res.prodThumbnail);
			$('#detail_id').text(res.prodId);
			$('#detail_name').text(res.prodName);
			$('#detail_price').text(res.prodPrice);
			$('#detail_color').text(res.prodColor);
			$('#detail_regdate').text(res.prodRegDate);
			
   		}
	});
};


</script>



<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</html>












