package kr.or.ddit.controller.test.dao;

import java.util.List;

import kr.or.ddit.vo.test.ProductVO;

public interface ITest03DAO {
	public List<ProductVO> selectProductList();
	public ProductVO selectProduct(String prodId);
	public void insertProduct(ProductVO productVO);
}
