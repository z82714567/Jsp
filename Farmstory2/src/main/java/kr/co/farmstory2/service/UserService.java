package kr.co.farmstory2.service;

import kr.co.farmstory2.dao.UserDAO;
import kr.co.farmstory2.dto.UserDTO;

public class UserService {
	
	private UserDAO dao = UserDAO.getInstance();
	
	//회원가입
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}
	
	
	
	//로그인
	public UserDTO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}
	
	
	
	//중복확인
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}
	
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}
	
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}
	

}
