<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
   integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
   crossorigin="anonymous">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
   <!--
       문제) input type="file" 을 통해서 Open된 File선택란에서 파일을 선택합니다.
       이때, 이미지면 이미지 썸네일 포함되어 있는 카드가 결과영역에 출력되고
       이미지가 아니면 파일명만 포함되어 있는 카드가 띄어져야 합니다.
      
      [필 수 이 벤 트 ]
      - 비동기 통신을 이용해서 내가 넘긴 파일명을 서버에 출력하고 파일명을 응답으로 내보냅니다.
      - 응답으로 받은 파일명은 카드에 출력합니다.
   
   -->


   <div class="row">
      <div class="col-md-12">
         <div class="row">
            <div class="col-md-4">
               <div class="card">
                  <div class="card-header">
                     <div class="card-title">Ajax 통신을 이용한 데이터 주고받기 연습해보기</div>
                  </div>
                  <div class="card-body">
                     <h5>파일을 선택 후, 결과창에 출력해주세요.</h5>
                     <input type="file" id="inputFile" name="inputFile" />
                  </div>
               </div>
            </div>
            <div class="col-md-8">
               <div class="row" id="test">
               </div>
            </div>
         </div>
      </div>
   </div>
</body>
<script type="text/javascript">
$(function(){
   
   var inputFile =$('#inputFile'); 
   var test= $("#test"); // 나타나는 부분
   var html = "";
   
   inputFile.on("change", function(event){
      var files = event.target.files;
      var file = files[0];
      var formData = new FormData();
      formData.append("file",file);
         
      $.ajax({
         url : "/test/uploadFile",
         type : "post",
         contentType : false,
         processData : false,
         data : formData,
         dataType : "text", 
         success : function(res){
            var reader = new FileReader();
            reader.onload = function(e){

               if(res != "noImage"){
                  html += "<div class='col-md-3'>"; //col-md-3나눠줌
                  html += "   <div class='card'>";
                  html += "       <div class='card-header'>"+file.name+ "</div>";
                  html += "       <div class='card-body'>";
                  html += "          <img src='"+e.target.result+"'";
                  html += "         alt='#' style ='width:100%;' />";
                  html += "      </div>";
                  html += "   </div>";
                  html += "</div>";

                  test.html(html);
               }else{
                  html += "<div class='col-md-3'>"; //col-md-3나눠줌
                  html += "   <div class='card'>";
                  html += "       <div class='card-header'>"+file.name+ "</div>";
                  html += "       <div class='card-body'>";
                  html += "이미지가 아닙니다";
                  html += "      </div>";
                  html += "   </div>";
                  html += "</div>";

                  test.html(html);
               }
            }
            reader.readAsDataURL(file);
         },
         error: function(xhr){
            console.log(xhr);
         }
      });
   });
});
</script>
</html>