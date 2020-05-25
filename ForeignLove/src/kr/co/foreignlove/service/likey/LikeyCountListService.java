package kr.co.foreignlove.service.likey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.dao.BoardMarketDAO;
import kr.co.foreignlove.dao.BoardPromotionDAO;
import kr.co.foreignlove.dao.BoardTypeDAO;
import kr.co.foreignlove.dao.LikeyDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardTypeVO;
import kr.co.foreignlove.vo.BoardVO;
import kr.co.foreignlove.vo.LikeyCountVO;

public class LikeyCountListService implements Service
{
	public static final String TYPE_BOARD = "BOARD";
	public static final String TYPE_POST = "POST";
	private LikeyDAO dao;
	
	public LikeyCountListService()
	{
		dao = (LikeyDAO)DAOManager.getDAO(LikeyDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strId = request.getParameter("b_id");
		int b_id = 0;
		
		if(strId != null)
		{
			b_id = Integer.parseInt(strId);	
		}
		
		String type = request.getParameter("type");
		
		String bt_type = request.getParameter("bt_type");
		BoardVO board = null;
		BoardTypeDAO bDAO = (BoardTypeDAO)DAOManager.getDAO(BoardTypeDAO.NAME);
		
		switch(bt_type)
		{
		case "FR":
			BoardFreeDAO bfDAO = (BoardFreeDAO)DAOManager.getDAO(BoardFreeDAO.NAME);
			if(b_id != 0)
			{
				board = bfDAO.getBoard(b_id);
			}
			else
			{
				board = new BoardVO();
				BoardTypeVO boardType = bDAO.getBoardTypeVO(bt_type);
				
				board.setBoardType(boardType);
			}
			break;
		case "MK":
			BoardMarketDAO bmDAO = (BoardMarketDAO)DAOManager.getDAO(BoardMarketDAO.NAME);
			if(b_id != 0)
			{
				board = bmDAO.getBoard(b_id);
			}
			else
			{
				board = new BoardVO();
				BoardTypeVO boardType = bDAO.getBoardTypeVO(bt_type);
				
				board.setBoardType(boardType);
			}
			break;
		case "PR":
			BoardPromotionDAO bpDAO = (BoardPromotionDAO)DAOManager.getDAO(BoardPromotionDAO.NAME);
			if(b_id != 0)
			{
				board = bpDAO.getBoard(b_id);				
			}
			else
			{
				board = new BoardVO();
				BoardTypeVO boardType = bDAO.getBoardTypeVO(bt_type);
				
				board.setBoardType(boardType);
			}
			break;
		}			

		
		ArrayList<LikeyCountVO> likeyCounts = dao.getCountList(board, type);
		request.setAttribute("likeyCounts", likeyCounts);
	}
}