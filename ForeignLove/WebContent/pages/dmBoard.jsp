<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<title>DM page</title>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

	<link rel="stylesheet" href="../css/mycss.css">
	<link rel="stylesheet" href="../css/index.css">
	<script type="text/javascript" src="../js/memberScript/index.js"></script>
	<script type="text/javascript" src="../js/dmScript/dmboard.js?var=1.0"></script>

	<style>
	#nav li:hover, #t1>tbody>tr:hover{
		cursor: pointer;
	}
	.cantsee{
		display: none;
	}
	.cansee{	
		display: block;
	}
	</style>
</head>

<body class="container-fluid ppojjak">
	<div class="ppojjak"
		style="width: 100%; background-color: #F8B195; height: 50px;">
		<!--NAVGATION-->
		<nav class="navbar navbar-dark"
			style="background-color: #F8B195; float: left;">

			<!--logo-->
			<a href="index.jsp" class=""> <img src="../images/logo.png"
				alt="nopic" width="30px"></a>
			<a href="index.jsp" class="" style="margin:0 5px 0 15px"> <img src="../images/title.png" alt="nopic" height="20px"></a>
			<!--menubar-->
			<ul class="ppojjak">
				<!--home-->
				<li class="ppojjak" style="float: left; display: inline-block;">
					<a href="index.jsp" class="nav-link" style="color: white;">HOME</a>
				</li>
				<!--market-->
				<li class="dropdown ppojjak" style="float: left; display: inline-block;">
					<a href="marketBoard.jsp" class="nav-link" style="color: white;">MARKET</a>
				</li>
				<!--free-->
				<li class="ppojjak" style="float: left; display: inline-block;">
					<a href="freeBoard.jsp" class="nav-link" style="color: white;">FREE</a>
				</li>
				<!--promotion-->
				<li class="ppojjak" style="float: left; display: inline-block;">
					<a href="adBoard.jsp" class="nav-link" style="color: white;">홍보</a>
				</li>
				<!--DM-->
				<li class="ppojjak" style="float: left; display: inline-block;">
					<a href="dmBoard.jsp" class="nav-link" style="color: white;">쪽지</a>
				</li>
				<!--space-->
				<li class="ppojjak" style="float: left; display: inline-block;">
					<a href="" class="nav-link" style="color: white;"> </a>
				</li>
				<!-- search box -->
				<li class="ppojjak" style="float: left; display: inline-block;">
					
				</li>
			</ul>
		</nav>
	</div>
	<!--menu end-->
	<!---------------------------------------------------------------------------------------------------------------------------------->
	<!-- contents box -->
	<div class="" style="width: 100%;">
		<!--top-->
		<div class="" style="width: 100%;">
			<table>
				<tr style="width: 100%; vertical-align: top;">
					<!--left-->
					<td
						style="width: 20%; margin-top: 0px; padding: 0; vertical-align: top;">
						<div class="" style="padding: 0; margin: 0 10px;">
							<!--cate name-->
            				<div class="well" style="margin-top:10px;width:100%;text-align:center">
                				<h5>쪽지함</h5>
              				</div>
							<!--PROFILE-->
							<div class="card" id="insertPic" style="margin-top: 12px;">
								<div class="card-body" id="profileBox">
									<a href="mypage.jsp" class="card-link">마이페이지 </a>
									<button type="button" id="logoutBtn" class="btn"
										style="width: 100%; height: 30px; margin-top: 10px; color: white; background-color: #F8B195; font-size: 12px;">로그아웃</button>
								</div>
							</div>

							<!--buttons-->
							<div class="">
								<button type="button" class="btn" id="wannaSend"
									style="width: 100%; margin-top: 5px; color: white; background-color: #F8B195;">쪽지보내기</button>
								<button type="button" name="button" class="btn" id="goDM"
									style="width: 100%; margin-top: 5px; color: white; background-color: #F8B195;">
									쪽지함 <span class="badge badge-pill badge-danger">18</span>
								</button>
							</div>

							<!--select-->
							<div class=""
								style="margin-right: 2%; width: 77%; margin-top: 5px;">
								<select class="form-control" name="">
									<option value="all">전체</option>
									<option value="title">제목</option>
									<option value="content">내용</option>
								</select>
							</div>

							<!--search in this cate-->
							<div class="">
								<form class="form-inline" action="#" style="margin-top: 5px;">
									<input type="text" name="" value="" class="form-control"
										placeholder="search" style="margin-right: 2%; width: 77%;">
									<button type="submit" name="searchbutton" class="btn"
										style="background-color: #F8B195; color: white; width: 21%;">GO</button>
								</form>
							</div>
						</div>
					</td>
					
					
					<!--right-->
					<td style="width: 70%">
						<div class="" id="viewAsPopDown" style="width: 95%; padding: 0; margin-top: 12px;">
							<!-- top -->
							<ul id="nav" class="nav nav-tabs ">
								<li role="presentation" id="recv"><a id="allRecv">받은쪽지함</a></li>
								<li role="presentation" id="send"><a id="allSend">보낸쪽지함</a></li>
								<li role="presentation" id="Member"><a id="allMember">학생보기</a></li>
							</ul>
							<table class="table table-hover" id="t1">
								<thead>
									
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>

</html>
