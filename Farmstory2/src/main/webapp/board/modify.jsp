<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>

<jsp:include page="./_aside${group}.jsp"/>
			<section class="modify">
			    <h3>글수정</h3>
			    <article>
			        <form action="${ctxPath}/board/modify.do" method="post">
			        <input type="hidden" name="group" value="${group}"/>
					<input type="hidden" name="cate" value="${cate}"/>
					<input type="hidden" name="no" value="${no}"/>
			            <table>
			                <tr>
			                    <td>제목</td>
			                    <td><input type="text" name="title" value="${dto.title}"/></td>
			                </tr>
			                <tr>
			                    <td>내용</td>
			                    <td>
			                        <textarea name="content">${dto.content}</textarea>
			                    </td>
			                </tr>
			                <tr>
			                    <td>첨부</td>
			                    <td>
			                    <a href="#" >${dto.fileDto.oriName}</a>
			                    
			                    <c:if test="${dto.fileDto.oriName != null}">
			                    <a href="#" class="btnDelete">삭제</a>
			                    </c:if>
			                    
			                    <input type="file" name="uploadFile" multiple="multiple" />
			                    <!-- 파일을 삭제했다가 게시글수정을 취소할 수 있기때문에 파일삭제클릭시 화면상에서만 삭제시키고 최종적으로 게시물 수정했을 때 반영해줌 -->
			                    <!-- 수정 : 게시물 등록 작업과 첨부파일 정보 함께 전송 -->
			                    </td>
			                    
			                </tr>
			            </table>
			            <div>
			                <a href="${ctxPath}/board/view.do?group=${group}&cate=${cate}&no=${dto.no}" class="btnCancel">취소</a>
			                <input type="submit" class="btnWrite" value="수정완료"> 
			            </div>
			        </form>
			    </article>
			</section>	
			<!-- 내용 끝 -->
        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp" %>