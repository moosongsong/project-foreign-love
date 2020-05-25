
String.prototype.isEmpty= function() {
	return (this.trim() == '');
}

$.fn.clearAndFocus = function(message) {
	alert(message);
	$(this).val('').focus();
}

String.prototype.equals = function(str) {
	return(this == str);
}

$(document).ready(function() {
	var EmailCheckNUM=false;
	var NickCheckNUM=false;
		
	$('#popup').addClass('cantsee');
	$('#popdown').addClass('cansee');
	
	$('#emailCheck').click(function() {
		$.ajax({
			url: '../FindEmail.do',
			type: 'post',
			data: {
				m_email: $('#m_email').val(),
				ori_email: $('#ori_email').val()
			},
			success: function(data) {
				if(data.result == true){
					EmailCheckNUM=true;
					alert("사용이 가능한 이메일입니다.");
				}
				else{
					EmailCheckNUM=false;
					alert("사용이 불가능한 이메일입니다.");
				}
				
			}
		});
	});
	
	$('#nickCheck').click(function() {
		$.ajax({
			url: '../FindNick.do',
			type: 'post',
			data: {
				m_nick: $('#m_nick').val()
			},
			success: function(data) {				
				if(data.result == true){
					NickCheckNUM=true;
					alert("사용이 가능한 닉네임입니다.");
				}
				else{
					NickCheckNUM=false;
					alert("사용이 불가능한 닉네임입니다.");
				}
				
			}
		});
	});
	
	$('#schoolCheck').click(function() {
		$('#popup').removeClass('cantsee');
		$('#popdown').removeClass('cansee');
		
		$('#popup').addClass('cansee');
		$('#popdown').addClass('cantsee');
	});
	
	$('#check').click(function() {		
		$( '#tb > tbody').empty();
		
		if($('#schoolTemp').val().isEmpty()){
			$('#schoolTemp').clearAndFocus("검색어를 입력해주세요!");
			return;
		}

		$.ajax({
			url:'../SchoolList.do',
			type:'post',
			data:
			{
				m_school: $('#schoolTemp').val()
			},
			success: function(data)
			{
				var aray = data.schools;
				
				$.each(aray, function (index, item) { 
					let tr = $('<tr/>').append('<td>'+item.s_name+'</td>')
										.append('<td>'+item.s_nation+'</td>')
										.append('<td>'+item.s_state+'</td>')
										.append('<td>'+item.s_id+'</td>');
					tr.click(function() {	
						$('#schoolTemp').val('');
						$('#school').val($(this).children().eq(0).text());
						$('#nation').val($(this).children().eq(1).text());
						$('#local').val($(this).children().eq(2).text());
						$('#m_school').val($(this).children().eq(3).text());
						
						$('#popup').removeClass('cansee');
						$('#popdown').removeClass('cantsee');
						
						$('#popup').addClass('cantsee');
						$('#popdown').addClass('cansee');
						$( '#tb > tbody').empty();
					});
					tr.appendTo('table');
				});
			}
		});		
					
	});

	
	$('#schoolDone').click(function() {
		$('#popup').removeClass('cansee');
		$('#popdown').removeClass('cantsee');
		
		$('#popup').addClass('cantsee');
		$('#popdown').addClass('cansee');
	});
	
	$('#registerBtn').click(function() {
		if($('#m_email').val().isEmpty()){
			$('#m_email').clearAndFocus("이메일을 입력해주세요!");
			return;
		}
		
		if($('#pass').val().isEmpty()){
			$('#pass').clearAndFocus("비밀번호를 입력해주세요!");
			return;
		}
		
		if(!$('#pass').val().equals($('#pass2').val())){
			$('#pass2').clearAndFocus("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		if($('#m_nick').val().isEmpty()){
			$('#m_nick').clearAndFocus("닉네임을 입력해주세요!");
			return;
		}
		
		if($('#m_name').val().isEmpty()){
			$('#m_name').clearAndFocus("본명을 입력해주세요!");
			return;
		}
		
		if($('#m_phone').val().isEmpty()){
			$('#m_phone').clearAndFocus("전화번호를 입력해주세요!");
			return;
		}
		
		if($('#birth').val().isEmpty()){
			$('#birth').clearAndFocus("생년월일을 입력해주세요!");
			return;
		}
		
		if($('#school').val().isEmpty()){
			$('#school').clearAndFocus("학교를 설정해주세요!");
			return;
		}
		
		if($('#from').val().isEmpty()){
			$('#from').clearAndFocus("교환시작 일자를 설정해주세요!");
			return;
		}
		
		if($('#addr1').val().isEmpty()){
			$('#addr1').clearAndFocus("주소를 입력해주세요!");
			return;
		}
		
		if(NickCheckNUM==false){
			alert("닉네임 중복확인을 해주세요!");
		}

		if(EmailCheckNUM==false){
			alert("이메일 중복확인을 해주세요!");
		}
		
		if($('#join_user_agree').is(':checked')==false){
			alert("약관동의를 하지 않아 가입이 불가합니다.");
			return;
		}

		
		$.ajax({
			url: '../Register.do',
			type: 'post',
			data: {
				m_email: $('#m_email').val(),
				ori_email: $('#ori_email').val(),
				m_pass: $('#pass').val(),
				m_name: $('#m_name').val(),
				m_phone: $('#m_phone').val(),
				m_birth: $('#birth').val(),
				m_nick: $('#m_nick').val(),
				m_startDate: $('#from').val(),
				m_sex:$('input[name=m_sex]').val(),
				addr1:$('[name=addr1]').val(),
				addr2:$('[name=addr2]').val(),
				m_school:$('#m_school').val()
			},
			success: function(data) {
				if(data.result == true){
					alert("회원가입에 성공하였습니다.로그인해주세요!");
					location.href = 'login.jsp';
				}
				else{
					alert("회원가입에 실패하였습니다.")
					location.href = 'register.jsp';
				}
				
			}
		});
	});
});


function openZipSearch() {
	new daum.Postcode({
		oncomplete: function(data) {
			$('[name=zip]').val(data.zonecode); // 우편번호 (5자리)
			$('[name=addr1]').val(data.address);
			$('[name=addr2]').val(data.buildingName);
		}
	}).open();
}