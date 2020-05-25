$.getReplyList = function()
{
	let id = document.location.href.split("="); // get idNum from to seperate uri=, idNum

	$.ajax(
	{
		url:'../ReplyList.do',
		type:'get',
		data:
		{
			b_id: id[1],
			bt_type:'FR'
		},
		success:function(data)
		{
			// if not load replies
			if(!data.result)
			{
				alert(data.message);
			}
			else
			{
				$('#replyList').children().remove();
				for(let i=0; i<data.replies.length; i++)
				{
					let element = data.replies[i];
					let reply = $('<div/>');	
					reply.attr('data-r_id', element.r_id);
					let nick = $('<div/>');
					nick.css('width', '10%');
					nick.css('display', 'inline-block');
					nick.text(element.m_id.m_nick);
					nick.appendTo(reply);
					let content = $('<div/>');
					content.addClass('replyContent');
					content.css('width', '40%');
					content.css('display', 'inline-block');
					content.text(element.r_content);
					content.appendTo(reply);
							
					let post = $('<div/>');
					post.css('width', '20%');
					post.css('display', 'inline-block');
					post.text(element.r_post.substring(0,19));
					post.appendTo(reply);
					
					let btnLikey = $('<button/>', {type:'button', name:'btnUpdate'});
					btnLikey.attr('id', 'btnReplyLikey');
					btnLikey.attr('data-r_id', element.r_id);
					btnLikey.addClass('btn btnLikey');
					btnLikey.css('width', '10%');
					btnLikey.text('좋아요');
					btnLikey.bindClickLikeyToggle();
					// 좋아요 기능 연결
					btnLikey.appendTo(reply);
					
					let btnUpdate = $('<button/>', {type:'button', name:'btnUpdate'});
					btnUpdate.attr('id', 'btnReplyUpdate');
					btnUpdate.addClass('btn');
					btnUpdate.css('width', '6%');
					btnUpdate.text('수정');
					btnUpdate.bindClickReplyUpdate();
					// 수정 기능 연결
					btnUpdate.appendTo(reply);
					let btnDelete = $('<button/>', {type:'button', name:'btnUpdate'});
					btnDelete.attr('id', 'btnReplyDelete');
					btnDelete.addClass('btn');
					btnDelete.css('width', '6%');
					btnDelete.text('삭제');
					btnDelete.bindClickReplyDelete();
					// 삭제 기능 연결
					btnDelete.appendTo(reply);		
					
					// if my reply or m_type == MANAGER
					if(data.loginUserInfo.m_type != "관리자" && data.loginUserInfo.m_id != element.m_id.m_id)
					{
						btnUpdate.css('visibility', 'hidden');
						btnDelete.css('visibility', 'hidden');
					}
					
					if(element.r_highId) // if it is replyReply
					{
						reply.css('margin', '2% 1% 0 2%');
						reply.appendTo($('div[data-r_id='+ element.r_highId.r_id +']'));
					}
					else
					{
						reply.addClass('well');
						reply.appendTo('#replyList');
						let btnReply = $('<button/>', {type:'button', name:'btnUpdate'});
						btnReply.attr('id', 'btnReplyReply');
						btnReply.addClass('btn');
						btnReply.css('width', '6%');
						btnReply.text('답글');
						btnReply.appendTo(reply);		
						btnReply.bindClickReplyReply();
					}
				}
			}
			$.getLikeyCountList();
			// 좋아요 카운트 가져오기
		}
	});
}

$.fn.bindClickReplyInsert = function()
{
	this.click(function()
	{	
		$.ajax(
		{
			url:'../ReplyInsert.do',
			type:'post',
			data:
			{
				b_id:$('.container-fluid').data('b_id'),
				content:$('#replyContent').val(),
				bt_type:'FR'
			},
			success:function(data)
			{
				alert(data.message);
				if(data.result)
				{
					location.reload();
				}
			}
		});
	});
}

$.fn.bindClickReplyUpdate = function()
{
	let btn = this;
	
	this.click(function()
	{
		let replyLocation = btn.parent();
		let origContent = replyLocation.children('.replyContent').text();

		let nick = $(this).parent().children().not('.reply').eq(0);
		
		let t = $(this).parent().children().not('.reply').not(':first-child');
		t.remove();
		
		let textArea = $('<textarea>', {width: '75%'});
		textArea.attr('id', 'btnReplyUpdateContent');
		textArea.text(origContent);
		let btnOk = $('<button>');
		btnOk.attr('id', 'btnReplyUpdateOk');
		btnOk.addClass('btn');
		btnOk.text('수정'); // Save original value
		let btnCancel = $('<button>');
		btnCancel.attr('id', 'btnReplyUpdateCancel');
		btnCancel.addClass('btn');
		btnCancel.text('취소');
		
		btnOk.bindClickReplyUpdateOk(replyLocation[0].dataset.r_id);
		btnCancel.bindClickReplyUpdateCancel();
		
		textArea.insertAfter(nick);
		btnCancel.insertAfter(textArea);
		btnOk.insertAfter(textArea);
	});
};

$.fn.bindClickReplyUpdateOk = function(r_id)
{
	this.click(function()
	{
		$.ajax(
		{
			url:'../ReplyUpdate.do',
			type:'get',
			data:
			{
				r_id:r_id,
				content: $('#btnReplyUpdateContent').val()
			},
			success:function(data)
			{
				if(data.result)
				{
					location.reload();
				}
			}
		});
	});
};

$.fn.bindClickReplyUpdateCancel = function()
{
	this.click(function()
	{
		location.reload();		
	});
}

$.fn.bindClickReplyDelete = function()
{
	let btn = this;
	
	this.click(function()
	{
		let replyLocation = btn.parent();
		
		let input = confirm("정말 삭제하시겠습니까?");
		
		if(input)
		{
			$.ajax(
			{
				url:'../ReplyDelete.do',
				type:'get',
				data:
				{
					r_id:replyLocation[0].dataset.r_id
				},
				success:function(data)
				{
					location.reload();
				}
			});
		}
	});
};

$.fn.bindClickLikeyToggle = function()
{
	let id = document.location.href.split("="); // get idNum from to seperate uri=, idNum
	let btn = this;
	let r_id = btn[0].dataset.r_id;
	
	this.click(function()
	{
		$.ajax(
		{
			url:'../LikeyToggle.do',
			type:'post',
			data:
			{
				b_id:id[1],
				bt_type:'FR',
				r_id:r_id
			},
			success:function(data)
			{
				// db 들러서 좋아요 리스트 받고 1 증가
				$.getLikeyCount(id[1], 'FR', r_id, btn, data.rValue);
			}
		});
	});
};

$.getLikeyCountList = function()
{
	let id = document.location.href.split("=");	
	
	$.ajax(
	{
		url:'../LikeyCountList.do',
		type:'get',
		data:
		{
			b_id: id[1],
			bt_type: 'FR',
			type:'POST'
		},
		success:function(data)
		{
			data.likeyCounts.forEach(function(item, index)
			{
				let likey = $('.btnLikey[data-r_id=' + item.id + ']');
				
				if(likey.length > 0)
				{
					likey.text(likey.text() + ' ' + item.count);
				}
			});
		}
	});
};

$.getMyList = function()
{
	let id = document.location.href.split("=");
	
	$.ajax(
	{
		url:'../LikeyMyList.do',
		type:'get',
		data:
		{
			b_id:id[1],
			bt_type: 'FR'
		},
		success:function(data)
		{
			console.log(data);
			data.myLikies.forEach(function(item, index)
			{
				let likey = $('.btnLikey[data-r_id=' + item.r_id.r_id + ']');
				
				likey.text('*' + likey.text());
			});
		}
	});
}

$.getLikeyCount = function(b_id, bt_type, r_id, btn, rValue)
{
	let id = document.location.href.split("=");
	
	$.ajax(
	{
		url:'../LikeyCount.do',
		type:'get',
		data:
		{
			b_id:b_id,
			bt_type:bt_type,
			r_id:r_id
		},
		success:function(data)
		{
			let count = data.likeyCount.count;
			
			if(count == 0)
			{
				btn.text('좋아요');
			}
			else
			{
				btn.text('좋아요 ' + count);
			}
			
			if(rValue == 1)
			{
				btn.text('*' + btn.text());
			}
		}
	});
};

$.fn.bindClickReplyReply = function()
{
	let btn = this;
	
	this.click(function()
	{
		$('#btnReplyReplyDiv').remove();
		
		let replyLocation = btn.parent();
		
		let div = $('<div>');
		div.attr('id', 'btnReplyReplyDiv');
		div.css('margin-left', '3%');
		div.css('margin-top', '2%');
		let textArea = $('<textarea>', {width: '93%'});
		textArea.attr('id', 'replyReplyContent');
		textArea.appendTo(div);
		
		let button = $('<button>');
		button.addClass('btn');
		button.attr('id', 'btnReplyReplyOk');
		button.text('등록');
    	button.appendTo(div);
		button.bindClickReplyReplyOk(replyLocation.data('r_id'));
    	
    	div.appendTo(replyLocation);
	});
};

$.fn.bindClickReplyReplyOk = function(parentR_id)
{
	this.click(function()
	{	
		$.ajax(
		{
			url:'../ReplyInsert.do',
			type:'post',
			data:
			{
				b_id:$('.container-fluid').data('b_id'),
				content:$('#replyReplyContent').val(),
				r_highId:parentR_id,
				bt_type:'FR'
			},
			success:function(data)
			{
				alert(data.message);
				if(data.result)
				{
					location.reload();
				}
			}
		});
	});
};

$(document).ready(function(){
	
	$('#btnReplyInsert').bindClickReplyInsert();
	$('#btnLikey').bindClickLikeyToggle();
	
	$.getReplyList();
	$.getMyList();
	
	
	$('#btnDelete').one('click',function(){
		let input = confirm("정말 삭제하시겠습니까?");
		
		//$('input[name=image]').삭제하는것은 하기.
		if(input)
		{
			$.ajax({
				url: '../BoardFreeDelete.do',
				type: 'post',
				data: {
					id:$(this).data('id')
				},
				success: function(data){
					alert(data.message);
					location.href="freeBoard.jsp";
				}
			});			
		}
	});
});