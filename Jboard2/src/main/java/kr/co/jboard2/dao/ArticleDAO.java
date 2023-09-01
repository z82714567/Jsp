package kr.co.jboard2.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;

public class ArticleDAO extends DBHelper {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//글쓰기 + SELECT_MAX_NO(방금 insert된 글번호(auto_increment라서) 조회)
	public int insertArticle(ArticleDTO dto) {
		
		int no = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false); //트래잭션(Transaction) 시작(insert와 select동시에 : 왜?insert와 select처리 사이의 insert가 될 경우 no가 최신순이 아니기에)
			
			stmt = conn.createStatement(); 
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate();
			
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO); //최신글 no 구함
			
			conn.commit(); //작업 확정
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error("insertArticle error()... : " + e.getMessage());
		}
		return no;
	}
	//view , modify, list Hit증가(select하면서 바로 hit update)
	public ArticleDTO selectArticle(String no) {
		ArticleDTO article = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			//list 조회 증가
			PreparedStatement psmt2 = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_HIT_PLUS);
			psmt2.setString(1, no);
			
			rs = psmt.executeQuery();
			
			psmt2.executeUpdate();
			
			
			
			conn.commit();
			
			if(rs.next()) {
				article = new ArticleDTO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setWriter(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				//`File`
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt(12));
				fileDto.setAno(rs.getInt(13));
				fileDto.setOriName(rs.getString(14));
				fileDto.setNewName(rs.getString(15));
				fileDto.setDownload(rs.getInt(16));
				fileDto.setRdate(rs.getString(17));
				
				article.setFileDto(fileDto); //리턴값 article에 담아주기  
				
				
			}
			
			close();
			psmt2.close();
			
		}catch(Exception e) {
			logger.error("selectArticle error()... : " + e.getMessage());
		}
		
		return article;
	}
	//list -전체 글 갯수 조회, 검색(제목 키워드)한 글 갯수 조회
	public int selectCountTotal(String search) {
		int total = 0;
		
		try {
			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
			}
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectCountTotal error()... : " + e.getMessage());
		}
		
		return total;
	}
	
	//list 전체 글+검색한 글
	public List<ArticleDTO> selectArticles(int start, String search) {
		List<ArticleDTO> articles = new ArrayList<>();
		
		try {
			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
				psmt.setInt(1, start);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
				psmt.setInt(2, start);
			}
			rs = psmt.executeQuery();
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				dto.setNick(rs.getString(12));
				
				articles.add(dto);
				
				
			}
			close();
			
		}catch(Exception e) {
			logger.error("selectArticles error()...: " + e.getMessage());
		}
		
		return articles;
	}
	//댓글 조회
	public List<ArticleDTO> selectComments(String parent) {
		List<ArticleDTO> comments = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, parent);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				dto.setNick(rs.getString(12));
				comments.add(dto);
			}
			close();
			
		}catch(Exception e) {
			logger.error("selectArticles error()...: " + e.getMessage());
		}
		
		return comments;
	}
	//댓글 입력
	public int insertComment(ArticleDTO dto) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			result = psmt.executeUpdate();
			close();
			
		}catch(Exception e) {
			logger.error("insertComment error()... : " + e.getMessage());
		}
		return result;
	}
	//댓글 +
	public void updateArticleForCommentPlus(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_PLUS);
			psmt.setString(1, no);
			
			psmt.executeUpdate();
			
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//댓글 -
	public void updateArticleForCommentMinus(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_MINUS);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//댓글 수정
	public void updateComment(String no, String content) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_COMMENT);
			psmt.setString(1, content);
			psmt.setString(2, no);
			
			psmt.executeUpdate();
			
			close(); // 커넥션풀에서 받아오기 위해 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//댓글 삭제
	public int deleteComment(String no) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_COMMENT);
			psmt.setString(1, no);
			
			result = psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//modify
	public void updateArticle(ArticleDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getNo());
			psmt.executeUpdate();
			close();
			
		}catch(Exception e) {
			logger.error("updateArticle error()... : " + e.getMessage());
		}
	}
	//view 부모글 삭제
	public void deleteArticle(String no) { 
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no); //부모글 삭제 시 댓글 같이 삭제위해 
			
			psmt.executeUpdate();
			 
			close();
			
			
		}catch(Exception e) {
			logger.error("deleteArticle error()... : " + e.getMessage());
		}
	}
}
