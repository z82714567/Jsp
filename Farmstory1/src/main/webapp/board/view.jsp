<%@page import="java.util.List"%>
<%@page import="kr.farmstory1.dto.ArticleDTO"%>
<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate"); //파라미터 수신위해서 한 번 더 
	String no = request.getParameter("no"); //list에서 no 
	
	//글 보기 시 로그인 여부 확인
	if(sessUser == null){
		response.sendRedirect("/Farmstory1/user/login.jsp?success=101&target=view&group="+group+"&cate="+cate+"&no="+no);
		return;
	}
	
	//DB조회
	ArticleDAO dao = new ArticleDAO();
	
	ArticleDTO dto = dao.selectArticle(no);
	List<ArticleDTO> comments = dao.selectComments(no);
	
	pageContext.include("./_aside"+group+".jsp");
%>
<script>
	$(function(){
		$('.btnDelete').click(function(){
			if(confirm('정말 삭제 하시겠습니까?')){
				return true;	
			}else{
				return false;
			}
		});	
	});
</script>


            <section class="view">
                <h3>글보기</h3>
                <table class="tbtt">
                    <tr>
                        <td>제목</td>
                        <td><input type="text" name="title" value="<%= dto.getTitle() %>" readonly></td>
                    </tr>
                    <% if(dto.getFile() > 0){ %>
                    <tr>
                        <td>첨부파일</td>
                        <td>
                            <a href="#">2020년 상반기 매출자료.xls</a>
                            <span>7회 다운로드</span>
                        </td>
                    </tr>
              		<% } %>
                    <tr>
                        <td>내용</td>
                        <td><textarea name="content" readonly><%= dto.getContent() %></textarea></td>
                    </tr>
                </table>
                <div>
                	
                    <a href="./delete.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= no %>" class="btnDelete">삭제</a>
                    <a href="./modify.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= no %>" class="btnModify">수정</a>
                    <a href="./list.jsp?group=<%= group %>&cate=<%= cate %>" class="btnList">목록</a>
                </div>
                <section class="commentList">
                    <h3>댓글목록</h3>
                    <% for(ArticleDTO comment : comments){ %>
                    <article class="comment">
                    	<form action="#" method="post">
                    		<input type="hidden" name="no" value=""> <%--commentupdate에 넘기기 --%>
                    		<input type="hidden" name="parent" value=""> <%--commentupdate에 넘기기 --%>
                        <span>
                            <span><%= comment.getNick() %></span>
                            <span><%= comment.getRdate() %></span>
                        </span>
                        <textarea class=teta name="comment" readonly><%= comment.getContent() %></textarea>
                        
                        <% if(sessUser.getUid().equals(comment.getWriter())){ %>
                        <div>
                            <a href="#" class="del">삭제</a> 
                            <a href="#" class="can">취소</a> <%--전송버튼 --%> 
                            <a href="#" class="mod">수정</a> <%--전송버튼 --%>
                        </div>
                        <% } %>
                        
                        </form> 
                    </article>
                    <% } %>
                    
                    <% if(comments.isEmpty()){ %>
       				<p class="empty">등록된 댓글이 없습니다.</p>
       				<% } %>
                </section>
                
                <section class="commentForm">
                    <h3>댓글쓰기</h3>
                    <form action="./proc/commentInsert.jsp" method="post">
                    	<input type="hidden" name="group" value="<%= group %>" /> 
                    	<input type="hidden" name="cate" value="<%= cate %>" /> 
                    	<input type="hidden" name="parent" value="<%= no %>" /> 
                    	<input type="hidden" name="writer" value="<%= sessUser.getUid() %>" />
                        <textarea name="content"></textarea>
                        <div>
                            <a href="#" class="btnCancel">취소</a>
                            <input type="submit" class="btnWrite" value="작성완료">
                        </div>
                    </form>
                </section>
            </section>
            
            <!-- 내용 끝 -->

                </article>
            </section>
        </div>
<%@ include file="../_footer.jsp" %>
