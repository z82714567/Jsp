package kr.co.jboard1.db;

public class SQL {
	
	//user
	public static final String INSERT_USER = "INSERT INTO `User` SET "
											+ "`uid`=?, "
											+ "`pass`=SHA2(?, 256),"
											+ "`name`=?,"
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`regip`=?,"
											+ "`regDate`=NOW()";
	public static final String SELECT_USER = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK = "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	
	//article
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` SET"
											+ "`title`=?, "
												+ "`content`=?, "
												+ "`writer`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW() ";
	
	public static final String SELECT_ARTICLES = "SELECT " //"SELECT * FROM `Article`"
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid " //writer -> nick
												+ "WHERE `parent`=0 " //댓글이 목록에 출력되지 않기 위해(부모글만 목록에)
												+ "ORDER BY `no` DESC " //최신글 순서대로 
												+ "LIMIT ?, 10"; //1페이지당(시작페이지INDEX번호) 10개 게시물 

	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0"; //부모글 갯수만 카운트
	
	public static final String SELECT_ARTICLE = "SELECT * FROM `Article` WHERE `no`=?";
	
	public static final String INSERT_COMMENT = "INSERT INTO `Article` SET"
												+ "`parent`=?, " //어떤 글(no)의 댓글인지 
												+ "`content`=?, "
												+ "`writer`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW()";
	
	public static final String SELECT_COMMENTS = "SELECT " 
													+ "a.*, "
													+ "b.`nick` "
													+ "FROM `Article` AS a "
													+ "JOIN `User` AS b ON a.writer = b.uid "
													+ "WHERE `parent`=?";
											
	public static final String UPDATE_ARTICLE_FOR_COMMENT_PLUS = "UPDATE `Article` SET `comment` = `comment` + 1 WHERE `no`=?";
	public static final String UPDATE_ARTICLE_FOR_COMMENT_MINUS = "UPDATE `Article` SET `comment` = `comment` - 1 WHERE `no`=?";
	
	public static final String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?";
	
	public static final String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=? WHERE `no`=?";

	public static final String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? OR `parent`=?"; //부모글 삭제 시 댓글 같이 삭제
	
	public static final String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=?";
}
