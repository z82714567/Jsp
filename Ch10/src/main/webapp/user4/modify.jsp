<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user4::modify</title>
	</head>
	<body>
		<h3>User4 수정</h3>
		
		<a href="/Ch10">메인</a>
		<a href="/Ch10/user4/list.do">User4 목록</a>
		
		<form action="/Ch10/user4/modify.do" method="post">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" readonly value="${user.name}"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
					<input type="radio" name="gender" value="1" value="${user.gender}">남
					<input type="radio" name="gender" value="2" value="${user.gender}">여
					</td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age" value="${user.age}"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr" value="${user.addr}"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="수정"></td>
				</tr>
			</table>
		</form>
	</body>
</html>