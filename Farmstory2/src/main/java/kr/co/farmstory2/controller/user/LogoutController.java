package kr.co.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.service.UserService;


@WebServlet("/user/logout.do")
public class LogoutController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//현재 클라이언트 세션 구하기
		HttpSession session = req.getSession();
		//세션 해제
		session.invalidate();
		
		//리다이렉트
		resp.sendRedirect("/Farmstory2/user/login.do?success=200");
	
	}
	
}
