<%@page import="kr.farmstory1.dto.ArticleDTO"%>
<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String group   = request.getParameter("group");
	String cate    = request.getParameter("cate");
	String no      = request.getParameter("no");
	String title   = request.getParameter("title");
	String content = request.getParameter("content");
	String file    = request.getParameter("file");

	ArticleDTO dto = new ArticleDTO();
	dto.setTitle(title);
	dto.setContent(content);
	dto.setNo(no);
	
	ArticleDAO dao = new ArticleDAO();
	dao.updateArticle(dto);
	
	response.sendRedirect("/Farmstory1/board/view.jsp?group="+group+"&cate="+cate+"&no="+no);
%>