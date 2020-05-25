var gPage = 1;
var pageSize = 20;

$.fn.bindClickTag = function()
{
	this.click(function()
	{
		gPage = 1;
		$('#promotionTypeList').children().removeClass('active');
		$('#promotionTypeList').children().eq(0).addClass('active');
		$(this).search('p_tag', $(this).text(), '');
	});
}

// load list of tag
$.fn.getTagList = function()
{
	$.ajax(
	{
		url:'../TagList.do',
		type:'get',
		success: function(data)
		{
			$('#tagList').children().remove();
			for(let i=0; i	<data.tags.length; i++)
			{
				let tag = $('<span/>');
				tag.addClass('badge badge-pill badge-primary');		
				tag.text(data.tags[i].t_name);
				tag.bindClickTag('p_tag', "", "");
				tag.css('cursor', 'pointer');
				tag.appendTo('#tagList');
			}
		}
	});
};

$.fn.addActiveClass = function()
{
	$('#promotionTypeList').children().removeClass('active');
	$(this).addClass('active');
}

$.fn.search = function(cond, word, type)
{
	$.ajax(
	{
		url:'../BoardPromotionList.do',
		type:'get',
		data:
		{
			page: gPage,
			pageSize: pageSize,
			cond: cond,	
			word: word,
			type: type // promotionType select
		},
		success: function(data)
		{
			if(gPage == 1)
			{
				//$('.card').remove();
				$('.boardList').children().remove();
			}
			
			if(gPage <= data.pageCount)
			{
				data.boards.forEach(function(item, index)
				{
					let card = $('<div/>', {onClick:"location.href='adview.jsp?id="+item.b_id+"'"});
					card.addClass('card');
					card.css('width', '190px');
					card.css('marginTop', '12px');
					card.css('display', 'inline-block');
					card.css('cursor', 'pointer');
					
					console.log(item);
						
					
					let path = $('.container-fluid').data('path');
					console.log(item.a_name);
					let img = $('<img name="image" class="card-img-top" alt:"../images/KakaoTalk_20190420_204435544.jpg" src="'+path+item.a_name+'"  />');
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
					card.appendTo('.boardList');
				});
			}
		}
	});
}

//load list of boardPromotion 
$.fn.bindGetList = function()
{
	this.click(function()
	{
		// activate button
		$(this).addActiveClass();
		
		gPage = 1;
		$(this).search($('#conditionBox').val(), 
				$('#searchBox').val(), 
				$('.active').data('code'));
	});
};

/*
// equal with bindGetList
$.fn.bindSearch = function()
{
	this.click(function()
	{
		gPage = 1;
		$(this).search($('#conditionBox').val(), 
				$('#searchBox').val(), 
				'');
	});
};
*/

$(document).ready(function()
{
	$('#promotionTypeList').children().bindGetList();
	
	$('#promotionTypeList').children(':first').trigger('click');

	$(document).scroll(function()
	{
		if(document.documentElement.scrollHeight - document.documentElement.scrollTop <= 969)
		{
			gPage++;
			$(this).search($('#conditionBox').val(), 
					$('#searchBox').val(), 
			'');
		}
	});
	
	$(this).getTagList();
});