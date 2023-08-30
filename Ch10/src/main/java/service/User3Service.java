package service;

import java.util.List;

import dao.User1DAO;
import dao.User3DAO;
import dto.User1DTO;
import dto.User3DTO;

public class User3Service {

	User3DAO dao = new User3DAO();
	
	public void insertUser3(User3DTO dto) {
		dao.insertUser3(dto);
	}
	public User3DTO selectUser3(String uid) {
		return dao.selectUser3(uid);
	}
	public List<User3DTO> selectUser3s() {
		List<User3DTO> users = dao.selectUser3s();
		return users;
	}
	public void updateUser3(User3DTO dto) {
		dao.updateUser3(dto);
	}
	public void deleteUser3(String uid) {
		dao.deleteUser3(uid);
	}
}
