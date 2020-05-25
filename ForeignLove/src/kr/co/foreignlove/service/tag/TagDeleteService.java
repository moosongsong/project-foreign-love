package kr.co.foreignlove.service.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.TagDAO;
import kr.co.foreignlove.service.Service;

public class TagDeleteService implements Service
{
	private TagDAO dao;
	
	public TagDeleteService()
	{
		dao = (TagDAO)DAOManager.getDAO(TagDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strT_id = request.getParameter("t_id");
		
		int t_id = Integer.parseInt(strT_id);
		
		if(!dao.delete(t_id))
		{
			throw new Exception("태그 삭제에 실패하였습니다.");
		}
	}
}
