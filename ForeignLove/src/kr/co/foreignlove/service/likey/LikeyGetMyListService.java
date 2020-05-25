package kr.co.foreignlove.service.likey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.dao.BoardMarketDAO;
import kr.co.foreignlove.dao.BoardPromotionDAO;
import kr.co.foreignlove.dao.LikeyDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardVO;
import kr.co.foreignlove.vo.LikeyVO;
import kr.co.foreignlove.vo.MemberVO;

public class LikeyGetMyListService implements Service
{
	private LikeyDAO dao;
	
	public LikeyGetMyListService()
	{
		dao = (LikeyDAO)DAOManager.getDAO(LikeyDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		String strId = request.getParameter("b_id");
		int b_id = Integer.parseInt(strId);
	
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
		
		MemberVO member = (MemberVO)request.getSession(false).getAttribute("loginUserInfo");
		
		ArrayList<LikeyVO> myLikies = dao.getMyList(board, member);
		request.setAttribute("myLikies", myLikies);
	}
}
