<%@page import="kr.farmstory1.dto.UserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	//로그인 성공 여부 list에 파라미터 수신되지만 include 헤더에서 설정해줌 
	String success = request.getParameter("success");

	UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");


%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
    <link rel="stylesheet" href="/Farmstory1/css/style.css"/>
    <link rel="stylesheet" href="/Farmstory1/user/css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>    
    <script>
    	const success = <%= success %>;
    	
    	if(success == 100){
    		alert('로그인에 실패하였습니다. 아이디, 비번을 다시 확인하시기 바랍니다.'); //loginProc에서 UserDTO가 null이면
    	}else if(success == 101){
    		alert('로그인을 먼저 하셔야 합니다.'); //로그인없이 write, view 사용 시 
    	}
    	
    	
        $(function(){
            $('.slider > ul').bxSlider({
                slideWidth: 980,
                pager: false,
                controls: false,
                auto: true
            });

            $('#tabs').tabs();
        });
    </script>

</head>
<body>
    <div id="container">
        <header>
            <a href="/Farmstory1" class="logo"><img src="/Farmstory1/images/logo.png" alt="로고"/></a>
            <p>
                <a href="/Farmstory1/index.jsp">HOME |</a>
                <% if(sessUser == null){ %>
                <a href="/Farmstory1/user/login.jsp">로그인 |</a>
                <a href="/Farmstory1/user/terms.jsp">회원가입 |</a>
                <% }else { %>
                <a href="/Farmstory1/user/logout.jsp">로그아웃 |</a>
                
                	<% if(!sessUser.getRole().equals("USER")) { %>
                	<a href="/Farmstory1/admin/">관리자 |</a>
                	<% } %>
                
                <% } %>
                <a href="#">고객센터</a>
            </p>
            <img src="/Farmstory1/images/head_txt_img.png" alt="3만원 이상 무료배송"/>
            
            <ul class="gnb">
                <li><a href="/Farmstory1/introduction/hello.jsp">팜스토리소개</a></li>
                <li><a href="/Farmstory1/market/list.jsp"><img src="/Farmstory1/images/head_menu_badge.png" alt="30%"/>장보기</a></li>
                <li><a href="/Farmstory1/board/list.jsp?group=Croptalk&cate=story">농작물이야기</a></li>
                <li><a href="/Farmstory1/board/list.jsp?group=Event&cate=event">이벤트</a></li>
                <li><a href="/Farmstory1/board/list.jsp?group=Community&cate=notice">커뮤니티</a></li>
            </ul>
        </header>
        