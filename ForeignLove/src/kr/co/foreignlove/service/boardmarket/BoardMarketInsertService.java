package kr.co.foreignlove.service.boardmarket;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.AttachedDAO;
import kr.co.foreignlove.dao.BoardMarketDAO;
import kr.co.foreignlove.dao.BoardTypeDAO;
import kr.co.foreignlove.dao.LowTypeDAO;
import kr.co.foreignlove.dao.MarketTypeDAO;
import kr.co.foreignlove.dao.TradeWayDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.AttachedVO;
import kr.co.foreignlove.vo.BoardMarketVO;
import kr.co.foreignlove.vo.LowTypeVO;
import kr.co.foreignlove.vo.MarketTypeVO;
import kr.co.foreignlove.vo.MemberVO;
import kr.co.foreignlove.vo.TradeWayVO;

public class BoardMarketInsertService implements Service{
	
	private BoardMarketDAO dao;
	private AttachedDAO adao;
	
	public BoardMarketInsertService() {
		dao=(BoardMarketDAO)DAOManager.getDAO(BoardMarketDAO.NAME);
		adao = (AttachedDAO)(DAOManager.getDAO(AttachedDAO.NAME));
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		String mk_title= request.getParameter("mk_title");
		String mk_type=request.getParameter("mk_type");
		String l_type=request.getParameter("l_type");
		String tw_type=request.getParameter("tw_type");
		String mk_price= request.getParameter("mk_price");
		String mk_content= request.getParameter("mk_content");
		
		String[] files = request.getParameterValues("files");
		String path = request.getServletContext().getRealPath("/uploadtemp");
		String pathOrig = request.getServletContext().getRealPath("/marketuploads");
		File uploadtemp = new File(path);
		File move_dir = new File(pathOrig);
		
		
		if(mk_title==null||mk_title.trim().equals("")) {
			throw new Exception("상품명을 포함한 제목을 입력하세요.");
		}
		
		if(mk_type.trim().equals("0"))
		{
			throw new Exception ("글분류를 선택하세요.");
		}
		
		if(l_type.trim().equals("0"))
		{
			throw new Exception ("소분류를 선택하세요.");
		}
		
		if(tw_type.trim().equals("0"))
		{
			throw new Exception ("거래방법을 선택하세요.");
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
	
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Calendar calendar = Calendar.getInstance();
		 String now = sdf.format(calendar.getTime());
		 
		 board.setB_title(mk_title);
		 board.setMk_price(mk_price);
		 board.setB_content(mk_content);
		 board.setB_post(now);
		 board.setB_count(0);
		 board.setMk_price(mk_price);
		 
		 BoardTypeDAO btDAO = (BoardTypeDAO)DAOManager.getDAO(BoardTypeDAO.NAME);
		 board.setBoardType(btDAO.getBoardTypeVO("MK"));
		
		 board.setMarketType(mt);
		
		 
		 board.setLowType(lt);	
		
		 
		 board.setTradeWay(tw);
		 

			HttpSession session = request.getSession();
			MemberVO member = (MemberVO)session.getAttribute("loginUserInfo");
			board.setMember(member);
			
		 if(!dao.insert(board)) {
			 throw new Exception("게시물 등록 실패");
		 }
	
			
		 AttachedVO attached = new AttachedVO();
			
			for(int i =1 ; i< files.length; i++) {
				
				for(int j=0; j< uploadtemp.list().length ; j++) {
					
					if(uploadtemp.list()[j].equals(files[i])) {
						System.out.println("---------------------");
						System.out.println(files[i]);
						System.out.println(uploadtemp.listFiles()[j]);
						System.out.println("성공");
						
						File moveFile = new File(move_dir, uploadtemp.listFiles()[j].getName());
						uploadtemp.listFiles()[j].renameTo(moveFile);
						
						attached.setA_name(files[i]);
						attached.setB_id(board.getB_id());
						attached.setBoardType(btDAO.getBoardTypeVO("MK"));
						
						boolean result = adao.insert(attached);
						System.out.println(result);
						board.setAttached(attached);
						
						System.out.println("---------------------");
					}
				}
			}
	}
}



