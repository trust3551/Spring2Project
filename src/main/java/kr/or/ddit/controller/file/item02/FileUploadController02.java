package kr.or.ddit.controller.file.item02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.service.IItemService;
import kr.or.ddit.service.IItemService2;
import kr.or.ddit.vo.Item;
import kr.or.ddit.vo.Item2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item2")
public class FileUploadController02 {

	/*
	 * 	3. 여러 개의 이미지 업로드
	 * 
	 * 		- 한 번에 여러 개의 이미지를 업로드 하는 파일 업로드 기능을 구현한다.
	 * 
	 * 			# 데이터 베이스 만들기
	 * 			- item2 테이블
	 * 
	 * 			# 파일 업로드 구현 설명
	 * 				- 파일 업로드 등록 화면 컨트롤러 만들기(FileUploadController02)
	 * 				- 파일 업로드 등록 화면 컨트롤러 메소드 만들기(item2RegisterForm:get)
	 * 				- 파일 업로드 등록 화면 만들기(item2/register.jsp)
	 * 				- 여기까지 확인
	 * 
	 * 				- 파일 업로드 등록 기능 컨트롤러 메소드 만들기 (item2Register:post)
                	- 파일 업로드 등록 기능 서비스 인터페이스 메소드 만들기
                	- 파일 업로드 등록 기능 서비스 클래스 메소드 만들기
                	- 파일 업로드 등록 기능 Mapper 인터페이스 메소드 만들기
                	- 파일 업로드 등록 기능 Mapper xml 쿼리 만들기
                	- 파일 업로드 등록 완료 페이지 만들기 (item2/success.jsp)
                	- 여기까지 확인
                	
                	- 파일 업로드 목록 화면 컨트롤러 메소드 만들기 (item2List:get)
	                - 파일 업로드 목록 화면 서비스 인터페이스 메소드 만들기
	                - 파일 업로드 목록 화면 서비스 클래스 메소드 만들기
	                - 파일 업로드 목록 화면 Mapper 인터페이스 메소드 만들기
	                - 파일 업로드 목록 화면 Mapper xml 쿼리 만들기
	                - 파일 업로드 목록 화면 만들기 (item2/list.jsp)
	                - 여기까지 확인
	               
	                - 파일 업로드 수정 화면 컨트롤러 메소드 만들기 (item2ModifyForm:get)
	                - 파일 업로드 수정 화면 서비스 인터페이스 메소드 만들기
	                - 파일 업로드 수정 화면 서비스 클래스 메소드 만들기
	                - 파일 업로드 수정 화면 Mapper 인터페이스 메소드 만들기
	                - 파일 업로드 수정 화면 Mapper xml 쿼리 만들기
	                - 파일 업로드 수정 화면 만들기 (item2/modify.jsp)
	                - 여기까지 확인
	                
	                - 파일 업로드 수정 기능 컨트롤러 메소드 만들기 (item2Modify:post)
	                - 파일 업로드 수정 기능 서비스 인터페이스 메소드 만들기
	                - 파일 업로드 수정 기능 서비스 클래스 메소드 만들기
	                - 파일 업로드 수정 기능 Mapper 인터페이스 메소드 만들기
	                - 파일 업로드 수정 기능 Mapper xml 쿼리 만들기
	                - 여기까지 확인
	                
	                - 파일 업로드 삭제 화면 컨트롤러 메소드 만들기 (item2RemoveForm:post)
	                - 파일 업로드 삭제 화면 서비스 인터페이스 메소드 만들기
	                - 파일 업로드 삭제 화면 서비스 클래스 메소드 만들기
	                - 파일 업로드 삭제 화면 Mapper 인터페이스 메소드 만들기
	                - 파일 업로드 삭제 화면 Mapper xml 쿼리 만들기
	                - 파일 업로드 삭제 화면 만들기 (item2/remove.jsp)
	                - 여기까지 확인
	                
	                - 파일 업로드 삭제 기능 컨트롤러 메소드 만들기 (item2Remove:post)
	                - 파일 업로드 삭제 기능 서비스 인터페이스 메소드 만들기
	                - 파일 업로드 삭제 기능 서비스 클래스 메소드 만들기
	                - 파일 업로드 삭제 기능 Mapper 인터페이스 메소드 만들기
	                - 파일 업로드 삭제 기능 Mapper xml 쿼리 만들기
	                - 여기까지 확인
	 */
	
	   @Resource(name = "uploadPath")
	   private String resourcePath;
	
	   @Inject
	   private IItemService2 itemService;
	   
	   
	@GetMapping("/register")
	public String item2RegisterForm() {
		log.info("item2RegisterForm() 실행...!");
		return "/item2/register";
	}
	
	@PostMapping("/register")
	public String item2Register(Item2 item, Model model) throws Exception {
		List<MultipartFile> pictures = item.getPictures();
		
		for(int i = 0; i<pictures.size(); i++) {
			MultipartFile file = pictures.get(i);
			
			log.info("#fileName : =" + file.getOriginalFilename());
			
			//UUID_원본파일명
			String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());	// 파일 업로드

			if(i==0) {		// 첫번째 파일의 URL 정보
				item.setPictureUrl(savedName);
			}else {			// 두번쨰 파일의 URL> 정보
				item.setPictureUrl2(savedName);
			}
		}
		
		itemService.register(item);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "item2/success";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Item2> itemList = itemService.list();
		model.addAttribute("itemList", itemList);
		return "item2/list";
	}
	
	// 수정화면
	@GetMapping("/modify")
	public String item2ModifyForm(int itemId, Model model) {
		Item2 item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "item2/modify";
	}
	
	@PostMapping("/modify")
	public String item2Modify(Item2 item, Model model) throws Exception {
		List<MultipartFile> pictures = item.getPictures();
		
		for(int i = 0; i < pictures.size(); i++){
			MultipartFile file = pictures.get(i);
			
			if(file != null && file.getSize() > 0) {
				log.info("# fileName : " + file.getOriginalFilename());
				
				String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
				if(i == 0) {
					item.setPictureUrl(savedName);
				}else {
					item.setPictureUrl2(savedName);
					
				}
			}
		}
		
		itemService.modify(item);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "item2/success";
		
	}
	
	// 삭제화면
	   @GetMapping("/remove")
	   public String item2RemoveForm(int itemId, Model model) {
		   log.info("itemRemoveForm() 실행 ...!");
		   Item2 item = itemService.read(itemId);
		   model.addAttribute("item", item);
		   return "item2/remove";
	   }

	   
	   // 삭제 기능
	   @PostMapping("/remove")
	   public String item2Remove(int itemId, Model model) {
		   log.info("itemRemove() 실행 ...!");
		   itemService.remove(itemId);
		   model.addAttribute("msg", "삭제가 완료되었습니다.");
		   return "item2/success";
	   }
	
	@ResponseBody
	   @GetMapping("/display2")
	   public ResponseEntity<byte[]> displayFile2(int itemId) throws Exception{
		   InputStream in = null;
		   ResponseEntity<byte[]> entity = null;
		   
		   String fileName = itemService.getPicture2(itemId);
		   log.info(" # fileName : " + fileName);
		   
		   try {
			   String formatName = fileName.substring(fileName.lastIndexOf(".") + 1 ); // 확장자 추출
			   // 파일 확장자에 알맞는 MediaType 가져오기
			   MediaType mType = getMediaType(formatName);
			   HttpHeaders headers = new HttpHeaders();
			   
			   in = new FileInputStream(resourcePath + File.separator + fileName);
			   if(mType != null) {
				   headers.setContentType(mType);
			   }
			   entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		   }catch(Exception e) {
			   e.printStackTrace();
			   entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		   }finally {
			   if(in != null) {
				   in.close();
			   }
		   }
		   return entity;
	   }
	   
	   @ResponseBody
	   @GetMapping("/display")
	   public ResponseEntity<byte[]> displayFile(int itemId) throws Exception{
		   InputStream in = null;
		   ResponseEntity<byte[]> entity = null;
		   
		   String fileName = itemService.getPicture(itemId);
		   log.info(" # fileName : " + fileName);
		   
		   try {
			   String formatName = fileName.substring(fileName.lastIndexOf(".") + 1 ); // 확장자 추출
			   // 파일 확장자에 알맞는 MediaType 가져오기
			   MediaType mType = getMediaType(formatName);
			   HttpHeaders headers = new HttpHeaders();
			   
			   in = new FileInputStream(resourcePath + File.separator + fileName);
			   if(mType != null) {
				   headers.setContentType(mType);
			   }
			   entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		   }catch(Exception e) {
			   e.printStackTrace();
			   entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		   }finally {
			   if(in != null) {
				   in.close();
			   }
		   }
		   return entity;
	   }
	   
	   // 확장자에 알맞는 미디어타입 가져오기
	   private MediaType getMediaType(String formatName) {
		   if(formatName != null) {
			   if(formatName.toUpperCase().equals("JPG")) {
				   return MediaType.IMAGE_JPEG;
			   }
			   if(formatName.toUpperCase().equals("GIF")) {
				   return MediaType.IMAGE_GIF;
			   }
			   if(formatName.toUpperCase().equals("PNG")) {
				   return MediaType.IMAGE_PNG;
			   }
		   }
		   return null;
	}
	
	
	// 파일 업로드
	   private String uploadFile (String originalFilename, byte[] bytes) throws Exception {
	      log.info("uploadFile in!");
	      UUID uuid = UUID.randomUUID();   // UUID로 파일 생성
	      // UUID_원본파일명
	      String createdFileName = uuid.toString() + "_" + originalFilename;
	      
	      File file = new File(resourcePath);
	      if (!file.exists()) {
	         file.mkdirs();
	      }
	      File target = new File(resourcePath, createdFileName);    // 파일 업로드 준비
	      FileCopyUtils.copy(bytes, target);       // 파일 복사
	      
	      return createdFileName;
	   }
}
