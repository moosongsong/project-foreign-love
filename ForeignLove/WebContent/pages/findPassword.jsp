<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

	<script type="text/javascript"  src="../js/memberScript/findpass.js?var=1.0"></script>

<style media="screen">
html, body {
	font-size: 14px;
	font-family: nanumgothic !important;
}

div {
	text-align: center;
	margin-left: auto;
	margin-right: auto;
}

button:hover {
	cursor: pointer;
}

.well {
	min-height: 20px;
	padding: 15px;
	margin-bottom: 12px;
	background-color: #f5f5f5;
	border: 1px solid #e3e3e3;
	border-radius: 4px;
}
</style>
</head>

<body>
	<div class="container" style="width: 400px">
		<form>
			<div class="container">
				<!--logo img-->
				<div class="container"
					style="align: center; margin: 70px 0px 40px 0;">
					<img src="../images/logo.png" alt="nopic" style="width: 150px;">
				</div>
				
				<!--logo title-->
				<div class="container" style="height: 50px">
					<img src="../images/title.png" alt="" style="width: 170px;">
				</div>

				<!--pass-->
				<div class="container">
					<input type="text" id="m_email" value="" class="form-control" placeholder="EMAIL" style="margin-bottom: 7px;">
					<button type="button" name="btnLogin" class="btn" id="sendEmail"
						style="width: 100%; background-color: #F67280; color: white; margin-bottom: 20px;">임시 비밀번호 받기</button> 
				</div>
				<div class="well" style="margin-bottom: 20px;">
					임시 비밀번호가 해당 이메일로 발송되었습니다.<br/>
					발송된 메일을 확인하여 로그인 해주세요.<br/>
					(한번더 누르면 인증번호가 재발송됩니다.)
				</div>
				<!--buttons-->
				<div class="container">
					<button type="button" name="button" class="btn" id="logpage"
						style="width: 100%; background-color: #6C5B7B; color: white;">로그인 페이지로 돌아가기</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>