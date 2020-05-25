package kr.co.foreignlove.service.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.ReplyDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.ReplyVO;

public class ReplyUpdateService implements Service
{
	private ReplyDAO dao;
	
	public ReplyUpdateService()
	{
		dao = (ReplyDAO)DAOManager.getDAO(ReplyDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String strId = request.getParameter("r_id");
		int r_id = Integer.parseInt(strId);
		String content = request.getParameter("content");
		
		if(content == null || content.trim().equals(""))
		{
			throw new Exception("내용을 입력해주세요.");
		}
		
		ReplyVO reply = new ReplyVO();
		
		reply.setR_id(r_id);
		reply.setR_content(content);
		
		if(!dao.update(reply))
		{
			throw new Exception("게시물 수정에 실패하였습니다.");
		}
	}
}
