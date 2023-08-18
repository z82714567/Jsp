<%@page import="java.util.List"%>
<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@page import="kr.farmstory1.dto.ArticleDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String pg = request.getParameter("pg");
	
	// DAO 객체 생성
	ArticleDAO dao = new ArticleDAO();
	
	// 페이지 관련 변수 선언
	int start = 0;
	int currentPage = 1;
	int total = 0;
	int lastPageNum = 0;
	int pageGroupCurrent = 1;
	int pageGroupStart = 1;
	int pageGroupEnd = 0;
	int pageStartNum = 0;
	
	// 현재 페이지 계산(pg를 null체크해서 currentPage로 변환해서 사용)
	if(pg != null){
		currentPage = Integer.parseInt(pg); //getParameter("pg") - String으로 반환되니까 변환시켜줌 
	}
	
	// Limit 시작값 계산(Limit:레코드 수를 제한해서 조회(불러올 인덱스번호, 불러올 레코드 수)) : 한 페이지당 10건씩 db에서 불러옴
	start = (currentPage - 1) * 10;
	
	// 전체 게시물 갯수 조회(cate마다)
	total = dao.selectCountTotal(cate);
	
	// 페이지 번호 계산(한 페이지당 10건씩)  
	if(total % 10 == 0){
		lastPageNum = (total / 10); //total갯수가 10이라고 가정, 10 % 10 == 0이 맞으니까 lastPageNum (10/10) = 1이 마지막페이지숫자
	}else{
		lastPageNum = (total / 10) + 1; //total갯수가 33이라고 가정, 33 % 10 == 0이 아니라 나머지가 3이면 lastPageNum은 (33/10) = 3에다가 + 1 해준 4페이지가 lastPageNum 
	}
	
	// 페이지 그룹 계산(1~10까지 1그룹)
	pageGroupCurrent = (int) Math.ceil(currentPage / 10.0); //10.0실수로 나눠주고 반올림해서 int정수 변환: java에서 정수와 실수간의 연산은 결과가 실수로 처리됨 그래서 int변환, 페이지번호가 정수형이 아닐 가능성(문자열 등)도 있어서 10.0으로 나눠줘야 함.
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1; //currentPage가 15일 때 (pageGroupCurrent 2 - 1)* 10 + 1 = 11
	pageGroupEnd = pageGroupCurrent * 10; // currentPage가 15일 때 pageGroupCurrent 2 * 10 = 20 
	
	if(pageGroupEnd > lastPageNum){ //게시물이 총 15개인데 2그룹에서 마지막번호가 20이 나오는게 아니라 lastPageNum인 15로 나오게 해줌
		pageGroupEnd = lastPageNum;
	}
	
	// 1페이지당 시작번호 계산 
	pageStartNum = total - start; //게시물이 총 15개 - 10 = 5(2페이지에서는 5부터 시작)	
	
	// 현재 페이지 게시물 조회(한페이지에 게시물들이 나올 수 있게)
	List<ArticleDTO> articles = dao.selectArticles(cate, start);
	
	pageContext.include("./_aside"+group+".jsp");
	
%>
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
                        <% for(ArticleDTO article : articles){ %>
                        <tr>
                            <td><%= pageStartNum-- %></td> <%-- 1페이지당 차례대로, 마이너스 --%>
                            <td>
                                <a href="./view.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= article.getNo() %>"><%= article.getTitle() %></a>&nbsp;[<%= article.getComment() %>]
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
		        	<% if(pageGroupStart > 1){ %>
		            <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pageGroupStart - 1 %>" class="prev">이전</a>
		            <% } %>
		            
		            <% for(int i=pageGroupStart ; i<=pageGroupEnd ; i++){ %>
		            <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= i %>" class="num <%= (currentPage == i)?"current":"" %>"><%= i %></a>
		            <% } %>
		            
		            <% if(pageGroupEnd < lastPageNum){ %>
		            <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pageGroupEnd + 1 %>" class="next">다음</a>
		            <% } %>
		        </div>
			
			    <!-- 글쓰기 버튼 -->
			    <a href="./write.jsp?group=<%= group %>&cate=<%= cate %>" class="btnWrite">글쓰기</a>
			</section>
            		<!-- 내용 끝 -->

                </article>
            </section>
        </div>

<%@ include file="../_footer.jsp" %>