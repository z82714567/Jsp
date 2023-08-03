<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//인코딩 설정
	request.setCharacterEncoding("UTF-8");

	//전송 데이터 수신
	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String age = request.getParameter("age");
	 
	//데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	//
	int result = 0;
	
	 try{
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 Connection conn = DriverManager.getConnection(host, user, pass);
		 PreparedStatement psmt = conn.prepareStatement("UPDATE `user7` SET `name`=?, `hp`=?, `age`=? WHERE `uid`=?");
		 psmt.setString(1, name);
		 psmt.setString(2, hp);
		 psmt.setString(3, age);
		 psmt.setString(4, uid);
		 
		 //
		 result = psmt.executeUpdate();
		 
		 psmt.close();
		 conn.close();
		 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 
	 //
	 String jsonData = "{\"result\":"+result+"}";
	out.print(jsonData);
%>