<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<!-- 
	ctxPath
	 - Context Root 경로 전역변수 
	 - _header.jsp 5줄 참고
-->
<div id="user">
	<section class="login">
        <form action="${ctxPath}/user/login.do" method="post">
            <table border="0">
                <tr>
                    <td><img src="${ctxPath}/images/login_ico_id.png" alt="아이디"></td>
                    <td><input type="text" name="uid" placeholder="아이디 입력"></td>
                </tr>
                <tr>
                    <td><img src="${ctxPath}/images/login_ico_pw.png" alt="비밀번호"></td>
                    <td><input type="password" name="pass" placeholder="비밀번호 입력"></td>
                </tr>
            </table>
            <input type="submit" value="로그인" class="btnLogin">
        </form>
        <div>
            <h3>회원 로그인 안내</h3>
            <p>
                아직 회원이 아니시면 회원으로 가입하세요.
            </p>
            <a href="${ctxPath}/user/terms.do">회원가입</a>
        </div>
    </section>
</div>
<%@ include file="../_footer.jsp" %>