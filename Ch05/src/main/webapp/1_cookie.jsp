<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>1_cookie</title>
		<%--
			날짜 : 2023/07/27
			이름 : 노수현
			내용 : JSP 쿠기 실습하기
		--%>
	</head>
	<body>
		<h3>1_cookie 실습</h3>
		<form action="./proc/cookieProc.jsp" method="post">
			<input type="text" name="id" placeholder="아이디 입력"><br>
			<input type="password" name="pw" placeholder="비밀번호 입력"><br>
			<input type="submit" value="로그인">
		</form>
	</body>
</html>