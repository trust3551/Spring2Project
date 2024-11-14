package kr.or.ddit.controller.file.item01;

import java.io.File;
import java.io.FileInputStream;
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
import kr.or.ddit.vo.Item;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item")
public class FileUploadController01 {

   /*
       [ 13장 : 파일 업로드 ]
       
          1. 파일 업로드 설명
          
             - 서블릿 3.0에서 지원하는 파일 업로드 기능과 스프링 웹에서 제공하는 컴포넌트를 사용하여 파일을 업로드한다.
             
             # 파일 업로드 설정 
             1) 서블릿 3.0 이상 지원
             2) 서블릿 표준 파일 업로드 기능을 활성화
             3) 스프링 MVC와 연계
             4) 업로드 된 파일 저장 위치 설정
             
             # 환경 설정
             1) 의존 관계 정의
                - 파일을 처리하기 위해서 의존 라이브러리를 추가한다.
                - pom.xml commoms-io 추가
                
             2) 웹 컨테이너 설정
                > web.xml 서블릿 표준 버전 3.1로 설정
                > multipart-config 활성화 (web.xml의 servlet 태그 내 설정)
                
             3) 스프링 웹 설정
                > servlet-content.xml
                > multipartResolver Bean 등록 (id는 multipartResolver로 설정)
                
                - 파일 필터 있는지 확인
                
                [ 파일 업로드 설정 ]
                - 파일 업로드를 설정하는 방식은 2가지가 있다.
                
                (1) StandardServletMultipartResolver 사용 시 설정, 
                   > Servlet 3.0의 Part를 이용한 MultipartFile 데이터 처리
                        > servlet-context.xml에 설정
                           - StandardServletMultipartResolver Bean에 등록
                        > web.xml 설정
                           - multipart-config(servlet 태그 안에 설정)
         
                    (2) CommonsMultipartResolver 사용시 설정, <<< 이녀석이 우리가 사용할 녀석
                        > Commons FileUpload API를 이용한 MultipartFile 데이터 처리
                        > bean 속성으로 maxUploadSize, maxInMemorySize 및 defaultEncoding 설정을 제공합니다.
                        > 기본값 및 허용되는 값에 대한 자세한 내용은 각 DiskFileUpload 속성을 참조
                        
                        > pom.xml 설정
                           - commons-fileupload 추가
                           
                        > root-context.xml에 설정
                           - CommonsMultipartResolver bean 등록
         
                       > root-context.xml에 설정
                           - uploadPath bean 등록
            
                       > multipartFilter 등록
                           - web.xml

       
       
          2. 이미지 업로드
          
             - 한개의 이미지를 업로드 하는 기본 파일 업로드 기능을 구현합니다.
             
             # 파일 업로드 구현 설정
             
                - 파일 업로드 등록 화면 컨트롤러 만들기 (FileUploadController01)
                - 파일 업로드 등록 화면 컨트롤러 메소드 만들기 (itemRegisterForm:get)
                - 파일 업로드 등록 화면 만들기 (item/register.jsp)
                - 여기까지 확인
       
                -----------------------------------------------------
       
                - 파일 업로드 등록 기능 컨트롤러 메소드 만들기 (itemRegister:post)
                - 파일 업로드 등록 기능 서비스 인터페이스 메소드 만들기
                - 파일 업로드 등록 기능 서비스 클래스 메소드 만들기
                - 파일 업로드 등록 기능 Mapper 인터페이스 메소드 만들기
                - 파일 업로드 등록 기능 Mapper xml 쿼리 만들기
                - 파일 업로드 등록 완료 페이지 만들기 (item/success.jsp)
                - 여기까지 확인
                
                - 파일 업로드 목록 화면 컨트롤러 메소드 만들기 (itemList:get)
                - 파일 업로드 목록 화면 서비스 인터페이스 메소드 만들기
                - 파일 업로드 목록 화면 서비스 클래스 메소드 만들기
                - 파일 업로드 목록 화면 Mapper 인터페이스 메소드 만들기
                - 파일 업로드 목록 화면 Mapper xml 쿼리 만들기
                - 파일 업로드 목록 화면 만들기 (item/list.jsp)
                - 여기까지 확인
                
                - 파일 업로드 수정 화면 컨트롤러 메소드 만들기 (itemModifyForm:get)
                - 파일 업로드 수정 화면 서비스 인터페이스 메소드 만들기
                - 파일 업로드 수정 화면 서비스 클래스 메소드 만들기
                - 파일 업로드 수정 화면 Mapper 인터페이스 메소드 만들기
                - 파일 업로드 수정 화면 Mapper xml 쿼리 만들기
                - 파일 업로드 수정 화면 만들기 (item/modify.jsp)
                - 여기까지 확인
                
                - 파일 업로드 수정 기능 컨트롤러 메소드 만들기 (itemModify:post)
                - 파일 업로드 수정 기능 서비스 인터페이스 메소드 만들기
                - 파일 업로드 수정 기능 서비스 클래스 메소드 만들기
                - 파일 업로드 수정 기능 Mapper 인터페이스 메소드 만들기
                - 파일 업로드 수정 기능 Mapper xml 쿼리 만들기
                - 여기까지 확인
                
                - 파일 업로드 삭제 화면 컨트롤러 메소드 만들기 (itemRemoveForm:post)
                - 파일 업로드 삭제 화면 서비스 인터페이스 메소드 만들기
                - 파일 업로드 삭제 화면 서비스 클래스 메소드 만들기
                - 파일 업로드 삭제 화면 Mapper 인터페이스 메소드 만들기
                - 파일 업로드 삭제 화면 Mapper xml 쿼리 만들기
                - 파일 업로드 삭제 화면 만들기 (item/remove.jsp)
                - 여기까지 확인
                
                - 파일 업로드 삭제 기능 컨트롤러 메소드 만들기 (itemRemove:post)
                - 파일 업로드 삭제 기능 서비스 인터페이스 메소드 만들기
                - 파일 업로드 삭제 기능 서비스 클래스 메소드 만들기
                - 파일 업로드 삭제 기능 Mapper 인터페이스 메소드 만들기
                - 파일 업로드 삭제 기능 Mapper xml 쿼리 만들기
                - 여기까지 확인
       
    */
   
   // root-context.xml에서 설정한 uploadPath라는 이름으로 빈 등록된 value의 경로 값을 가져온다.
   // uploadPath안에 들어있는 문자열이 여기에 매핑됨
   @Resource(name = "uploadPath")
   private String resourcePath;
   
   @Resource(name = "localPath")
   private String localPath;
   
   @Inject
   private IItemService itemService;
   
   // 등록화면 요청
   @GetMapping("/register")
   public String itemRegisterForm() {
      log.info("itemRegisterForm() 실행!");
      return "/item/register";
   }
   
   @PostMapping("/register")
   public String itemRegister(Item item, Model model) throws Exception {
      log.info("itemRegister() 실행!");
      
      MultipartFile file = item.getPicture();
      log.info("OriginalFilename : " + file.getOriginalFilename());
      log.info("Size : " + file.getSize());
      log.info("ContentType : " + file.getContentType());
      
      // 넘겨받은 파일을 이용하여 파일 업로드를 진행한다.
      // return : UUID_원본파일명
      String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
      
      item.setPictureUrl(createdFileName);
      itemService.register(item);   // 일반 데이터 및 파일 데이터 DB 등록
      model.addAttribute("msg", "등록이 완료되었습니다!");
      
      return "item/success";
   }
   
   // 목록 
   @GetMapping("/list")
   public String itemList(Model model) {
	   log.info("itemList() 실행...!");
	   List<Item>itemList = itemService.list();
	   model.addAttribute("itemList", itemList);
	   return "item/list";
   }
   
   
   // 수정 화면
   @GetMapping("/modify")
   public String itemModifyForm(int itemId, Model model) {
	   	log.info("itemModifyForm() 실행...!");
	   	Item item = itemService.read(itemId);
	   	model.addAttribute("item", item);
	   	return "item/modify";
   }
   
   // 수정 화면
   @PostMapping("/modify")
   public String itemModify(Item item, Model model) throws Exception {
	   log.info("itemModify() 실행...!");
	   MultipartFile file = item.getPicture();
	   if(file != null && file.getSize() > 0) {
		   log.info("fileName : " + file.getOriginalFilename());
		   log.info("size : " + file.getSize());
		   log.info("contentType : " + file.getContentType());
		   
		   String createFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		   item.setPictureUrl(createFileName);
	   }
	   itemService.modify(item);
	   model.addAttribute("msg", "수정이 완료되었습니다.");
	   return "item/success";
   }
   
   // 삭제화면
   @GetMapping("/remove")
   public String itemRemoveForm(int itemId, Model model) {
	   log.info("itemRemoveForm() 실행 ...!");
	   Item item = itemService.read(itemId);
	   model.addAttribute("item", item);
	   return "item/remove";
   }

   
   // 삭제 기능
   @PostMapping("/remove")
   public String itemRemove(int itemId, Model model) {
	   log.info("itemRemove() 실행 ...!");
	   itemService.remove(itemId);
	   model.addAttribute("msg", "삭제가 완료되었습니다.");
	   return "item/success";
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
      
//      File file = new File(resourcePath);
//      if (!file.exists()) {
//         file.mkdirs();
//      }
//      File target = new File(resourcePath, createdFileName);    // 파일 업로드 준비
      
      // localPath 활용
      File file = new File(localPath);
      if (!file.exists()) {
    	  file.mkdirs();
      }
      File target = new File(localPath, createdFileName);    // 파일 업로드 준비
      FileCopyUtils.copy(bytes, target);       // 파일 복사
      
      return createdFileName;
   }
   
}
