package kr.co.foreignlove.service.boardmarket;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardMarketDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardMarketVO;

public class BoardMarketListService implements Service
{
	public static final String CONDITION_TITLE = "mk_title";
	public static final String CONDITION_CONTENT = "mk_content";
	public static final int PAGESIZE = 10;
	
	private BoardMarketDAO dao;
	
	public BoardMarketListService()
	{
		dao = (BoardMarketDAO)DAOManager.getDAO(BoardMarketDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strPage = request.getParameter("page");
		String mode = request.getParameter("mode");
		String cond = request.getParameter("cond");
		String word = request.getParameter("word");
		String type = request.getParameter("type");
		
		int page = 1;
		
		try
		{
			if(strPage != null)
				page = Integer.parseInt(strPage);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		mode = (mode == null) ? "" : mode;
		cond = (cond == null) ? "" : cond;
		word = (word == null) ? "" : word;
		type = (type == null) ? "" : type;
		
		int totalRecord = dao.getTotalRecord(cond, word, type);
		int pageCount = (int)Math.ceil((double)totalRecord / PAGESIZE);
		
		
		switch(mode)
		{
		case "first":
			page = 1;
			break;
		case "last":
			page = pageCount;
			break;
		case "prev":
			if(--page < 1) page = 1;
			break;
		case "next":
			if(++page > pageCount) page = pageCount;
			break;
		default:
			if(page > pageCount) page = pageCount;
			if(page < 1) page = 1;
		}
		
		ArrayList<BoardMarketVO>boards = dao.getList(page, PAGESIZE, cond, word, type);
		
		request.setAttribute("page", page);
		request.setAttribute("cond", cond);
		request.setAttribute("word", word);
		request.setAttribute("type", type);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("boards", boards);
	}
}




