package kr.co.foreignlove.service.boardpromotion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardPromotionDAO;
import kr.co.foreignlove.service.Service;

public class BoardPromotionDeleteService implements Service
{
	private BoardPromotionDAO dao;
	
	public BoardPromotionDeleteService()
	{
		dao = (BoardPromotionDAO)DAOManager.getDAO(BoardPromotionDAO.NAME); 
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strId = request.getParameter("id");
		
		int id = Integer.parseInt(strId);
		boolean result = dao.delete(id);
		
		if(!result)
		{
			throw new Exception("게시글 삭제에 실패하였습니다.");
		}
	}
}
