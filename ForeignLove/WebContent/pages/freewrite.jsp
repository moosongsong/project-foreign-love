<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.foreignlove.controller.DAOManager" %>
<%@ page import="kr.co.foreignlove.dao.BoardFreeDAO" %>
<%@ page import="kr.co.foreignlove.vo.BoardFreeVO" %>    

<!DOCTYPE html>
<html>
<%
	String IU = request.getParameter("IU");
	BoardFreeVO board = null;
	if(IU.equals("update")) {
		int id = Integer.parseInt(request.getParameter("id"));
		BoardFreeDAO dao = (BoardFreeDAO)DAOManager.getDAO(BoardFreeDAO.NAME);
		board = dao.getBoard(id);
	}
	
%>
<head>
  	<title>free write</title>	
  	<meta charset="utf-8">	
  	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

  	<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
  	<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
  	
  	<link rel="stylesheet" href="../css/mycss.css">	
  	<script src="../js/boardFreeScript/freewrite.js"></script>
  	<script type="text/javascript" src="../js/memberScript/index.js"></script>
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
  <div class="" style="width:100%;">
    <!--top-->
    <div class="" style="width:100%;">
      <table>
        <tr style="width:100%;vertical-align: top;">
          <!--left-->
          <td style="width:20%;margin-top:0px;padding:0;vertical-align: top;">
            <div class="" style="padding:0;margin:0 10px;">
              <!--cate name-->
              <div class="well" style="margin-top:10px;width:100%;text-align:center">
                <h5>자유게시판</h5>
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
                <button type="button" name="button" class="btn" style="width:100%; margin-top:5px; color:white; background-color:#F8B195;" onclick="location.href='freewrite.jsp?IU=insert'" >글쓰기</button>
                <button type="button" id="goDM" class="btn" style="width:100%; margin-top:5px; color:white; background-color:#F8B195;">쪽지함
                  <span class="badge badge-pill badge-danger">18</span>
                </button>
              </div>

            </div>
          </td>
          <!--right-->
          <td style="width:70%" style="width:100%;padding:0;margin:0;">
            <div class="" style="width:100%;padding:0;margin:0;margin-left:10px;margin-top:3px;">
              <input id="title" type="text" name="" value="<% if(IU.equals("update")) { out.print(board.getB_title());} %>" placeholder="제목" style="margin:20px 0; width:50%;display:inline;" class="form-control">
             <% if(IU.equals("insert")) {%>
              <form id="plusFileForm">
	              
		              <input type="file" id="fileChoose" name="file" />
		              <input type="button" id="btnPlusFile" value="추가"/>
		         
	          </form>
	           
	              <div id="attachedFiles" style="margin: 10px 0;">
	              
	              </div>
	           <input type="button" id="btnDeleteFile" value="선택삭제"/> 
	          <%} %> 
	              <textarea id="content" name="name" style="width:95%;" rows="19" class="form-control" placeholder="내용"><% if(IU.equals("update")) out.print(board.getB_content()); %></textarea>
              
              <% if(IU.equals("update")) {%>
              <button id="btnUpdate" data-bid="<%=board.getB_id()%>" type="button" name="button" class="btn" style="width:10%; background-color:#F8B195; color:white; margin-top:20px;">수정하기</button>
              <%} else {%>
              <button id="btnWrite" type="button" name="button" class="btn" style="width:10%; background-color:#F8B195; color:white; margin-top:20px;">글등록</button>
              <%} %>
              <a href="freeBoard.jsp">
                <button id="btnCancel" type="button" name="button" class="btn" style="width:10%; background-color:#F8B195; color:white; margin-top:20px;">등록취소</button>
              </a>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>

</html>
