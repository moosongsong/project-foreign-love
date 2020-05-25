
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
	$('#sendBtn').click(function() {
		
		if($('#email').val().isEmpty()){
			$('#email').clearAndFocus("받는 사람의 이메일을 입력해주세요.");
			return;
		}
		
		if($('#dm_content').val().isEmpty()){
			$('#dm_content').clearAndFocus("내용을 입력해주세요.");
			return;
		}
		
		$.ajax({
			url:'../SendDM.do',
			type:'post',
			data:{
				email:$('#email').val().trim(),
				emailBack:$('#emailBack').val(),
				dm_content:$('#dm_content').val()
			},
			success: function(data) {
				if(data.result == true){
					alert(data.dm+"님에게 전송이 완료되었습니다.");
					location.href='dmBoard.jsp'
				}
				else{
					alert("전송에 실패하였습니다.");
				}
			}
		});
	});
});