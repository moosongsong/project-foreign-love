<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
	String path = "../promotionuploads/";
%>
<html>
<head>
	<title>Promotion</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

	<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	
	<script src="../js/boardPromotionScript/adBoard.js"></script>
	<script type="text/javascript" src="../js/memberScript/index.js"></script>
	
	<link rel="stylesheet" href="../css/mycss.css">
	
	<style >
	#tagList span{
		margin-right: 4px;
	}
	#tagList span :hover{
		cursor:pointer;
	}
	</style>
</head>

<body class="container-fluid ppojjak" data-path="<%=path %>">
	<div class="ppojjak" style="background-color: #F8B195; height: 50px;">
		<!--NAVGATION-->
		<nav class="navbar navbar-dark" style="background-color: #F8B195; float: left;">
			
			<!--logo-->
			<a href="index.jsp" class=""> <img src="../images/logo.png" alt="nopic" width="30px"></a>
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
					<a href="#" class="nav-link" style="color: white;"> </a>
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
							<div class="well"
								style="margin-top: 10px; width: 100%; text-align: center">
								<h5>홍보게시판</h5>
							</div>
							<!--PROFILE-->
							<div class="card" id="insertPic" style="margin-top: 12px;">
								<div class="card-body" id="profileBox">
									<a href="mypage.jsp" class="card-link">마이페이지 </a>
									<button type="button" id="logoutBtn" class="btn" style="width: 100%;height:30px; margin-top: 10px; color: white; background-color: #F8B195;font-size:12px;">로그아웃</button>
								</div>
							</div>

							<!--buttons-->
							<div class="">
								<button type="button" name="button" class="btn" onclick="location.href='adwrite.jsp?IU=insert'"
									style="width: 100%; margin-top: 5px; color: white; background-color: #F8B195;">글쓰기</button>
								<button type="button" name="button" class="btn" id="goDM"
									style="width: 100%; margin-top: 5px; color: white; background-color: #F8B195;">
									쪽지함 <span class="badge badge-pill badge-danger">18</span>
								</button>
							</div>
						</div>
					</td>
					
					<!--right-->
					<td style="width: 70%">
						<div class="" style="width: 100%; padding: 12px 12px 0 0; margin: 0;">
							<div>
								<div class="well" style="width: 10%; text-align: center; display: inline-block">TAGS</div>
								<div class="well" id="tagList" style="width: 89%; display: inline-block">
								</div>
							</div>
							<ul id="promotionTypeList" class="nav nav-tabs ">
								<li role="presentation" data-code="" class="active"><a href="#">전체보기</a></li>
								<li role="presentation" data-code="CIRCLE"><a href="#">모임</a></li>
								<li role="presentation" data-code="FESTI"><a href="#">축제</a></li>
								<li role="presentation" data-code="STUDY"><a href="#">스터디</a></li>
								<li role="presentation" data-code="END"><a href="#">종료된 행사</a></li>
							</ul>
							<!--1-->
							<div style="width: 100%;">
								<div class="ppojjak boardList"style="width: 100%;">
								
								</div>
							</div>
							
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
</body>

</html>
