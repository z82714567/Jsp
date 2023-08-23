package kr.farmstory1.db;

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
	
	//article 게시물 작성
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` SET "
												+ "`cate`=?, "
												+ "`title`=?, "
												+ "`content`=?, "
												+ "`writer`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW()";
	//article 전체 게시물 조회 : no로 조회하면 댓글포함이니까 parent=0으로 조회해야 함
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0 AND `cate`=?";
	
	//article 현재 페이지 게시물 조회 
	public static final String SELECT_ARTICLES = "SELECT a.*, b.`nick` FROM `Article` AS a JOIN `User` AS b ON a.writer = b.uid WHERE `parent`=0 AND `cate`=? ORDER BY `no` DESC LIMIT ?,10";
	
	//article 수정할 게시물 조회
	public static final String SELECT_ARTICLE = "SELECT * FROM `Article` WHERE `no`=?"; //클릭해서 수정할 게시물을 선택하니까 no로만 검색
	
	//article 게시물 수정
	public final static String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=? WHERE `no`=?";
	
	//article view
	public final static String SELECT_COMMENTS = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid "
												+ "WHERE `parent`=?";
	
	//article 게시물 삭제
	public final static String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? OR `parent`=?";
	
	//article 댓글 달기
	public final static String INSERT_COMMENT = "INSERT INTO `Article` SET "
												+ "`parent`=?, "
												+ "`content`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	//index 최신글(cate마다) 조회 
	public static final String SELECT_LATESTS= "SELECT `no`, `title`, `rdate` FROM `Article` WHERE `parent`=0 AND `cate`=? ORDER BY `no` DESC LIMIT ?";
	
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
	}
