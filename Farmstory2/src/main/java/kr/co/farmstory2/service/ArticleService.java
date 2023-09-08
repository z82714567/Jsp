package kr.co.farmstory2.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;

public enum ArticleService {

	INSTANCE;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ArticleDAO dao = new ArticleDAO();
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	public int selectCountTotal(String cate, String search) {
		return dao.selectCountTotal(cate, search);
	}
	public List<ArticleDTO> selectArticles(String cate, int start, String search) {
		return dao.selectArticles(cate, start, search);
	}
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	public List<ArticleDTO> selectComments(String parent) {
		return dao.selectComments(parent);
	}	
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}	
	public ArticleDTO insertComment(ArticleDTO dto) {
		return dao.insertComment(dto);
	}
	public void updateArticleForCommentPlus(String no) {
		dao.updateArticleForCommentPlus(no);
	}
	public void updateArticleForCommentMinus(String no) {
		dao.updateArticleForCommentMinus(no);
	}
	public int updateComment(String no, String content) {
		 return dao.updateComment(no, content);
	}
	public int deleteComment(String no) {
		return dao.deleteComment(no);
	}
	public List<ArticleDTO> selectLatests(String cate, int size) {
		return dao.selectLatests(cate, size);
	}
	
	//업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
			//파일 업로드 경로 구하기
			ServletContext ctx = req.getServletContext();
			String path = ctx.getRealPath("/upload");
			
			return path;
		}
		
	//파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		
		String path = getFilePath(req);
		
		int i = oName.lastIndexOf("."); //.확장자 이름 구하기
		String ext = oName.substring(i); //.확장자 이름 구하기
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext; //새로운 파일명
		
		File f1 = new File(path+"/"+oName);
		File f2 = new File(path+"/"+sName);
		
		f1.renameTo(f2); //파일명 수정(o->s)
		
		return sName;
	}
	
	//파일 업로드
	public MultipartRequest uploadFile(HttpServletRequest req) {
		//파일 경로 구하기
		String path = getFilePath(req);
		
		//최대 업로드 파일 크기(10메가)
		int maxSize = 1024 * 1024 * 10;
		
		//파일 업로드(con.jar라이브러리 사용) 및 Multipart 객체 생성
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.error("uploadFile error()...: " + e.getMessage());
		} 
		return mr;
	}
	
	//파일 다운로드
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp, FileDTO dto) throws IOException {
		//response 파일 다운로드 헤더 수정(기술노트-[JSP] response download헤더정보 복붙)
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(dto.getOriName(), "utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
			
		//response 파일 스트림 작업
		String path = getFilePath(req);
		File file = new File(path+"/"+dto.getNewName());
		
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		while(true){
			int data = bis.read();
			if(data == -1){
				break;
			}
			bos.write(data);
		}
		
		bos.close();
		bis.close();
	}
	
	// 페이지 마지막 번호
	public int getLastPageNum(int total) {
		
		int lastPageNum = 0;
		
		if(total % 10 == 0){
			lastPageNum = total / 10;
		}else{
			lastPageNum = total / 10 + 1;
		}
		
		return lastPageNum;
	}
		
	// 페이지 그룹
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	
	// 페이지 시작번호
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start;
	}
	
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null){
			currentPage = Integer.parseInt(pg);	
		}
		
		return currentPage;
	}
	
	// Limit 시작번호
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;
	}

	
	
	
	
}
