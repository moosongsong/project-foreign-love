var gPage = 1;

$.fn.bindClickPrev = function()
{
	this.click(function()
	{
		if(gPage >= 2)
		{
			gPage--;
			$(this).setContent();
		}		
	});
};

$.fn.bindClickNext = function()
{
	this.click(function()
	{
		let totalPage = $('.page-num:last').text();
		if(gPage < totalPage)
		{			
			gPage++;
			$(this).setContent();
		}
	});
};

$.fn.setContent = function(){
	$('.boardList').remove();// tbody쪽을 다 지운다.
	//어떤 태그든 id는 하나만 있어야한다.만약 id로 참조했다면 아래의 for문에서 모두 id = boardList로
	//등록을 했을것이기 때문에 오류가 난다.id는 하나라고 인식하고 맨 위의것만 지우고 나머지 아래의 것들은 안지운다.
	$.ajax({
		url: '../BoardFreeGetList.do',
		type: 'get',
		data:{
			page: gPage,
			cond: $('#cond').val(),
			word: $('#word').val()
		},
		success: function(data){
			console.log(data);
			
			if(data.result)
			{
				for(let i =0; i<data.boards.length; i++)
				{				
					let board = data.boards[i];
					//let link = $('<a/>', {text: board.b_title , href : 'freeview.jsp?id=' + board.b_id});

					let tr = $('<tr>', {onClick:"location.href='freeview.jsp?id="+board.b_id+"'"});
					tr.addClass('boardList');
					tr.css('cursor', 'pointer');
					let td1 = $('<td>');
					td1.text(board.b_title.substring(0,20));
					let td2 = $('<td>');
					td2.text(board.member.m_nick);
					let td3 = $('<td>');
					td3.text(board.b_post);
					let td4 = $('<td>');
					td4.text(0);
					td4.addClass('likeyCount');
					let td5 = $('<td>');
					td5.text(board.b_count);
					
					td1.appendTo(tr);
					td2.appendTo(tr);
					td3.appendTo(tr);
					td4.appendTo(tr);
					td5.appendTo(tr);		
					
					tr.attr('data-b_id', board.b_id);
					tr.appendTo('#boardBody');
				}
			}

			// 좋아요 갯수 가져오기
			$.getLikeyCountList();
			
			$('.pagination').children().not('#prev, #next').remove();
			
			for(let i=gPage-2; i<=gPage+2; i++)
			{
				if(i>=1 && i<=data.pageCount)
				{
					let li = $('<li/>');
					li.addClass('page-item');
					li.addClass('page-num');
					let a = $('<a/>', {href:"#"});
					a.addClass('page-link');
					a.text(i);
					a.bindSearch(i);
					a.appendTo(li);
					$('#next').before(li);						
				}
			}
		},
		error: function(e)
		{
			console.log(e);
		}
	});
}

$.fn.bindSearch = function(page)
{
	this.click(function()
	{
		gPage = page;
		$(this).setContent();
	});
}

$.getLikeyCountList = function()
{
	$.ajax(
	{
		url:'../LikeyCountList.do',
		type:'get',
		data:
		{
			bt_type: 'FR',
			type:'BOARD'
		},
		success:function(data)
		{
			data.likeyCounts.forEach(function(item, index)
			{
				let post = $('.boardList[data-b_id=' + item.id + ']');
				post.children('.likeyCount').text(item.count);
			});
		}
	});
};

$(document).ready(function()
{
	$('#main').setContent();
	
	$('#go').bindSearch(1);
	
	$('#next').bindClickNext();
	$('#prev').bindClickPrev();
	
/*	$('#go').click(function(){
		$('#main').setContent();
	});*/
})