/**
 * 회원가입 유효성 검사
 */
//폼 데이터 검증 상태변수
	let isUidOk = false;
	let isPassOk = false;
	let isNameOk = false;
	let isNickOk = false;
	let isEmailOk = false;
	let isHpOk = false;
	
	// 데이터 검증에 사용하는 정규표현식
	const reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
	const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
	const reName  = /^[가-힣]{2,10}$/ 
	const reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
	const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	const reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

	//유효성 검사(Validation)
	$(function(){
		//아이디 검사(유효하지 않은 아이디 입력 후 재입력시 span부분 초기화(공백))
		$('input[name=uid]').keydown(function(){
			$('.resultId').text('');
			isUidOk = false;
		});
		//비밀번호 검사
		$('input[name=pass2]').focusout(function(){
			const pass1 = $('input[name=pass1]').val();
			const pass2 = $('input[name=pass2]').val();
			
			if(pass1 == pass2){
				if(pass2.match(rePass)){
					$('.resultPass').css('color', 'green').text('사용 가능한 비밀번호입니다.');
					isPassOk = true;
				}else{
					$('.resultPass').css('color', 'red').text('사용 불가능한 비밀번호입니다.');
					isPassOk = false;
				}
			}else{
				$('.resultPass').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
				isPassOk = false;
			}
		});
		//이름 검사
		$('input[name=name]').focusout(function(){
			const name = $(this).val();
			if(name.match(reName)){
				$('.resultName').text('');
				isNameOk = true;
			}else{
				$('.resultName').css('color', 'red').text('유효한 이름이 아닙니다.');
				isPassOk = false;
			}
		});
		//별명 검사(유효하지 않은 아이디 입력 후 재입력시 span부분 초기화(공백))
		$('input[name=nick]').keydown(function(){
			$('.resultNick').text('');
			isNickOk = false;
		});
		//이메일 검사(유효하지 않은 아이디 입력 후 재입력시 span부분 초기화(공백))
		$('input[name=email]').keydown(function(){
			$('#resultEmail').text('');
			isEmailOk = false;
		});
		//휴대폰 검사(유효하지 않은 아이디 입력 후 재입력시 span부분 초기화(공백))
		$('input[name=hp]').keydown(function(){
			
			$('#resultHp').text('');
			isHpOk = false;
		});
		
		//최종 확인(모두 통과해야 최종 폼 전송 가능)
		$('#formUser').submit(function(){
			if(!isUidOk){
				alert('아이디를 확인하십시오.');
				return false; //전송실패
			}
			if(!isPassOk){
				alert('비밀번호를 확인하십시오.');
				return false; //전송실패
			}
			if(!isNameOk){
				alert('이름을 확인하십시오.');
				return false; //전송실패
			}
			if(!isNickOk){
				alert('별명을 확인하십시오.');
				return false; //전송실패
			}
			if(!isEmailOk){
				alert('이메일을 확인하십시오.');
				return false; //전송실패
			}
			if(!isHpOk){
				alert('휴대폰을 확인하십시오.');
				return false; //전송실패
			}
			
			return true; //전송완료
		});
		
		
		
	});
	