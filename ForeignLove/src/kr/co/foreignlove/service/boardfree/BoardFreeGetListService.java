package kr.co.foreignlove.service.boardfree;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardFreeVO;

public class BoardFreeGetListService implements Service {
	public static final String CONDITION_TITLE="title";
	public static final String CONDITION_CONTENT="content";
	public static final String CONDITION_ALL="all";
	public static final int PAGESIZE = 10;
	BoardFreeDAO dao;
	public BoardFreeGetListService() {
		dao = (BoardFreeDAO)DAOManager.getDAO(BoardFreeDAO.NAME);
	}
	public void doService(HttpServletRequest request, HttpServletResponse response) {
		String strPage = request.getParameter("page");//ajax에서 보낸값
		String cond = request.getParameter("cond");
		String word = request.getParameter("word");
		String mode = request.getParameter("mode");//이전,1,2,3,4,5,다음 같은것
		int page = 1;
		
		try {
			if(strPage != null) page= Integer.parseInt(strPage);
		}
		catch(Exception e) {}
		mode= (mode == null) ? "": mode;
		cond= (cond == null) ? "": cond;
		word= (word == null) ? "": word;
		
		int totalRecord = dao.getTotalRecord(cond, word);
		int pageCount = (int)Math.ceil((double)totalRecord/PAGESIZE);//전체 페이지수
		
		switch(mode) {
		case "first":
			page = 1;
			break;
		case "last":
			page = pageCount;
			break;
		case "prev":
			if(--page < 1) page =1;
			break;
		case "next":
			if(++page > pageCount) page = pageCount;
			break;
		default:
			if(page < 1) page = 1;
			if(page > pageCount) page = pageCount;
		}
		
		ArrayList<BoardFreeVO> boards = dao.getList(page, PAGESIZE, cond, word); 
		request.setAttribute("page", page);
		request.setAttribute("cond", cond);
		request.setAttribute("word", word);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("boards", boards);
	}
	
	
}
