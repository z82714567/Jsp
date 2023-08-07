<%@page import="kr.co.jboard1.vo.UserVO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//현재 로그인한 사용자 불러오기
	UserVO sessUser = (UserVO) session.getAttribute("sessUser");

	//로그인 했는지 안 했는지 검사하기
	if(sessUser == null){
		response.sendRedirect("/Jboard1/user/login.jsp?success=101");
		return; //실행 종료
	}

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <link rel="stylesheet" href="/Jboard1/css/style.css">
</head>
<body>
    <div id="container">
        <header>
            <h3>Board System v1.0</h3>
            <p>
                <%= sessUser.getNick() %>님 반갑습니다.
                <a href="/Jboard1/user/logout.jsp" class="logout">[로그아웃]</a>
            </p>
        </header>
