<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String nick = request.getParameter("nick");
	
	int result = 0;
	
	try{
		Context initCtx = new InitialContext();
		Context ctx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) ctx.lookup("jdbc/Jboard");
		Connection conn = ds.getConnection();
		
		PreparedStatement psmt = conn.prepareStatement("SELECT COUNT(*) FROM `User` WHERE `nick`=?");
		psmt.setString(1, nick);
		
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()) {
			result = rs.getInt(1); //COUNT쿼리
		}
		
		rs.close();
		conn.close();
		psmt.close();
		
	}catch(Exception e) {
		System.out.println("check : " + e.getMessage());
	}
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	out.print(jsonData);

%>