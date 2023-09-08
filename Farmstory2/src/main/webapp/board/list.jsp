<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<<script>
	//게시글 조회 시 hit+ 카운트 동적처리 
</script>
<!--
	- group 값을 참조하기 위해 include 태그를 사용
	- include 지시자는 표현언어 참조 안됨
-->
<jsp:include page="./_aside${group}.jsp"/>
			<section class="list">
				<form action="${ctxPath}/board/list.do" method="get">
					<input type="hidden" name="group" value="${group}"/>
					<input type="hidden" name="cate" value="${cate}"/>
                    <input type="text" name="search" placeholder="제목 키워드 검색">
                    <input type="submit" value="검색">
				</form>
			    <h3>글목록</h3>
			    <article>
			        <table border="0">
			            <tr>
			                <th>번호</th>
			                <th>제목</th>
			                <th>글쓴이</th>
			                <th>날짜</th>
			                <th>조회</th>
			            </tr>
			            <c:forEach var="article" items="${articles}">
			            <tr>
			                <td>${pageStartNum = pageStartNum - 1}</td>
			                <td><a href="${ctxPath}/board/view.do?group=${group}&cate=${cate}&no=${article.no}">${article.title}&nbsp;[${article.comment}]</a></td>
			                <td>${article.nick}</td>
			                <td>${article.rdate}</td>
			                <td>${article.hit}</td>
			            </tr>
			            </c:forEach>
			        </table>
			    </article>
			
			    <!-- 페이지 네비게이션 -->
		        <div class="paging">
		        	
		        	<c:if test="${pageGroupStart > 1}">
		            <a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}&pg=${pageGroupStart - 1}&search=${search}" class="prev">이전</a>
		        	</c:if>
		        	
		        	<c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
		            <a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}&pg=${i}&search=${search}" class="num ${currentPage == i?'current':'off'}">${i}</a>
		        	</c:forEach>
		        	
		        	<c:if test="${pageGroupEnd < lastPageNum}">
		            <a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}&pg=${pageGroupEnd + 1}&search=${search}" class="next">다음</a>
		            </c:if>
		        
		        </div>
			
			    <!-- 글쓰기 버튼 -->
			    <a href="${ctxPath}/board/write.do?group=${group}&cate=${cate}" class="btnWrite">글쓰기</a>
			    <a href="${ctxPath}/board/list.do?group=${group}&cate=${cate}" class="btnList">글목록</a>
			</section>
			<!-- 내용 끝 -->
        </article>
    </section>
</div>		
<%@ include file="../_footer.jsp" %>