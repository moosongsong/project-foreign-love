
String.prototype.isEmpty= function() {
	return (this.trim() == '');
}

$.fn.clearAndFocus = function(message) {
	alert(message);
	$(this).val('').focus();
}


$(document).ready(function() {
	$('#btnRegister').click(function() {
		location.href = 'register.jsp';
	});
	
	$('#btnLogin').click(function() {
		
		if($('input[name=m_email]').val().isEmpty()){
			$('input[name=m_email]').clearAndFocus("이메일을 입력해주세요!");
			return;
		}
		
		if($('input[name=m_pass]').val().isEmpty()){
			$('input[name=m_pass]').clearAndFocus("비밀번호를 입력해주세요!");
			return;
		}
		
		$.ajax({
			url: '../Login.do',
			type: 'post',
			data: {
				m_email: $('input[name=m_email]').val(),
				m_pass: $('input[name=m_pass]').val()
			},
			success: function(data) {
				let obj = JSON.parse(data);
				alert(obj.message);
				
				if(obj.result == true){
					location.href = 'index.jsp';
				}
				else{
					location.href = 'index.jsp';
				}
			}
		});
	});
	
	$('#forgetPass').click(function() {
		location.href = 'findPassword.jsp';
	});
	
});