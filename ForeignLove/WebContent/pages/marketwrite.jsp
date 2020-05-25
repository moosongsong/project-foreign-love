<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.foreignlove.controller.DAOManager" %>
<%@ page import="kr.co.foreignlove.dao.BoardMarketDAO" %>
<%@ page import="kr.co.foreignlove.vo.BoardMarketVO" %>    
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<%
   String IU = request.getParameter("IU");
   BoardMarketVO board = null;
   if(IU.equals("update")) {
	 int id = Integer.parseInt(request.getParameter("id"));
	 BoardMarketDAO dao = (BoardMarketDAO)DAOManager.getDAO(BoardMarketDAO.NAME);
	 board = dao.getBoard(id);
   }
%>

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
  	
  	<link rel="stylesheet" href="../css/mycss.css">
  	<script type="text/javascript" src="../js/boardMarketScript/marketwrite.js"></script>
  	<script type="text/javascript" src="../js/memberScript/index.js"></script>
</head>

<body class="container-fluid" style="margin:0; padding:0;">
  <div class="" style="margin:0;padding:0;width:100%;background-color:#F8B195;height:50px;">
    <!--menutable-->
    <nav class="navbar navbar-dark" style="background-color:#F8B195;float:left;">
      <!--logo-->
      <a href="../index.jsp" class="">
        <img src="../images/logo.png" alt="nonic" width="30px"></a>
		<a href="index.jsp" class="" style="margin:0 5px 0 15px"> <img src="../images/title.png" alt="nopic" height="20px"></a>
      <!--menubar-->
      <ul class="" style="margin:0;padding:0;">
        <!--home-->
        <li class="" style="float:left;display:inline-block;margin:0;padding:0;">
          <a href="index.jsp" class="nav-link" style="color:white;">HOME</a>
        </li>
        <!--market-->
				<li class="dropdown ppojjak" style="float: left; display: inline-block;">
					<a href="marketBoard.jsp" class="nav-link" style="color: white;">MARKET</a>
				</li>
        <!--free-->
        <li class="" style="float:left;display:inline-block;margin:0;padding:0">
          <a href="freeBoard.jsp" class="nav-link" style="color:white;">FREE</a>
        </li>
        <!--DM-->
        <li class="" style="float:left;display:inline-block;margin:0;padding:0">
          <a href="dmBoard.jsp" class="nav-link" style="color:white;">쪽지</a>
        </li>
        <!--space-->
        <li class="" style="float:left;display:inline-block;margin:0;padding:0">
          <a href="" class="nav-link" style="color:white;"> </a>
        </li>
        <li class="" style="float:left;display:inline-block;margin:0;padding:0">
          
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
                <button  id="btnNew" type="button" name="button" class="btn" onclick="location.href='marketwrite.jsp?IU=insert'" style="width:100%; margin-top:5px; color:white; background-color:#F8B195;">글쓰기</button>
                <button type="button" id="goDM" class="btn" style="width:100%; margin-top:5px; color:white; background-color:#F8B195;">쪽지함
                  <span class="badge badge-pill badge-danger">18</span>
                </button>
              </div>
            </div>
          </td>
          <!--right-->
          <td style="width:70%" style="width:100%;padding:0;margin:0;">
            <div class="" style="padding:0;margin-right:10px;margin-left:10px">
              <input id="marketTitle" type="text" name="" value="<% if(IU.equals("update")) { out.print(board.getB_title());}%>" placeholder="상품명" style="margin:10px 10px 10px 0; width:40%;display:inline;" class="form-control">
              
              <!--글분류선택-->
              <div class="" style="padding:0;margin:0;">
                <select id="marketType" class="form-control" name="" style="width:150px;margin-bottom:10px;display:inline;">
                  <option value="0">글분류</option>
                  <option value="SELL">팔아요</option>
                  <option value="BUY">구해요</option>
                </select>
                <select id="lowType" class="form-control" name="" style="width:150px;margin-bottom:10px;display:inline;">
                  <option value="0">소분류</option>
                  <option value="FURNIB">가구(대형)</option>
                  <option value="FURNIS">가구(소형)</option>
                  <option value="SUPPLY">생활용품</option>
                  <option value="BOOK">서적</option>
                  <option value="ETC">기타</option>
                </select>
                <select id="tradeWay" class="form-control" name="" style="width:150px;margin-bottom:10px;display:inline;">
                  <option value="0">거래방법</option>
                  <option value="DELIVER">직접거래</option>
                  <option value="DIRECT">배송거래</option>
                </select>
              </div>
              <div class="" style="padding:0;margin:0;">
                판매가격 <input id="price" type="text" name="" value="<%if(IU.equals("update"))out.print(board.getMk_price()); %>" class="form-control" placeholder="달러" style="width:150px;display:inline;margin-bottom:10px;">
              </div>
               <form id="plusFileForm">
	              
		              <input type="file" id="fileChoose" name="file" />
		              <input type="button" id="btnPlusFile" value="추가"/>
		         
	          </form>
	          <div id="attachedFiles">
	          
	          </div>
              <textarea id="marketContent" name="name" style="width:95%;" rows="17" class="form-control" placeholder="정확한 상품 상태와 사진 첨부는 필수입니다. 그밖의 상품 특이사항에 대해 기재해주세요."><% if(IU.equals("update")) out.print(board.getB_content()); %></textarea>
   </div>
            <div class="" style="padding:0;margin:0px;margin-left:10px">
             <% if(IU.equals("update")) {%>
             <button id="btnUpdate" data-bid="<%=board.getB_id()%>" type="button" name="button" class="btn" style="width:10%; background-color:#F8B195; color:white; margin-top:20px;">수정하기</button>
             <% } else { %>
              <button id="marketWrite" type="button" name="button" class="btn" style="width:10%; background-color:#F8B195; color:white; margin-top:20px;">글등록</button>
              <% } %>
              <a href="marketBoard.jsp">
                <button id="marketWriteCancel" type="button" name="button" class="btn" style="width:10%; background-color:#F8B195; color:white; margin-top:20px;">등록취소</button>
              </a>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>

</html>

