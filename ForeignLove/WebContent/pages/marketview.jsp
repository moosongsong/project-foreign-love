<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.co.foreignlove.controller.DAOManager"%>
<%@ page import="kr.co.foreignlove.dao.BoardMarketDAO"%>
<%@ page import="kr.co.foreignlove.vo.BoardMarketVO"%>
<%@ page import="kr.co.foreignlove.vo.MemberVO"%>
<%@ page import="kr.co.foreignlove.vo.AttachedVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.co.foreignlove.dao.AttachedDAO"%>
<%@ page import="kr.co.foreignlove.vo.BoardTypeVO"%>

<!DOCTYPE html>
<html>

<%
	int id = Integer.parseInt(request.getParameter("id"));
	BoardMarketDAO dao = (BoardMarketDAO) DAOManager.getDAO(BoardMarketDAO.NAME);
	dao.count(id);
	
	BoardMarketVO board = dao.getBoard(id);
	BoardTypeVO boardType = board.getBoardType();
	
	ArrayList<AttachedVO> attachedList = null;
	AttachedDAO adao = (AttachedDAO) DAOManager.getDAO(AttachedDAO.NAME);
	attachedList = adao.getAttached(boardType, board.getB_id());
	
	
	MemberVO member = null;
try {
	member = (MemberVO) session.getAttribute("loginUserInfo");
} catch (NullPointerException e) {
	response.sendRedirect("login.jsp");
}
%>

<style>
	#replyList button {
		margin-left: 3px;
	}
</style>

<head>

<title>Market view</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>


<link
	href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>

<link rel="stylesheet" href="../css/mycss.css">
<script src="../js/boardMarketScript/marketview.js"></script>
<script type="text/javascript" src="../js/memberScript/index.js"></script>


</head>

<body class="container-fluid ppojjak" data-b_id="<%=board.getB_id()%>">
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
					<td id="left"
						style="width: 20%; margin-top: 0px; padding: 0; vertical-align: top;">
						<div class="" style="padding: 0; margin: 0 10px;">
							<!--cate name-->
							<div class="well"
								style="margin-top: 10px; width: 100%; text-align: center">
								<h5>장터게시판</h5>
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
								<button id="btnNew" type="button" name="button" class="btn"
									style="width: 100%; margin-top: 5px; color: white; background-color: #F8B195;"
									onclick="location.href='marketwrite.jsp?IU=insert'">글쓰기</button>
								<button type="button" id="goDM" class="btn"
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
					<td id="right" style="width: 70%; padding: 0; margin: 0;">
						<!--글정보-->
						<div class=""
							style="width: 97%; padding: 0; margin: 0; margin-left: 10px; margin-top: 10px;">

							<!--title-->
							<div class="page-header" id="title">
								<h3>
									<%=board.getB_title()%>
								</h3>
							</div>

							<!--content-->
							<div class="panel panel-default" id="panel">
								<!-- header -->
								<div class="panel-heading" id="extra">
									<div class="" style="display: inline-block; width: 30%;">
										조회수:<%=board.getB_count()%>
									</div>
									<div class="" style="display: inline-block; width: 28%;">
										작성자:
										<%=board.getMember().getM_nick()%>
									</div>
									<div class="" style="display: inline-block; width: 38%; text-align: right">
										<%
											if (board.getMember().getM_id() != member.getM_id()) {
										%>
										<button id="btnSendDM" type="button" name="btnSendDM"
											class="btn" style="font-size: 12px"
											onclick="location.href='dmWriteAsMember.jsp?receiver_id=<%=board.getMember().getM_id()%>&IU=update'">쪽지보내기</button>
										<%
											}
										%>
										<button type="button" id="btnLikey" name="btnUpdate"
											class="btn btnLikey" style="width: 30%; font-size: 12px"
											data-r_id="0">좋아요</button>
									</div>
								</div>
								
								<!-- body -->
								<div class="panel-body" id="content">
									<div class="well" style="display: inline-block; width: 40%; margin: 0;">
										<!--여기에 이미지들을 작성 -->
										<div>
											<%
												if (attachedList.size() != 0) {
												for (int i = 0; i < attachedList.size(); i++) {
													String name = attachedList.get(i).getA_name();
											%>
											<img name="image" src="<%=BoardMarketVO.UPFOLDER + name%>" />
											<%
												}
											%>
											<%
												}else{
											%>
											<div style="height: 100px">이미지 없음</div>
											<%} %>
										</div>
									</div>
									
									<!-- 내용 -->
									<div class="" style="display: inline-block; width: 59%; vertical-align: top; padding: 0">
										<div class="well">
											판매가격:
											<%=board.getMk_price()%>
										</div>
										<%=board.getB_content()%>
									</div>
								</div>
								
								<!-- footer -->
								<div class="panel-footer" id="buttonSpace">
									<div class="" style="display: inline-block; width: 40%;">
										이메일 :
										<%=board.getMember().getM_email()%>
									</div>
									<div class="" style="display: inline-block; width: 55%;">
										주소 :
										<%=board.getMember().getM_addr()%>
									</div>
									<div class="" style="display: inline-block; width: 40%;">
										학교 :
										<%=board.getMember().getS_id().getS_name()%>
									</div>
									<div class="" style="display: inline-block; width: 40%;">
										작성일자 :
										<%=board.getB_post()%>
									</div>
								</div>
							</div>

							<!--buttons-->
							<%
								if ((board.getMember().getM_id() == member.getM_id())) {
							%>
								<div class="" style="padding: 0; margin: 0px 0 10px 0;">
								<button id="btnUpdate" type="button" name="btnUpdate"
									class="btn" style="width: 10%;"
									onclick="location.href='marketwrite.jsp?id=<%=id%>&IU=update'">수정하기</button>
							<%
								}
								if(member.getM_type().getM_type().equals("MANAGER") || (board.getMember().getM_id() == member.getM_id()))
								{
							%>
									<button id="btnDelete" type="button" name="btnDelete"
									class="btn" data-id="<%=board.getB_id()%>" style="width: 10%;">삭제하기</button>

							</div>
							<%
								}
							%>
								<!--댓글-->
							<div class="" id="replyList" style="margin: 0; padding: 0;">
								</div>
							<fieldset>
								<legend>댓글 쓰기</legend>
								<div class="well">
									<textarea id="replyContent"
										style="width: 90%; display: inline-block;"
										class="form-control"></textarea>
									<button type="button" id="btnReplyInsert" class="btn"
										style="width: 9%; display: inline-block;">등록</button>
								</div>
							</fieldset>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>

</html>
