package kr.co.farmstory2.db;

public class SQL {

	//user 약관
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	//user 회원가입
	public static final String INSERT_USER = "INSERT INTO `User` SET "
											+ "`uid`=?, "
											+ "`pass`=SHA2(?, 256),"
											+ "`name`=?, "
											+ "`nick`=?, "
											+ "`email`=?, "
											+ "`hp`=?, "
											+ "`zip`=?, "
											+ "`addr1`=?, "
											+ "`addr2`=?, "
											+ "`regip`=?, "
											+ "`regDate`=NOW()";
	//user 회원가입 중복체크(이름,비번,우편은 중복 가능하니까 제외)(COUNT값이 1(중복 있으면) 또는 0(중복 없으면))
	public static final String SELECT_COUNT_UID   = "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	
	public static final String SELECT_COUNT_NICK  = "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	
	public static final String SELECT_COUNT_HP    = "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	
	//user 로그인
	public static final String SELECT_USER        = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
	public static final String SELECT_USER_BY_NAME_EMAIL = "SELECT * FROM `User` WHERE `name`=? AND `email`=?"; //아이디 찾기 전송
	public static final String SELECT_COUNT_NAME_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `name`=? AND `email`=?"; //아이디 찾기
	public static final String SELECT_COUNT_UID_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `uid`=? AND `email`=?"; //비밀번호 찾기
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
	//article 게시물 작성
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` SET "
												+ "`cate`=?, "
												+ "`title`=?, "
												+ "`content`=?, "
												+ "`file`=?, " //첨부된 파일 갯수
												+ "`writer`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW()";
	//article 전체 게시물 조회 : no로 조회하면 댓글포함이니까 parent=0으로 조회해야 함
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0 AND `cate`=?";
	
	public static final String SELECT_COUNT_TOTAL_FOR_SEARCH = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0 AND `cate`=? AND `title` LIKE ?"; //제목키워드 조회한 갯수 카운트(cate마다)
	
	//article 현재 페이지 게시물 조회 
	public static final String SELECT_ARTICLES = "SELECT a.*, b.`nick` FROM `Article` AS a JOIN `User` AS b ON a.writer = b.uid WHERE `parent`=0 AND `cate`=? ORDER BY `no` DESC LIMIT ?,10";
	
	//article 수정할 게시물 조회
	public static final String SELECT_ARTICLE = "SELECT * FROM `Article` AS a LEFT JOIN `File` AS b ON a.`no` = b.`ano` WHERE `no`=?"; //파일첨부 없을 경우도 조회가능(외부조인LEFT) , 클릭해서 수정할 게시물을 선택하니까 no로만 검색
	
	//article 게시물 수정
	public final static String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=? WHERE `no`=?";
	
	//article 댓글 수정
	public static final String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=?";
	
	//article 게시물 댓글 카운트+-
	public static final String UPDATE_ARTICLE_FOR_COMMENT_PLUS = "UPDATE `Article` SET `comment` = `comment` + 1 WHERE `no`=?";
	public static final String UPDATE_ARTICLE_FOR_COMMENT_MINUS = "UPDATE `Article` SET `comment` = `comment` - 1 WHERE `no`=?";
	
	//article view
	public final static String SELECT_COMMENTS = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid "
												+ "WHERE `parent`=?";
	public static final String SELECT_COMMENT_LATEST = "SELECT "
														+ "a.*, "
														+ "b.`nick` "
														+ "FROM `Article` AS a "
														+ "JOIN `User` AS b ON a.writer = b.uid "
														+ "WHERE `parent`!=0 "
														+ "ORDER BY `no` DESC LIMIT 1";
	
	public static final String UPDATE_ARTICLE_FOR_HIT_PLUS = "UPDATE `Article` SET `hit` = `hit` + 1 WHERE `no`=?"; //list Hit 조회증가
	
	//article 게시물 삭제
	public final static String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? OR `parent`=?";
	
	//article 게시물 댓글 삭제
	public static final String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?";
	
	//article 댓글 달기
	public final static String INSERT_COMMENT = "INSERT INTO `Article` SET "
												+ "`parent`=?, "
												+ "`content`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	//index 최신글(cate마다) 조회 
	public static final String SELECT_LATESTS= "SELECT `no`, `title`, `rdate` FROM `Article` WHERE `parent`=0 AND `cate`=? ORDER BY `no` DESC LIMIT ?";
	
	
	public static final String SELECT_ARTICLES_FOR_SEARCH = "SELECT "  //검색한 글(cate마다) 조회
															+ "a.*, "
															+ "b.`nick` "
															+ "FROM `Article` AS a "
															+ "JOIN `User` AS b ON a.writer = b.uid " //writer -> nick
															+ "WHERE `parent`=0 AND `cate`=? AND `title` LIKE ? " //댓글이 목록에 출력되지 않기 위해(부모글만 목록에)
															+ "ORDER BY `no` DESC " //최신글 순서대로 
															+ "LIMIT ?, 10"; //1페이지당(시작페이지마다 INDEX번호) 10개 게시물 
	
	
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`"; //제일 최신글 조회
	
	//File
	public static final String INSERT_FILE = "INSERT INTO `File` SET "
											+ "`ano`=?, "
											+ "`oriName`=?, "
											+ "`newName`=?, "
											+ "`rdate`=NOW()"; //업로드
	
	public static final String SELECT_FILE = "SELECT * FROM `File` WHERE `fno`=?"; //다운로드
	public static final String UPDATE_FILE_FOR_DOWNLOAD_PLUS = "UPDATE `File` SET `download` = `download` + 1 WHERE `fno`=?"; //view download 횟수 증가
	
	public static final String DELETE_FILE = "DELETE FROM `File` WHERE `ano`=?"; //글 1개 + 여러파일 경우 부모글 삭제 시 여러파일도 삭제 가능하게 ano(글번호)로 조회 
	
	//admin - product 상품등록
	public static final String INSERT_PRODUCT = "INSERT INTO `Product` SET "
												+ "`type`=?, "
												+ "`pName`=?, "
												+ "`price`=?, "
												+ "`delivery`=?, "
												+ "`stock`=?, "
												+ "`thumb1`=?, "
												+ "`thumb2`=?, "
												+ "`thumb3`=?, "
												+ "`seller`=?, "
												+ "`etc`=?, "
												+ "`rdate`=NOW() ";
	//market list - product 전체상품 조회(10개씩)
	public static final String SELECT_PRODUCTS_ALL = "SELECT * FROM `Product` WHERE `stock` > 0 LIMIT ?, 10";
	//market list - 유형별 상품 조회(10개씩)
	public static final String SELECT_PRODUCTS_TYPE = "SELECT * FROM `Product` WHERE `stock` > 0 AND `type`=? LIMIT ?, 10";
	//market list - 전체상품 갯수 조회
	public static final String SELECT_COUNT_PRODUCTS_ALL = "SELECT COUNT(*) FROM `Product` WHERE `stock` > 0";
	//market list - 전체상품 갯수 조회(유형별로)
	public static final String SELECT_COUNT_PRODUCTS_TYPE = "SELECT COUNT(*) FROM `Product` WHERE `stock` > 0 AND `type`=?";
	//market view - 상품번호로 상품 조회
	public static final String SELECT_PRODUCT = "SELECT * FROM `Product` WHERE `pNo`=?";
	
	//market order
	public static final String INSERT_ORDER = "INSERT INTO `Order` SET "
											+ "`orderProduct`=?, "
											+ "`orderCount`=?, "
											+ "`orderDelivery`=?, "
											+ "`orderPrice`=?, "
											+ "`orderTotal`=?, "
											+ "`receiver`=?, "
											+ "`hp`=?, "
											+ "`zip`=?, "
											+ "`addr1`=?, "
											+ "`addr2`=?, "
											+ "`orderEtc`=?, "
											+ "`orderUser`=?, "
											+ "`orderDate`=NOW()";
	//admin orderList - 전체주문상품 갯수 조회
	public static final String SELECT_COUNT_ORDERS_ALL = "SELECT COUNT(*) FROM `Order` WHERE `orderCount` > 0";
	
	//admin orderList - 전체주문상품 조회(10개씩)(Product의 상품명, 상세확인사진 포함)
	public static final String SELECT_ORDERS_ALL = "SELECT "
													+ "a.*, "
													+ "b.`pName`, "
													+ "b.`thumb1` "
													+ "FROM `Order` AS a "
													+ "JOIN `Product` AS b "
													+ "ON a.`orderProduct` = b.`pNo` "
													+ "LIMIT ?, 10";		
	//admin orderList - 선택삭제
	public static final String DELETE_ORDER = "DELETE FROM `Order` WHERE `orderNo`=?";
	
	}
