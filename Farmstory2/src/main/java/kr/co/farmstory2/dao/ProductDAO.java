package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ProductDTO;

public class ProductDAO extends DBHelper{

	//admin - product 상품등록
	public void insertProduct(ProductDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_PRODUCT);
			psmt.setInt(1, dto.getType());
			psmt.setString(2, dto.getpName());
			psmt.setInt(3, dto.getPrice());
			psmt.setInt(4, dto.getDelivery());
			psmt.setInt(5, dto.getStock());
			psmt.setString(6, dto.getThumb1());
			psmt.setString(7, dto.getThumb2());
			psmt.setString(8, dto.getThumb3());
			psmt.setString(9, dto.getSeller());
			psmt.setString(10, dto.getEtc());
			psmt.executeUpdate();
			close();

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ProductDTO selectProduct(String pNo) {
		ProductDTO dto = new ProductDTO();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCT);
			psmt.setString(1, pNo);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setpNo(rs.getInt(1));
				dto.setType(rs.getInt(2));
				dto.setpName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setDelivery(rs.getInt(5));
				dto.setStock(rs.getInt(6));
				dto.setSold(rs.getInt(7));
				dto.setThumb1(rs.getString(8));
				dto.setThumb2(rs.getString(9));
				dto.setThumb3(rs.getString(10));
				dto.setSeller(rs.getString(11));
				dto.setEtc(rs.getString(12));
				dto.setRdate(rs.getString(13));
				
				
			}
			close();
			
		}catch(Exception e ) {
			e.printStackTrace();
		}
		
		return dto;
	}
	//market list - product 전체상품 조회(10개씩)(유형별)
	public List<ProductDTO> selectProducts(String type, int start) {
		
		List<ProductDTO> products = new ArrayList<>();
		
		try {
			conn = getConnection();
			
			if(type.equals("0")) {
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_ALL);
			psmt.setInt(1, start);
			}else {
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_TYPE);
			psmt.setString(1, type);
			psmt.setInt(2, start);
			}
			
			rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setpNo(rs.getInt(1));
				dto.setType(rs.getInt(2));
				dto.setpName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setDelivery(rs.getInt(5));
				dto.setStock(rs.getInt(6));
				dto.setSold(rs.getInt(7));
				dto.setThumb1(rs.getString(8));
				dto.setThumb2(rs.getString(9));
				dto.setThumb3(rs.getString(10));
				dto.setSeller(rs.getString(11));
				dto.setEtc(rs.getString(12));
				dto.setRdate(rs.getString(13));
				products.add(dto);
				
			}
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
	//admin - product 상품목록 
	public List<ProductDTO> selectProducts(int start) {
		
		List<ProductDTO> products = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_PRODUCTS_ALL);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setpNo(rs.getInt(1));
				dto.setType(rs.getInt(2));
				dto.setpName(rs.getString(3));
				dto.setPrice(rs.getInt(4));
				dto.setDelivery(rs.getInt(5));
				dto.setStock(rs.getInt(6));
				dto.setSold(rs.getInt(7));
				dto.setThumb1(rs.getString(8));
				dto.setThumb2(rs.getString(9));
				dto.setThumb3(rs.getString(10));
				dto.setSeller(rs.getString(11));
				dto.setEtc(rs.getString(12));
				dto.setRdate(rs.getString(13));
				products.add(dto);
				
			}
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	
	public void updateProduct(ProductDTO dto) {
		
	}
	public void deleteProduct(int pNo) {
		
	}
	
	//market list 전체상품 갯수(유형별)
	public int selectCountProductsTotal(String type) {
		int total = 0;
		
		try {
			conn = getConnection();
			if(type.equals("0")) {
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_ALL);
			}else {
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_TYPE);
			psmt.setString(1, type);

			}
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	//admin - product 상품목록 
	public int selectCountProductsTotal() {
		int total = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_ALL);	
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
}
