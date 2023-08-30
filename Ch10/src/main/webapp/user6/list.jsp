<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user6::list</title>
	</head>
	<body>
		<h3>User6 목록</h3>
		
		<a href="/Ch10">메인</a>
		<a href="/Ch10/user6/register.do">등록</a>
		
		<table border="1">
			<tr>
				<th>이름</th>
				<th>생년월일</th>
				<th>나이</th>
				<th>주소</th>
				<th>휴대폰</th>
				<th>관리</th>
			</tr>
			<c:forEach var="user" items="${requestScope.users}">
			<tr>
				<td>${user.getName()}</td>
				<td>${user.getBirth()}</td>
				<td>${user.getAge()}</td>
				<td>${user.getAddress()}</td>
				<td>${user.getHp()}</td>
				<td>
					<a href="/Ch10/user6/modify.do?uid=${user.getName()}">수정</a>
					<a href="/Ch10/user6/delete.do?uid=${user.getName()}">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</html>