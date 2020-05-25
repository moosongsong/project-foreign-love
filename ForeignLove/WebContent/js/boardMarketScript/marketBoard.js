var gPage = 1;

$.fn.bindClickPrev = function()
{
	this.click(function()
	{
		if(gPage >= 2)
		{
			gPage--;
			$(this).search();
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
			$(this).search();
		}
	});
};

$.fn.addActiveClass = function()
{
	$('#marketTypeList').children().removeClass('active');
	$(this).addClass('active');
}

$.fn.search = function(){
	
	console.log($('#conditionBox').val());
	console.log($('#go').val());
	$.ajax({
		url: '../BoardMarketList.do',
		type: 'get',
		data:{
			page: gPage,
			cond: $('#conditionBox').val(),
			word: $('#word').val(),
			type: $('.active').data('code')
		},
		success:function(data){
			$('.boardMarketList').remove();
			
			console.log(data);
			if (data.result) {
				for(let i=0; i<data.boards.length; i++){
					
					let board=data.boards[i];
					
					let tr = $('<tr>', {onClick:"location.href='marketview.jsp?id="+board.b_id+"'"});
					tr.addClass('boardMarketList');
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
					tr.appendTo('#boardMarketBody');					
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
						let a = $('<a/>', {href:'#'});
						a.addClass('page-link');
						a.text(i);
						a.bindSearch(i);
						a.appendTo(li);
						$('#next').before(li);						
					}
				}
			}
		},
		error:function(e)
		{
			console.log(e);
		}
	});
}


$.fn.bindGetList = function(page)
{
	this.click(function()
	{
		$(this).addActiveClass();
		gPage = page;
		$(this).search();
	});
};

$.fn.bindSearch = function(page)
{
	this.click(function()
	{
		gPage = page;
		$(this).search();
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
			bt_type: 'MK',
			type:'BOARD'
		},
		success:function(data)
		{
			console.log(data);
			data.likeyCounts.forEach(function(item, index)
			{
				let post = $('.boardMarketList[data-b_id=' + item.id + ']');
				post.children('.likeyCount').text(item.count);
			});
		}
	});
};

$(document).ready(function(){
	
	//$('#main').children().bindGetList();
	$('#marketTypeList').children().bindGetList(1);
	
	$('#next').bindClickNext();
	$('#prev').bindClickPrev();
	
	$('#go').bindSearch(1);
	$('#go').trigger('click');
	
})
