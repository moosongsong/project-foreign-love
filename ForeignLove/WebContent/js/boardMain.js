var gPage = 1;

$.getFreeList = function()
{
	$.ajax(
	{
		url: '../BoardFreeGetList.do',
		type: 'get',
		data:
		{
			page: gPage
		},
		success: function(data)
		{
			$('.freeList').children().remove();
			
			for(let i=0; i<4; i++)
			{
				let board = data.boards[i];
				if(board){
					
				}else{
					
				}
				let tr = $('<tr>', {onClick:"location.href='freeview.jsp?id="+board.b_id+"'"});
				tr.css('cursor', 'pointer');
				let td1 = $('<td>');
				td1.text(board.b_title.substring(0,30));
				let td2 = $('<td>');
				td2.text(board.member.m_nick);
				let td3 = $('<td>');
				td3.text(board.b_post.substring(0,10));
				td1.appendTo(tr);
				td2.appendTo(tr);
				td3.appendTo(tr);
				tr.appendTo('.freeList');
			}
		},
		error: function(e)
		{
			console.log(e);
		}
	});
}

$.getBuyList = function()
{
	$.ajax({
		url: '../BoardMarketList.do',
		type: 'get',
		data:
		{
			page: gPage,
			type: 'BUY'
		},
		success:function(data)
		{
			$('.buyList').children().remove();
			
			if (data.result) 
			{
				for(let i=0; i<4; i++)
				{
					let board = data.boards[i];
					
					let tr = $('<tr>', {onClick:"location.href='marketview.jsp?id="+board.b_id+"'"});
					tr.css('cursor', 'pointer');
					let td1 = $('<td>');
					td1.text(board.b_title.substring(0,20));
					let td2 = $('<td>');
					td2.text(board.member.m_nick);
					let td3 = $('<td>');
					td3.text(board.b_post.substring(0,10));
					td1.appendTo(tr);
					td2.appendTo(tr);
					td3.appendTo(tr);
					tr.appendTo('.buyList');
				}
			}
		},
		error:function(e)
		{
			console.log(e);
		}
	});
}

$.getSellList = function()
{
	$.ajax({
		url: '../BoardMarketList.do',
		type: 'get',
		data:
		{
			page: gPage,
			type: 'SELL'
		},
		success:function(data)
		{
			$('.sellList').children().remove();
			
			if (data.result) 
			{
				for(let i=0; i<4; i++)
				{
					let board = data.boards[i];
					
					let tr = $('<tr>', {onClick:"location.href='marketview.jsp?id="+board.b_id+"'"});
					tr.css('cursor', 'pointer');
					let td1 = $('<td>');
					td1.text(board.b_title);
					let td2 = $('<td>');
					td2.text(board.member.m_nick);
					let td3 = $('<td>');
					td3.text(board.b_post.substring(0,10));
					td1.appendTo(tr);
					td2.appendTo(tr);
					td3.appendTo(tr);
					tr.appendTo('.sellList');
				}
			}
		},
		error:function(e)
		{
			console.log(e);
		}
	});
}

$.getPromotionList = function(cond, word, type)
{
	$.ajax(
	{
		url:'../BoardPromotionList.do',
		type:'get',
		data:
		{
			page: gPage,
			pageSize: 10,
			cond: cond,	
			word: word,
			type: type // promotionType select
		},
		success: function(data)
		{
			if(gPage == 1)
			{
				//$('.card').remove();
				$('.promotionList').children().remove();
			}
			
			if(gPage <= data.pageCount)
			{
				data.boards.forEach(function(item, index)
				{
					let link = $('<a/>', {href : 'adview.jsp?id=' + item.b_id});
					link.css('margin', '3px');
					let card = $('<div/>');
					card.addClass('card');
					card.css('width', '19%');
					card.css('marginTop', '12px');
					card.css('display', 'inline-block');
					let path = $('.container-fluid').data('path');
					let img = $('<img name="image" src="'+path+item.a_name+'" />');
					img.addClass('card-img-top');
					img.appendTo(card);
					let div = $('<div/>');
					div.addClass('card-body');
					div.appendTo(card);
					let h5 = $('<h5/>');
					h5.addClass('card-title');
					h5.text(item.b_title);
					h5.css('text-overflow', 'ellipsis');
					h5.css('overflow', 'hidden');
					h5.css('white-space', 'nowrap');
					h5.appendTo(div);
					let p1 = $('<p/>');
					p1.addClass('card-text');
					p1.text(item.b_content);
					p1.css('text-overflow', 'ellipsis');
					p1.css('overflow', 'hidden');
					p1.css('white-space', 'nowrap');
					p1.appendTo(div);
					let p2 = $('<p/>');
					p2.addClass('card-text');
					p2.text(item.b_startDate.substring(2,10) + " ~ " + item.b_endDate.substring(2,10));
					p2.appendTo(div);
					card.appendTo(link);
					link.appendTo('.promotionList');
				});
			}
		}
	});
}

$.fn.bindScrollLeft = function()
{
	this.click(function()
	{
		$('.promotionList').animate({scrollLeft:0}, 500);
	});
};

$.fn.bindScrollRight = function()
{
	this.click(function()
	{
		$('.promotionList').animate({scrollLeft:888}, 500);
	});
};

$(document).ready(function() 
{
	$.getFreeList();
	$.getBuyList();
	$.getSellList();
	$.getPromotionList("","","");
	$('.btnLeft').bindScrollLeft();
	$('.btnRight').bindScrollRight();
});