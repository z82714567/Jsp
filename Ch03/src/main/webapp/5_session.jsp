<%@page import="sub1.UserVo"%>
<%@page import="org.apache.catalina.User"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>5_session</title>
		<%--
			날짜 : 2023/07/26
			이름 : 노수현
			내용 : JSP session(클라이언트 객체) 내장객체 실습하기
			
			session
			- session은 최초 요청부터 마지막 요청까지의 전체 시간 경과를 의미함
			- 클라이언트에 대응되는 객체, 서버에 기록되는 클라이언트 고유번호(Session Table)중복없음
			- 서버(WAS)는 각 클라이언트에 대한 고유식별번호(Session ID)를 자동으로 발급함  
			
						
			session : 첫 request부터 마지막 response
			session ID : 브라우저마다 다른 ID부여 (식별)
			WAS에 session Table 존재
		 --%>
	</head>
	<body>
		<h3>5_session 내장객체</h3>
			
		<h4>session ID 확인</h4>
		<%= session.getId() %>
		
		<h4>session 설정</h4>
		<%
			UserVo user = new UserVo("a101", "김유신", "010-1234-1001", 23);
		
			// session table에 userColumn컬럼으로 user객체 저장
			session.setAttribute("userColumn", user);
			
			// session table에 저장된 userColumn 컬럼 값을 가져옴
			UserVo userVo = (UserVo) session.getAttribute("userColumn");
		%>
		<p>
			아이디 : <%= userVo.getUid() %><br>
			이름 : <%= userVo.getName() %><br>
			휴대폰 : <%= userVo.getHp() %><br>
			나이 : <%= userVo.getAge() %><br>
		</p>
			
	</body>
</html>