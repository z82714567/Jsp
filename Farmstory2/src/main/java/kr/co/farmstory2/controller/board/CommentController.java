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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;


@WebServlet("/board/comment.do")
public class CommentController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String no = req.getParameter("no");
		String content = req.getParameter("content");
		String parent = req.getParameter("parent");
		
		logger.debug("type : " + type);
		logger.debug("no : " + no);
		logger.debug("content : " + content);
		logger.debug("parent : " + parent);
		
		
		int result = 0;
		
		switch(type) {
		case "REMOVE":
			result = service.deleteComment(no);
			service.updateArticleForCommentMinus(parent);
			break;
			
		case "MODIFY":
			result = service.updateComment(no, content);
			break;
		}
		
		//JSON 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String parent = req.getParameter("parent");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();
		
		logger.debug("parent : " + parent);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("regip : " + regip);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setParent(parent);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setRegip(regip);
	
		ArticleDTO comment = service.insertComment(dto);
		
		service.updateArticleForCommentPlus(parent);
		logger.info("updateArticleForCommentPlus...1");
		
		// Json 출력
		resp.setContentType("application/json;charset=UTF-8"); // <-- 이거해야 클라이언트로 전송되는 JSON 한글 안깨짐
		Gson gson = new Gson();
		String strJson = gson.toJson(comment);
		resp.getWriter().print(strJson);
	}

}
