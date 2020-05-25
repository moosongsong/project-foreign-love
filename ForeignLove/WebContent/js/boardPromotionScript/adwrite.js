var id = new URLSearchParams(location.search).get("id"); // get value from queryString
var IU = new URLSearchParams(location.search).get("IU");
var i = 0;

$.fn.bindClickAddTag = function()
{
	this.click(function()
	{
		let value = $('#tagTree').val().replace(/ /g, '');
		let length = $('#tagTree').siblings('span').length;
		
		// already 
		for(let i=0; i<length; i++)
		{
			if($('#tagTree').siblings('span').eq(i).text() == value)
			{
				alert(value + '는 이미 추가한 태그입니다.');
				return;
			}
		}
		// no more
		if(length >= 3)
		{
			alert('더 이상 태그를 추가할 수 없습니다.');
		}
		else if(value)
		{
			let span = $('<span>');
			span.addClass('badge badge-pill badge-primary');
			span.text($('#tagTree').val().replace(/ /g, ''));
			
			$('#tagTree').before(span);
		}
		
		$('#tagTree').val('');
	});
};

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

$.fn.bindClickWrite = function()
{
	this.click(function()
	{
		if($('input[name=attachedFile]').length == 0){
			alert('사진을 꼭 첨부해주세요!!');
			return;
		}
		
		
		var files =[1];
		
		$('#attachedFiles > div').each(function(index, item)
		{
			let fileName = $(this).children(':first-child').val();
			files.push(fileName);
		});
		
		$.ajax(
		{
			url:'../BoardPromotionInsert.do',
			type:'post',
			traditional:'true',
			data:
			{
				title:$('#title').val(),
				pType:$('#pType').val(),
				startDate:$('#from').val(),
				endDate:$('#to').val(),
				content:$('#content').val(),
				files: files
			},
			success: function(data)
			{
				console.log(data);
				alert(data.message);
				if(data.result)
				{
					//
					var tags = new Array();
					let length = $('#tagTree').siblings('span').length;
					
					for(let i=0; i<length; i++)
					{
						tags[i] = ($('#tagTree').siblings('span').eq(i).text());
					}
					
					$.ajax(
					{
						url:'../TagInsert.do',
						type:'post',
					    traditional : true,
						data:
						{
							tags:tags,
							b_id:data.board.b_id
						}
					});
					//
					window.location.href="adBoard.jsp";
				}
			},
			error: function(e)
			{
				console.log(e);
			}
		});
	});
};

$.fn.bindClickUpdate = function()
{
	
	this.click(function()
	{
		$.ajax(
		{
			url:'../BoardPromotionUpdate.do',
			type:'post',
			data:
			{
				id:id,
				title:$('#title').val(),
				pType:$('#pType').val(),
				startDate:$('#from').val(),
				endDate:$('#to').val(),
				content:$('#content').val()
			},
			success: function(data)
			{
				console.log(data);
				alert(data.message);
				if(data.result)
				{
					window.location.href="adview.jsp?id="+id;
				}
			},
			error: function(e)
			{
				console.log(e);
			}
		});
	});
};

$(document).ready(function()
{
	$('#btnPlusFile').btnPlusFileClick();
	$('#btnAddTag').bindClickAddTag();
	if(IU == "insert")
	{
		$('#btnIU').bindClickWrite();
	}
	else
	{
		$('#btnIU').bindClickUpdate();
	}
});