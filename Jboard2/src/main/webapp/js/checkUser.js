/**
 * 
 * 회원가입 중복체크
 * 
 */
window.onload = function(){
		//아이디 중복체크
		const inputUid = document.getElementsByName('uid')[0];
		const uidResult = document.getElementsByClassName('uidResult')[0];
		const btnCheckUid = document.getElementById('btnCheckUid');
		btnCheckUid.onclick = function(){
			
			const uid = inputUid.value;
			
			//유효성검사 확인(서버에 부담을 덜기위해 중복확인전에 유효성 확인)
			if(!uid.match(reUid)){
				uidResult.innerText = '유효한 아이디가 아닙니다.';
				uidResult.style.color = 'red';
				isUidOk = false;
				return; //종료
			}
			
			
			//서버전송
			const xhr = new XMLHttpRequest();
			xhr.open('GET', '/Jboard2/user/checkUid.do?uid='+uid);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				if(xhr.readyState == XMLHttpRequest.DONE){
					if(xhr.status == 200){
						const data = JSON.parse(xhr.response)
						
						if(data.result > 0){
							uidResult.innerText = '이미 사용중인 아이디입니다.';
							uidResult.style.color = 'red';
							//유효성검사 확인
							isUidOk = false; 
						}else{
							uidResult.innerText = '사용 가능한 아이디입니다.';
							uidResult.style.color = 'green';
							//유효성검사 확인
							isUidOk = true; 
						}
					}
				}//readyState end
			}//onreadystatechange end
		}//btnCheckUid onclick end
		
		//닉네임 중복체크
		$('#btnCheckNick').click(function(){
			
			const nick = $('input[name=nick]').val();
			
			//유효성검사 확인(서버에 부담을 덜기위해 중복확인전에 유효성 확인)
			if(!nick.match(reNick)){
				$('.nickResult').css('color','red').text('유효한 별명이 아닙니다.');
				isNickOk = false;
				return; //종료
			}
			
			
			$.ajax({
				url:'/Jboard2/user/checkNick.do?nick='+nick,
				type:'get',
				dataType:'json',
				success: function(data){
					if(data.result > 0){
						$('.nickResult').css('color','red').text('이미 사용중인 별명입니다.');
						//유효성검사 확인
						isNickOk = false;
					}else{
						$('.nickResult').css('color','green').text('사용 가능한 별명입니다.');
						//유효성검사 확인
						isNickOk = true;
					}
				}
			});			
		});//btnCheckNick end
		
		//휴대폰 중복체크
		$('input[name=hp]').focusout(function(){
			
			const hp = $(this).val(); 
			//정규표현식 확인
			if(!hp.match(reHp)){
				$('.resultHp').css('color','red').text('휴대폰 번호가 유효하지 않습니다.');
				isHpOk = false;
				return;
			}
				
			const url = '/Jboard2/user/checkHp.do?hp='+hp;
			$.get(url, function(result){
				
				const data = JSON.parse(result);
				
				if(data.result > 0){
					$('.resultHp').css('color','red').text('이미 사용중인 휴대폰입니다.');
					//유효성검사 확인
					isHpOk = false;
				}else{
					$('.resultHp').css('color','green').text('사용 가능한 휴대폰입니다.');
					//유효성검사 확인
					isHpOk = true;
				}
				
				
			});
		});//resultHp end
	}//onload end