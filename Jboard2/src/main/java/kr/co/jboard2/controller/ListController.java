package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		//데이터 수신
		String pg = req.getParameter("pg");
		String search = req.getParameter("search");
		
		//현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 + 검색한 게시물 갯수
		int total = service.selectCountTotal(search);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		// 글 조회 + 검색한 글 조회
		List<ArticleDTO> articles = service.selectArticles(start, search);
		
		
		if(sessUser != null) {
			
			// VIEW 공유 참조
			req.setAttribute("articles", articles);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("lastPageNum", lastPageNum);
			req.setAttribute("pageGroupStart", result[0]);
			req.setAttribute("pageGroupEnd", result[1]);
			req.setAttribute("pageStartNum", pageStartNum+1); //+1 왜? 글목록번호 계산 연산 후 값이 나오기 때문에(총5개의 게시물이면 5-1=4부터 나오니까 5번째 게시물 계산 불가능)
			req.setAttribute("search", search);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
			dispatcher.forward(req, resp);			
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101"); //로그인없이 view 바로 들어갈 수 없게 (modify, view, write 모두 해당 -> 필터처리)
		}
		
		
		
	
	}

}
