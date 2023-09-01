<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="_header.jsp" %>
        <main id="board">
            <section class="write">

                <form action="/Jboard2/write.do" method="post" enctype="multipart/form-data"> <!-- 파일전송폼 -->
                <input type="hidden" name="writer" value="${sessUser.uid}"/> <!-- writer입력칸 없음  -->
                    <table border="0">
                        <caption>글쓰기</caption>
                        <tr>
                            <th>제목</th>
                            <td><input type="text" required name="title" placeholder="제목을 입력하세요."/></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea required name="content"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>파일</th>
                            <td>
                                <input type="file" name="file"/> <!--업로드 할 파일 동시에 설정 multiple="multiple"-->
                            </td>
                        </tr>
                    </table>
                    
                    <div>
                        <a href="./list.do" class="btn btnCancel">취소</a>
                        <input type="submit" value="작성완료" class="btn btnComplete"/>
                    </div>
                </form>

            </section>
        </main>
<%@ include file="_footer.jsp" %>