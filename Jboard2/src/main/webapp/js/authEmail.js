/**
 * 이메일 인증
 */

 //이메일 인증
	$(function(){
		
		let preventDoubleClick = false;
		
		$('#btnEmailCode').click(function(){
	
			const type = $('input[name=type]').val(); //이메일 인증 - 회원가입, 아이디 찾기, 비밀번호 찾기, 회원정보수정 구분짓기 위해서
			const name = $('input[name=name]').val(); //아이디찾기
			const uid = $('input[name=uid]').val(); //비밀번호찾기
			const email = $('input[name=email]').val();
			
			const jsonData = {
					"type" : type,
					"name" : name,
					"uid" : uid,
					"email" : email
			};
			
			if(preventDoubleClick){
				return;
			}
			preventDoubleClick = true;
			$('.resultEmail').text('인증코드 전송중입니다 잠시만 기다려주세요....');
			$('.resultEmailForId').text('인증코드 전송중입니다 잠시만 기다려주세요....');
			$('.resultEmailForPass').text('인증코드 전송중입니다 잠시만 기다려주세요....');
			
			
			setTimeout(function(){
				$.ajax({
					url:'/Jboard2/user/authEmail.do',
					type: 'GET',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						//이메일 중복체크
						if(data.result > 0){
							$('.resultEmail').css('color', 'red').text('이미 사용중인 이메일입니다.');
							isEmailOk = false;
							
							//아이디 찾기 이메일 인증
							if(data.status > 0){
							$('.resultEmailForId').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.resultEmailForPass').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('input[name=auth]').prop('disabled', false);
							}else{
								$('.resultEmailForId').css('color', 'red').text('인증코드 전송 실패. 잠시후 다시 시도하십시오.');
								$('.resultEmailForPass').css('color', 'red').text('인증코드 전송 실패. 잠시후 다시 시도하십시오.');
							}
							
						}else{//이메일 인증코드
							if(data.status > 0){
							$('.resultEmail').css('color', 'green').text('이메일을 확인 후 인증코드를 입력하세요.');
							$('.auth').show();
							$('input[name=email]').attr('readonly', true); //인증코드 입력칸 생성시 이메일 입력칸 수정 못하게 
							}else{
								$('.resultEmail').css('color', 'red').text('인증코드 전송 실패. 잠시후 다시 시도하십시오.');
								$('.resultEmailForId').css('color', 'red').text('해당하는 사용자 또는 이메일이 없습니다.'); //아이디 찾기
								$('.resultEmailForPass').css('color', 'red').text('해당하는 아이디 또는 이메일이 없습니다.'); //비밀번호 찾기
							}
						}
						preventDoubleClick = false;
					}
				});
			}, 1000);
			
		});
		$('#btnEmailAuth').click(function(){
			const code = $('input[name=auth]').val();
			const jsonData = {
					"code": code
			};
			
			$.ajax({
				url: '/Jboard2/user/authEmail.do',
				type: 'POST',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					if(data.result > 0){
						$('.resultEmail').css('color', 'green').text('이메일 인증 완료되었습니다.');
						$('.resultEmailForId').css('color', 'green').text('이메일 인증 완료되었습니다.');
						$('.resultEmailForPass').css('color', 'green').text('이메일 인증 완료되었습니다.');
						//유효성검사 확인
						isEmailOk = true;
					}else{
						$('.resultEmail').css('color', 'red').text('이메일 인증 실패하였습니다. 다시 시도하십시오');
						$('.resultEmailForId').css('color', 'red').text('이메일 인증 실패하였습니다. 다시 시도하십시오');
						$('.resultEmailForPass').css('color', 'red').text('이메일 인증 실패하였습니다. 다시 시도하십시오');
						//유효성검사 확인
						isEmailOk = false;
					}
				}
			})
			
			
		});
	});//이메일인증 end