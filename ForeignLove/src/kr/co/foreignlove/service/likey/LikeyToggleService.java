package kr.co.foreignlove.service.likey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.dao.BoardMarketDAO;
import kr.co.foreignlove.dao.BoardPromotionDAO;
import kr.co.foreignlove.dao.LikeyDAO;
import kr.co.foreignlove.dao.ReplyDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardVO;
import kr.co.foreignlove.vo.LikeyVO;
import kr.co.foreignlove.vo.MemberVO;
import kr.co.foreignlove.vo.ReplyVO;

public class LikeyToggleService implements Service
{
	private LikeyDAO dao;
	
	public LikeyToggleService()
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
		
		LikeyVO likey = new LikeyVO();
		
		ReplyVO reply = null;
		
		if(r_id == 0) // on post
		{
			reply = new ReplyVO();
			reply.setR_id(0);
		}
		else
		{
			ReplyDAO rDAO = (ReplyDAO)DAOManager.getDAO(ReplyDAO.NAME);
			reply = rDAO.getReply(r_id);						
		}
		
		likey.setR_id(reply);
		
		likey.setM_id((MemberVO)request.getSession(false).getAttribute("loginUserInfo"));
		
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
		likey.setB_id(board);
		
		int rValue = dao.toggle(likey);
		if(rValue == 0)
		{
			throw new Exception("좋아요 요청 실패");
		}
		else
		{
			request.setAttribute("rValue", rValue);
		}
	}
}
