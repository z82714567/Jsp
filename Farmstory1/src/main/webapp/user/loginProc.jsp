<%@page import="kr.farmstory1.dto.UserDTO"%>
<%@page import="kr.farmstory1.dao.UserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	
	//추가
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no = request.getParameter("no");
	String target = request.getParameter("target");
	
	UserDAO dao = UserDAO.getInstance();
	UserDTO dto = dao.selectUser(uid, pass);
	
	if(dto != null){
		session.setAttribute("sessUser", dto);
		
		
			//추가(로그인없이 write, view에서 넘어왔을 때 다시 write, view로 되돌아 가는 설정)
			if(target.equals("write")){ //login의 input값이 문자열임(문자열비교)
				response.sendRedirect("/Farmstory1/board/write.jsp?group="+group+"&cate="+cate);
			}else if(target.equals("view")){
				response.sendRedirect("/Farmstory1/board/view.jsp?group="+group+"&cate="+cate+"&no="+no);
			}else{
				response.sendRedirect("/Farmstory1");
			}
		
			
		}else{
			response.sendRedirect("/Farmstory1/user/login.jsp?success=100");		
		}
	
%>