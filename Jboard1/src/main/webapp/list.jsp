<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@page import="kr.co.jboard1.dto.UserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String pg = request.getParameter("pg");
	
	//DAO객체 생성
	ArticleDAO dao = new ArticleDAO();
	
	//페이지 관련 변수 선언
	int start = 0;
	int currentPage = 1;
	int total = 0;
	int lastPageNum = 0;
	int pageGroupCurrent = 1;
	int pageGroupStart = 1;
	int pageGroupEnd = 0;
	int pageStartNum = 0;
	
	//현재 페이지 계산
	if(pg != null){
		currentPage = Integer.parseInt(pg); //int로변환
	}
	//LIMIT 시작값 계산
	start = (currentPage - 1) * 10;
	
	//전체 게시물 갯수 조회
	total = dao.selectCountTotal();
	
	//페이지 번호 계산
	if(total % 10 == 0){
		lastPageNum = (total / 10); //1페이지당 10건
	}else{
		lastPageNum = (total / 10) + 1;
	}
	
	//페이지그룹 계산
	pageGroupCurrent = (int) Math.ceil(currentPage / 10.0); // 실수로 나눠서 올림 (int 정수변환)
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1; //1, 11, 21, ... 
	pageGroupEnd = pageGroupCurrent * 10; // 10, 20, 30, ...
	
	if(pageGroupEnd > lastPageNum) {
		pageGroupEnd = lastPageNum;
	}
	
	//각 페이지의 시작번호 계산(`no`값 아님)
	pageStartNum = total - start;
	
	//현재 페이지 게시물 조회
	List<ArticleDTO> articles = dao.selectArticles(start);
%>
		  <main>
		      <section class="list">
                <h3>글목록</h3>
                <article>
                    <table border="0" class="tab">
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>글쓴이</th>
                            <th>날짜</th>
                            <th>조회</th>
                        </tr>
                        <%
                        for(ArticleDTO article : articles) {
                        %>
                        <tr>
                            <td><%= pageStartNum-- %></td> <%-- 차례대로, 마이너스 --%>
                            <td>
                                <a href="/Jboard1/view.jsp?no=<%= article.getNo()%>"><%= article.getTitle() %></a>&nbsp;[<%= article.getComment() %>]
                            </td>
                            <td><%= article.getNick() %></td>
                            <td><%= article.getRdate() %></td>
                            <td><%= article.getHit() %></td>
                        </tr>
                        <% } %>
                    </table>
                </article>
                <!-- 페이지 네비게이션 -->
                <div class="paging">
                	<% if(pageGroupStart > 1) { %>
                    <a href="/Jboard1/list.jsp?pg=<%= pageGroupStart - 1 %>" class="prev">이전</a>
                    <% } %>
                    
                    <% for(int i=pageGroupStart; i<=pageGroupEnd; i++){ %>
                    <a href="/Jboard1/list.jsp?pg=<%= i %>" class="num <%= (currentPage == i)?"current":"" %>"><%= i %></a>
                    <% } %>  
                                       
                    <% if(pageGroupEnd < lastPageNum) { %>  
                    <a href="/Jboard1/list.jsp?pg=<%= pageGroupEnd + 1 %>" class="next">다음</a>
                    <% } %>
                </div>

                <!-- 글쓰기 버튼 -->
                <a href="/Jboard1/write.jsp" class="btnWrite">글쓰기</a>
            </section>
        </main>
<%@ include file="./_footer.jsp"%>