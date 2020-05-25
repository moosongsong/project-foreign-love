
String.prototype.isEmpty= function() {
	return (this.trim() == '');
}

$.fn.clearAndFocus = function(message) {
	alert(message);
	$(this).val('').focus();
}


$(document).ready(function() {
	$('.well').hide();
	$('#sendEmail').click(function() {
		if($('#m_email').val().isEmpty()){
			$('#m_email').clearAndFocus("이메일을 입력해주세요!");
			return;
		}
		$.ajax({
			url: '../FindPassword.do',
			type: 'post',
			data:{
				m_email:$('#m_email').val()
			},
			success: function(data) {
				$('.well').show();
				let obj = JSON.parse(data);
				alert(obj.message);
			}
		});
	});
	
	$('#logpage').click(function() {
		location.href = 'login.jsp';
	});	
});


