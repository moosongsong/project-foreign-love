package kr.co.foreignlove.service.boardpromotion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardPromotionDAO;
import kr.co.foreignlove.dao.PromotionTypeDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardPromotionVO;

public class BoardPromotionUpdateService implements Service
{
	private BoardPromotionDAO dao;
	
	public BoardPromotionUpdateService()
	{
		dao = (BoardPromotionDAO)DAOManager.getDAO(BoardPromotionDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		int pTypeValue = Integer.parseInt(request.getParameter("pType"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String content = request.getParameter("content");		
		
		if(title == null || title.trim().equals(""))
		{
			throw new Exception("제목을 입력해주세요");
		}
		
		if(pTypeValue == 0)
		{
			throw new Exception("세부 카테고리를 선택해주세요.");
		}
		
		if(startDate == null || startDate.trim().equals(""))
		{
			throw new Exception("시작일을 선택해주세요.");
		}
		
		if(endDate == null || startDate.trim().equals(""))
		{
			throw new Exception("종료일을 선택해주세요.");
		}
		
		if(content == null || content.trim().equals(""))
		{
			throw new Exception("내용을 입력해주세요.");
		}
		
		BoardPromotionVO board = new BoardPromotionVO();
		
		board.setB_id(id);
		board.setB_title(title);
		board.setB_content(content);
		board.setB_startDate(startDate);
		board.setB_endDate(endDate);		
		
		PromotionTypeDAO ptDAO = (PromotionTypeDAO)DAOManager.getDAO(PromotionTypeDAO.NAME);
		String pType;
		switch(pTypeValue)
		{
		case 1:
			pType = "CIRCLE";
			break;
		case 2:
			pType = "FESTI";
			break;
		case 3:
			pType = "STUDY";
			break;
		default:
			throw new Exception("세부 카테고리를 선택해주세요.");	
		}
		board.setP_type(ptDAO.getType(pType));
		
		if(!dao.update(board))
		{
			throw new Exception("게시물 수정에 실패하였습니다.");
		}
	}
	
}
