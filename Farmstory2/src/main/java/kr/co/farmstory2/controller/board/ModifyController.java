package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/modify.do")
public class ModifyController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String no = req.getParameter("no");
		
		logger.debug("group : " + group);
		logger.debug("cate : " + cate);
		logger.debug("no : " + no);
		
		ArticleDTO dto = service.selectArticle(no);
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("no", no);
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/modify.jsp");
		dispatcher.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String no = req.getParameter("no");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String file = req.getParameter("file");
		
		logger.debug("group : " + group);
		logger.debug("cate : " + cate);
		logger.debug("no : " + no);
		logger.debug("title : " + title);
		logger.debug("content : " + content);
		logger.debug("file : " + file);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setNo(no);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(file);
		
		service.updateArticle(dto);

		resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);
		
		
		

		
	}
	
}
