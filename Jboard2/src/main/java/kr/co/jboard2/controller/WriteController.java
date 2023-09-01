package kr.co.jboard2.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;

@WebServlet("/write.do") //글쓰기 처리
public class WriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");

		//로그인없이 write 바로 들어갈 수 없게 (modify, view, write 모두 해당 -> 필터처리)
		if(sessUser != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/write.jsp");
			dispatcher.forward(req, resp);
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MultipartRequest mr = aService.uploadFile(req);
		
		//폼 데이터 수신(이후 logger.debug처리 꼭 해주기)
		String title = mr.getParameter("title"); //파일 전송 폼은 req으로 수신받을 수 없음
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer"); 
		String oName = mr.getOriginalFileName("file"); //첨부된 파일명 수신 
		String regip = req.getRemoteAddr(); 
		
		logger.debug("title : " + title);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("oName : " + oName);
		logger.debug("regip : " + regip);
		
		//DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oName); //첨부된 파일명으로 파일 갯수 알아내기(ArticleDTO추가)
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		//글 Insert + 최신글no select
		int no = aService.insertArticle(dto);
		
		//파일명 수정(첨부된 원래 파일명 있으면) 및 파일 Insert
				if(oName != null) {
					 String sName = aService.renameToFile(req, oName);
					//파일테이블(`File`) Insert(다운로드시 oName으로 다운받기 위해서 저장)
					FileDTO fileDto = new FileDTO();
					fileDto.setAno(no); //`article` insert + select max no
					fileDto.setOriName(oName);
					fileDto.setNewName(sName);
					
					fService.insertFile(fileDto);
				}
		//리다이렉트
		resp.sendRedirect("/Jboard2/list.do");
	
	}
	
}
