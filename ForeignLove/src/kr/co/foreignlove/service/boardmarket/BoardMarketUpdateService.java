package kr.co.foreignlove.service.boardmarket;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.co.foreignlove.controller.DAOManager;

import kr.co.foreignlove.dao.BoardMarketDAO;

import kr.co.foreignlove.dao.LowTypeDAO;
import kr.co.foreignlove.dao.MarketTypeDAO;
import kr.co.foreignlove.dao.TradeWayDAO;
import kr.co.foreignlove.service.Service;

import kr.co.foreignlove.vo.BoardMarketVO;
import kr.co.foreignlove.vo.LowTypeVO;
import kr.co.foreignlove.vo.MarketTypeVO;

import kr.co.foreignlove.vo.TradeWayVO;

public class BoardMarketUpdateService implements Service{

	BoardMarketDAO dao;
	public BoardMarketUpdateService() {
		dao = (BoardMarketDAO)DAOManager.getDAO(BoardMarketDAO.NAME);
	}
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String title= request.getParameter("title");
		String mk_type=request.getParameter("mk_type");
		String l_type=request.getParameter("l_type");
		String tw_type=request.getParameter("tw_type");
		String mk_price= request.getParameter("mk_price");
		String mk_content= request.getParameter("content");
		int b_id = Integer.parseInt(request.getParameter("id"));
		
		
		if(title==null||title.trim().equals("")) {
			throw new Exception("상품명을 포함한 제목을 입력하세요.");
		}
		
		if(mk_price==null||mk_price.trim().equals("")) {
			throw new Exception ("상품가격을 입력하세요.");
		}
		
		if(mk_content==null||mk_content.trim().equals("")) {
			throw new Exception ("상품 상세 설명을 입력하세요.");
		}
		
		 BoardMarketVO board = new BoardMarketVO();
		 
		 MarketTypeVO mt = new MarketTypeVO();
		 mt=((MarketTypeDAO)(DAOManager.getDAO(MarketTypeDAO.NAME))).find(mk_type);

		 
		 LowTypeVO lt = new LowTypeVO();
		 lt=((LowTypeDAO)(DAOManager.getDAO(LowTypeDAO.NAME))).find(l_type);
		
		 
		 TradeWayVO tw = new TradeWayVO();
		 tw=((TradeWayDAO)(DAOManager.getDAO(TradeWayDAO.NAME))).find(tw_type);
	
		 
		 
		 board.setB_title(title);
		 board.setMarketType(mt);
		 board.setLowType(lt);	
		 board.setTradeWay(tw);
		 board.setMk_price(mk_price);
		 board.setB_content(mk_content);
		 board.setB_id(b_id);
		 
		if(!dao.update(board)) {
			throw new Exception("게시물 수정에 실패하였습니다.");
		}
		
		
	}
	
}
