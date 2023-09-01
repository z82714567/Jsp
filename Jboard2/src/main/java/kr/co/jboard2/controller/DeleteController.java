package kr.co.jboard2.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;

@WebServlet("/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//데이터 수신(글 번호)
		String no = req.getParameter("no");
		logger.debug("no : " + no);
		
		//파일 삭제(`File`테이블의 데이터)
		int result = fService.deleteFile(no);
		
		//글+댓글 삭제 (파일삭제 먼저 ? `File`에서 ano참조하기 때문에)
		aService.deleteArticle(no);
		
		//파일 삭제(upload디렉터리안의 파일 삭제) : 파일이 삭제되면 부모글파일 삭제
		if(result > 0) {
			String path = aService.getFilePath(req); //파일경로
			File file = new File(path+"/"+"파일명");
			
			if(file.exists()) {
				file.delete();
			}
			
			
		}
		//리다이렉트
		resp.sendRedirect("/Jboard2/list.do");
	}
}
