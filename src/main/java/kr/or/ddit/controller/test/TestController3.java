package kr.or.ddit.controller.test;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.controller.test.dao.ITest03DAO;
import kr.or.ddit.vo.test.ProductVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/test03")
public class TestController3 {
	
	@Inject
	private ITest03DAO Dao;

	@RequestMapping(value="/test03.do", method = RequestMethod.GET)
	public String test03() {
		log.info("TestController3() 실행..!");
		return "test/test03";
	}
	
	@ResponseBody
	@RequestMapping(value="/upload", method= RequestMethod.POST)
	public ResponseEntity<String> upload(ProductVO productVO){
		Dao.insertProduct(productVO);
		
		log.info(productVO.toString());
		
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 얘는 모든 리스트 가져오기(처음에 있었던 값)
	@RequestMapping(value="/list", method = RequestMethod.POST )
	public ResponseEntity<List<ProductVO>> list(){
		List<ProductVO> carList = Dao.selectProductList();
		return new ResponseEntity<List<ProductVO>>(carList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/select", method = RequestMethod.POST)
	public ResponseEntity<ProductVO> select(@RequestParam Map<String, String> prodId){
		ProductVO select = Dao.selectProduct(prodId.get("prodId"));
		
		log.info(prodId.toString());
		
		return new ResponseEntity<ProductVO>(select, HttpStatus.OK);
	}
	
	
	
}
