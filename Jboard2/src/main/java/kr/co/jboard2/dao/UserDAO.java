package kr.co.jboard2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.UserDTO;

public class UserDAO  extends DBHelper{
	
	
	private static UserDAO instance = new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;
	}	
	public UserDAO() {}
	

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//회원가입
	public void insertUser(UserDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_USER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getNick());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			psmt.executeUpdate();
			close();
			
			
			
		}catch(Exception e) {
			logger.error("insertUser error : " + e.getMessage());
		}
		
	}
	
	//중복체크
	public int selectCountUid(String uid) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectCountUid() error : " + e.getMessage());
		}
		return result;
		
	}
	//중복체크
	public int selectCountNick(String nick) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectCountNick() error : " + e.getMessage());
		}
		return result;
		
	}
	//중복체크
	public int selectCountHp(String hp) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_HP);
			psmt.setString(1, hp);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectCountHp() error : " + e.getMessage());
		}
		return result;
		
	}
	//중복체크
	public int selectCountEmail(String email) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_EMAIL);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectCountEmail() error : " + e.getMessage());
		}
		return result;
		
	}
	//아이디 찾기
	public int selectCountNameAndEmail(String name,String email) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_NAME_EMAIL);
			psmt.setString(1, name);
			psmt.setString(2, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectCountNameAndEmail() error : " + e.getMessage());
		}
		return result;
		
	}
	//비밀번호 찾기
	public int selectCountUidAndEmail(String uid,String email) {
		
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_UID_EMAIL);
			psmt.setString(1, uid);
			psmt.setString(2, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectCountUidAndEmail() error : " + e.getMessage());
		}
		return result;
		
	}
	
	//로그인
	public UserDTO selectUser(String uid, String pass) {
		
		UserDTO dto = null; //회원이 아닐경우 uid, pass없으니까 null로 리턴해주려고 따로 선언, 생성
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new UserDTO();
				dto.setUid(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setNick(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setHp(rs.getString(6));
				dto.setRole(rs.getString(7));
				dto.setZip(rs.getString(8));
				dto.setAddr1(rs.getString(9));
				dto.setAddr2(rs.getString(10));
				dto.setRegip(rs.getString(11));
				dto.setRegDate(rs.getString(12));
				dto.setLeaveDate(rs.getString(13));
			}
			close();
			
		}catch(Exception e) {
			logger.error("selectUser() error : " + e.getMessage());
		}
		return dto;
	}
	//아이디찾기 전송
	public UserDTO selectUserByNameAndEmail(String name, String email) {
		
		UserDTO dto = null; //회원이 아닐경우 uid, pass없으니까 null로 리턴해주려고 따로 선언, 생성
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER_BY_NAME_EMAIL);
			psmt.setString(1, name);
			psmt.setString(2, email);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new UserDTO();
				dto.setUid(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setNick(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setHp(rs.getString(6));
				dto.setRole(rs.getString(7));
				dto.setZip(rs.getString(8));
				dto.setAddr1(rs.getString(9));
				dto.setAddr2(rs.getString(10));
				dto.setRegip(rs.getString(11));
				dto.setRegDate(rs.getString(12));
				dto.setLeaveDate(rs.getString(13));
			}
			close();
			
		}catch(Exception e) {
			logger.error("selectUserByNameAndEmail() error : " + e.getMessage());
		}
		return dto;
	}
	public List<UserDTO> selectUsers() {
		return null;
	}
	//비밀번호 찾기 변경, 회원정보수정 비밀번호 변경
	public int updateUserPass(String uid, String pass) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_USER_PASS);
			psmt.setString(1, pass);
			psmt.setString(2, uid);
			result = psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error("updateUserPass() error : " + e.getMessage());
		}
		return result;
	}
	//회원탈퇴
	public int updateUserForWithdraw(String uid) {
		
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_USER_FOR_WITHDRAW);
			psmt.setString(1, uid);
			result = psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error("updateUserForWithdraw() error : " + e.getMessage());
		}
		return result;
	}
	
	//회원정보수정
	public void updateUser(UserDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_USER);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getNick());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getHp());
			psmt.setString(5, dto.getZip());
			psmt.setString(6, dto.getAddr1());
			psmt.setString(7, dto.getAddr2());
			psmt.setString(8, dto.getUid());
			psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			logger.error("updateUser() error : " + e.getMessage());
		}
	}
	
	public void deleteUser(String uid) {}
	
	
}
