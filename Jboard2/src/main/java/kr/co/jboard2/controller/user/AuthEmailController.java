package kr.co.jboard2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.jboard2.service.UserService;

@WebServlet("/user/authEmail.do")
public class AuthEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name"); //아이디 찾기
		String email = req.getParameter("email");

		int result = 0;
		int status = 0;
		
		if(name == null) {
			//회원가입 할 때 이메일 인증
			result = service.selectCountEmail(email);//이메일 중복체크
			status  = service.sendCodeByEmail(email);//이메일 발송 함수 호출
		}else {
			//아이디찾기 할 때 이메일 인증
			result = service.selectCountNameAndEmail(name, email);
			
			if(result == 1) {
				status  = service.sendCodeByEmail(email);//이메일 발송 함수 호출
			}
		}
		
		//JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("status", status);
	
		//JSON 출력
		PrintWriter writer= resp.getWriter();
		writer.print(json.toString());
		
		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String code = req.getParameter("code");
			
		int result = service.confirmCodeByEmail(code);
			
		//JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
	
		//JSON 출력
		PrintWriter writer= resp.getWriter();
		writer.print(json.toString());
	}
}
