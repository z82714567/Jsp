<%@page import="kr.farmstory1.dao.UserDAO"%>
<%@page import="kr.farmstory1.dto.TermsDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>

<%
	TermsDTO td = UserDAO.getInstance().selectTerms();
%>
<script>
    	window.onload = function(){
    		
    		const chk1 = document.getElementsByName('chk1')[0];
    		const chk2 = document.getElementsByName('chk2')[0];
    		
    		const btnNext = document.querySelector('.btnNext');
    		btnNext.addEventListener('click', function(e){
    			e.preventDefault();
    			
    			if(!chk1.checked){
    				alert('이용약관에 동의하셔야 합니다.');
    				return;
    			}else if(!chk2.checked){
    				alert('개인정보 취급방침에 동의하셔야 합니다.');
    				return;
    			}else{
    				location.href = '/Farmstory1/user/register.jsp';
    			}
    		})
    		
    	}
</script>
        <div id="user">
          <section class="terms">
                <table border="0">
                    <caption>사이트 이용약관</caption>
                    <tr>
                        <td id="tdd">
                            <textarea readonly><%= td.getTerms() %></textarea>
                            <p>
                                <label><input type="checkbox" name="chk1">동의합니다.</label>
                            </p>
                        </td>
                    </tr>
                </table>
                <table border="0">
                    <caption>개인정보 취급방침</caption>
                    <tr>
                        <td id="tdd">
                            <textarea readonly><%= td.getPrivacy() %></textarea>
                            <p>
                                <label><input type="checkbox" name="chk2">동의합니다.</label>
                            </p>
                        </td>
                    </tr>
                </table>
                <div>
                    <a href="/Farmstory1/user/login.jsp" class="btnCancle">취소</a>
                    <a href="#" class="btnNext">다음</a>
                </div>
            </section>
        </div>
<%@ include file="../_footer.jsp" %>