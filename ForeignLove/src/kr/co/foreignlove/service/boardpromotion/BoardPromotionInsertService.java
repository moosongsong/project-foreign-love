package kr.co.foreignlove.service.boardpromotion;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.AttachedDAO;
import kr.co.foreignlove.dao.BoardPromotionDAO;
import kr.co.foreignlove.dao.BoardTypeDAO;
import kr.co.foreignlove.dao.PromotionTypeDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.AttachedVO;
import kr.co.foreignlove.vo.BoardPromotionVO;
import kr.co.foreignlove.vo.MemberVO;

public class BoardPromotionInsertService implements Service
{
	private BoardPromotionDAO dao;
	private AttachedDAO adao;
	
	public BoardPromotionInsertService()
	{
		dao = (BoardPromotionDAO)DAOManager.getDAO(BoardPromotionDAO.NAME);
		adao = (AttachedDAO)DAOManager.getDAO(AttachedDAO.NAME);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String title = request.getParameter("title");
		int pTypeValue = Integer.parseInt(request.getParameter("pType"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String content = request.getParameter("content");
		
		String[] files = request.getParameterValues("files");
		String path = request.getServletContext().getRealPath("/uploadtemp");
		String pathOrig = request.getServletContext().getRealPath("/promotionuploads");
		File uploadtemp = new File(path);
		File move_dir = new File(pathOrig);
		
		
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
		
		
		if(endDate == null || endDate.trim().equals(""))
		{
			throw new Exception("종료일을 선택해주세요.");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date sDate = sdf.parse(startDate);
		Date eDate = sdf.parse(endDate);
		
		if(!(eDate.equals(sDate) || eDate.after(sDate)))
		{
			throw new Exception("종료일이 시작일보다 작습니다.");			
		}
		
		if(content == null || content.trim().equals(""))
		{
			throw new Exception("내용을 입력해주세요.");
		}
		
		BoardPromotionVO board = new BoardPromotionVO();
		
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String now = sdf.format(calendar.getTime());
		
		board.setB_title(title);
		board.setB_content(content);
		board.setB_post(now);
		board.setB_startDate(startDate);
		board.setB_endDate(endDate);
		board.setB_count(0);
		BoardTypeDAO btDAO = (BoardTypeDAO)DAOManager.getDAO(BoardTypeDAO.NAME);
		board.setBoardType(btDAO.getBoardTypeVO("PR"));
		
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
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("loginUserInfo");
		board.setMember(member);
		
		if(!dao.insert(board))
		{
			throw new Exception("게시물 등록에 실패하였습니다.");
		}
		
		request.setAttribute("board", board);
		
		AttachedVO attached = new AttachedVO();
		for(int i = 1 ; i<files.length; i++) {
			for (int j = 0; j<uploadtemp.list().length; j++) {
				
				if(uploadtemp.list()[j].equals(files[i])) {
					File moveFile = new File(move_dir, uploadtemp.listFiles()[j].getName());
					uploadtemp.listFiles()[j].renameTo(moveFile);
					
					attached.setA_name(files[i]);
					attached.setBoardType(btDAO.getBoardTypeVO("PR"));
					attached.setB_id(board.getB_id());
					adao.insert(attached);
				}
			}
		}
	}
}
