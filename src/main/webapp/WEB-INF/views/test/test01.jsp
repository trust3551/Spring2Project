<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
   <div class="container">
      <div class="row pt-4">
         <select class="form-control" id="selectImgType">
            <option value="all">전체</option>
            <option value="jpg">JPG</option>
            <option value="png">PNG</option>
            <option value="gif">GIF</option>
         </select>
      </div>
      <div class="row pt-4">
         <div class="col-md-12">
            <div class="card">
               <div class="card-header">
                  <div class="card-title">
                     배운거 연습해보기
                  </div>
               </div>
               <div class="card-body">
                  <div class="row" id="imageArea">
                  
                     <c:choose>
                        <c:when test="${empty imageFileList }">
                           <h3>이미지가 존재하지 않습니다!</h3>
                        </c:when>
                        <c:otherwise>
                           <c:forEach items="${imageFileList }" var="item">
                              <div class="col-md-3">
                                 <div class="card">
                                    <div class="card-header">${item }</div>
                                    <div class="card-body">
                                       <img src="${pageContent.request.contextPath }/resources/image/${item}" style="width:100%;"/>
                                    </div>
                                    <div class="card-footer">
                                       <button class="btn btn-primary" >다운로드</button>
                                    </div>
                                 </div>
                               </div>
                           </c:forEach>
                        </c:otherwise>
                     </c:choose>   
                  </div>            
               </div>
            </div>
         </div>
      </div>
   </div>   
</body>
<script type="text/javascript">
$(function(){
   var selectImgType = $("#selectImgType");
   var imageArea = $("#imageArea");
   
   selectImgType.on("change", function(){
      let selectedValue = $(this).val(); // 내가 선택한 selectbox의 값
      console.log(selectedValue);
      
      let data = {
         type : selectedValue
      };
      
      $.ajax({
         url : "/test/changeImage.do",
         type : "post",
         contentType : "application/json; charset=utf-8",
         data : JSON.stringify(data),
         success : function(res) {
            console.log("res : " + res);
            
            let html = "";
            res.map(function(v,i) {
               html += "<div class='col-md-3'>";
               html += "   <div class='card'>";
               html += "      <div class='card-header'>" + v + "</div>";
               html += "      <div class='card-body'>";
               html += "         <img src='${pageContext.request.contextPath}/resources/image/" + v + "'";
               html += "         alt='" + v + "' style='width:100%;' data-filename='" + v + "'/>";
               html += "      </div>";
               html += "		<div class='card-footer'>";
               html += "		<button class='btn btn-primary' >다운로드</button>";
               html += "		</div>";
               html += "   </div>"
               html += "</div>"
            });
            imageArea.html(html);
         }
      });
      
   });
});

$(function(){
	$("#clickImg").on("click", function() {
        
    });
});
</script>
</html>







