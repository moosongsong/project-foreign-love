$(document).ready(function() {
	$( '#t1 > thead').empty();
	$( '#t1 > tbody').empty();
	
	let trh = $('<tr><th width="10%">쪽지번호</th><th width="20%">보낸사람</th><th width="40%">내용</th><th width="20%">일시</th><th width="10%">읽음확인</th></tr>');
	trh.appendTo('#t1 > thead');
	
	$.ajax({
		url:'../ShowDmAllReceived.do',
		type:'get',
		data:
		{
			con:'con'
		},
		success: function(data)
		{
			var aray = data.dms;
			$('#send').removeClass('active');
			$('#recv').addClass('active');
			$('#Member').removeClass('active');
			
			if(data.result == true){
				$.each(aray, function (index, item) {
					let sampleContent=(item.dm_content).substring(0,10)+'...';
					let temp;
					if(item.dm_isChecked == 0){
						temp = "읽지않음";
					}else{
						temp = "읽음";
					}
					
					let tr = $('<tr><td>'
							+item.dm_id+'</td><td>'+item.sender_id+'</td><td>'
							+sampleContent+'</td><td>'+item.dm_sendDate+'</td><td>'+temp+'</td></tr>');

					tr.click(function() {
						location.href = 'dmView.jsp?dm_id='+item.dm_id;
					});

					tr.appendTo('#t1 > tbody');

				});
			}
		}
	});	
	
	$('#wannaSend').click(function() {
		location.href='dmwrite.jsp';
	});
	
	$('#allRecv').click(function() {
		$( '#t1 > thead').empty();
		$( '#t1 > tbody').empty();
		
		let trh = $('<tr><th width="10%">쪽지번호</th><th width="20%">보낸사람</th><th width="40%">내용</th><th width="20%">일시</th><th width="10%">읽음확인</th></tr>');
		trh.appendTo('#t1 > thead');
		
		$.ajax({
			url:'../ShowDmAllReceived.do',
			type:'get',
			data:
			{
				con:'con'
			},
			success: function(data)
			{
				var aray = data.dms;
				$('#send').removeClass('active');
				$('#recv').addClass('active');
				$('#Member').removeClass('active');
				
				if(data.result == true){
					$.each(aray, function (index, item) {
						let sampleContent=(item.dm_content).substring(0,10)+'...';
						let temp;
						if(item.dm_isChecked == 0){
							temp = "읽지않음";
						}else{
							temp = "읽음";
						}
						
						let tr = $('<tr><td>'
								+item.dm_id+'</td><td>'+item.sender_id+'</td><td>'
								+sampleContent+'</td><td>'+item.dm_sendDate+'</td><td>'+temp+'</td></tr>');

						tr.click(function() {
							location.href = 'dmView.jsp?dm_id='+item.dm_id;
						});

						tr.appendTo('#t1 > tbody');
					});
				}
			}
		});	
	});
	
	$('#allSend').click(function() {
		$( '#t1 > thead').empty();
		$( '#t1 > tbody').empty();
		
		let trh = $('<tr><th width="10%">쪽지번호</th><th width="20%">받은사람</th><th width="40%">내용</th><th width="20%">일시</th><th width="10%">읽음확인</th></tr>');
		trh.appendTo('#t1 > thead');
		
		$.ajax({
			url:'../ShowDmAllSended.do',
			type:'get',
			data:
			{
				con:'con'
			},
			success: function(data)
			{
				var aray = data.dms;
				$('#recv').removeClass('active');
				$('#send').addClass('active');
				$('#Member').removeClass('active');
				
				if(data.result == true){
					$.each(aray, function (index, item) {
						let sampleContent=(item.dm_content).substring(0,10)+'...';
						let temp;
						if(item.dm_isChecked == 0){
							temp = "읽지않음";
						}else{
							temp = "읽음";
						}
						let tr = $('<tr><td>'
								+item.dm_id+'</td><td>'+item.receiver_id+'</td><td>'
								+sampleContent+'</td><td>'+item.dm_sendDate+'</td><td>'+temp+'</td></tr>');

						tr.click(function() {
							location.href = 'dmView.jsp?dm_id='+item.dm_id;
						});
						
						tr.appendTo('#t1 > tbody');
					});
				}
			}
		});	
	});
	
	$('#allMember').click(function() {
		$( '#t1 > thead').empty();
		$( '#t1 > tbody').empty();
		
		let trh = $('<tr><th width="10%">회원번호</th><th width="20%">닉네임</th><th width="20%">이메일</th><th width="30%">학교</th><th width="10%">회원등급</th></tr>');
		trh.appendTo('#t1 > thead');
		
		$.ajax({
			url:'../MemberList.do',
			type:'get',
			data:
			{
				condition: 's_state'
			},
			success: function(data)
			{
				var aray = data.members;
				$('#recv').removeClass('active');
				$('#send').removeClass('active');
				$('#Member').addClass('active');
				
				if(data.result == true){
					$.each(aray, function (index, item) {
						
						let tr = $('<tr><td>'
								+item.m_id+'</td><td>'+item.m_nick+'</td><td>'
								+item.m_email+'</td><td>'+item.s_id+'</td><td>'+item.m_type+'</td></tr>');

						tr.click(function() {
							location.href = 'dmWriteAsMember.jsp?receiver_id='+item.m_id;
						});
						
						tr.appendTo('#t1 > tbody');
					});
				}
			}
		});	
	});
});