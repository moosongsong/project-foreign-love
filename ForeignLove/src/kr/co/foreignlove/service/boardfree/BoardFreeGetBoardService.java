package kr.co.foreignlove.service.boardfree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardDAO;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardFreeVO;

public class BoardFreeGetBoardService implements Service{
	private BoardDAO dao; 
	public BoardFreeGetBoardService() {
		dao = (BoardFreeDAO)(DAOManager.getDAO(BoardFreeDAO.NAME));
	}
	public void doService(HttpServletRequest request, HttpServletResponse response) {
		BoardFreeVO board = null;
		int id;
		id = Integer.parseInt(request.getParameter("b_id"));//ajax에서 보낸 data는 getParameter로 가져온다.
		System.out.println(id);
		board = (BoardFreeVO)dao.getBoard(id);
		request.setAttribute("board", board);
	}
}
