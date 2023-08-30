package service;

import java.util.List;

import dao.User1DAO;
import dao.User4DAO;
import dto.User1DTO;
import dto.User4DTO;

public class User4Service {

	User4DAO dao = new User4DAO();
	
	public void insertUser4(User4DTO dto) {
		dao.insertUser4(dto);
	}
	public User4DTO selectUser4(int seq) {
		return dao.selectUser4(seq);
	}
	public List<User4DTO> selectUser4s() {
		List<User4DTO> users = dao.selectUser4s();
		return users;
	}
	public void updateUser4(User4DTO dto) {
		dao.updateUser4(dto);
	}
	public void deleteUser4(int seq) {
		dao.deleteUser4(seq);
	}
}
