package kr.co.jboard2.db;

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
	public static final String SELECT_USER_BY_NAME_EMAIL = "SELECT * FROM `User` WHERE `name`=? AND `email`=?"; //아이디 찾기 전송
	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK = "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	public static final String SELECT_COUNT_NAME_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `name`=? AND `email`=?"; //아이디 찾기
	public static final String SELECT_COUNT_UID_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `uid`=? AND `email`=?"; //비밀번호 찾기
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	public static final String UPDATE_USER_PASS = "UPDATE `User` SET `pass`=SHA2(?, 256) WHERE `uid`=?"; //비밀번호찾기 비밀번호 변경
	public static final String UPDATE_USER_FOR_WITHDRAW = "UPDATE `User` SET"
														+ "`pass`=null,"
														+ "`name`=null,"
														+ "`nick`=null,"
														+ "`email`=null,"
														+ "`hp`=null,"
														+ "`role`=null,"
														+ "`zip`=null,"
														+ "`addr1`=null,"
														+ "`addr2`=null,"
														+ "`leaveDate`=NOW() "
														+ " WHERE `uid`=?"; //회원정보수정 비밀번호 변경
	public static final String UPDATE_USER = "UPDATE `User` SET"
											+ "`name`=?,"
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=? "
											+ " WHERE `uid`=?"; //회원정보수정
	
	
	
	//article
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` SET"
												+ "`title`=?, "
												+ "`content`=?, "
												+ "`file`=?, " //첨부된 파일 갯수
												+ "`writer`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW() "; //글쓰기
	
	public static final String SELECT_ARTICLES = "SELECT " //"SELECT * FROM `Article`" //모든 글 조회
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid " //writer -> nick
												+ "WHERE `parent`=0 " //댓글이 목록에 출력되지 않기 위해(부모글만 목록에)
												+ "ORDER BY `no` DESC " //최신글 순서대로 
												+ "LIMIT ?, 10"; //1페이지당(시작페이지마다 INDEX번호) 10개 게시물 
	public static final String SELECT_ARTICLES_FOR_SEARCH = "SELECT " //"SELECT * FROM `Article`" //검색한 글 조회
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid " //writer -> nick
												+ "WHERE `parent`=0 AND `title` LIKE ? " //댓글이 목록에 출력되지 않기 위해(부모글만 목록에)
												+ "ORDER BY `no` DESC " //최신글 순서대로 
												+ "LIMIT ?, 10"; //1페이지당(시작페이지마다 INDEX번호) 10개 게시물 

	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0"; //부모글 갯수만 카운트
	public static final String SELECT_COUNT_TOTAL_FOR_SEARCH = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0 AND `title` LIKE ?"; //제목키워드 조회한 갯수 카운트
	
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`"; //제일 최신글 조회
	
	public static final String SELECT_ARTICLE = "SELECT * FROM `Article` AS a LEFT JOIN `File` AS b ON a.`no` = b.`ano` WHERE `no`=?"; //파일첨부 없을 경우도 조회가능(외부조인LEFT)
	
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
	
	public static final String UPDATE_ARTICLE_FOR_HIT_PLUS = "UPDATE `Article` SET `hit` = `hit` + 1 WHERE `no`=?"; //list Hit 조회증가
	
	public static final String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?";
	
	public static final String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=? WHERE `no`=?";

	public static final String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? OR `parent`=?"; //부모글 삭제 시 댓글 같이 삭제
	
	public static final String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=?";
	
	
	//File
	public static final String INSERT_FILE = "INSERT INTO `File` SET "
											+ "`ano`=?, "
											+ "`oriName`=?, "
											+ "`newName`=?, "
											+ "`rdate`=NOW()"; //업로드
	
	public static final String SELECT_FILE = "SELECT * FROM `File` WHERE `fno`=?"; //다운로드
	
	public static final String DELETE_FILE = "DELETE FROM `File` WHERE `ano`=?"; //글 1개 + 여러파일 경우 부모글 삭제 시 여러파일도 삭제 가능하게 ano(글번호)로 조회 
	
}
