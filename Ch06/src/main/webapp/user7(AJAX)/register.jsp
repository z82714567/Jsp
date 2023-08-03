<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user7::register</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(function(){
				$('input[type=submit]').click(function(e){
					e.preventDefault(); //form#의 기본이벤트 해제
					
					//입력데이터 가져오기
					const uid = $('input[name=uid]').val();
					const name = $('input[name=name]').val();
					const hp = $('input[name=hp]').val();
					const age = $('input[name=age]').val();
					
					//입력데이터 -> json생성
					const jsonData = {
							"uid" : uid,
							"name" : name,
							"hp" : hp,
							"age" : age
					};
					
					console.log(jsonData);
					
					$.ajax({
						url: './registerProc.jsp',
						type: 'POST',
						data: jsonData,
						dataType: 'json',
						success: function(data){
							console.log(data);
							
							if(data.result >= 1){
								alert('데이터가 추가 되었습니다.');
								
								//데이터 입력 후 input칸 초기화
								$('form')[0].reset();
								
							}else {
								alert('데이터 추가 실패했습니다. 아이디 또는 휴대폰 중복인 것 같아요.');
							}
						}
						
					});
					
					
					
					
				});
			});
		</script>
	</head>
	<body>
		<h3>User7 등록</h3>
	
		<a href="/Ch06/user7/list.jsp">User7 목록</a>
		
		<form action="#" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp"></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
	</body>
</html>