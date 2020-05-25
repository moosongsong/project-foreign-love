package kr.co.foreignlove.service.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.ReplyDAO;
import kr.co.foreignlove.service.Service;

public class ReplyDeleteService implements Service
{
	private ReplyDAO dao;
	
	public ReplyDeleteService()
	{
		dao = (ReplyDAO)DAOManager.getDAO(ReplyDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		String strId = request.getParameter("r_id");
		int r_id = Integer.parseInt(strId);
		
		if(!dao.delete(r_id))
		{
			throw new Exception("게시물 수정에 실패하였습니다.");
		}
	}
}
