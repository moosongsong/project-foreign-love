$(document).ready(function() {
	$('body').append('<div class="" style="width: 100%;height:100px;text-align:center;">projected by TEAM FALLINLOVE</div>');
	
	$.ajax({
		url: '../ViewIndexProfile.do',
		type: 'post',
		data: {
			
		},
		success: function(data) {
			let obj = JSON.parse(data);	
			console.log(obj);
			if(obj.result == true){
				let str = '../stateFlag/'+obj.schoolState.trim()+'.png';
				$('#insertPic').prepend('<img src="'+str+'" alt="../images/gray.ipg" class="card-img-top">');
				$('#profileBox').prepend('<p class="card-text">'+obj.schoolName+'</p>');
				$('#profileBox').prepend('<h5 class="card-title">'+obj.m_nick+'</h5>');
				$('#stateName').append('<h5 class="card-title">'+'UNIV IN '+obj.schoolState+'</h5>');
			}
			else{
				location.href = 'login.jsp';
			}
		}
	});
	
	$.ajax({
		url: '../HowManuDM.do',
		type: 'post',
		data: {
			
		},
		success: function(data) {
			console.log(data);
			if(data.result == true){
				$('#goDM span').html(data.num);
			}
			else{
				$('#goDM span').html(0);
			}
		}
	});
	
	$('#logoutBtn').click(function() {
		alert("로그아웃 되었습니다! 다음에 또 만나요!");
		$.ajax({
			url: '../Logout.do',
			type: 'post',
			data: {
				
			},
			success: function(data) {
				let obj = JSON.parse(data);		
				if(obj.result == true){
					location.href = 'login.jsp';
				}
				else{
					location.href = 'index.jsp';
				}
			}
		});
	});
	
	$('#goDM').click(function(){
		location.href = 'dmBoard.jsp';
	});
	
	$('button[name=searchbutton]').click(function() {
		//alert($('input[name=searchInput]').val());
		//location.href='totalBoard.jsp?str='+$('input[name=searchInput]').val()+'';
	});
	
});