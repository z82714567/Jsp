<%@page import="kr.co.jboard1.dao.UserDAO"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//전송 데이터 수신
	request.setCharacterEncoding("UTF-8");
	String email = request.getParameter("email");
	
	//DB조회
	int result = UserDAO.getInstance().selectCountEmail(email);
	
	
	
	// Json 생성
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	// Json 출력
	String jsonData = json.toString();
	out.print(jsonData);
%>