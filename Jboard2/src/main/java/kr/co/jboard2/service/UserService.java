package kr.co.jboard2.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import kr.co.jboard2.dao.UserDAO;
import kr.co.jboard2.dto.UserDTO;

public class UserService {

	private static UserService instance = new UserService();
	public static UserService getInstance() {
		return instance;
	}
	private UserService() {}
	
	//인증코드 
	private static String generatedCode;
	
	private UserDAO dao = UserDAO.getInstance();
	
	//회원가입
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
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
	//아이디 찾기
	public int selectCountNameAndEmail(String name, String email) {
		return dao.selectCountNameAndEmail(name,email);
	}
	//비밀번호 찾기
	public int selectCountUidAndEmail(String uid, String email) {
		return dao.selectCountUidAndEmail(uid,email);
	}
	
	//로그인
	public UserDTO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}
	//아이디 찾기 전송
	public UserDTO selectUserByNameAndEmail(String name, String email) {
		return dao.selectUserByNameAndEmail(name, email);
	}

	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}

	//회원정보수정
	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}
	//비밀번호찾기 비밀번호 변경, 회원정보수정 비밀번호 변경
	public int updateUserPass(String uid, String pass) {
		return dao.updateUserPass(uid, pass);
	}
	//회원탈되
	public int updateUserForWithdraw(String uid) {
		return dao.updateUserForWithdraw(uid);
	}

	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
	
	//이메일 인증
	public int sendCodeByEmail(String receiver) {
		
		//인증코드 생성
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		generatedCode = ""+code;
		
		//기본정보
		String sender = "z82714567@gmail.com";
		String password = "hygeihfpwgeukruv";
		String title = "Jboard2 인증코드 입니다.";
		String content = "<h1>인증코드는 " + code + "</h1>";
		
		//Gmail SMTP 서버 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		//Gmail STMP 세션 생성
		Session gmailSession = Session.getInstance(props, new Authenticator(){
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});
		
		//메일 발송
		int status = 0;
		Message message = new MimeMessage(gmailSession);
		
		try{
			message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=UTF-8");
			Transport.send(message);
			
			status = 1;
		}catch(Exception e){
			status = 0;
		}
		return status;
	}//sendCodeByEmail end
	
	public int confirmCodeByEmail(String code) {
	
		if(code.equals(generatedCode)) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
