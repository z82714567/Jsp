<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="dto.FileDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//파일정보 수신 -fno정보를 받아서
	request.setCharacterEncoding("UTF-8");
	String fno = request.getParameter("fno");
	
	//파일정보 조회 -나머지 정보 조회
	Context ctx = (Context) new InitialContext().lookup("java:comp/env");
	DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");

	FileDTO dto = new FileDTO();
	
	try{
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	
	ResultSet rs = stmt.executeQuery("SELECT * FROM `FileTest` WHERE `fno`="+fno);
	
	while(rs.next()){
		dto.setFno(rs.getInt(1));
		dto.setoName(rs.getString(2));
		dto.setsName(rs.getString(3));
		dto.setRdate(rs.getString(4));
		
	}
	
	rs.close();
	stmt.close();
	conn.close();
	
	}catch(Exception e){
		e.printStackTrace();
	}

	

	//response 파일 다운로드 헤더 수정(기술노트-[JSP] response download헤더정보 복붙)
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(dto.getoName(), "utf-8"));
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "private");
		
	//response 파일 스트림 작업
	String path = application.getRealPath("/upload");
	File file = new File(path+"/"+dto.getsName());
	
	out.clear();
	
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	
	while(true){
		int data = bis.read();
		if(data == -1){
			break;
		}
		bos.write(data);
	}
	
	bos.close();
	bis.close();
	
	
	

%>