<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.io.File"%>
<%@page import="java.util.UUID"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	//경로, 사이즈 지정
	String path = application.getRealPath("/upload");
	int maxSize = 1024 * 1024 * 10;
	
	//파일 폼 데이터 수신
	MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	String uid = mr.getParameter("uid");
	String name = mr.getParameter("name");
	String fname = mr.getFilesystemName("fname"); //getOriginalFileName 사용 가능
	
	//파일명 수정(동일한 파일 경우)
	int i = fname.lastIndexOf("."); //확장자앞의 . 인덱스값 구하기
	String ext = fname.substring(i); //확장자 자르기
	
	String uuid = UUID.randomUUID().toString(); //절대 중복될 수 없는 uuid 랜덤값 만들기
	String sName = uuid+ext; 
	
	File f1 = new File(path+"/"+fname); //원래파일명 
	File f2 = new File(path+"/"+sName); //만든파일명
	
	f1.renameTo(f2); //바꿔줌
	
	//원본 파일명 INSERT(DB에 저장)
	Context ctx = (Context) new InitialContext().lookup("java:comp/env");
	DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");
	
	try{
		Connection conn = ds.getConnection();
		PreparedStatement psmt = conn.prepareStatement("INSERT INTO `FileTest` SET `oName`=?, `sName`=?, `rdate`=NOW()");
		psmt.setString(1, fname);
		psmt.setString(2, sName);
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	//파일 다운로드 이동
	response.sendRedirect("../2_FileDownload.jsp");
	
%>