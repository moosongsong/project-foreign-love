package kr.co.foreignlove.service.boardpromotion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.AttachedDAO;
import kr.co.foreignlove.dao.BoardPromotionDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.AttachedVO;
import kr.co.foreignlove.vo.BoardPromotionVO;

public class BoardPromotionListService implements Service
{
	public static final String CONDITION_TITLE = "p_title";
	public static final String CONDITION_CONTENT = "p_content";
	public static final String CONDITION_TAG = "p_tag";
	
	private BoardPromotionDAO dao;
	private AttachedDAO adao;
	public BoardPromotionListService()
	{
		dao = (BoardPromotionDAO)DAOManager.getDAO(BoardPromotionDAO.NAME);
		adao = (AttachedDAO)DAOManager.getDAO(AttachedDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strPage = request.getParameter("page");
		String strPageSize = request.getParameter("pageSize");
		String cond = request.getParameter("cond");
		String word = request.getParameter("word");
		String type = request.getParameter("type");
		
		
		int page = 1;
		int pageSize = 20;
		
		try
		{
			if(strPage != null)
			{
				page = Integer.parseInt(strPage);
			}
			if(strPageSize != null)
			{
				pageSize = Integer.parseInt(strPageSize);
			}
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		
		cond = (cond == null) ? "" : cond;
		word = (word == null) ? "" : word;
		type = (type == null) ? "" : type;
		
		int totalRecord = dao.getTotalRecord(cond, word, type);
		int pageCount = (int)Math.ceil((double)totalRecord / pageSize);
		
		ArrayList<BoardPromotionVO> boards = dao.getList(page, pageSize, cond, word, type);
		
		request.setAttribute("page", page);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("cond", cond);
		request.setAttribute("word", word);
		request.setAttribute("type", type);
		request.setAttribute("pageCount", pageCount);
		
		for(int i=0; i<boards.size();i++) {
			ArrayList<AttachedVO> attaches = adao.getAttached(boards.get(i).getBoardType(), boards.get(i).getB_id());
			System.out.println(attaches);
			System.out.println(boards.get(i).getAttachedList());
			boards.get(i).setAttachedList(attaches);
			System.out.println(boards.get(i).getAttachedList());
		}
		request.setAttribute("boards", boards);
	}
}
