<%@page import="kr.co.jboard1.vo.UserVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//시작페이지
	//현재 로그인 여부를 확인
	UserVO sessUser = (UserVO) session.getAttribute("sessUser");
	
	//도메인주소 -> 로그인화면, (로그인 된 경우)리스트 -> 리스트화면
	if(sessUser == null){
		pageContext.forward("./user/login.jsp");
	}else{
		pageContext.forward("./list.jsp");
	}


%>