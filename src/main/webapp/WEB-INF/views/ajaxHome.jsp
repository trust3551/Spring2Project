<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>Ajax Home</h1>
	
	<form action ="" method="get">
		boardNo : <input type="text" name="boardNo" id="boardNo"/><br/>
		title : <input type="text" name = "title" id="title"/><br/>
		content : <textarea rows="10" cols="10" name="content" id="content"></textarea><br/>
		writer : <input type="text" name ="writer" id="writer"><br/>
		<input type="button" id="btn" value="전송"/><br/> 
	</form>
	<br/>
<div>
	<h3>Headers 매핑</h3>
	<button id="putBtn">MODIFY(PUT)</button>
	<button id ="putHeadersBtn">MODIFY(PUT With Headers)</button>

	<h3>Content Type 매핑</h3>	
	<button id="postBtn">MODIFY(POST)</button>
	<button id="putJsonBtn">MODIFY(PUT JSON)</button>
	<button id="putXmlBtn">MODIFY(PUT XML)</button>

	<h3>Accept 매핑</h3>	
	<button id="getBtn">READ</button>
	<button id="getJsonBtn">READ(JSON)</button>
	<button id="getXmlBtn">READ(XML)</button>
</div>
</body>
<script type="text/javascript">
$(function(){
	var putBtn = $("#putBtn");
	var putHeadersBtn = $("#putHeadersBtn");
	
	// conetent type 매핑
	var postBtn = $("#postBtn");
	var putJsonBtn = $("#putJsonBtn");
	var putXmlBtn = $("#putXmlBtn");

	// aceept 매핑
	var getBtn = $("#getBtn");
	var getJsonBtn = $("#getJsonBtn");
	var getXmlBtn = $("#getXmlBtn");
	
	putBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
		}
		
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "put",
			data : JSON.stringify(boardObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log("result : " + result);
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		});
		
		putHeadersBtn.on("click",function(){
			var boardNo = $("#boardNo").val();
			var title = $("#title").val();
			var content = $("#content").val();
			var writer = $("#writer").val();
			
			var boardObject = {
				boardNo : boardNo,
				title : title,
				content : content,
				writer : writer
			};
			
			$.ajax({
				url : "/board/" + boardNo,
				type : "put",
				data : JSON.stringify(boardObject),
				headers : {
					"X-HTTP-Method-Override" : "PUT"
				},
				contentType : "application/json; charset=utf-8",
				success : function(result){
					console.log("result : " + result );
					if(result === "SUCCESS"){
						alert(result);
					}
				}
			});
		});
	});
	
	// Content Type 매핑 이벤트
	postBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
		}
		
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "post",
			data : JSON.stringify(boardObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log("result : " + result);
				// '=='는 Equal Operator, '==='는 Strict Equal Operator로 '==='는 값을 더 엄격하게 비교할때 사용한다.
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		});
	});
		
	putJsonBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
		}
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "put",
			data : JSON.stringify(boardObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log("result : " + result);
				// '=='는 Equal Operator, '==='는 Strict Equal Operator로 '==='는 값을 더 엄격하게 비교할때 사용한다.
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		});
	});	
	
	
	putXmlBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var xmlData = "";
		xmlData +="<Board>";
		xmlData +="<boardNo>" + boardNo + "</boardNo>";
		xmlData +="<title>" + title + "</title>";
		xmlData +="<content>" + content + "</content>";
		xmlData +="<writer>" + writer + "</writer>";
		xmlData +="</Board>";
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "put",
			data : xmlData,
			contentType : "application/xml; charset=utf-8",
			success : function(result){
				console.log("result : " + result);
				// '=='는 Equal Operator, '==='는 Strict Equal Operator로 '==='는 값을 더 엄격하게 비교할때 사용한다.
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		});
	});	
	
	// accept 매핑 이벤트
	getBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		
		// GET 방식 비동기 HTTP 요청 수행
		$.get("/board/" + boardNo, function(data){
			console.log(data);
			alert(JSON.stringify(data));
		});
	});
	
	getJsonBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		
		$.ajax({
			url : "/board/" + boardNo,
			type : "get",
			headers: {
				"Accept" : "application/json"
			},
			success: function(result){
				console.log("result : " + result);
				alert(JSON.stringify(result));
			}
		});
	});
	
	getXmlBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		
		$.ajax({
			type : "get",
			url : "/board/" + boardNo,
			headers : {
				"Accept" : "application/xml"
			},
			success : function(result){
				console.log("result : " + result);
				alert(xmlToString(result));
			}
		});
	});
});
function xmlToString(xmlData){
	var xmlString;
	
	// window.ActiveXObject는 ActiveXObject를 지원하는 브라우저면
	// Object를 리턴하고 그렇지 않다면 null을 리턴합니다.
	
	if(window.ActiveXObject){
		xmlString = xmlData.xml;
	}else{
		xmlString = (new XMLSerializer()).serializeToString(xmlData);
	}
	return xmlString;
}
</script>
</html>