<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<body>
	<h2>REMOVE</h2>
	<hr/>
	<form action="/item3/remove" method="post"  id="addForm" enctype="multipart/form-data">
		<input type="hidden" name = "itemId" value="${item.itemId }"/>
		<table>
			<tr>
				<td> 상품명</td>
				<td><input type="text" name ="itemName" value="${item.itemName }" disabled="disabled"/></td>
			</tr>
			<tr>
				<td> 가격</td>
				<td><input type="text" name ="price" value="${item.price }" disabled="disabled"/></td>
			</tr>
			<tr>
				<td> 파일</td>
				<td>
					<div class="uploadedList"></div>
				</td>
			</tr>
			<tr>
				<td> 개요</td>
				<td><textarea rows="10" cols="30" name="description" disabled="disabled">${item.description }</textarea></td>
			</tr>
		</table>
		<div>
			<button type="button" id="addBtn">Remove</button>
			<button type="button" onclick ="javascript:location.href='/item3/list'">List</button>
		</div>	
	</form>
</body>
<script type="text/javascript">
$(function(){
	var uploadedList = $(".uploadedList");
	var addBtn = $("#addBtn");
	var addForm = $("#addForm");
	
	var itemId = ${item.itemId};
	console.log("item : " + itemId);
	
	
	$.getJSON("/item3/getAttach/" + itemId, function(list){
		$(list).each(function(){
			var str = "";
			var data = this;	// list안에 들어있는 데이터1개의 낱개 데이터
			
			if(checkImageType(data)){	// 이미지면 이미지태그를 이용하여 출력
				str += "<div>";
				str += "	<a href='/item3/displayFile?fileName=" + data + "'>";
				str += "		<img src ='/item3/displayFile?fileName=" + getThumbnailName(data) + "'/>";
				str += "	</a>";
				str += "</div>";
			}else{						// 파일이면 파일명에 대한 링크로만 출력
				str += "<div>";					
				str += "	<a href='/item3/displayFile?fileName=" + data +"'>" + getOriginalName(data) + "</a>";					
				str += "</div>";					
			}
			
			$(".uploadedList").append(str);
		});
	});
	
	addBtn.on("click", function(){
		addForm.submit();
	});
	
	// 임시 파일로 썸네일 이미지 만들기
	function getThumbnailName(fileName){
		var front = fileName.substr(0, 12); 	// /2024/09/09/	폴더경로
		var end = fileName.substr(12);			// 뒤 파일명
		
		console.log("front : " + front);
		console.log("end : " + end);
		return front + "s_" + end;
	}
	
	// 파일명 추출
	function getOriginalName(fileName){
		if(checkImageType(fileName)){
			return;
		}
		var idx = fileName.indexOf("_") + 1;
		return fileName.substr(idx);
	}
	
	// 이미지 파일인지 확인
	function checkImageType(fileName){
		var pattern = /jpg|gif|png|jpeg/i;
		return fileName.match(pattern);		// 패턴과 일치하면 true (이미지구나?)
	}
});

</script>
</html>