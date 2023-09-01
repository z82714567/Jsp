<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="_header.jsp" %>
<script>
	$(function(){
		//댓글 삭제(정적페이지 화면에서만(이미 생성된 댓글에서만) 이벤트 동작함)
		/*
		$('.remove').click(function(e){
			e.preventDefault();
			
			alert('!!!');
		})
		*/
		
		//댓글 삭제(동적 생성 이벤트 구현(새로 만들어진 댓글도 이벤트 동작함))
		$(document).on('click', '.remove', function(e){
			e.preventDefault();
			const no = $(this).data('no'); //내가 선택한 a태그(사용자정의속성data-no)
			const article = $(this).parent().parent();
			const parent = $('input[name=commentParent]').val();
			
			console.log('no : ' + no)
			
			const jsonData = {
				"kind": "REMOVE",
				"no": no,
				"parent": parent
			}
			
			$.ajax({
				url: '/Jboard2/comment.do',
				type: 'GET',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					
					if(data.result > 0){
						alert('댓글 삭제 완료!');
						
						//화면처리
						article.remove();
						}
					}
			});
		});
		
		//댓글 입력
		$('#btnComment').click(function(e){
			e.preventDefault();
			
			const parent = $('#formComment > input[name=parent]').val();
			const content = $('#formComment > textarea[name=content]'); //문서객체선언
			const writer = $('#formComment > input[name=writer]').val();
			
			const jsonData = {
					"parent": parent,
					"content": content.val(),
					"writer": writer
			};
			
			console.log('jsonData : ' + jsonData);
			
			$.ajax({
				url: '/Jboard2/comment.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					
					console.log(data);
					if(data.result > 0){
						alert('댓글이 등록되었습니다.');
						
						//동적 화면 생성(댓글 작성 : 폼전송은 화면 갱신됨, ajax으로는 통신을 줄일 수 있음)
						const dt = new Date();
						const year = dt.getFullYear().toString().substr(2, 4);
						const month = dt.getMonth()+1;
						const date = dt.getDate();
						const now = year + "-" + month + "-" + date;
						const empty = $('.empty');
						
						const article = `<article>
											<span class='nick'>${sessUser.nick}</span>
											<span class='date'>`+now+`</span>
											<p class='content'>`+content.val()+`</p>
											<div>
												<a href='#' class='remove'>삭제</a>
												<a href='#' class='modify'>수정</a>
											</div>
										</article>`; //서버에 추가만 하는 동작
										
						$('.commentList').append(article);
						content.val(''); //댓글작성완료시 댓글칸 공백 
						empty.remove(); //
					}else{
						alert('댓글이 등록 실패했습니다.');
					}
				}
			});
		});
	});

</script>
        <main id="board">
            <section class="view">
                
                <table border="0">
                    <caption>글보기</caption>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" name="title" value="${article.title}" readonly/></td>
                    </tr>
                    
                    <c:if test="${article.file > 0}">
                    <tr>
                        <th>파일</th>
                        <td><a href="/Jboard2/fileDownload.do?fno=${article.fileDto.fno}">${article.fileDto.oriName}</a>&nbsp;
                        <span>${article.fileDto.download}</span>회 다운로드</td>
                    </tr>
                    </c:if>
                    
                    <tr>
                        <th>내용</th>
                        <td>
                            <textarea name="content" readonly>${article.content}</textarea>
                        </td>
                    </tr>                    
                </table>
                
                <div>
                    <a href="./delete.do?no=${article.no}" class="btn btnRemove">삭제</a>
                    <a href="./modify.do" class="btn btnModify">수정</a>
                    <a href="./list.do" class="btn btnList">목록</a>
                </div>

                <!-- 댓글목록 -->
                <section class="commentList">
                    <h3>댓글목록</h3>                   
						<c:forEach var="comment" items="${comments}">
				            <article>
				                <span class="nick">${comment.nick}</span>
				                <span class="date">${comment.rdate}</span>
				                <p class="content">${comment.content}</p>
				                
				                <c:if test="${sessUser.uid eq comment.writer}"> <!--내가쓴댓글만 수정삭제  -->                     
				                <div>
				                    <a href="#" class="remove" data-no=${comment.no}>삭제</a>
				                    <input type="hidden" name="commentParent" value=${comment.parent}/>
				                    <a href="#" class="modify">수정</a>
				                </div>
				                </c:if>  
				                
				            </article>
								</c:forEach>
								<c:if test="${comments.size() == 0}">
			            			<p class="empty">등록된 댓글이 없습니다.</p>
								</c:if>
                </section>

                <!-- 댓글쓰기 -->
                <section class="commentForm">
                    <h3>댓글쓰기</h3>
                    <form id="formComment" action="#" method="post">
                    <input type="hidden" name="parent" value="${no}" />
                    <input type="hidden" name="writer" value="${sessUser.uid}" />
                        <textarea name="content"></textarea>
                        <div>
                            <a href="#" class="btn btnCancel">취소</a>
                            <input type="submit" id="btnComment" value="작성완료" class="btn btnComplete"/>
                        </div>
                    </form>
                </section>

            </section>
        </main>
<%@ include file="_footer.jsp" %>