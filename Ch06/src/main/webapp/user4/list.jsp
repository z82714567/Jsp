<%@page import="vo.User4VO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//데이터베이스 처리
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	//리스트
	List<User4VO> users = new ArrayList<>();
	
	//6단계
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `user4`");
		
		while(rs.next()) {
			User4VO vo = new User4VO();
			vo.setSeq(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setGender(rs.getInt(3));
			vo.setAge(rs.getInt(4));
			vo.setAddr(rs.getString(5));
			
			users.add(vo);
			
		}
		conn.close();
		stmt.close();
		rs.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user4::list</title>
	</head>
	<body>
		<h3>User4 목록</h3>
		
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user4/register.jsp">User4 등록</a>
		
		<table border="1">
			<tr>
				<th>시퀀스</th>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>주소</th>
				<th>관리</th>
			</tr>
			<% for(User4VO vo : users) { %>
			<tr>
				<td><%= vo.getSeq() %></td>
				<td><%= vo.getName() %></td>
				<td><%= vo.getGender() %></td>
				<td><%= vo.getAge() %></td>
				<td><%= vo.getAddr() %></td>
				<td>
					<a href="/Ch06/user4/modify.jsp?seq=<%= vo.getSeq() %>">수정</a>
					<a href="/Ch06/user4/delete.jsp?seq=<%= vo.getSeq() %>">삭제</a>
				</td>				
			</tr>
			<% } %>
		</table>
	</body>
</html>