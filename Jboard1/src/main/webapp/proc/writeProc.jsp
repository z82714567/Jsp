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
	//���� ������ ����
	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String file = request.getParameter("file");
	String writer = request.getParameter("writer");	
	String regip = request.getRemoteAddr(); //ip�ּҸ� ��� ���� ����ϴ� �ڵ�
	
	ArticleVO vo = new ArticleVO();
	vo.setTitle(title);
	vo.setContent(content);
	vo.setWriter(writer);
	vo.setRegip(regip);
	
	//�����ͺ��̽� ó��
	ArticleDAO dao = new ArticleDAO();
	dao.insertArticle(vo);
	
	
	//�����̷�Ʈ
	response.sendRedirect("/Jboard1/list.jsp");
%>
