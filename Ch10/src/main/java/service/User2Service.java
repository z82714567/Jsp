package service;

import java.util.List;

import dao.User1DAO;
import dao.User2DAO;
import dto.User1DTO;
import dto.User2DTO;

public class User2Service {

	User2DAO dao = new User2DAO();
	
	public void insertUser2(User2DTO dto) {
		dao.insertUser2(dto);
	}
	public User2DTO selectUser2(String uid) {
		return dao.selectUser2(uid);
	}
	public List<User2DTO> selectUser2s() {
		List<User2DTO> users = dao.selectUser2s();
		return users;
	}
	public void updateUser2(User2DTO dto) {
		dao.updateUser2(dto);
	}
	public void deleteUser2(String uid) {
		dao.deleteUser2(uid);
	}
}
