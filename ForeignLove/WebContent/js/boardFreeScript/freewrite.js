
var i =0;
$.fn.btnWriteClick = function(){
	//fn(전역함수)을 사용하면 this를 사용할수있어서 즉 누르는 자신주최를 가져올수있기때문.
	this.one('click',function(){
		
		var files =[1];
		
		$('#attachedFiles > div').each(function(index, item)
		{
			let fileName = $(this).children(':first-child').val();
			files.push(fileName);
		});
/*		
		for(let j=0; j<i; j++)
		{
			console.log($('#attachedFiles > div').eq(j).children(':first-child').val());
		}
*/		
		
		$.ajax({
			
			url:'../BoardFreeInsert.do',
			type:'post',
			traditional: 'true',
			data:{
				title: $('#title').val(),
				content: $('#content').val(),
				files: files
			},
			success:function(data)
			{
				alert(data.message);
//				console.log(data);
				if(data.result){
					
					location.href= 'freeBoard.jsp ';
				}
				else{
					$('#btnWrite').btnWriteClick();
				}
			},
			error:function(e)
			{
				console.log(e);
			}
		});
	});
}



//member check한 후 login이 안되어있으면 바로 login페이지로 가도록해야함.

$.fn.btnUpdateClick = function(){
	this.one('click',function(){
		alert('수정하기버튼이 눌렸습니다.');
		var files =[1];
		
		$('#attachedFiles > div').each(function(index, item)
		{
			let fileName = $(this).children(':first-child').val();
			files.push(fileName);
		});
		
		
		$.ajax({
			url:"../BoardFreeUpdate.do",
			type:"post",
			traditional: "true",
			data:{
				title: $('#title').val(),
				content: $('#content').val(),
				id: $(this).data('bid')
				//files: files
			},
			success:function(data)
			{
				alert(data.message);
				console.log(data);
				if(data.result){
					$('#btnCancel').trigger('click');
					location.href= 'freeBoard.jsp ';
				}
				else{
					$('#btnWrite').btnWriteClick();
				}
			}
		});
	});
}



$.fn.btnPlusFileClick = function(){
	this.click(function(){
		alert('추가버튼이 눌렸습니다.');
		
		var form = document.getElementById("plusFileForm");
		var formData = new FormData(form);
		var file = $('#fileChoose')[0].files[0];
		
		var fileExtension = file.name.substr(file.name.length-3, 3);
		if(!(fileExtension == 'jpg' || fileExtension == 'png' || fileExtension == 'img' || fileExtension == 'gif') ){
			alert('이미지파일만 넣어주세용. ');
			$('#fileChoose').val('');
			return;
		}
		if(file){
			i++;
			$('#fileChoose').val('');
		}
		console.log(i);
		formData.append("file", file);
		console.log(file);

		$.ajax({
			url: "../AttachedFileUploader.doit",
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



$.fn.btnDeleteFileClick = function(){
	
	this.click(function(){
		alert('선택파일삭제 버튼을 클릭하였습니다.');
		
		$('[name=attachedFile]:checked').parent('div').remove();
	});
}



$(document).ready(function()
{
	$('#btnUpdate').btnUpdateClick();
	$('#btnWrite').btnWriteClick();
	//$('#btnCancel').btnCancelClick(); //this one is implemented by freewrite.jsp
	$('#btnPlusFile').btnPlusFileClick();
	$('#btnDeleteFile').btnDeleteFileClick();
	
});