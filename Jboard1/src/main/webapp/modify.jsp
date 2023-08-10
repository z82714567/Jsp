<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");
	
	ArticleDAO dao = new ArticleDAO();
	ArticleDTO dto = dao.selectArticle(no);


%>
        <main>
            <section class="write">
                <h3>글수정</h3>
                <article>
                   <form action="/Jboard1/proc/updateProc.jsp" method="post">
                   <input type="hidden" name="no" value="<%= no %>"/>
                        <table class="tbt">
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" value="<%= dto.getTitle() %>" placeholder="제목을 입력하세요."></td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td><textarea name="content"><%= dto.getContent() %></textarea></td>
                            </tr>
                            <tr>
                                <td>첨부</td>
                                <td><input type="file" name="file"></td>
                            </tr>
                        </table>
                        <div class="ddi">
                            <a href="/Jboard1/view.jsp?no=<%= no %>" class="btnCancel">취소</a>
                            <input type="submit" class="btnWrite" value="수정완료">
                        </div>
                   </form> 
                </article>
            </section>
        </main>
 <%@ include file="./_footer.jsp" %>