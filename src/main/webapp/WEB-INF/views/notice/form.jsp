<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri ="http://www.springframework.org/security/tags" prefix="sec" %>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>공지사항 등록/수정</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">DDIT HOME</a></li>
					<li class="breadcrumb-item active">공지사항 등록/수정</li>
				</ol>
			</div>
		</div>
	</div>

</section>

<c:set value="등록 " var="name"/>
<c:if test="${status eq 'u' }">
	<c:set value="수정" var="name"/>
</c:if>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="card card-dark">
				<div class="card-header">
					<h3 class="card-title">공지사항 ${name }</h3>
					<div class="card-tools"></div>
				</div>
				<form action="/notice/insert.do" method="post" id="noticeForm" enctype="multipart/form-data">
				<c:if test ="${status eq 'u' }">
					<input type="hidden" name = "boNo" value="${noticeVO.boNo }"/>
				</c:if>
				<div class="card-body">
					<div class="form-group">
						<label for="inputName">제목을 입력해주세요</label>
						<input type="text" id="boTitle" name="boTitle" class="form-control" 
						value="${noticeVO.boTitle }" placeholder="제목을 입력해주세요">
					</div>
					<div class="form-group">
						<label for="inputDescription">내용을 입력해주세요</label>
						<textarea id="boContent" name="boContent" class="form-control" rows="14">${noticeVO.boContent }</textarea>
					</div>
					<div class="form-group">
						<div class="custom-file">

							<input type="file" class="custom-file-input" id="customFile" name="boFile" multiple="multiple"> 
							<label class="custom-file-label" for="boFile">파일을 선택해주세요</label>
						</div>
					</div>
				</div>
		
				<c:if test = "${status eq 'u' }">
				<div class="card-footer bg-white">
					<c:if test = "${not empty noticeVO.noticeFileList }">
						<ul class="mailbox-attachments d-flex align-items-stretch clearfix">
							<c:forEach items="${noticeVO.noticeFileList }" var="noticeFile">
								<li>
									<span class="mailbox-attachment-icon">
										<i class="far fa-file-pdf"></i>
									</span>
	
									<div class="mailbox-attachment-info">
										<a href="#" class="mailbox-attachment-name">
											<i class="fas fa-paperclip"></i> ${noticeFile.fileName }
										</a> 
										<span class="mailbox-attachment-size clearfix mt-1"> 
											<span>${noticeFile.fileFancysize }</span> 
											<span class="btn btn-default btn-sm float-right attachmentFileDel" id="span_${noticeFile.fileNo }">
												<i class="fas fa-times"></i>
											</span>
										</span>
									</div>
								</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
				</c:if>
				<div class="card-footer bg-white">
					<div class="row">
						<div class="col-12">
							<input type="button" value="${name }" id="addBtn" class="btn btn-secondary float-right"> 
							<c:if test = "${status eq 'u' }">
							<input type="button" value="취소" id="cancelBtn" class="btn btn-dark float-right">
							</c:if>
							<c:if test = "${status ne 'u' }">
							<input type="button" value="목록" id="listBtn" class="btn btn-secondary float-right"> 
							</c:if>
						</div>
					</div>
				</div>
				<sec:csrfInput/>
				</form>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
$(function(){
	CKEDITOR.replace("boContent",{
		filebrowserUploadUrl: "/imageUpload.do?${_csrf.parameterName}=${_csrf.token}"
	});
	CKEDITOR.config.height = "600px";	// CKEDITOR 높이 설정
	
	var listBtn = $("#listBtn");		// 목록 버튼
	var addBtn = $("#addBtn");			// 등록 버튼
	var cancelBtn = $("#cancelBtn");	// 취소 버튼
	var noticeForm = $("#noticeForm");  // 등록 Form
	var attachmentFileDel = $(".attachmentFileDel");
	
	// 등록 버튼 이벤트
	addBtn.on("click", function(){
		var title = $("#boTitle").val();		// 일반적인 input 요소를 이용한 값 얻어오기
		var content = CKEDITOR.instances.boContent.getData(); 	// CKEDITOR를 이용한 값 얻어오기
		
		if(title == null || title ==""){
			alert("제목을 입력해주세요!");
			return false;
		}
		if(content == null || content ==""){
			alert("내용을 입력해주세요!");
			return false;
		}
		
		if($(this).val() == "수정"){
			noticeForm.attr("action", "/notice/update.do");
		}
		
		noticeForm.submit();
	});
	
	cancelBtn.on("click", function(){
		location.href = "/notice/detail.do?boNo=${noticeVO.boNo}";
	});
	
	
	// 목록 버튼 이벤트
	listBtn.on("click", function(){
		location.href = "/notice/list.do";
	});
	
	// 'x'버튼 클릭 이벤트
	attachmentFileDel.on("click", function(){
		var id = $(this).prop("id");
		var idx = id.indexOf("_"); 		// span_fileNo에서 '_'의 현재 idx위치를 가져온다.
		var noticeFileNo = id.substring(idx + 1);
		var ptrn = "<input type='hidden' name='delNoticeNo' value='%V'/>";
		$("#noticeForm").append(ptrn.replace("%V", noticeFileNo));
		$(this).parents("li:first").hide();
	});
});
</script>