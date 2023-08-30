package service;

import java.util.List;

import dao.User1DAO;
import dao.User5DAO;
import dto.User1DTO;
import dto.User5DTO;

public class User5Service {

	User5DAO dao = new User5DAO();
	
	public void insertUser5(User5DTO dto) {
		dao.insertUser5(dto);
	}
	public User5DTO selectUser5(String uid) {
		return dao.selectUser5(uid);
	}
	public List<User5DTO> selectUser5s() {
		List<User5DTO> users = dao.selectUser5s();
		return users;
	}
	public void updateUser5(User5DTO dto) {
		dao.updateUser5(dto);
	}
	public void deleteUser5(String uid) {
		dao.deleteUser5(uid);
	}
}
