package kr.co.foreignlove.service.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.TagDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.TagVO;

public class TagViewService implements Service
{
	private TagDAO dao;
	
	public TagViewService()
	{
		dao = (TagDAO)DAOManager.getDAO(TagDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strT_id = request.getParameter("t_id");
		
		int t_id = Integer.parseInt(strT_id);
		TagVO tag = dao.getTag(t_id);
		request.setAttribute("tag", tag);
	}
}
