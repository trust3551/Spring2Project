package kr.or.ddit.controller.test.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.test.ProductVO;

@Repository
public class Test03DAOImpl implements ITest03DAO {

	int prodNo = 2;
	
	List<ProductVO> productList = null;
	public Test03DAOImpl() {
		productList = new ArrayList<ProductVO>();
		
		ProductVO product1 = new ProductVO(
				1,"C20240829001","BMW iX3 M 스포츠	",87890000,"White",
				"최초의 순수전기 그란 쿠페 모델인 BMW i4는 일상 속에서 다이내믹하면서도 편안한 드라이빙 경험을 제공합니다\r\n" + 
				"스포티한 성능을 위해 5세대 BMW eDrive 기술을 적용한 5도어 모델으로, 최대 340마력*의 출력을 자랑합니다.\r\n" + 
				"최대 429km*의 주행 가능 거리와 넉넉한 크기의 5개 시트로 구성되어 어디로든 떠나기에 완벽합니다.");
		productList.add(product1);
	}
	
	@Override
	public List<ProductVO> selectProductList() {
		return productList;
	}

	@Override
	public ProductVO selectProduct(String prodId) {
		ProductVO pVO = null;
		for(int i = 0; i < productList.size(); i++) {
			ProductVO productVO = productList.get(i);
			if(productVO.getProdId().equals(prodId)) {
				pVO = productVO;
				break;
			}
		}
		return pVO;
	}

	@Override
	public void insertProduct(ProductVO productVO) {
		productVO.setProdNo(prodNo++);// 번호 하나씩 올려주기
		productVO.setProdRegDate(getCurrentTime());
		productList.add(productVO);
		
	}

	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
	

	
	
}
