package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.User2DTO;
import dto.User3DTO;
import dto.User4DTO;
import dto.User6DTO;

public class User6DAO {
	
	private final String HOST = "jdbc:mysql://13.124.4.134:3306/userdb";
	private final String USER = "user1";
	private final String PASS = "zx12cv34!!ZX";

	//등록
	public void insertUser6(User6DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `user6` VALUES (?,?,?,?,?)");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getBirth());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddress());
			psmt.setString(5, dto.getHp());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//수정데이터목록
	public User6DTO selectUser6(String name) {
		User6DTO dto = new User6DTO();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `user6` WHERE `name`=?");
			psmt.setString(1, name);
			ResultSet rs =  psmt.executeQuery();
			
			if(rs.next()) {
				dto.setName(rs.getString(1));
				dto.setBirth(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setAddress(rs.getString(4));
				dto.setHp(rs.getString(5));
				
			}
				
			
			rs.close();
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	//목록
	public List<User6DTO> selectUser6s() {
		List<User6DTO> users6 = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `user6`");
			
			while(rs.next()) {
				User6DTO dto = new User6DTO();
				dto.setName(rs.getString(1));
				dto.setBirth(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setAddress(rs.getString(4));
				dto.setHp(rs.getString(5));
				users6.add(dto);
			}
			
			rs.close();
			conn.close();
			stmt.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users6;
	}
	//수정
	public void updateUser6(User6DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("UPDATE `user6` SET `birth`=?, `age`=?, `address`=?, `hp`=? WHERE `name`=?");
			
			psmt.setString(1, dto.getBirth());
			psmt.setInt(2, dto.getAge());
			psmt.setString(3, dto.getAddress());
			psmt.setString(4, dto.getHp());
			psmt.setString(5, dto.getName());
			psmt.executeUpdate();
			
			conn.close();
			psmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//삭제
	public void deleteUser6(String name) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `user6` WHERE `name`=?");
			psmt.setString(1, name);
			psmt.executeUpdate();
			
			conn.close();
			psmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
