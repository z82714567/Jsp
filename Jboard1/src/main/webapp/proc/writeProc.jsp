<%@page import="kr.co.jboard1.vo.ArticleVO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@page import="kr.co.jboard1.db.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%
	//전송 데이터 수신
	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String file = request.getParameter("file");
	String writer = request.getParameter("writer");	
	String regip = request.getRemoteAddr(); //ip주소를 얻기 위해 사용하는 코드
	
	ArticleVO vo = new ArticleVO();
	vo.setTitle(title);
	vo.setContent(content);
	vo.setWriter(writer);
	vo.setRegip(regip);
	
	//데이터베이스 처리
	ArticleDAO dao = new ArticleDAO();
	dao.insertArticle(vo);
	
	
	//리다이렉트
	response.sendRedirect("/Jboard1/list.jsp");
%>
