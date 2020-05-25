<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	
	<title>Login</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="../js/memberScript/login.js?var=1.0"></script>
	
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
		
		button{
			width: 100%; 
			color: white; 
			margin-top: 5px;
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
				
				<!--id-->
				<!--pass-->
				<div class="container">
					<input type="text" name="m_email" class="form-control" placeholder="EMAIL" style="margin-bottom: 7px;"> 
					<input type="password" name="m_pass" class="form-control" placeholder="PASSWORD" style="margin-bottom: 20px;">
				</div>

				<div class="container">
					<button type="button" class="btn" id="btnLogin" style="background-color: #F67280;">로그인</button>
				</div>

				<!--buttons-->
				<div class="container">
					<button type="button" class="btn" id="btnRegister" style="background-color: #C06C84;">회원가입</button>
					<button type="button" class="btn" id="forgetPass" style="background-color: #6C5B7B;">비밀번호찾기</button>
				</div>
			</div>
		</form>
	</div>
</body>

</html>
