package kr.or.ddit.controller.crud.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class ImageUpload {
	
	// CKEDITOR를 이용하여 본문 내용에 선택 이미지 업로드하기
	@RequestMapping(value="/imageUpload.do")
	public String imageUpload(
			HttpServletRequest req, HttpServletResponse resp,
			MultipartHttpServletRequest multiFile
			) throws Exception {
		// CKEDITOR 특정 버전 이후부터 html 형식의 데이터를 리턴하는 방법에서 JSON 데이터를 구성해서 리턴하는 방식으로 변경됨
		JsonObject json = new JsonObject(); 	// JSON 객체를 만들기 위한 준비
		PrintWriter printWriter = null;			// 외부 응답으로 내보낼 때 사용할 객체
		OutputStream out = null; 				// 본문내용에 추가한 이미지를 파일로 생성할 객체
		long limitSize = 1025 * 1024 * 2;		// 업로드 파일 최대 크기(2MB)
		
		MultipartFile file = multiFile.getFile("upload"); 	// upload라는 키로 MultipartFile 타입의 파일 데이터를 꺼낸다.
		
		// 파일 객체가 null이 아니고 파일 사이즈가 0보다 크고 파일명이 공백이 아닌경우는 무조건 파일 데이터가 존재하는 경우
		if(file != null && file.getSize() > 0 && StringUtils.isNotBlank(file.getOriginalFilename())) {
			// 데이터 Mime 타입이 'image/'를 포함한 이미지 파일인지 체크
			if(file.getContentType().toLowerCase().startsWith("image/")){
				// 업로드 한 파일 사이즈가 최대 크기 (2MB) 보다 클때
				if(file.getSize() > limitSize ) {	// 크기 초과 에러
					/*
					 * {
					 * 		"uploaded" : 0,
					 * 		"error" : [
					 * 			{
					 * 				"message" : "2MB미만의 이미지만 업로드 가능합니다."
					 * 			}
					 * 		]
					 * }
					 * 에러가 발생했을 때 출력 형태를 위와 같은 형식으로 만든다.
					 */
					JsonObject jsonMsg = new JsonObject();
					JsonArray jsonArr = new JsonArray();
					jsonMsg.addProperty("message", "2MB미만의 이미지만 업로드 가능합니다.");
					jsonArr.add(jsonMsg);
					json.addProperty("uploaded", 0);
					json.add("error", jsonArr.get(0));
					
					resp.setCharacterEncoding("UTF-8");
					printWriter = resp.getWriter();
					printWriter.println(json);
			}else {									// 정상 범위 안에있는 파일
				/*
				 * {
				 * 		"uploaded" : 1,
				 * 		"fileName" : "xxxxx-xxxxx.jpg",
				 * 		"url" : "/resources/img/xxxxx-xxxxx.jpg"
				 * }
				 * 
				 */
				try {
					String fileName = file.getOriginalFilename(); 	// 파일명 얻어오기
					byte[] bytes = file.getBytes();		// 파일 데이터 얻어오기
					// 업로드 경로 설정(배포 서버 안에 들어있는)
					String uploadPath = req.getServletContext().getRealPath("/resources/img");
					File uploadFile = new File(uploadPath);
					// 업로드 경로로 설정된 폴더구조가 존재하지 않는 경우, 파일을 복사할 수 없으므로 폴더 구조가 존재하지 않는 경우
					// 해당 위치 폴더를 생성하고 존재하는 경우 건너뛰도록 한다.
					if(!uploadFile.exists()) {
						uploadFile.mkdirs();
					}
					
					// UUID_ 원본 파일명
					fileName = UUID.randomUUID().toString() + "_" + fileName;
					uploadPath = uploadPath + "/" + fileName; 		// 업로드 경로 + 파일명
					out = new FileOutputStream(new File(uploadPath));
					out.write(bytes);	// 파일 복사
					
					printWriter = resp.getWriter();
					String fileUrl = req.getContextPath() + "/resources/img/" + fileName;
					json.addProperty("uploaded", 1);
					json.addProperty("fileName", fileName);
					json.addProperty("url", fileUrl);
					
					printWriter.println(json);
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(out != null) {
						out.close();
					}
					if(printWriter != null) {
						printWriter.close();
					}
				}
			}
			}
		}
		return null;
	}
}
