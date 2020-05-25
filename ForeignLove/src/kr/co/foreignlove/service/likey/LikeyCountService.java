package kr.co.foreignlove.service.likey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.dao.BoardMarketDAO;
import kr.co.foreignlove.dao.BoardPromotionDAO;
import kr.co.foreignlove.dao.LikeyDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardVO;
import kr.co.foreignlove.vo.LikeyCountVO;

public class LikeyCountService implements Service
{
	private LikeyDAO dao;
	
	public LikeyCountService()
	{
		dao = (LikeyDAO)DAOManager.getDAO(LikeyDAO.NAME);
	}

	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strB_id = request.getParameter("b_id");
		int b_id = Integer.parseInt(strB_id);
		
		String strR_id = request.getParameter("r_id");
		int r_id = Integer.parseInt(strR_id);
		// insert only use r_id
		String bt_type = request.getParameter("bt_type");
		BoardVO board = null;
		
		switch(bt_type)
		{
		case "FR":
			BoardFreeDAO bfDAO = (BoardFreeDAO)DAOManager.getDAO(BoardFreeDAO.NAME);
			board = bfDAO.getBoard(b_id);
			break;
		case "MK":
			BoardMarketDAO bmDAO = (BoardMarketDAO)DAOManager.getDAO(BoardMarketDAO.NAME);
			board = bmDAO.getBoard(b_id);
			break;
		case "PR":
			BoardPromotionDAO bpDAO = (BoardPromotionDAO)DAOManager.getDAO(BoardPromotionDAO.NAME);
			board = bpDAO.getBoard(b_id);
			break;
		}
		
		LikeyCountVO likeyCount = dao.getCount(board, r_id);
		request.setAttribute("likeyCount", likeyCount);
	}
}
