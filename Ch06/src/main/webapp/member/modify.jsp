<%@page import="vo.MemberVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//
	request.setCharacterEncoding("UTF-8");
	//
	String uid = request.getParameter("uid");
	//
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	//
	MemberVO vo = new MemberVO();
	//
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `member` WHERE `uid`=?");
		psmt.setString(1, uid);
		ResultSet rs =psmt.executeQuery();
	
		if(rs.next()) {
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setHp(rs.getString(3));
			vo.setPos(rs.getString(4));
			vo.setDep(rs.getInt(5));
			vo.setRdate(rs.getString(6));
		}
	
		conn.close();
		psmt.close();
		rs.close();
	
	}catch(Exception e) {
		e.printStackTrace();
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>member::modify</title>
	</head>
	<body>
	<h3>Member 수정</h3>
		
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/member/list.jsp">Member 목록</a>
		
		<form action="/Ch06/member/modifyProc.jsp" method="post">
		
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly value="<%= vo.getUid() %>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="<%= vo.getName() %>"/></td>
				</tr>
				<tr>
					<td>핸드폰</td>
					<td><input type="text" name="hp" value="<%= vo.getHp() %>"></td>
				</tr>
				<tr>
					<td>직급</td>
					<td>
						<select name="pos">
							<option>
								<%=vo.getPos() %></option>
								<% if (!vo.getPos().equals("사원")) { %><option value="사원">사원</option><% } %>
    							<% if (!vo.getPos().equals("대리")) { %><option value="대리">대리</option><% } %>
    							<% if (!vo.getPos().equals("과장")) { %><option value="과장">과장</option><% } %>
    							<% if (!vo.getPos().equals("차장")) { %><option value="차장">차장</option><% } %>
    							<% if (!vo.getPos().equals("부장")) { %><option value="부장">부장</option><% } %>
							
							
						</select>
					</td>
				</tr>
				<tr>
					<td>부서</td>
					<td>
						<select name="dep" >
							<option value="<%= vo.getDep() %>">영업1부</option>
							<option value="<%= vo.getDep() %>">영업2부</option>
							<option value="<%= vo.getDep() %>">영업3부</option>
							<option value="<%= vo.getDep() %>">인사부</option>
							<option value="<%= vo.getDep() %>">경영지원부</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>입사일</td>
					<td><input type="text" name="rdate" value="<%= vo.getRdate() %>"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="수정"></td>
				</tr>
			</table>
		</form>
	</body>
</html>