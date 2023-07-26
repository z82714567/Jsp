<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>forward1</title>
	</head>
	<body>
		<h3>forward1 페이지</h3>
		<%
			//forward는 같은 서버 자원내에서 제어권이동이기 때문에 타 서버 자원으로는 이동이 안됨
			pageContext.forward("../1_request.jsp");
		
		%>
		
	</body>
</html>