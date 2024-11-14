package kr.or.ddit.controller.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private List<String> imageList;
	
	@PostConstruct
	public void init() {
		String[] imageFileName = {
			"audi01.png",
			"audi02.png",
			"audi03.png",
			"audi04.png",
			"bmw.png",
			"bmw01.jpg",
			"bmw02.jpg",
			"bmw03.jpg",
			"bmw04.jpg",
			"bmw05.jpg",
			"gif01.gif",
			"gif02.gif",
			"gif03.gif",
			"gif04.gif",
			"jeep01.jpg",
			"jeep02.jpg",
			"jeep03.jpg",
			"jeep04.jpg",
			"jeep05.jpg",
			"jeep06.jpg"
		};
		imageList = new ArrayList<String>();
		for(int i = 0; i < imageFileName.length; i++) {
			imageList.add(imageFileName[i]);
		}
	}
	
	@RequestMapping(value="/test01.do")
	public String test(Model model) {
		model.addAttribute("imageFileList", imageList);
		return "test/test01";
	}
	
	@RequestMapping(value="/changeImage.do", method = RequestMethod.POST)
	public ResponseEntity<List<String>> imageChange(@RequestBody Map<String, String> map){
		List<String> typeImageList = new ArrayList<String>();
		
		if(map.get("type").toString().equals("all")) {	// 전체
			typeImageList = imageList;
		}else {	// 전체가 아닌 각 확장자
			for(int i = 0; i < imageList.size(); i++) {
				if(imageList.get(i).contains(map.get("type"))) {
					String image = imageList.get(i);
					typeImageList.add(image);
				}
			}
		}
		return new ResponseEntity<List<String>>(typeImageList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{clickImg}", method = RequestMethod.GET)
	public ResponseEntity<byte[]>restHome1101(){
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		try {
			in = new FileInputStream("C:\\Users\\PC-14\\Desktop\\images\\som1.jpg");
			headers.setContentType(MediaType.IMAGE_JPEG);
			// IOUtils.toByteArray(in) : InputStream의 내용을 byte[]로 가져옵니다.
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			try {
				in.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
}
