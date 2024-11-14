package kr.or.ddit.controller.file.item03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.service.IItemService3;
import kr.or.ddit.vo.Item3;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item3")
public class FileUploadController03 {
	/*
	 * 	4. 비동기 방식 업로드
	 * 
	 * 		- 비동기 방식으로 여러 개의 이미지를 업로드 하는 파일 업로드 기능을 구현한다.
	 * 
	 * 			# 환경 설정
	 * 			
	 * 				- 의존 관계 정의(pom.xml)
	 * 				> commons-io			: 파일을 처리하기 위한 의존 라이브러리
	 * 				> imgscalr-lib			: 이미지 변환을 처리하기 위한 의존 라이브러리
	 * 				> jackson-databind		: json 데이터 바인딩을 위한 의존 라이브러리
	 * 				
	 * 			# 파일 업로드 구현 설명
	 * 
	 * 				- 파일업로드 등록 화면 컨트롤러 만들기(FileUploadController03)
	 * 				- 파일업로드 등록 화면 컨트롤러 메소드 만들기(item3RegisterForm:get)
	 * 				- 파일업로드 등록 화면 만들기(item3/register.jsp)
	 * 				- 여기까지 확인
	 * 
	 * 				천천히 차근히 작성해주세요
	 * 				- 파일업로드 등록 기능 컨트롤러 메소드 만들기(item3Register:post)
	 * 				- 파일업로드 등록 기능 서비스 인터페이스 메소드 만들기
	 *				- 파일업로드 등록 기능 서비스 클래스 메소드 만들기
	 *				- 파일업로드 등록 기능 Mapper 인터페이스 메소드 만들기
	 *				- 파입업로드 등록 기능 Mapper xml 쿼리 만들기
	 *				- 파일업로드 등록 완료 화면 만들기(item3/success.jsp)
	 *				- 여기까지 확인
	 *
	 *				- 파일업로드 목록 기능 컨트롤러 메소드 만들기(item3List:post)
	 * 				- 파일업로드 목록 기능 서비스 인터페이스 메소드 만들기
	 *				- 파일업로드 목록 기능 서비스 클래스 메소드 만들기
	 *				- 파일업로드 목록 기능 Mapper 인터페이스 메소드 만들기
	 *				- 파입업로드 목록 기능 Mapper xml 쿼리 만들기
	 *				- 파일업로드 목록 완료 화면 만들기(item3/list.jsp)
	 *				- 여기까지 확인
	 *
	 *				- 파일업로드 수정 화면 컨트롤러 메소드 만들기(item3ModifyForm:post)
	 * 				- 파일업로드 수정 화면 서비스 인터페이스 메소드 만들기
	 *				- 파일업로드 수정 화면 서비스 클래스 메소드 만들기
	 *				- 파일업로드 수정 화면 Mapper 인터페이스 메소드 만들기
	 *				- 파입업로드 수정 화면 Mapper xml 쿼리 만들기
	 *				- 파일업로드 수정 화면 화면 만들기(item3/modify.jsp)
	 *				- 여기까지 확인
	 *
	 *				- 파일업로드 수정 기능 컨트롤러 메소드 만들기(item3Modify:post)
	 * 				- 파일업로드 수정 기능 서비스 인터페이스 메소드 만들기
	 *				- 파일업로드 수정 기능 서비스 클래스 메소드 만들기
	 *				- 파일업로드 수정 기능 Mapper 인터페이스 메소드 만들기
	 *				- 파입업로드 수정 기능 Mapper xml 쿼리 만들기
	 *				- 파일업로드 수정 완료 화면 만들기(item3/modify.jsp)
	 *				- 여기까지 확인
	 *
	 */

	@Resource(name="uploadPath")
	private String resourcePath;
	
	@Inject
	private IItemService3 itemService;
	
	@GetMapping("/register")
	public String item3RegisterForm() {
		log.info("item3RegisterForm() 실행...!");
		return "item3/register";
	}
	
	@PostMapping("/register")
	public String item3Register(Item3 item, Model model) {
		String[] files = item.getFiles();
		
		for(String fileName : files) {
			log.info("files[] : " + fileName);
		}
		
		itemService.register(item); 	// 등록
		model.addAttribute("msg", "등록이 완료되었습니다!");
		return "item3/success";
	}
	
	@GetMapping("/list")
	public String item3List(Model model) {
		List<Item3> itemList = itemService.list();
		model.addAttribute("itemList", itemList);
		return "item3/list";
	}
	
	@GetMapping("/modify")
	public String item3ModifyForm(int itemId, Model model) {
		Item3 item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "item3/modify";
	}
	
	@ResponseBody
	@GetMapping("/getAttach/{itemId}")
	public List<String> getAttach(@PathVariable int itemId) {
		log.info("itemId : " + itemId);
		// item3_attch 테이블에서 fillname들의 목록 데이터를 리턴
		return itemService.getAttach(itemId);
	}
	
	@PostMapping("/modify")
	public String item3Modify(Item3 item,Model model) {
		itemService.modify(item);
		model.addAttribute("msg", "수정이 완료되었습니다!");
		return "item3/success";
	}
	
	@GetMapping("/remove")
	public String item3RemoveForm(int itemId, Model model) {
		Item3 item = itemService.read(itemId);
		model.addAttribute("item", item);
		return "item3/remove";
	}
	
	@PostMapping("/remove")
	public String item3Remove(int itemId, Model model) {
		itemService.remove(itemId);
		model.addAttribute("msg", "삭제가 완료되었습니다!");
		return "item3/success";
	}
	
	
	@ResponseBody
	@PostMapping(value = "/uploadAjax", produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		log.info("originalFileName : " + file.getOriginalFilename());
		
		// savedName은 /2024/09/09/UUID_원본파일명
		String savedName = UploadFileUtils.uploadFile(resourcePath, file.getOriginalFilename(), file.getBytes());
		return new ResponseEntity<String>(savedName, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> display(String fileName){
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1); // 확장자 추출
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(resourcePath + fileName); 	// 파일을 읽어온다.
			if(mType != null) {
				headers.setContentType(mType);
			}else {
				// 원본 파일명 추출
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\"" +
						new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
		
	}
}







