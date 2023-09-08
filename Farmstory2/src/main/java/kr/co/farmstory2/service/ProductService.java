package kr.co.farmstory2.service;

import kr.co.farmstory2.dao.ProductDAO;
import kr.co.farmstory2.dto.ProductDTO;

public enum ProductService {

	INSTANCE;
	
	private ProductDAO dao = new ProductDAO();
	
	public void insertProduct(ProductDTO dto) {}
	
}
