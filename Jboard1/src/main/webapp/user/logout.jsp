<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//세션해제
	session.invalidate();

	//리다이렉트 (로그인화면으로)
	response.sendRedirect("/Jboard1/user/login.jsp");

%>