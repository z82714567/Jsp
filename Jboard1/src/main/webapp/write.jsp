<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글쓰기</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
    <div id="container">
        <header>
            <h3>Board System v1.0</h3>
            <p>
                ooo님 반갑습니다.
                <a href="#" class="logout">[로그아웃]</a>
            </p>
        </header>
        <main>
            <section class="write">
                <h3>글쓰기</h3>
                <article>
                   <form action="#">
                        <table class="tbt">
                            <tr>
                                <td>제목</td>
                                <td><input type="text" name="title" placeholder="제목을 입력하세요."></td>
                            </tr>
                            <tr>
                                <td>내용</td>
                                <td><textarea name="content"></textarea></td>
                            </tr>
                            <tr>
                                <td>첨부</td>
                                <td><input type="file" name="file"></td>
                            </tr>
                        </table>
                        <div class="ddi">
                            <a href="#" class="btnCancel">취소</a>
                            <input type="submit" class="btnWrite" value="작성완료">
                        </div>
                   </form> 
                </article>
            </section>
        </main>
        <footer>
            <p>ⓒCopyright 노수현.com</p>
        </footer>
    </div>
</body>
</html>