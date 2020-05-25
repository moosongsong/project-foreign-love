<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.foreignlove.dao.BoardPromotionDAO" %>
<%@ page import="kr.co.foreignlove.vo.BoardPromotionVO" %>
<%@ page import="kr.co.foreignlove.controller.DAOManager" %>

<!DOCTYPE html>
<html lang="ko" dir="ltr">
<% 
	String strId = request.getParameter("id");
	int id = ((strId == null || strId.equals("")) ? -1 : Integer.parseInt(strId));
	BoardPromotionDAO dao = (BoardPromotionDAO)DAOManager.getDAO(BoardPromotionDAO.NAME);
	BoardPromotionVO board = (id == -1 ? null : dao.getBoard(id));
%>
<head>
  <title>Free</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jquery CDN -->
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <!-- jquery UI CDN -->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
  <!-- 언어 별 CDN -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.9.2/i18n/jquery.ui.datepicker-ko.min.js"></script>
  <!-- Date 라이브러리 -->
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
  <link rel="stylesheet" href="../css/mycss.css">
  <script type="text/javascript" src="../js/cal.js"></script>
  <link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
  <script src="../js/boardPromotionScript/adwrite.js"></script>
  <script type="text/javascript" src="../js/memberScript/index.js"></script>
</head>

<body class="container-fluid" style="margin:0; padding:0;">
  <div class="" style="margin:0;padding:0;width:100%;background-color:#F8B195;height:50px;">
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
                <h5>홍보게시판</h5>
              </div>
             <div class="card" id="insertPic" style="margin-top: 12px;">
					<div class="card-body" id="profileBox">
						<a href="mypage.jsp" class="card-link">마이페이지 </a>
						<button type="button" id="logoutBtn" class="btn" style="width: 100%;height:30px; margin-top: 10px; color: white; background-color: #F8B195;font-size:12px;">로그아웃</button>
					</div>
				</div>

              <!--buttons-->
              <div class="">
                <button type="button" name="button" class="btn" onclick="location.href='adwrite.jsp?IU=insert'" style="width:100%; margin-top:5px; color:white; background-color:#F8B195;">글쓰기</button>
                <button type="button" id="goDM" class="btn" style="width:100%; margin-top:5px; color:white; background-color:#F8B195;">쪽지함
                  <span class="badge badge-pill badge-danger">18</span>
                </button>
              </div>
			</div>
          <!--right-->
          <td style="width:70%" style="width:100%;padding:0;margin:0;">
            <div class="" style="width:100%;padding:0;margin:0;margin-left:10px;margin-top:3px;">
              <input type="text" id="title" name="" value="<%if(id != -1) out.print(board.getB_title());%>" placeholder="제목" style="margin:10px 10px 10px 0; width:40%;display:inline;" class="form-control">

              <!--글분류선택-->
              <div class="" style="padding:0;margin:0;">
                <select class="form-control" id="pType" name="" style="width:150px;margin-bottom:5px;margin-right: 5px;display:inline;">
                  <option value="0">글분류</option>
                  <option value="1">모임</option>
                  <option value="2">축제</option>
                  <option value="3">스터디</option>
                </select>
             	<input type="text" id="tagTree" class="form-control" placeholder="태그명" style="width:10%; display:inline; margin:3px;">
             	<button type="button" id="btnAddTag" class="btn">추가</button>
              </div>
              
             
              
              <div class="" style="padding:0;margin:0;margin-bottom:5px;">
                <input type="text" id="from" class="form-control" value="<%if(id != -1) out.print(board.getB_startDate().substring(0, 10)); %>" placeholder="시작일" style="width:20%; display:inline; margin:3px;"> ~
                <input type="text" class="form-control" id="to" value="<%if(id != -1) out.print(board.getB_endDate().substring(0, 10)); %>" placeholder="종료일" style="width:20%; display:inline; margin:3px;">
                
                <input type="hidden" id="birthday" class="form-control" size="30">
				<input type="hidden" id="alternateFrom" class="form-control" size="30">
				<input type="hidden" id="alternateTo" class="form-control" size="30">
              </div>
               
              <form id="plusFileForm">
		              <input type="file" id="fileChoose" name="file" />
		              <input type="button" id="btnPlusFile" class="btn" value="추가"/>
	          </form>
	              <div id="attachedFiles" style="margin: 10px 0;">
	              
	              </div>
	           <input type="button" id="btnDeleteFile" class="btn" value="선택삭제"/> 
	          
              
              <textarea name="name" id="content" style="width:95%;" rows="16" class="form-control" placeholder="내용을 기입해주세요."><%if(id != -1) out.print(board.getB_content()); %></textarea>
            </div>
            <div class="" style="padding:0;margin:0 0 30px 10px;">
              <button type="button" id="btnIU" name="button" class="btn" style="width:10%; background-color:#F8B195; color:white; margin-top:20px;">
              <%
             	 if(id != -1)
             	 {
             		 out.print("글수정");
             	 }
             	 else
             	 {
             		 out.print("글등록");
             	 }
              %>
              </button>
              <a href="adBoard.jsp">
                <button type="button" name="button" class="btn" style="width:10%; background-color:#F8B195; color:white; margin-top:20px;">
              <%
             	 if(id != -1)
             	 {
             		 out.print("수정취소");
             	 }
             	 else
             	 {
             		 out.print("등록취소");
             	 }
              %>
                </button>
              </a>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>

</html>
