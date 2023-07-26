<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>서버에러</title>
	</head>
	<body>
		<h3>일시적으로 서버에 문제가 생겼습니다. 고객센터로 문의하시길 바랍니다.</h3>
		
		<p>
			에러종류 : <%= exception.getClass().getName() %>
			에러내용 : <%= exception.getMessage() %>
		</p>
		
		<a href="../6_exception.jsp">뒤로가기</a>
	</body>
</html>