package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.MemberService;

@WebServlet("/register.do")
public class RegisterController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//enum을 활용한 싱글톤 객체 
	private MemberService service = MemberService.Instance;
	

	//자바 기본 로거 생성 java.util.logging (잘 안씀)
	private Logger logger = Logger.getGlobal();
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger.info("RegisterController init()...");
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("RegisterController doGet()...");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/register.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("RegisterController doPost()...1");
		
		String uid = req.getParameter("uid");
		String name = req.getParameter("name");
		String hp = req.getParameter("hp");
		String pos = req.getParameter("pos");
		String dep = req.getParameter("dep");
		
		MemberDTO dto = new MemberDTO();
		dto.setUid(uid);
		dto.setName(name);
		dto.setHp(hp);
		dto.setPos(pos);
		dto.setDep(dep);
		
		logger.info("RegisterController doPost()...2 : " + dto);
		
		service.insertMember(dto);
		
		resp.sendRedirect("/Ch11/list.do");
	}
	
	
}
