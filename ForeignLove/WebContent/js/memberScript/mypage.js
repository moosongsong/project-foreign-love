
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
	$('#popup').addClass('cantsee');
	$('#popdown').addClass('cantsee');
	$('#blocking').addClass('cansee');
	
	$.ajax({
		url: '../ViewMypage.do',
		type: 'post',
		data: {
			
		},
		success: function(data) {
			let obj = JSON.parse(data);		
			if(obj.result == true){
				$('#m_email').val(obj.m_email);
				$('#m_name').val(obj.m_name);
				$('#m_phone').val(obj.m_phone);
				$('#m_birth').val(obj.m_birth);
				$('#m_nick').val(obj.m_nick);
				$('#school').val(obj.schoolName);
				$('#nation').val(obj.schoolNation);
				$('#local').val(obj.schoolState);
				$('#m_school').val(obj.schoolNum);
				$('#from').val(obj.m_startDate);
				$('#m_addr').val(obj.m_addr.trim());
				$('#reg').append('<p>'+'가입일시 : '+obj.m_regDate+'</p>');
				$('#hiddenReg').val(obj.m_regDate);
			}
			else{
				location.href = 'login.jsp';
			}
		}
	});
	
	$('#updateBtn').click(function() {
		if($('#m_email').val().isEmpty()){
			$('#m_email').clearAndFocus("이메일을 입력해주세요!");
			return;
		}
		
		if($('#pass').val().isEmpty()){
			$('#pass').clearAndFocus("비밀번호를 입력해주세요!");
			return;
		}
		
		if(!$('#pass').val().equals($('#pass2').val())){
			$('#pass2').clearAndFocus("비밀번호가 일치하지 않습니다.!");
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
		
		if($('#m_birth').val().isEmpty()){
			$('#m_birth').clearAndFocus("생년월일을 입력해주세요!");
			return;
		}
		
		if($('#m_school').val().isEmpty()){
			$('#m_school').clearAndFocus("학교를 설정해주세요!");
			return;
		}
		
		if($('#from').val().isEmpty()){
			$('#from').clearAndFocus("교환시작 일자를 설정해주세요!");
			return;
		}
		
		if($('#m_addr').val().isEmpty()){
			$('#m_addr').clearAndFocus("주소를 입력해주세요!");
			return;
		}

		$.ajax({
			url: '../MemberUpdate.do',
			type: 'post',
			data: {
				m_email:$('#m_email').val(),
				m_pass: $('#pass').val(),
				m_phone: $('#m_phone').val(),
				m_nick: $('#m_nick').val(),
				m_startDate: $('#from').val(),
				addr1:$('[name=addr1]').val(),
				addr2:$('[name=addr2]').val(),
				m_school:$('#m_school').val(),
				m_regDate:$('#hiddenReg').val()
			},
			success: function(data) {

				let obj = JSON.parse(data);
				if(obj.result == 0){
					alert("정보수정 성공!");
					location.href = 'mypage.jsp';
				}
				else{
					alert("정보변경 실패...ㅠㅠ 한번더 도전해주세요!");
					location.href = 'mypage.jsp';
				}
				location.reload();
			}
		});	
	});
	
	$('#deleteBtn').click(function() {

		if (confirm("정말  탈퇴하시겠습니까?") == true){
			$.ajax({
				url: '../MemberDelete.do',
				type: 'post',
				data: {
					m_email:$('#m_email').val()
				},
				success: function(data) {
					let obj = JSON.parse(data);
					
					if(obj.result == 0){
						alert("탈퇴되었습니다. 그동안 이용해주셔서 감사합니다.");		
						location.href = 'login.jsp';
					}
					else{
						alert("휴...다행ㅠㅠ");		
						location.href = 'mypage.jsp';
					}
				}
			});
		}else{ 
		    
		}

		
	});
	
	$('#openBlocking').click(function() {
		$.ajax({
			url: '../IsItYou.do',
			type: 'post',
			data: {
				passtemp:$('#passtemp').val()
			},
			success: function(data) {
				console.log(data);
				let obj = JSON.parse(data);
				alert(obj.message);
				
				if(obj.result == true){
					$('#popup').removeClass('cansee');
					$('#popdown').removeClass('cantsee');
					$('#blocking').removeClass('cansee');
					
					$('#popup').addClass('cantsee');
					$('#popdown').addClass('cansee');
					$('#blocking').addClass('cantsee');
				}
				else{
					$('#passtemp').val('').focus();
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
				console.log(data);
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
					tr.appendTo('#tb');
				});
			}
		});		
					
	});
	
	$('#schoolDone').click(function() {
		$('#popup').removeClass('cansee');
		$('#popdown').removeClass('cantsee');
		
		$('#tb tbody').empty();
		$('#schoolTemp').val('');
		
		$('#popup').addClass('cantsee');
		$('#popdown').addClass('cansee');
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