package kr.co.foreignlove.service.tag;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.TagDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.TagVO;

public class TagListService implements Service
{
	private TagDAO dao;
	
	public TagListService()
	{
		dao = (TagDAO)DAOManager.getDAO(TagDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String word = request.getParameter("word");
		
		word = (word == null) ? "" : word;
		
		ArrayList<TagVO> tags = dao.getList(word);
		request.setAttribute("word", word);
		request.setAttribute("tags", tags);
	}
}
