<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix="sec" %>

<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1>공지사항 상세보기</h1>
			</div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">DDIT HOME</a></li>
					<li class="breadcrumb-item active">공지사항 상세보기</li>
				</ol>
			</div>
		</div>
	</div>
</section>


<section class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-dark">
					<div class="card-header">
						<h3 class="card-title">${noticeVO.boTitle }</h3>
						<div class="card-tools">${noticeVO.boWriter } ${noticeVO.boDate } ${noticeVO.boHit }</div>
					</div>
					<form id="quickForm" novalidate="novalidate">
						<div class="card-body">${noticeVO.boContent }</div>
						<div class="card-footer bg-white">
						
							<c:if test="${not empty noticeVO.noticeFileList }">
							<ul class="mailbox-attachments d-flex align-items-stretch clearfix">
								<c:forEach items="${noticeVO.noticeFileList }" var="noticeFile">
								<li>
									<span class="mailbox-attachment-icon"> 
									<i class="far fa-file-pdf"></i>
								</span>
									<div class="mailbox-attachment-info">
										<a href="#" class="mailbox-attachment-name"> 
										<i class="fas fa-paperclip"></i> ${noticeFile.fileName }
										</a> <span class="mailbox-attachment-size clearfix mt-1"> 
											<span>${noticeFile.fileFancysize }</span>
											<c:url value ="/notice/download.do" var = "downloadUrl">
												<c:param name="fileNo" value="${noticeFile.fileNo }"/>
											</c:url> 
												<a href="${downloadUrl }"> 
													<span class="btn btn-default btn-sm float-right"> 
														<i class="fas fa-download"></i>
											</span>
										</a>
										</span>
									</div>
								</li>
								</c:forEach>
							</ul>
							</c:if>
						</div>
						<div class="card-footer">
							<button type="button" id ="listBtn" class="btn btn-secondary">목록</button>
							<!-- 세션을 이용한 방법1 -->
<%-- 							<c:if test="${sessionScope.SessionInfo.memId eq noticeVO.boWriter}"> --%>
<!-- 								<button type="button" id ="udtBtn" class="btn btn-dark">수정</button> -->
<!-- 								<button type="button" id ="delBtn" class="btn btn-danger">삭제</button> -->
<%-- 							</c:if>  --%>
							<!--  시큐리티를 이용한 방법2 -->
							<sec:authentication property="principal.member" var="member"/>
							<c:if test="${member.memId eq noticeVO.boWriter or member.memId eq 'admin' }">
								<button type="button" id ="udtBtn" class="btn btn-dark">수정</button>
								<button type="button" id ="delBtn" class="btn btn-danger">삭제</button>
							</c:if>
						</div>
						
					</form>
				</div>
			</div>
			<div class="col-md-6"></div>
			<form action="/notice/delete.do" method="post" id="delForm">
				<input type="hidden" name="boNo" value="${noticeVO.boNo }"/>
				<sec:csrfInput/>
			</form>
		</div>
	</div>
</section>
<script type="text/javascript">
$(function(){
	var delForm = $("#delForm");	// 수정 및 삭제 공유할 Form
	var listBtn = $("#listBtn");	// 목록버튼
	var udtBtn = $("#udtBtn");	// 수정버튼
	var delBtn = $("#delBtn");	// 삭제버튼
	
	// 목록 버튼 이벤트
	listBtn.on("click", function(){
		location.href = "/notice/list.do";
	});
		
	// 수정 버튼 이벤트
	udtBtn.on("click", function(){
		delForm.attr("action", "/notice/update.do");
		delForm.attr("method", "get");
		delForm.submit();
	});
	
	// 삭제 버튼 이벤트
	delBtn.on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			delForm.submit();
		}
	});
});
</script>