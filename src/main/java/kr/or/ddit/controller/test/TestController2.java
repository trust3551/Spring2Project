package kr.or.ddit.controller.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController2 {
   
   @RequestMapping(value = "/ajaxTest", method = RequestMethod.GET )
   public String TestFileForm() {
      log.info("TestFileForm()... 실행");
      return "test/test02";
   }
   
   
   @ResponseBody
   @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
   public ResponseEntity<String> uploadFile(MultipartFile file){
      if(file.getContentType().contains("image")) {  //contains
         return new ResponseEntity<String>(file.getOriginalFilename(), HttpStatus.OK);
      }else {
         return new ResponseEntity<String>("noImage" , HttpStatus.OK);
      }
   }
}
