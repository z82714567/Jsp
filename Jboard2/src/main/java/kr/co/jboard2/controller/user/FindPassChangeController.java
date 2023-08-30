package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.service.UserService;


@WebServlet("/user/findPassChange.do")
public class FindPassChangeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//바로 비밀번호변경 페이지로 올 수 없게 session의 uid사용
		HttpSession session = req.getSession();
		String uid = (String) session.getAttribute("uid");
		if(uid == null) {
			resp.sendRedirect("/Jboard2/user/findPass.do");
		}else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findPassChange.jsp");
			dispatcher.forward(req, resp);
		}
		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pass = req.getParameter("pass1"); //pass1,2는 동일하니까 pass1만(유효성검사완료)
		String uid = req.getParameter("uid");
		
		service.updateUserPass(uid, pass);
		
		resp.sendRedirect("/Jboard2/user/login.do?success=300");
		
		
	}
}
