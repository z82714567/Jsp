/**
 * 사용자 개인정보 중복체크
 */
//사용자 개인정보 중복체크
$(function(){
    		//아이디 중복체그
    		$('#btnCheckUid').click(function(){
    			
    			const uid = $('input[name=uid]').val();
    			
    			//유효성 검사
    			if(!uid.match(reUid)){
					$('.resultId').css('color','red').text('유효한 아이디가 아닙니다.');
					// register script에서 선언했기에 사용 가능함.
					isUidOk = false;
					return; //종료(유효하지 않으니까)
				}
    			
    			//아이디 입력 빈칸일 때 
    			if(uid == ''){
    				alert('아이디를 입력하세요.');
    				return;
    			}
    			//////////////////
    			
    			const jsonData = {
    					"uid" : uid
    			};
    			
    			$.ajax({
    				url: '/Jboard1/user/checkUid.jsp',
    				type: 'GET',
    				data: jsonData,
    				dataType: 'json',
    				success: function(data){
    					if(data.result >= 1){
    						$('.resultId').css('color','red').text('이미 사용중인 아이디입니다.');
    						isUidOk = false;
    					}else {
    						$('.resultId').css('color','green').text('사용 가능한 아이디입니다.');
    						isUidOk = true;
    					}
    				}
    			});
    			
    		}); //아이디 중복체크 end
    		
    		//닉네임 중복체크(중복없으면 다음칸으로 커서 넘어가게)
    		$('input[name=nick]').focusout(function(){
    			
    			//입력 데이터 가져오기
    			const nick = $(this).val();
    			
    			//유효성 검사
    			if(!nick.match(reNick)){
					$('.resultNick').css('color','red').text('유효한 닉네임이 아닙니다.');
					isNickOk = false;
					return;
				}
    			//콘솔 찍고 확인
    			//console.log('nick : ' + nick);
    			
    			//json 생성
    			const jsonData = {
    					"nick": nick
    			};
    			
    			//데이터 전송
    			$.get('/Jboard1/user/checkNick.jsp', jsonData, function(data){
    				//console.log(data);
    				if(data.result >= 1) {
    					$('.resultNick').css('color','red').text('이미 사용중인 별명입니다.');
    					isNickOk = false;
    				}else {
    					$('.resultNick').css('color','green').text('사용 가능한 별명입니다.');
    					isNickOk = true;
    				}
    			});
    			
    		}); //닉네임 중복체크 end
    		
    		//이메일 중복체크
    		document.getElementsByName('email')[0].focusout = function(){
				
				const resultEmail = document.getElementById('resultEmail');
    		
    			//alert('email 포커스아웃!');
    			
    			//입력 데이터 가져오기
    			const email = this.value;
    			
    			//유효성 검사
    			if(!email.match(reEmail)){
					$('#resultEmail').css('color','red').text('유효한 이메일이 아닙니다.');
					isEmailOk = false;
					return;
				}
    			
    			
    			//데이터 전송
    			const xhr = new XMLHttpRequest();
    			xhr.open('GET', '/Jboard1/user/checkEmail.jsp?email='+email);
    			xhr.send();
    			
    			//응답 결과
    			xhr.onreadystatechange = function(){
    				if(xhr.readyState == XMLHttpRequest.DONE){
    				
    					if(xhr.status == 200){
    						const data = JSON.parse(xhr.response);
    						console.log('data : ' + data);
    						
    						
    						if(data.result >= 1){
    							resultEmail.innerText = '이미 사용중인 이메일입니다.';
    							resultEmail.style.color = 'red';
    							isEmailOk = false;
    						}else{
    							resultEmail.innerText = '사용 가능한 이메일입니다.';
    							resultEmail.style.color = 'green';
    							isEmailOk = true;
    						}
    					}
    				}
    				
    			} //onreadystatechange end
    		} //이메일 중복체크 end
    		
    		//휴대폰 중복체크
    		document.getElementsByName('hp')[0].addEventListener('focusout', function(){
				
				const resultHp = document.getElementById('resultHp');
    			const hp = this.value;
    			
    			//유효성 검사
    			if(!hp.match(reHp)){
					resultHp.innerText = '유효한 휴대폰번호가 아닙니다.';
					resultHp.style.color = 'red';
					isHpOk = false;
					return;
				}
    			
    			const url = '/Jboard1/user/checkHp.jsp?hp='+this.value;
    			
    			fetch(url)
    				.then(response => response.json())
    				.then(data => {
    					console.log(data);
    					
    					
    					if(data.result >= 1) {
    						resultHp.innerText = '이미 사용중인 휴대폰번호입니다.';
							resultHp.style.color = 'red';
							isHpOk = false;
    					}else {
    						resultHp.innerText = '사용 가능한 휴대폰번호입니다.';
							resultHp.style.color = 'green';
							isHpOk = true;
    					}
    				});
    		}); //휴대폰 중복체크 end
    		
    		
    	}); //사용자 개인정보 중복체크 end
    	
    