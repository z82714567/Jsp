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
	//전송데이터 수신
	request.setCharacterEncoding("UTF-8");
	String hp = request.getParameter("hp");

	//DB조회
	int result = UserDAO.getInstance().selectCountHp(hp);
	
	

	//json생성
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	//json출력
	String jsonData = json.toString();
	out.print(jsonData);
%>