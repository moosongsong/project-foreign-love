<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
	<title>my page</title>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />	
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>

	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<!-- jquery CDN -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<!-- jquery UI CDN -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<!-- 언어 별 CDN -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.9.2/i18n/jquery.ui.datepicker-ko.min.js"></script>
	<!-- Date 라이브러리 -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	
	<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	
	<!-- address -->
	<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
	
	<link rel="stylesheet" href="../css/index.css">
	<script type="text/javascript" src="../js/cal.js"></script>
	<script type="text/javascript" src="../js/memberScript/index.js"></script>
	<script type="text/javascript" src="../js/memberScript/mypage.js"></script>
</head>

<body class="container-fluid ppojjak">
	<div class="ppojjak" style="width: 100%; background-color: #F8B195; height: 50px;">
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
					<form class="form-inline ppojjak" action="#" style="width: 270px;">
						<input type="text" name="searchInput" class="form-control" placeholder="search" style="margin-right: 3px; width: 200px;">
						<button type="submit" name="searchbutton" class="btn btn-outline-light" style="backgroun-color: white; width: 50px;">GO</button>
					</form>
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
							<!--PROFILE-->
							<div class="card" id="insertPic" style="margin-top: 12px;">
								<div class="card-body" id="profileBox">
									<a href="mypage.jsp" class="card-link">마이페이지 </a>
									<button type="button" id="logoutBtn" class="btn" style="width: 100%;height:30px; margin-top: 10px; color: white; background-color: #F8B195;font-size:12px;">로그아웃</button>
								</div>
							</div>

							<!--buttons-->
							<div class="">
								<button type="button" name="button" class="btn"
									style="width: 100%; margin-top: 5px; color: white; background-color: #F8B195;">글쓰기</button>
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
						<!-- block -->
						<div class="" id="blocking" style="width: 50%; margin: 20px;">
							<div>
								<table>
									<tr>
										<td colspan="2">마이페이지 확인을 위해서는 비밀번호 입력이 필요합니다.</td>
									</tr>
									<tr>
										<td>비밀번호 :</td>
										<td>
											<input type="password" id="passtemp" value="" class="form-control" placeholder="비밀번호" style="margin: 2px;">
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<button type="button" class="btn" id="openBlocking"
																	style="background-color: #F8B195; color: white;margin:0 0 0 40%;">확인</button>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<!--popup-->
						<div class="" id="popup" style="width: 70%; margin: 20px;">
							<div class="">
								<h2>학교찾기</h2>
								<input type="text" name="m_school" id="schoolTemp" class="form-control" placeholder="학교명" style="margin: 2px; width: 60%; display: inline;">
								<button type="button" id="check" class="btn" style="background-color: #C06C84; color: white; margin: 2px; width: 30%; display: inline;">검색</button>
							</div>
							<div class="">
								<div>
									<table class="table table-hover" id="tb">
										<thead>
											<tr>
												<th>학교명</th>
												<th>국가</th>
												<th>세부지역</th>
												<th>학교번호</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
								<button type="button" name="button" class="btn" id="schoolDone"
									style="width: 150px; margin: 20px 0; background-color: #355C7D; color: white;">학교찾기
									취소</button>
							</div>
						</div> 
						<!-- popdown -->
						<div class="" style="width: 600px; padding: 0; margin: 0 0 0 10px;" id="popdown">
							<div class="" style="width: 100%; padding: 0; margin: 0;"></div>
							<div class="" style="padding: 0; margin: 0;">
								<div class="" style="padding: 12px 12px 0 0; margin: 0;">
									<img src="../images/my.png" alt="" height="20px"
										style="padding: 0; margin-bottom: 8px;">
									<div class="" style="padding: 0; margin: 0;">
										<div class="" style="padding: 0; margin: 0;">

											<!--id-->
											<div class="" style="padding: 0; margin: 0;" id="mypageInfo">
												<table style="padding: 0; margin: 0 0 30px 0;">
													<tbody style="padding: 0; margin: 0;">
														<tr style="padding: 0; margin: 0;">
															<td>이메일 : </td>
															<td style="width:300px">
																<input type="text" id="m_email" value="" class="form-control" disabled style="margin: 2px;">
															</td>
														</tr>
														<tr>
															<td>비밀번호 :</td>
															<td>
																<input type="password" id="pass" value="" class="form-control" placeholder="비밀번호" style="margin: 2px;">
															</td>
														</tr>
														<tr>
															<td>비밀번호 확인 : </td>
															<td>
																<input type="password" id="pass2" value="" class="form-control" placeholder="비밀번호 확인" style="margin: 2px;"> 
															</td>
														</tr>
														<tr>
															<td>본명 : </td>
															<td>
																<input type="text" id="m_name" value="" class="form-control" disabled style="margin: 2px;"> 
															</td>
														</tr>
														<tr>
															<td>휴대전화 번호 : </td>
															<td>
																<input type="text" id="m_phone" value="" class="form-control" style="margin: 2px;"> 
															</td>
														</tr>
														<tr>
															<td>생년월일</td>
															<td>
																<input type="text" id="m_birth" value="" class="form-control" disabled style="margin: 2px;"> 
																<input type="hidden" id="birthday" class="form-control" size="30">
															</td>
														</tr>
														<tr>
															<td>닉네임 : </td>
															<td>
																<input type="text" id="m_nick" value="" class="form-control" style="margin: 2px;" disabled>	
															</td>
														</tr>
														<tr>
															<td>학교 : </td>
															<td>
																<input type="text" id="school" class="form-control" disabled style="margin: 2px;">
																<input type="hidden" name="m_school" id="m_school">
															</td>
														</tr>
														<tr>
															<td></td>
															<td>
																<button type="button" name="schoolCheck" id="schoolCheck" style="background-color: #6C5B7B; color: white; margin: 2px;" class="btn">학교찾기</button>
															</td>
														</tr>
														<tr>
															<td>국가 : </td>
															<td>
																<input type="text" id="nation" class="form-control" placeholder="국가" style="margin: 2px;" disabled> 
															</td>
														</tr>
														<tr>
															<td>세부지역 : </td>
															<td>
																<input type="text" id="local" class="form-control" placeholder="세부지역" style="margin: 2px;" disabled> 
															</td>
														</tr>
														<tr>
															<td>교환시작 일자 : </td>
															<td>
																<input type="text" id="from" class="form-control" style="width: 91%; display: inline; margin: 2px;">
																<input type="hidden" id="alternateFrom" class="form-control" size="30"> 
															</td>
														</tr>
														<tr>
															<td>주소 : </td>
															<td>
																<input type="text" name="addr1" id="m_addr" style="margin: 2px;display: inline-block" class="form-control" />
																<input type="hidden" name="zip" style="; display: inline-block;margin: 2px;" class="form-control"  disabled />
															</td>
														</tr>
														<tr>
															<td>상세 : </td>
															<td>
																<input type="text" name="addr2" style="margin: 2px;display: inline-block" class="form-control"/>
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<button type="button" class="btn" style="width:100%" onclick="openZipSearch()">한국주소로 입력을 원할 경우 여기를 눌러주세요</button>
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<div class="" style="height: 20px; margin: 10px 0 0 0;" id="reg"></div>
																<input type="hidden" disabled id="hiddenReg"/>
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<button type="button" class="btn" id="updateBtn"
																	style="background-color: #F8B195; color: white; margin-top: 20px;">수정완료</button>
																<a href="index.jsp">
																	<button type="button" class="btn"
																		style="background-color: #F8B195; color: white; margin-top: 20px;">수정취소</button>
																</a>
																<button type="button" class="btn" id="deleteBtn"
																	style="background-color: #F8B195; color: white; margin-top: 20px;">탈퇴하기</button>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
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
