var id = new URLSearchParams(location.search).get("id");
var IU = new URLSearchParams(location.search).get("IU");
var i = 0;
$.fn.btnPlusFileClick = function(){
	this.click(function(){
		alert('추가버튼이 눌렸습니다.');
		
		var form = document.getElementById("plusFileForm");
		var formData = new FormData(form);
		var file = $('#fileChoose')[0].files[0];
		if(file){
			i++;
			$('#fileChoose').val('');
		}
		console.log(i);
		formData.append("file", file);
		console.log(file);

		$.ajax({
			url: "AttachedFileUploader.doit",
			type: "post",
			enctype: "multipart/form-data",
			processData: false,
			contentType: false,
			data: formData,
			success: function(data) {
				//when you click this button, 
				//file will be sent to the Uploader by using formData  
				
				console.log(data);
				console.log(data.fileName);
				let obj = $('<div>'+
						'<input type = "checkbox" name = "attachedFile" value= "'+ data.fileName+'">' +
						data.fileName+data.fileSize+'kb'+
						'<input type = "hidden" name = "attachedFile" value= "'+ data.fileName+'">'+
						'</div>');
				obj.appendTo($('#attachedFiles'));		
			}
		});
	});		
}

$.fn.marketWriteClick = function() {
	this.click(function() {


		var files =[1];
		
		$('#attachedFiles > div').each(function(index, item)
		{
			let fileName = $(this).children(':first-child').val();
			files.push(fileName);
		});
		
		$.ajax({
			url : '../BoardMarketInsert.do',
			type : 'post',
			traditional: 'true',
			data : {
				mk_title : $('#marketTitle').val(),
				mk_type : $('#marketType').val(),
				l_type : $('#lowType').val(),
				tw_type : $('#tradeWay').val(),
				mk_price : $('#price').val(),
				mk_content : $('#marketContent').val(),
				files: files
			},
			success : function(data) {

				alert(data.message);
				if (data.result) {
					
					location.href = 'marketBoard.jsp';
				}
			},
			error : function(e) {
				console.log(e);
			}

		});
	});
}



$.fn.marketWriteCancelClick = function() {
	this.one('click', function() {
		alert('글 등록이 취소되었습니다.');
		
	});
}

$.fn.btnUpdateClick = function() {
	this.one('click', function() {
		alert('글 수정 버튼이 눌렸습니다.');
		$.ajax({
			url : '../BoardMarketUpdate.do',
			type : 'post',
			data :  {
				title : $('#marketTitle').val(),
				mk_type : $('#marketType').val(),
				l_type : $('#lowType').val(),
				tw_type : $('#tradeWay').val(),
				mk_price : $('#price').val(),
				content : $('#marketContent').val(),
				id: $(this).data('bid')
			},
			success : function(data) {
				console.log(data);
				alert(data.message);
			
				if (data.result) {
					location.href='marketBoard.jsp';
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	});
}

$(document).ready(function() {
	$('#btnUpdate').btnUpdateClick();
	$('#marketWrite').marketWriteClick();
	$('#marketWriteCancel').marketWriteCancelClick();
	$('#btnPlusFile').btnPlusFileClick();
});
