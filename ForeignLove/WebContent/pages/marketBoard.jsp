<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
 	<title>Market</title>
 	<meta charset="utf-8">
 	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
  
	<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet"> 
	<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	<script src="../js/boardMarketScript/marketBoard.js"></script>
	<script type="text/javascript" src="../js/memberScript/index.js"></script>

	<link rel="stylesheet" href="../css/mycss.css">
</head>

<body class="container-fluid ppojjak">
	<div class="ppojjak" style="width:100%;background-color:#F8B195;height:50px;">
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
  <!--top-->
  <div class="" style="width:100%;">
    <table>
      <tr style="width:100%;vertical-align: top;">
        <!--left-->
        <td style="width:20%;margin-top:0px;padding:0;vertical-align: top;">
          <div class="" style="padding:0;margin:0 10px;">
            <!--cate name-->
            <div class="well" style="margin-top:10px;width:100%;text-align:center">
              <h5>장터게시판</h5>
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
              <button id="btnNew" type="button" name="button" onclick="location.href='marketwrite.jsp?IU=insert'" class="btn" style="width:100%; margin-top:5px; color:white; background-color:#F8B195;">글쓰기</button>
              <button type="button" id="goDM" class="btn" style="width:100%; margin-top:5px; color:white; background-color:#F8B195;">쪽지함
                <span class="badge badge-pill badge-danger">18</span>
              </button>
            </div>

            <!--select-->
            <div class="" style="margin-right:2%;width:77%;margin-top:5px;">
              <select id="conditionBox" class="form-control" name="">
                <option id="all" value="all">전체</option>
                <option id="mk_title" value="mk_title">제목</option>
                <option id="mk_content" value="mk_content">내용</option>
              </select>
            </div>

            <!--search in this case-->
            <div class="">
              <form class="form-inline" action="#" style="margin-top:5px;">
                <input id="word" type="text" name="" value="" class="form-control" placeholder="search" style="margin-right:2%;width:77%;">
                <button id="go" type="button" name="btnSearch" class="btn" style="background-color:#F8B195;color:white;width:21%;">GO</button>
              </form>
            </div>
          </div>
        </td>
        <!--right-->
        <td style="width:70%">
          <div class="" style="width:100%; padding:12px 12px 0 0;margin:0;">
            <ul id="marketTypeList" class="nav nav-tabs ">
              <li role="presentation" data-code="SELL" class="active"><a href="#">판매해요</a></li>
              <li role="presentation" data-code="BUY"><a href="#">구매해요</a></li>
            </ul>
            <table id="main" class="table table-hover">
              <thead>
                <tr>
                  <th>글제목</th>
                  <th>작성자</th>
                  <th>작성일자</th>
                  <th>좋아요</th>
                  <th>조회수</th>
                </tr>
              </thead>
              <tbody id="boardMarketBody">
                <tr class="boardMarketList">
               
                </tr>
              </tbody>
            </table>
          </div>
          
           <div class="" style="padding:0 auto; margin:20px 0 40px 0; ">
                <div class="" style="padding:0 auto; margin:0 auto;margin-left:33%;">
                  <ul class="pagination" style="padding:0;margin:0 auto;">
                    <li class="page-item" id="prev"><a class="page-link" href="#">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="marketBoard.jsp?page=1">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">4</a></li>
                    <li class="page-item"><a class="page-link" href="#">5</a></li>
                    <li class="page-item" id="next"><a class="page-link" href="#">Next</a></li>
                  </ul>
                </div>
            </div>
        </td>
      </tr>
    </table>
  </div>
</body>

</html>
