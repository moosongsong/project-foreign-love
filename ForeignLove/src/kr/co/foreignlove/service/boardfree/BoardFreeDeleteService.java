package kr.co.foreignlove.service.boardfree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.BoardDAO;
import kr.co.foreignlove.dao.BoardFreeDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.BoardFreeVO;

public class BoardFreeDeleteService implements Service{
	BoardDAO dao;
	BoardFreeVO board = null;
	public BoardFreeDeleteService() {
		dao = (BoardDAO)(DAOManager.getDAO(BoardFreeDAO.NAME));
	}
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int id;
		id =Integer.parseInt((request.getParameter("id")));
		//board들을 여러개를 등록했다면 내가 누른 board를 가져와야하는데 어떻게 가져올까
		boolean result = dao.delete(id);
		if(!result) {
			throw new Exception ("게시글 삭제에 실패하였습니다.");
		}
		
	}
}
