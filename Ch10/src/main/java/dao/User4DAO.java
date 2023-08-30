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

public class User4DAO {
	
	private final String HOST = "jdbc:mysql://13.124.4.134:3306/userdb";
	private final String USER = "user1";
	private final String PASS = "zx12cv34!!ZX";

	//등록
	public void insertUser4(User4DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `user4` VALUES (?,?,?,?)");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//수정데이터목록
	public User4DTO selectUser4(int seq) {
		User4DTO dto = new User4DTO();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `user4` WHERE `seq`=?");
			psmt.setInt(1, seq);
			ResultSet rs =  psmt.executeQuery();
			
			if(rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setGender(rs.getInt(3));
				dto.setAge(rs.getInt(4));
				dto.setAddr(rs.getString(5));
				
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
	public List<User4DTO> selectUser4s() {
		List<User4DTO> users4 = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `user4`");
			
			while(rs.next()) {
				User4DTO dto = new User4DTO();
				dto.setSeq(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setGender(rs.getInt(3));
				dto.setAge(rs.getInt(4));
				dto.setAddr(rs.getString(5));
				users4.add(dto);
			}
			
			rs.close();
			conn.close();
			stmt.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users4;
	}
	//수정
	public void updateUser4(User4DTO dto) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("UPDATE `user4` SET `name`=?, `gender`=?, `age`=?, `addr`=? WHERE `seq`=?");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.setInt(5, dto.getSeq());
			psmt.executeUpdate();
			
			conn.close();
			psmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//삭제
	public void deleteUser4(int seq) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `user4` WHERE `seq`=?");
			psmt.setInt(1, seq);
			psmt.executeUpdate();
			
			conn.close();
			psmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
