<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="vo.User2VO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//데이터베이스 처리(JDBC방식)
	//String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	//String user = "root";
	//String pass = "1234";

	//리스트
	List<User2VO> users = new ArrayList<>();
	
	//6단계
	try{
		//(JDBC방식)
		//Class.forName("com.mysql.cj.jdbc.Driver");
		//Connection conn = DriverManager.getConnection(host, user, pass);
		
		//JNDI 서비스 객체 생성
		Context initCtx = new InitialContext();
		Context ctx = (Context) initCtx.lookup("java:comp/env");
		//커넥션풀에서 커넥션 가져오기
		DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
		Connection conn = ds.getConnection();
		
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `user2`");
		
		while(rs.next()) {
			User2VO vo = new User2VO();
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setHp(rs.getString(3));
			vo.setAge(rs.getInt(4));
			
			users.add(vo);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}

%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user2::list</title>
	</head>
	<body>
		<h3>User2 목록</h3>
		
		<a href="/Ch06/2_DBCP.jsp">처음으로</a>
		<a href="/Ch06/user2/register.jsp">User2 등록</a>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>나이</th>
				<th>관리</th>
			</tr>
			<% for(User2VO vo : users) { %>
			<tr>
				<td><%= vo.getUid() %></td>
				<td><%= vo.getName() %></td>
				<td><%= vo.getHp() %></td>
				<td><%= vo.getAge() %></td>
				<td>
					<a href="/Ch06/user2/modify.jsp?uid=<%= vo.getUid() %>">수정</a>
					<a href="/Ch06/user2/delete.jsp?uid=<%= vo.getUid() %>">삭제</a>
				</td>
			</tr>
			<% } %>
		</table>
	</body>
</html>