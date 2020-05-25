package kr.co.foreignlove.service.reply;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.dao.BoardMarketDAO;
import kr.co.foreignlove.dao.BoardPromotionDAO;
import kr.co.foreignlove.dao.ReplyDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardVO;
import kr.co.foreignlove.vo.MemberVO;
import kr.co.foreignlove.vo.ReplyVO;

public class ReplyInsertService implements Service
{
	private ReplyDAO dao;
	
	public ReplyInsertService()
	{
		dao = (ReplyDAO)DAOManager.getDAO(ReplyDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strB_id = request.getParameter("b_id");
		int b_id = Integer.parseInt(strB_id);
		
		String content = request.getParameter("content");
		
		if(content == null || content.trim().equals(""))
		{
			throw new Exception("내용을 입력해주세요.");
		}
		
		String bt_type = request.getParameter("bt_type");
		
		String r_highId = request.getParameter("r_highId");

		BoardVO board = null;
		ReplyVO reply = new ReplyVO();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String now = sdf.format(calendar.getTime());
		reply.setR_post(now);
		
		reply.setR_content(content);
		reply.setR_delete(null);
		
		if(r_highId != null)
		{
			reply.setR_highId(dao.getReply(Integer.parseInt(r_highId)));
		}
		else
		{
			reply.setR_highId(null);
		}
		
		reply.setM_id((MemberVO)request.getSession(false).getAttribute("loginUserInfo"));
		
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

		reply.setB_id(board);
		
		if(!dao.insert(reply))
		{
			throw new Exception("댓글 작성에 실패하였습니다.");
		}
	}
}
