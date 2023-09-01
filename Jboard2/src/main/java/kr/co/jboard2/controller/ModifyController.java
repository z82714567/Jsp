package kr.co.jboard2.controller;

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

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

		//로그인없이 modify 바로 들어갈 수 없게 (modify, view, write 모두 해당 -> 필터처리)
		if(sessUser != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/modify.jsp");
			dispatcher.forward(req, resp);
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	
	}
}
