<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8"); //한글을 안정적이고 빠르게 변환 위해서
	
	String parent = request.getParameter("parent");
	String content = request.getParameter("content");
	String writer = request.getParameter("writer");
	String regip = request.getRemoteAddr();
	
	
	ArticleDTO dto = new ArticleDTO();
	dto.setParent(parent); //seter추가 
	dto.setContent(content);
	dto.setWriter(writer);
	dto.setRegip(regip);
	
	
	ArticleDAO dao = new ArticleDAO();
	//댓글 입력
	dao.insertComment(dto);
	
	//댓글 카운트 수정 Plus
	dao.updateArticleForCommentPlus(parent);
	
	response.sendRedirect("/Jboard1/view.jsp?no="+parent); //댓글 쓰고(insert) 부모글의 no를 불러오기

%>