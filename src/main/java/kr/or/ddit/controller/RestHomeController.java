package kr.or.ddit.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestHomeController {
	
	@RequestMapping(value="/goRestHome0301", method = RequestMethod.GET)
	public Member restHome0301() {
		log.info("restHome0301() 실행 ...!");
		return new Member();
	}
	
	@RequestMapping(value="/goRestHome0401", method = RequestMethod.GET)
	public List<Member> restHome0401(){
		log.info("restHome0401() 실행 ...!");
		List<Member> list = new ArrayList<Member>();
		Member member = new Member();
		Member member2 = new Member();
		list.add(member);
		list.add(member2);
		
		return list;
	}
	
	@RequestMapping(value="/goRestHome0501", method = RequestMethod.GET)
	public Map<String, Member> restHome0501(){
		log.info("restHome0501() 실행...!");
		Map<String, Member> map = new HashMap<String, Member>();
		Member member = new Member();
		Member member2 = new Member();
		map.put("key1", member);
		map.put("key2", member2);
		return map;
	}
	
	@RequestMapping(value = "/goRestHome0601", method = RequestMethod.GET)
	public ResponseEntity<Void> restHome0601(){
		log.info("restHome0601() 실행 ...!");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome0701", method = RequestMethod.GET)
	public ResponseEntity<String> restHome0701(){
		log.info("restHome0701() 실행 ...!");
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome0801", method = RequestMethod.GET)
	public ResponseEntity<Member> restHmoe0801(){
		log.info("restHome0801() 실행...!");
		Member member = new Member();
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome0901", method = RequestMethod.GET)
	public ResponseEntity<List<Member>> restHome0901(){
		log.info("restHome0901() 실행...!");
		List<Member> list = new ArrayList<Member>();
		Member member = new Member();
		Member member2 = new Member();
		list.add(member);
		list.add(member2);
		return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome1001", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Member>> restHome1001(){
		log.info("restHome1001() 실행 ...!");
		Map<String, Member> map = new HashMap<String, Member>();
		Member member = new Member();
		Member member2 = new Member();
		map.put("key1", member);
		map.put("key2", member2);
		
		return new ResponseEntity<Map<String,Member>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/goRestHome1101", method = RequestMethod.GET)
	public ResponseEntity<byte[]>restHome1101(){
		log.info("restHome1101() 실행 ...!");
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
	
	@RequestMapping(value="/goRestHome1102", method = RequestMethod.GET)
	public ResponseEntity<byte[]> restHome1102(){
		log.info("restHome1102() 실행...!");
		InputStream in = null;
		ResponseEntity<byte[]>entity = null;
		
		String fileName = "DDIT_downloadFile.jpg";
		HttpHeaders headers = new HttpHeaders();
		
		try {
			in = new FileInputStream("C:\\Users\\PC-14\\Desktop\\images\\som1.jpg");
			// MediaType.APPLICATION_OCTET_STREAM은 이진 파일을 위한 기본값입니다.
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);;
			headers.add("Content-Disposition", "attachment; filename=\"" +
					new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
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
