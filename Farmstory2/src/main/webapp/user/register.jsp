<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<!-- 
	ctxPath
	 - Context Root 경로 전역변수 
	 - _header.jsp 5줄 참고
-->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
<script src="${ctxPath}/js/zipcode.js"></script>
<script src="${ctxPath}/js/validation.js"></script> 
<script src="${ctxPath}/js/checkUser.js"></script> 
<!-- <script src="${ctxPath}/js/authEmail.js"></script> -->
<div id="user">
	<section class="register">
        <form id="formUser" action="${ctxPath}/user/register.do" method="post">
        <input type="hidden" name="type" value="REGISTER"/> <!-- 이메일 인증 - 회원가입, 아이디 찾기, 비밀번호 찾기 구분짓기 위해서 --> 
            <table border="1">
                <caption>사이트 이용정보 입력</caption>
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" name="uid" placeholder="아이디 입력"/>
                        <button type="button" id="btnCheckUid"><img src="${ctxPath}/user/images/chk_id.gif" alt="중복확인"></button>
                        <span class="resultId"></span>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td>
                        <input type="password" name="pass1" placeholder="비밀번호 입력"/>                            
                    </td>
                </tr>
                <tr>
                    <td>비밀번호 확인</td>
                    <td>
                        <input type="password" name="pass2" placeholder="비밀번호 확인 입력"/>
                        <span class="resultPass"></span>
                    </td>
                </tr>
            </table>
            <table border="1">
                <caption>개인정보 입력</caption>
                <tr>
                    <td>이름</td>
                    <td>
                        <input type="text" name="name" placeholder="이름 입력"/>
                        <span class="resultName"></span>
                    </td>
                </tr>
                <tr>
                    <td>별명</td>
                    <td>
                        <p>공백없이 한글, 영문, 숫자만 입력가능</p>
                        <input type="text" name="nick" placeholder="별명 입력"/>
                        <button type="button" id="btnCheckNick"><img src="${ctxPath}/user/images/chk_id.gif" alt="중복확인"></button>
                        <span class="resultNick"></span>                            
                    </td>
                </tr>
                <tr>
                    <td>E-Mail</td>
                    <td>
                        <input type="email" name="email" placeholder="이메일 입력"/>
                        <!--  
                        <button type="button" id="btnEmailCode"><img src="${ctxPath}/user/images/chk_auth.gif" alt="인증번호 받기"></button>
                        -->
                        <span id="resultEmail"></span>
                        <!--  
                        <div class="auth">
                        	<input type="text" name="auth" placeholder="인증번호 입력"/>
                        	<button type="button" id="btnEmailAuth"><img src="${ctxPath}/user/images/chk_confirm.gif" alt="확인"></button>
                        </div>
                        -->
                    </td>
                </tr>
                <tr>
                    <td>휴대폰</td>
                    <td>
                        <input type="text" name="hp" placeholder="- 포함 13자리 입력" minlength="13" maxlength="13" />
                        <span id="resultHp"></span>
                    </td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>
                        <div>
                            <input type="text" name="zip" placeholder="우편번호" readonly/>                                
                            <button type="button" class="btnZip" onclick="zipcode()"><img src="${ctxPath}/user/images/chk_post.gif" alt="우편번호 찾기"></button>
                        </div>                            
                        <div>
                            <input type="text" name="addr1" placeholder="주소를 검색하세요." readonly/>
                        </div>
                        <div>
                            <input type="text" name="addr2" placeholder="상세주소를 입력하세요."/>
                        </div>
                    </td>
                </tr>
            </table>

            <div>
                <a href="${ctxPath}/user/login.do" class="btnCancel">취소</a>
                <input type="submit"   class="btnSubmit" value="회원가입"/>
            </div>    
        </form>
    </section>
</div>
<%@ include file="../_footer.jsp" %>