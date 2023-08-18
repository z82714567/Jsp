<%@page import="java.util.List"%>
<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");
	
	ArticleDAO dao = new ArticleDAO();
	
	//부모글 조회
	ArticleDTO dto = dao.selectArticle(no); //no값 getParameter(String)으로 받아오니까 selectArticle에서 문자로 
	
	//댓글 조회
	List<ArticleDTO> comments = dao.selectComments(no);

%>
<script>// 삭제버튼 눌렸을 때 이벤트 주기
	$(function(){
		
		//댓글 내용 전역변수(댓글수정 취소시 사용)
		let comment= '';
		
		//this = 수정완료버튼
		//댓글 수정
		$('.mod').click(function(e){
			e.preventDefault();
			
			const txt = $(this).text();
			if(txt == '수정'){
				$(this).parent().prev().addClass('modi'); //textarea 열림
				$(this).parent().prev().attr('readonly', false); //readonly 해제
				$(this).parent().prev().focus();
				//수정 모드 전환
				$(this).text('수정완료');
				$(this).prev().show(); //취소버튼 등장
			}else{
				//수정완료 클릭
				
				if(confirm('정말 수정하시겠습니까?')){
				//수정 데이터 전송(폼->전송) = $(this).closest('form').submit();
				$(this).parent().parent().submit();
				}
				//수정모드 해제
				$(this).parent().prev().removeClass('modi'); //textarea 닫힘	
				$(this).parent().prev().attr('readonly', true); //readonly 다시
				$(this).text('수정');
				$(this).prev().hide();
			}
		});
		
		//댓글 수정 취소(모델1:댓글 수정 취소시 view로 이동만)
		/*
		$('.can').click(function(e){
			e.preventDefault();
			
			$(this).parent().prev().removeClass('modi'); //textarea 닫힘	
			$(this).parent().prev().attr('readonly', true); //readonly 다시
			$(this).hide();
			$(this).next().text('수정');
			
		});
		*/
		
		//댓글 삭제
		$('.del').click(function(){
			const result = confirm('정말 삭제 하시겠습니까?');
			
			if(result){
				return true; //proc으로 이동
			}else{
				return false;//종료(a태그 기본링크이동 해제)
			}
		});
		
		//댓글 쓰기 취소
		const commentContent = document.querySelector('form > textarea[name=content]');
		
		const btnCancel = document.querySelector('.btnCancel');
		btnCancel.onclick = function(e){
			e.preventDefault(); //a태그 이벤트 초기화
			
			commentContent.value = '';
		}
		
	
		
		//부모글 삭제
		const btnDelete = document.getElementsByClassName('btnDelete')[0];
		btnDelete.onclick = function(){
						
			if(confirm('정말 삭제 하시겠습니까?')){
				return true; //proc으로 이동
			}else{
				return false;//종료(a태그 기본링크이동 해제)
			}
		}
		
		
	});
	
</script>
	    <main>
            <section class="view">
                <h3>글보기</h3>
                <table class="tbtt">
                    <tr>
                        <td>제목</td>
                        <td><input type="text" name="title" value="<%= dto.getTitle() %> " readonly></td>
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
                	<% if(sessUser.getUid().equals(dto.getWriter())){ %>
                    <a href="/Jboard1/delete.jsp?no=<%= no %>" class="btnDelete">삭제</a>
                    <a href="/Jboard1/modify.jsp?no=<%= no %>" class="btnModify">수정</a>
                    <% } %>
                    <a href="/Jboard1/list.jsp" class="btnList">목록</a>
                </div>
                <section class="commentList">
                    <h3>댓글목록</h3>
                    
                    <% for(ArticleDTO comment : comments) { %>
                    <article class="comment">
                    
                    	<form action="/Jboard1/proc/commentUpdate.jsp" method="post">
                    		<input type="hidden" name="no" value="<%= comment.getNo() %>"> <%--commentupdate에 넘기기 --%>
                    		<input type="hidden" name="parent" value="<%= comment.getParent() %>"> <%--commentupdate에 넘기기 --%>
                        <span>
                            <span><%= comment.getNick() %></span>
                            <span><%= comment.getRdate() %></span>
                        </span>
                        <textarea class=teta name="comment" readonly><%= comment.getContent() %></textarea>
                        
                        <% if(sessUser.getUid().equals(comment.getWriter())){ %>
                        <div>
                            <a href="/Jboard1/proc/commentDelete.jsp?no=<%= comment.getNo() %>&parent=<%= comment.getParent() %>" class="del">삭제</a> 
                            <a href="/Jboard1/view.jsp?no=<%= no %>" class="can">취소</a> <%--전송버튼 --%> 
                            <a href="#" class="mod">수정</a> <%--전송버튼 --%>
                        </div>
                        <% } %>
                        
                        </form>
                        
                    </article>
                    <% } %>
                    
                    <% if(comments.isEmpty()) { %>
                    <p class="empty">등록된 댓글이 없습니다.</p>
                    <% } %>
                    
                </section>
                <section class="commentForm">
                    <h3>댓글쓰기</h3>
                    <form action="/Jboard1/proc/commentProc.jsp" method="post">
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
        </main>
 <%@ include file="./_footer.jsp" %>