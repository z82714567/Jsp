package kr.co.farmstory2.dao;


import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;



public class ArticleDAO extends DBHelper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//게시물 작성 write + SELECT_MAX_NO(방금 insert된 글번호(auto_increment라서) 조회)
	public int insertArticle(ArticleDTO dto) {
		
		int no = 0;
		
		try{
			conn = getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getCate()); //게시물(article)마다 cate구분을 위해서 
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, dto.getFile());
			psmt.setString(5, dto.getWriter());
			psmt.setString(6, dto.getRegip());
			
			psmt.executeUpdate();
			
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			
			conn.commit();
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e) {
			logger.error("insertArticle : " + e.getMessage());
		}
		return no;
	}
	
	//전체 게시물 조회 list + 검색(제목 키워드만)한 글 갯수 조회
	public int selectCountTotal(String cate, String search) {
		int total = 0;
		
		try {
			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
				psmt.setString(1, cate);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, cate);
				psmt.setString(2, "%"+search+"%");
			}
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
		}catch(Exception e) {
			logger.error("selectCountTotal error()...: " + e.getMessage());
		}
		return total;
	}
	
	//현재 페이지 게시물 조회 list +  검색한 글
	public List<ArticleDTO> selectArticles(String cate, int start, String search) {
	
	List<ArticleDTO> articles = new ArrayList<>();
	
	try {
		conn = getConnection();
		if(search == null) {
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
		}else {
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES_FOR_SEARCH);
			psmt.setString(1, cate);
			psmt.setString(2, "%"+search+"%");
			psmt.setInt(3, start);
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
	}catch(Exception e){
		logger.error("selectArticles error()...: " + e.getMessage());
	}
	return articles;
	}
	
	//view , modify, list 조회+ Hit증가(select하면서 바로 hit update)
	public ArticleDTO selectArticle(String no) {
		
		ArticleDTO dto = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			PreparedStatement psmt2 = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_HIT_PLUS);
			psmt2.setString(1, no);
			rs = psmt.executeQuery();
			psmt2.executeUpdate();
			conn.commit();
			if(rs.next()) {
				dto = new ArticleDTO();
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
				//`File`
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt(12));
				fileDto.setAno(rs.getInt(13));
				fileDto.setOriName(rs.getString(14));
				fileDto.setNewName(rs.getString(15));
				fileDto.setDownload(rs.getInt(16));
				fileDto.setRdate(rs.getString(17));
				
				dto.setFileDto(fileDto); //리턴값 article에 담아주기  
				
			}
			close();
			psmt2.close();
		}catch (Exception e) {
			logger.error("selectArticle error()...: " + e.getMessage());
		}
		
		return dto;
		}
	
	//게시글 수정 modify
	public void updateArticle(ArticleDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getNo());
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//view 댓글조회
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
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return comments;
		}
	
	//게시물 삭제
	public void deleteArticle(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//댓글 달기 + 최신 댓글 조회(댓글 등록하고 등록한 해당 댓글을 바로 조회해서 dto출력)
	public ArticleDTO insertComment(ArticleDTO dto) {
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_COMMENT_LATEST);
			conn.commit();
			
			if(rs.next()) {
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
				dto.setRdateYYMMDD(rs.getString(11));
				dto.setNick(rs.getString(12));
			}
			
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	//댓글 +
		public void updateArticleForCommentPlus(String no) {
			logger.info("updateArticleForCommentPlus");
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_PLUS);
				psmt.setString(1, no);
				
				psmt.executeUpdate();
				
				close();
				
			}catch(Exception e) {
				logger.error("updateArticleForCommentPlus : "+e.getMessage());
			}
		}
		//댓글 -
		public void updateArticleForCommentMinus(String no) {
			logger.info("updateArticleForCommentMinus");
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_MINUS);
				psmt.setString(1, no);
				psmt.executeUpdate();
				close();
			}catch (Exception e) {
				logger.error("updateArticleForCommentMinus : "+e.getMessage());
			}
		}
		//댓글 수정
		public int updateComment(String no, String content) {
			int result = 0;
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.UPDATE_COMMENT);
				psmt.setString(1, content);
				psmt.setString(2, no);
				
				result = psmt.executeUpdate();
				
				close(); // 커넥션풀에서 받아오기 위해 
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return result;
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
	
	//index cate마다 최신글 조회(size: Limit 불러올 레코드 갯수)
	public List<ArticleDTO> selectLatests(String cate, int size) {
		
		List<ArticleDTO> latests = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_LATESTS);
			psmt.setString(1, cate);
			psmt.setInt(2, size);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getString("no"));
				dto.setTitle(rs.getString("title"));
				dto.setRdate(rs.getString("rdate"));
				latests.add(dto);
			}
			close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return latests;
	}
	
}
